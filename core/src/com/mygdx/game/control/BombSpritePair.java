package com.mygdx.game.control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Figure;

public class BombSpritePair implements Disposable{
	
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
	
	private int dzadded;
	private int dzaddedLEFT;
	private int dzaddedRIGHT;
	private int dzaddedDOWN;
	private int dzaddedUP;
	
	private boolean blockedExplosion;
	
	private RectangleMapObject rmo;
	
	public int getDzadded() {
		return dzadded;
	}

	public void setDzadded() {
		dzadded++;
	}

	private long startTime=System.currentTimeMillis();
	
	public BombSpritePair(Figure figure, Bomb bomb) {
		this.figure=figure;
		this.bomb = bomb;
		
		this.x=figure.getX();
		this.y=figure.getY();
		
		this.sprite=new Sprite(new Texture("bomb.png"));
		this.sprite2=new Sprite(new Texture("bomb2.png"));
		this.sprite3=new Sprite(new Texture("bomb3.png"));
		this.sprite4=new Sprite(new Texture("bomb4.png"));
		this.sprite5=new Sprite(new Texture("bomb5.png"));
		this.sprite6=new Sprite(new Texture("bomb6.png"));
		
		blockedExplosion = false;
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

	@Override
	public void dispose() {
		if(System.currentTimeMillis()-startTime>5000)
			this.dispose();
	}
	
	public Figure getFigure() {
		return figure;
	}

	public void setDzaddedLeft() {
		dzaddedLEFT++;
	}
	public void setDzaddedRight() {
		dzaddedRIGHT++;
	}
	public void setDzaddedUp() {
		dzaddedUP++;
	}
	public void setDzaddedDown() {
		dzaddedDOWN++;
	}

	public int getDzaddedLEFT() {
		return dzaddedLEFT;
	}

	public int getDzaddedRIGHT() {
		return dzaddedRIGHT;
	}

	public int getDzaddedDOWN() {
		return dzaddedDOWN;
	}

	public int getDzaddedUP() {
		return dzaddedUP;
	}
	
	public RectangleMapObject getRmo() {
		return rmo;
	}

	public void setRmo(RectangleMapObject rmo) {
		this.rmo = rmo;
	}

	public boolean isBlockedExplosion() {
		return blockedExplosion;
	}

	public void setBlockedExplosion(boolean blockedExplosion) {
		this.blockedExplosion = blockedExplosion;
	}
	
	
}
