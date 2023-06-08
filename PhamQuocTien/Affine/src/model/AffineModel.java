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

	public String encryptMessage(char[] msg) {
		String cipher = "";
		for (int i = 0; i < msg.length; i++) {
			msg[i] = Character.toUpperCase(msg[i]);
		}
		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != ' ') {
				cipher = cipher + (char) ((((this.a * (msg[i] - 'A')) + this.b) % 26) + 'A');
			} else {
				cipher += msg[i];
			}
		}
		return cipher;
	}

	public String decryptCipher(String cipher) {
		String msg = "";
		int a_inv = 0;
		int flag = 0;

		// Find a^-1 (the multiplicative inverse of a in the group of integers modulo m)
		for (int i = 0; i < 26; i++) {
			flag = (a * i) % 26;

			if (flag == 1) {
				a_inv = i;
				break;
			}
		}
		
		cipher.toUpperCase();
		
		for (int i = 0; i < cipher.length(); i++) {
			if (cipher.charAt(i) != ' ') {
				msg = msg + (char) (((a_inv * ((cipher.charAt(i) + 'A' - this.b)) % 26)) + 'A');
			} else {
				msg += cipher.charAt(i);
			}
		}

		return msg;
	}
}
