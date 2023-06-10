package main;

import java.math.BigInteger;

import model.RSAModel;

public class Main {
	public static void main(String[] args) {
    	String plainText = "abcdefghijkladsfasdfasdfasdfasmn";
		int digits = (int) (plainText.length() * 1.6);
    	RSAModel rsaModel = new RSAModel(digits);

    	BigInteger p = rsaModel.getP();
    	BigInteger q = rsaModel.getQ();
    	BigInteger e = rsaModel.getE();
    	BigInteger d = rsaModel.getD();
    	BigInteger n = rsaModel.getN();
    	
    	// Encryption
    	String cipherText = rsaModel.encrypt(plainText);
    	
    	// Decryption
    	String message = rsaModel.decryptArray(cipherText);

    	System.out.println("P: " + p + "\nQ: " + q + "\nE: " + e + "\nD: " + d + "\nN: " + n);
    	System.out.println("Cipher text: " + cipherText);
    	
    	System.out.println("Plaintext: " + plainText);
    	System.out.println("Message  : " + rsaModel.convertToAscii(message));
	}
}
