package com.razvanilin.swampwars.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.razvanilin.swampwars.game.Game;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GameScreen extends JFrame {

	private static final int CELL_SIZE = 50;
	
	private JPanel contentPane;
	private int gridWidth;
	private int gridHeight;
	private Game game;

	/**
	 * Create the frame.
	 */
	public GameScreen(int gridWidth, int gridHeight) {
		
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		
		// get the instance of the game and start it
		this.game = Game.Instace();
		game.start();
		
		drawScene();
	}
	
	private void drawScene() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTurn = new JLabel("Turn:");
		lblTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTurn.setBounds(17, 213, 58, 16);
		contentPane.add(lblTurn);
		
		JLabel labelTurnNumber = new JLabel("5");
		labelTurnNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelTurnNumber.setBounds(73, 213, 61, 16);
		contentPane.add(labelTurnNumber);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(17, 241, 119, 16);
		contentPane.add(separator);
		
		JLabel lblEnemiesDefeated = new JLabel("Enemies Defeated");
		lblEnemiesDefeated.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblEnemiesDefeated.setBounds(17, 280, 88, 16);
		contentPane.add(lblEnemiesDefeated);
		
		JLabel label_1 = new JLabel("25");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(27, 308, 61, 16);
		contentPane.add(label_1);
		
		JButton btnUndoMove = new JButton("Undo Move");
		btnUndoMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.undo();
				labelTurnNumber.setText(String.valueOf(game.getTurnNumber()));
			}
		});
		btnUndoMove.setBounds(6, 482, 117, 29);
		contentPane.add(btnUndoMove);
		
		GameGrid panel = GameGrid.Instance();
		panel.generate(gridWidth, gridHeight);
		
		contentPane.add(panel);
		
		JComboBox dietBox = new JComboBox(game.getDiets());
		dietBox.setBounds(6, 130, 130, 27);
		contentPane.add(dietBox);
		
		JLabel lblChangeDiet = new JLabel("Change Diet");
		lblChangeDiet.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblChangeDiet.setBounds(17, 113, 109, 16);
		contentPane.add(lblChangeDiet);
		
		JButton nextTurnButton = new JButton("Next Turn");
		// update the game when the next turn button is pressed
		nextTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update((String)dietBox.getSelectedItem());
				labelTurnNumber.setText(String.valueOf(game.getTurnNumber()));
			}
		});
		nextTurnButton.setBounds(6, 20, 130, 59);
		contentPane.add(nextTurnButton);
	}
}
