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

/*
 * Score Thread that will help all the game come together,
 * the speed of the bullets will depend on this, and the highscores
 * also will be obtained by this
 */

public class Score implements Runnable{
	
	Settings opciones;
	Font fuente;
	boolean paused;
	
	public Score() {
		opciones = Settings.getInstance();
		paused = false;
		opciones.setScore(0);
		fuente = new Font("TimesRoman", Font.BOLD, 60);
	}
		
	public void run() {
		try {
			while(true) {
				if(opciones.getPaused() == 0) {
					Thread.sleep(1250);
					//Every second-ish, add 10 to the Score
					if(opciones.getPaused() == 0) {
						opciones.setScore(opciones.getScore() + 10);
					}
				}
				else {
					waiting();
				}
			}
		} catch (InterruptedException e) {
			//System.out.println("Score: Me han matado tío!");
		}
	}
	
	//Print the Score if not Versus Mode
	public void render(Graphics g) {
		if(!opciones.getVS()) {
			g.setFont(fuente);
			g.setColor(Color.black);
			g.drawString("Score:", 675, 50);
			g.drawString(Integer.toString(opciones.getScore()), 850, 50);
		}
	}
	
	//Resume the score when game not paused
	public synchronized void resume() {
		if(!paused) {
			paused = !paused; //Empty changes to true
			notifyAll(); //Notifies the waiting Thread
		}
	}
	
	//Pause the score when game paused
	public synchronized void waiting() {
		//Guarded Block with the variable paused
		while(!paused) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Score: Me han matado tío!");
			}
		}
		paused = !paused;
	}
	
}
