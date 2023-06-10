package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RSAView extends JFrame {

	private JPanel contentPane;
	private JTextField numberOfCharGenTextField;
	private JTextField privateKeyTextField;
	private JTextField publicKeyTextField;
	private JTextField ePublicTextField;
	private JTextField dKeyTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSAView frame = new RSAView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RSAView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{50, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		Border rsaBorder = new TitledBorder(new LineBorder(Color.gray),"RSA", TitledBorder.CENTER, TitledBorder.CENTER);
		contentPane.setBorder(rsaBorder);
		
		
		JPanel keyGenPanel = new JPanel();
		GridBagConstraints gbc_keyGenPanel = new GridBagConstraints();
		gbc_keyGenPanel.insets = new Insets(0, 0, 5, 0);
		gbc_keyGenPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyGenPanel.gridx = 0;
		gbc_keyGenPanel.gridy = 0;
		contentPane.add(keyGenPanel, gbc_keyGenPanel);
		keyGenPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Border keyBorder = new TitledBorder(new LineBorder(Color.gray),"Key generater", TitledBorder.LEFT, TitledBorder.CENTER);
		keyGenPanel.setBorder(keyBorder);
		
		JPanel keyPanel = new JPanel();
		keyGenPanel.add(keyPanel);
		
		JLabel privateKeyGenLabel = new JLabel("Private Key");
		keyPanel.add(privateKeyGenLabel);
		
		privateKeyTextField = new JTextField();
		privateKeyTextField.setColumns(10);
		keyPanel.add(privateKeyTextField);
		
		JLabel publicKeyGenLabel = new JLabel("Public Key");
		keyPanel.add(publicKeyGenLabel);
		
		publicKeyTextField = new JTextField();
		publicKeyTextField.setColumns(10);
		keyPanel.add(publicKeyTextField);
		
		JPanel numberOfCharPanel = new JPanel();
		keyGenPanel.add(numberOfCharPanel);
		
		JLabel numberOfCharLabel = new JLabel("How many character(s) you want to encrypt?");
		numberOfCharPanel.add(numberOfCharLabel);
		
		numberOfCharGenTextField = new JTextField();
		numberOfCharPanel.add(numberOfCharGenTextField);
		numberOfCharGenTextField.setColumns(10);
		
		JPanel GenPanel = new JPanel();
		keyGenPanel.add(GenPanel);
		
		JButton keyGenButt = new JButton("Generate");
		keyGenButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		keyGenButt.setBackground(Color.WHITE);
		keyGenButt.setFocusPainted(false);
		keyGenButt.setVerticalAlignment(SwingConstants.BOTTOM);
		GenPanel.add(keyGenButt);
		
		JButton setEKeyButt = new JButton("Set Encrypt key");
		setEKeyButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setEKeyButt.setForeground(Color.BLACK);
		setEKeyButt.setFocusPainted(false);
		setEKeyButt.setBackground(Color.WHITE);
		GenPanel.add(setEKeyButt);
		
		JButton setDKeyButt = new JButton("Set Decrypt key");
		setDKeyButt.setForeground(Color.BLACK);
		setDKeyButt.setFocusPainted(false);
		setDKeyButt.setBackground(Color.WHITE);
		GenPanel.add(setDKeyButt);
	
		JPanel cipherPanel = new JPanel();
		GridBagConstraints gbc_cipherPanel = new GridBagConstraints();
		gbc_cipherPanel.fill = GridBagConstraints.BOTH;
		gbc_cipherPanel.gridx = 0;
		gbc_cipherPanel.gridy = 1;
		contentPane.add(cipherPanel, gbc_cipherPanel);
		cipherPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel encrypt = new JPanel();
		cipherPanel.add(encrypt);
		encrypt.setLayout(new BorderLayout(0, 0));
		
		Border encryptBorder = new TitledBorder(new LineBorder(Color.gray),"Encrypt", TitledBorder.LEFT, TitledBorder.CENTER);
		encrypt.setBorder(encryptBorder);
		
		JPanel eKeyPanel = new JPanel();
		encrypt.add(eKeyPanel, BorderLayout.NORTH);
		
		JLabel ePublicKeyLabel = new JLabel("Public Key");
		eKeyPanel.add(ePublicKeyLabel);
		
		ePublicTextField = new JTextField();
		eKeyPanel.add(ePublicTextField);
		ePublicTextField.setColumns(10);
		
		JPanel eTextPanel = new JPanel();
		eTextPanel.setForeground(Color.BLACK);
		eTextPanel.setBackground(SystemColor.menu);
		encrypt.add(eTextPanel, BorderLayout.CENTER);
		eTextPanel.setLayout(new GridLayout(2, 2, 5, 5));
		
		JLabel ePlainLabel = new JLabel("Plain text:");
		ePlainLabel.setVerticalAlignment(SwingConstants.TOP);
		ePlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ePlainLabel.setForeground(Color.BLACK);
		eTextPanel.add(ePlainLabel);
		
		JTextPane ePlainTextPane = new JTextPane();
		eTextPanel.add(ePlainTextPane);
		
		JLabel eCipherLabel = new JLabel("Cipher text:");
		eCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eCipherLabel.setForeground(Color.BLACK);
		eTextPanel.add(eCipherLabel);
		
		JTextPane eCipherTextPane = new JTextPane();
		eTextPanel.add(eCipherTextPane);
		
		JPanel encryptButtPanel = new JPanel();
		encryptButtPanel.setForeground(Color.BLACK);
		encryptButtPanel.setBackground(SystemColor.menu);
		encrypt.add(encryptButtPanel, BorderLayout.SOUTH);
		
		JButton encryptButt = new JButton("Encrypt");
		encryptButt.setForeground(Color.BLACK);
		encryptButt.setFocusPainted(false);
		encryptButt.setBackground(Color.WHITE);
		encryptButtPanel.add(encryptButt);
		
		JButton eSetButt = new JButton("Set Cipher text");
		eSetButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		eSetButt.setForeground(Color.BLACK);
		eSetButt.setFocusPainted(false);
		eSetButt.setBackground(Color.WHITE);
		encryptButtPanel.add(eSetButt);
		
		JPanel decrypt = new JPanel();
		cipherPanel.add(decrypt);
		decrypt.setLayout(new BorderLayout(0, 0));
		
		Border decryptBorder = new TitledBorder(new LineBorder(Color.gray),"Decrypt", TitledBorder.LEFT, TitledBorder.CENTER);
		decrypt.setBorder(decryptBorder);
		
		JPanel dKeyPanel = new JPanel();
		decrypt.add(dKeyPanel, BorderLayout.NORTH);
		
		JLabel dKeyLabel = new JLabel("Private Key");
		dKeyPanel.add(dKeyLabel);
		
		dKeyTextField = new JTextField();
		dKeyTextField.setColumns(10);
		dKeyPanel.add(dKeyTextField);
		
		JPanel dTextPanel = new JPanel();
		dTextPanel.setForeground(Color.BLACK);
		dTextPanel.setBackground(SystemColor.menu);
		decrypt.add(dTextPanel, BorderLayout.CENTER);
		dTextPanel.setLayout(new GridLayout(2, 2, 5, 5));
		
		JLabel dCipherLabel = new JLabel("Cipher text:");
		dCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		dCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dCipherLabel.setForeground(Color.BLACK);
		dTextPanel.add(dCipherLabel);
		
		JTextPane dCipherPane = new JTextPane();
		dTextPanel.add(dCipherPane);
		
		JLabel dPlainLabel = new JLabel("Plain text:");
		dPlainLabel.setVerticalAlignment(SwingConstants.TOP);
		dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dPlainLabel.setForeground(Color.BLACK);
		dTextPanel.add(dPlainLabel);
		
		JTextPane dPlainPane = new JTextPane();
		dTextPanel.add(dPlainPane);
		
		JPanel decryptButtPanel = new JPanel();
		decryptButtPanel.setForeground(Color.BLACK);
		decryptButtPanel.setBackground(SystemColor.menu);
		decrypt.add(decryptButtPanel, BorderLayout.SOUTH);
		
		JButton decryptButt = new JButton("Decrypt");
		decryptButt.setForeground(Color.BLACK);
		decryptButt.setFocusPainted(false);
		decryptButt.setBackground(Color.WHITE);
		decryptButtPanel.add(decryptButt);
		
		JButton dSetButt = new JButton("Set Plain text");
		dSetButt.setForeground(Color.BLACK);
		dSetButt.setFocusPainted(false);
		dSetButt.setBackground(Color.WHITE);
		decryptButtPanel.add(dSetButt);
	}

}
