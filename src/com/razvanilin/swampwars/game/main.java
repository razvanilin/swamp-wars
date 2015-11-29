package com.razvanilin.swampwars.game;

import java.awt.EventQueue;

import com.razvanilin.swampwars.ui.StartScreen;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen frame = new StartScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
