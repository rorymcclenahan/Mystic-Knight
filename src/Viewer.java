import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.GameObject;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 

// Name: Rory McClenahan
// Student Number: 22210985

public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	
	Model gameworld =new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
//		System.out.println(CurrentAnimationTime);
		
		//Draw background 
		drawBackground(g);
		if (!MainWindow.gameOver && !MainWindow.gameWin) {
			
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer1().getCentre().getX();
		int y = (int) gameworld.getPlayer1().getCentre().getY();
		int width = (int) gameworld.getPlayer1().getWidth();
		int height = (int) gameworld.getPlayer1().getHeight();
		String texture = gameworld.getPlayer1().getTexture();
		
		
		
		
		//Draw player
		if (gameworld.getPlayer1().isAlive()) {
			drawPlayer(x, y, width, height, texture,g, 0);
		}
		drawHealth(gameworld.getPlayer1(),0,g);

		
		if (MainWindow.twoPlayer) {
			int x2 = (int) gameworld.getPlayer2().getCentre().getX();
			int y2 = (int) gameworld.getPlayer2().getCentre().getY();
			int width2 = (int) gameworld.getPlayer2().getWidth();
			int height2 = (int) gameworld.getPlayer2().getHeight();
			String texture2 = gameworld.getPlayer2().getTexture();
			if (gameworld.getPlayer2().isAlive()) {
				drawPlayer(x2, y2, width2, height2, texture2,g, 1);
			}
			drawHealth(gameworld.getPlayer2(),1,g);
		}
		
		
		
		
		//Draw Bullets 
		// change back 
		gameworld.getBullets().forEach((temp) -> 
		{ 
			drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		}); 
		
		gameworld.getSlashes().forEach((temp) ->
		{
			drawSlash((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), temp, g);
		});
		
		//Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> 
		{
			drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),temp,g);	 
		 
	    });
		
		//Draw Door
		if (gameworld.getDoor().isAlive()) {
			drawDoor((int) gameworld.getDoor().getCentre().getX(),
					(int) gameworld.getDoor().getCentre().getY(),
					gameworld.getDoor().getWidth(),
					gameworld.getDoor().getHeight(),
					gameworld.getDoor().getTexture(),
					g);
		}
		}
		
//		System.out.println((int) gameworld.getDoor().getCentre().getX());
//		System.out.println((int) gameworld.getDoor().getCentre().getY());
//		System.out.println(gameworld.getDoor().getWidth());
//		System.out.println(gameworld.getDoor().getHeight());
//		System.out.println(gameworld.getDoor().getTexture());
	}
	
	private void drawEnemies(int x, int y, int width, int height, String texture, GameObject enemy, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
//			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4 )*32); //slows down animation so every 10 frames we get another frame so every 100ms 
//			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
			
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%30)/10))*16; //slows down animation so every 10 frames we get another frame so every 100ms
			if (enemy.getWalkIdle())  // default animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, 15  , 36, 31, 54, null); 
			}
			if (enemy.getWalkDown()) // down walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 36, currentPositionInAnimation+15, 54, null); 
			}
			if (enemy.getWalkUp()) // up walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+15, 18, null); 
			}
			if (enemy.getWalkLeft()) // left walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 54, currentPositionInAnimation+15, 72, null); 
			}
			if (enemy.getWalkRight()) // right walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 18, currentPositionInAnimation+15, 36, null); 
			}
			
			
			//The sprite is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private void drawBackground(Graphics g)
	{
		if (gameworld.getCurrLevel() == 0 && !MainWindow.gameOver) {
			File TextureToLoad = new File("res/Level1MapFinal.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				Image myImage = ImageIO.read(TextureToLoad); 
				 g.drawImage(myImage, 0,0, 960, 640, 0 , 0, 960, 640, null); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (gameworld.getCurrLevel() == 1 && !MainWindow.gameOver) {
			File TextureToLoad = new File("res/Level2MapFinal.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				Image myImage = ImageIO.read(TextureToLoad); 
				 g.drawImage(myImage, 0,0, 960, 640, 0 , 0, 960, 640, null); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (MainWindow.gameOver) {
			File TextureToLoad = new File("res/gameOverBackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				Image myImage = ImageIO.read(TextureToLoad); 
				 g.drawImage(myImage, 0,0, 960, 640, 0 , 0, 960, 640, null); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (MainWindow.gameWin) {
			File TextureToLoad = new File("res/gameWinBackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				Image myImage = ImageIO.read(TextureToLoad); 
				 g.drawImage(myImage, 0,0, 960, 640, 0 , 0, 960, 640, null); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void drawHealth(GameObject player, int playerIndex, Graphics g)
	{
		File TextureToLoad = new File("res/pixelHeartFinal.png");
		int numLives = player.getLives();
		int x = playerIndex == 0 ? 30 : 300;
	    try {
	        Image myImage = ImageIO.read(TextureToLoad);
	        g.setFont(new Font("Impact", Font.PLAIN, 20));
	        g.setColor(Color.WHITE);
	        g.drawString("Player " +(playerIndex + 1) + " Lives", x, 20);
	        x += 100;
	        int width = myImage.getWidth(null);
            int height = myImage.getHeight(null);
	        for (int i = 0; i < numLives && i < 3; i++) { // Ensure no more than 3 lives are drawn
	            g.drawImage(myImage, x + 10, 5, width + 10, height, null);
	            
	            x += width + 13;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void drawDoor(int x, int y, int width, int height, String texture,Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//32 by 64
			 g.drawImage(myImage, x,y, x+width, y+height,0,0,width,height, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0, 16, 15, 32, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawSlash(int x, int y, int width, int height, String texture, GameObject slash, Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%30)/20))*16; //slows down animation so every 10 frames we get another frame so every 100ms
//			System.out.println(currentPositionInAnimation);
//			System.out.println(CurrentAnimationTime);
			if (slash.getWalkDown()) // down walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 32, currentPositionInAnimation+15, 48, null); 
			}
			if (slash.getWalkUp()) // up walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+15, 16, null); 
			}
			if (slash.getWalkLeft()) // left walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 48, currentPositionInAnimation+15, 64, null); 
			}
			if (slash.getWalkRight()) // right walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 16, currentPositionInAnimation+15, 32, null); 
			}
//			g.drawImage(myImage, x,y, x+width, y+height, 0, 0, 16, 16, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g, int playerNum) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			
			GameObject Player = gameworld.getPlayers().get(playerNum);
			
			//The sprite is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%30)/10))*16; //slows down animation so every 10 frames we get another frame so every 100ms
			
			if (Player.getWalkIdle())  // default animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, 15  , 36, 31, 54, null); 
			}
			if (Player.getWalkDown()) // down walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 36, currentPositionInAnimation+15, 54, null); 
			}
			if (Player.getWalkUp()) // up walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+15, 18, null); 
			}
			if (Player.getWalkLeft()) // left walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 54, currentPositionInAnimation+15, 72, null); 
			}
			if (Player.getWalkRight()) // right walk animation
			{
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 18, currentPositionInAnimation+15, 36, null); 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
	}
		 
	 

}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
