import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

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
 */ 

//Singeton pattern

// Name: Rory McClenahan
// Student Number: 22210985

public class Controller implements KeyListener {
        
	   private static boolean KeyAPressed= false;
	   private static boolean KeySPressed= false;
	   private static boolean KeyDPressed= false;
	   private static boolean KeyWPressed= false;
	   private static boolean KeyFPressed= false;
	   private static boolean KeyMPressed= false;
	   private static boolean KeySpacePressed= false; 
	   private static boolean KeyUpPressed= false;
	   private static boolean KeyDownPressed= false;
	   private static boolean KeyLeftPressed= false;
	   private static boolean KeyRightPressed= false;
	   
	   private static final Controller instance = new Controller();
	   
	 public Controller() { 
	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) { 
		 
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_A: setKeyAPressed(true); break;
		    case KeyEvent.VK_S: setKeySPressed(true); break;
		    case KeyEvent.VK_W: setKeyWPressed(true); break;
		    case KeyEvent.VK_D: setKeyDPressed(true); break;
		    case KeyEvent.VK_F: setKeyFPressed(true); break;
		    case KeyEvent.VK_M: setKeyMPressed(true); break;
		    case KeyEvent.VK_SPACE: setKeySpacePressed(true); break;
		    case KeyEvent.VK_UP: setKeyUpPressed(true); break;
		    case KeyEvent.VK_DOWN: setKeyDownPressed(true); break;
		    case KeyEvent.VK_LEFT: setKeyLeftPressed(true); break;
		    case KeyEvent.VK_RIGHT: setKeyRightPressed(true); break; 
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		
	 // You can implement to keep moving while pressing the key here . 
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		GameObject Player1 = Model.PlayersList.get(0);
		GameObject Player2 = Model.PlayersList.get(1);
		
		switch (e.getKeyCode()) {
	    case KeyEvent.VK_A: 
	    	setKeyAPressed(false); 
	    	Player1.resetWalkLeft();
	    	
	    	break;
	    case KeyEvent.VK_S: 
	    	setKeySPressed(false); 
	    	Player1.resetWalkDown();
	    	break;
	    case KeyEvent.VK_W: 
	    	setKeyWPressed(false); 
	    	Player1.resetWalkUp();
	    	break;
	    case KeyEvent.VK_D: 
	    	setKeyDPressed(false);
	    	Player1.resetWalkRight();
	    	break;
	    case KeyEvent.VK_F: setKeyFPressed(false); break;
	    case KeyEvent.VK_M: setKeyMPressed(false); break;
	    case KeyEvent.VK_SPACE: setKeySpacePressed(false); break;
	    case KeyEvent.VK_UP: 
	    	setKeyUpPressed(false);
	    	Player2.resetWalkUp();
	    	break;
	    case KeyEvent.VK_DOWN: 
	    	setKeyDownPressed(false); 
	    	Player2.resetWalkDown();
//	    	System.out.println("Cont rel move down");
	    	break;
	    case KeyEvent.VK_LEFT: 
	    	setKeyLeftPressed(false); 
	    	Player2.resetWalkLeft();
	    	break;
	    case KeyEvent.VK_RIGHT: 
	    	setKeyRightPressed(false);
	    	Player2.resetWalkRight();
	    	break;
	    default:
	        //System.out.println("Controller test: Unknown key pressed");
	        break;
	}
		 //upper case 
	
	}

	// ----------------------------------
	// player 1
	// ----------------------------------
	// A key
	public boolean isKeyAPressed() {
		return KeyAPressed;
	}
	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}
	// ----------------------------------
	// S key
	public boolean isKeySPressed() {
		return KeySPressed;
	}
	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}
	// ----------------------------------
	// D key
	public boolean isKeyDPressed() {
		return KeyDPressed;
	}
	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}
	// ----------------------------------
	// W key
	public boolean isKeyWPressed() {
		return KeyWPressed;
	}
	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}
	// ----------------------------------
	// F key
	public boolean isKeyFPressed() {
		return KeyFPressed;
	}
	public void setKeyFPressed(boolean keyFPressed) {
		KeyFPressed = keyFPressed;
	}
	// ----------------------------------
	// Space key
	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}
	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	} 
	// ----------------------------------
	// player 2
	// ----------------------------------
	// Up Arrow key
	public boolean isKeyUpPressed() {
		return KeyUpPressed;
	}
	public void setKeyUpPressed(boolean keyUpPressed) {
		KeyUpPressed = keyUpPressed;
	}
	// ----------------------------------
	// Down Arrow key
	public boolean isKeyDownPressed() {
		return KeyDownPressed;
	}
	public void setKeyDownPressed(boolean keyDownPressed) {
		KeyDownPressed = keyDownPressed;
	}
	// ----------------------------------
	// Left Arrow key
	public boolean isKeyLeftPressed() {
		return KeyLeftPressed;
	}
	public void setKeyLeftPressed(boolean keyLeftPressed) {
		KeyLeftPressed = keyLeftPressed;
	} 
	// ----------------------------------
	// Right Arrow key
	public boolean isKeyRightPressed() {
		return KeyRightPressed;
	}
	public void setKeyRightPressed(boolean keyRightPressed) {
		KeyRightPressed = keyRightPressed;
	}
	// ----------------------------------
	// M key
	public boolean isKeyMPressed() {
		return KeyMPressed;
	}
	public void setKeyMPressed(boolean keyMPressed) {
		KeyMPressed = keyMPressed;
	}
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
