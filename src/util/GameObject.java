package util;

import java.util.concurrent.CopyOnWriteArrayList;

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

// Name: Rory McClenahan
// Student Number: 22210985

public class GameObject {
	
	private Point3f centre= new Point3f(0,0,0);			// Centre of object, using 3D as objects may be scaled  
	private int width=10;
	private int height=10;
	private boolean hasTextured=false;
	private String textureLocation; 
	private String blanktexture="res/blankSprite.png";
	
	// Walk booleans for players
	public boolean walkUp;
    public boolean walkDown;
    public boolean walkLeft;
    public boolean walkRight;
    public boolean walkIdle;
    
    public int lives;
    private GameObject parent;
	
	public GameObject() {  
		
	}
	
    public GameObject(String textureLocation,int width,int height,Point3f centre) { 
    	 hasTextured=true;
    	 this.textureLocation=textureLocation;
    	 this.width=width;
		 this.height=height;
//		 this.centre = new Point3f(width/2,height/2,0);
		 this.centre =centre;
		 // Walk booleans for players
		 this.walkDown = false;
		 this.walkUp = false;
		 this.walkLeft = false;
		 this.walkRight = false;
		 this.walkIdle = true;
		 this.lives = 1;
	}

	public Point3f getCentre() {
		return centre;
	}

