package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.RSAView;

public class RSAListener implements ActionListener{
	
	private RSAView rsaView;

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
			
			this.rsaView.setPrivateKeyArea(privateKey);
			this.rsaView.setPublicKeyArea(publicKey);
		}else if(command == "") {
			
		}else if(command == "") {
			
		}else if(command == "") {
			
		}else if(command == "") {
			
		}else if(command == "") {
			
		}else if(command == "") {
			
		}
		
	}
	
	
}
