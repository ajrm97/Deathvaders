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
 * A simple interface that will ensure that all the states have the same methods necessary to work
 */

interface GameState {
	
	public void gameOver();
	public void paused();
	public void running();
	public void starting();
	public void coop();
	public void versus();
	public void gameOver2();
	
	public void render(Graphics g);
	public void update();
	
	public void mPressed(int x, int y);
	public void kPressed(KeyEvent e);
	public void kReleased(KeyEvent e);
	
}
