package model;

public class AffineModel {
	String msg;
	int a;
	int b;

	public AffineModel() {
		this.a = aGen();
		this.b = bGen();
	}

	public AffineModel(String msg, int a, int b) {
		this.msg = msg;
		this.a = a;
		this.b = b;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int gcdWith26(int a) {
		if (a <= 1) {
			return 0;
		}

		int b = 26;
		int temp;
		while (a != 0) {
			temp = a;
			a = b % a;
			b = temp;
		}

		return b;
	}

	public int aGen() {
		int a = 0;
		do {
			a = (int) (Math.random() * 24) + 2;
		} while (gcdWith26(a) != 1);
		return a;
	}

	public int bGen() {
		return (int) (Math.random() * 26);
	}

	private int modularInverse(int a, int m) {
		for (int x = 1; x < m; x++) {
			if ((a * x) % m == 1) {
				return x;
			}
		}
		return -1;
	}

	public String encryptMessage(char[] msg) {
		String cipher = "";
		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != ' ') {
				if (Character.isUpperCase(msg[i]))
					cipher = cipher + (char) ((((this.a * (msg[i] - 'A')) + this.b) % 26) + 'A');
				else
					cipher = cipher + (char) ((((this.a * (msg[i] - 'a')) + this.b) % 26) + 'a');
			} else {
				cipher += msg[i];
			}
		}
		return cipher;
	}

	public String decrypt(String ciphertext) {
		StringBuilder decryptedText = new StringBuilder();
		int m = 26; // Number of letters in the alphabet

		// Modular multiplicative inverse of 'a' modulo 26
		int aInverse = modularInverse(a, m);

		if (aInverse == -1) {
			throw new IllegalArgumentException("'a' must be coprime with 26.");
		}

		for (char c : ciphertext.toCharArray()) {
			if (Character.isLetter(c)) {
				if (Character.isUpperCase(c)) {
					int letterValue = c - 'A';
					int p = (aInverse * (letterValue - b + m)) % m;
					decryptedText.append((char) (p + 'A'));
				} else if (Character.isLowerCase(c)) {
					int letterValue = c - 'a';
					int p = (aInverse * (letterValue - b + m)) % m;
					decryptedText.append((char) (p + 'a'));
				}
			} else{
				decryptedText.append(c);
			}
		}

		return decryptedText.toString();
	}

	public static void main(String[] args) {
		AffineModel affineModel = new AffineModel();

		String message = "Hello World!";
		char[] messageChar = message.toCharArray();

		String cipherText = affineModel.encryptMessage(messageChar);

		String plainText = affineModel.decrypt(cipherText);

		System.out.println("Cipher :" + cipherText);
		System.out.println("Plain  :" + plainText);
	}
}
