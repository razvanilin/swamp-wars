package com.razvanilin.swampwars.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Razvan Ilin
 *
 */
public class EntityManager {
	private static EntityManager instance;
	private ArrayList<Entity> entities;
	private HashMap<Integer, Entity> removedEntities;
	private ArrayList<EntityObserver> conflictingEntities;
	private int turnNumber;

	private EntityManager() {
		entities = new ArrayList<Entity>();
		conflictingEntities = new ArrayList<EntityObserver>();
		removedEntities = new HashMap<Integer, Entity>();
		
	}
	
	public static EntityManager Instance() {
		if (instance == null) instance = new EntityManager();
		
		return instance;
	}
	
	public void add(Entity entity) {
		entities.add(entity);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void moveEntities() {
		// clear the conflicting entities array and prepare it for the next iteration
		conflictingEntities.clear();
		
		for (Entity entity : entities) {
			Movement movement = new Movement(entity);
			movement.move();
			// check to see if there are any conflicts after the movement
			if (EntityObserver.class.isAssignableFrom(entity.getClass())) {
				EntityObserver observer = (EntityObserver)entity;
				if (observer.getState()) {
					System.out.println(observer.getClass().getSimpleName() + " is in conflict");
					conflictingEntities.add(observer);
				}
			}
		}
		
	}
	
	public void undoEntityMovements(int turn) {
		turnNumber = turn;
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
		
		// add entities that were removed back to the game
		if (removedEntities.get(turnNumber) != null) {
			MainCharacter mainCharacter = (MainCharacter)entities.get(0);
			mainCharacter.removeScore();
			entities.add(removedEntities.get(turnNumber));
		}
	}
	
	public void removeEntity(Entity entity, int turn) {
		MainCharacter mainCharacter = (MainCharacter)entities.get(0);
		mainCharacter.addScore();
		
		entities.remove(entity);
		turnNumber = turn+1;
		removedEntities.put(turnNumber, entity);
	}
	
	public ArrayList<EntityObserver> getConflicts() {
		return conflictingEntities;
	}
	
	public void clean() {
		entities.clear();
		removedEntities.clear();
		conflictingEntities.clear();
	}
	
	public int getScore() {
		MainCharacter mainCharacter = (MainCharacter)entities.get(0);
		return mainCharacter.getScore();
	}
}
