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
 * The Game Context will be the one that keeps track of which State we are at the moment
 */

public class GameContext {
	
	
	private GameState current;
	private GameFactory states;
	private GameState[] estados;
	
	//Constructor
	public GameContext(){
		states = GameFactory.getInstance(); //Design Pattern
		estados = new GameState[10];
		for (int i = 0; i < 10; i++){
			estados[i] = states.makeState(i, this);
		}
		current = estados[0];
	}
	
	//Change or Resume a State already created
	public void changeState(int i){
		current = estados[i];
	}
	
	//Create again the state and delete the previous one
	public void rebootState(int i){
		estados[i] = states.makeState(i, this);
		current = estados[i];
	}
	
	//Run the update method of current state
	public void update(){
		current.update();
	}
	
	//Run the render method of current state
	public void render(Graphics g){
		current.render(g);
	}
	
	//Send Mouse pressed events to current state
	public void mPressed(int x, int y){
		current.mPressed(x,y);
	}
	
	//Send Keyboard Pressed event to current state
	public void kPressed (KeyEvent e){
		current.kPressed(e);
	}
	
	//Not in use
	public void kReleased (KeyEvent e){
		current.kReleased(e);
	}
}
