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

/*
 * This is the State that will be display when the any
 * time that the game is paused
 * When paused all Threads are stopped until the game is resumed
 */

public class GameStatePaused implements GameState{
	GameContext gc;
	Settings opciones;
	Font fuente;
	ImageLoader images;
	public GameStatePaused(GameContext gc){
		this.gc = gc;
		images = ImageLoader.getInstance();
		opciones = Settings.getInstance();
	}
	public void gameOver(){
		opciones.setPGO(1);
		if(opciones.getCoop()) {
			gc.changeState(4);
		}
		else if(opciones.getVS()){
			gc.changeState(6);
		}
		else{
			gc.changeState(1);
		}
	}
	public void gameOver2(){
		
	}
	public void paused(){
		
	}
	
	public void running(){
		if(opciones.getCoop()) {
			gc.changeState(4);
		}
		else if(opciones.getVS()){
			gc.changeState(6);
		}
		else{
			gc.changeState(1);
		}
	}
	
	public void starting(){
		opciones.setPMenu(1);
		if(opciones.getCoop()) {
			gc.changeState(4);
		}
		else if(opciones.getVS()){
			opciones.setWins1(0);
			opciones.setWins2(0);
			gc.changeState(6);
		}
		else{
			gc.changeState(1);
		}
	}
	public void coop() {
		
	}
	
	public void versus() {
		
	}
	public void render(Graphics g){
		fuente = new Font("TimesRoman", Font.BOLD, 100);
		g.setFont(fuente);
		g.setColor(Color.black);
		g.drawString("Paused", 305, 100);
		g.drawImage(images.getImage("paused"),350,300,200,200,null);
		g.drawImage(images.getImage("menu"),150,350,100,100,null);
		g.drawImage(images.getImage("restart"),650,350,100,100,null);
	}
	
	public void update(){
	
	}
	public void mPressed(int x, int y){
		if ((x >= 350 && x <= 550)&&(y >= 300 && y <= 500)){
			running();
		}
		else if ((x >= 150 && x <= 250)&&(y >= 350 && y <= 450)){
			starting();
		}
		else if ((x >= 650 && x <= 750)&&(y >= 350 && y <= 450)){
			gameOver();
		}
	}
	public void kPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE){
			running();
		}
		if(keyCode == KeyEvent.VK_R){
			gameOver();
		}
		if(keyCode == KeyEvent.VK_M){
			starting();
		}
	}
	public void kReleased (KeyEvent e){
		
	}
}