	public void setCentre(Point3f centre) {
		this.centre = centre;
		
		//make sure to put boundaries on the gameObject 
	 
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTexture() {
		if(hasTextured) 
			{
			return textureLocation;
			}
		 
		return blanktexture; 
	}
	
	// ----------------------------------------------
	// distance helpers
	// ----------------------------------------------
	public double getXDifference (GameObject objectOne, GameObject objectTwo) {
		return objectOne.getCentre().getX()-objectTwo.getCentre().getX();
	}
	public double getYDifference (GameObject objectOne, GameObject objectTwo) {
		return objectOne.getCentre().getY()-objectTwo.getCentre().getY();
	}
	public double getDistance(GameObject objectOne, GameObject objectTwo) {
		return Math.sqrt(getXDifference(objectOne,objectTwo)*getXDifference(objectOne,objectTwo)+
				getYDifference(objectOne,objectTwo)*getYDifference(objectOne,objectTwo));
	}
	public double normalizeX(GameObject objectOne, GameObject objectTwo) {
		return getXDifference(objectOne,objectTwo)/(getDistance(objectOne, objectTwo));
	}
	public double normalizeY(GameObject objectOne, GameObject objectTwo) {
		return getYDifference(objectOne,objectTwo)/(getDistance(objectOne, objectTwo));
	}
	
	// ----------------------------------------------
	// collision detection
	// ----------------------------------------------
	public GameObject getClosestObject(GameObject targetObject, CopyOnWriteArrayList<GameObject> objectList) {
	    GameObject closestObject = null;
	    float minDistance = Float.MAX_VALUE;
	    
	    for (GameObject object : objectList) {
	        if (object.isAlive()) {
	            double distance = getDistance(targetObject, object);
	            if (distance < minDistance) {
	                closestObject = object;
	                minDistance = (float) distance;
	            }
	        }
	    }
	    return closestObject;
	}
	public String getDirectionToObject(GameObject originObject, GameObject targetObject) {
		double normalizedX = originObject.normalizeX(originObject, targetObject);
		double normalizedY = originObject.normalizeY(originObject, targetObject);
		if (Math.abs(normalizedX) > Math.abs(normalizedY)) {
			return normalizedX > 0 ? "left" : "right";
		} else {
			return normalizedY > 0 ? "up" : "down";
		}
	}
	
	// ----------------------------------------------
	// lives
	// ----------------------------------------------
	public int getLives() {
		return lives;
	}
	public void removeLife() {
		lives --;
	}
	public void setLives(int newLives) {
		lives = newLives;
	}
	public void killObject() {
		lives = 0;
	}
	public boolean isAlive() {
		return lives > 0;
	}
	public boolean isDead() {
		return lives <= 0;
	}
	public void setParentObject(GameObject parentObject) {
		parent = parentObject;
	}
	public GameObject getParentObject(GameObject childObject) {
		return parent;
	}
	
	// ----------------------------------------------
	// walk animation helpers for players
	// ----------------------------------------------
	public boolean getWalkUp() { // walk up methods
    	return walkUp;
    }
    public void startWalkUp() {
    	walkIdle = false;
    	walkUp = true;
    	if (getWalkDown()) { // prevent opposite directions
    		walkIdle = true;
    		walkUp = false;
    		walkDown = false;
    	}
    }
    public void resetWalkUp() {
    	walkUp = false;
    	walkIdle = true;
    }
    
    public boolean getWalkDown() { // walk down methods
    	return walkDown;
    }
    public void startWalkDown() {
    	walkIdle = false;
    	walkDown = true;
    	if (getWalkUp()) { // prevent opposite directions
    		walkIdle = true;
    		walkUp = false;
    		walkDown = false;
    	}
    }
    public void resetWalkDown() {
    	walkDown = false;
    	walkIdle = true;
    }
    
    public boolean getWalkLeft() { // walk left methods
    	return walkLeft;
    }
    public void startWalkLeft() {
    	walkIdle = false;
    	walkLeft = true;
    	walkUp = false; // check if moving up and right
    	walkDown = false; // check if moving down and right
    	if (getWalkRight()) { // prevent opposite directions
    		walkIdle = true;
    		walkLeft = false;
    		walkRight = false;
    	}
    }
    public void resetWalkLeft() {
    	walkLeft = false;
    	walkIdle = true;
    }
    public boolean getWalkRight() {
    	return walkRight;
    }
    public void startWalkRight() {
    	walkIdle = false;
    	walkRight = true;
    	walkUp = false; // check if moving up and right
    	walkDown = false; // check if moving down and right
    	if (getWalkLeft()) { // prevent opposite directions
    		walkIdle = true;
    		walkLeft = false;
    		walkRight = false;
    	}
    }
    public void resetWalkRight() {
    	walkRight = false;
    	walkIdle = true;
    }
    public boolean getWalkIdle() {
    	return walkIdle;
    }
    public void startWalkIdle() {
    	walkIdle = true;
    	walkUp = false;
    	walkDown = false;
    	walkLeft = false;
    	walkRight = false;
    }
    public void resetWalkIdle() {
    	walkIdle = false;
    }
    public String getDirection() {
    	if (getWalkLeft()) {
    		return "left";
    	} else if (getWalkRight()) {
    		return "right";
    	} else if (getWalkUp()) {
    		return "up";
    	} else if (getWalkDown()) {
    		return "down";
    	} else {
    		return "idle";
    	}
    }
	public void updateEnemyDirection(GameObject enemy, GameObject Player) {
		if (enemy.getXDifference(enemy, Player)>0) {
			enemy.startWalkLeft();
		} else if (enemy.getXDifference(enemy, Player)<0) {
			enemy.startWalkRight();
		} else if (enemy.getXDifference(enemy, Player) == 0 && enemy.getYDifference(enemy, Player) > 0) {
			enemy.startWalkUp();
		} else if (enemy.getXDifference(enemy, Player) == 0 && enemy.getYDifference(enemy, Player) < 0) {
			enemy.startWalkDown();
		} else {
			enemy.startWalkIdle();
		}
	}
}

/*
 *  Game Object 
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::::::c:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::clc::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::lol:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::::::::;;cool:;::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::codk0Oxolc:::::::::::::::::::::::::::::::::::::::::::::::::::::
::::::::::::::::::::::::::::::::coxk0XNWMMWWWNK0kxdolc::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::loxO0XWMMMMMMMWWWMMMMMMWWNK0Oxdlc::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::cldkOKNWMMMMMMMMMMMMMWWMMMMMMMMMMMMMWNX0Okdolc:::::::::::::::::::::::::::::::::
::::::::::::::::codk0KNWMMMMMMMMMMMMMMMMMMWMMMMMMMMMMMMMMMMMMMMWWXKOkxo:::::::::::::::::::::::::::::
::::::::::::;cdOXNWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMMNKOdc::::::::::::::::::::::::::::
:::::::::::::cxKXXNWWMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMMMMMMMMMWX0kdolc:::::::::::::::::::::::::::::
::::::::::::::d0000KKXNNWMMMMMMMMMMMMMMMMWWMMMMMMMMMMMMMMMMWNKOxolllllc:::::::::::::::::::::::::::::
::::::::::::::oO00000000KXXNWWMMMMMMMMMMMMWMMMMMMMMMMMMMWX0kdollllllllc:::::::::::::::::::::::::::::
::::::::::::::lk00000O000000KKXNWWMMMMMMMWWWMMMMMMMMWNKOxollllllllllll::::::::::::::::::::::::::::::
::::::::::::::cx0000000000O000000KXXNWMMMWWWMMMMWXK0kdlllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::dO00000000000000000000KKXNNNWWNKOxolllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::lO000000000000000OOOO0000000Kkdlllllllllllllllllllllllc::::::::::::::::::::::::::::::
:::::::::::::::ck00000000000000000OOOOOOOOkxollllllllllllllllllllllll:::::::::::::::::::::::::::::::
:::::::::::::;;cx00000000000000000000OOOOOOxocllllllllllllllllllllllc:;;;;;;;;;;::::::::::::::::::::
;;;;;;;;;;;;;;;:oO00000000000000000OOOO0000kdllllcclllllllllllllllllc:;;;;;;;;;;;;;;;;;;::::::::::::
;;;;;;;;;;;;;;;:lO00000000000000OOO00000000Oolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;ck0000000000OOO000000000000kolllllllllllllllllllllll:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:dOO0000OOO0000000000000000kolllllllllllllllllllllllc:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;:lxkOOOO0000000000000000000kolllllllllllllllllllllooool::;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;::;;::cokOkkO00000000000000000000kolllllllllllllllllllllccccllcc::::;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;:;;:;::ccllodxkO00000000000000000000kolllllllllllllllllllcc:;;;;:::::;;:;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::::::::::;;:ldkO0000000000000000000kolllllllllllllllllc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;::;;:::;;;;;:;::ldkO0000000000000000kollllllllllllllcc::;;;;:;;:;;;;;:;;;:;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;:;;;;:cldkO0000000000000kollllllllllllc:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;::ldkO0000000000kolllllllllcc::;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;::;:;;::ldkO0000000kolllllllc::::;;;;:;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::ldkO0000kolllcc:::;;;;;;;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;;;:ldkO0kolcc::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;;::lodl:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;::;:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:;;::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::;;;;;;;;;;;:::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
*/