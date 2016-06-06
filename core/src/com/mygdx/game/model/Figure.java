package com.mygdx.game.model;

public class Figure {
	
	private int x;
	private int y;
	private int lives;
//	private int speed;
	private int bombs;
	private int bombRange;
	private boolean kickable;
	private State currState;
	private Direction directionState;
	
	public enum Direction{
		LEFT, RIGHT, UP, DOWN
	}
	public enum State{
		WALKING, DEAD, IDLE
	}
	
	public Figure(int x, int y, int lives, int bombs, int bombRange, boolean kickable){
		this.x=x;
		this.y=y;
		this.lives=lives;
		this.bombs=bombs;
		this.bombRange=bombRange;
//		this.speed=speed;
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

//	public int getSpeed() {
//		return speed;
//	}
//
//	public void setSpeed(int speed) {
//		this.speed = speed;
//	}

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

	public void setDirection(Direction d) {
		directionState=d;
	}
	
	public Direction getDirection(){
		return directionState;
	}

	public void setState(State state) {
		currState=state;
	}
	
	public State getState(){
		return currState;
	}
	
	public void moveRight() {
		if(x < 17)
			x++;
	}
	
	public void moveLeft() {
		if(x > 1)
			x--;
	}
	
	public void moveUp() {
		if(y < 17)
			y++;
	}
	
	public void moveDown() {
		if (y > 1)
			y--;
	}
	
}
