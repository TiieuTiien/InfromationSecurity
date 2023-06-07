package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CeasarView;

public class CeasarListener implements ActionListener{
	private CeasarView ceasarView;

	public CeasarListener(CeasarView ceasarView) {
		this.ceasarView = ceasarView;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().toString();
		if(command == "Generate") {
			String shiftKey = this.ceasarView.generate();
			this.ceasarView.setShiftKeyTextField(shiftKey);
		}else if(command == "Encrypt") {
			this.ceasarView.seteCipherTextField(this.ceasarView.encrypt());
		}else if(command == "Decrypt") {
			this.ceasarView.setdPlainTextField(this.ceasarView.decrypt());
		}else if(command == "Set") {
			this.ceasarView.setdCipherText(this.ceasarView.set());
		}
	}
	
}
