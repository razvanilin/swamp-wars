package com.razvanilin.swampwars.game;

import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;

import com.razvanilin.swampwars.entity.EntityFactory;
import com.razvanilin.swampwars.ui.UIConfiguration;
import com.razvanilin.swampwars.entity.*;

/**
 * @author Razvan Ilin
 *
 */
public class Game {
	private static Game instance;
	private UIConfiguration configuration;
	private boolean isGameOver;
	
	private EntityFactory entityFactory;
	
	private Game() {
		entityFactory = new EntityFactory(new Point2D.Double(0,0));
		configuration = UIConfiguration.Instace();
		isGameOver = false;
	}
	
	public static Game Instace() {
		if (instance == null) instance = new Game();
		
		return instance;
	}
	
	public void start() {
		entityFactory.generateMainCharacter(configuration.getCharacterName(), new Point2D.Double(2, 2), "ogre enemies", true);
	}
	
	public void update() {
		// when the Next Turn button is pressed, call this method
		try {
			entityFactory.generateEnemy();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Enemy could not be generated.");
		}
	}
	
	public void draw() {
		// maybe not needed
	}
}

