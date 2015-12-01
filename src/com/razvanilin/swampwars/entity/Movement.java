package com.razvanilin.swampwars.entity;

import java.awt.geom.Point2D;
import java.util.Random;

import com.razvanilin.swampwars.ui.UIConfiguration;

/**
 * 
 * @author Razvan Ilin
 *
 */
public class Movement implements Action {
	
	private Entity entity;
	private UIConfiguration configuration;
	
	public Movement(Entity entity) {
		this.entity = entity;
		this.configuration = UIConfiguration.Instace();
	}
	
	@Override
	public void move() {
		int x = (int) entity.getCurrentPosition().getX();
		int y = (int) entity.getCurrentPosition().getY();
		
		System.out.print(entity.getClass().getSimpleName() + " is moving from (" + x + "," + y + ") to ");
		
		boolean isMovable = false;
		
		while(!isMovable) {
			// get a random number to help with choosing the way the entity goes
			Random movementRand = new Random();
			int movementType = movementRand.nextInt(8);
			
			// based on the number, we have 8 posibilities where we can move the entity
			switch(movementType) {
			// left upper corner
			case 0: 
				// this move is only possible when both coordinates are bigger than 0
				if (x > 0 && y > 0) {
					x -= 1;
					y -= 1;
					isMovable = true;
				}
				break;
			// up	
			case 1:
				// the move is only possible if the y coordinate is bigger than 0
				if (y > 0) {
					y -= 1;
					isMovable = true;
				}
				break;
			// upper right corner
			case 2:
				if(x < configuration.getGridWidth() - 1 && y > 0) {
					x += 1;
					y -= 1;
					isMovable = true;
				}
				break;
			// right
			case 3:
				if (x < configuration.getGridWidth() - 1) {
					x += 1;
					isMovable = true;
				}
				break;
			// bottom right corner
			case 4:
				if (x < configuration.getGridWidth() - 1 && y < configuration.getGridHeight() - 1) {
					x += 1;
					y += 1;
					isMovable = true;
				}
				break;
			// bottom
			case 5:
				if (y < configuration.getGridHeight() - 1) {
					y += 1;
					isMovable = true;
				}
				break;
			// bottom left corner
			case 6:
				if (x>0 && y<configuration.getGridHeight() - 1) {
					x -= 1;
					y += 1;
					isMovable = true;
				}
				break;
			// left
			case 7:
				if (x>0) {
					x -= 1;
					isMovable = true;
				}
				break;
			}
		}
		
		entity.setPosition(new Point2D.Double(x, y));
		
		System.out.println("(" + (int)entity.getCurrentPosition().getX() + "," + (int)entity.getCurrentPosition().getY() + ")");
	}
}
