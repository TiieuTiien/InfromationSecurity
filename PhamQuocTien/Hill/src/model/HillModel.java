package model;

import java.util.Random;

public class HillModel {

	private String message;
	private String key;

	public HillModel() {
	}
	
	public HillModel(String key, String message) {
		this.key = key;
		this.message = message;
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
	public int[][] getKeyMatrix(String key) {
		key.toUpperCase();
		
		int keyMatrix[][] = new int[3][3];
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				keyMatrix[i][j] = key.charAt(k) % 65;
				k++;
			}
		}
		return keyMatrix;
	}

	public static int mod(int a, int n) {
		return (a % n + n) % n;
	}

	public int[][] invertMatrix(int[][] matrix) {
		int[][] cofactors = new int[3][3];

		// Calculate matrix of cofactors
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cofactors[i][j] = getCofactor(matrix, i, j);
			}
		}

		// Calculate determinant of the original matrix
		int determinant = getDeterminant(matrix);

		if (determinant == 0) {
			throw new ArithmeticException("Matrix is not invertible. Det: " + determinant);
		}

		int inverseNum = findInverse(26, determinant);

		// Calculate adjoint matrix (transpose of the matrix of cofactors)
		int[][] adjoint = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				adjoint[j][i] = mod(mod(cofactors[i][j], 26) * inverseNum, 26);
			}
		}

		return adjoint;
	}

	public int[][] matrixMultiply(int messageVector[][], int inverseVector[][]) {
		int row = messageVector.length;
		int col = inverseVector[0].length;

		int[][] result = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < inverseVector.length; k++)
					result[i][j] += messageVector[i][k] * inverseVector[k][j];
				result[i][j] %= 26;
			}
		}

		return result;
	}

	public static int findInverse(int modulus, int number) {
		int r0 = modulus, r1 = number, s0 = 1, s1 = 0, t0 = 0, t1 = 1;

		while (r1 != 0) {
			int quotient = r0 / r1;
			int remainder = r0 % r1;

			int s2 = s0 - quotient * s1;
			int t2 = t0 - quotient * t1;

			r0 = r1;
			r1 = remainder;
			s0 = s1;
			s1 = s2;
			t0 = t1;
			t1 = t2;
		}

		if (r0 != 1) {
			return -1;
		}

		// Adjusting the modular multiplicative inverse to be positive
		int inverse = (t0 + modulus) % modulus;

		return inverse;
	}

	public static int getDeterminant(int[][] matrix) {
		int determinant = 0;

		if (matrix.length == 2) {
			determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
		} else {
			determinant += matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]);
			determinant -= matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
			determinant += matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
		}

		return mod(determinant, 26);
	}

	public int getCofactor(int[][] matrix, int row, int col) {
		int[][] submatrix = new int[2][2];
		int subRow = 0;
		int subCol;

		for (int i = 0; i < 3; i++) {
			if (i == row)
				continue;

			subCol = 0;
			for (int j = 0; j < 3; j++) {
				if (j == col)
					continue;

				submatrix[subRow][subCol] = matrix[i][j];
				subCol++;
			}

			subRow++;
		}

		int cofactor = (int) (Math.pow(-1, row + col) * getDeterminant(submatrix));
		return cofactor;
	}

	// Following function encrypts the message
	public String encrypt() {

		// Get key matrix from the key string
		int[][] keyMatrix = getKeyMatrix(this.key);

		setMessage(message.toUpperCase());
		
		// Generate vector for the message
		int[][] messageVector = new int[1][3];
		for (int i = 0; i < 3; i++)
			messageVector[0][i] = this.message.charAt(i) % 65;

		// Generates the cipherMatrix
		int[][] cipherMatrix = matrixMultiply(messageVector, keyMatrix);

		String CipherText = "";
		for (int i = 0; i < 3; i++) {
			CipherText += (char) (cipherMatrix[0][i] + 'A');
		}

		return CipherText;
	}

	// Following function decrypts the message
	public String decrypt(String cipherText, String key) {
		
		// Get key matrix from the key string
		int[][] keyMatrix = getKeyMatrix(this.key);
		
		// Calculate inverse matrix of keyMatrix
		int[][] inverseMatrix = invertMatrix(keyMatrix);
		
		// Get cipherMatrix from CipherText
		int[][] cipherMatrix = new int[1][3];
		for (int i = 0; i < 3; i++)
			cipherMatrix[0][i] = cipherText.charAt(i) - 'A';
	
		// Decrypt cipherMatrix
		int[][] plainMatrix = matrixMultiply(cipherMatrix, inverseMatrix);
	
		String plainText = "";
		for(int i = 0; i < 3; i++) {
			plainText += (char) (plainMatrix[0][i] + 'A');
		}
		return plainText;
	}
	
    public static int[][] generateKeyMatrix(int N) {
        Random random = new Random();
        int[][] key = new int[N][N];

        do {
            // Generate random key values
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    key[i][j] = random.nextInt(26);  // Generate values between 0 and 25
                }
            }
        } while (findInverse(getDeterminant(key), 26) == -1);
        
        return key;
    }
    
    public String keyGen() {
    	int[][] matrix = generateKeyMatrix(3);
    	String key = "";

    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix[i].length; j++) {
    			key += (char)(matrix[i][j] + 'A');
    		}
    	}
    	
    	return key;
    }
    
    public static void printMatrix(int matrix[][]) {
    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix[i].length; j++) {
    			System.out.print(matrix[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    public static void printMatrixChar(int matrix[][]) {
    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix[i].length; j++) {
    			System.out.print((char)(matrix[i][j] + 'A') + " ");
    		}
    		System.out.println();
    	}
    }

	public static void main(String[] args) {

		HillModel hill = new HillModel("RRFVSVCCT", "mor");

		// Text
		System.out.println("Plaintext: " + hill.message);

		// Encrypting
		String cipherText = hill.encrypt();

		// Finally print the ciphertext
		System.out.print("Ciphertext:" + cipherText);
		
		// Decrypting
		String PlainText = hill.decrypt(cipherText, hill.key);
		
		System.out.println("\nPlaintext: "+PlainText);
		
		int[][] matrix = generateKeyMatrix(3);
		
		printMatrixChar(matrix);
		
		int det = getDeterminant(matrix);
		System.out.println("Det: " + det);
		System.out.println("Invs: " + findInverse(det, 26));
	}
}
