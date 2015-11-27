package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Razvan Ilin
 *
 */
public interface Entity {
	
	public void move();
	public ArrayList<Point2D> getPath();
	public Point2D getCurrentPosition();
	public boolean isAlive();
}
