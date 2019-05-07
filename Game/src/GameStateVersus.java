/*
						Deathvaders

	A small video game based on the Bullet Hell genre.
	
	Try to survive the longest on your own or grab a friend
	and get the highest score in the cooperative mode, or
	see who's the best in the versus mode.
	
	Remember to use the power-ups to improve your chances.
	
	Enjoy :D

	Copyright (C) 2019  Armando Josué Ruiz Muñoz

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * This class is the same as the Running State but with some modifications so that 
 * Versus mode can be easy to implement
 */

public class GameStateVersus implements GameState{
	GameContext gc;
	Settings opciones;
	ImageLoader images;
	Score s;
	Thread score;
	Player p;
	Player p2;
	ObjectsVS obj;
	int ban;
	int posX;
	int posY;
	int posX2;
	int posY2;
	int casillaX;
	int casillaY;
	int casillaX2;
	int casillaY2;
	int timer;
	int timerSlow;
	int timerNuke;
	int timerTp1;
	int timerTp2;
	boolean gO;
	int tp1;
	int tp2;
	int sh1;
	int sh2;
	Random r;
	Font fuente;
	
	public GameStateVersus(GameContext gc){
		this.gc = gc;
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		ban = 0;
		tp1 = 0;
		tp2 = 0;
		sh1 = 0;
		sh2 = 0;
		s = new Score();
		p = new Player(1);
		p2 = new Player(2);
		obj = new ObjectsVS(p, p2);
		score = new Thread(s);
		opciones.setPU1(-1);
		opciones.setPU2(-1);
		opciones.setTp1(0);
		opciones.setTp2(0);
		opciones.setNuke(0);
		opciones.setNuke2(0);
		posX = p.getPosX();
		posY = p.getPosY();
		posX2 = p2.getPosX();
		posY2 = p2.getPosY();
		casillaX = 2;
		casillaY = 2;
		casillaX2 = 2;
		casillaY2 = 2;
		timer = 0;
		timerSlow = 0;
		timerTp1 = 0;
		timerTp2 = 0;
		timerNuke = 0;
		gO = false;
		opciones.setSpeed(0);
		r = new Random();
	}
	public void gameOver(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		opciones.setScore(0);
		gc.rebootState(5);
	}
	public void gameOver2(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		opciones.setScore(0);
		if(opciones.getWinner() == 1) {
			opciones.setWins1(opciones.getWins1() + 1);
		}
		else {
			opciones.setWins2(opciones.getWins2() + 1);
		}
		gc.rebootState(5);
	}
	public void paused(){
		opciones.setPaused(1);
		gc.rebootState(2);
	}
	
	public void running(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		gc.rebootState(6);
	}
	public void starting(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		opciones.setScore(0);
		gc.changeState(0);
	}
	public void coop(){
		
	}
	public void versus(){
		
	}
	
	public void render(Graphics g){
		fuente = new Font("TimesRoman", Font.BOLD, 50);
		g.setFont(fuente);
		g.setColor(Color.black);
		g.drawImage(images.getImage("plat"), 200, 100, 600, 600, null);
//		g.drawString("PU1: " + Integer.toString(opciones.getPU1()), 150, 150);
//		g.drawString("PU2: " + Integer.toString(opciones.getPU2()), 150, 250);
//		g.drawString("GO: " + Boolean.toString(gO), 150, 350);
		s.render(g);
		if(opciones.getTp2() == 2 || opciones.getTp2() == 3) {
			g.drawImage(images.getImage("portal2"), posX2, posY2, 90, 90, null);
		}
		if(opciones.getTp1() == 2 || opciones.getTp1() == 3) {
			g.drawImage(images.getImage("portal1"), posX, posY, 90, 90, null);
		}
		if(opciones.getPU1() == 7) {
			if(p.getVida() > 0) {
				g.drawImage(images.getImage("laserX"), -70, p.getPosY() - 100, opciones.getWidth() + 115, 300, null);
				g.drawImage(images.getImage("laserY"), p.getPosX() - 100, -70, 300, opciones.getHeight() + 115, null);
			}
			timer++;
			if (timer > 2) {
				opciones.setPU1(-1);
				timer = 0;
				if(gO){
					opciones.setGO(1);
				}
			}
		}
		if(opciones.getPU2() == 7) {
			if(p2.getVida() > 0) {
				g.drawImage(images.getImage("laserX"), -70, p2.getPosY() - 100, opciones.getWidth() + 115, 300, null);
				g.drawImage(images.getImage("laserY"), p2.getPosX() - 100, -70, 300, opciones.getHeight() + 115, null);
			}
			timer++;
			if (timer > 2) {
				opciones.setPU2(-1);
				timer = 0;
				if(gO){
					opciones.setGO(1);
				}
			}
		}
		p2.render(g);
		p.render(g);
		if(p.getSh() && sh1 > 0) {
			g.drawString(Integer.toString(sh1), p.getPosX() + 45, p.getPosY() + 75);
		}
		if(p2.getSh() && sh2 > 0) {
			g.drawString(Integer.toString(sh2), p2.getPosX() + 45, p2.getPosY() + 75);
		}
		obj.render(g);
		if(opciones.getPU1() == 6 || opciones.getPU2() == 6) {
			g.drawImage(images.getImage("boom"), 50, 0, 900, 850, null);
			timer++;
			if (timer > 6) {
				opciones.setPU1(-1);
				opciones.setPU2(-1);
				timer = 0;
				if(gO){
					opciones.setGO(1);
				}
			}
		}
	}
	
