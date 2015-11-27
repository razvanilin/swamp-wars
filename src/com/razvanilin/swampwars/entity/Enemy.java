package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public abstract class Enemy implements Entity {

	@Override
	public abstract void move(Point2D newPosition);

	@Override
	public abstract ArrayList<Point2D> getPath();

	@Override
	public abstract Point2D getCurrentPosition();

	@Override
	public abstract boolean isAlive();
	
	@Override
	public abstract void setAlive(boolean isAlive);

}
