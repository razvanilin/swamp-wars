package com.razvanilin.swampwars.ui;

/**
 * @author Razvan Ilin
 * @description Class used to hold the game configuration set up from the interface
 */
public class UIConfiguration {
	private static UIConfiguration instance;
	private String characterName;
	private int gridWidth;
	private int gridHeight;
	
	
	private UIConfiguration() {
		
	}
	
	public static UIConfiguration Instace() {
		if (instance == null) instance = new UIConfiguration();
		
		return instance;
	}
	
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getCharacterName() {
		return characterName;
	}
	
	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}
	public int getGridWidth() {
		return gridWidth;
	}
	
	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}
	public int getGridHeight() {
		return this.gridHeight;
	}
}
