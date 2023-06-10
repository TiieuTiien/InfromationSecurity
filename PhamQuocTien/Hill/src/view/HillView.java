package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.HillListener;
import model.HillModel;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class HillView extends JFrame {

	private HillModel hillModel;
	private JPanel contentPane;
	private JTextField keyGenTextField;
	private JTextField eCipherTextField;
	private JTextField dPlainTextField;
	private JTextField dCipherTextField;
	private JTextField ePlainTextField;
	private JTextField eKeyTextField;
	private JTextField dKeyTextField;

	/**
	 * Create the frame.
	 */
	public HillView() {
		setResizable(false);
		setTitle("Hill Cipher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		HillListener hillListener = new HillListener(this);

		TitledBorder border = BorderFactory.createTitledBorder("Hill Cipher");
		border.setTitleJustification(TitledBorder.CENTER);
		contentPane.setBorder(border);

		JPanel borderPanel = new JPanel();
		borderPanel.setBackground(SystemColor.control);
		borderPanel.setForeground(SystemColor.desktop);
		contentPane.add(borderPanel, BorderLayout.CENTER);
		borderPanel.setLayout(new BorderLayout(0, 0));

		JPanel keyGenPanel = new JPanel();
		keyGenPanel.setBackground(SystemColor.control);
		keyGenPanel.setForeground(SystemColor.desktop);
		borderPanel.add(keyGenPanel, BorderLayout.NORTH);
		keyGenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel keyGenLabel = new JLabel("Key generate");
		keyGenLabel.setForeground(SystemColor.desktop);
		keyGenPanel.add(keyGenLabel);

		keyGenTextField = new JTextField("RRFVSVCCT");
		keyGenTextField.setForeground(SystemColor.desktop);
		keyGenPanel.add(keyGenTextField);
		keyGenTextField.setColumns(10);

		JButton keyGenButt = new JButton("Generate");
		keyGenButt.setForeground(SystemColor.desktop);
		keyGenButt.setBackground(Color.WHITE);
		keyGenPanel.add(keyGenButt);

		JButton setEKeyButt = new JButton("Set Encrypt key");
		setEKeyButt.setForeground(SystemColor.desktop);
		setEKeyButt.setBackground(Color.WHITE);
		keyGenPanel.add(setEKeyButt);

		JButton setDKeyButt = new JButton("Set Decrypt key");
		setDKeyButt.setForeground(SystemColor.desktop);
		setDKeyButt.setBackground(Color.WHITE);
		keyGenPanel.add(setDKeyButt);

		JPanel cipherPanel = new JPanel();
		cipherPanel.setForeground(SystemColor.desktop);
		cipherPanel.setBackground(SystemColor.control);
		borderPanel.add(cipherPanel, BorderLayout.CENTER);
		cipherPanel.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel encrypt = new JPanel();
		encrypt.setForeground(SystemColor.desktop);
		encrypt.setBackground(SystemColor.control);
		cipherPanel.add(encrypt);
		GridBagLayout gbl_encrypt = new GridBagLayout();
		gbl_encrypt.columnWidths = new int[] { 282, 0 };
		gbl_encrypt.rowHeights = new int[] { 20, 160, 33, 0 };
		gbl_encrypt.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_encrypt.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		encrypt.setLayout(gbl_encrypt);

		TitledBorder encryptBorder = BorderFactory.createTitledBorder("Encrypt");
		encrypt.setBorder(encryptBorder);

		JPanel eKeyPanel = new JPanel();
		eKeyPanel.setForeground(SystemColor.desktop);
		eKeyPanel.setBackground(SystemColor.control);
		GridBagConstraints gbc_eKeyPanel = new GridBagConstraints();
		gbc_eKeyPanel.anchor = GridBagConstraints.NORTH;
		gbc_eKeyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_eKeyPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_eKeyPanel.gridx = 0;
		gbc_eKeyPanel.gridy = 0;
		encrypt.add(eKeyPanel, gbc_eKeyPanel);

		JLabel eKeyLabel = new JLabel("Enter encrypt key:");
		eKeyLabel.setForeground(SystemColor.desktop);
		eKeyPanel.add(eKeyLabel);

		eKeyTextField = new JTextField();
		eKeyTextField.setForeground(SystemColor.desktop);
		eKeyPanel.add(eKeyTextField);
		eKeyTextField.setColumns(10);

		JPanel eTextPanel = new JPanel();
		eTextPanel.setBackground(SystemColor.control);
		eTextPanel.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_eTextPanel = new GridBagConstraints();
		gbc_eTextPanel.fill = GridBagConstraints.BOTH;
		gbc_eTextPanel.insets = new Insets(0, 0, 5, 0);
		gbc_eTextPanel.gridx = 0;
		gbc_eTextPanel.gridy = 1;
		encrypt.add(eTextPanel, gbc_eTextPanel);
		eTextPanel.setLayout(new GridLayout(2, 2, 5, 5));

		JLabel ePlainLabel = new JLabel("Plain text:");
		ePlainLabel.setForeground(SystemColor.desktop);
		ePlainLabel.setVerticalAlignment(SwingConstants.TOP);
		ePlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eTextPanel.add(ePlainLabel);

		ePlainTextField = new JTextField("MOR");
		ePlainTextField.setForeground(SystemColor.desktop);
		eTextPanel.add(ePlainTextField);

		JLabel eCipherLabel = new JLabel("Cipher text:");
		eCipherLabel.setForeground(SystemColor.desktop);
		eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		eTextPanel.add(eCipherLabel);

		eCipherTextField = new JTextField();
		eCipherTextField.setForeground(SystemColor.desktop);
		eTextPanel.add(eCipherTextField);

		JPanel encryptButtPanel = new JPanel();
		encryptButtPanel.setBackground(SystemColor.control);
		encryptButtPanel.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_encryptButtPanel = new GridBagConstraints();
		gbc_encryptButtPanel.anchor = GridBagConstraints.NORTH;
		gbc_encryptButtPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_encryptButtPanel.gridx = 0;
		gbc_encryptButtPanel.gridy = 2;
		encrypt.add(encryptButtPanel, gbc_encryptButtPanel);

		JButton encryptButt = new JButton("Encrypt");
		encryptButt.setForeground(SystemColor.desktop);
		encryptButt.setBackground(Color.WHITE);
		encryptButtPanel.add(encryptButt);

		JButton eSetButt = new JButton("Set Cipher text");
		eSetButt.setForeground(SystemColor.desktop);
		eSetButt.setBackground(Color.WHITE);
		encryptButtPanel.add(eSetButt);

		JPanel decrypt = new JPanel();
		decrypt.setBackground(SystemColor.control);
		decrypt.setForeground(SystemColor.desktop);
		cipherPanel.add(decrypt);
		GridBagLayout gbl_decrypt = new GridBagLayout();
		gbl_decrypt.columnWidths = new int[] { 282, 0 };
		gbl_decrypt.rowHeights = new int[] { 20, 160, 33, 0 };
		gbl_decrypt.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_decrypt.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		decrypt.setLayout(gbl_decrypt);

		TitledBorder decryptBorder = BorderFactory.createTitledBorder("Decrypt");
		decrypt.setBorder(decryptBorder);

		JPanel dKeyPanel = new JPanel();
		dKeyPanel.setBackground(SystemColor.control);
		dKeyPanel.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_dKeyPanel = new GridBagConstraints();
		gbc_dKeyPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_dKeyPanel.anchor = GridBagConstraints.NORTH;
		gbc_dKeyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dKeyPanel.gridx = 0;
		gbc_dKeyPanel.gridy = 0;
		decrypt.add(dKeyPanel, gbc_dKeyPanel);

		JLabel dKeyLabel = new JLabel("Enter decrypt key:");
		dKeyLabel.setForeground(SystemColor.desktop);
		dKeyPanel.add(dKeyLabel);

		dKeyTextField = new JTextField();
		dKeyTextField.setForeground(SystemColor.desktop);
		dKeyPanel.add(dKeyTextField);
		dKeyTextField.setColumns(10);

		JPanel dTextPanel = new JPanel();
		dTextPanel.setBackground(SystemColor.control);
		dTextPanel.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_dTextPanel = new GridBagConstraints();
		gbc_dTextPanel.fill = GridBagConstraints.BOTH;
		gbc_dTextPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dTextPanel.gridx = 0;
		gbc_dTextPanel.gridy = 1;
		decrypt.add(dTextPanel, gbc_dTextPanel);
		dTextPanel.setLayout(new GridLayout(2, 2, 5, 5));

		JLabel dCipherLabel = new JLabel("Cipher text:");
		dCipherLabel.setForeground(SystemColor.desktop);
		dCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		dCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dCipherLabel);

		dCipherTextField = new JTextField();
		dCipherTextField.setForeground(SystemColor.desktop);
		dTextPanel.add(dCipherTextField);

		JLabel dPlainLabel = new JLabel("Plain text:");
		dPlainLabel.setForeground(SystemColor.desktop);
		dPlainLabel.setVerticalAlignment(SwingConstants.TOP);
		dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dPlainLabel);

		dPlainTextField = new JTextField();
		dPlainTextField.setForeground(SystemColor.desktop);
		dTextPanel.add(dPlainTextField);

		JPanel decryptButtPanel = new JPanel();
		decryptButtPanel.setBackground(SystemColor.control);
		decryptButtPanel.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_decryptButtPanel = new GridBagConstraints();
		gbc_decryptButtPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_decryptButtPanel.anchor = GridBagConstraints.NORTH;
		gbc_decryptButtPanel.gridx = 0;
		gbc_decryptButtPanel.gridy = 2;
		decrypt.add(decryptButtPanel, gbc_decryptButtPanel);

		JButton decryptButt = new JButton("Decrypt");
		decryptButt.setForeground(SystemColor.desktop);
		decryptButt.setBackground(Color.WHITE);
		decryptButtPanel.add(decryptButt);

		JButton dSetButt = new JButton("Set Plain text");
		dSetButt.setForeground(SystemColor.desktop);
		dSetButt.setBackground(Color.WHITE);
		decryptButtPanel.add(dSetButt);

		keyGenButt.addActionListener(hillListener);
		setEKeyButt.addActionListener(hillListener);
		setDKeyButt.addActionListener(hillListener);
		encryptButt.addActionListener(hillListener);
		eSetButt.addActionListener(hillListener);
		decryptButt.addActionListener(hillListener);
		dSetButt.addActionListener(hillListener);

		Font font = new Font("Tahoma", Font.PLAIN, 11);
		Font fontPlain = new Font("Tahoma", Font.PLAIN, 11);

		keyGenLabel.setFont(font);
		eKeyLabel.setFont(font);
		ePlainLabel.setFont(font);
		eCipherLabel.setFont(font);
		dKeyLabel.setFont(font);
		dCipherLabel.setFont(font);
		dPlainLabel.setFont(font);

		keyGenTextField.setFont(fontPlain);
		eKeyTextField.setFont(fontPlain);
		ePlainTextField.setFont(fontPlain);
		eCipherTextField.setFont(fontPlain);
		dKeyTextField.setFont(fontPlain);
		dCipherTextField.setFont(fontPlain);
		dPlainTextField.setFont(fontPlain);

		keyGenButt.setFont(font);
		setEKeyButt.setFont(font);
		setDKeyButt.setFont(font);
		encryptButt.setFont(font);
		eSetButt.setFont(font);
		decryptButt.setFont(font);
		dSetButt.setFont(font);

		keyGenButt.setFocusPainted(false);
		setEKeyButt.setFocusPainted(false);
		setDKeyButt.setFocusPainted(false);
		encryptButt.setFocusPainted(false);
		eSetButt.setFocusPainted(false);
		decryptButt.setFocusPainted(false);
		dSetButt.setFocusPainted(false);
		this.setLocationRelativeTo(null);
		contentPane.setBackground(SystemColor.control);

	}

	public JTextField getKeyGenTextField() {
		return keyGenTextField;
	}

	public String geteCipherTextField() {
		return eCipherTextField.getText().toString();
	}

	public String getdCipherTextField() {
		return dCipherTextField.getText().toString();
	}

	public String getdPlainTextField() {
		return dPlainTextField.getText().toString();
	}

	public String getePlainTextField() {
		return ePlainTextField.getText().toString();
	}

	public String geteKeyTextField() {
		return eKeyTextField.getText().toString();
	}

	public String getdKeyTextField() {
		return dKeyTextField.getText().toString();
	}

	public void setKeyGenTextField(String str) {
		this.keyGenTextField.setText(str);
		;
	}

	public void seteCipherTextField(String str) {
		this.eCipherTextField.setText(str);
		;
	}

	public void setdCipherTextField(String str) {
		this.dCipherTextField.setText(str);
		;
	}

	public void setdPlainTextField(String str) {
		this.dPlainTextField.setText(str);
		;
	}

	public void setePlainTextField(String str) {
		this.ePlainTextField.setText(str);
		;
	}

	public void seteKeyTextField(String str) {
		this.eKeyTextField.setText(str);
		;
	}

	public void setdKeyTextField(String str) {
		this.dKeyTextField.setText(str);
		;
	}

	public String encrypt() {
		String key = this.geteKeyTextField().toUpperCase();
		String message = this.getePlainTextField().toUpperCase();

		if (key.length() != 9) {
			return "Key not equal 9!";
		} else if (message.length() != 3) {
			return "Message not equal 3!";
		}

		this.hillModel = new HillModel(key, message);

		System.out.println(hillModel.getKey());
		String cipher = this.hillModel.encrypt();

		return cipher = (cipher.length() > 0) ? cipher : "Type something, please!";
	}

	public String decrypt() {
		String key = this.getdKeyTextField().toUpperCase();
		String message = this.getdCipherTextField().toUpperCase();

		if (key.length() != 9) {
			return "Key not equal 9!";
		} else if (message.length() != 3) {
			return "Message not equal 3!";
		}

		this.hillModel = new HillModel(key, message);

		String plain = this.hillModel.decrypt(message, key);

		return plain = (plain.length() > 0) ? plain : "Type something, please!";
	}

	public String keyGen() {
		hillModel = new HillModel();

		hillModel.setKey("");

		String key = this.hillModel.keyGen();

		System.out.println(key);

		return key;
	}

}
