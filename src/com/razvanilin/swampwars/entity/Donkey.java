package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Donkey extends Enemy {
	
	private ArrayList<Point2D> positions;
	private boolean isAlive;
	private Movement movement;
	
	/* CONSTRUCTORS */
	public Donkey(Point2D position, boolean isAlive) {
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
		this.movement = new Movement(this);
		System.out.println("Donkey was created.");
	}
	
	/* END OF CONSTRUCTORS */

	@Override
	public void move() {
		movement.move();
	}

	@Override
	public ArrayList<Point2D> getPath() {
		return this.positions;
	}

	@Override
	public Point2D getCurrentPosition() {
		return this.positions.get(positions.size()-1);
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

}
