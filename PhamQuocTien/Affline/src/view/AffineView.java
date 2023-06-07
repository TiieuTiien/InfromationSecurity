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

		setBounds(100, 100, 600, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(10, 10));
		setContentPane(panel);

		Border border = BorderFactory.createLineBorder(Color.GRAY);
		JPanel affPanel = new JPanel();

		JLabel affLabel = new JLabel("AFFLINE CIPHER");
		affLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

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
		gbl_encryptPanel.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
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
		eKeyATextField.setColumns(5);

		JLabel eKeyBLabel = new JLabel("b");
		eKeyPanel.add(eKeyBLabel);
		eKeyBLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		eKeyBTextField = new JTextField();
		eKeyPanel.add(eKeyBTextField);
		eKeyBTextField.setColumns(5);

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

		ePlainTextField = new JTextField();
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

		JButton eButt = new JButton("Encrypt");
		GridBagConstraints gbc_eButt = new GridBagConstraints();
		gbc_eButt.gridx = 0;
		gbc_eButt.gridy = 3;
		encryptPanel.add(eButt, gbc_eButt);

		JPanel decryptPanel = new JPanel();
		cipherPanel.add(decryptPanel);
		GridBagLayout gbl_decryptPanel = new GridBagLayout();
		gbl_decryptPanel.columnWidths = new int[] { 305, 5 };
		gbl_decryptPanel.rowHeights = new int[] { 20, 0, 123, 0, 5 };
		gbl_decryptPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_decryptPanel.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
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
		dKeyATextField.setColumns(5);

		JLabel dKeyBLabel = new JLabel("b");
		dKeyPanel.add(dKeyBLabel);
		dKeyBLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		dKeyBTextField = new JTextField();
		dKeyPanel.add(dKeyBTextField);
		dKeyBTextField.setColumns(5);

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

		JButton dButt = new JButton("Decrypt");
		GridBagConstraints gbc_dButt = new GridBagConstraints();
		gbc_dButt.gridx = 0;
		gbc_dButt.gridy = 3;
		decryptPanel.add(dButt, gbc_dButt);

		JPanel keyPanel = new JPanel();
		keyPanel.setBorder(border);
		keyPanel.setLayout(new BorderLayout(0, 0));

		JPanel keyGenPanel = new JPanel();
		keyPanel.add(keyGenPanel, BorderLayout.NORTH);

		JLabel keyGenLabel = new JLabel("Key gen");
		keyGenPanel.add(keyGenLabel);

		JPanel abPanel = new JPanel();
		keyPanel.add(abPanel, BorderLayout.CENTER);
		abPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel aPanel = new JPanel();
		abPanel.add(aPanel);

		JLabel aLabel = new JLabel("a");
		aLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		aPanel.add(aLabel);

		aTextField = new JTextField();
		aTextField.setHorizontalAlignment(SwingConstants.LEFT);
		aTextField.setColumns(5);
		aPanel.add(aTextField);

		JPanel bPanel = new JPanel();
		abPanel.add(bPanel);

		JLabel bLabel = new JLabel("b");
		bLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		bPanel.add(bLabel);

		bTextField = new JTextField();
		bTextField.setColumns(5);
		bPanel.add(bTextField);

		JPanel keyGenButtPanel = new JPanel();
		keyPanel.add(keyGenButtPanel, BorderLayout.SOUTH);

		JButton keyGenButt = new JButton("Generate");
		keyGenButtPanel.add(keyGenButt);

		keyGenButt.addActionListener(affineListener);
		eButt.addActionListener(affineListener);
		dButt.addActionListener(affineListener);

		affPanel.setBorder(border);
		affPanel.add(affLabel);

		panel.add(affPanel, BorderLayout.NORTH);
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(300, 400));
		panel.add(keyPanel, BorderLayout.WEST);

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
		return dCipherTextField;
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

	public void setePlainTextField(JTextField ePlainTextField) {
		this.ePlainTextField = ePlainTextField;
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
		this.dKeyATextField.setText(str);;
	}

	public JTextField getdKeyBTextField() {
		return dKeyBTextField;
	}

	public void setdKeyBTextField(String str) {
		this.dKeyBTextField.setText(str);;
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
		String cipher = (plain.length() > 0)
				? this.afflineModel.encryptMessage(plain.toCharArray())
				: "";
		return cipher;
	}

	public String decrypt() {
		this.afflineModel.setA(Integer.valueOf(this.getdKeyATextField().getText().toString()));
		this.afflineModel.setB(Integer.valueOf(this.getdKeyBTextField().getText().toString()));
		String cipher = this.getdCipherTextField().getText().toString();
		String plain = (cipher.length() > 0)
				? this.afflineModel.decryptCipher(cipher)
				: "";
		return plain;
	}
}
