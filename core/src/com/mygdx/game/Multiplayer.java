package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.model.Figure;

public class Multiplayer implements Screen {

	 private SpriteBatch batch;
	 Sprite sprite;
	
	 Texture img;
	 TiledMap tiledMap;
	 TiledMapTileSets tiledSet;
	 OrthographicCamera camera;
//	 TiledMapRenderer tiledMapRenderer;
	 MapProperties prop;
	
	 Figure testfigure;
	 Texture textfigure;
	 
	 CollisionDetection coll;
	
	 int mapWidth;
	 int mapHeight;
	 int tilePixelWidth;
	 int tilePixelHeight;
	
	 int mapPixelWidth;
	 int mapPixelHeight;

//	Texture img;
//	TiledMap tiledMap;
//	OrthographicCamera camera;
	OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
//	Texture texture;
//	Sprite sprite;

	public Multiplayer(Bomberman game) {
		this.create();
	}

	 private void create() {
	
	 batch = new SpriteBatch();
	 camera = new OrthographicCamera();
	
	 tiledMap = new TmxMapLoader().load("BombermanMap.tmx");
	 tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
	 coll = new CollisionDetection(tiledMap);
	 
	 tiledSet = tiledMap.getTileSets();
	
	 prop = tiledMap.getProperties();
	
	 mapWidth = prop.get("width", Integer.class);
	 mapHeight = prop.get("height", Integer.class);
	 tilePixelWidth = prop.get("tilewidth", Integer.class);
	 tilePixelHeight = prop.get("tileheight", Integer.class);
	
	 mapPixelWidth = mapWidth * tilePixelWidth;
	 mapPixelHeight = mapHeight * tilePixelHeight;
	
	 camera.setToOrtho(false,mapPixelWidth,mapPixelHeight);
	 camera.update();
	
	 testfigure = new Figure(1, 1, 3, 1, 2, false);
	 textfigure = new Texture("P1_Down.png");

	 sprite = new Sprite(textfigure);
	 tiledMapRenderer.addSprite(sprite);
	 sprite.setPosition(testfigure.getX()*16, testfigure.getY()*16);
	 getInputPlayer1();
	 }


//	private void create() {
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, w, h);
//		camera.update();
//
//		texture = new Texture(Gdx.files.internal("figur.png"));
//		sprite = new Sprite(texture);
//
//		tiledMap = new TmxMapLoader().load("BombermanMap.tmx");
//		tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
//		tiledMapRenderer.addSprite(sprite);
//		Gdx.input.setInputProcessor(this);
//		sprite.setPosition(x, y);
//	}

	 protected void getInputPlayer1() {
	 if(Gdx.input.isKeyJustPressed(Keys.UP)) {
		 boolean allowed = false;
		 sprite = new Sprite(new Texture("P1_Up.png"));
		 tiledMapRenderer.addSprite(sprite);
		 allowed = coll.detect(testfigure.getX(), testfigure.getY()+1);
		 
		 if (allowed == false)
			 testfigure.moveUp();
	 }
	 else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
		 boolean allowed = false;
		 sprite = new Sprite(new Texture("P1_Left.png"));
		 tiledMapRenderer.addSprite(sprite);
		 allowed = coll.detect(testfigure.getX()-1, testfigure.getY());
		 
		 if (allowed == false)
			 testfigure.moveLeft();
	 }
	 else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
		 boolean allowed = false;
		 sprite = new Sprite(new Texture("P1_Right.png"));
		 tiledMapRenderer.addSprite(sprite);
		 allowed = coll.detect(testfigure.getX()+1, testfigure.getY());
		 
		 if (allowed == false)
			 testfigure.moveRight();
	 }
	 else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
		 boolean allowed = false;
		 sprite = new Sprite(new Texture("P1_Down.png"));
		 tiledMapRenderer.addSprite(sprite);
		 allowed = coll.detect(testfigure.getX(), testfigure.getY()-1);
		 
		 if (allowed == false)
			 testfigure.moveDown();
	 }
	 else if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
		 Bomb testbomb = new Bomb(testfigure.getX(), testfigure.getY(), testfigure.getBombRange());
		 Texture textbomb = new Texture("P1_Down.png");
		 
		 Sprite spritebomb = new Sprite(textbomb);
		 tiledMapRenderer.addSprite(spritebomb);
		 spritebomb.setPosition(testfigure.getX()*16, testfigure.getY()*16);
	 }
	 }
	//
	// @Override
	// public void show() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void render(float delta) {
	// Gdx.gl.glClearColor(0, 0, 0, 1);
	// Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	// camera.update();
	// tiledMapRenderer.setView(camera);
	// tiledMapRenderer.render();
	// batch.setProjectionMatrix(camera.combined);
	// batch.begin();
	// sprite.draw(batch);
	// batch.end();
	// }

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

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		getInputPlayer1();
		
		sprite.setPosition(testfigure.getX()*16, testfigure.getY()*16);
		//coll.detect();
	}

}
