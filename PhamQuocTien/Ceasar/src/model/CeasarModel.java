package model;

public class CeasarModel {
	public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	
	private String message;
	private int shiftKey;
	
	public CeasarModel(String message, int shiftKey) {
		this.setMessage(message);
		this.setShiftKey(shiftKey);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getShiftKey() {
		return shiftKey;
	}

	public void setShiftKey(int shiftKey) {
		this.shiftKey = shiftKey;
	}

	public String encrypt(String message, int shiftKey) {
		
		message = message.toLowerCase();
		
		String cipherText = "";
		for(int i = 0; i < message.length(); i++) {
			int charPos = ALPHA.indexOf(message.charAt(i));
			int keyVal = (shiftKey + charPos) % 26;
			char replaceVal = ALPHA.charAt(keyVal);
			cipherText += replaceVal;
		}
		
		return cipherText;
	}
	
	public String decrypt(String cipherText, int shiftKey) {
		
		cipherText = cipherText.toLowerCase();
		
		String message = "";
		for(int i = 0; i < cipherText.length(); i++) {
			int charPos = ALPHA.indexOf(cipherText.charAt(i));
			int keyVal = (charPos - shiftKey) % 26;
			
			if(keyVal < 0) {
				keyVal += 26;
			}
			
			char replaceVal = ALPHA.charAt(keyVal);
			message += replaceVal;
		}
		
		return message;
	}
}
