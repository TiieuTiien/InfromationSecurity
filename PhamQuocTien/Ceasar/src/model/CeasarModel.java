package model;

public class CeasarModel {
	public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	private int shiftKey;
	
	

	public CeasarModel() {
		this.shiftKey = shiftKeyGen();
	}

	public int getShiftKey() {
		return shiftKey;
	}

	public void setShiftKey(int shiftKey) {
		this.shiftKey = shiftKey;
	}

	public int shiftKeyGen() {
		return (int) (Math.random() * 26);
	}

	public String encrypt(String message) {

		message = message.toLowerCase();

		String cipherText = "";
		for (int i = 0; i < message.length(); i++) {
			int charPos = ALPHA.indexOf(message.charAt(i));
			int keyVal = (shiftKey + charPos) % 26;
			char replaceVal = ALPHA.charAt(keyVal);
			cipherText += replaceVal;
		}

		return cipherText;
	}

	public String decrypt(String cipherText) {

		cipherText = cipherText.toUpperCase();

		String message = "";
		for (int i = 0; i < cipherText.length(); i++) {
			int charPos = ALPHA.indexOf(cipherText.charAt(i));
			int keyVal = (charPos - shiftKey) % 26;

			if (keyVal < 0) {
				keyVal += 26;
			}

			char replaceVal = ALPHA.charAt(keyVal);
			message += replaceVal;
		}

		return message;
	}
}
