package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.view.Multiplayer;

public class MainMenu implements Screen{

	Texture background;
	Bomberman game;
	
	Texture exit1;
	Texture exit2;
	Rectangle exitarea;
	
//	Texture mp1;
//	Texture mp2;
	Rectangle mparea;

    public MainMenu(Bomberman game) {
    	this.game=game;
    	this.background = new Texture("StartScreen.png");
    	
//    	this.mp1=new Texture("");
//    	this.mp2=new Texture("");
//    	
//    	this.exit1=new Texture("");
//    	this.exit2=new Texture("");
    	
    	this.mparea = new Rectangle(651,420,626,98);
    	this.exitarea = new Rectangle(651,540,626,98);
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
//        game.batch.draw(mp1, 610, 500);
//        game.batch.draw(exit1, 610, 143);

        if(Gdx.input.justTouched()){
        	game.setScreen(new Multiplayer(game));
        }

//        if (mparea.contains(Gdx.input.getX(),Gdx.input.getY())){
//        	game.batch.draw(mp2, 610, 500);
//        	if(Gdx.input.justTouched()){
//        		game.setScreen(new Multiplayer(game));
//        	}
//        }
        
//        if (exitarea.contains(Gdx.input.getX(),Gdx.input.getY())){
//        	game.batch.draw(exit2, 610, 143);
//        	if(Gdx.input.justTouched()){
//        		System.exit(0);
//        	}
//        }
        
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
