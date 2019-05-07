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
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Same as the Power.java class but this one is for a more organized
 * and easier way to implement the Power-Up in Versus Mode
 */

public class PowerVS implements Runnable{
	
	private boolean disponible;
	private int tipo;
	private int posX;
	private int posY;
	private int size;
	private int casillaX;
	private int casillaY;
	private BufferedImage imagen;
	private int r;
	boolean paused;
	Player player1;
	Player player2;
	int timer;
	
	Settings opciones;
	ImageLoader images;
	
	public PowerVS(Player p, Player p2){
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		
		disponible = false;
		paused = false;
		r = ThreadLocalRandom.current().nextInt(0, 21);
		tipo = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaX = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaY = r;
		casilla(casillaX, casillaY);
		size = 70;
		imagen();
		player1 = p;
		player2 = p2;
		timer = 0;
	}
	
	public boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public BufferedImage getImagen() {
		return imagen;
	}
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	public boolean collision(int x, int y){
		if (disponible && player1.getVida() > 0){
			if (casillaX == x && casillaY == y){
				disponible = false;
				return true;
			}
		}
		else if (disponible && player2.getVida() > 0){
			if (casillaX == x && casillaY == y){
				disponible = false;
				return true;
			}
		}
		return false;
	}
	public void casilla(int x, int y){
		casillaX = x;
		casillaY = y;
		if (x == 0){
			posX = 235;
		}
		else if (x == 1){
			posX = 355;
		}
		else if (x == 2){
			posX = 475;
		}
		else if (x == 3){
			posX = 595;
		}
		else if (x == 4){
			posX = 715;
		}
		if (y == 0){
			posY = 130;
		}
		else if (y == 1){
			posY = 250;
		}
		else if (y == 2){
			posY = 370;
		}
		else if (y == 3){
			posY = 490;
		}
		else if (y == 4){
			posY = 610;
		}
	}
	public void imagen(){
		if (tipo == 0 || tipo == 6 || tipo == 11 || tipo == 16 || tipo == 19){
			tipo = 0;
			imagen = images.getImage("shield");
		}
		else if (tipo == 1 || tipo == 7 || tipo == 12 || tipo == 17){
			tipo = 1;
			imagen = images.getImage("teleport");
		}
		else if (tipo == 2 || tipo == 8 || tipo == 13 || tipo == 18){
			tipo = 2;
			imagen = images.getImage("clock");
		}
		else if (tipo == 3 || tipo == 9 || tipo == 14){
			tipo = 3;
			imagen = images.getImage("cross");
		}
		else if (tipo == 4 || tipo == 10 || tipo == 15 || tipo == 20){
			tipo = 4;
			imagen = images.getImage("rewind");
		}
		else if (tipo == 5 ){
			tipo = 5;
			imagen = images.getImage("nuke");
		}
	}
	
	public void setNew() {
		r = ThreadLocalRandom.current().nextInt(0, 21);
		tipo = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaX = r;
		r = ThreadLocalRandom.current().nextInt(0, 5);
		casillaY = r;
		casilla(casillaX, casillaY);
		imagen();
	}
	
	public void run() {
		try {
			for(int i = 0; i <= 7; i++) {
				if(opciones.getPaused() == 0) {
					Thread.sleep(1000);
				}
				else {
					waiting();
				}
			}
			disponible = true;
			while(true) {
				if(opciones.getPaused() == 0) {
					if(timer > 176) {
						disponible = false;
						timer = 0;
						for(int i = 0; i <= 5; i++) {
							if(opciones.getPaused() == 0) {
								Thread.sleep(1000);
							}
							else {
								waiting();
							}
						}
						setNew();
						disponible = true;
					}
					if(collision(player1.getCasillaX(), player1.getCasillaY())) {
						opciones.setPU1(tipo);
						timer = 0;
						for(int i = 0; i <= 7; i++) {
							if(opciones.getPaused() == 0) {
								Thread.sleep(1000);
							}
							else {
								waiting();
							}
						}
						setNew();
						disponible = true;
					}
					if(collision(player2.getCasillaX(), player2.getCasillaY())) {
						opciones.setPU2(tipo);
						timer = 0;
						for(int i = 0; i <= 7; i++) {
							if(opciones.getPaused() == 0) {
								Thread.sleep(1000);
							}
							else {
								waiting();
							}
						}
						setNew();
						disponible = true;
					}
					timer++;
					Thread.sleep(20);
				}
				else {
					waiting();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("PowerUp: Me han matado tío!");
		}
	}
	
	public synchronized void resume() {
		if(!paused) {
			paused = !paused; //Empty changes to true
			notifyAll(); //Notifies the waiting Thread
		}
	}
	
	public synchronized void waiting() {
		//Guarded Block with the variable paused
		while(!paused) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("PowerUp: Me han matado tío!");
			}
		}
		paused = !paused;
	}
	
	public void render(Graphics g) {
		if (disponible)
			g.drawImage(imagen, posX - 10, posY - 10, size, size, null);
	} 

}
