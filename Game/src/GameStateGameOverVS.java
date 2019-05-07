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
 * This is the State that will be display when the Versus mode
 * get to Game Over
 */

public class GameStateGameOverVS implements GameState{
	GameContext gc;
	Font fuente;
	ImageLoader images;
	Settings opciones;
	public GameStateGameOverVS(GameContext gc){
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
		gc.rebootState(6);
	}
	
	public void starting(){
		opciones.setWins1(0);
		opciones.setWins2(0);
		opciones.setScore(0);
		opciones.setVS(false);
		gc.changeState(0);
	}
	
	public void render(Graphics g){
		fuente = new Font("TimesRoman", Font.BOLD, 100);
		g.setFont(fuente);
		g.setColor(Color.white);
		g.drawString("VICTORY", 205, 100);
		if(opciones.getWinner() == 1) {
			g.drawString("Player 1", 275, 250);
			g.drawImage(opciones.player1,300,325,300,300,null);
		}
		else {
			g.drawString("Player 2", 275, 250);
			g.drawImage(opciones.player2,300,325,300,300,null);
		}
		fuente = new Font("TimesRoman", Font.BOLD, 50);
		g.setFont(fuente);
		g.setColor(Color.white);
		g.drawString("Player 1", 75, 650);
		g.drawString(Integer.toString(opciones.getWins1()), 150, 715);
		g.drawString("Player 2", 675, 650);
		g.drawString(Integer.toString(opciones.getWins2()), 750, 715);
		g.drawImage(images.getImage("menu"),150,425,100,100,null);
		g.drawImage(images.getImage("restart"),650,425,100,100,null);
		
	}
	
	public void update(){
		
	}
	public void mPressed(int x, int y){
		if ((x >= 150 && x <= 250) && (y >= 425 && y<= 525)){
			starting();
		}
		if ((x >= 650 && x <= 750) && (y >= 425 && y<= 525)){
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