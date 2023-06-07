package model;

class AfflineModel {

	// Key values of a and b
	static int a = 17;
	static int b = 20;

	static String encryptMessage(char[] msg) {
		/// Cipher Text initially empty
		String cipher = "";
		for (int i = 0; i < msg.length; i++) {
			// Avoid space to be encrypted
			if (msg[i] != ' ') {
				cipher = cipher + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
			} else // else simply append space character
			{
				cipher += msg[i];
			}
		}
		return cipher;
	}

	static String decryptCipher(String cipher) {
		String msg = "";
		int a_inv = 0;
		int flag = 0;

		// Find a^-1 (the multiplicative inverse of a in the group of integers modulo m.)
		for (int i = 0; i < 26; i++) {
			flag = (a * i) % 26;

			if (flag == 1) {
				a_inv = i;
				break;
			}
		}
		for (int i = 0; i < cipher.length(); i++) {
			if (cipher.charAt(i) != ' ') {
				msg = msg + (char) (((a_inv * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
			} else {
				msg += cipher.charAt(i);
			}
		}

		return msg;
	}

	// Driver code
	public static void main(String[] args) {
		String msg = "AFFINE CIPHER";
		msg = msg.toUpperCase();
		// Calling encryption function
		String cipherText = encryptMessage(msg.toCharArray());
		System.out.println("Encrypted Message is : " + cipherText);

		// Calling Decryption function
		System.out.println("Decrypted Message is : " + decryptCipher(cipherText));

	}
}
