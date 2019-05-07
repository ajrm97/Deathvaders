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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/*
 * This class will paint all the options available in the Starting Screen
 * with this we can have several screens without having to change states
 */

public class GameStartScreen extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pantalla;
	String SFX, Music;
	private Font fuente;
	Settings opciones;
	ImageLoader images;
	
	public GameStartScreen (){
		super();
		pantalla = 0;
		fuente=new Font("TimesRoman", Font.BOLD, 100);
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
	}
	public void paint (Graphics g){
		
		switch(pantalla){
		case 0:
			fuente = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawImage(images.getImage("exit"),0,10,60,60,null);
			g.drawImage(images.getImage("title1"),100,10,700,102,null);
			g.drawImage(images.getImage("p1"),150,250,200,200,null);
			g.drawString("1 Player", 160, 235);
			g.drawImage(images.getImage("p2"),600,265,200,200,null);
			g.drawString("2 Players", 610, 235);
			g.drawImage(images.getImage("gear"),280,625,80,80,null);
			g.drawImage(images.getImage("help"),430,575,80,80,null);
			if(opciones.getSilence() == 0)
				g.drawImage(images.getImage("sonido"),590,625,80,80,null);
			else
				g.drawImage(images.getImage("mute"),593,625,80,80,null);
			g.drawImage(images.getImage("creditos"),850,750,100,27,null);
			g.drawImage(images.getImage("hs"),20,680,100,100,null);
			break;
		case 1:
			fuente = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawImage(images.getImage("back"),5,10,60,60,null);
			g.drawImage(images.getImage("title1"),100,10,700,102,null);
			g.drawImage(images.getImage("coop"),150,250,200,200,null);
			g.drawString("Coop-Mode", 130, 515);
			g.drawImage(images.getImage("vs"),600,265,200,200,null);
			g.drawString("Versus-Mode", 580, 515);
			break;
		case 2:
			g.drawImage(images.getImage("back"),5,10,60,60,null);
			fuente = new Font("TimesRoman", Font.BOLD, 100);
			g.setFont(fuente);
			g.setColor(Color.black);
			g.drawString("Options", 275, 100);
			
			fuente = new Font("TimesRoman", Font.BOLD, 60);
			g.setFont(fuente);
			g.drawString("Volume SFX", 50, 250);
			SFX = Integer.toString(opciones.getvSFX());
			Music = Integer.toString(opciones.getvMusic());
			g.drawString("- "+SFX, 600, 250);
			g.drawString("+", 740, 250);
			g.drawString("Volume Music", 50, 350);
			g.drawString("- "+Music, 600, 350);
			g.drawString("+", 740, 350);
			g.drawString("Player 1:", 50, 500);
			g.drawString("Player 2:", 50, 650);
			g.drawString("<", 580, 500);
			g.drawString(">", 725, 500);
			g.drawString("<", 580, 650);
			g.drawString(">", 725, 650);
			
			if (opciones.getShape() == 0){
				g.drawImage(images.getImage("player1"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 1){
				g.drawImage(images.getImage("player2"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 2){
				g.drawImage(images.getImage("player3"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 3){
				g.drawImage(images.getImage("player4"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 4){
				g.drawImage(images.getImage("player5"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 5){
				g.drawImage(images.getImage("player6"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 6){
				g.drawImage(images.getImage("player7"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 7){
				g.drawImage(images.getImage("player8"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 8){
				g.drawImage(images.getImage("player9"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 9){
				g.drawImage(images.getImage("player10"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 10){
				g.drawImage(images.getImage("player11"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 11){
				g.drawImage(images.getImage("player12"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 12){
				g.drawImage(images.getImage("player13"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 13){
				g.drawImage(images.getImage("player14"),620,420,100,100,null);
			}
			else if(opciones.getShape() == 14){
				g.drawImage(images.getImage("player15"),620,420,100,100,null);
			}
			
			if (opciones.getShape2() == 0){
				g.drawImage(images.getImage("player1"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 1){
				g.drawImage(images.getImage("player2"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 2){
				g.drawImage(images.getImage("player3"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 3){
				g.drawImage(images.getImage("player4"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 4){
				g.drawImage(images.getImage("player5"),620,570,100,100,null);
			}
			else if (opciones.getShape2() == 5){
				g.drawImage(images.getImage("player6"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 6){
				g.drawImage(images.getImage("player7"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 7){
				g.drawImage(images.getImage("player8"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 8){
				g.drawImage(images.getImage("player9"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 9){
				g.drawImage(images.getImage("player10"),620,570,100,100,null);
			}
			else if (opciones.getShape2() == 10){
				g.drawImage(images.getImage("player11"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 11){
				g.drawImage(images.getImage("player12"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 12){
				g.drawImage(images.getImage("player13"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 13){
				g.drawImage(images.getImage("player14"),620,570,100,100,null);
			}
			else if(opciones.getShape2() == 14){
				g.drawImage(images.getImage("player15"),620,570,100,100,null);
			}
			break;
		case 3:
			g.drawImage(images.getImage("back"),5,10,60,60,null);
			fuente = new Font("TimesRoman", Font.BOLD, 100);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawString("CREATOR", 250, 100);
			fuente = new Font("TimesRoman", Font.BOLD, 80);
			g.setFont(fuente);
			g.drawString("A01187388", 285, 380);
			fuente = new Font("TimesRoman", Font.BOLD, 60);
			g.setFont(fuente);
			g.drawString("Armando Josue Ruiz Munoz", 90, 500);
			break;
		case 4:
			g.drawImage(images.getImage("back"),5,10,60,60,null);
			fuente = new Font("TimesRoman", Font.BOLD, 100);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawString("HIGHSCORES", 135, 100);
			fuente = new Font("TimesRoman", Font.BOLD, 80);
			g.setFont(fuente);
			g.drawString("Survival: "+ Integer.toString(opciones.getHs1()), 200, 350);
			g.drawString("Coop: "+ Integer.toString(opciones.getHs2()), 200, 550);
			break;
		case 5:
			g.drawImage(images.getImage("back"),5,10,60,60,null);
			fuente = new Font("TimesRoman", Font.BOLD, 100);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawString("INSTRUCTIONS", 135, 100);
			fuente = new Font("TimesRoman", Font.BOLD, 80);
			g.setFont(fuente);
			g.drawString("Survival", 25, 200);
			g.drawString("Coop", 400, 200);
			g.drawString("Versus", 675, 200);
			fuente = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(fuente);
			g.setColor(Color.black);
			g.drawString("Movement", 25, 275);
			g.drawString("Movement", 375, 275);
			g.drawString("Movement", 675, 275);
			fuente = new Font("TimesRoman", Font.BOLD, 30);
			g.setFont(fuente);
			g.setColor(Color.white);
			g.drawString("Arrow Keys/W A S D", 25, 325);
			g.drawString("1P: W A S D", 375, 325);
			g.drawString("2P: Arrow Keys", 375, 375);
			g.drawString("1P: W A S D", 675, 325);
			g.drawString("2P: Arrow Keys", 675, 375);
			fuente = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(fuente);
			g.setColor(Color.black);
			g.drawString("Power-Ups", 25, 435);
			g.drawString("Power-Ups", 375, 435);
			g.drawString("Power-Ups", 675, 435);
			fuente = new Font("TimesRoman", Font.BOLD, 15);
			g.setFont(fuente);
			g.setColor(Color.white);
			//Survival
			g.drawImage(images.getImage("shield"),30,450,30,30,null);
			g.drawString("Survive 2 hits (cannot stack)", 65, 475);
			g.drawImage(images.getImage("teleport"),30,485,30,30,null);
			g.drawString("Spacebar: Place > Teleport", 65, 505);
			g.drawImage(images.getImage("cross"),30,515,30,30,null);
			g.drawString("Destroys projectiles in line", 65, 540);
			g.drawImage(images.getImage("clock"),30,545,30,30,null);
			g.drawString("Stop projectiles (3 seconds)", 65, 570);
			g.drawImage(images.getImage("rewind"),30,575,30,30,null);
			g.drawString("Halves projectiles speed (5 seconds)", 65, 600);
			g.drawImage(images.getImage("nuke"),30,605,30,30,null);
			g.drawString("Destroys all projectiles in screen", 65, 630);
			//Cooperative
			g.drawImage(images.getImage("shield"),360,450,30,30,null);
			g.drawString("Survive 2 hits (cannot stack)", 395, 470);
			g.drawImage(images.getImage("teleport"),360,485,30,30,null);
			g.drawString("1P Spacebar: Place > Teleport 1P", 395, 500);
			g.drawString("2P Shift: Place > Teleport 2P", 395, 515);
			g.drawImage(images.getImage("cross"),360,515,30,30,null);
			g.drawString("Destroys projectiles in line", 395, 540);
			g.drawImage(images.getImage("clock"),360,545,30,30,null);
			g.drawString("Stop projectiles (3 seconds)", 395, 570);
			g.drawImage(images.getImage("rewind"),360,575,30,30,null);
			g.drawString("Halves projectiles speed (5 seconds)", 395, 600);
			g.drawImage(images.getImage("nuke"),360,605,30,30,null);
			g.drawString("Destroys all projectiles in screen", 395, 630);
			g.drawImage(images.getImage("life"),360,640,30,30,null);
			g.drawString("Revive your fallen teammate", 395, 660);
			g.drawImage(images.getImage("team"),360,675,30,30,null);
			g.drawString("Overlap in the same spot a bit", 395, 680);
			g.drawString("and activate a random power-up", 395, 695);
			g.drawString("5 activations - 5 second cooldown", 395, 710);
			g.drawString("-If one player grabs the Power-Up", 380, 735);
			g.drawString("-both players get the bonus", 380, 750);
			//Versus
			g.drawImage(images.getImage("shield"),680,450,30,30,null);
			g.drawString("Survive 2 hits (cannot stack)", 715, 465);
			g.drawString("Can push oppent 3 times", 715, 480);
			g.drawImage(images.getImage("teleport"),680,485,30,30,null);
			g.drawString("1P Spacebar: Place > Teleports 2P (2s CD)", 715, 500);
			g.drawString("2P Shift: Place > Teleports 1P (2s CD)", 715, 515);
			g.drawImage(images.getImage("cross"),680,515,30,30,null);
			g.drawString("Destroys projectiles in line, hits opponent", 715, 540);
			g.drawImage(images.getImage("clock"),680,545,30,30,null);
			g.drawString("Freezes opponent (8 seconds)", 715, 570);
			g.drawImage(images.getImage("rewind"),680,575,30,30,null);
			g.drawString("Inverts opponent controls (5 seconds)", 715, 600);
			g.drawImage(images.getImage("nuke"),680,605,30,30,null);
			g.drawString("No longer destroys all projectiles but", 715, 625);
			g.drawString("hits opponent and if they survive", 715, 640);
			g.drawString("freeze (15s) or confuse (10s)", 715, 655);
			g.drawString("-If you are frozen, you can tap your", 700, 690);
			g.drawString("-movement keys repeatedly to break", 700, 705);
			g.drawString("-the ice sooner", 700, 720);
			break;
		}
		
	}
	public void render(Graphics g, int scr){
		pantalla = scr;
		paint(g);
	}
}
