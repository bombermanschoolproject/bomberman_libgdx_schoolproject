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
	
	public int detect(int posx, int posy) {
		int hit = 0;
		
		for (RectangleMapObject r : map.getLayers().get("Collidable").getObjects().getByType(RectangleMapObject.class)) {

				Rectangle rect = r.getRectangle();
				Rectangle recto = new Rectangle(posx*16,
						posy*16,
						16,
						16);
				if (Intersector.overlaps(rect, recto))
				{
					hit = 1;
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
							hit = 2;
						}
						else {
							for (RectangleMapObject c : map.getLayers().get("Bombs").getObjects().getByType(RectangleMapObject.class)) {

								Rectangle bomb = c.getRectangle();
								Rectangle bombb = new Rectangle(posx*16,
										posy*16,
										16,
										16);
								
								if (Intersector.overlaps(bomb, bombb))
								{
									hit = 3;
								}
						}
					}
		
				}
				}
		
		
		}
		return hit;
	}
}
