package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bomberman;
import com.mygdx.game.Multiplayer;

public class StartMenu implements Screen{

	Texture background;
	Bomberman game;
	
	Texture exit1;
	Texture exit2;
	Rectangle exitarea;
	
	Texture p2;
	Texture p2_2;
	Rectangle p2area;

	Texture p3;
	Texture p3_2;
	Rectangle p3area;
	
	Texture p4;
	Texture p4_2;
	Rectangle p4area;
	
    public StartMenu(Bomberman game) {
    	this.game=game;
    	this.background = new Texture("StartMenu2.png");
    	
    	this.p2 = new Texture("P2_Button.png");
    	this.p2_2 = new Texture("P2_Button_2.png");
    	this.p2area = new Rectangle(297,280,214,55);
    	
      	this.p3 = new Texture("P3_Button.png");
    	this.p3_2 = new Texture("P3_Button_2.png");
    	this.p3area = new Rectangle(297,399,214,55);
    	
      	this.p4 = new Texture("P4_Button.png");
    	this.p4_2 = new Texture("P4_Button_2.png");
    	this.p4area = new Rectangle(297,518,214,55);
    	
    	this.exit1 = new Texture("Exit_Button.png");
    	this.exit2 = new Texture("Exit_Button_2.png");
    	this.exitarea = new Rectangle(297,637,214,55);
    	
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
        game.batch.draw(p2, 50, 400);
        game.batch.draw(p3, 50, 281);
        game.batch.draw(p4, 50, 162);
        game.batch.draw(exit1, 50, 43);

        if(p2area.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(p2_2, 50, 400);
        	if(Gdx.input.justTouched()){
        		game.setScreen(new Multiplayer(game,2));
        	}
        }
        if(p3area.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(p3_2, 50, 281);
        	if(Gdx.input.justTouched()){
        		game.setScreen(new Multiplayer(game,3));
        	}
        }
        if(p4area.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(p4_2, 50, 162);
        	if(Gdx.input.justTouched()){
        		game.setScreen(new Multiplayer(game,4));
        	}
        }
        if(exitarea.contains(Gdx.input.getX(),Gdx.input.getY())){
        	game.batch.draw(exit2, 50, 43);
        	if(Gdx.input.justTouched()){
        		System.exit(0);
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
