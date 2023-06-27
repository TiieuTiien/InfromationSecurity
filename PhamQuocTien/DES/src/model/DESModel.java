package model;

public class DESModel {
<<<<<<< HEAD
	
	private String keyString;
	private long[] roundKeys;
	
	public DESModel() {
		
	}
	
	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public long[] getRoundKeys() {
		return roundKeys;
	}

	public void setRoundKeys(long[] roundKeys) {
		this.roundKeys = roundKeys;
	}

	// PC-1 table for key permutation
	private static final int[] PC1_TABLE = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43,
			35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45,
			37, 29, 21, 13, 5, 28, 20, 12, 4 };

	// PC-2 table for key permutation
	private static final int[] PC2_TABLE = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7,
			27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
			32 };

	// P-table for permutation
	private static final int[] P_TABLE = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14,
			32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };

	// Define the IP table as a global constant variable
	private static final int[] IPTable = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38,
			30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
			61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

	// Define the IP^-1 table as a global constant variable
	public static final int[] IP_INVERSE_TABLE = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6,
			46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19,
			59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };

	public static final int[] E_TABLE = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16,
			17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };

	// S-boxes
	private static final int[][] S_BOXES = {
			// S1
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3,
					8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10,
					0, 6, 13 },
			// S2
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
					5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
					5, 14, 9 },
			// S3
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15,
					1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11,
					5, 2, 12 },
			// S4
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
					9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12,
					7, 2, 14 },
			// S5
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
					6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
					10, 4, 5, 3 },
			// S6
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
					8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6,
					0, 8, 13 },
			// S7
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
					6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14,
					2, 3, 12 },
			// S8
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
					2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3,
					5, 6, 11 } };

//    private static String generateRandomString(int length) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        StringBuilder sb = new StringBuilder();
//
//        Random random = new Random();
//        for (int i = 0; i < length; i++) {
//            int randomIndex = random.nextInt(characters.length());
//            char randomChar = characters.charAt(randomIndex);
//            sb.append(randomChar);
//        }
//
//        return sb.toString();
//    }

	// Convert key string to a 64-bit key
	private static long convertKeyTo64Bit(String keyString) {
		byte[] keyBytes = keyString.getBytes();
		long key64Bit = 0;
		for (int i = 0; i < 8; i++) {
			key64Bit <<= 8;
			key64Bit |= (keyBytes[i] & 0xFF);
		}
		return key64Bit;
	}

	// STEP 1: Key Generation
	// Helper method to permute the key using a specified permutation table
	private long permuteKey64(long key, int[] permutationTable) {
		long permutedKey = 0;
		for (int i = 0; i < permutationTable.length; i++) {
			int bitPosition = 63 - permutationTable[i];
			long bitValue = (key >> bitPosition) & 1;
			permutedKey |= (bitValue << (55 - i));
		}
		return permutedKey;
	}

	// Helper method to perform permutation using a specified permutation table
	private long permuteKey56(long value, int[] permutationTable) {
		long permutedValue = 0;
		for (int i = 0; i < permutationTable.length; i++) {
			int bitPosition = 56 - permutationTable[i];
			long bitValue = (value >> bitPosition) & 1;
			permutedValue |= (bitValue << (47 - i));
		}
		return permutedValue;
	}

	// Divide a 56-bit key into two 28-bit halves
	private long[] divideKeyIntoHalves(long key56Bit) {
		long[] keyHalves = new long[2];

		long C0 = key56Bit >>> 28; // Shift right by 28 bits
		long D0 = key56Bit & 0xFFFFFFF; // Mask with 28 bits (0xFFFFFFF is 28 bits set to 1)

		keyHalves[0] = C0;
		keyHalves[1] = D0;

		return keyHalves;
	}

	// STEP 2: Round Key Generation
	// Helper method for circular left shift
	private long circularLeftShift(long value, int shifts) {
		return ((value << shifts) | (value >>> (28 - shifts))) & 0xFFFFFFF; // Mask with 28 bits
	}

	// Perform round key generation
	private long[] generateRoundKeys(long C0, long D0) {
		long[] roundKeys = new long[16];

		for (int i = 0; i < 16; i++) {
			// Apply left shift based on round number
			int leftShifts = (i == 0 || i == 1 || i == 8 || i == 15) ? 1 : 2;
			C0 = circularLeftShift(C0, leftShifts);
			D0 = circularLeftShift(D0, leftShifts);

			// Combine C0 and D0 halves
			long combinedCD = (C0 << 28) | D0;

			// Apply PC-2 permutation
			roundKeys[i] = permuteKey56(combinedCD, PC2_TABLE);
		}

		return roundKeys;
	}
	// ^
	// |
	// |
	// This is a part of this function
	// Apply PC-2 key permutation to combine C0 and D0 and generate a 48-bit subkey
