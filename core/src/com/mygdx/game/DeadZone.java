package com.mygdx.game;

public class DeadZone {
	private final int deadZoneTime = 500;
	private long startTime;
	private int x;
	private int y;
	
	public DeadZone(int xpos, int ypos) {
		this.x=xpos;
		this.y=ypos;
		this.startTime=System.currentTimeMillis();
	}
	
	public int timeLeft(){
		if(System.currentTimeMillis()-startTime >= deadZoneTime){
			return 0;
		}
		return (int) (System.currentTimeMillis()-startTime);
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
}
