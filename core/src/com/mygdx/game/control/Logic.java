package com.mygdx.game.control;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.game.model.Arena;
import com.mygdx.game.model.Figure;

public class Logic {
	
	private Logic renderer;
	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}
	
	public Arena arena;
	public Figure bomberman;
	static Map<Keys, Boolean> keys = new HashMap<Logic.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};

	public Logic(Arena arena) {
		this.arena = arena;
		this.bomberman = new Figure(0, 0, 0, 0, 0, 0, false);
	}

	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}
	
	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}
	
	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}
	

	public void update(float delta) {
		processInput();
	}
	

	public void resetWay(){
		rightReleased();
		leftReleased();
		downReleased();
		upReleased();
	}
	
	private void processInput() {
//		bomberman.resetDirection();
		
		if (!bomberman.isDead()){
			if (keys.get(Keys.LEFT)) {
				bomberman.setState(Figure.State.WALKING);
				bomberman.setDirection(Figure.Direction.LEFT, true);
//				bomberman.getVelocity().x = -bomberman.getSpeed();
			}
			else if (keys.get(Keys.RIGHT)) {
				bomberman.setDirection(Figure.Direction.RIGHT, true);
				bomberman.setState(Figure.State.WALKING);
//				bomberman.getVelocity().x = bomberman.getSpeed();	
			}
			else if (keys.get(Keys.UP)) {
				bomberman.setDirection(Figure.Direction.UP, true);
				bomberman.setState(Figure.State.WALKING);
//				bomberman.getVelocity().y = bomberman.getSpeed();
			}
			else if (keys.get(Keys.DOWN)) {
				bomberman.setDirection(Figure.Direction.DOWN, true);
				bomberman.setState(Figure.State.WALKING);
//				bomberman.getVelocity().y = -bomberman.getSpeed();
			}
			
			
			//Wenn mehrere Tasten gedrückt sind --> Idle
			if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && (!keys.get(Keys.RIGHT)))) {
//				bomberman.getVelocity().x = 0;			
			}
			if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) || (!keys.get(Keys.UP) && (!keys.get(Keys.DOWN)))) {
//				bomberman.getVelocity().y = 0;			
			}
			if(!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)&& !keys.get(Keys.UP) && !keys.get(Keys.DOWN)){
//				bomberman.getVelocity().x = 0;
//				bomberman.getVelocity().y = 0;
//				bomberman.setState(Bomberman.State.IDLE);
			}
		}
		else {
//			bomberman.getVelocity().x = 0;
//			bomberman.getVelocity().y = 0;
		}
	}
}