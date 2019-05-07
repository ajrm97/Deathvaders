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

/*
 * This class will be the one in charge of creating all the states at the beginning
 */

public class GameFactory {
	private static GameFactory states = null;
	static boolean firstThread = true;
	
	private GameFactory(){
		
	}
	
	//With this, we ensure that only one instance of this class is created
	public static GameFactory getInstance(){
		
		if (states == null){
			if (firstThread){
				firstThread = false;
				Thread.currentThread();
				try { 
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
			}
			synchronized(GameFactory.class){
				if (states == null){
					states = new GameFactory();  
				}
			}
		}
		return states;
	}
	
	//Create every state
	public GameState makeState (int i, GameContext gc){
		GameState newState = null;
		if (i == 1){
			newState = new GameStateRunning(gc);
		}else if (i == 2){
			newState = new GameStatePaused(gc);
		}else if (i == 0){
			newState = new GameStateStart(gc);
		}else if(i == 3){
			newState = new GameStateGameOver(gc);
		}
		else if (i == 4){
			newState = new GameStateCoop(gc);
		}
		else if (i == 5){
			newState = new GameStateGameOverVS(gc);
		}
		else if (i == 6){
			newState = new GameStateVersus(gc);
		}
		return newState;
	}
}
