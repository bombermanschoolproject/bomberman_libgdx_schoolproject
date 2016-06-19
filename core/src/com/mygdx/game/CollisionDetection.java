package com.mygdx.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionDetection {
	private TiledMap map;
	
	public CollisionDetection(TiledMap map) {
        this.map = map;
    }
	
	public boolean detect(int posx, int posy) {
		boolean hit = false;
		
		for (RectangleMapObject r : map.getLayers().get("Collidable").getObjects().getByType(RectangleMapObject.class)) {

				Rectangle rect = r.getRectangle();
				Rectangle recto = new Rectangle(posx*16,
						posy*16,
						16,
						16);
				if (Intersector.overlaps(rect, recto))
				{
					hit = true;
				}
				else {
					for (RectangleMapObject b : map.getLayers().get("Boxes").getObjects().getByType(RectangleMapObject.class)) {

						Rectangle box = b.getRectangle();
						Rectangle boxx = new Rectangle(posx*16,
								posy*16,
								16,
								16);
						
						if (Intersector.overlaps(box, boxx))
						{
							hit = true;
						}
					}
		
				}
		}
		
		return hit;
	}
}
