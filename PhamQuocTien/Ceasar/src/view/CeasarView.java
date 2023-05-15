package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CeasarView extends JFrame implements ActionListener{
	
	private JLabel encryptLabel;
	private JLabel keyLabel;
	private JTextField encryptField;
	private JTextField keyField;
	private JButton encryptBtn;
	private JButton decryptBtn;
	private JLabel encryptedLabel;
	private JLabel decryptedLabel;

	public CeasarView() {
		init();
	}

	private void init() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        encryptLabel = new JLabel("Encrypt Text: ");
        keyLabel = new JLabel("Key: ");
        encryptField = new JTextField(15);
        keyField = new JTextField(15);
        encryptBtn = new JButton("Encrypt");
        encryptedLabel = new JLabel();
        decryptBtn = new JButton("Decrypt");
        decryptedLabel = new JLabel();
        
        int width = 400;
        int height = 300;
        
        JPanel panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(new GridLayout(4, 2));
        panel.add(encryptLabel);
        panel.add(encryptField);
        panel.add(keyLabel);
        panel.add(keyField);
        panel.add(encryptBtn);
        panel.add(encryptedLabel);
        panel.add(decryptBtn);
        panel.add(decryptedLabel);
        
        this.add(panel);
        this.setTitle("Ceasar encryption and decryption");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
