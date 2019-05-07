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

import java.awt.image.BufferedImage;

/*
 * The Singleton class that keeps the game in place, with this class
 * it is easy to pass values between Threads and the Main Game Thread
 * It has all variables needed, their Setters and their Getters
 */

public class Settings {
	
	private static Settings opciones = null;
	static boolean firstThread = true;
	private int silence, vSFX, vMusic, score, winner, paused, pMenu, pGO, gO, gO2, pU1, pU2, tp1, tp2;
	int speed, hs1, hs2, nuke, nuke2, shape, shape2, pCoop, wins1, wins2; 
	boolean coop, vs;
	BufferedImage player1, player2;
	ImageLoader images;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 800;
	
	private Settings(){
		images = ImageLoader.getInstance();
		silence = 0;
		vSFX = 100;
		vMusic = 100;
		score = 0;
		winner = 0;
		paused = 0;
		pMenu = 0;
		pGO = 0;
		gO = 0;
		gO2 = 0;
		pU1 = -1;
		pU2 = -1;
		pCoop = -1;
		tp1 = 0;
		tp2 = 0;
		speed = 0;
		hs1 = 0;
		hs2 = 0;
		coop = false;
		nuke = 0;
		nuke2 = 0;
		shape = 0;
		shape2 = 1;
		wins1 = 0;
		wins2 = 0;
		setImage();
		setImage2();
	}
	public static Settings getInstance(){
		
		if (opciones == null){
			if (firstThread){
				firstThread = false;
				
				Thread.currentThread();
				try { 
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
			}
			synchronized(Settings.class){
				if (opciones == null){
					opciones = new Settings();  
				}
			}
		}
		return opciones;
	}
	
	public void setImage() {
		if (shape == 0){
			player1 = images.getImage("player1");
		}
		else if(shape == 1){
			player1 = images.getImage("player2");
		}
		else if(shape == 2){
			player1 = images.getImage("player3");
		}
		else if(shape == 3){
			player1 = images.getImage("player4");
		}
		else if(shape == 4){
			player1 = images.getImage("player5");
		}
		else if (shape == 5){
			player1 = images.getImage("player6");
		}
		else if(shape == 6){
			player1 = images.getImage("player7");
		}
		else if(shape == 7){
			player1 = images.getImage("player8");
		}
		else if(shape == 8){
			player1 = images.getImage("player9");
		}
		else if(shape == 9){
			player1 = images.getImage("player10");
		}
		else if (shape == 10){
			player1 = images.getImage("player11");
		}
		else if(shape == 11){
			player1 = images.getImage("player12");
		}
		else if(shape == 12){
			player1 = images.getImage("player13");
		}
		else if(shape == 13){
			player1 = images.getImage("player14");
		}
		else if(shape == 14){
			player1 = images.getImage("player15");
		}
	}
	public void setImage2() {
		if (shape2 == 0){
			player2 = images.getImage("player1");
		}
		else if(shape2 == 1){
			player2 = images.getImage("player2");
		}
		else if(shape2 == 2){
			player2 = images.getImage("player3");
		}
		else if(shape2 == 3){
			player2 = images.getImage("player4");
		}
		else if(shape2 == 4){
			player2 = images.getImage("player5");
		}
		else if (shape2 == 5){
			player2 = images.getImage("player6");
		}
		else if(shape2 == 6){
			player2 = images.getImage("player7");
		}
		else if(shape2 == 7){
			player2 = images.getImage("player8");
		}
		else if(shape2 == 8){
			player2 = images.getImage("player9");
		}
		else if(shape2 == 9){
			player2 = images.getImage("player10");
		}
		else if (shape2 == 10){
			player2 = images.getImage("player11");
		}
		else if(shape2 == 11){
			player2 = images.getImage("player12");
		}
		else if(shape2 == 12){
			player2 = images.getImage("player13");
		}
		else if(shape2 == 13){
			player2 = images.getImage("player14");
		}
		else if(shape2 == 14){
			player2 = images.getImage("player15");
		}
	}
	
	public int getSilence() {
		return silence;
	}
	public void setSilence(int silence) {
		this.silence = silence;
	}
	public int getvSFX() {
		return vSFX;
	}
	public void setvSFX(int vSFX) {
		this.vSFX = vSFX;
	}
	public int getvMusic() {
		return vMusic;
	}
	public void setvMusic(int vMusic) {
		this.vMusic = vMusic;
	}
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}	
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getPaused() {
		return paused;
	}
	public void setPaused(int paused) {
		this.paused = paused;
	}
	public int getPMenu() {
		return pMenu;
	}
	public void setPMenu(int pMenu) {
		this.pMenu = pMenu;
	}
	public int getPGO() {
		return pGO;
	}
	public void setPGO(int pGO) {
		this.pGO = pGO;
	}
	public int getGO() {
		return gO;
	}
	public void setGO(int gO) {
		this.gO = gO;
	}
	public int getGO2() {
		return gO2;
	}
	public void setGO2(int gO2) {
		this.gO2 = gO2;
	}
	public int getPU1() {
		return pU1;
	}
	public void setPU1(int pU1) {
		this.pU1 = pU1;
	}
	public int getPU2() {
		return pU2;
	}
	public void setPU2(int pU2) {
		this.pU2 = pU2;
	}
	public int getPCoop() {
		return pCoop;
	}
	public void setPCoop(int pCoop) {
		this.pCoop = pCoop;
	}
	public int getTp1() {
		return tp1;
	}
	public void setTp1(int tp1) {
		this.tp1 = tp1;
	}
	public int getTp2() {
		return tp2;
	}
	public void setTp2(int tp2) {
		this.tp2 = tp2;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHs1() {
		return hs1;
	}
	public void setHs1(int hs1) {
		this.hs1 = hs1;
	}
	public int getHs2() {
		return hs2;
	}
	public void setHs2(int hs2) {
		this.hs2 = hs2;
	}
	public boolean getVS() {
		return vs;
	}
	public void setVS(boolean vs) {
		this.vs = vs;
	}
	public boolean getCoop() {
		return coop;
	}
	public void setCoop(boolean coop) {
		this.coop = coop;
	}
	public int getNuke() {
		return nuke;
	}
	public void setNuke(int nuke) {
		this.nuke = nuke;
	}
	public int getNuke2() {
		return nuke2;
	}
	public void setNuke2(int nuke2) {
		this.nuke2 = nuke2;
	}
	public int getShape() {
		return shape;
	}
	public void setShape(int shape) {
		this.shape = shape;
	}
	public int getShape2() {
		return shape2;
	}
	public void setShape2(int shape2) {
		this.shape2 = shape2;
	}
	public int getWins1() {
		return wins1;
	}
	public void setWins1(int wins1) {
		this.wins1 = wins1;
	}
	public int getWins2() {
		return wins2;
	}
	public void setWins2(int wins2) {
		this.wins2 = wins2;
	}
}
