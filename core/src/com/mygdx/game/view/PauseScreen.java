package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bomberman;
import com.mygdx.game.Multiplayer;

public class PauseScreen implements Screen{

	Texture background;
	Bomberman game;
	Multiplayer screen;
	
	Texture back1;
	Texture back2;
	Rectangle backarea;
	
	Texture continue1;
	Texture continue2;
	Rectangle continuearea;
    
	public PauseScreen(Bomberman game, Multiplayer screen) {
		this.game=game;
    	this.screen=screen;
    	this.background = new Texture("StartMenu2.png");
    	
    	this.continue1=new Texture("Continue_Button.png");
    	this.continue2=new Texture("Continue_Button_2.png");
    	
    	this.back1=new Texture("Back_Button.png");
    	this.back2=new Texture("Back_Button_2.png");
    	
    	this.continuearea = new Rectangle(297,280,214,55);
    	this.backarea = new Rectangle(297,399,214,55);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(continue1, 50, 400);
        game.batch.draw(back1, 50, 281);

        if(continuearea.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(continue2, 50, 400);
        	if(Gdx.input.justTouched()){
        		game.setScreen(screen);
        	}
        }
        if(backarea.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(back2, 50, 281);
        	if(Gdx.input.justTouched()){
        		game.setScreen(new StartMenu(game));
        	}
        }
        
        game.batch.end();
    }
		

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
