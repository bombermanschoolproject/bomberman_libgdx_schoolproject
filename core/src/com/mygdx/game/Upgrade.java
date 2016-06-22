package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class Upgrade {

	private int upgradeType;
	private int x;
	private int y;
	private Sprite sprite;
	private RectangleMapObject rmo;
	private boolean pickedUp = false; 
	
	private Random rand;

	public Upgrade(int xpos, int ypos) {
		this.x=xpos;
		this.y=ypos;
		rand = new Random();
		upgradeType = 1+rand.nextInt(2); 
	}
	
	public int getUpgradeType() {
		return upgradeType;
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
