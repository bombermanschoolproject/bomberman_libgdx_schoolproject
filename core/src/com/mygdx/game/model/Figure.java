package com.mygdx.game.model;

import com.mygdx.game.model.Figure.Direction;
import com.mygdx.game.model.Figure.State;

public class Figure {
	
	private int x;
	private int y;
	private int lives;
	private int speed;
	private int bombs;
	private int bombRange;
	private boolean kickable;
	
	public enum Direction{
		LEFT, RIGHT, UP, DOWN
	}
	public enum State{
		WALKING, DEAD, IDLE
	}
	
	public Figure(int x, int y, int lives, int speed, int bombs, int bombRange, boolean kickable){
		this.x=x;
		this.y=y;
		this.lives=lives;
		this.bombs=bombs;
		this.bombRange=bombRange;
		this.speed=speed;
		this.kickable=kickable;
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getBombRange() {
		return bombRange;
	}

	public void setBombRange(int bombRange) {
		this.bombRange = bombRange;
	}

	public boolean isKickable() {
		return kickable;
	}

	public void setKickable(boolean kickable) {
		this.kickable = kickable;
	}

	public boolean isDead() {
		if(lives==0){
			return true;
		}
		return false;
	}

	public int getVelocity1() {
		return speed;
	}

	public Object getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDirection(Direction down, boolean b) {
		
	}

	public void setState(State walking) {
		// TODO Auto-generated method stub
		
	}
	
	
}
