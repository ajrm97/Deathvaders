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

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/*
 * This is the class for each bullet that will be on the screen
 * Since it implements Runnable we can have several bullets each with
 * their own conditions to check
*/

public class Bullet implements Runnable {
	private int posX;
	private int posY;
	private int size;
	private int direccion;
	private int casillaX;
	private int casillaY;
	private boolean visible;
	private BufferedImage imagen;
	private int r;
	boolean paused;
	int speed;
	int sleep;
	int index;
	int prev;
	Player player1;
	Player player2;
	Rectangle hitbox;
	int timer;
	Font fuente;
	boolean coop;
	
	Settings opciones;
	ImageLoader images;
	
	//Constructor for single player
	public Bullet(Player p, int i){
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		
		r = ThreadLocalRandom.current().nextInt(1, 5);
		direccion = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaX = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaY = r;
		size = 90;
		direccion();
		visible = true;
		paused = false;
		speed = 1;
		sleep = 30;
		index = i;
		prev = 0;
		player1 = p;
		setHitbox();
		timer = 0;
		coop = false;
	}
	
	//Constructor for Cooperative mode, receives 2 players
	public Bullet(Player p, Player p2, int i){
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		
		r = ThreadLocalRandom.current().nextInt(1, 5);
		direccion = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaX = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaY = r;
		size = 90;
		direccion();
		visible = true;
		paused = false;
		speed = 1;
		sleep = 30;
		index = i;
		prev = 0;
		player1 = p;
		player2 = p2;
		setHitbox();
		timer = 0;
		coop = true;
	}
	
