package com.mygdx.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class BombDetection {
	private TiledMap map;
	
	public BombDetection(TiledMap map) {
        this.map = map;
    }
	
	public boolean detect(int posx, int posy) {	
		boolean hit = false;
		for (RectangleMapObject r : map.getLayers().get("Boxes").getObjects().getByType(RectangleMapObject.class)) {

				Rectangle rect = r.getRectangle();
				Rectangle recto = new Rectangle(posx*16,
						posy*16,
						16,
						16);
				if (Intersector.overlaps(rect, recto))
				{
					map.getLayers().get("Boxes").getObjects().remove(r);
					hit = true;
				}
		}
		return hit;
	}
}
