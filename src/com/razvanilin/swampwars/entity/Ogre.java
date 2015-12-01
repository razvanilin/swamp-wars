package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public class Ogre extends MainCharacter {

	private ArrayList<Point2D> positions;
	private boolean isAlive;
		
	private Movement movement;

	/* CONSTRUCTORS */
	public Ogre(String name, Point2D position, String diet, boolean isAlive) {
		this.name = name;
		this.diet = diet;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
		
		this.observersList = new ArrayList<EntityObserver>();
		
		this.movement = new Movement(this);
		System.out.println("Ogre was created.");
	}

	/* END OF CONSTRUCTORS */

	@Override
	public ArrayList<Point2D> getPath() {
		return positions;
	}

	@Override
	public Point2D getCurrentPosition() {
		
		return positions.get(positions.size()-1);
	}
	
	@Override
	public void setPosition(Point2D newPosition) {
		positions.add(newPosition);
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}
	
	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String getDiet() {
		return diet;
	}

	@Override
	public void setDiet(String diet) {
		this.diet = diet;
	}
	
	@Override
	public void registerObserver(EntityObserver observer) {
		observersList.add(observer);
	}
	
	@Override
	public void notifyAllObservers() {
		for (EntityObserver enemy : observersList) {
			enemy.updateState();
		}
	}

}
