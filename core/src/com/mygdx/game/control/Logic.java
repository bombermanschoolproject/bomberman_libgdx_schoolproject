package com.mygdx.game.control;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Arena;
import com.mygdx.game.model.Figure;

public class Logic {
	
	private Logic logic;
	public Arena arena;
	public Figure bomberman;

	public Logic(Arena arena) {
		this.arena = arena;
		this.bomberman = new Figure(0, 0, 0, 0, 0, false);
	}

	public void update(float delta) {
		processInput();
	}

	private void processInput() {
		
		if (!bomberman.isDead()){
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && bomberman.getState()!=Figure.State.IDLE) {
//				bomberman.setState(Figure.State.WALKING);
				bomberman.setDirection(Figure.Direction.LEFT);
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && bomberman.getState()!=Figure.State.IDLE) {
				bomberman.setDirection(Figure.Direction.RIGHT);
//				bomberman.setState(Figure.State.WALKING);
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && bomberman.getState()!=Figure.State.IDLE) {
				bomberman.setDirection(Figure.Direction.UP);
//				bomberman.setState(Figure.State.WALKING);
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && bomberman.getState()!=Figure.State.IDLE) {
				bomberman.setDirection(Figure.Direction.DOWN);
//				bomberman.setState(Figure.State.WALKING);
			}

		}
	}
}