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
import com.razvanilin.swampwars.ui.GameScreen;
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
	private GameScreen gameScreen;
	private boolean isGameOver;
	private int gameScore;
	
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
	
	public void start(GameScreen gameScreen) {
		entityManager.add(entityFactory.generateMainCharacter(configuration.getCharacterName(), new Point2D.Double(2, 2), "ogre enemies", true));
		this.gameScreen = gameScreen;
		draw();
	}
	
	public void update(String diet) {
		if (isGameOver) {
			System.out.println("The game is over.");
			return;
		}
		
		
		// Move existing entities
		entityManager.moveEntities();
		
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
		
		draw();
		
		// set up the strategy for the game turn
		switch(diet) {
		case "enemies":
			StrategyEnemy strategyEnemy = new StrategyEnemy();
			strategyEnemy.execute(turn);
			break;
		case "knights":
			StrategyKnight strategyKnight = new StrategyKnight();
			strategyKnight.execute(turn);
			break;
		case "macs":
			StrategyMac strategyMac = new StrategyMac();
			strategyMac.execute(turn);
			break;
		}
		
		gameScore = entityManager.getScore();
		
		turn++;
		System.out.println();
	}
	
	public void undo() {
		if (turn > 1) {
			turn--;			
			entityManager.undoEntityMovements(turn);
			gameScore = entityManager.getScore();
			draw();
		}
		System.out.println();
	}
	
	public void draw() {
		gameGrid.draw(entityManager.getEntities());
		
		if (gameScreen != null) gameScreen.clearConflicts();
		if (entityManager.getConflicts().size() > 0) {
			gameScreen.drawConflicts(entityManager.getConflicts());
		}
	}
	
	public String[] getDiets() {
		return diets;
	}
	
	public int getTurnNumber() {
		return turn;
	}
	
	public int getScore() {
		return gameScore;
	}
	
	public void IsGameOver(boolean state) {
		this.isGameOver = state;
	}
	
	public boolean checkGameOver() {
		return isGameOver;
	}
	
	public void end() {
		entityManager.clean();
		isGameOver = false;
	}
}

