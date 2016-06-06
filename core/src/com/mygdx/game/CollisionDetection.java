package com.mygdx.game;

import java.util.ArrayList;
import java.util.Properties;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class CollisionDetection {
	private TiledMap map;
	
	public CollisionDetection(TiledMap map) {
        this.map = map;
    }
	
	public boolean detect() {
		for (MapLayer layer : map.getLayers()) {

			MapProperties prop = layer.getProperties();
			System.out.println(prop.get("Collidable"));
		}
		return true;
	}
}
