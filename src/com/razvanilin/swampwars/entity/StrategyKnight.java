package com.razvanilin.swampwars.entity;

import com.razvanilin.swampwars.game.Game;

/**
 * @author Razvan Ilin
 *
 */
public class StrategyKnight implements DietStrategy {

	private EntityManager entityManager;
	private Game game;
	
	public StrategyKnight() {
		game = Game.Instace();
		entityManager = EntityManager.Instance();
	}
	
	@Override
	public void execute() {
		int enemies = 0;
		
		// if there is just one enemy in the conflicts array, then remove that from the game
		if (entityManager.getConflicts().size() == 1) {
			// make sure the entity in the array is an enemy
			if (Enemy.class.isAssignableFrom(entityManager.getConflicts().get(0).getClass())) {
				entityManager.removeEntity((Entity)entityManager.getConflicts().get(0));
			}
		} else if (entityManager.getConflicts().size() > 1) {
			// go through the conflicts and make sure that in the array there are 2 or more enemies
			for (EntityObserver entity : entityManager.getConflicts()) {
				if (Enemy.class.isAssignableFrom(entity.getClass())) {
					enemies++;
				}
			}
			
			if (enemies > 1) {
				game.IsGameOver(true);
			}
		}
	}

}
