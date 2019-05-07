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
 * Same as the Objects.java class but with little modifications to make it
 * easier to implement Versus Mode 
 */

public class ObjectsVS {

	int size;
	PowerVS pU;
	Thread powerUp;
	BulletVS [] bullets;
	Thread [] threads;
	
	public ObjectsVS(Player p, Player p2) {
		size = 8;
		bullets = new BulletVS[size];
		threads = new Thread[size];
		pU = new PowerVS(p, p2);
		powerUp = new Thread(pU);
		for(int i = 0; i < size; i++) {
			bullets[i] = new BulletVS(p, p2, i);
			threads[i] = new Thread(bullets[i]);
		}
	}
	
	public void start() {
		powerUp.start();
		for(int i = 0; i < size; i++) {
			threads[i].start();
		}
	}
	
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
	
	public void destroy(int cX, int cY) {
		for(int i = 0; i < size; i++) {
			bullets[i].destroy(cX, cY);
		}
	}
	
	public void nuke() {
		for(int i = 0; i < size; i++) {
			bullets[i].nuke();
		}
	}
	
	public void resume() {
		pU.resume();
		for(int i = 0; i < size; i++) {
			bullets[i].resume();
		}
	}
	
	public void waiting() {
		pU.waiting();
		for(int i = 0; i < size; i++) {
			bullets[i].waiting();
		}
	}
	
	public void bulletsResume() {
		for(int i = 0; i < size; i++) {
			bullets[i].resume();
		}
	}
	
	public void bulletsWaiting() {
		for(int i = 0; i < size; i++) {
			bullets[i].waiting();
		}
	}
	
	public void render(Graphics g) {
		pU.render(g);
		for(int i = 0; i < size; i++) {
			bullets[i].render(g);
		}
	}
	
}
