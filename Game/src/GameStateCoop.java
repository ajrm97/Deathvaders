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
 * Cooperative mode can be easy to implement
 */

public class GameStateCoop implements GameState{
	GameContext gc;
	Settings opciones;
	ImageLoader images;
	Score s;
	Thread score;
	Player p;
	Player p2;
	Objects obj;
	int ban;
	int posX;
	int posY;
	int posX2;
	int posY2;
	int casillaX;
	int casillaY;
	int timer;
	int timerVida;
	int timerSlow;
	int timerCoop;
	int timerReset;
	int banCoop;
	Random r;
	Font fuente;
	
	public GameStateCoop(GameContext gc){
		this.gc = gc;
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		ban = 0;
		banCoop = 5;
		s = new Score();
		p = new Player(1);
		p2 = new Player(2);
		obj = new Objects(p, p2);
		score = new Thread(s);
		opciones.setPU1(-1);
		opciones.setPU2(-1);
		opciones.setPCoop(-1);
		posX = p.getPosX();
		posY = p.getPosY();
		posX2 = p2.getPosX();
		posY2 = p2.getPosY();
		casillaX = 2;
		casillaY = 2;
		timer = 0;
		timerSlow = 0;
		timerCoop = 0;
		timerReset = 0;
		timerVida = 0;
		opciones.setSpeed(0);
		r = new Random();
	}
	public void gameOver(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		opciones.setScore(0);
		gc.rebootState(3);
	}
	public void gameOver2(){
		if(opciones.getScore() > opciones.getHs2()) {
			opciones.setHs2(opciones.getScore());
		}
		opciones.setTp1(0);
		opciones.setTp2(0);
		gc.rebootState(3);
	}
	public void paused(){
		opciones.setPaused(1);
		gc.rebootState(2);
	}
	
	public void running(){
		opciones.setTp1(0);
		opciones.setTp2(0);
		gc.rebootState(4);
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
		g.drawImage(images.getImage("plat"), 200, 100, 600, 600, null);
		fuente = new Font("TimesRoman", Font.BOLD, 30);
		g.setFont(fuente);
		g.setColor(Color.black);
		//g.drawString("Timer: " + Integer.toString(timerCoop), 150, 50);
		//g.drawString("Reset: " + Integer.toString(timerReset), 150, 150);
		g.drawString("Left: " + Integer.toString(banCoop), 15, 135);
		s.render(g);
		if(opciones.getTp2() == 2) {
			g.drawImage(images.getImage("portal2"), posX2, posY2, 90, 90, null);
		}
		if(opciones.getTp1() == 2) {
			g.drawImage(images.getImage("portal1"), posX, posY, 90, 90, null);
		}
		if(opciones.getPU1() == 3 || opciones.getPU2() == 3 || opciones.getPCoop() == 3) {
			if(p.getVida() > 0) {
				g.drawImage(images.getImage("laserX"), -70, p.getPosY() - 100, opciones.getWidth() + 115, 300, null);
				g.drawImage(images.getImage("laserY"), p.getPosX() - 100, -70, 300, opciones.getHeight() + 115, null);
			}
			if(p2.getVida() > 0) {
				g.drawImage(images.getImage("laserX"), -70, p2.getPosY() - 100, opciones.getWidth() + 115, 300, null);
				g.drawImage(images.getImage("laserY"), p2.getPosX() - 100, -70, 300, opciones.getHeight() + 115, null);
			}
			timer++;
			if (timer > 2) {
				opciones.setPU1(-1);
				opciones.setPU2(-1);
				opciones.setPCoop(-1);
				timer = 0;
			}
		}
		if(opciones.getPU1() == 6) {
			if(timerVida % 15 >= 0 && timerVida % 15 < 7) {
				p2.render(g);
			}
			p.render(g);
		}			
		else if(opciones.getPU2() == 6) {
			if(timerVida % 15 >= 0 && timerVida % 15 < 7) {
				p.render(g);
			}
			p2.render(g);
		}
		else {
			p2.render(g);
			p.render(g);
		}
		obj.render(g);
		if(opciones.getPU1() == 5 || opciones.getPU2() == 5 || opciones.getPCoop() == 5) {
			g.drawImage(images.getImage("boom"), 50, 0, 900, 850, null);
			timer++;
			if (timer > 6) {
				opciones.setPU1(-1);
				opciones.setPU2(-1);
				opciones.setPCoop(-1);
				timer = 0;
			}
		}
		if((timerReset > 250 || banCoop == 5) && banCoop > 0) {
			g.drawImage(images.getImage("team"), 10, 10, 100, 100, null);
		}
	}
	
