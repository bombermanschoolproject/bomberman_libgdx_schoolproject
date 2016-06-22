package com.mygdx.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class UpgradeDetection {
	private TiledMap map;
	
	public UpgradeDetection(TiledMap map) {
        this.map = map;
    }
	
	public RectangleMapObject detect(int posx, int posy) {	
		for (RectangleMapObject r : map.getLayers().get("Upgrades").getObjects().getByType(RectangleMapObject.class)) {

				Rectangle rect = r.getRectangle();
				Rectangle recto = new Rectangle(posx*16,
						posy*16,
						16,
						16);
				if (Intersector.overlaps(rect, recto))
				{
					map.getLayers().get("Upgrades").getObjects().remove(r);
					return r;
				}
		}
		return null;
	}
}
