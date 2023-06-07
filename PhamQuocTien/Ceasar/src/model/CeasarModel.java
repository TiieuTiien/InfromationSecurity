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

		String cipherText = "";
		char[] charMessage = message.toCharArray();
		
		for (char ch : charMessage) {
			if(Character.isLowerCase(ch))
				cipherText += (char) ((ch - 'a' + shiftKey) % 26 + 'a');
			else if(Character.isUpperCase(ch))
				cipherText += (char) ((ch + shiftKey - 'A') % 26 + 'A');
			else 
				cipherText += ch;
		}

		return cipherText;
	}

	public String decrypt(String cipherText) {
		
		String message = "";
		char[] charMessage = cipherText.toCharArray();
		
		for (char ch : charMessage) {
			if (Character.isUpperCase(ch))
				message += (char) ((ch - 'A' - shiftKey + 26) % 26 + 'A');
			else if (Character.isLowerCase(ch))
				message += (char) ((ch - 'a' - shiftKey + 26) % 26 + 'a');
			else
				message += ch;
		}

		return message;
	}
}
