package main;

public class Main {
	static void getKeyMatrix(String key, int keyMatrix[][]) {
		int k = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				keyMatrix[i][j] = (key.charAt(k)) % 65;
				k++;
			}
		}
	}
	
	static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
		int x, i, j;
		
		for	(i = 0; i < 3; i++) {
			for(j = 0; j < 3; j++) {
				cipherMatrix[i][j] = 0;
				
				for(x = 0; x < 3; x++) {
					cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
				}
				
				cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
			}
		}
	}
}