//    private static long permutePC2(long C0, long D0, int[] permutationTable) {
//        long combinedKey = (C0 << 28) | D0;
//        long subkey = 0;
//
//        for (int i = 0; i < permutationTable.length; i++) {
//            int bitPosition = permutationTable[i];
//            long bitMask = 1L << (55 - bitPosition);
//            long extractedBit = (combinedKey & bitMask) >>> (55 - bitPosition);
//            subkey |= (extractedBit << (47 - i));
//        }
//
//        return subkey;
//    }

	// Initial Permutation (IP) function
	private long initialPermutation(long input) {
		long permutedData = 0;

		for (int i = 0; i < IPTable.length; i++) {
			int bitPosition = IPTable[i] - 1;
			long bitMask = 1L << (63 - bitPosition);
			long extractedBit = (input & bitMask) >>> (63 - bitPosition);
			permutedData |= (extractedBit << (63 - i));
		}

		return permutedData;
	}

	private long stringToPlaintextBlock(String input) {
		// Pad the string with spaces if needed
		while (input.length() < 8) {
			input += " ";
		}

		// Take the first 8 characters and convert them to a 64-bit block
		String substring = input.substring(0, 8);
		char[] string = substring.toCharArray();
		long plaintextBlock = 0;

		for (int i = 0; i < 8; i++) {
			plaintextBlock |= ((long) string[i] & 0xFF) << (56 - (i * 8));
		}

		return plaintextBlock;
	}

	// Expand R: Expand the 32-bit R from the previous round to 48 bits using the E
	// (Expansion) table.
	// Expand R function
	private long expandR(long input) {
		long expanded = 0;
		for (int i = 0; i < 48; i++) {
			int bitIndex = E_TABLE[i] - 1;
			long bitValue = (input & (1L << (32 - bitIndex))) >>> (32 - bitIndex);
			expanded |= bitValue << (47 - i);
		}
		return expanded;
	}

	// Substitute function
	private long substitute(String xored) {
		long substituted = 0;

		for (int i = 0; i < 8; i++) {

			int k = 6 * i;

			int row = Integer.parseInt(xored.substring(k, k + 1).concat(xored).substring(k + 5, k + 6), 2);
			int col = Integer.parseInt(xored.substring(k + 1, k + 5), 2);

//			System.out.println("Sub " + (i + 1) + " Row: " + row + " Col: " + col);
			// Perform the S-box lookup and get the substitution value
			int substitutionValue = S_BOXES[i][row * 16 + col];

			// Append the 4-bit substitution value to the output
			substituted = (substituted << 4) | substitutionValue;
		}
		return substituted;
	}

	// Permute function
	private long permute(long input) {
		long permuted = 0;
		for (int i = 0; i < 32; i++) {
			int bitIndex = P_TABLE[i] - 1;
			long bitValue = (input & (1L << (32 - bitIndex))) >>> (32 - bitIndex);
			permuted |= bitValue << (31 - i);
		}
		return permuted;
	}

	// Feistel round
	private long feistelRound(long right, long roundKeys) {

		long expanded = expandR(right);

		// Perform XOR operation
		long xorResult = expanded ^ roundKeys;

		// Convert the XOR result back to a binary string
		String xored = Long.toBinaryString(xorResult);
		String expandedString = Long.toBinaryString(expanded);
		String roundKeysString = Long.toBinaryString(roundKeys);

		int expandedLength = expandedString.length();
		int roundKeysLength = roundKeysString.length();
		int xoredLength = xored.length();

		// Pad the string with leading zeros if necessary
		if (expandedLength < 48)
			for (int i = 0; i < 48 - expandedLength; i++)
				expandedString = "0" + expandedString;

		if (roundKeysLength < 48)
			for (int i = 0; i < 48 - roundKeysLength; i++)
				roundKeysString = "0" + roundKeysString;

		if (xoredLength < 48)
			for (int i = 0; i < 48 - xoredLength; i++)
				xored = "0" + xored;

		expandedLength = expandedString.length();
		roundKeysLength = roundKeysString.length();
		xoredLength = xored.length();

//		System.out.println("Expanded : " + expandedString + " " + expandedLength);
//		System.out.println("RoundKeys: " + roundKeysString + " " + roundKeysLength);
//		System.out.println("XORed    : " + xored + " " + xoredLength);
		long substituted = substitute(xored);
		long permuted = permute(substituted);
		return permuted;
	}

	// Complete Feistel structure
	public long feistel(long permutedData, long roundKeys[]) {

		long left, right;

		// In the code below, leftHalf represents the 32-bit left half of the final
		// output (L16),
		// and rightHalf represents the 32-bit right half of the final output (R16).
		// By shifting the bits appropriately and performing a bitwise OR operation,
		// we combine the left and right halves into a single 64-bit block (R16L16).
		for (int i = 0; i < 16; i++) {
			left = permutedData >>> 32;
			right = permutedData & 0xFFFFFFFF;

			long newRight = left ^ feistelRound(right, roundKeys[i]);
			left = right;

			permutedData = (left << 32) | newRight;
		}

		// Swap the left and right halves by shifting the entire block and combining it again (L16R16).
		return (permutedData << 32) | (permutedData >>> 32);
	}

	// Complete Feistel structure
	public long feistelReverse(long permutedData, long roundKeys[]) {

		long left, right;
		
		for (int i = 15; i >= 0; i--) {
			left = permutedData >>> 32;
			right = permutedData & 0xFFFFFFFF;

			long newRight = left ^ feistelRound(right, roundKeys[i]);
			left = right;

			permutedData = (left << 32) | newRight;
		}

		// Swap the left and right halves by shifting the entire block and combining it again (L16R16).
		return (permutedData << 32) | (permutedData >>> 32);
	}

	private long IPInversePermutation(long input) {

		long permuted = 0;
		for (int i = 0; i < 64; i++) {
			int bitPosition = IP_INVERSE_TABLE[i] - 1;
			long bitValue = (input >> bitPosition) & 1L;
			permuted |= bitValue << i;
		}
		return permuted;
	}

	public long encrypt(String keyString, String plainText) {

		// Convert the key string to a 64-bit key
		long key64Bit = convertKeyTo64Bit(keyString);

		// Apply PC-1 permutation to the 64-bit key
		long permutedKey56Bit = permuteKey64(key64Bit, PC1_TABLE);

//		System.out.println("Original Key	   : " + keyString);
//		System.out.println("64-bit Key	   : " + key64Bit);
//		System.out.println("Permuted 56-bit Key: " + permutedKey56Bit);

		// Divide the 56-bit key into two 28-bit halves
		long[] keyHalves = divideKeyIntoHalves(permutedKey56Bit);

		long C0 = keyHalves[0];
		long D0 = keyHalves[1];

		// 2. Round Key Generation:
		// Perform round key generation
		setRoundKeys(generateRoundKeys(C0, D0));

		// 3. Initial Permutation (IP):
//		System.out.println("Input String       : " + plainText);
		long plaintextBlock = stringToPlaintextBlock(plainText);
		System.out.println(Long.toHexString(plaintextBlock));
		long permutedData = initialPermutation(plaintextBlock);

//		System.out.println("Permuted data      : " + Long.toHexString(permutedData));

		// 4. Feistel Structure:
		// 			&
		// 5. Round Function:
		// 			&
		// 6. Final Permutation (FP): inside the feistel
		
		long output = feistel(permutedData, this.roundKeys);
//		System.out.println("Initial Permutation (IP) After swap: " + Long.toHexString(output));

		// Apply the final permutation, which is the inverse of the initial permutation,
		// using the "IP-1" table (other name is "FP" table).
		return IPInversePermutation(output);
	}

	public long decrypt(String keyString, long cipherText) {

		// Convert the key string to a 64-bit key
		long key64Bit = convertKeyTo64Bit(keyString);

		// Apply PC-1 permutation to the 64-bit key
		long permutedKey56Bit = permuteKey64(key64Bit, PC1_TABLE);

//		System.out.println("Original Key	   : " + keyString);
//		System.out.println("64-bit Key	   : " + key64Bit);
//		System.out.println("Permuted 56-bit Key: " + permutedKey56Bit);

		// Divide the 56-bit key into two 28-bit halves
		long[] keyHalves = divideKeyIntoHalves(permutedKey56Bit);

		long C0 = keyHalves[0];
		long D0 = keyHalves[1];

		// 2. Round Key Generation:
		// Perform round key generation
		setRoundKeys(generateRoundKeys(C0, D0));

		// 3. Initial Permutation (IP):
//		System.out.println("Input String       : " + plainText);

		long permutedData = initialPermutation(cipherText);

//		System.out.println("Permuted data      : " + Long.toHexString(permutedData));

		// 4. Feistel Structure:
		// 			&
		// 5. Round Function:
		// 			&
		// 6. Final Permutation (FP): inside the feistel
		
		long output = feistelReverse(permutedData, this.roundKeys);
//		System.out.println("Initial Permutation (IP) After swap: " + Long.toHexString(output));

		// Apply the final permutation, which is the inverse of the initial permutation,
		// using the "IP-1" table (other name is "FP" table).
		return IPInversePermutation(output);
	}

	public static void main(String[] args) {

		DESModel desModel = new DESModel();
		
		String keyString = "HiCipher!";
		String message = "Crypting";

		long cipherText = desModel.encrypt(keyString, message);
		long plainText = desModel.decrypt(keyString, cipherText);

		System.out.println("Key         : " + keyString);
		System.out.println("Message     : " + message);
		System.out.println("Cipher text : " + Long.toHexString(cipherText));
		System.out.println("Plain Text  : " + Long.toHexString(plainText));
	}
=======
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

>>>>>>> 5ce87d9574fe715cc5a838cb76e2f689c02e6243
}
