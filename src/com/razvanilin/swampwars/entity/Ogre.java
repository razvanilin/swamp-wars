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
	private String diet;
	private boolean isAlive;
	
	private Movement movement;

	/* CONSTRUCTORS */
	public Ogre(String name, Point2D position, String diet, boolean isAlive) {
		this.name = name;
		this.diet = diet;
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
		this.movement = new Movement(this);
		System.out.println("Ogre was created.");
	}

	// no alive specified
	public Ogre(String name, Point2D position, String diet) {
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

	public Ogre(String name, String diet) {
		this.name = name;
		this.diet = diet;
	}

	public Ogre(String name) {
		this.name = name;
	}
	// ----------------------------------

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
	// -----------------------------------

	// simple constructor
	public Ogre() {
		this.positions = new ArrayList<Point2D>();
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

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

}
