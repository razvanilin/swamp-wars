package com.razvanilin.swampwars.game;

import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;

import com.razvanilin.swampwars.entity.Entity;
import com.razvanilin.swampwars.entity.EntityFactory;
import com.razvanilin.swampwars.entity.EntityManager;
import com.razvanilin.swampwars.entity.StrategyEnemy;
import com.razvanilin.swampwars.entity.StrategyKnight;
import com.razvanilin.swampwars.entity.StrategyMac;
import com.razvanilin.swampwars.ui.GameGrid;
import com.razvanilin.swampwars.ui.UIConfiguration;

/**
 * @author Razvan Ilin
 *
 */
public class Game {
	private static final String[] diets = {"enemies", "knights", "macs"};
	private static Game instance;
	private int turn;
	
	private UIConfiguration configuration;
	private GameGrid gameGrid;
	private boolean isGameOver;
	
	private EntityFactory entityFactory;
	private EntityManager entityManager;
	
	private Game() {
		entityFactory = new EntityFactory(new Point2D.Double(0,0));
		configuration = UIConfiguration.Instace();
		gameGrid = GameGrid.Instance();
		entityManager = EntityManager.Instance();
		isGameOver = false;
		turn = 1;
	}
	
	public static Game Instace() {
		if (instance == null) instance = new Game();
		
		return instance;
	}
	
	public void start() {
		entityManager.add(entityFactory.generateMainCharacter(configuration.getCharacterName(), new Point2D.Double(2, 2), "ogre enemies", true));
		draw();
	}
	
	public void update(String diet) {
		if (isGameOver) {
			System.out.println("The game is over.");
			return;
		}
		
		// Move existing entities
		entityManager.moveEntities();
		
		draw();
		
		// set up the strategy for the game turn
		switch(diet) {
		case "enemies":
			StrategyEnemy strategyEnemy = new StrategyEnemy();
			strategyEnemy.execute();
			break;
		case "knights":
			StrategyKnight strategyKnight = new StrategyKnight();
			strategyKnight.execute();
			break;
		case "macs":
			StrategyMac strategyMac = new StrategyMac();
			strategyMac.execute();
			break;
		}
		
		// generate enemies
		try {
			Entity enemy = entityFactory.generateEnemy();
			if (enemy != null)
				entityManager.add(enemy);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Enemy could not be generated.");
		}
		
		turn++;
	}
	
	public void undo() {
		if (turn > 1) {
			entityManager.undoEntityMovements();
			turn--;
			draw();
		}
	}
	
	public void draw() {
		gameGrid.draw(entityManager.getEntities());
	}
	
	public String[] getDiets() {
		return diets;
	}
	
	public int getTurnNumber() {
		return turn;
	}
	
	public void IsGameOver(boolean state) {
		this.isGameOver = state;
	}
}

