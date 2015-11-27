package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public class Snake extends Enemy {
	
	private ArrayList<Point2D> positions;
	private boolean isAlive;
	
	/* CONSTRUCTORS*/
	public Snake(Point2D position, boolean isAlive) {
		this.positions = new ArrayList<Point2D>();
		this.positions.add(position);
		this.isAlive = isAlive;
	}
	public Snake(){};

	/* END OF CONSTRUCTORS */
	
	@Override
	public void move(Point2D newPosition) {
		if (this.positions == null) this.positions = new ArrayList<Point2D>();
		
		this.positions.add(newPosition);
	}

	@Override
	public ArrayList<Point2D> getPath() {
		return this.positions;
	}

	@Override
	public Point2D getCurrentPosition() {
		return this.positions.get(0);
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}

	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
