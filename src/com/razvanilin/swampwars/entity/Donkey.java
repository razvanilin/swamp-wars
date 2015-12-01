package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Donkey extends Enemy {
	
	private ArrayList<Point2D> positions;
	private boolean isAlive;
	private Movement movement;
	private boolean isInConflict;
	
	/* CONSTRUCTORS */
	public Donkey(Point2D position, boolean isAlive, MainCharacter mainCharacter) {
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
		this.subject = mainCharacter;
		
		this.isInConflict = false;
		this.movement = new Movement(this);
		System.out.println("Donkey was created.");
	}
	
	/* END OF CONSTRUCTORS */

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
		// check if the new position will create a conflict with the main character (subject)
		if (newPosition.equals(subject.getCurrentPosition())) {
			updateState();
		} else {
			isInConflict = false;
		}
		
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
	public void updateState() {
		isInConflict = !isInConflict;
	}

	@Override
	public boolean getState() {
		return isInConflict;
	}

	@Override
	public void removeLastPosition() {
		positions.remove(positions.size()-1);
	}

}
