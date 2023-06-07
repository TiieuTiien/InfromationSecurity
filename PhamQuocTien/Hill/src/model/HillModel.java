package model;

public class HillModel {

	private String message;
	private String key;

	public HillModel(String message, String key) {
		this.message = message;
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	// Following function generates the key matrix for the key string
	static void getKeyMatrix(String key, int keyMatrix[][]) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				keyMatrix[i][j] = key.charAt(k) % 65;
				k++;
			}
		}
	}

	// Following function encrypts the message
	static String encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {

		for (int i = 0; i < 3; i++) {

			cipherMatrix[i][0] = 0;

			for (int x = 0; x < 3; x++) {
				cipherMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
			}

			cipherMatrix[i][0] %= 26;

		}

		String CipherText = "";

		for (int i = 0; i < 3; i++) {
			CipherText += (char) (cipherMatrix[i][0] + 65);
		}

		return CipherText;
	}

	// Decode
	public static int findInverse(int a) {
		int r0 = 101, r1 = a, r2 = 1, t0 = 0, t1 = 1, q = r0 / r1;
		while (r1 > 0) {
			r2 = r0 % r1;
			if(r2==0)
				break;
			q = r0 / r1;
			t1 = q - t0 * t1;
			r0=r1;
			r1=r2;
			t0=t1;
		}
		sysou
		if(r1 > 1)
			return -1;
		else if (t1 > 0)
			return t1;
		else
			return t1+26;
	}

	// Function to implement Hill Cipher
	public void HillCipher() {
		// Get key matrix from the key string
//		int[][] keyMatrix = new int[3][3];
//		getKeyMatrix(key, keyMatrix);
//
//		// Generate vector for the message
//		int[][] messageVector = new int[3][1];
//
//		for (int i = 0; i < 3; i++)
//			messageVector[i][0] = message.charAt(i) % 65;
//
//		// Generates the cipherMatrix
//		int[][] cipherMatrix = new int[3][1];
//
//		// Encrypting
//		String CipherText = encrypt(cipherMatrix, keyMatrix, messageVector);
//
//		// Finally print the ciphertext
//		System.out.print(" Ciphertext:" + CipherText);
	}

	public static void main(String[] args) {

		System.out.println(findInverse(25));
	}
}
