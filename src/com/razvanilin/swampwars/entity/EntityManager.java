package com.razvanilin.swampwars.entity;

import java.util.ArrayList;

/**
 * 
 * @author Razvan Ilin
 *
 */
public class EntityManager {
	private static EntityManager instance;
	private ArrayList<Entity> entities;

	private EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public static EntityManager Instance() {
		if (instance == null) instance = new EntityManager();
		
		return instance;
	}
	
	public void add(Entity entity) {
		System.out.println(entity.getClass().getTypeName() + " was added to the game.");
		entities.add(entity);
	}
	
	public void moveEntities() {
		for (Entity entity : entities) {
			try {
				//entity.move();
				Movement movement = new Movement(entity);
				movement.move();
			} catch (NullPointerException e) {
				//e.printStackTrace();
				System.out.println("Error Size: " + entities.size());
			}
		}
	}
}
