package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.HillView;

public class HillListener implements ActionListener{

	private HillView hillView;
	
	public HillListener(HillView hillView) {
		this.hillView = hillView;
	}



	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().toString();
		if(command == "Encrypt") {
			this.hillView.seteCipherTextField(this.hillView.encrypt());
		}else if(command == "Set Cipher text") {
			this.hillView.setdCipherTextField(this.hillView.geteCipherTextField().toString());
		}else if(command == "Decrypt") {
			this.hillView.setdPlainTextField(this.hillView.decrypt());
		}else if(command == "Generate") {
			this.hillView.setKeyGenTextField(this.hillView.keyGen());
		}else if(command == "Set Encrypt key") {
			this.hillView.seteKeyTextField(this.hillView.getKeyGenTextField().getText().toString());
		}else if(command == "Set Decrypt key") {
			this.hillView.setdKeyTextField(this.hillView.getKeyGenTextField().getText().toString());
		}else if(command == "Set Plain text") {
			this.hillView.setePlainTextField(this.hillView.getdPlainTextField().toString());
		}
	}
	
}
