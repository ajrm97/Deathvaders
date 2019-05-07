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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class is the one in charge of running the game
 * Based on the State Machine Design Pattern to keep a better
 * movement of states for the game to feel fluent and real
*/

public class Game extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private Thread animator;
	private volatile boolean running = false;
	private volatile boolean gameOver = false;
	private volatile boolean isPaused = false;
	
	private GameContext context;
	Settings opciones;
	ImageLoader images;
	
	//Constructor
	public Game(){
		opciones = Settings.getInstance();
		images = ImageLoader.getInstance();
		Color c = new Color (92,108,152);
		setBackground(c);
		setPreferredSize(new Dimension(opciones.getWidth(), opciones.getHeight()));
		
		setFocusable(true);
		requestFocus();
		
		context = new GameContext();
		
		//Add mouse and keyboard listeners
		kPressed();
		addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent e) { 
				mPress(e.getX(), e.getY()); }
		});
	}
	
	//Don't use it
	public void addNotify()
	{
		super.addNotify();
		startGame();
	}
	
	//Start the animator thread
	private void startGame()
	{
		if(animator == null || !running){
			animator = new Thread(this);
			animator.start();
		}
	}
	
	//Don't use it
	public void stopGame(){
		running = false;
	}
	
	//Run method of the game, it will execute itself every 20 milliseconds
	public void run(){
		running = true;
		while(running){
			gameUpdate();
			gameRender();
			paintScreen();
			
			try{
				Thread.sleep(20);
			}catch(InterruptedException ex){}
		}
		System.exit(0);
	}
	
	//Update the Context State
	private void gameUpdate(){
		context.update();
		if(!isPaused && !gameOver){
			
		}
		
	}
	
	private Graphics dbg;
	private Image dbImage = null;
	
	//Render the Context State
	private void gameRender(){
		if(dbImage == null){
			dbImage = createImage(opciones.getWidth(),opciones.getHeight());
			if(dbImage == null){
				System.out.println("dbImage is null");
				return;
			}else{
				dbg = dbImage.getGraphics();
			}
		}
		Color c = new Color (92,108,152);
		dbg.setColor(c);
		dbg.fillRect(0,0,opciones.getWidth(),opciones.getHeight());
		context.render(dbg);
	}
	
	//Pain the Context State
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(dbImage != null)
			g.drawImage(dbImage, 0, 0, null);
	}
	
	//Interface Mouse Pressed
	private void mPress(int x, int y){
		context.mPressed(x, y);
	}
	
	//Interface Keyboard Typed
	private void kPressed() {
		addKeyListener( new KeyAdapter() {
			public void keyPressed(KeyEvent e) { 
				context.kPressed(e);
			} 
			public void keyReleased(KeyEvent e) {
				context.kReleased(e);
		    }
			});
	}
	
	//Paint the Screen
	private void paintScreen(){
		Graphics g;
		try{
			g = this.getGraphics();
			if((g != null) && (dbImage != null))
				g.drawImage(dbImage,0,0,null);
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		catch(Exception e){
			System.out.println("Graphics context error: "+e);
		}
	}
	
	//Don't use it
	public void pauseGame(){
		isPaused = true;
	}
	
	//Don't use it
	public void resumeGame(){
		isPaused = false;
	}
	
	//Create the JFrame and the JPanel
	public static void main(String args[]){
		JFrame app = new JFrame("Deathvaders");
	    app.getContentPane().add(new Game(), BorderLayout.CENTER);
	    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    app.pack();
	    app.setResizable(false);  
	    app.setVisible(true);
	}
}