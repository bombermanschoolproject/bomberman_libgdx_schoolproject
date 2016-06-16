package com.mygdx.game.model;


public class Bomb {
	
	private final int explosionTime = 3000;
	private int strength;
	private int x;
	private int y;
	
	private long startTime;
	
	public Bomb(int xpos, int ypos, int strength) {
		this.x=xpos;
		this.y=ypos;
		this.strength=strength;
		this.startTime=System.currentTimeMillis();
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int timeLeft(){
		if(System.currentTimeMillis()-startTime >= explosionTime){
			return 0;
		}
		return (int) (System.currentTimeMillis()-startTime);
	}
}