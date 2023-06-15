package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

public class DiffieHellmanGUI extends JFrame implements ActionListener {

	private JTextField primeField;
	private JTextField baseField;
	private JTextField privateKeyField;
	private JTextArea publicKeyArea;
	private JButton generateKeysButton;

	public DiffieHellmanGUI() {
		setTitle("Diffie-Hellman Key Exchange");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5, 2));

		JLabel primeLabel = new JLabel("Prime Number (p):");
		primeField = new JTextField();
		JLabel baseLabel = new JLabel("Base Value (g):");
		baseField = new JTextField();
		JLabel privateKeyLabel = new JLabel("Private Key:");
		privateKeyField = new JTextField();
		JLabel publicKeyLabel = new JLabel("Public Key:");
		publicKeyArea = new JTextArea();
		publicKeyArea.setEditable(false);
		generateKeysButton = new JButton("Generate Keys");
		generateKeysButton.addActionListener(this);

		add(primeLabel);
		add(primeField);
		add(baseLabel);
		add(baseField);
		add(privateKeyLabel);
		add(privateKeyField);
		add(publicKeyLabel);
		add(new JScrollPane(publicKeyArea));
		add(new JLabel()); // Empty label for spacing
		add(generateKeysButton);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generateKeysButton) {
			BigInteger p = new BigInteger(primeField.getText());
			BigInteger g = new BigInteger(baseField.getText());
			BigInteger privateKey = new BigInteger(privateKeyField.getText());

			BigInteger publicKey = g.modPow(privateKey, p);

			publicKeyArea.setText(publicKey.toString());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new DiffieHellmanGUI());
	}
}
