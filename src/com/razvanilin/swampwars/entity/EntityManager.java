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
		entities.add(entity);
	}
	
	public void moveEntities() {
		for (Entity entity : entities) {
			//entity.move();
		}
	}
}