	//Function used to create each bullet hitbox when needed
	public void setHitbox(){
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
		    }
			if (direccion == 1){
				hitbox = new Rectangle(posX + size/4, posY + size/4 + 20, size/2, size/2 - 15);
			}
			else if (direccion == 2) {
				hitbox = new Rectangle(posX + size/4 + 20, posY + size/4, size/2 - 15, size/2);
			}
			else if (direccion == 3){
				hitbox = new Rectangle(posX + size/4, posY + size/4, size/2, size/4 + 5);
			}
			else if (direccion == 4){
				hitbox = new Rectangle(posX + size/4, posY + size/4, size/4 + 5, size/2);
			}
		} catch (InterruptedException e) {}
	}
	
	//Function used to set a direction on the screen when necessary
	public void direccion(){
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
		    }
			//Up
			if (direccion == 1){
				posX = casilla(0);
				posY = -90;
				imagen = images.getImage("ball1");
			}
			//Left
			else if (direccion == 2) {
				posX = -90;
				posY = casilla(1);
				imagen = images.getImage("ball2");
			}
			//Down
			else if (direccion == 3){
				posX = casilla(0);
				posY = opciones.getHeight() + 90;
				imagen = images.getImage("ball3");
			}
			//Right
			else if (direccion == 4){
				posX = opciones.getWidth() + 90;
				posY = casilla(1);
				imagen = images.getImage("ball4");
			}
		} catch (InterruptedException e) {}
	}
	
	//This function gives us the spot from where the bullet will spawn
	public int casilla(int c){
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
		    }
			if(c == 0) {
				casillaY = -1;
				if (casillaX == 0){
					return 215;
				}
				else if (casillaX == 1){
					return 335;
				}
				else if (casillaX == 2){
					return 455;
				}
				else if (casillaX == 3){
					return 575;
				}
				else if (casillaX == 4){
					return 695;
				}
			}
			else {
				casillaX = -1;
				if (casillaY == 0){
					return 110;
				}
				else if (casillaY == 1){
					return 230;
				}
				else if (casillaY == 2){
					return 350;
				}
				else if (casillaY == 3){
					return 470;
				}
				else if (casillaY == 4){
					return 590;
				}
			}
			return -90;
		} catch (InterruptedException e) {return -90;}
	}
	
	//Function that calculates the spot continiously
	public void calcCasilla() {
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
		    }
			if (direccion == 1 || direccion == 3){
				if(hitbox.y >= 110 && hitbox.y <= 180) {
					casillaY = 0;
				}
				else if(hitbox.y >= 230 && hitbox.y <= 300) {
					casillaY = 1;
				}
				else if(hitbox.y >= 350 && hitbox.y <= 420) {
					casillaY = 2;
				}
				else if(hitbox.y >= 470 && hitbox.y <= 540) {
					casillaY = 3;
				}
				else if(hitbox.y >= 590 && hitbox.y <= 660) {
					casillaY = 4;
				}
				else {
					casillaY = -1;
				}
			}
			else if (direccion == 2 || direccion == 4) {
				if(hitbox.x >= 215 && hitbox.x <= 285) {
					casillaX = 0;
				}
				else if(hitbox.x >= 335 && hitbox.x <= 405) {
					casillaX = 1;
				}
				else if(hitbox.x >= 455 && hitbox.x <= 525) {
					casillaX = 2;
				}
				else if(hitbox.x >= 575 && hitbox.x <= 545) {
					casillaX = 3;
				}
				else if(hitbox.x >= 695 && hitbox.x <= 765) {
					casillaX = 4;
				}
				else {
					casillaX = -1;
				}
			}
		} catch (InterruptedException e) {}
	}
	
	//Set a new direction, and hitbox for the despawned bullet
	public void setNew() {
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
		    }
			r = ThreadLocalRandom.current().nextInt(1, 5);
			direccion = r;
			r = ThreadLocalRandom.current().nextInt(0, 5);
			casillaX = r;
			r = ThreadLocalRandom.current().nextInt(0, 5);
			casillaY = r;
			direccion();
			visible = true;
			setHitbox();
		} catch (InterruptedException e) {}
	}
	
	//Move the bullet when updated
	public void move(){
		try {
			if (direccion == 1){
				if (posY <= opciones.getHeight() + 90){
					posY = posY + speed;
					hitbox.setLocation(posX + size/4, posY + size/4 + 20);
				}
				else {
					Thread.sleep(index*10);
					setNew();
				}
			}
			else if (direccion == 2){
				if (posX <= opciones.getWidth() + 90){
					posX = posX + speed;
					hitbox.setLocation(posX + size/4 + 20, posY + size/4);
				}
				else {
					Thread.sleep(index*10);
					setNew();
				}
			}
			else if (direccion == 3){
				if (posY >= -90){
					posY = posY - speed;
					hitbox.setLocation(posX + size/4, posY + size/4);
				}
				else {
					Thread.sleep(index*10);
					setNew();
				}
			}
			else if (direccion == 4){
				if (posX >= -90){
					posX = posX - speed;
					hitbox.setLocation(posX + size/4, posY + size/4);
				}
				else {
					Thread.sleep(index*10);
					setNew();
				}
			}
		} catch (InterruptedException e) {}
	}
	
	//Checks collision with Player 1
	public boolean collision() {
		try {
			if (Thread.interrupted()) {
					throw new InterruptedException();
		    }
			if(visible && player1.getVida() > 0) {
				if(hitbox.intersects(player1.getHitbox())){
					this.visible = false;
					return true;
				}
			}
			return false;
		} catch (InterruptedException e) {return false;}
	}
	
	//Checks collision with Player 2
	public boolean collision2() {
		try {
			if (Thread.interrupted()) {
					throw new InterruptedException();
		    }
			if(visible && player2.getVida() > 0) {
				if(hitbox.intersects(player2.getHitbox())){
					this.visible = false;
					return true;
				}
			}
			return false;
		} catch (InterruptedException e) {return false;}
	}
	
	//Destoy all the bullets that are in the same spot as the player (Power-Up)
	public void destroy(int cX, int cY) {
		if(casillaX == cX || casillaY == cY) {
			this.visible = false;
		}
	}
	
	//Destroy every bullet on the screen (Power-Up)
	public void nuke() {
		this.visible = false;
	}
	
	//Runnable method
	public void run() {
		try {
			//Spawn each bullet at differnt time depending on their index
			for(int i = 0; i < index; i++) {
				if(opciones.getPaused() == 0) {
					Thread.sleep(3000);
				}
				else {
					//Send thread to a guarded block if the game is paused
					waiting();
				}
			}
			while(true) {
				//Bullet behavior when Power-Up 2 is active
				while(opciones.getPU1() == 2 || opciones.getPU2() == 2 || opciones.getPCoop() == 2) {
					if(opciones.getPaused() == 0) {
						timer++;
						speed = 0;
						move();
						calcCasilla();
						if(collision()) {
							if(player1.getVida() == 1) {
								if(!coop) {
									opciones.setGO(1);
								}
								else {
									if(player2.getVida() == 0) {
										opciones.setGO(1);
									}
									else {
										player1.setVida(0);
									}
								}
								Thread.sleep(1000);
							}
							else if(player1.getVida() == 2){
								opciones.setPU1(-1);
								player1.setSh(false);
								player1.setVida(1);
							}
						}
						if(coop) {
							if(collision2()) {
								if(player2.getVida() == 1) {
									if(player1.getVida() == 0) {
										opciones.setGO(1);
									}
									else {
										player2.setVida(0);
									}
									Thread.sleep(1000);
								}
								else if(player2.getVida() == 2){
									opciones.setPU2(-1);
									player2.setSh(false);
									player2.setVida(1);
								}
							}
						}
						Thread.sleep(sleep);
						if(timer > 100) {
							opciones.setPU1(-1);
							opciones.setPU2(-1);
							opciones.setPCoop(-1);
							timer = 0;
						}
					}
					else {
						waiting();
					}
				}
				timer = 0;
				
				//Bullet normal behavior
				if(opciones.getPaused() == 0) {
					
					//Set the speed of the bullets depending on the score (time)
					speed = (opciones.getScore() / 100) + 1;
					
					if(speed > 10) {
						speed = 10;
					}
					
					//If Power-Up is active halve bullet speed
					if(opciones.getSpeed() != 0) {
						if(speed > 3) {
							speed = speed - opciones.getSpeed();
						}
						else if(speed == 3) {
							speed = speed - 2;
						}
						else if(speed == 2) {
							speed = speed - 1;
						}
						else if(speed == 1) {
							speed = speed - 0;
						}
					}
					
					//Move each bullet
					move();
					
					//Calculate new spot for the bullet
					calcCasilla();
					
					//Check collisions with Player 1
					if(collision()) {
						//If player does not have a shield
						if(player1.getVida() == 1) {
							if(!coop) {
								//Game Over if Sigle Player
								opciones.setGO(1);
							}
							else {
								//If both players are death GO
								if(player2.getVida() == 0) {
									opciones.setGO(1);
								}
								else {
									//Player 1 is death
									player1.setVida(0);
								}
							}
							Thread.sleep(1000);
						}
						//If player has shield then destroys it and destroys itself 
						else if(player1.getVida() == 2){
							opciones.setPU1(-1);
							player1.setSh(false);
							player1.setVida(1);
						}
					}
					if(coop) {
						//Check collision with Player 2, same as Player 1
						if(collision2()) {
							if(player2.getVida() == 1) {
								if(player1.getVida() == 0) {
									opciones.setGO(1);
								}
								else {
									player2.setVida(0);
								}
								Thread.sleep(1000);
							}
							else if(player2.getVida() == 2){
								opciones.setPU2(-1);
								player2.setSh(false);
								player2.setVida(1);
							}
						}
					}
					Thread.sleep(sleep);
				}
				else {
					//If game is paused, then guarded block
					waiting();
				}
			}
		} catch (InterruptedException e) {
			//System.out.println("Bullet: Me han matado tío!");
		}
	}
	
	//Stop the guarded block, game is now resume
	public synchronized void resume() {
		if(!paused) {
			paused = !paused; //Empty changes to true
			notifyAll(); //Notifies the waiting Thread
		}
	}
	
	//Guarded Block when the game is paused
	public synchronized void waiting() {
		//Guarded Block with the variable paused
		try {
			while(!paused) {
				wait();
			}
			paused = !paused;
		} catch (InterruptedException e) {}
	}
	
	//Render each bullet
	public void render(Graphics g){
//		fuente = new Font("TimesRoman", Font.BOLD, 60);
//		g.setFont(fuente);
//		g.setColor(Color.black);
//		g.drawString(Integer.toString(opciones.getPU1()), 150, 50);
//		g.drawString("Speed: " + Integer.toString(speed), 150, 150);
		//g.drawString("Settings: " + Integer.toString(opciones.getSpeed()), 150, 250);
		if (visible) {
			g.drawImage(imagen, posX, posY, size, size, null);
			//g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}

}