	public void update(){
		if(!score.isAlive() && ban == 0){
			score.start();
			obj.start();
			ban = 1;
		}
		if(banCoop > 0) {
			if(timerReset > 250 || banCoop == 5) {
				if(p.getVida() > 0 && p2.getVida() > 0) {
					if(p.getCasillaX() == p2.getCasillaX() && p.getCasillaY() == p2.getCasillaY()){
						timerCoop++;
						if(timerCoop > 60) {
							r = new Random();
							opciones.setPCoop(r.nextInt(6));
							timerCoop = 0;
							banCoop--;
							timerReset = 0;
						}
					}
					else {
						timerCoop = 0;
					}
				}
			}
			else {
				timerReset++;
			}
		}
		if(opciones.getPU1() == 0 || opciones.getPU2() == 0 || opciones.getPCoop() == 0){
			if(p.getVida() > 0) {
				p.setSh(true);
				p.setVida(2);
			}
			if(p2.getVida() > 0) {
				p2.setSh(true);
				p2.setVida(2);
			}			
			opciones.setPU1(-1);
			opciones.setPU2(-1);
			opciones.setPCoop(-1);
		}
		if(opciones.getPU1() == 1 || opciones.getPU2() == 1 || opciones.getPCoop() == 1){
			if(p.getVida() > 0) {
				if(opciones.getTp1() != 2) {
					opciones.setTp1(1);
					p.setTp(true);
				}
			}
			if(p2.getVida() > 0) {
				if(opciones.getTp2() != 2) {
					opciones.setTp2(1);
					p2.setTp(true);
				}
			}
			opciones.setPU2(-1);
			opciones.setPU1(-1);
			opciones.setPCoop(-1);
		}
		if(opciones.getPU1() == 3){
			obj.destroy(p.getCasillaX(), p.getCasillaY());
			if(p2.getVida() > 0) {
				obj.destroy(p2.getCasillaX(), p2.getCasillaY());
			}
		}
		if(opciones.getPU2() == 3){
			if(p.getVida() > 0) {
				obj.destroy(p.getCasillaX(), p.getCasillaY());
			}			
			obj.destroy(p2.getCasillaX(), p2.getCasillaY());
		}
		if(opciones.getPCoop() == 3) {
			if(p.getVida() > 0) {
				obj.destroy(p.getCasillaX(), p.getCasillaY());
			}
			if(p2.getVida() > 0) {
				obj.destroy(p2.getCasillaX(), p2.getCasillaY());
			}
		}
		if(opciones.getPU1() == 4 || opciones.getPU2() == 4 || opciones.getPCoop() == 4){
			if(((opciones.getScore() / 100) + 1) > 10){
				opciones.setSpeed(5);
			}
			else{
				opciones.setSpeed(((opciones.getScore() / 100) + 1) / 2);
			}
			timerSlow++;
			if (timerSlow > 225) {
				opciones.setPU1(-1);
				opciones.setPU2(-1);
				opciones.setPCoop(-1);
				opciones.setSpeed(0);
				timerSlow = 0;
			}
		}
		if(opciones.getPU1() == 5 || opciones.getPU2() == 5 || opciones.getPCoop() == 5){
			obj.nuke();
		}
		if(opciones.getPU1() == 6){
			p2.setVida(100);
			timerVida++;
			if(timerVida > 100) {
				p2.setVida(1);
				opciones.setPU1(-1);
				timerVida = 0;
			}
		}
		if(opciones.getPU2() == 6){
			p.setVida(100);
			timerVida++;
			if(timerVida > 100) {
				p.setVida(1);
				opciones.setPU2(-1);
				timerVida = 0;
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
			p.moveRight();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_A){
			p.moveLeft();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_W){
			p.moveUp();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_S){
			p.moveDown();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE){
			paused();
		}
		else if(keyCode == KeyEvent.VK_SPACE){
			if(opciones.getTp1() == 1 && p.getVida() > 0) {
				posX = p.getPosX();
				posY = p.getPosY();
				opciones.setTp1(2);
				p.setTp(false);
			}
			else if(opciones.getTp1() == 2 && p.getVida() > 0) {
				p.move(posX, posY);
				p.casilla();
				opciones.setTp1(0);
			}
		}
		else if(keyCode == KeyEvent.VK_RIGHT){
			p2.moveRight();
			p2.casilla();
		}
		else if(keyCode == KeyEvent.VK_LEFT){
			p2.moveLeft();
			p2.casilla();
		}
		else if(keyCode == KeyEvent.VK_UP){
			p2.moveUp();
			p2.casilla();
		}
		else if(keyCode == KeyEvent.VK_DOWN){
			p2.moveDown();
			p2.casilla();
		}
		else if(keyCode == KeyEvent.VK_SHIFT){
			if(opciones.getTp2() == 1 && p2.getVida() > 0) {
				posX2 = p2.getPosX();
				posY2 = p2.getPosY();
				opciones.setTp2(2);
				p2.setTp(false);
			}
			else if(opciones.getTp2() == 2 && p2.getVida() > 0) {
				p2.move(posX2, posY2);
				p2.casilla();
				opciones.setTp2(0);
			}
		}
	}
	
	public void kReleased (KeyEvent e){
		
	}
}
