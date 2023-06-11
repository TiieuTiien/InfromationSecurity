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
