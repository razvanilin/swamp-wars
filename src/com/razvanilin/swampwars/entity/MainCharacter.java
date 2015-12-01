package com.razvanilin.swampwars.entity;

import java.util.ArrayList;

/**
 * 
 * @author Razvan Ilin
 *
 */
public abstract class MainCharacter implements Entity{
	protected ArrayList<EntityObserver> observersList;
	protected String name;
	protected String diet;
	
	public abstract String getDiet();
	public abstract void setDiet(String diet);
	public abstract void notifyAllObservers();
	public abstract void registerObserver(EntityObserver observer);
	public abstract int getScore();
	public abstract void addScore();
	public abstract void removeScore();
}
