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
			Movement movement = new Movement(entity);
			movement.move();
			// check to see if there are any conflicts after the movement
			if (EnemyObserver.class.isAssignableFrom(entity.getClass())) {
				EnemyObserver observer = (EnemyObserver)entity;
				if (observer.getState()) {
					System.out.println(observer.getClass().getSimpleName() + " is in conflict");
				}
			}
		}
		
	}
	
	public void undoEntityMovements() {
		ArrayList<Entity> entityRemoveList = new ArrayList<Entity>();
		
		for (Entity entity : entities) {
			// if the entity didn't move (has just one position in the total path), add it to the remove list to be removed from the game
			if (entity.getPath().size() < 2) {
				entityRemoveList.add(entity);
			} else {
				UndoMovement undo = new UndoMovement(entity);
				undo.move();
			}
		}
		
		// remove all objects from the remove list (that had just been placed on the grid)
		for (Entity entity : entityRemoveList) {
			entities.remove(entity);
		}
	}
}
