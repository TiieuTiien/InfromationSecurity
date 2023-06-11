package model;

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


	public static void main(String[] args) {
		String keyString = "MyKey123"; // Example key string

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
    private static long permutation(long value, int[] permutationTable) {
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
            roundKeys[i] = permutation(combinedCD, PC2_TABLE);
        }

        return roundKeys;
    }
}
