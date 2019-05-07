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

/*
 * Object class that will keep track of all the running threads
 * Also, this will be the one class to interrupt all Threads
 * and Join them so they will not run forever
 */

public class Objects {

	int size;
	Power pU;
	Thread powerUp;
	Bullet [] bullets;
	Thread [] threads;
	
	//For Single Player, creation of threads
	public Objects(Player p) {
		size = 14;
		bullets = new Bullet[size];
		threads = new Thread[size];
		pU = new Power(p);
		powerUp = new Thread(pU);
		for(int i = 0; i < size; i++) {
			bullets[i] = new Bullet(p, i);
			threads[i] = new Thread(bullets[i]);
		}
	}
	
	//For Cooperative Mode, creations of threads
	public Objects(Player p, Player p2) {
		size = 18;
		bullets = new Bullet[size];
		threads = new Thread[size];
		pU = new Power(p, p2);
		powerUp = new Thread(pU);
		for(int i = 0; i < size; i++) {
			bullets[i] = new Bullet(p, p2, i);
			threads[i] = new Thread(bullets[i]);
		}
	}
	
	//Initializes all threads
	public void start() {
		powerUp.start();
		for(int i = 0; i < size; i++) {
			threads[i].start();
		}
	}
	
	//Interrupts and Joins all Threads
	public void stop() {
		powerUp.interrupt();
		try {
			powerUp.join();
		} catch (InterruptedException e) {}
		for(int i = 0; i < size; i++) {
			threads[i].interrupt();
			try {
				threads[i].join(500);
			} catch (InterruptedException e) {}
		}
		for(int i = 0; i < size; i++) {
			if(threads[i].isAlive()) {
				threads[i].interrupt();
				try {
					threads[i].join(500);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	//Passes the necessary positions to destroy the bullets
	public void destroy(int cX, int cY) {
		for(int i = 0; i < size; i++) {
			bullets[i].destroy(cX, cY);
		}
	}
	
	//Send to the Bullets that they have to destroy themselves
	public void nuke() {
		for(int i = 0; i < size; i++) {
			bullets[i].nuke();
		}
	}
	
	//Resume all threads
	public void resume() {
		pU.resume();
		for(int i = 0; i < size; i++) {
			bullets[i].resume();
		}
	}
	
	//Pause all threads
	public void waiting() {
		pU.waiting();
		for(int i = 0; i < size; i++) {
			bullets[i].waiting();
		}
	}
	
	//Resume only the Bullets
	public void bulletsResume() {
		for(int i = 0; i < size; i++) {
			bullets[i].resume();
		}
	}
	
	//Pause only the Bullets
	public void bulletsWaiting() {
		for(int i = 0; i < size; i++) {
			bullets[i].waiting();
		}
	}
	
	//Send the graphics variable for the Threads to paint on
	public void render(Graphics g) {
		pU.render(g);
		for(int i = 0; i < size; i++) {
			bullets[i].render(g);
		}
	}
	
}
