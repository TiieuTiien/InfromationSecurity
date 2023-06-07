package main;

public class MainInverse {
	public static void main(String[] args) {
		int[][] keyMatrix = { { 6, 24, 1 }, { 13, 16, 10 }, { 20, 17, 15 } };

		int[][] encryptedMessage = { { 18 }, { 17 }, { 17 } };

		int[][] decryptedMessage = decryptHillCipher(encryptedMessage, keyMatrix);

		// Print the decrypted message
		for (int i = 0; i < decryptedMessage.length; i++) {
			for (int j = 0; j < decryptedMessage[i].length; j++) {
				System.out.print((char) (decryptedMessage[i][j] + 65));
			}
		}
		System.out.println();
	}

	public static int[][] decryptHillCipher(int[][] encryptedMessage, int[][] keyMatrix) {
		int[][] inverseKeyMatrix = invertMatrix(keyMatrix);

		// Check if the inverse matrix exists
		if (inverseKeyMatrix == null) {
			throw new IllegalArgumentException("Invalid key matrix. Inverse does not exist.");
		}

		int[][] decryptedMessage = multiplyMatrices(inverseKeyMatrix, encryptedMessage);

		return decryptedMessage;
	}

	public static int[][] invertMatrix(int[][] matrix) {
		int determinant = calculateDeterminant(matrix);
		int[][] adjugate = calculateAdjugate(matrix);

		// Check if the matrix is invertible
		if (determinant == 0) {
			return null;
		}

		int[][] inverse = new int[matrix.length][matrix[0].length];
		int modInverse = calculateModularInverse(determinant);

		// Calculate the inverse matrix by multiplying the adjugate by the modular
		// inverse of the determinant
		for (int i = 0; i < inverse.length; i++) {
			for (int j = 0; j < inverse[i].length; j++) {
				inverse[i][j] = (adjugate[i][j] * modInverse) % 26; // Assuming modulo 26 arithmetic for decryption
			}
		}

		return inverse;
	}

	public static int calculateDeterminant(int[][] matrix) {
		int a = matrix[0][0];
		int b = matrix[0][1];
		int c = matrix[0][2];
		int d = matrix[1][0];
		int e = matrix[1][1];
		int f = matrix[1][2];
		int g = matrix[2][0];
		int h = matrix[2][1];
		int i = matrix[2][2];

		return (a * e * i) + (b * f * g) + (c * d * h) - (c * e * g) - (b * d * i) - (a * f * h);
	}

	public static int[][] calculateAdjugate(int[][] matrix) {
		int[][] adjugate = new int[matrix.length][matrix[0].length];

		adjugate[0][0] = matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1];
		adjugate[0][1] = -(matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
		adjugate[0][2] = matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0];
		adjugate[1][0] = -(matrix[0][1] * matrix[2][2] - matrix[0][2] * matrix[2][1]);
		adjugate[1][1] = matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0];
		adjugate[1][2] = -(matrix[0][0] * matrix[2][1] - matrix[0][1] * matrix[2][0]);
		adjugate[2][0] = matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1];
		adjugate[2][1] = -(matrix[0][0] * matrix[1][2] - matrix[0][2] * matrix[1][0]);
		adjugate[2][2] = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

		return adjugate;
	}

	public static int calculateModularInverse(int num) {
		num %= 26; // Assuming modulo 26 arithmetic for decryption

		for (int i = 1; i < 26; i++) {
			if ((num * i) % 26 == 1) {
				return i;
			}
		}

		return 0; // No modular inverse exists
	}

	public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
		int rows1 = matrix1.length;
		int cols1 = matrix1[0].length;
		int rows2 = matrix2.length;
		int cols2 = matrix2[0].length;

		// Check if matrices can be multiplied
		if (cols1 != rows2) {
			throw new IllegalArgumentException("Matrices cannot be multiplied");
		}

		int[][] result = new int[rows1][cols2];

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				for (int k = 0; k < cols1; k++) {
					result[i][j] += matrix1[i][k] * matrix2[k][j];
				}
				result[i][j] %= 26; // Assuming modulo 26 arithmetic for decryption
			}
		}

		return result;
	}
}
