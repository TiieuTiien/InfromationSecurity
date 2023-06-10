package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.RSAListener;
import model.RSAModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class RSAView extends JFrame {

	private RSAModel rsaModel;
	private JPanel contentPane;
	private JTextField ePublicTextField;
	private JTextField dKeyTextField;
	private JTextArea ePlainArea;
	private JTextArea eCipherArea;
	private JTextArea dCipherArea;
	private JTextArea dPlainArea;
	private JSpinner numberOfCharSpiner;
	private JTextArea privateKeyArea;
	private JTextArea publicKeyArea;

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
		RSAListener rsaListener = new RSAListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 50, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		Border rsaBorder = new TitledBorder(new LineBorder(Color.gray), "RSA", TitledBorder.CENTER,
				TitledBorder.CENTER);
		contentPane.setBorder(rsaBorder);

		JPanel keyGenPanel = new JPanel();
		GridBagConstraints gbc_keyGenPanel = new GridBagConstraints();
		gbc_keyGenPanel.insets = new Insets(0, 0, 5, 0);
		gbc_keyGenPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyGenPanel.gridx = 0;
		gbc_keyGenPanel.gridy = 0;
		contentPane.add(keyGenPanel, gbc_keyGenPanel);
		keyGenPanel.setLayout(new GridLayout(0, 1, 0, 0));

		Border keyBorder = new TitledBorder(new LineBorder(Color.gray), "Key generater", TitledBorder.LEFT,
				TitledBorder.CENTER);
		keyGenPanel.setBorder(keyBorder);

		JPanel keyPanel = new JPanel();
		keyGenPanel.add(keyPanel);
		GridBagLayout gbl_keyPanel = new GridBagLayout();
		gbl_keyPanel.columnWidths = new int[] { 50, 86, 50, 86, 0 };
		gbl_keyPanel.rowHeights = new int[] { 24, 0 };
		gbl_keyPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_keyPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		keyPanel.setLayout(gbl_keyPanel);

		JLabel privateKeyGenLabel = new JLabel("Private Key");
		privateKeyGenLabel.setVerticalAlignment(SwingConstants.TOP);
		privateKeyGenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_privateKeyGenLabel = new GridBagConstraints();
		gbc_privateKeyGenLabel.insets = new Insets(0, 0, 0, 5);
		gbc_privateKeyGenLabel.gridx = 0;
		gbc_privateKeyGenLabel.gridy = 0;
		keyPanel.add(privateKeyGenLabel, gbc_privateKeyGenLabel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		keyPanel.add(scrollPane, gbc_scrollPane);

		privateKeyArea = new JTextArea();
		scrollPane.setViewportView(privateKeyArea);

		JLabel publicKeyGenLabel = new JLabel("Public Key");
		publicKeyGenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		publicKeyGenLabel.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_publicKeyGenLabel = new GridBagConstraints();
		gbc_publicKeyGenLabel.insets = new Insets(0, 0, 0, 5);
		gbc_publicKeyGenLabel.gridx = 2;
		gbc_publicKeyGenLabel.gridy = 0;
		keyPanel.add(publicKeyGenLabel, gbc_publicKeyGenLabel);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 0;
		keyPanel.add(scrollPane_1, gbc_scrollPane_1);

		publicKeyArea = new JTextArea();
		scrollPane_1.setViewportView(publicKeyArea);

		JPanel numberOfCharPanel = new JPanel();
		keyGenPanel.add(numberOfCharPanel);

		JLabel numberOfCharLabel = new JLabel("How many character(s) you want to encrypt?");
		numberOfCharPanel.add(numberOfCharLabel);

		numberOfCharSpiner = new JSpinner();
		numberOfCharPanel.add(numberOfCharSpiner);
		numberOfCharSpiner.setValue(10);

		JPanel GenPanel = new JPanel();
		keyGenPanel.add(GenPanel);

		JButton keyGenButt = new JButton("Generate");
		keyGenButt.addActionListener(rsaListener);
		keyGenButt.setBackground(Color.WHITE);
		keyGenButt.setFocusPainted(false);
		keyGenButt.setVerticalAlignment(SwingConstants.BOTTOM);
		GenPanel.add(keyGenButt);

		JButton setEKeyButt = new JButton("Set Encrypt key");
		setEKeyButt.addActionListener(rsaListener);
		setEKeyButt.setForeground(Color.BLACK);
		setEKeyButt.setFocusPainted(false);
		setEKeyButt.setBackground(Color.WHITE);
		GenPanel.add(setEKeyButt);

		JButton setDKeyButt = new JButton("Set Decrypt key");
		setDKeyButt.addActionListener(rsaListener);
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

		Border encryptBorder = new TitledBorder(new LineBorder(Color.gray), "Encrypt", TitledBorder.LEFT,
				TitledBorder.CENTER);
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

		ePlainArea = new JTextArea();
		eTextPanel.add(ePlainArea);

		JLabel eCipherLabel = new JLabel("Cipher text:");
		eCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eCipherLabel.setForeground(Color.BLACK);
		eTextPanel.add(eCipherLabel);

		eCipherArea = new JTextArea();
		eTextPanel.add(eCipherArea);

		JPanel encryptButtPanel = new JPanel();
		encryptButtPanel.setForeground(Color.BLACK);
		encryptButtPanel.setBackground(SystemColor.menu);
		encrypt.add(encryptButtPanel, BorderLayout.SOUTH);

		JButton encryptButt = new JButton("Encrypt");
		encryptButt.addActionListener(rsaListener);
		encryptButt.setForeground(Color.BLACK);
		encryptButt.setFocusPainted(false);
		encryptButt.setBackground(Color.WHITE);
		encryptButtPanel.add(encryptButt);

		JButton eSetButt = new JButton("Set Cipher text");
		eSetButt.addActionListener(rsaListener);
		eSetButt.setForeground(Color.BLACK);
		eSetButt.setFocusPainted(false);
		eSetButt.setBackground(Color.WHITE);
		encryptButtPanel.add(eSetButt);

		JPanel decrypt = new JPanel();
		cipherPanel.add(decrypt);
		decrypt.setLayout(new BorderLayout(0, 0));

		Border decryptBorder = new TitledBorder(new LineBorder(Color.gray), "Decrypt", TitledBorder.LEFT,
				TitledBorder.CENTER);
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

		dCipherArea = new JTextArea();
		dTextPanel.add(dCipherArea);

		JLabel dPlainLabel = new JLabel("Plain text:");
		dPlainLabel.setVerticalAlignment(SwingConstants.TOP);
		dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dPlainLabel.setForeground(Color.BLACK);
		dTextPanel.add(dPlainLabel);

		dPlainArea = new JTextArea();
		dTextPanel.add(dPlainArea);

		JPanel decryptButtPanel = new JPanel();
		decryptButtPanel.setForeground(Color.BLACK);
		decryptButtPanel.setBackground(SystemColor.menu);
		decrypt.add(decryptButtPanel, BorderLayout.SOUTH);

		JButton decryptButt = new JButton("Decrypt");
		decryptButt.addActionListener(rsaListener);
		decryptButt.setForeground(Color.BLACK);
		decryptButt.setFocusPainted(false);
		decryptButt.setBackground(Color.WHITE);
		decryptButtPanel.add(decryptButt);

		JButton dSetButt = new JButton("Set Plain text");
		dSetButt.addActionListener(rsaListener);
		dSetButt.setForeground(Color.BLACK);
		dSetButt.setFocusPainted(false);
		dSetButt.setBackground(Color.WHITE);
		decryptButtPanel.add(dSetButt);
	}

	public RSAModel getRsaModel() {
		return rsaModel;
	}

	public String getePublicTextField() {
		return ePublicTextField.getText().toString();
	}

	public String getdKeyTextField() {
		return dKeyTextField.getText().toString();
	}

	public String getePlainArea() {
		return ePlainArea.getText().toString();
	}

	public String geteCipherArea() {
		return eCipherArea.getText().toString();
	}

	public String getdCipherArea() {
		return dCipherArea.getText().toString();
	}

	public String getdPlainArea() {
		return dPlainArea.getText().toString();
	}

	public int getNumberOfCharSpiner() {
		return (int) numberOfCharSpiner.getValue();
	}

	public String getPrivateKeyArea() {
		return privateKeyArea.getText().toString();
	}

	public String getPublicKeyArea() {
		return publicKeyArea.getText().toString();
	}

	public void setRsaModel(RSAModel rsaModel) {
		this.rsaModel = rsaModel;
	}
	public void setePublicTextField(String str) {
		this.ePublicTextField.setText(str);
	}

	public void setdKeyTextField(String str) {
		this.dKeyTextField.setText(str);
	}

	public void setePlainArea(String str) {
		this.ePlainArea.setText(str);
	}

	public void seteCipherArea(String str) {
		this.eCipherArea.setText(str);
	}

	public void setdCipherArea(String str) {
		this.dCipherArea.setText(str);
	}

	public void setdPlainArea(String str) {
		this.dPlainArea.setText(str);
	}

	public void setNumberOfCharSpiner(int number) {
		this.numberOfCharSpiner.setValue(number);
	}

	public void setPrivateKeyArea(String str) {
		this.privateKeyArea.setText(str);
	}

	public void setPublicKeyArea(String str) {
		this.publicKeyArea.setText(str);
	}

	public String[] generate() {
		int digits = Integer.valueOf(this.getNumberOfCharSpiner());
		this.rsaModel = new RSAModel(digits);
		String[] key = new String[2];

		key[0] = this.rsaModel.getD().toString();
		key[1] = this.rsaModel.getE().toString();

		return key;
	}

}
