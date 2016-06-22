package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.control.BombSpritePair;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Figure;
import com.mygdx.game.view.PauseScreen;

public class Multiplayer extends OrthogonalTiledMapRenderer implements Screen{
	 
	 private Bomberman game;
	 
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
	 DeadDetection dd;
	
	 int mapWidth;
	 int mapHeight;
	 int tilePixelWidth;
	 int tilePixelHeight;
	
	 int mapPixelWidth;
	 int mapPixelHeight;
	 
	 boolean p1alive = true;
	 boolean p2alive = true;
	 boolean p3alive = true;
	 boolean p4alive = true;
	 
	   private Sprite sprite;
	   private List<Sprite> sprites;
	   private int drawSpritesAfterLayer = 1;
	   private Sprite p1sprite;
	   private Sprite p2sprite;
	   private Sprite p3sprite;
	   private Sprite p4sprite;
	    
	   private Sprite bomb1;
	   private Sprite bomb2;
	   private Sprite bomb3;
	   private Sprite bomb4;
	   private Sprite bomb5;
	   private Sprite bomb6;
	    
	   private List<BombSpritePair> bombsprites;
	   private List<DeadZone> deadzones;
	   private int count=0;
	    
	   private BombDetection bd;
	   private Texture txtstart;
	   private Texture txtvertical;
	   private Texture txthorizontal;
	    
	OrthogonalTiledMapRendererWithSprites tiledMapRenderer;


	public Multiplayer(TiledMap map, Bomberman game, int playerCount) {
		super(map);
		this.game=game;
		this.tiledSet=map.getTileSets();
		this.prop=map.getProperties();
		this.coll=new CollisionDetection(map);
		this.dd=new DeadDetection(map);
		this.create(playerCount);
		this.txtvertical = new Texture("Explosion_UpDown.png");
		this.txtstart = new Texture("Explosion_Middle.png");
		this.txthorizontal = new Texture("Explosion_LeftRight.png");
	}

