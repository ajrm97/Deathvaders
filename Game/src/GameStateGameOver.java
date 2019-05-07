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
 * This is the State that will be display when the Single Player or Cooperative
 * get to Game Over
 */

public class GameStateGameOver implements GameState{
	GameContext gc;
	Font fuente;
	ImageLoader images;
	Settings opciones;
	public GameStateGameOver(GameContext gc){
		this.gc = gc;
		images = ImageLoader.getInstance();
		opciones = Settings.getInstance();
	}
	public void gameOver(){
		
	}
	public void gameOver2(){
		
	}
	public void paused(){
		
	}
	public void coop() {
		
	}
	public void versus() {
		
	}
	public void running(){
		opciones.setScore(0);
		if(opciones.getCoop()) {
			gc.rebootState(4);
		}
		else{
			gc.rebootState(1);
		}
	}
	
	public void starting(){
		opciones.setScore(0);
		opciones.setCoop(false);
		gc.changeState(0);
	}
	
	public void render(Graphics g){
		fuente = new Font("TimesRoman", Font.BOLD, 100);
		g.setFont(fuente);
		g.setColor(Color.white);
		g.drawString("Game Over", 205, 100);
		g.drawString("Score: "+ Integer.toString(opciones.getScore()), 250, 350);
		g.drawImage(images.getImage("menu"),300,500,100,100,null);
		g.drawImage(images.getImage("restart"),500,500,100,100,null);
		
	}
	
	public void update(){
		
	}
	public void mPressed(int x, int y){
		if ((x >= 300 && x <= 400) && (y >= 500 && y<= 600)){
			starting();
		}
		if ((x >= 500 && x <= 600) && (y >= 500 && y<= 600)){
			running();
		}
	}
	public void kPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_R){
			running();
		}
		if(keyCode == KeyEvent.VK_M){
			starting();
		}
	}
	public void kReleased (KeyEvent e){
		
	}
}