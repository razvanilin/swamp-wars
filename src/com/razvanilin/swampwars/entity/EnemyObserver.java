package com.razvanilin.swampwars.entity;

/**
 * 
 * @author Razvan Ilin
 *
 */
public abstract class EnemyObserver {
	protected MainCharacter subject;
	public abstract void updateState();
	public abstract boolean getState();
}