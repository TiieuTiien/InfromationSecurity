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
			this.affineView.seteKeyATextField(a);
			this.affineView.seteKeyBTextField(b);
			this.affineView.setdKeyATextField(a);
			this.affineView.setdKeyBTextField(b);
		} else if (command == "Encrypt") {
			this.affineView.seteCipherTextField(this.affineView.encrypt());
		} else if (command == "Decrypt") {
			this.affineView.setdPlainTextField(this.affineView.decrypt());
		}

	}

}
