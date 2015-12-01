package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public interface Entity {
	
	public ArrayList<Point2D> getPath();
	public Point2D getCurrentPosition();
	public void setPosition(Point2D newPosition);
	public void removeLastPosition();
	public boolean isAlive();
	public void setAlive(boolean isAlive);
}
