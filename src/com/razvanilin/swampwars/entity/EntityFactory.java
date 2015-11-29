package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;

/**
 * @author Razvan Ilin
 *
 */
public class EntityFactory {
	
	private Point2D initialEnemyPos;
	
	public EntityFactory(Point2D initialPos) {
		initialEnemyPos.setLocation(initialPos.getX(), initialPos.getY());
	}
	
	public Enemy generateEnemy(String enemyType) {
		
		if (enemyType.equalsIgnoreCase("DONKEY")) {
			return new Donkey(initialEnemyPos, true);
		} else if (enemyType.equalsIgnoreCase("SNAKE")) {
			return new Snake(initialEnemyPos, true);
		} else if (enemyType.equalsIgnoreCase("PARROT")) {
			return new Parrot(initialEnemyPos, true);
		}
		
		return null;
	}
	
	public Entity generateMainCharacter(String name, Point2D position, Class diet, boolean isAlive) {
		return new Ogre(name, position, diet, isAlive);
	}
}
