package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.AffineView;

public class Main {

//	// Driver code
//	public static void main(String[] args) {
//		AfflineModel afflineModel = new AfflineModel();
//		String msg = "AFFINE CIPHER";
//		msg = msg.toUpperCase();
//		
//		// Get a and b
//		System.out.println("a is: " + afflineModel.getA() + "\nb is: " + afflineModel.getB());
//		
//		// Calling encryption function
//		String cipherText = afflineModel.encryptMessage(msg.toCharArray());
//		System.out.println("Encrypted Message is : " + cipherText);
//
//		// Calling Decryption function
//		System.out.println("Decrypted Message is : " + afflineModel.decryptCipher(cipherText));
//
//	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffineView frame = new AffineView();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
