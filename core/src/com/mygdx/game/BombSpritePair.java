package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BombSpritePair {
	
	private Bomb bomb;
	
	private Sprite sprite;
	
	public BombSpritePair(Bomb bomb, Sprite sprite) {
		this.bomb = bomb;
		this.sprite = sprite;
	}
	
	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
