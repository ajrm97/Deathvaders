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
 * The starting state that will show us a menu of all the things we can access
 * and also allow us to travel into one another before playing
 * It has mouse and key listeners to interact in either way
 * with the menu
 */

public class GameStateStart implements GameState{
	GameContext gc;
	private int pantalla;
	GameStartScreen screen;
	Settings opciones;
	
	public GameStateStart(GameContext gc){
		this.gc = gc;
		screen = new GameStartScreen();
		pantalla = 0;
		opciones = Settings.getInstance();
	}
	public void gameOver(){
		
	}
	public void gameOver2(){
		
	}
	public void paused(){
		
	}
	
	public void running(){
		opciones.setCoop(false);
		opciones.setVS(false);
		gc.rebootState(1);
	}
	
	public void starting(){
		
	}
	
	public void coop(){
		opciones.setVS(false);
		opciones.setCoop(true);
		gc.rebootState(4);
	}
	
	public void versus(){
		opciones.setCoop(false);
		opciones.setVS(true);
		gc.rebootState(6);
	}
	
	public void render(Graphics g){
		screen.render(g, pantalla);
	}
	
	public void update(){
		
	}
	public void mPressed(int x, int y){
		//Pantalla menu principal
		if (pantalla == 0){ 
			//Listener exit
			if((x>=0 && x<=60) && (y>=10 && y<=70)){
				System.exit(0); 
			}
			//Listener 1 Jugador
			if((x>=150 && x<=350) && (y>=270 && y<=400)){
				running();
			}
			//Listener 2 Jugadores
			if((x>=600 && x<=800) && (y>=270 && y<=465)){
				pantalla = 1;
			}
			//Listener Opciones
			if((x>=280 && x<=360) && (y>=625 && y<=705)){
				pantalla = 2;
			}
			//Listener Sonido
			if((x>=590 && x<=670) && (y>=625 && y<=705)){
				opciones.setSilence (1-opciones.getSilence());
			}
			//Listener Creditos
			if((x>=850 && x<=950) && (y>=750 && y<=777)){
				pantalla = 3;
			}
			//Listener HighScore
			if((x>=20 && x<=120) && (y>=680 && y<=780)){
				pantalla = 4;
			}
			//Listener Help
			if((x>=430 && x<=510) && (y>=575 && y<=655)){
				pantalla = 5;
			}
		}
		//Pantalla de 2 jugadores
		else if (pantalla == 1){
			//Listener back
			if((x>=5 && x<=65) && (y>=10 && y<=70)){
				pantalla = 0;
			}
			//Listener Coop
			if((x>=150 && x<=350) && (y>=250 && y<=450)){
				coop();
			}
			//Listener VS
			if((x>=600 && x<=800) && (y>=270 && y<=465)){
				versus();
			}
		}
		//Pantalla de opciones
		else if (pantalla == 2){
			//Listener back
			if((x>=5 && x<=65) && (y>=10 && y<=70)){
				pantalla = 0;
			}
			//Listener SFX+
			if((x>=740 && x<=770) && (y>=213 && y<=243)){
				if (opciones.getvSFX() < 100) opciones.setvSFX(opciones.getvSFX()+1);
			}
			//Listener SFX-
			if((x>=599 && x<=619) && (y>=211 && y<=252)){
				if (opciones.getvSFX() != 0) opciones.setvSFX(opciones.getvSFX()-1);
			}
			//Listener Music+
			if((x>=740 && x<=770) && (y>=313 && y<=343)){
				if (opciones.getvMusic() < 100) opciones.setvMusic(opciones.getvMusic()+1);
			}
			//Listener Music-
			if((x>=601 && x<=618) && (y>=313 && y<=350)){
				if (opciones.getvMusic() != 0) opciones.setvMusic(opciones.getvMusic()-1);
			}
			//shape
			if((x>=580 && x<=630) && (y>=420 && y<=520)){
				if(opciones.getShape() == 0) {
					opciones.setShape(15);
				}
				opciones.setShape(opciones.getShape()-1);
				if(opciones.getShape() == opciones.getShape2()) {
					if(opciones.getShape() == 0) {
						opciones.setShape(15);
					}
					opciones.setShape(opciones.getShape()-1);
				}
			}
			if((x>=631 && x<=760) && (y>=420 && y<=520)){
				opciones.setShape((opciones.getShape()+1)%15);
				if(opciones.getShape() == opciones.getShape2()) {
					opciones.setShape((opciones.getShape()+1)%15);
				}
			}
			if((x>=580 && x<=630) && (y>=570 && y<=670)){
				if(opciones.getShape2() == 0) {
					opciones.setShape2(15);
				}
				opciones.setShape2(opciones.getShape2()-1);
				if(opciones.getShape() == opciones.getShape2()) {
					if(opciones.getShape2() == 0) {
						opciones.setShape2(15);
					}
					opciones.setShape2(opciones.getShape2()-1);
				}
			}
			if((x>=631 && x<=760) && (y>=570 && y<=670)){
				opciones.setShape2((opciones.getShape2()+1)%15);
				if(opciones.getShape() == opciones.getShape2()) {
					opciones.setShape2((opciones.getShape2()+1)%15);
				}
			}
			
		}
		//Pantalla de créditos
		else if (pantalla == 3){
			//Listener back
			if((x>=5 && x<=65) && (y>=10 && y<=70)){
				pantalla = 0;
			}
		}
		else if (pantalla == 4){
			//Listener back
			if((x>=5 && x<=65) && (y>=10 && y<=70)){
				pantalla = 0;
			}
		}
		else if (pantalla == 5){
			//Listener back
			if((x>=5 && x<=65) && (y>=10 && y<=70)){
				pantalla = 0;
			}
		}
	}
	public void kPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if (pantalla == 0){
			if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_O){
				pantalla = 2;
			}
			if(keyCode == KeyEvent.VK_S){
				pantalla = 4;
			}
			if(keyCode == KeyEvent.VK_H){
				pantalla = 5;
			}
			if(keyCode == KeyEvent.VK_C){
				pantalla = 3;
			}
			if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
				pantalla = 1;
			}
			if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
				running();
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				System.exit(0);
			}
		}
		else if (pantalla == 1){
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				pantalla = 0;
			}
			if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
				versus();
			}
			if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
				coop();
			}
		}
		if (pantalla == 2){
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				pantalla = 0;
			}
			if(keyCode == KeyEvent.VK_SPACE){
				opciones.setShape((opciones.getShape2()));
			}
			if(keyCode == KeyEvent.VK_SHIFT){
				opciones.setShape2((opciones.getShape()));
			}
			if(keyCode == KeyEvent.VK_D){
				opciones.setShape((opciones.getShape()+1)%15);
				if(opciones.getShape() == opciones.getShape2()) {
					opciones.setShape((opciones.getShape()+1)%15);
				}
			}
			if(keyCode == KeyEvent.VK_A){
				if(opciones.getShape() == 0) {
					opciones.setShape(15);
				}
				opciones.setShape(opciones.getShape()-1);
				if(opciones.getShape() == opciones.getShape2()) {
					if(opciones.getShape() == 0) {
						opciones.setShape(15);
					}
					opciones.setShape(opciones.getShape()-1);
				}
			}
			if(keyCode == KeyEvent.VK_RIGHT){
				opciones.setShape2((opciones.getShape2()+1)%15);
				if(opciones.getShape() == opciones.getShape2()) {
					opciones.setShape2((opciones.getShape2()+1)%15);
				}
			}
			if(keyCode == KeyEvent.VK_LEFT){
				if(opciones.getShape2() == 0) {
					opciones.setShape2(15);
				}
				opciones.setShape2(opciones.getShape2()-1);
				if(opciones.getShape() == opciones.getShape2()) {
					if(opciones.getShape2() == 0) {
						opciones.setShape2(15);
					}
					opciones.setShape2(opciones.getShape2()-1);
				}
			}
		}
		if (pantalla == 3){
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				pantalla = 0;
			}
		}
		if (pantalla == 4){
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				pantalla = 0;
			}
		}
		if (pantalla == 5){
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			if(keyCode == KeyEvent.VK_BACK_SPACE){
				pantalla = 0;
			}
		}
	}
	public void kReleased (KeyEvent e){
		
	}
}