	 private void create(int playerCount) {
	
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		 
		sprites = new ArrayList<Sprite>();
		bombsprites = new ArrayList<BombSpritePair>();
		deadzones = new ArrayList<DeadZone>();
		bd = new BombDetection(this.getMap());
		bomb1 = new Sprite(new Texture("bomb.png"));
		bomb2 = new Sprite(new Texture("bomb2.png"));
		bomb3 = new Sprite(new Texture("bomb3.png"));
		bomb4 = new Sprite(new Texture("bomb4.png"));
		bomb5 = new Sprite(new Texture("bomb5.png"));
		bomb6 = new Sprite(new Texture("bomb6.png"));
		
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
			 this.addSprite1(spriteP1);
			 spriteP1.setPosition(p1.getX()*16, p1.getY()*16);
			 
			 spriteP2 = new Sprite(p2PNG);
			 this.addSprite2(spriteP2);
			 spriteP2.setPosition(p2.getX()*16, p2.getY()*16);
	
			 getInputPlayer1();
			 getInputPlayer2();
		 }
		 if(playerCount>=3){
			 p3 = new Figure(17, 1, 3, 1, 2, false);
			 p3PNG = new Texture("P3_Down.png");	
			 
			 spriteP3 = new Sprite(p3PNG);
			 this.addSprite3(spriteP3);
			 spriteP3.setPosition(p3.getX()*16, p3.getY()*16);
			 
			 getInputPlayer3();
		 }
		 if(playerCount>=4){
			 p4 = new Figure(17, 17, 3, 1, 2, false);
			 p4PNG = new Texture("P4_Down.png");
			 
			 spriteP4 = new Sprite(p4PNG);
			 this.addSprite4(spriteP4);
			 spriteP4.setPosition(p4.getX()*16, p4.getY()*16);
			 
			 getInputPlayer4();
		 }

	 }
	 
    public void addSprite1(Sprite sprite){
        p1sprite = sprite;
    }
    public void addSprite2(Sprite sprite){
    	p2sprite = sprite;
    }
    public void addSprite3(Sprite sprite){
    	p3sprite = sprite;
    	this.count = 3;
    }
    public void addSprite4(Sprite sprite){
    	p4sprite = sprite;
    	this.count=4;
    }
    
    public void addBombSpritePair(BombSpritePair bsp) {
    	bombsprites.add(bsp);
    }

	 protected void getInputPlayer1() {
		 if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			 int allowed = 0;
			 spriteP1 = new Sprite(new Texture("P1_Up.png"));
			 this.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX(), p1.getY()+1);

			 
			 if (allowed == 0 && p1alive)
				 p1.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			 int allowed = 0;
			 spriteP1 = new Sprite(new Texture("P1_Left.png"));
			 this.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX()-1, p1.getY());
			 
			 if (allowed == 0 && p1alive)
				 p1.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			 int allowed = 0;
			 spriteP1 = new Sprite(new Texture("P1_Right.png"));
			 this.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX()+1, p1.getY());
			 
			 if (allowed == 0 && p1alive)
				 p1.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			 int allowed = 0;
			 spriteP1 = new Sprite(new Texture("P1_Down.png"));
			 this.addSprite1(spriteP1);
			 allowed = coll.detect(p1.getX(), p1.getY()-1);
			 
			 if (allowed == 0 && p1alive)
				 p1.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			 if(p1.getBombsPlaced() < p1.getBombs()) {
				 if(coll.detect(p1.getX(), p1.getY()) == 0) {
					 Bomb testbomb = new Bomb(p1.getX(), p1.getY(), p1.getBombRange());
					 BombSpritePair bsp = new BombSpritePair(p1, testbomb);
					 bsp.dispose();
					 
					 bsp.setRmo(new RectangleMapObject(p1.getX()*16, p1.getY()*16, 16, 16));
					 map.getLayers().get("Bombs").getObjects().add(bsp.getRmo());
					 this.addBombSpritePair(bsp);
					 p1.plusBombsPlaced();
				 }
			 }
		 }
	 }
	 
	 protected void getInputPlayer2() {
		 if(Gdx.input.isKeyJustPressed(Keys.W)) {
			 int allowed = 0;
			 spriteP2 = new Sprite(new Texture("P2_Up.png"));
			 this.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX(), p2.getY()+1);
			 
			 if (allowed == 0 && p2alive)
				 p2.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.A)) {
			 int allowed = 0;
			 spriteP2 = new Sprite(new Texture("P2_Left.png"));
			 this.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX()-1, p2.getY());
			 
			 if (allowed == 0 && p2alive)
				 p2.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.D)) {
			 int allowed = 0;
			 spriteP2 = new Sprite(new Texture("P2_Right.png"));
			 this.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX()+1, p2.getY());
			 
			 if (allowed == 0 && p2alive)
				 p2.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.S)) {
			 int allowed = 0;
			 spriteP2 = new Sprite(new Texture("P2_Down.png"));
			 this.addSprite2(spriteP2);
			 allowed = coll.detect(p2.getX(), p2.getY()-1);
			 
			 if (allowed == 0 && p2alive)
				 p2.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {
			 if(p2.getBombsPlaced() < p2.getBombs()) {
				 Bomb testbomb = new Bomb(p2.getX(), p2.getY(), p2.getBombRange());
				 BombSpritePair bsp = new BombSpritePair(p2, testbomb);
				 bsp.dispose();
				 bsp.setRmo(new RectangleMapObject(p2.getX()*16, p2.getY()*16, 16, 16));
				 this.addBombSpritePair(bsp);
				 p2.plusBombsPlaced();
			 }
			 
		 }
	 }
	 
	 protected void getInputPlayer3() {
		 if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_8)) {
			 int allowed = 0;
			 spriteP3 = new Sprite(new Texture("P3_Up.png"));
			 this.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX(), p3.getY()+1);
			 
			 if (allowed == 0 && p3alive)
				 p3.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_4)) {
			 int allowed = 0;
			 spriteP3 = new Sprite(new Texture("P3_Left.png"));
			 this.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX()-1, p3.getY());
			 
			 if (allowed == 0 && p3alive)
				 p3.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_6)) {
			 int allowed = 0;
			 spriteP3 = new Sprite(new Texture("P3_Right.png"));
			 this.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX()+1, p3.getY());
			 
			 if (allowed == 0 && p3alive)
				 p3.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_2)) {
			 int allowed = 0;
			 spriteP3 = new Sprite(new Texture("P3_Down.png"));
			 this.addSprite3(spriteP3);
			 allowed = coll.detect(p3.getX(), p3.getY()-1);
			 
			 if (allowed == 0 && p3alive)
				 p3.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.NUMPAD_7)) {
			 if(p3.getBombsPlaced() < p3.getBombs()) {
				 Bomb testbomb = new Bomb(p3.getX(), p3.getY(), p3.getBombRange());
				 BombSpritePair bsp = new BombSpritePair(p3, testbomb);
				 bsp.dispose();
				 this.addBombSpritePair(bsp);
				 p3.plusBombsPlaced();
			 }
		 }
	 }
	 
	 protected void getInputPlayer4() {
		 if(Gdx.input.isKeyJustPressed(Keys.I)) {
			 int allowed = 0;
			 spriteP4 = new Sprite(new Texture("P4_Up.png"));
			 this.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX(), p4.getY()+1);
			 
			 if (allowed == 0 && p4alive)
				 p4.moveUp();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.J)) {
			 int allowed = 0;
			 spriteP4 = new Sprite(new Texture("P4_Left.png"));
			 this.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX()-1, p4.getY());
			 
			 if (allowed == 0 && p4alive)
				 p4.moveLeft();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.L)) {
			 int allowed = 0;
			 spriteP4 = new Sprite(new Texture("P4_Right.png"));
			 this.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX()+1, p4.getY());
			 
			 if (allowed == 0 && p4alive)
				 p4.moveRight();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.K)) {
			 int allowed = 0;
			 spriteP4 = new Sprite(new Texture("P4_Down.png"));
			 this.addSprite4(spriteP4);
			 allowed = coll.detect(p4.getX(), p4.getY()-1);
			 
			 if (allowed == 0 && p4alive)
				 p4.moveDown();
		 }
		 else if(Gdx.input.isKeyJustPressed(Keys.H)) {
			 if(p4.getBombsPlaced() < p4.getBombs()) {
				 Bomb testbomb = new Bomb(p4.getX(), p4.getY(), p4.getBombRange());
				 BombSpritePair bsp = new BombSpritePair(p4, testbomb);
				 bsp.dispose();
				 this.addBombSpritePair(bsp);
				 p4.plusBombsPlaced();
			 }
		 }
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

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
   public void detectBomb(BombSpritePair bsp) {
    	
    	for(int i = 0; i <= bsp.getBomb().getStrength() && bsp.getDzaddedDOWN() == 0; i++) {
    		bsp.setBlockedExplosion(false);
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX(), bsp.getBomb().getY()-i);
    		if (r != null) {
    			map.getLayers().get("Boxes").getObjects().remove(r);
        		Sprite grasspritee = new Sprite(new Texture("gras.png"));
    			grasspritee.setPosition(bsp.getBomb().getX()*16, (bsp.getBomb().getY()-i)*16);
        		sprites.add(grasspritee);
        		bsp.setDzaddedDown();
    		}
    		
    		if(coll.detect(bsp.getBomb().getX(), bsp.getBomb().getY()-i) == 1) {
    			//bsp.setDzaddedDown();
    			bsp.setBlockedExplosion(true);
    			bsp.setDzaddedDown();
    		}
    		
    		if(bsp.getDzadded() == 0 && bsp.getDzaddedDOWN() == 0) {
    			if(i == 0)
    				addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()-i, txtstart);
    			else
    				addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()-i, txtvertical);
    		}
    		else if (bsp.getDzadded() == 0 && !bsp.isBlockedExplosion()){
    			addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()-i, txtvertical);
    		}
    	}
    	for(int i = 1; i <= bsp.getBomb().getStrength() && bsp.getDzaddedUP() == 0; i++) {
    		bsp.setBlockedExplosion(false);
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX(), bsp.getBomb().getY()+i);
    		if (r != null) {
    			map.getLayers().get("Boxes").getObjects().remove(r);
        		Sprite grasspritee = new Sprite(new Texture("gras.png"));
    			grasspritee.setPosition(bsp.getBomb().getX()*16, (bsp.getBomb().getY()+i)*16);
        		sprites.add(grasspritee);
        		bsp.setDzaddedUp();
    		}
    		
    		if(coll.detect(bsp.getBomb().getX(), bsp.getBomb().getY()+i) == 1) {
    			bsp.setDzaddedUp();
    			bsp.setBlockedExplosion(true);
    		}
    		
    		if(bsp.getDzadded() == 0 && bsp.getDzaddedUP() == 0) {
    			addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()+i, txtvertical); 			
    		}
    		else if (bsp.getDzadded() == 0 && !bsp.isBlockedExplosion()){
    			addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()+i, txtvertical);
    		}
    	}
    	for(int i = 1; i <= bsp.getBomb().getStrength() && bsp.getDzaddedRIGHT() == 0; i++) {
    		bsp.setBlockedExplosion(false);
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX()+i, bsp.getBomb().getY());
    		if (r != null) {
    			map.getLayers().get("Boxes").getObjects().remove(r);
        		Sprite grasspritee = new Sprite(new Texture("gras.png"));
    			grasspritee.setPosition((bsp.getBomb().getX()+i)*16, bsp.getBomb().getY()*16);
        		sprites.add(grasspritee);
        		bsp.setDzaddedRight();
    		}
    		
    		if(coll.detect(bsp.getBomb().getX()+i, bsp.getBomb().getY()) == 1) {
    			bsp.setDzaddedRight();
    			bsp.setBlockedExplosion(true);
    		}
    		
    		if(bsp.getDzadded() == 0 && bsp.getDzaddedRIGHT() == 0) {
    			addDeadZone(bsp.getBomb().getX()+i, bsp.getBomb().getY(), txthorizontal); 			
    		}
    		else if (bsp.getDzadded() == 0 && !bsp.isBlockedExplosion()){
    			addDeadZone(bsp.getBomb().getX()+i, bsp.getBomb().getY(), txthorizontal);
    		}
    	}
    	for(int i = 1; i <= bsp.getBomb().getStrength() && bsp.getDzaddedLEFT() == 0; i++) {
    		bsp.setBlockedExplosion(false);
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX()-i, bsp.getBomb().getY());
    		if (r != null) {
    			map.getLayers().get("Boxes").getObjects().remove(r);
        		Sprite grasspritee = new Sprite(new Texture("gras.png"));
    			grasspritee.setPosition((bsp.getBomb().getX()-i)*16, bsp.getBomb().getY()*16);
        		sprites.add(grasspritee);
        		bsp.setDzaddedLeft();
    		}
    		
    		if(coll.detect(bsp.getBomb().getX()-i, bsp.getBomb().getY()) == 1) {
    			bsp.setDzaddedLeft();
    			bsp.setBlockedExplosion(true);
    		}
    		
    		if(bsp.getDzadded() == 0 && bsp.getDzaddedLEFT() == 0) {
    			addDeadZone(bsp.getBomb().getX()-i, bsp.getBomb().getY(), txthorizontal); 			
    		}
    		else if (bsp.getDzadded() == 0 && !bsp.isBlockedExplosion()){
    			addDeadZone(bsp.getBomb().getX()-i, bsp.getBomb().getY(), txthorizontal);
    		}
    	}
    	if(bsp.getDzadded() == 0)
    		bsp.getFigure().minusBombsPlaced();
    	
    	bsp.setDzadded();
    }
	    
    public void addDeadZone(int x, int y, Texture txt) {
    	DeadZone dz = new DeadZone(x, y);
    	
    	Sprite explosionUpDown = new Sprite(txt);
		explosionUpDown.setPosition(dz.getX()*16, dz.getY()*16);
		dz.setSprite(explosionUpDown);
		dz.setRmo(new RectangleMapObject(dz.getX()*16, dz.getY()*16, 16, 16));
		map.getLayers().get("DeadZones").getObjects().add(dz.getRmo());
		deadzones.add(dz);
    }
    
    public boolean checkDeadZone(DeadZone dz) {
    	if(dz.timeLeft() <= 0) {
    		map.getLayers().get("DeadZones").getObjects().remove(dz.getRmo());
    		return true;
    	}
    	else
    		return false;
    }

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		this.setView(camera);
		beginRender();
		

        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                  //  if(currentLayer == drawSpritesAfterLayer){
                        for(Sprite sprite : sprites)
                            sprite.draw(this.getBatch());
                   // }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
            
        }
	          
		for(BombSpritePair bsp : bombsprites) {
			if(bsp.getBomb().timeLeft() != 0)
				bsp.getSprite().draw(this.getBatch());
			else {
					detectBomb(bsp);
					if(bsp.getDzadded() > 0)
						map.getLayers().get("Bombs").getObjects().remove(bsp.getRmo());
			}
		}
	        
        for (DeadZone dz : deadzones) {
        	if(checkDeadZone(dz) == false)
        		dz.getSprite().draw(this.getBatch());
        }
