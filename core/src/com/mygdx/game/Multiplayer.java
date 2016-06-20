package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Figure;

public class Multiplayer implements Screen {

	 private SpriteBatch batch;
	 
	 Sprite spriteP1;
	 Sprite spriteP2;
	 Sprite spriteP3;
	 Sprite spriteP4;
	
	 Texture img;
	 TiledMap tiledMap;
	 TiledMapTileSets tiledSet;
	 OrthographicCamera camera;
//	 TiledMapRenderer tiledMapRenderer;
	 MapProperties prop;
	
	 Figure p1;
	 Texture p1PNG;
	 Figure p2;
	 Texture p2PNG;
	 Figure p3;
	 Texture p3PNG;
	 Figure p4;
	 Texture p4PNG;
	 
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
//	spriteP1 spriteP1;

	public Multiplayer(Bomberman game, int playerCount) {
		this.create(playerCount);
	}

	 private void create(int playerCount) {
	
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
		
		 if(playerCount>=2){
			 p1 = new Figure(1, 17, 3, 1, 2, false);
			 p1PNG = new Texture("P1_Down.png");
			 p2 = new Figure(1, 1, 3, 1, 2, false);
			 p2PNG = new Texture("P2_Down.png");
			 
			 spriteP1 = new Sprite(p1PNG);
			 tiledMapRenderer.addSprite1(spriteP1);
			 spriteP1.setPosition(p1.getX()*16, p1.getY()*16);
			 
			 spriteP2 = new Sprite(p2PNG);
			 tiledMapRenderer.addSprite2(spriteP2);
			 spriteP2.setPosition(p2.getX()*16, p2.getY()*16);
	
			 getInputPlayer1();
			 getInputPlayer2();
		 }
		 if(playerCount>=3){
			 p3 = new Figure(17, 1, 3, 1, 2, false);
			 p3PNG = new Texture("P3_Down.png");	
			 
			 spriteP3 = new Sprite(p3PNG);
			 tiledMapRenderer.addSprite3(spriteP3);
			 spriteP3.setPosition(p3.getX()*16, p3.getY()*16);
			 
			 getInputPlayer3();
		 }
		 if(playerCount>=4){
			 p4 = new Figure(17, 17, 3, 1, 2, false);
			 p4PNG = new Texture("P4_Down.png");
			 
			 spriteP4 = new Sprite(p4PNG);
			 tiledMapRenderer.addSprite4(spriteP4);
			 spriteP4.setPosition(p4.getX()*16, p4.getY()*16);
			 
			 getInputPlayer4();
		 }

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
//		spriteP1 = new spriteP1(texture);
//
//		tiledMap = new TmxMapLoader().load("BombermanMap.tmx");
//		tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
//		tiledMapRenderer.addSprite(spriteP1);
//		Gdx.input.setInputProcessor(this);
//		spriteP1.setPosition(x, y);
//	}

	 protected void getInputPlayer1() {
		 if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			 boolean allowed = false;
			 spriteP1 = new Sprite(new Texture("P1_Up.png"));
			 tiledMapRenderer.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX(), p1.getY()+1);
			 
			 if (allowed == false)
				 p1.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			 boolean allowed = false;
			 spriteP1 = new Sprite(new Texture("P1_Left.png"));
			 tiledMapRenderer.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX()-1, p1.getY());
			 
