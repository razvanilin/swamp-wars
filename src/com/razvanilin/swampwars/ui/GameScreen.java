package com.razvanilin.swampwars.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.razvanilin.swampwars.entity.EntityObserver;
import com.razvanilin.swampwars.game.Game;

public class GameScreen extends JFrame {

	private static final int CELL_SIZE = 50;
	private static GameScreen instance;
	
	private JPanel contentPane;
	private JPanel conflictsPanel;
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
		game.start(this);
		
		drawScene();
	}
	
	/*public static GameScreen Instance(int gridWidth, int gridHeight) {
		if (instance == null) instance = new GameScreen(gridWidth, gridHeight);
		
		return instance;
	}*/
	
	private void drawScene() {
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
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
		
		JLabel labelScore = new JLabel("25");
		labelScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelScore.setBounds(27, 308, 61, 16);
		contentPane.add(labelScore);
		
		JButton btnUndoMove = new JButton("Undo Move");
		btnUndoMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.undo();
				labelTurnNumber.setText(String.valueOf(game.getTurnNumber()));
				labelScore.setText(String.valueOf(game.getScore()));
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
			private GameScreen gameScreen;
			
			public <T extends Container> T findParent(Component comp, Class<T> clazz)  {
		      if (comp == null)
		         return null;
		      if (clazz.isInstance(comp))
		         return (clazz.cast(comp));
		      else
		         return findParent(comp.getParent(), clazz);     
			}
			
			public void actionPerformed(ActionEvent e) {
				gameScreen = findParent((Component)e.getSource(), GameScreen.class);
				
				if (game.checkGameOver()) {
					JOptionPane.showMessageDialog(gameScreen, "Game Over!");
					return;
				}
				game.update((String)dietBox.getSelectedItem());
				labelTurnNumber.setText(String.valueOf(game.getTurnNumber()));
				labelScore.setText(String.valueOf(game.getScore()));
			}
		});
		nextTurnButton.setBounds(6, 20, 130, 59);
		contentPane.add(nextTurnButton);
		
		// create a panel for the conflicts details
		conflictsPanel = new JPanel();
		conflictsPanel.setBounds(154+CELL_SIZE*(gridWidth+1), 20, 200, CELL_SIZE*(gridHeight+1));
		conflictsPanel.setBorder(BorderFactory.createEtchedBorder(Color.black,Color.black));
		contentPane.add(conflictsPanel);		
	}
	
	public void drawConflicts(ArrayList<EntityObserver> conflicts) {
	
		System.out.println("Conflict Label");
		for(int i=0; i<conflicts.size(); i++) {
			String conflictString = conflicts.get(i).getClass().getSimpleName() + " is in conflict!";
			JLabel label = new JLabel(conflictString);
			label.setBounds(conflictsPanel.getX(), conflictsPanel.getY()*i, conflictsPanel.getWidth(), 20);
			label.setForeground(Color.red);
			conflictsPanel.add(label);
		}
		conflictsPanel.repaint();
	}
	
	public void clearConflicts() {
		if (conflictsPanel != null) {
			conflictsPanel.removeAll();
			conflictsPanel.repaint();
		}
	}
}
