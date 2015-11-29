package com.razvanilin.swampwars.game;

import com.razvanilin.swampwars.entity.EntityFactory;

/**
 * @author Razvan Ilin
 *
 */
public class Game {
	private static Game instance;
	
	private EntityFactory entityFactory;
	
	private Game() {
		entityFactory = new EntityFactory(null);
	}
	
	public static Game Instace() {
		if (instance == null) instance = new Game();
		
		return instance;
	}
	
	public void start() {
		
	}
	
	public void update() {
		
	}
	
	public void draw() {
		
	}
}

