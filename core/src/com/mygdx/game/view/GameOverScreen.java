package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Bomberman;
import com.mygdx.game.Multiplayer;

public class GameOverScreen implements Screen{

	Texture background;
	Bomberman game;
	
	private int won;
	
	Texture back1;
	Texture back2;
	Rectangle backarea;
	
	Texture continue1;
	Texture continue2;
	Rectangle continuearea;
	
	private BitmapFont scoreFont;
	private SpriteBatch batch;
    
	public GameOverScreen(Bomberman game, int won) {
		batch = new SpriteBatch();
		this.game=game;
    	this.background = new Texture("EndMenu.png");

    	this.won=won;
		this.scoreFont = new BitmapFont(Gdx.files.internal ("font1.fnt"));
		this.scoreFont.setColor(Color.RED);
    	
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
        
        
        if(won==1){
        	scoreFont.draw(batch, "Player 1 won!", 100, 399);
        }
        else if(won==2){
        	scoreFont.draw(batch, "Player 2 won!", 100, 399);
        }
        else if(won==3){
        	scoreFont.draw(batch, "Player 3 won!", 100, 399);
        }
        else if(won==4){
        	scoreFont.draw(batch, "Player 4 won!", 100, 399);
        }
        
        game.batch.draw(back1, 50, 281);

        
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
