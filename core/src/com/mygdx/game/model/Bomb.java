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
	public static  float SIZE = 1f;
	public static String Name =  "Bomb";
	public Vector2 position = new Vector2();
	protected Rectangle bounds = new Rectangle();

	public int length;
	public boolean isAutoBoom = true;
	TimerTask task;
	Timer timer;
	public static enum State {NONE,  BOOM}
	public State state;
	private float  animationState;
	
	public Bomb( Vector2 pos, boolean auto, int length){ 
		this.length = length;
		state =  State.NONE;
		isAutoBoom = auto;
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		
		animationState = 0; 
		
		if(auto){
			createTimer();
		}
	}

	public void setState(State state){
			this.state = state;
	}
	
	int timeLeft;
	private void createTimer(){
		timeLeft = 0;
		task = new TimerTask(){
			public void run()
		      { 


				if(state == State.NONE){
					if(timeLeft>3){
						setState(State.BOOM);
						clearTimer();
					}
				}
				
		      }
		};
		timer=new Timer();
	
	}
	
	private void clearTimer(){

		timer = null;
	}
	public Vector2 getPosition() {
		return position;
	}
/*
	public TextureRegion getZeroFrame(){
		return walkAnimation.getKeyFrame(0, true);
	}*/
	public float getAnimationState(){
		animationState+=0.5;
		//animationState+=Gdx.graphics.getDeltaTime();  
		return animationState;
		
	}
	/*public TextureRegion getCurrentFrame(){
		TextureRegion curFrame;
		curState+=0.5;
	
	
		curFrame = walkAnimation.getKeyFrame(curState, true);
		
		
		return curFrame;
	}*/
	
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}