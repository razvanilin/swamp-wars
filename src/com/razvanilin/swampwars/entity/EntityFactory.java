package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Razvan Ilin
 *
 */
public class EntityFactory {
	
	private Point2D initialEnemyPos;
	private ArrayList<Class<?>> enemies;
	private int difficulty;
	private MainCharacter mainCharacter;
	
	public EntityFactory(Point2D initialPos) {
		initialEnemyPos = new Point2D.Double(initialPos.getX(), initialPos.getY());
		
		// register the enemy types
		enemies = new ArrayList<Class<?>>();
		enemies.add(Donkey.class);
		enemies.add(Parrot.class);
		enemies.add(Snake.class);
		
		difficulty = 3;
	}
	
	public Enemy generateEnemy() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Random generateRand = new Random();
		
		// the lower the difficulty number, the higher the chances that an enemy is created
		if ((generateRand.nextInt(difficulty)+1) == 1) {
			// generate a random type of enemy
			Random enemyRand = new Random();
			Class<?> enemyClass = enemies.get(enemyRand.nextInt(enemies.size()));
			Constructor<?> enemyConstructor = enemyClass.getConstructors()[0];
			Object enemyObject = enemyConstructor.newInstance(new Point2D.Double(0,0), true, mainCharacter);
			
			// register the enemy with the main character
			mainCharacter.registerObserver((Enemy) enemyObject);
			
			return (Enemy)enemyObject;
		}
		
		return null;
	}
	
	public MainCharacter generateMainCharacter(String name, Point2D position, String diet, boolean isAlive) {
		this.mainCharacter = new Ogre(name, position, diet, isAlive);
		return mainCharacter;
	}
}
