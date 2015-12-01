package com.razvanilin.swampwars.entity;

/**
 * 
 * @author Razvan Ilin
 *
 */
public abstract class EntityObserver {
	protected MainCharacter subject;
	public abstract void updateState();
	public abstract boolean getState();
}