package com.razvanilin.swampwars.ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGrid extends JPanel {

	private static final int CELL_SIZE = 50;
	private int gridWidth;
	private int gridHeight;
	private static GameGrid instance;
	
	/**
	 * Create the panel.
	 */
	private GameGrid() {
		
	}
	
	public static GameGrid Instance() {
		if (instance == null) instance = new GameGrid();
		return instance;
	}

	public void generate(int width, int height) {
		this.gridWidth = width;
		this.gridHeight = height;
		
		// create the panel that contains the grid
		//JPanel panel = new JPanel();
		this.setBounds(154, 20, CELL_SIZE*(gridWidth+1), CELL_SIZE*(gridHeight+1));
		this.setBorder(BorderFactory.createEtchedBorder(Color.black,Color.black));
		
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				JLabel cell = new JLabel();
				cell.setIcon(new ImageIcon(new ImageIcon("assets/grass.png").getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_DEFAULT)));
				cell.setBounds(CELL_SIZE*j, CELL_SIZE*i, CELL_SIZE, CELL_SIZE);
				this.add(cell);
			}
		}
	}
}
