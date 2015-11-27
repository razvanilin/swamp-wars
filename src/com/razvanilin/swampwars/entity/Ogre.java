package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public class Ogre implements Entity {
	
	private String name;
	private ArrayList<Point2D> positions;
	private Class diet;
	private boolean isAlive;
	
	
	/* CONSTRUCTORS */
	public Ogre(String name, Point2D position, Class diet, boolean isAlive) {
		this.name = name;
		this.diet = diet;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
	}
	// no alive specified
	public Ogre(String name, Point2D position, Class diet) {
		this.name = name;
		this.diet = diet;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
	}
	public Ogre(String name, Point2D position) {
		this.name = name;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
	}
	public Ogre(String name, Class diet) {
		this.name = name;
		this.diet = diet;
	}
	public Ogre(String name) {
		this.name = name;
	}
	//----------------------------------
	
	// no diet specified
	public Ogre(String name, Point2D position, boolean isAlive) {
		this.name = name;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
	}
	public Ogre(String name, boolean isAlive) {
		this.name = name;
		this.isAlive = isAlive;
	}
	//-----------------------------------
	
	// simple constructor
	public Ogre() {
		this.positions = new ArrayList<Point2D>();
	}
	
	/* END OF CONSTRUCTORS */

	@Override
	public void move(Point2D newPosition) {
		// make sure that the array is initialised before adding anything to it
		if (positions == null) positions = new ArrayList<Point2D>();
		// add the new position at the beginning  of the array
		positions.add(newPosition);
	}

	@Override
	public ArrayList<Point2D> getPath() {
		return positions;
	}

	@Override
	public Point2D getCurrentPosition() {
		
		return positions.get(0);
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Class getDiet() {
		return diet;
	}
	
	public void setDiet(Class diet) {
		this.diet = diet;
	}

}
