package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;

/**
 * @author Razvan Ilin
 *
 */
public class UndoMovement implements Action {

	private Entity entity;
	
	public UndoMovement(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void move() {
		System.out.print(entity.getClass().getSimpleName() + " is undoing from (" + (int)entity.getCurrentPosition().getX() + "," + (int)entity.getCurrentPosition().getY() + ") to ");
		// get the coordinates of the second last position
		int x = (int) entity.getPath().get(entity.getPath().size()-2).getX();
		int y = (int) entity.getPath().get(entity.getPath().size()-2).getY();
		
		entity.setPosition(new Point2D.Double(x, y));
		
		System.out.println("(" + (int)entity.getCurrentPosition().getX() + "," + (int)entity.getCurrentPosition().getY() + ")");
	}

}
