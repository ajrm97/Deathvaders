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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * This class is the one in charge of loading the images at the
 * start of the game, so that every time that an image is
 * referenced it would not try to Buffer the image
 * and instead just have it as a variable
 */

public class ImageLoader {
	private static ImageLoader images = null;
	static boolean firstThread = true;
	BufferedImage gear, title1, exit, p1, p2, sonido, mute, back, creditos;
	BufferedImage paused, player1, player2, ball1, ball2, ball3, ball4, coop, vs;
	BufferedImage restart, menu, laserX, laserY, clock, cross, shield, portal1, teleport;
	BufferedImage rewind, plat, nuke, portal2, life, boom, hs, ice, confused;
	BufferedImage player3, player4, player5, player6, player7, player8, player9, player10, player11;
	BufferedImage player12, player13, player14, player15, team, help;
	private ImageLoader(){
		//Imagen gear
		String nomImagen= "gear.png";
		File archImagen =new  File(nomImagen);
		try{
			gear= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//imagen titulo
		nomImagen= "title1.png";
		archImagen =new  File(nomImagen);
		try{
			title1= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//exit image
		nomImagen= "exit.png";
		archImagen =new  File(nomImagen);
		try{
			exit= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//1p image
		nomImagen= "1p.png";
		archImagen =new  File(nomImagen);
		try{
			p1= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		} 
		//2p image
		nomImagen= "2p.png";
		archImagen =new  File(nomImagen);
		try{
			p2= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//imagen de sonido
		nomImagen= "sonido.png";
		archImagen =new  File(nomImagen);
		try{
			sonido= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//imagen de mute
		nomImagen= "mute.png";
		archImagen =new  File(nomImagen);
		try{
			mute= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//imagen de creditos
		nomImagen= "Creditos.png";
		archImagen =new  File(nomImagen);
		try{
			creditos= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//imagen atrás
		nomImagen= "back.png";
		archImagen =new  File(nomImagen);
		try{
			back = ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Pause logo
		nomImagen= "paused.png";
		archImagen =new  File(nomImagen);
		try{
			paused = ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//menu logo
		nomImagen= "menu.png";
		archImagen =new  File(nomImagen);
		try{
			menu = ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//restart logo
		nomImagen= "restart.png";
		archImagen =new  File(nomImagen);
		try{
			restart= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//player skins
		nomImagen= "player1.png";
		archImagen =new  File(nomImagen);
		try{
			player1= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player2.png";
		archImagen =new  File(nomImagen);
		try{
			player2= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player3.png";
		archImagen =new  File(nomImagen);
		try{
			player3= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player4.png";
		archImagen =new  File(nomImagen);
		try{
			player4= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player5.png";
		archImagen =new  File(nomImagen);
		try{
			player5= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player6.png";
		archImagen =new  File(nomImagen);
		try{
			player6= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player7.png";
		archImagen =new  File(nomImagen);
		try{
			player7= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player8.png";
		archImagen =new  File(nomImagen);
		try{
			player8= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player9.png";
		archImagen =new  File(nomImagen);
		try{
			player9= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player10.png";
		archImagen =new  File(nomImagen);
		try{
			player10= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player11.png";
		archImagen =new  File(nomImagen);
		try{
			player11= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player12.png";
		archImagen =new  File(nomImagen);
		try{
			player12= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player13.png";
		archImagen =new  File(nomImagen);
		try{
			player13= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player14.png";
		archImagen =new  File(nomImagen);
		try{
			player14= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		nomImagen= "player15.png";
		archImagen =new  File(nomImagen);
		try{
			player15= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//ball1
		nomImagen= "ball1.png";
		archImagen =new  File(nomImagen);
		try{
			ball1= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//ball2
		nomImagen= "ball2.png";
		archImagen =new  File(nomImagen);
		try{
			ball2= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//ball3
		nomImagen= "ball3.png";
		archImagen =new  File(nomImagen);
		try{
			ball3= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//ball4
		nomImagen= "ball4.png";
		archImagen =new  File(nomImagen);
		try{
			ball4= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Coop
		nomImagen= "coop.png";
		archImagen =new  File(nomImagen);
		try{
			coop= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Versus
		nomImagen= "vs.png";
		archImagen =new  File(nomImagen);
		try{
			vs= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//LaserX
		nomImagen= "laserX.png";
		archImagen =new  File(nomImagen);
		try{
			laserX= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//LaserY
		nomImagen= "laserY.png";
		archImagen =new  File(nomImagen);
		try{
			laserY= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Clock
		nomImagen= "clock.png";
		archImagen =new  File(nomImagen);
		try{
			clock= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Cross
		nomImagen= "cruz.png";
		archImagen =new  File(nomImagen);
		try{
			cross= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Shield
		nomImagen= "escudo.png";
		archImagen =new  File(nomImagen);
		try{
			shield= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Portal1
		nomImagen= "portal1.png";
		archImagen =new  File(nomImagen);
		try{
			portal1= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Portal2
		nomImagen= "portal2.png";
		archImagen =new  File(nomImagen);
		try{
			portal2= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Teleport
		nomImagen= "teleport.png";
		archImagen =new  File(nomImagen);
		try{
			teleport= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Rewind
		nomImagen= "rewind.png";
		archImagen =new  File(nomImagen);
		try{
			rewind= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Plat
		nomImagen= "plat.png";
		archImagen =new  File(nomImagen);
		try{
			plat= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Nuke
		nomImagen= "nuke.png";
		archImagen =new  File(nomImagen);
		try{
			nuke= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Life
		nomImagen= "life.png";
		archImagen =new  File(nomImagen);
		try{
			life= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Boom
		nomImagen= "boom.png";
		archImagen =new  File(nomImagen);
		try{
			boom= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//HighScore
		nomImagen= "hs.png";
		archImagen =new  File(nomImagen);
		try{
			hs= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Ice Block
		nomImagen= "ice.png";
		archImagen =new  File(nomImagen);
		try{
			ice= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Confused
		nomImagen= "confused.png";
		archImagen =new  File(nomImagen);
		try{
			confused= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Team
		nomImagen= "team.png";
		archImagen =new  File(nomImagen);
		try{
			team= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
		//Help
		nomImagen= "help.png";
		archImagen =new  File(nomImagen);
		try{
			help= ImageIO.read(archImagen);	
					
		} catch (IOException e){
			e.printStackTrace();		
		}
	}
	public static ImageLoader getInstance(){
		
		if (images == null){
			if (firstThread){
				firstThread = false;
				
				Thread.currentThread();
				try { 
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
			}
			synchronized(ImageLoader.class){
				if (images == null){
					images = new ImageLoader();  
				}
			}
		}
		return images;
	}
	public BufferedImage getImage (String str){
		if(str.equals("gear")){
			return gear;
		}
		if(str.equals("title1")){
			return title1;
		}
		if(str.equals("exit")){
			return exit;
		}
		if(str.equals("p1")){
			return p1;
		}
		if(str.equals("p2")){
			return p2;
		}
		if(str.equals("sonido")){
			return sonido;
		}
		if(str.equals("mute")){
			return mute;
		}
		if(str.equals("creditos")){
			return creditos;
		}
		if(str.equals("back")){
			return back;
		}
		if(str.equals("paused")){
			return paused;
		}
		if(str.equals("restart")){
			return restart;
		}
		if(str.equals("menu")){
			return menu;
		}
		if(str.equals("player1")){
			return player1;
		}
		if(str.equals("player2")){
			return player2;
		}
		if(str.equals("player3")){
			return player3;
		}
		if(str.equals("player4")){
			return player4;
		}
		if(str.equals("player5")){
			return player5;
		}
		if(str.equals("player6")){
			return player6;
		}
		if(str.equals("player7")){
			return player7;
		}
		if(str.equals("player8")){
			return player8;
		}
		if(str.equals("player9")){
			return player9;
		}
		if(str.equals("player10")){
			return player10;
		}
		if(str.equals("player11")){
			return player11;
		}
		if(str.equals("player12")){
			return player12;
		}
		if(str.equals("player13")){
			return player13;
		}
		if(str.equals("player14")){
			return player14;
		}
		if(str.equals("player15")){
			return player15;
		}
		if(str.equals("ball1")){
			return ball1;
		}
		if(str.equals("ball2")){
			return ball2;
		}
		if(str.equals("ball3")){
			return ball3;
		}
		if(str.equals("ball4")){
			return ball4;
		}
		if(str.equals("coop")){
			return coop;
		}
		if(str.equals("vs")){
			return vs;
		}
		if(str.equals("clock")){
			return clock;
		}
		if(str.equals("portal1")){
			return portal1;
		}
		if(str.equals("portal2")){
			return portal2;
		}
		if(str.equals("teleport")){
			return teleport;
		}
		if(str.equals("shield")){
			return shield;
		}
		if(str.equals("cross")){
			return cross;
		}
		if(str.equals("laserY")){
			return laserY;
		}
		if(str.equals("laserX")){
			return laserX;
		}
		if(str.equals("rewind")){
			return rewind;
		}
		if(str.equals("nuke")){
			return nuke;
		}
		if(str.equals("plat")){
			return plat;
		}
		if(str.equals("life")){
			return life;
		}
		if(str.equals("boom")){
			return boom;
		}
		if(str.equals("hs")){
			return hs;
		}
		if(str.equals("ice")){
			return ice;
		}
		if(str.equals("confused")){
			return confused;
		}
		if(str.equals("team")){
			return team;
		}
		if(str.equals("help")){
			return help;
		}
		return null;
	}
}
