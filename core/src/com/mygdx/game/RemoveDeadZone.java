package com.mygdx.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class RemoveDeadZone {
	
	public void remove(TiledMap map, RectangleMapObject rmo) {
		map.getLayers().get("DeadZones").getObjects().remove(rmo);
	}
}
