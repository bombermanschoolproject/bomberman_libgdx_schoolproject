package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WalkAnimation implements ApplicationListener{
	
	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 1;
	
	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	SpriteBatch batch;
	TextureRegion currentFrame;
	
	float stateTime;
	
	@Override
	public void create() {
		walkSheet = new Texture(Gdx.files.internal("Animation_Left_Right.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index=0;
		for(int i=0; i<FRAME_ROWS; i++){
			for(int j=0; j<FRAME_COLS; j++){
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames);
		batch = new SpriteBatch();
		stateTime = 0f;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		batch.draw(currentFrame, 50, 50);
		batch.end();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
