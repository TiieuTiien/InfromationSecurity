package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.CeasarListener;
import model.CeasarModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CeasarView extends JFrame {

	private CeasarModel ceasarModel = new CeasarModel();
	private JPanel ceasarPanel;
	private JTextField shiftKeyTextField;
	private JTextField dCipherText;
	private JTextField dPlainTextField;
	private JTextField ePlainTextField;
	private JTextField eCipherTextField;

	/**
	 * Create the frame.
	 */
	public CeasarView() {
		
		CeasarListener ceasarListener = new CeasarListener(this);

		int width = 400;
		int height = 300;
		
		Border border = BorderFactory.createLineBorder(Color.gray);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ceasarPanel = new JPanel();
		ceasarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ceasarPanel);
		ceasarPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel mainPanel = new JPanel();
		ceasarPanel.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(10, 10));

		JPanel namePanel = new JPanel();
		namePanel.setBorder(border);
		mainPanel.add(namePanel, BorderLayout.NORTH);

		JLabel ceasarCipherPanel = new JLabel("Ceasar Cipher");
		namePanel.add(ceasarCipherPanel);

		JPanel borderPanel = new JPanel();
		borderPanel.setBorder(border);
		mainPanel.add(borderPanel, BorderLayout.CENTER);
		borderPanel.setLayout(new BorderLayout(0, 0));

		JPanel keyGenPanel = new JPanel();
		borderPanel.add(keyGenPanel, BorderLayout.NORTH);

		JLabel shiftKeyLabel = new JLabel("Shift key");
		keyGenPanel.add(shiftKeyLabel);

		shiftKeyTextField = new JTextField();
		keyGenPanel.add(shiftKeyTextField);
		shiftKeyTextField.setColumns(10);

		JButton keyGenButt = new JButton("Generate");
		keyGenButt.addActionListener(ceasarListener);
		keyGenPanel.add(keyGenButt);

		JPanel cipherPanel = new JPanel();
		borderPanel.add(cipherPanel, BorderLayout.CENTER);
		cipherPanel.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel encryptPanel = new JPanel();
		cipherPanel.add(encryptPanel);
		encryptPanel.setLayout(new BorderLayout(10, 10));

		JLabel eLabel = new JLabel("Encrypt");
		eLabel.setHorizontalAlignment(SwingConstants.CENTER);
		encryptPanel.add(eLabel, BorderLayout.NORTH);

		JPanel eTextPanel = new JPanel();
		encryptPanel.add(eTextPanel, BorderLayout.CENTER);
		eTextPanel.setLayout(new GridLayout(2, 2, 10, 10));

		JLabel ePlainLabel = new JLabel("Plaint Text");
		ePlainLabel.setVerticalAlignment(SwingConstants.TOP);
		ePlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eTextPanel.add(ePlainLabel);
		
		ePlainTextField = new JTextField();
		ePlainTextField.setColumns(10);
		eTextPanel.add(ePlainTextField);
		
				JLabel eCipherLabel = new JLabel("Cipher Text:");
				eCipherLabel.setVerticalAlignment(SwingConstants.TOP);
				eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
				eTextPanel.add(eCipherLabel);
		
		eCipherTextField = new JTextField();
		eCipherTextField.setColumns(10);
		eTextPanel.add(eCipherTextField);
		
		JPanel eButtPanel = new JPanel();
		encryptPanel.add(eButtPanel, BorderLayout.SOUTH);
		
		JButton eButt = new JButton("Encrypt");
		eButt.addActionListener(ceasarListener);
		eButtPanel.add(eButt);

		JPanel decryptPanel = new JPanel();
		cipherPanel.add(decryptPanel);
		decryptPanel.setLayout(new BorderLayout(10, 10));

		JLabel dLabel = new JLabel("Decrypt");
		dLabel.setHorizontalAlignment(SwingConstants.CENTER);
		decryptPanel.add(dLabel, BorderLayout.NORTH);

		JPanel dTextPanel = new JPanel();
		decryptPanel.add(dTextPanel, BorderLayout.CENTER);
		dTextPanel.setLayout(new GridLayout(2, 2, 10, 10));

		JLabel dCipherLabel = new JLabel("Cipher Text:");
		dCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		dCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dCipherLabel);

		dCipherText = new JTextField();
		dTextPanel.add(dCipherText);
		dCipherText.setColumns(10);

		JLabel dPlainLabel = new JLabel("Plaint Text");
		dPlainLabel.setVerticalAlignment(SwingConstants.TOP);
		dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dPlainLabel);

		dPlainTextField = new JTextField();
		dPlainTextField.setColumns(10);
		dTextPanel.add(dPlainTextField);
		
		JPanel dButtPanel = new JPanel();
		decryptPanel.add(dButtPanel, BorderLayout.SOUTH);
		
		JButton dButt = new JButton("Decrypt");
		dButt.addActionListener(ceasarListener);
		dButtPanel.add(dButt);
		this.setTitle("Ceasar encryption and decryption");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
	}

	public JPanel getCeasarPanel() {
		return ceasarPanel;
	}

	public void setCeasarPanel(JPanel ceasarPanel) {
		this.ceasarPanel = ceasarPanel;
	}

	public JTextField getShiftKeyTextField() {
		return shiftKeyTextField;
	}

	public void setShiftKeyTextField(String str) {
		this.shiftKeyTextField.setText(str);;
	}

	public JTextField getdCipherText() {
		return dCipherText;
	}

	public void setdCipherText(JTextField dCipherText) {
		this.dCipherText = dCipherText;
	}

	public CeasarModel getCeasarModel() {
		return ceasarModel;
	}

	public void setCeasarModel(CeasarModel ceasarModel) {
		this.ceasarModel = ceasarModel;
	}

	public JTextField getePlainTextField() {
		return ePlainTextField;
	}

	public void setePlainTextField(JTextField ePlainTextField) {
		this.ePlainTextField = ePlainTextField;
	}

	public JTextField geteCipherTextField() {
		return eCipherTextField;
	}

	public void seteCipherTextField(String str) {
		this.eCipherTextField.setText(str);;
	}

	public void setShiftKeyTextField(JTextField shiftKeyTextField) {
		this.shiftKeyTextField = shiftKeyTextField;
	}

	public JTextField getdPlainTextField() {
		return dPlainTextField;
	}

	public void setdPlainTextField(String str) {
		this.dPlainTextField.setText(str);;
	}

	public String generate() {
		int shiftKey = this.ceasarModel.shiftKeyGen();
		this.ceasarModel.setShiftKey(shiftKey);
		return Integer.toString(shiftKey);
	}

	public String encrypt() {
		int shiftKey = Integer.valueOf(this.getShiftKeyTextField().getText().toString());
		this.ceasarModel.setShiftKey(shiftKey);
		String plain = this.getePlainTextField().getText().toString();
		String cipher = (plain.length() > 0 ) ? this.ceasarModel.encrypt(plain) : "";
		return cipher;
	}

	public String decrypt() {
		int shiftKey = Integer.valueOf(this.getShiftKeyTextField().getText().toString());
		this.ceasarModel.setShiftKey(shiftKey);
		String cipher = this.getdCipherText().getText().toString();
		String plain = (cipher.length() > 0 ) ? this.ceasarModel.decrypt(cipher) : "";
		return plain;
	}

}
