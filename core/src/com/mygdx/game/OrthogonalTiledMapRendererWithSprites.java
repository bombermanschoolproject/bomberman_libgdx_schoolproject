package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.control.BombSpritePair;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
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
    
    private Sprite explosionUpDown;
    
    private List<BombSpritePair> bombsprites;
    private List<DeadZone> deadzones;
    private Sprite grassprite;
    private int count=0;
    private boolean detectAllowed = true;
    
    private BombDetection bd;

    public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
        super(map);
        sprites = new ArrayList<Sprite>();
        bombsprites = new ArrayList<BombSpritePair>();
        deadzones = new ArrayList<DeadZone>();
        bd = new BombDetection(this.getMap());
        grassprite = new Sprite(new Texture("gras.png"));
        bomb1 = new Sprite(new Texture("bomb.png"));
        bomb2 = new Sprite(new Texture("bomb2.png"));
        bomb3 = new Sprite(new Texture("bomb3.png"));
        bomb4 = new Sprite(new Texture("bomb4.png"));
        bomb5 = new Sprite(new Texture("bomb5.png"));
        bomb6 = new Sprite(new Texture("bomb6.png"));
        explosionUpDown = new Sprite(new Texture("Explosion_UpDown.png"));
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

    @Override
    public void render() {
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
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
        	detectAllowed=true;
        	if(bsp.getBomb().timeLeft() != 0)
        		bsp.getSprite().draw(this.getBatch());
        	else {
        		if(detectAllowed) {
        			//addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY());
        			detectBomb(bsp);
        			detectAllowed = false;
        		}
        	}
        		
        		
//        		for(i = 1; i <= bsp.getBomb().getStrength() && !end; i++) {
//	        		if(bd.detect(bsp.getBomb().getX(), bsp.getBomb().getY()-i)){
//	        			Sprite sprite = new Sprite(new Texture("gras.png"));
//	            		sprite.setPosition(bsp.getBomb().getX()*16, (bsp.getBomb().getY()-i)*16);
//	            		sprites.add(sprite);
//	            		end = true;
//		    		}
//        		}
//        		
//        		for(i = 1; i <= bsp.getBomb().getStrength() && !end; i++) {
//        			if(bd.detect(bsp.getBomb().getX()+i, bsp.getBomb().getY())) {
//        				Sprite sprite = new Sprite(new Texture("gras.png"));
//        				sprite.setPosition((bsp.getBomb().getX()+i)*16, bsp.getBomb().getY()*16);
//        				sprites.add(sprite);
//        				end = true;
//        			}
//	    		}
//        		end = false;
//        		for(i = 1; i <= bsp.getBomb().getStrength() && !end; i++) {
//        			if(bd.detect(bsp.getBomb().getX()-i, bsp.getBomb().getY())){
//        				Sprite sprite = new Sprite(new Texture("gras.png"));
//        				sprite.setPosition((bsp.getBomb().getX()-i)*16, bsp.getBomb().getY()*16);
//        				sprites.add(sprite);
//        				end = true;
//        			}
//        		}
        		
        		//bombsprites.remove(bsp);
        	
        }
        
        for (DeadZone dz : deadzones) {
        	if(checkDeadZone(dz) == false)
        		dz.getSprite().draw(this.getBatch());
        }
        if(p1sprite!=null)p1sprite.draw(this.getBatch());	
        if(p2sprite!=null)p2sprite.draw(this.getBatch());
        if(p3sprite!=null)p3sprite.draw(this.getBatch());
        if(p4sprite!=null)p4sprite.draw(this.getBatch());
        endRender();
        
        
    }
    
    public void detectBomb(BombSpritePair bsp) {
    	detectAllowed = false;
    	
    	for(int i = 0; i <= bsp.getBomb().getStrength() && !detectAllowed; i++) {
    		
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX(), bsp.getBomb().getY()-i);
    		if (r != null) {
    			if(!detectAllowed){
    				map.getLayers().get("Boxes").getObjects().remove(r);
    			}
        		grassprite.setPosition(bsp.getBomb().getX()*16, (bsp.getBomb().getY()-i)*16);
        		sprites.add(grassprite);
        		
        		//DeadZone
        		
    			
    			detectAllowed = true;
    		}
    		addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()-i);
    	}
    	for(int i = 0; i <= bsp.getBomb().getStrength() && !detectAllowed; i++) {
    		
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX(), bsp.getBomb().getY()+i);
    		if (r != null) {
    			if(!detectAllowed){
    				map.getLayers().get("Boxes").getObjects().remove(r);
    			}
        		grassprite.setPosition(bsp.getBomb().getX()*16, (bsp.getBomb().getY()+i)*16);
        		sprites.add(grassprite);
        		
        		//DeadZone
        		
    			
    			detectAllowed = true;
    		}
    		addDeadZone(bsp.getBomb().getX(), bsp.getBomb().getY()+i);
    	}
    	for(int i = 0; i <= bsp.getBomb().getStrength() && !detectAllowed; i++) {
    		
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX()-i, bsp.getBomb().getY());
    		if (r != null) {
    			if(!detectAllowed){
    				map.getLayers().get("Boxes").getObjects().remove(r);
    			}
        		grassprite.setPosition((bsp.getBomb().getX()-i)*16, bsp.getBomb().getY()*16);
        		sprites.add(grassprite);
        		
        		//DeadZone
        		
    			
    			detectAllowed = true;
    		}
    		addDeadZone(bsp.getBomb().getX()-i, bsp.getBomb().getY());
    	}
    	for(int i = 0; i <= bsp.getBomb().getStrength() && !detectAllowed; i++) {
    		
    		RectangleMapObject r = bd.detect(bsp.getBomb().getX()+i, bsp.getBomb().getY());
    		if (r != null) {
    			if(!detectAllowed){
    				map.getLayers().get("Boxes").getObjects().remove(r);
    			}
        		grassprite.setPosition((bsp.getBomb().getX()+i)*16, bsp.getBomb().getY()*16);
        		sprites.add(grassprite);
        		
        		//DeadZone
        		
    			
    			detectAllowed = true;
    		}
    		addDeadZone(bsp.getBomb().getX()+i, bsp.getBomb().getY());
    	}
    }
    
    public void addDeadZone(int x, int y) {
    	DeadZone dz = new DeadZone(x, y);
    	
		explosionUpDown.setPosition(dz.getX()*16, dz.getY()*16);
		dz.setSprite(explosionUpDown);
		dz.setRmo(new RectangleMapObject(dz.getX()*16, dz.getY()*16, 16, 16));
		map.getLayers().get("DeadZones").getObjects().add(dz.getRmo());
		deadzones.add(dz);
    }
    
    public boolean checkDeadZone(DeadZone dz) {
    	if(dz.timeLeft() == 0) {
    		map.getLayers().get("DeadZones").getObjects().remove(dz.getRmo());
    		return true;
    	}
    	else
    		return false;
    }
}