package main;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.UIManager;

import model.CeasarModel;
import view.CeasarView;

public class Main {

	public static void main(String[] args) {
		/**
		 * Launch the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CeasarView frame = new CeasarView();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
