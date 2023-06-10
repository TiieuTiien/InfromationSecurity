package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import view.RSAView;

public class RSAListener implements ActionListener {

	private RSAView rsaView;
	private BigInteger n;

	public RSAListener(RSAView rsaView) {
		this.rsaView = rsaView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().toString();

		if (command == "Generate") {
			String[] key = this.rsaView.generate();

			String privateKey = key[0];
			String publicKey = key[1];
			this.n = new BigInteger(key[2]);

			this.rsaView.setPrivateKeyArea(privateKey);
			this.rsaView.setPublicKeyArea(publicKey);

		} else if (command == "Set Encrypt key") {

			this.rsaView.seteKeyArea(this.rsaView.getPublicKeyArea());

		} else if (command == "Set Decrypt key") {

			this.rsaView.setdKeyArea(this.rsaView.getPrivateKeyArea());

		} else if (command == "Set Cipher text") {
			
			this.rsaView.setdCipherArea(this.rsaView.geteCipherArea());
			
		} else if (command == "Set Plain text") {
			
			this.rsaView.setePlainArea(this.rsaView.getdPlainArea());
			
		} else if (command == "Encrypt") {
			
			if(this.rsaView.getePlainArea().toString().length() == 0) {
				this.rsaView.setePlainArea("TypeSomeThing");
			} else {

				String cipherText = this.rsaView.encrypt(this.rsaView.getePlainArea(), this.n);

				this.rsaView.seteCipherArea(cipherText);
				
			}
		} else if (command == "Decrypt") {

			String message = this.rsaView.decrypt(this.rsaView.getdCipherArea(), this.n);

			this.rsaView.setdPlainArea(message);
			
		}

	}

}