//        
        if(p1sprite!=null)p1sprite.draw(this.getBatch());	
        if(p2sprite!=null)p2sprite.draw(this.getBatch());
        if(p3sprite!=null)p3sprite.draw(this.getBatch());
        if(p4sprite!=null)p4sprite.draw(this.getBatch());
        
		if(p1alive) {
			getInputPlayer1();
			p1alive = dd.detect(p1.getX(), p1.getY());
		}
		if(p2alive) {
			getInputPlayer2();
			p2alive = dd.detect(p2.getX(), p2.getY());
		}
		if(spriteP3!=null) {
			getInputPlayer3();
			p3alive = dd.detect(p3.getX(), p3.getY());
		}
		if(spriteP4!=null) {
			getInputPlayer4();
			p4alive = dd.detect(p4.getX(), p4.getY());
		}
		
		if(p1alive)
			spriteP1.setPosition(p1.getX()*16, p1.getY()*16);
		else
			this.addSprite1(null);
		
		if(p2alive)
			spriteP2.setPosition(p2.getX()*16, p2.getY()*16);
		else
			this.addSprite2(null);
		
		if(spriteP3!=null && p3alive)spriteP3.setPosition(p3.getX()*16, p3.getY()*16);
		if(spriteP4!=null && p4alive)spriteP4.setPosition(p4.getX()*16, p4.getY()*16);
	
	
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new PauseScreen(game,this));
		}
		endRender();
	}

}
