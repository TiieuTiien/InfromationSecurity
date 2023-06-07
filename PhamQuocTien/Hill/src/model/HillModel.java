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
	static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				
				cipherMatrix[i][j] = 0;
				
				for (int x = 0; x < 3; x++) {
					cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
				}
				
				cipherMatrix[i][j] %= 26;
			}
		}
	}

	// Function to implement Hill Cipher
	public void HillCipher() {
		// Get key matrix from the key string
		int[][] keyMatrix = new int[3][3];
		getKeyMatrix(key, keyMatrix);

		// Generate vector for the message
		int[][] messageVector = new int[3][1];
		
		for(int i = 0; i < 3; i++)
			messageVector[i][0] = message.charAt(i) % 65;

		// Generates the cipherMatrix
		int[][] cipherMatrix = new int[3][1];
		
		encrypt(cipherMatrix, keyMatrix, messageVector);

		// Generate the encrypted text from the encrypted vector
		String CipherText = "";
		
		for (int i = 0; i < 3; i++) {
			CipherText += (char) (cipherMatrix[i][0] + 65);
		}

		// Finally print the ciphertext
		System.out.print(" Ciphertext:" + CipherText);
	}
}
