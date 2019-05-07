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
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * Player class, the one that will show as the Player 1 or Player 2
 * also depending on the game mode 
 */

public class Player{
	
	Settings opciones;
	ImageLoader images;
	
	private int vida;
	private int posX;
	private int posY;
	private int size;
	private int casillaX;
	private int casillaY;
	private int powerUp;
	private int ban;
	private BufferedImage imagen;
	Rectangle hitbox;
	boolean sh;
	boolean tp;
	boolean ice;
	boolean cf;
	BufferedImage shield;
	int safe;
	
	//For Single Player
	public Player(){
		images = ImageLoader.getInstance();
		opciones = Settings.getInstance();
		vida = 1; 
		posX = 455;
		posY = 350;
		size = 90;
		casilla();
		powerUp = 0;
		ban = 0;
		tp = false;
		sh = false;
		ice = false;
		cf = false;
		safe = 20;
		hitbox = new Rectangle(posX + safe, posY + safe, (size - safe) - safe, (size - safe) - safe);
		opciones.setImage();
		imagen = opciones.player1;
		shield = images.getImage("shield");
		opciones = Settings.getInstance();
	}
	
	//For Multiplayer
	public Player(int i){
		images = ImageLoader.getInstance();
		opciones = Settings.getInstance();
		vida = 1; 
		posX = 455;
		posY = 350;
		if(i == 1) {
			posX = 335;
			posY = 350;
		}
		else {
			posX = 575;
			posY = 350;
		}
		size = 90;
		casilla();
		powerUp = 0;
		ban = 0;
		tp = false;
		sh = false;
		ice = false;
		cf = false;
		safe = 20;
		hitbox = new Rectangle(posX + safe, posY + safe, (size - safe) - safe, (size - safe) - safe);
		if(i == 1) {
			opciones.setImage();
			imagen = opciones.player1;
		}
		else {
			opciones.setImage2();
			imagen = opciones.player2;
		}
		shield = images.getImage("shield");
		opciones = Settings.getInstance();
	}
	
	public int getVida() {
		return vida;
	}
	public synchronized void setVida(int vida) {
		this.vida = vida;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCasillaX() {
		return casillaX;
	}
	public void setCasillaX(int casillaX) {
		this.casillaX = casillaX;
	}
	public int getCasillaY() {
		return casillaY;
	}
	public void setCasillaY(int casillaY) {
		this.casillaY = casillaY;
	}
	public int getPowerUp() {
		return powerUp;
	}
	public void setPowerUp(int powerUp) {
		this.powerUp = powerUp;
	}
	public int getBan() {
		return ban;
	}
	public void setBan(int ban) {
		this.ban = ban;
	}
	public boolean getTp() {
		return tp;
	}
	public void setTp(boolean tp) {
		this.tp = tp;
	}
	public boolean getIce() {
		return ice;
	}
	public void setIce(boolean ice) {
		this.ice = ice;
	}
	public boolean getSh() {
		return sh;
	}
	public synchronized void setSh(boolean sh) {
		this.sh = sh;
	}
	public boolean getCf() {
		return cf;
	}
	public synchronized void setCf(boolean cf) {
		this.cf = cf;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	//Move player to an specific position
	public void move(int x, int y) {
		if(vida > 0) {
			posX = x;
			posY = y;
			hitbox.setLocation(posX + safe, posY + safe);
		}
	}
	
	
	//All the movements that the player can do
	
	public void moveUp(){
		if(vida > 0) {
			if (posY >= 200) {
				posY = posY - 120;
				hitbox.setLocation(posX + safe, posY + safe);
			}
		}
	}
	public void moveDown(){
		if(vida > 0) {
			if (posY <= 500) {
				posY = posY + 120;
				hitbox.setLocation(posX + safe, posY + safe);
			}
		}
	}
	public void moveLeft(){
		if(vida > 0) {
			if (posX >= 300) {
				posX = posX - 120;
				hitbox.setLocation(posX + safe, posY + safe);
			}
		}
	}
	public void moveRight(){
		if(vida > 0) {
			if (posX <= 600) {
				posX = posX + 120;
				hitbox.setLocation(posX + safe, posY + safe);
			}
		}			
	}
	
	//Update the spot in which the player is currently
	public void casilla(){
		if(vida > 0) {
			if (posX == 215){
				casillaX = 0;
			}
			else if (posX == 335){
				casillaX = 1;
			}
			else if (posX == 455){
				casillaX = 2;
			}
			else if (posX == 575){
				casillaX = 3;
			}
			else if (posX == 695){
				casillaX = 4;
			}
			if (posY == 110){
				casillaY = 0;
			}
			else if (posY == 230){
				casillaY = 1;
			}
			else if (posY == 350){
				casillaY = 2;
			}
			else if (posY == 470){
				casillaY = 3;
			}
			else if (posY == 590){
				casillaY = 4;
			}
		}
	}
	
	//Render the player
	public void render(Graphics g) {
//		fuente = new Font("TimesRoman", Font.BOLD, 60);
//		g.setFont(fuente);
//		g.setColor(Color.black);
//		g.drawString("Pos X: " + Integer.toString(posX + 10), 150, 50);
//		g.drawString("Pos Y: " + Integer.toString(posY + 10), 150, 150);
		if (vida > 0) {
			g.drawImage(imagen, posX, posY, size, size, null);
			//g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
			//g.drawRect(posX + 10, posY + 10, size - 10 - 10, size - 10 - 10);
			if(sh) {
				g.drawImage(shield, posX + 30, posY + 30, size - 30, size - 30, null);
			}
			if(tp) {
				g.drawImage(images.getImage("teleport"), posX, posY + 50, 40, 40, null);
			}
			if(ice) {
				g.drawImage(images.getImage("ice"), posX, posY, 90, 90, null);
			}
			if(cf) {
				g.drawImage(images.getImage("confused"), posX + 20, posY-20, 70, 50, null);
			}
		}
	}

}