	public void update(){
		if(!score.isAlive() && ban == 0){
			score.start();
			obj.start();
			ban = 1;
		}
		if(opciones.getPU1() == 0){
			p.setSh(true);
			p.setVida(2);
			sh1 = 3;
			opciones.setPU1(-1);
		}
		if(opciones.getPU2() == 0){
			p2.setSh(true);
			p2.setVida(2);
			sh2 = 3;
			opciones.setPU2(-1);
		}
		if(opciones.getPU1() == 1){
			if(opciones.getTp1() != 2) {
				opciones.setTp1(1);
				p.setTp(true);
			}
			opciones.setPU1(-1);
		}
		if(opciones.getPU2() == 1){
			if(opciones.getTp2() != 2) {
				opciones.setTp2(1);
				p2.setTp(true);
			}
			opciones.setPU2(-1);
		}
		if(opciones.getTp1() == 3) {
			if(timerTp1 > 99) {
				timerTp1 = 0;
				opciones.setTp1(2);
			}
			else {
				timerTp1++;
			}
		}
		if(opciones.getTp2() == 3) {
			if(timerTp2 > 99) {
				timerTp2 = 0;
				opciones.setTp2(2);
			}
			else {
				timerTp2++;
			}
		}
		if(opciones.getPU1() == 2){
			p2.setIce(true);
			timerSlow++;
			if(timerSlow > 276) {
				p2.setIce(false);
				opciones.setPU1(-1);
				timerSlow = 0;
			}
		}
		if(opciones.getPU2() == 2){
			p.setIce(true);
			timerSlow++;
			if(timerSlow > 276) {
				p.setIce(false);
				opciones.setPU2(-1);
				timerSlow = 0;
			}
		}
		if(opciones.getPU1() == 3){
			obj.destroy(p.getCasillaX(), p.getCasillaY());
			opciones.setPU1(7);
			if(p.getCasillaX() == p2.getCasillaX() || p.getCasillaY() == p2.getCasillaY()) {
				if(p2.getVida() == 1) {
					opciones.setWinner(1);
					gO = true;
				}
				else {
					p2.setSh(false);
					p2.setVida(1);
				}
			}
			if((casillaX2 == p.getCasillaX() || casillaY2 == p.getCasillaY()) && opciones.getTp2() == 2) {
				opciones.setTp2(0);
			}
		}
		if(opciones.getPU2() == 3){			
			obj.destroy(p2.getCasillaX(), p2.getCasillaY());
			opciones.setPU2(7);
			if(p.getCasillaX() == p2.getCasillaX() || p.getCasillaY() == p2.getCasillaY()) {
				if(p.getVida() == 1) {
					opciones.setWinner(2);
					gO = true;
				}
				else {
					p.setSh(false);
					p.setVida(1);
				}
			}
			if((casillaX == p2.getCasillaX() || casillaY == p2.getCasillaY()) && opciones.getTp1() == 2) {
				opciones.setTp1(0);
			}
		}
		if(opciones.getPU1() == 4){
			p2.setCf(true);
			timer++;
			if(timer > 156) {
				opciones.setPU1(-1);
				timer = 0;
				p2.setCf(false);
			}
		}
		if(opciones.getPU2() == 4){
			p.setCf(true);
			timer++;
			if(timer > 156) {
				opciones.setPU2(-1);
				timer = 0;
				p.setCf(false);
			}
		}
		if(opciones.getPU1() == 5){
			//obj.nuke();
			opciones.setPU1(6);
			opciones.setNuke(r.nextInt(2) + 1);
			if(opciones.getTp2() == 2) {
				opciones.setTp2(0);
			}
			if(p2.getVida() == 1) {
				opciones.setWinner(1);
				gO = true;
			}
			else {
				p2.setSh(false);
				p2.setVida(1);
			}
		}
		if(opciones.getPU2() == 5){
			//obj.nuke();
			opciones.setPU2(6);
			opciones.setNuke2(r.nextInt(2) + 1);
			if(opciones.getTp1() == 2) {
				opciones.setTp1(0);
			}
			if(p.getVida() == 1) {
				opciones.setWinner(2);
				gO = true;
			}
			else {
				p.setSh(false);
				p.setVida(1);
			}
		}
		if(opciones.getNuke() == 1){
			p2.setIce(true);
			timerNuke++;
			if(timerNuke > 751) {
				p2.setIce(false);
				opciones.setNuke(0);
				timerNuke = 0;
			}
		}
		if(opciones.getNuke2() == 1){
			p.setIce(true);
			timerNuke++;
			if(timerNuke > 751) {
				p.setIce(false);
				opciones.setNuke2(0);
				timerNuke = 0;
			}
		}
		if(opciones.getNuke() == 2){
			p2.setCf(true);
			timerNuke++;
			if(timerNuke > 501) {
				opciones.setNuke(0);
				timerNuke = 0;
				p2.setCf(false);
			}
		}
		if(opciones.getNuke2() == 2){
			p.setCf(true);
			timerNuke++;
			if(timerNuke > 501) {
				opciones.setNuke2(0);
				timerNuke = 0;
				p.setCf(false);
			}
		}
		if(opciones.getGO() == 1) {
			opciones.setGO(0);
			score.interrupt();
			try {
				score.join();
			} catch (InterruptedException e) {}
			ban = 0;
			obj.stop();
			gameOver2();
		}
		if(opciones.getPaused() == 1) {
			opciones.setPaused(0);
			s.resume();
			obj.resume();
			
		}
		if(opciones.getPMenu() == 1) {
			opciones.setPMenu(0);
			score.interrupt();
			try {
				score.join();
			} catch (InterruptedException e) {}
			ban = 0;
			obj.stop();
			starting();
		}
		if(opciones.getPGO() == 1) {
			opciones.setPGO(0);
			score.interrupt();
			try {
				score.join();
			} catch (InterruptedException e) {}
			ban = 0;
			obj.stop();
			running();
		}
	}
	
