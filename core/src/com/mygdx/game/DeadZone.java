package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class DeadZone {
	private final int deadZoneTime = 500;
	private long startTime;
	private int x;
	private int y;
	private Sprite sprite;
	private RectangleMapObject rmo;
	


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
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public RectangleMapObject getRmo() {
		return rmo;
	}

	public void setRmo(RectangleMapObject rmo) {
		this.rmo = rmo;
	}
}
