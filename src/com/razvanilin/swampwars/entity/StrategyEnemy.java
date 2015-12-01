package com.razvanilin.swampwars.entity;

import com.razvanilin.swampwars.game.Game;

/**
 * 
 * @author Razvan Ilin
 *
 */
public class StrategyEnemy implements DietStrategy {
	
	private EntityManager entityManager;
	
	public StrategyEnemy() {
		entityManager = EntityManager.Instance();
	}

	@Override
	public void execute(int turn) {
		// remove the enemies from the game
		for (EntityObserver entity : entityManager.getConflicts()) {
			// do a check to make sure that we have the right entity in the conflicts array
			if (Enemy.class.isAssignableFrom(entity.getClass()))
				entityManager.removeEntity((Entity)entity, turn);
		}
	}

}
