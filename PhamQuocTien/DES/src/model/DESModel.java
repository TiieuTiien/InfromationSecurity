package model;

public class DESModel {
	public static void main(String[] args) {
        String keyString = "MyKey123"; // Example key string

        // Convert the key string to bytes using ASCII encoding
        byte[] keyBytes = keyString.getBytes();

        // Create a 64-bit key using the first 8 bytes
        long encryptionKey = bytesToLong(keyBytes, 0, 8);

        System.out.println("Encryption Key: " + encryptionKey);
    }

    // Helper method to convert a byte array to a long value
    private static long bytesToLong(byte[] bytes, int offset, int length) {
        long result = 0;
        for (int i = offset; i < offset + length; i++) {
            result <<= 8; // Shift the existing bits to the left
            result |= (bytes[i] & 0xFF); // Set the least significant byte
        }
        return result;
    }
}
