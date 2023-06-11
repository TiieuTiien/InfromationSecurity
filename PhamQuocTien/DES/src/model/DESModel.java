package model;

public class DESModel {
	public static void main(String[] args) {
		String keyString = "M"; // Example key string

		// Convert the key string to bytes using ASCII encoding
		byte[] keyBytes = keyString.getBytes();

		// Create a 64-bit key using the first 8 bytes
		long encryptionKey = 0;
		
		int characters = keyString.length();
		
		for (int i = 0; i < characters; i++) {
			encryptionKey <<= 8; // Shift the existing bits to the left
			encryptionKey |= (keyBytes[i] & 0xFF); // Set the least significant byte
		}

		System.out.println("Encryption Key: " + encryptionKey);
	}

}
