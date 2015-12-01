package com.razvanilin.swampwars.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.razvanilin.swampwars.game.Game;

public class StartScreen extends JFrame {
	
	private static final int MAX_WIDTH = 10;
	private static final int MAX_HEIGHT = 10;

	private JPanel contentPane;
	private JTextField textName;
	private JTextField txtGridWidth;
	private JTextField txtGridHeight;
	
	private UIConfiguration configuration;
	private Game game;

	/**
	 * Create the frame.
	 */
	public StartScreen() {
		
		configuration = UIConfiguration.Instace();
		game = Game.Instace();
		
		drawScreen();
	}
	
	private void drawScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSwampwars = new JLabel("SwampWars");
		lblSwampwars.setBounds(159, 6, 115, 25);
		lblSwampwars.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwampwars.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblSwampwars);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(51, 85, 40, 16);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBounds(144, 80, 144, 26);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblGridSize = new JLabel("Grid Size:");
		lblGridSize.setBounds(30, 139, 61, 16);
		contentPane.add(lblGridSize);
		
		txtGridWidth = new JTextField();
		txtGridWidth.setBounds(144, 134, 65, 26);
		contentPane.add(txtGridWidth);
		txtGridWidth.setColumns(10);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(210, 139, 9, 16);
		contentPane.add(lblX);
		
		txtGridHeight = new JTextField();
		txtGridHeight.setBounds(221, 134, 67, 26);
		contentPane.add(txtGridHeight);
		txtGridHeight.setColumns(10);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			
			private int gridWidth = 0;
			private int gridHeight = 0;
			private StartScreen startScreen;
			
			public <T extends Container> T findParent(Component comp, Class<T> clazz)  {
		      if (comp == null)
		         return null;
		      if (clazz.isInstance(comp))
		         return (clazz.cast(comp));
		      else
		         return findParent(comp.getParent(), clazz);     
			}
			
			public void actionPerformed(ActionEvent e) {
				startScreen = findParent((Component)e.getSource(), StartScreen.class);
				
				if (txtGridHeight.getText().matches("^-?\\d+$") && txtGridWidth.getText().matches("^-?\\d+$")) {
					gridWidth = Integer.parseInt(txtGridWidth.getText());
					gridHeight = Integer.parseInt(txtGridHeight.getText());
					if (gridWidth > MAX_WIDTH || gridHeight > MAX_HEIGHT) {
						JOptionPane.showMessageDialog(startScreen, "The grid dimensions should not be more than: " + MAX_WIDTH + "x" + MAX_HEIGHT);
						gridWidth = 0;
						gridHeight = 0;
						return;
					}
				} else {
					return;
				}
				
				// save the entered information in the configuration object
				if (textName.getText().length() > 0)
					configuration.setCharacterName(textName.getText());
				else 
					return;
				
				configuration.setGridWidth(gridWidth);
				configuration.setGridHeight(gridHeight);
				
				// make the start screen invisible
				setVisible(false);
				System.out.println("Start button clicked.");
				
				// start a new thread with the game screen
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						try {
							GameScreen frame = new GameScreen(gridWidth, gridHeight);
							frame.addWindowListener(new WindowAdapter() {
								
								@Override
								public void windowClosing(WindowEvent e) {
									startScreen.setVisible(true);
									game.end();
								}
							});
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnStart.setBackground(new Color(0, 204, 153));
		btnStart.setBounds(144, 201, 144, 29);
		contentPane.add(btnStart);
	}
}
