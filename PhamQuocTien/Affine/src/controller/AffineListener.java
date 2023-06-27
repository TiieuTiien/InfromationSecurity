package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AffineView;

public class AffineListener implements ActionListener {
	private AffineView affineView;

	public AffineListener(AffineView affineView) {
		this.affineView = affineView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().toString();
		if (command == "Generate") {
			String a = Integer.toString(this.affineView.genarateA());
			String b = Integer.toString(this.affineView.getB());
			this.affineView.setaTextField(a);
			this.affineView.setbTextField(b);
		} else if (command == "Encrypt") {
			
			String a = this.affineView.geteKeyATextField().getText().toString();
			String b = this.affineView.geteKeyBTextField().getText().toString();
			
			if (a.length() == 0 || b.length() == 0)
				this.affineView.seteCipherTextField("Nhap key !");
			else
				this.affineView.seteCipherTextField(this.affineView.encrypt());
		} else if (command == "Decrypt") {
			
			String a = this.affineView.getdKeyATextField().getText().toString();
			String b = this.affineView.getdKeyBTextField().getText().toString();
			
			if (a.length() == 0 || b.length() == 0)
				this.affineView.setdPlainTextField("Nhap key !");
			else
				this.affineView.setdPlainTextField(this.affineView.decrypt());
		} else if (command == "Set Encrypt key") {
			this.affineView.seteKeyATextField(this.affineView.getaTextField().getText().toString());
			this.affineView.seteKeyBTextField(this.affineView.getbTextField().getText().toString());
		} else if (command == "Set Decrypt key") {
			this.affineView.setdKeyATextField(this.affineView.getaTextField().getText().toString());
			this.affineView.setdKeyBTextField(this.affineView.getbTextField().getText().toString());
		} else if (command == "Set Cipher Text") {
			this.affineView.setdCipherTextField(this.affineView.geteCipherTextField().getText().toString());
		} else if (command == "Set Plain Text") {
			this.affineView.setePlainTextField(this.affineView.getdPlainTextField().getText().toString());
		}

	}

}