			 if (allowed == false)
				 p1.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			 boolean allowed = false;
			 spriteP1 = new Sprite(new Texture("P1_Right.png"));
			 tiledMapRenderer.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX()+1, p1.getY());
			 
			 if (allowed == false)
				 p1.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			 boolean allowed = false;
			 spriteP1 = new Sprite(new Texture("P1_Down.png"));
			 tiledMapRenderer.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX(), p1.getY()-1);
			 
			 if (allowed == false)
				 p1.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			 Bomb testbomb = new Bomb(p1.getX(), p1.getY(), p1.getBombRange());
			 Texture textbomb = new Texture("bomb.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 spritebomb.setPosition(p1.getX()*16, p1.getY()*16);
			 BombSpritePair bsp = new BombSpritePair(testbomb, spritebomb);
			 
			 tiledMapRenderer.addBombSpritePair(bsp);
			 
		 }
	 }
	 
	 protected void getInputPlayer2() {
		 if(Gdx.input.isKeyJustPressed(Keys.W)) {
			 boolean allowed = false;
			 spriteP2 = new Sprite(new Texture("P1_Up.png"));
			 tiledMapRenderer.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX(), p2.getY()+1);
			 
			 if (allowed == false)
				 p2.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.A)) {
			 boolean allowed = false;
			 spriteP2 = new Sprite(new Texture("P1_Left.png"));
			 tiledMapRenderer.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX()-1, p2.getY());
			 
			 if (allowed == false)
				 p2.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.D)) {
			 boolean allowed = false;
			 spriteP2 = new Sprite(new Texture("P1_Right.png"));
			 tiledMapRenderer.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX()+1, p2.getY());
			 
			 if (allowed == false)
				 p2.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.S)) {
			 boolean allowed = false;
			 spriteP2 = new Sprite(new Texture("P1_Down.png"));
			 tiledMapRenderer.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX(), p2.getY()-1);
			 
			 if (allowed == false)
				 p2.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {
			 Bomb testbomb = new Bomb(p2.getX(), p2.getY(), p2.getBombRange());
			 Texture textbomb = new Texture("bomb.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 spritebomb.setPosition(p2.getX()*16, p2.getY()*16);
			 BombSpritePair bsp = new BombSpritePair(testbomb, spritebomb);
			 
			 tiledMapRenderer.addBombSpritePair(bsp);
			 
		 }
	 }
	 
	 protected void getInputPlayer3() {
		 if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_8)) {
			 boolean allowed = false;
			 allowed = coll.detect(p3.getX(), p3.getY()+1);
			 
			 if (allowed == false)
				 p3.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_4)) {
			 boolean allowed = false;
			 allowed = coll.detect(p3.getX()-1, p3.getY());
			 
			 if (allowed == false)
				 p3.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_6)) {
			 boolean allowed = false;
			 allowed = coll.detect(p3.getX()+1, p3.getY());
			 
			 if (allowed == false)
				 p3.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_2)) {
			 boolean allowed = false;
			 allowed = coll.detect(p3.getX(), p3.getY()-1);
			 
			 if (allowed == false)
				 p3.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_7)) {
			 Bomb testbomb = new Bomb(p3.getX(), p3.getY(), 2);
			 Texture textbomb = new Texture("P1_Down.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 tiledMapRenderer.addSprite3(spritebomb);
			 spritebomb.setPosition(p3.getX()*16, p3.getY()*16);
		 }
		 
		 if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_8)) {
			 boolean allowed = false;
			 spriteP3 = new Sprite(new Texture("P1_Up.png"));
			 tiledMapRenderer.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX(), p3.getY()+1);
			 
			 if (allowed == false)
				 p3.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_4)) {
			 boolean allowed = false;
			 spriteP3 = new Sprite(new Texture("P1_Left.png"));
			 tiledMapRenderer.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX()-1, p3.getY());
			 
			 if (allowed == false)
				 p3.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_6)) {
			 boolean allowed = false;
			 spriteP3 = new Sprite(new Texture("P1_Right.png"));
			 tiledMapRenderer.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX()+1, p3.getY());
			 
			 if (allowed == false)
				 p3.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_2)) {
			 boolean allowed = false;
			 spriteP3 = new Sprite(new Texture("P1_Down.png"));
			 tiledMapRenderer.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX(), p3.getY()-1);
			 
			 if (allowed == false)
				 p3.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_7)) {
			 Bomb testbomb = new Bomb(p3.getX(), p3.getY(), p3.getBombRange());
			 Texture textbomb = new Texture("bomb.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 spritebomb.setPosition(p3.getX()*16, p3.getY()*16);
			 BombSpritePair bsp = new BombSpritePair(testbomb, spritebomb);
			 
			 tiledMapRenderer.addBombSpritePair(bsp);
			 
		 }
	 }
	 
	 protected void getInputPlayer4() {
		 if(Gdx.input.isKeyJustPressed(Keys.I)) {
			 boolean allowed = false;
			 allowed = coll.detect(p4.getX(), p4.getY()+1);
			 
			 if (allowed == false)
				 p4.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.J)) {
			 boolean allowed = false;
			 allowed = coll.detect(p4.getX()-1, p4.getY());
			 
			 if (allowed == false)
				 p4.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.L)) {
			 boolean allowed = false;
			 allowed = coll.detect(p4.getX()+1, p4.getY());
			 
			 if (allowed == false)
				 p4.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.K)) {
			 boolean allowed = false;
			 allowed = coll.detect(p4.getX(), p4.getY()-1);
			 
			 if (allowed == false)
				 p4.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.H)) {
			 Bomb testbomb = new Bomb(p4.getX(), p4.getY(), 2);
			 Texture textbomb = new Texture("P1_Down.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 tiledMapRenderer.addSprite4(spritebomb);
			 spritebomb.setPosition(p4.getX()*16, p4.getY()*16);
		 }
		 
		 if(Gdx.input.isKeyJustPressed(Keys.I)) {
			 boolean allowed = false;
			 spriteP4 = new Sprite(new Texture("P1_Up.png"));
			 tiledMapRenderer.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX(), p4.getY()+1);
			 
			 if (allowed == false)
				 p4.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.J)) {
			 boolean allowed = false;
			 spriteP4 = new Sprite(new Texture("P1_Left.png"));
			 tiledMapRenderer.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX()-1, p4.getY());
			 
			 if (allowed == false)
				 p4.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.L)) {
			 boolean allowed = false;
			 spriteP4 = new Sprite(new Texture("P1_Right.png"));
			 tiledMapRenderer.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX()+1, p4.getY());
			 
			 if (allowed == false)
				 p4.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.K)) {
			 boolean allowed = false;
			 spriteP4 = new Sprite(new Texture("P1_Down.png"));
			 tiledMapRenderer.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX(), p4.getY()-1);
			 
			 if (allowed == false)
				 p4.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.H)) {
			 Bomb testbomb = new Bomb(p4.getX(), p4.getY(), p4.getBombRange());
			 Texture textbomb = new Texture("bomb.png");
			 
			 Sprite spritebomb = new Sprite(textbomb);
			 spritebomb.setPosition(p4.getX()*16, p4.getY()*16);
			 BombSpritePair bsp = new BombSpritePair(testbomb, spritebomb);
			 
			 tiledMapRenderer.addBombSpritePair(bsp);
			 
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
	// spriteP1.draw(batch);
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
		getInputPlayer2();
		getInputPlayer3();
		getInputPlayer4();
		
		spriteP1.setPosition(p1.getX()*16, p1.getY()*16);
		spriteP2.setPosition(p2.getX()*16, p2.getY()*16);
		if(spriteP3!=null)spriteP3.setPosition(p3.getX()*16, p3.getY()*16);
		if(spriteP3!=null)spriteP4.setPosition(p4.getX()*16, p4.getY()*16);
	}

}
