package com.mygdx.game.control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Figure;

public class BombSpritePair {
	
	private Bomb bomb;
	
	private Figure figure;
	
	private int x;
	private int y;
	
	private Sprite sprite;
	
	private Sprite sprite2;
	private Sprite sprite3;
	private Sprite sprite4;
	private Sprite sprite5;
	private Sprite sprite6;
	
	private long startTime=System.currentTimeMillis();
	
	public BombSpritePair(Figure figure, Bomb bomb, Sprite sprite) {
		this.figure=figure;
		this.bomb = bomb;
		this.sprite = sprite;
		
		this.x=figure.getX();
		this.y=figure.getY();
		
		this.sprite2=new Sprite(new Texture("bomb2.png"));
		this.sprite3=new Sprite(new Texture("bomb3.png"));
		this.sprite4=new Sprite(new Texture("bomb4.png"));
		this.sprite5=new Sprite(new Texture("bomb5.png"));
		this.sprite6=new Sprite(new Texture("bomb6.png"));
	}
	
	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public Sprite getSprite() {
		if(System.currentTimeMillis()-startTime < 500){
			sprite.setPosition(x*16, y*16);
			return sprite;
		}
		else if(System.currentTimeMillis()-startTime < 1000){
			sprite2.setPosition(x*16, y*16);
			return sprite2;
		}
		else if(System.currentTimeMillis()-startTime < 1500){
			sprite3.setPosition(x*16, y*16);
			return sprite3;
		}
		else if(System.currentTimeMillis()-startTime < 2000){
			sprite4.setPosition(x*16, y*16);
			return sprite4;
		}
		else if(System.currentTimeMillis()-startTime < 2500){
			sprite5.setPosition(x*16, y*16);
			return sprite5;
		}
		else if(System.currentTimeMillis()-startTime < 3000){
			sprite6.setPosition(x*16, y*16);
			return sprite6;
		}
		
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
