package model;

public class MatrixInverse {

	public static int[][] invertMatrix(int[][] matrix) {
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
			throw new ArithmeticException("Matrix is not invertible.");
		}

		// Calculate adjoint matrix (transpose of the matrix of cofactors)
		int[][] adjoint = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				adjoint[j][i] = mod(cofactors[i][j]);
			}
		}

		return adjoint;
	}

	public static int mod(int a) {
		return (a % 26 + 26) % 26;
	}

	public static int getCofactor(int[][] matrix, int row, int col) {
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

	public static int getDeterminant(int[][] matrix) {
		int determinant = 0;

		if (matrix.length == 2) {
			determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
		} else {
			determinant += matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]);
			determinant -= matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
			determinant += matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
		}

		return determinant;
	}

	public static void main(String[] args) {
		System.out.println(mod(466) + " " + mod(220) + " " + mod(258));
	}
}
