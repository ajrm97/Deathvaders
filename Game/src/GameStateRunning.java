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

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/*
 * Most important class that has all the game related rules and is basically the
 * state in which you can play 
 */

//It implements our GameState interface
public class GameStateRunning implements GameState{
	GameContext gc;
	Settings opciones;
	ImageLoader images;
	Score s;
	Thread score;
	Player p;
	Objects obj;
	int ban;
	int posX;
	int posY;
	int casillaX;
	int casillaY;
	int timer;
	int timerSlow;
	
	//Constructor
	public GameStateRunning(GameContext gc){
		this.gc = gc;
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		ban = 0;
		s = new Score();
		p = new Player();
		obj = new Objects(p);
		score = new Thread(s);
		opciones.setPU1(-1);
		posX = p.getPosX();
		posY = p.getPosY();
		casillaX = 2;
		casillaY = 2;
		timer = 0;
		timerSlow = 0;
		opciones.setSpeed(0);
	}
	
	//Optional change of State when another game over is achived
	public void gameOver(){
		opciones.setScore(0);
		opciones.setTp1(0);
		gc.rebootState(3);
	}
	
	//Change State when gameOver
	public void gameOver2(){
		if(opciones.getScore() > opciones.getHs1()) {
			opciones.setHs1(opciones.getScore());
		}
		opciones.setTp1(0);
		gc.rebootState(3);
	}
	
	//Change State when paused
	public void paused(){
		opciones.setPaused(1);
		gc.rebootState(2);
	}
	
	//Reboot state when replay button is hit
	public void running(){
		opciones.setTp1(0);
		gc.rebootState(1);
	}
	
	//Change State when menu button is clicked
	public void starting(){
		opciones.setTp1(0);
		opciones.setScore(0);
		gc.changeState(0);
	}
	
	public void coop(){
		
	}
	public void versus(){
		
	}
	
	//Render all things needed to visualize the game
	public void render(Graphics g){
		g.drawImage(images.getImage("plat"), 200, 100, 600, 600, null);
		s.render(g);
		if(opciones.getTp1() == 2) {
			g.drawImage(images.getImage("portal1"), posX, posY, 90, 90, null);
		}
		if(opciones.getPU1() == 3) {
			g.drawImage(images.getImage("laserX"), -70, p.getPosY() - 100, opciones.getWidth() + 115, 300, null);
			g.drawImage(images.getImage("laserY"), p.getPosX() - 100, -70, 300, opciones.getHeight() + 115, null);
			timer++;
			if (timer > 2) {
				opciones.setPU1(-1);
				timer = 0;
			}
		}
		p.render(g);
		obj.render(g);
		if(opciones.getPU1() == 5) {
			g.drawImage(images.getImage("boom"), 50, 0, 900, 850, null);
			timer++;
			if (timer > 6) {
				opciones.setPU1(-1);
				timer = 0;
			}
		}
	}
	
	//This update will occur every 20 milliseconds
	//Used to check for Power-Ups or game changing stuff
	public void update(){
		if(!score.isAlive() && ban == 0){
			score.start();
			obj.start();
			ban = 1;
		}
		if(opciones.getPU1() == 0){
			p.setSh(true);
			p.setVida(2);
		}
		if(opciones.getPU1() == 1){
			if(opciones.getTp1() != 2) {
				opciones.setTp1(1);
				p.setTp(true);
			}
			opciones.setPU1(-1);
		}
		if(opciones.getPU1() == 3){
			obj.destroy(p.getCasillaX(), p.getCasillaY());
		}
		if(opciones.getPU1() == 4){
			if(((opciones.getScore() / 100) + 1) > 10){
				opciones.setSpeed(5);
			}
			else{
				opciones.setSpeed(((opciones.getScore() / 100) + 1) / 2);
			}
			timerSlow++;
			if (timerSlow > 225) {
				opciones.setPU1(-1);
				opciones.setSpeed(0);
				timerSlow = 0;
			}
		}
		if(opciones.getPU1() == 5){
			obj.nuke();
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
	
	//What to do when the keys are typed
	public void kPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			p.moveRight();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			p.moveLeft();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			p.moveUp();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			p.moveDown();
			p.casilla();
		}
		else if(keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE){
			paused();
		}
		else if(keyCode == KeyEvent.VK_SPACE){
			if(opciones.getTp1() == 1) {
				posX = p.getPosX();
				posY = p.getPosY();
				p.setTp(false);
				opciones.setTp1(2);
			}
			else if(opciones.getTp1() == 2) {
				p.move(posX, posY);
				p.casilla();
				opciones.setTp1(0);
			}
		}
	}
	
	public void kReleased (KeyEvent e){
		
	}
}
