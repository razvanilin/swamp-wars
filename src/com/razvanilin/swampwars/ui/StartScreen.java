package com.razvanilin.swampwars.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField txtGridWidth;
	private JTextField txtGridHeight;
	
	private UIConfiguration configuration;

	/**
	 * Create the frame.
	 */
	public StartScreen() {
		
		configuration = UIConfiguration.Instace();
		
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
			
			public void actionPerformed(ActionEvent e) {	
				
				if (txtGridHeight.getText().matches("^-?\\d+$") && txtGridWidth.getText().matches("^-?\\d+$")) {
					gridWidth = Integer.parseInt(txtGridWidth.getText());
					gridHeight = Integer.parseInt(txtGridHeight.getText());
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
