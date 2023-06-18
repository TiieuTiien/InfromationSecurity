package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.AffineListener;
import model.AffineModel;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AffineView extends JFrame {

	private static final long serialVersionUID = 1L;
	private AffineModel afflineModel = new AffineModel();
	private JPanel panel;
	private JTextField dCipherTextField;
	private JTextField dPlainTextField;
	private JTextField eCipherTextField;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField ePlainTextField;
	private JTextField eKeyATextField;
	private JTextField eKeyBTextField;
	private JTextField dKeyATextField;
	private JTextField dKeyBTextField;

	/**
	 * Create the frame.
	 */
	public AffineView() {

		AffineListener affineListener = new AffineListener(this);

		setBounds(100, 100, 600, 350);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);

		Border border = BorderFactory.createLineBorder(Color.GRAY);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 574, 0 };
		gbl_panel.rowHeights = new int[] { 60, 206, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel keyPanel = new JPanel();
		keyPanel.setBorder(border);
		GridBagLayout gbl_keyPanel = new GridBagLayout();
		gbl_keyPanel.columnWidths = new int[] { 263, 1, 0 };
		gbl_keyPanel.rowHeights = new int[] { 30, 23, 0 };
		gbl_keyPanel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_keyPanel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		keyPanel.setLayout(gbl_keyPanel);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		keyPanel.add(panel_3, gbc_panel_3);

		JLabel keyGenLabel = new JLabel("Key generater");
		panel_3.add(keyGenLabel);

		JLabel aLabel = new JLabel("a");
		panel_3.add(aLabel);
		aLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		aTextField = new JTextField();
		panel_3.add(aTextField);
		aTextField.setHorizontalAlignment(SwingConstants.LEFT);
		aTextField.setColumns(3);

		JLabel bLabel = new JLabel("b");
		panel_3.add(bLabel);
		bLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		bTextField = new JTextField();
		panel_3.add(bTextField);
		bTextField.setColumns(3);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		keyPanel.add(panel_5, gbc_panel_5);

		JButton setEKeyButt = new JButton("Set Encrypt key");
		setEKeyButt.addActionListener(affineListener);
		panel_5.add(setEKeyButt);

		JButton keyGenButt = new JButton("Generate");
		panel_5.add(keyGenButt);

		JButton setDkeyButt = new JButton("Set Decrypt key");
		setDkeyButt.addActionListener(affineListener);
		panel_5.add(setDkeyButt);

		keyGenButt.addActionListener(affineListener);

		JPanel abPanel = new JPanel();
		GridBagConstraints gbc_abPanel = new GridBagConstraints();
		gbc_abPanel.anchor = GridBagConstraints.WEST;
		gbc_abPanel.gridx = 1;
		gbc_abPanel.gridy = 1;
		keyPanel.add(abPanel, gbc_abPanel);
		abPanel.setLayout(new GridLayout(2, 1, 0, 0));
		GridBagConstraints gbc_keyPanel = new GridBagConstraints();
		gbc_keyPanel.anchor = GridBagConstraints.NORTH;
		gbc_keyPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_keyPanel.gridx = 0;
		gbc_keyPanel.gridy = 0;
		panel.add(keyPanel, gbc_keyPanel);
		panel.setPreferredSize(new Dimension(300, 400));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2, 10, 10));

		JPanel borderPanel = new JPanel();
		mainPanel.add(borderPanel);
		borderPanel.setLayout(new BorderLayout(10, 10));

		JPanel cipherPanel = new JPanel();
		borderPanel.add(cipherPanel, BorderLayout.CENTER);
		cipherPanel.setLayout(new GridLayout(1, 2, 10, 10));

		JPanel encryptPanel = new JPanel();
		cipherPanel.add(encryptPanel);
		GridBagLayout gbl_encryptPanel = new GridBagLayout();
		gbl_encryptPanel.columnWidths = new int[] { 305, 5 };
		gbl_encryptPanel.rowHeights = new int[] { 20, 0, 123, 0, 5 };
		gbl_encryptPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_encryptPanel.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		encryptPanel.setLayout(gbl_encryptPanel);
		encryptPanel.setBorder(border);

		JLabel encryptLabel = new JLabel("Encrypt");
		GridBagConstraints gbc_encryptLabel = new GridBagConstraints();
		gbc_encryptLabel.insets = new Insets(0, 0, 5, 0);
		gbc_encryptLabel.gridx = 0;
		gbc_encryptLabel.gridy = 0;
		encryptPanel.add(encryptLabel, gbc_encryptLabel);

		JPanel eKeyPanel = new JPanel();
		GridBagConstraints gbc_eKeyPanel = new GridBagConstraints();
		gbc_eKeyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_eKeyPanel.fill = GridBagConstraints.BOTH;
		gbc_eKeyPanel.gridx = 0;
		gbc_eKeyPanel.gridy = 1;
		encryptPanel.add(eKeyPanel, gbc_eKeyPanel);
		eKeyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel eKeyALabel = new JLabel("a");
		eKeyPanel.add(eKeyALabel);
		eKeyALabel.setHorizontalAlignment(SwingConstants.RIGHT);

		eKeyATextField = new JTextField();
		eKeyPanel.add(eKeyATextField);
		eKeyATextField.setHorizontalAlignment(SwingConstants.LEFT);
		eKeyATextField.setColumns(3);

		JLabel eKeyBLabel = new JLabel("b");
		eKeyPanel.add(eKeyBLabel);
		eKeyBLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		eKeyBTextField = new JTextField();
		eKeyPanel.add(eKeyBTextField);
		eKeyBTextField.setColumns(3);

		JPanel eTextPanel = new JPanel();
		GridBagConstraints gbc_eTextPanel = new GridBagConstraints();
		gbc_eTextPanel.insets = new Insets(0, 0, 5, 0);
		gbc_eTextPanel.fill = GridBagConstraints.BOTH;
		gbc_eTextPanel.gridx = 0;
		gbc_eTextPanel.gridy = 2;
		encryptPanel.add(eTextPanel, gbc_eTextPanel);
		eTextPanel.setLayout(new GridLayout(2, 2, 10, 10));

		JLabel ePlainLabel = new JLabel("Plaint Text");
		ePlainLabel.setVerticalAlignment(SwingConstants.TOP);
		ePlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eTextPanel.add(ePlainLabel);

		JPanel ePlainPanel = new JPanel();
		eTextPanel.add(ePlainPanel);
		ePlainPanel.setLayout(new GridLayout(0, 1, 0, 0));

		ePlainTextField = new JTextField("ABCDefgh");
		ePlainPanel.add(ePlainTextField);
		ePlainTextField.setColumns(10);

		JLabel eCipherLabel = new JLabel("Cipher Text:");
		eCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eTextPanel.add(eCipherLabel);

		JPanel eCipherPanel = new JPanel();
		eTextPanel.add(eCipherPanel);
		eCipherPanel.setLayout(new GridLayout(0, 1, 0, 0));

		eCipherTextField = new JTextField();
		eCipherPanel.add(eCipherTextField);
		eCipherTextField.setColumns(10);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		encryptPanel.add(panel_1, gbc_panel_1);

		JButton eButt = new JButton("Encrypt");
		panel_1.add(eButt);

		JButton setDCipherButt = new JButton("Set Cipher Text");
		setDCipherButt.addActionListener(affineListener);
		panel_1.add(setDCipherButt);
		eButt.addActionListener(affineListener);

		JPanel decryptPanel = new JPanel();
		cipherPanel.add(decryptPanel);
		GridBagLayout gbl_decryptPanel = new GridBagLayout();
		gbl_decryptPanel.columnWidths = new int[] { 305, 5 };
		gbl_decryptPanel.rowHeights = new int[] { 20, 0, 123, 0, 5 };
		gbl_decryptPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_decryptPanel.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		decryptPanel.setLayout(gbl_decryptPanel);
		decryptPanel.setBorder(border);

		JLabel decryptLabel = new JLabel("Decrypt");
		GridBagConstraints gbc_decryptLabel = new GridBagConstraints();
		gbc_decryptLabel.insets = new Insets(0, 0, 5, 0);
		gbc_decryptLabel.gridx = 0;
		gbc_decryptLabel.gridy = 0;
		decryptPanel.add(decryptLabel, gbc_decryptLabel);

		JPanel dKeyPanel = new JPanel();
		GridBagConstraints gbc_dKeyPanel = new GridBagConstraints();
		gbc_dKeyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dKeyPanel.fill = GridBagConstraints.BOTH;
		gbc_dKeyPanel.gridx = 0;
		gbc_dKeyPanel.gridy = 1;
		decryptPanel.add(dKeyPanel, gbc_dKeyPanel);
		dKeyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dKeyALabel = new JLabel("a");
		dKeyPanel.add(dKeyALabel);
		dKeyALabel.setHorizontalAlignment(SwingConstants.RIGHT);

		dKeyATextField = new JTextField();
		dKeyPanel.add(dKeyATextField);
		dKeyATextField.setHorizontalAlignment(SwingConstants.LEFT);
		dKeyATextField.setColumns(3);

		JLabel dKeyBLabel = new JLabel("b");
		dKeyPanel.add(dKeyBLabel);
		dKeyBLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		dKeyBTextField = new JTextField();
		dKeyPanel.add(dKeyBTextField);
		dKeyBTextField.setColumns(3);

		JPanel dTextPanel = new JPanel();
		GridBagConstraints gbc_dTextPanel = new GridBagConstraints();
		gbc_dTextPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dTextPanel.fill = GridBagConstraints.BOTH;
		gbc_dTextPanel.gridx = 0;
		gbc_dTextPanel.gridy = 2;
		decryptPanel.add(dTextPanel, gbc_dTextPanel);
		dTextPanel.setLayout(new GridLayout(2, 2, 10, 10));

		JLabel dCipherLabel = new JLabel("Cipher Text:");
		dCipherLabel.setVerticalAlignment(SwingConstants.TOP);
		dCipherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dCipherLabel);

		JPanel dCipherPanel = new JPanel();
		dTextPanel.add(dCipherPanel);
		dCipherPanel.setLayout(new GridLayout(0, 1, 0, 0));

		dCipherTextField = new JTextField();
		dCipherPanel.add(dCipherTextField);
		dCipherTextField.setColumns(10);

		JLabel dPlainLabel = new JLabel("Plaint Text");
		dPlainLabel.setVerticalAlignment(SwingConstants.TOP);
		dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dTextPanel.add(dPlainLabel);

		JPanel dPlainPanel = new JPanel();
		dTextPanel.add(dPlainPanel);
		dPlainPanel.setLayout(new GridLayout(0, 1, 0, 0));

		dPlainTextField = new JTextField();
		dPlainPanel.add(dPlainTextField);
		dPlainTextField.setColumns(10);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		decryptPanel.add(panel_2, gbc_panel_2);

		JButton dButt = new JButton("Decrypt");
		panel_2.add(dButt);
		dButt.addActionListener(affineListener);

		JButton setEPlainButt = new JButton("Set Plain Text");
		setEPlainButt.addActionListener(affineListener);
		panel_2.add(setEPlainButt);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		panel.add(mainPanel, gbc_mainPanel);

		setEKeyButt.setFocusPainted(false);
		setEPlainButt.setFocusPainted(false);
		setDkeyButt.setFocusPainted(false);
		setDCipherButt.setFocusPainted(false);
		keyGenButt.setFocusPainted(false);
		
		this.setTitle("Affline Cipher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public AffineModel getAfflineModel() {
		return afflineModel;
	}

	public void setAfflineModel(AffineModel afflineModel) {
		this.afflineModel = afflineModel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getdPlainTextField() {
		return dPlainTextField;
	}

	public void setdPlainTextField(String str) {
		this.dPlainTextField.setText(str);
	}

	public JTextField getdCipherTextField() {
		return dCipherTextField;
	}

	public void setdCipherTextField(String str) {
		this.dCipherTextField.setText(str);
	}

	public JTextField geteCipherTextField() {
		return eCipherTextField;
	}

	public void seteCipherTextField(String str) {
		this.eCipherTextField.setText(str);
		;
	}

	public JTextField getaTextField() {
		return aTextField;
	}

	public void setaTextField(String i) {
		this.aTextField.setText(i);
	}

	public JTextField getbTextField() {
		return bTextField;
	}

	public void setbTextField(String str) {
		this.bTextField.setText(str);
		;
	}

	public JTextField getePlainTextField() {
		return ePlainTextField;
	}

	public void setePlainTextField(String str) {
		this.ePlainTextField.setText(str);
		;
	}

	public JTextField geteKeyATextField() {
		return eKeyATextField;
	}

	public void seteKeyATextField(String str) {
		this.eKeyATextField.setText(str);
		;
	}

	public JTextField geteKeyBTextField() {
		return eKeyBTextField;
	}

	public void seteKeyBTextField(String str) {
		this.eKeyBTextField.setText(str);
		;
	}

	public JTextField getdKeyATextField() {
		return dKeyATextField;
	}

	public void setdKeyATextField(String str) {
		this.dKeyATextField.setText(str);
		;
	}

	public JTextField getdKeyBTextField() {
		return dKeyBTextField;
	}

	public void setdKeyBTextField(String str) {
		this.dKeyBTextField.setText(str);
		;
	}

	public int genarateA() {
		return this.afflineModel.aGen();
	}

	public int getB() {
		return this.afflineModel.bGen();
	}

	public String encrypt() {
		this.afflineModel.setA(Integer.valueOf(this.geteKeyATextField().getText().toString()));
		this.afflineModel.setB(Integer.valueOf(this.geteKeyBTextField().getText().toString()));
		String plain = this.getePlainTextField().getText().toString();
		String cipher = (plain.length() > 0) ? this.afflineModel.encryptMessage(plain.toCharArray()) : "";
		return cipher;
	}

	public String decrypt() {
		this.afflineModel.setA(Integer.valueOf(this.getdKeyATextField().getText().toString()));
		this.afflineModel.setB(Integer.valueOf(this.getdKeyBTextField().getText().toString()));
		String cipher = this.getdCipherTextField().getText().toString();
		String plain = (cipher.length() > 0) ? this.afflineModel.decrypt(cipher) : "";
		return plain;
	}
}
