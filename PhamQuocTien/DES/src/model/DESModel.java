package model;

import java.util.Random;

public class DESModel {
	// PC-1 table for key permutation
    private static final int[] PC1_TABLE = {
        57, 49, 41, 33, 25, 17, 9,  1,
        58, 50, 42, 34, 26, 18, 10, 2,
        59, 51, 43, 35, 27, 19, 11, 3,
        60, 52, 44, 36, 63, 55, 47, 39,
        31, 23, 15, 7,  62, 54, 46, 38,
        30, 22, 14, 6,  61, 53, 45, 37,
        29, 21, 13, 5,  28, 20, 12, 4
    };

    // PC-2 table for key permutation
    private static final int[] PC2_TABLE = {
        14, 17, 11, 24, 1,  5,  3,  28,
        15, 6,  21, 10, 23, 19, 12, 4,
        26, 8,  16, 7,  27, 20, 13, 2,
        41, 52, 31, 37, 47, 55, 30, 40,
        51, 45, 33, 48, 44, 49, 39, 56,
        34, 53, 46, 42, 50, 36, 29, 32
    };
    
    // Define the IP table as a global constant variable
    private static final int[] IPTable = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };
    
    public static final int[][] SBox1 = {
        { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
        { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
        { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
        { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
    };

    public static final int[][] SBox2 = {
        { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
        { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
        { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
        { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
    };

    public static final int[][] SBox3 = {
        { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
        { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
        { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
        { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
    };

    public static final int[][] SBox4 = {
        { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
        { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
        { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
        { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
    };

    public static final int[][] SBox5 = {
        { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
        { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
        { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
        { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
    };

    public static final int[][] SBox6 = {
        { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
        { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
        { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 13, 1, 11, 6 },
        { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
    };

    public static final int[][] SBox7 = {
        { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
        { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
        { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
        { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
    };

    public static final int[][] SBox8 = {
        { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
        { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
        { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
        { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
    };

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }


	public static void main(String[] args) {
		
		String keyString = generateRandomString(8); // Random key string

		// Convert the key string to a 64-bit key
		long key64Bit = convertKeyTo64Bit(keyString);

		// Apply PC-1 permutation to the 64-bit key
		long permutedKey56Bit = permuteKey64(key64Bit, PC1_TABLE);

		System.out.println("Original Key	   : " + keyString);
		System.out.println("64-bit Key	   : " + key64Bit);
		System.out.println("Permuted 56-bit Key: " + permutedKey56Bit);

		// Divide the 56-bit key into two 28-bit halves
		long[] keyHalves = divideKeyIntoHalves(permutedKey56Bit);

		long C0 = keyHalves[0];
		long D0 = keyHalves[1];

		System.out.println("Permuted 56-bit Key: " + Long.toHexString(permutedKey56Bit));
		System.out.println("C0: " + Long.toHexString(C0));
		System.out.println("D0: " + Long.toHexString(D0));
		

        // Perform round key generation
        long[] roundKeys = generateRoundKeys(C0, D0);

//        System.out.println("Round Keys:");
//        for (int i = 0; i < roundKeys.length; i++) {
//            System.out.println("Round " + (i + 1) + ": " + Long.toHexString(roundKeys[i]));
//        }
        

        String input = generateRandomString(8); // Random input string
        
        System.out.println("Random String      : " + input);
        
        long plaintextBlock = stringToPlaintextBlock(input);
        
        long permutedData = initialPermutation(plaintextBlock);
        
        System.out.println("Permuted data      : " + Long.toHexString(permutedData));
        
	}

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
	private static long permuteKey64(long key, int[] permutationTable) {
		long permutedKey = 0;
		for (int i = 0; i < permutationTable.length; i++) {
			int bitPosition = 63 - permutationTable[i];
			long bitValue = (key >> bitPosition) & 1;
			permutedKey |= (bitValue << (55 - i));
		}
		return permutedKey;
	}

    // Helper method to perform permutation using a specified permutation table
    private static long permuteKey56(long value, int[] permutationTable) {
        long permutedValue = 0;
        for (int i = 0; i < permutationTable.length; i++) {
            int bitPosition = 56 - permutationTable[i];
            long bitValue = (value >> bitPosition) & 1;
            permutedValue |= (bitValue << (55 - i));
        }
        return permutedValue;
    }

	// Divide a 56-bit key into two 28-bit halves
	private static long[] divideKeyIntoHalves(long key56Bit) {
		long[] keyHalves = new long[2];

		long C0 = key56Bit >>> 28; // Shift right by 28 bits
		long D0 = key56Bit & 0xFFFFFFF; // Mask with 28 bits (0xFFFFFFF is 28 bits set to 1)

		keyHalves[0] = C0;
		keyHalves[1] = D0;

		return keyHalves;
	}

	// STEP 2: Round Key Generation
    // Helper method for circular left shift
    private static long circularLeftShift(long value, int shifts) {
        return ((value << shifts) | (value >>> (28 - shifts))) & 0xFFFFFFF; // Mask with 28 bits
    }

    // Perform round key generation
    private static long[] generateRoundKeys(long C0, long D0) {
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
    //^
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
    private static long initialPermutation(long input) {
        long permutedData = 0;

        for (int i = 0; i < IPTable.length; i++) {
            int bitPosition = IPTable[i] - 1;
            long bitMask = 1L << (63 - bitPosition);
            long extractedBit = (input & bitMask) >>> (63 - bitPosition);
            permutedData |= (extractedBit << (63 - i));
        }

        return permutedData;
    }
    
    private static long stringToPlaintextBlock(String input) {
        // Pad the string with spaces if needed
        while (input.length() < 8) {
            input += " ";
        }

        // Take the first 8 characters and convert them to a 64-bit block
        String substring = input.substring(0, 8);
        byte[] bytes = substring.getBytes();
        long plaintextBlock = 0;

        for (int i = 0; i < 8; i++) {
            plaintextBlock |= ((long) bytes[i] & 0xFF) << (56 - (i * 8));
        }

        return plaintextBlock;
    }
}
