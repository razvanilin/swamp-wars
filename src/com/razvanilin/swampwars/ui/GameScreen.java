package com.razvanilin.swampwars.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameScreen extends JFrame {

	private static final int CELL_SIZE = 50;
	
	private JPanel contentPane;
	private int gridWidth;
	private int gridHeight;

	/**
	 * Create the frame.
	 */
	public GameScreen(int gridWidth, int gridHeight) {
		
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTurn = new JLabel("Turn:");
		lblTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTurn.setBounds(17, 91, 58, 16);
		contentPane.add(lblTurn);
		
		JLabel label = new JLabel("5");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(77, 91, 61, 16);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Next Turn");
		btnNewButton.setBounds(6, 20, 96, 59);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(17, 119, 68, 16);
		contentPane.add(separator);
		
		JLabel lblEnemiesDefeated = new JLabel("Enemies Defeated");
		lblEnemiesDefeated.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblEnemiesDefeated.setBounds(14, 147, 88, 16);
		contentPane.add(lblEnemiesDefeated);
		
		JLabel label_1 = new JLabel("25");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(24, 175, 61, 16);
		contentPane.add(label_1);
		
		JButton btnUndoMove = new JButton("Undo Move");
		btnUndoMove.setBounds(6, 482, 117, 29);
		contentPane.add(btnUndoMove);
		
		GameGrid panel = GameGrid.Instance();
		panel.generate(gridWidth, gridHeight);
		
		contentPane.add(panel);
	}
}