	public void mPressed(int x, int y){
	}
	
	public void kPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_D){
			if(opciones.getPU2() != 2 && opciones.getNuke2() != 1) {
				if(opciones.getPU2() != 4 && opciones.getNuke2() != 2) {
					p.moveRight();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveLeft();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaX() != 4) {
								p2.moveRight();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveLeft();
								p.casilla();
							}
						}
						else {
							p.moveLeft();
							p.casilla();
						}
					}
				}
				else {
					p.moveLeft();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveRight();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaX() != 0) {
								p2.moveLeft();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveRight();
								p.casilla();
							}
						}
						else {
							p.moveRight();
							p.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU2() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke2() == 1){
					 timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_A){
			if(opciones.getPU2() != 2 && opciones.getNuke2() != 1) {
				if(opciones.getPU2() != 4 && opciones.getNuke2() != 2) {
					p.moveLeft();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveRight();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaX() != 0) {
								p2.moveLeft();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveRight();
								p.casilla();
							}
						}
						else {
							p.moveRight();
							p.casilla();
						}
					}
				}
				else {
					p.moveRight();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveLeft();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaX() != 4) {
								p2.moveRight();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveLeft();
								p.casilla();
							}
						}
						else {
							p.moveLeft();
							p.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU2() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke2() == 1){
					 timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_W){
			if(opciones.getPU2() != 2 && opciones.getNuke2() != 1) {
				if(opciones.getPU2() != 4 && opciones.getNuke2() != 2) {
					p.moveUp();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveDown();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaY() != 0) {
								p2.moveUp();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveDown();
								p.casilla();
							}
						}
						else {
							p.moveDown();
							p.casilla();
						}
					}
				}
				else {
					p.moveDown();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveUp();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaY() != 4) {
								p2.moveDown();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveUp();
								p.casilla();
							}
						}
						else {
							p.moveUp();
							p.casilla();
						}
					}
				}
			}
			else{
				if(opciones.getPU2() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke2() == 1){
					 timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_S){
			if(opciones.getPU2() != 2 && opciones.getNuke2() != 1) {
				if(opciones.getPU2() != 4 && opciones.getNuke2() != 2) {
					p.moveDown();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveUp();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaY() != 4) {
								p2.moveDown();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveUp();
								p.casilla();
							}
						}
						else {
							p.moveUp();
							p.casilla();
						}
					}
				}
				else {
					p.moveUp();
					p.casilla();
					if(p.getCasillaX() == casillaX2 && p.getCasillaY() == casillaY2 && (opciones.getTp2() == 2 || opciones.getTp2() == 3)) {
						p.moveDown();
						p.casilla();
						tp2--;
						if(tp2 <= 0) {
							opciones.setTp2(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh1 > 0 && p.getSh()) {
							if(p2.getCasillaY() != 0) {
								p2.moveUp();
								p2.casilla();
								sh1--;
							}
							else{
								p.moveDown();
								p.casilla();
							}
						}
						else {
							p.moveDown();
							p.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU2() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke2() == 1){
					 timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE){
			paused();
		}
		else if(keyCode == KeyEvent.VK_SPACE){
			if(opciones.getTp1() == 1 && p.getVida() > 0) {
				posX = p.getPosX();
				posY = p.getPosY();
				casillaX = p.getCasillaX();
				casillaY = p.getCasillaY();
				tp1 = 10;
				opciones.setTp1(3);
				p.setTp(false);
			}
			else if(opciones.getTp1() == 2 && p.getVida() > 0) {
				if(casillaX != p2.getCasillaX() || casillaY != p2.getCasillaY()) {
					p2.move(posX, posY);
					p2.casilla();
					opciones.setTp1(0);
				}
			}
		}
		else if(keyCode == KeyEvent.VK_RIGHT){
			if(opciones.getPU1() != 2 && opciones.getNuke() != 1) {
				if(opciones.getPU1() != 4 && opciones.getNuke() != 2) {
					p2.moveRight();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveLeft();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaX() != 4) {
								p.moveRight();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveLeft();
								p2.casilla();
							}
						}
						else {
							p2.moveLeft();
							p2.casilla();
						}
					}
				}
				else {
					p2.moveLeft();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveRight();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaX() != 0) {
								p.moveLeft();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveRight();
								p2.casilla();
							}
						}
						else {
							p2.moveRight();
							p2.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU1() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke() == 1){
					timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_LEFT){
			if(opciones.getPU1() != 2 && opciones.getNuke() != 1) {
				if(opciones.getPU1() != 4 && opciones.getNuke() != 2) {
					p2.moveLeft();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveRight();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaX() != 0) {
								p.moveLeft();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveRight();
								p2.casilla();
							}
						}
						else {
							p2.moveRight();
							p2.casilla();
						}
					}
				}
				else {
					p2.moveRight();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveLeft();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaX() != 4) {
								p.moveRight();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveLeft();
								p2.casilla();
							}
						}
						else {
							p2.moveLeft();
							p2.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU1() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke() == 1){
					timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_UP){
			if(opciones.getPU1() != 2 && opciones.getNuke() != 1) {
				if(opciones.getPU1() != 4 && opciones.getNuke() != 2) {
					p2.moveUp();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveDown();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaY() != 0) {
								p.moveUp();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveDown();
								p2.casilla();
							}
						}
						else {
							p2.moveDown();
							p2.casilla();
						}
					}
				}
				else {
					p2.moveDown();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveUp();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaY() != 4) {
								p.moveDown();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveUp();
								p2.casilla();
							}
						}
						else {
							p2.moveUp();
							p2.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU1() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke() == 1){
					timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_DOWN){
			if(opciones.getPU1() != 2 && opciones.getNuke() != 1) {
				if(opciones.getPU1() != 4 && opciones.getNuke() != 2) {
					p2.moveDown();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveUp();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaY() != 4) {
								p.moveDown();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveUp();
								p2.casilla();
							}
						}
						else {
							p2.moveUp();
							p2.casilla();
						}
					}
				}
				else {
					p2.moveUp();
					p2.casilla();
					if(p2.getCasillaX() == casillaX && p2.getCasillaY() == casillaY && (opciones.getTp1() == 2 || opciones.getTp1() == 3)) {
						p2.moveDown();
						p2.casilla();
						tp1--;
						if(tp1 <= 0) {
							opciones.setTp1(0);
						}
					}
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()) {
						if(sh2 > 0 && p2.getSh()) {
							if(p.getCasillaY() != 0) {
								p.moveUp();
								p.casilla();
								sh2--;
							}
							else{
								p2.moveDown();
								p2.casilla();
							}
						}
						else {
							p2.moveDown();
							p2.casilla();
						}
					}
				}
			}
			else {
				if(opciones.getPU1() == 2) {
					timerSlow += 10;
				}
				else if(opciones.getNuke() == 1){
					timerNuke += 10;
				}
			}
		}
		else if(keyCode == KeyEvent.VK_SHIFT){
			if(opciones.getTp2() == 1 && p2.getVida() > 0) {
				posX2 = p2.getPosX();
				posY2 = p2.getPosY();
				casillaX2 = p2.getCasillaX();
				casillaY2 = p2.getCasillaY();
				opciones.setTp2(3);
				tp2 = 10;
				p2.setTp(false);
			}
			else if(opciones.getTp2() == 2 && p2.getVida() > 0) {
				if(casillaX2 != p.getCasillaX() || casillaY2 != p.getCasillaY()) {
					p.move(posX2, posY2);
					p.casilla();
					opciones.setTp2(0);
				}
			}
		}
	}
	
	public void kReleased (KeyEvent e){
		
	}
}
