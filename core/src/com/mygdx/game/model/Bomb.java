package com.mygdx.game.model;

import java.util.TimerTask;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.control.Logic;

public class Bomb {
	
	public static String textureName = "images/BombSprite.png";
	
	public static final int FRAME_COLS =2; 
	public static final int FRAME_ROWS = 2;
	public static String Name =  "Bomb";
	public Arena arena;
	protected Rectangle bounds = new Rectangle();

	public int length;
	TimerTask task;
	Timer timer;
	private float  animationState;
	
	public Bomb(Arena arena, int length){ 
		this.arena=arena;
		this.length = length;
		this.bounds.width = 1;
		this.bounds.height = 1;
		
		animationState = 0; 
	}

	public float getAnimationState(){
		animationState+=0.5;
		//animationState+=Gdx.graphics.getDeltaTime();  
		return animationState;
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}