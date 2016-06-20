package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
    private Sprite sprite;
    private List<Sprite> sprites;
    private int drawSpritesAfterLayer = 1;
    private Sprite p1sprite;
    private Sprite p2sprite;
    private Sprite p3sprite;
    private Sprite p4sprite;
    private List<BombSpritePair> bombsprites;

    public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
        super(map);
        sprites = new ArrayList<Sprite>();
        bombsprites = new ArrayList<BombSpritePair>();
    }

    public void addSprite1(Sprite sprite){
        p1sprite = sprite;
    }
    public void addSprite2(Sprite sprite){
    	p2sprite = sprite;
    }
    public void addSprite3(Sprite sprite){
    	p3sprite = sprite;
    }
    public void addSprite4(Sprite sprite){
    	p4sprite = sprite;
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
                    if(currentLayer == drawSpritesAfterLayer){
                        for(Sprite sprite : sprites)
                            sprite.draw(this.getBatch());
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
            
        }
        
        for(BombSpritePair bsp : bombsprites) {
        	bsp.getSprite().draw(this.getBatch());
        }
        p1sprite.draw(this.getBatch());	
        p2sprite.draw(this.getBatch());
        p3sprite.draw(this.getBatch());
        p4sprite.draw(this.getBatch());
        endRender();
    }
}