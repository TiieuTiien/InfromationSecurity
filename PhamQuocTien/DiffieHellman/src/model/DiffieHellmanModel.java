package model;

import java.math.BigInteger;
import java.util.Random;

public class DiffieHellmanModel {

	private BigInteger base;
	private BigInteger modulus; // public for the third-side party
	private BigInteger privateKeyA;
	private BigInteger privateKeyB;
	private BigInteger publicKeyA;
	private BigInteger publicKeyB;
	private BigInteger sharedSecretA;
	private BigInteger sharedSecretB;

	public DiffieHellmanModel() {
		this.modulus = generatePrimeNumber();
		this.base = selectBaseValue(modulus);
		this.privateKeyA = generatePrimeNumber(10);
		this.privateKeyB = generatePrimeNumber(10);
		this.publicKeyA = correspondingPublicKey(privateKeyA, base, modulus);
		this.publicKeyB = correspondingPublicKey(privateKeyB, base, modulus);
		this.sharedSecretA = publicKeyB.modPow(privateKeyA, modulus);
		this.sharedSecretB = publicKeyA.modPow(privateKeyB, modulus);
	}

	// Generate a prime
	public BigInteger generatePrimeNumber() {
		return generatePrimeNumber(15);
	}

	// Generate a different prime
	public static BigInteger generatePrimeNumber(int numDigits) {
		BigInteger prime;
		Random random = new Random();

		do {
			StringBuilder sb = new StringBuilder(numDigits);

			// Generate random digits
			for (int i = 0; i < numDigits; i++) {
				int digit = random.nextInt(10); // Generate a random digit from 0 to 9
				sb.append(digit);
			}

			// Convert the generated digits to a BigInteger
			BigInteger candidate = new BigInteger(sb.toString());

			// Check if the candidate is prime using the isProbablePrime() method
			boolean isPrime = candidate.isProbablePrime(100); // Adjust the certainty level as needed

			if (isPrime) {
				prime = candidate;
				break;
			}
		} while (true);

		return prime;
	}

	public static BigInteger selectBaseValue(BigInteger p) {
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);

		Random random = new Random();
		BigInteger g;
		boolean found = false;

		do {
			g = new BigInteger(p.bitLength(), random);

			if (g.compareTo(BigInteger.TWO) >= 0 && g.compareTo(pMinusOne) <= 0) {
				found = checkIfPrimitiveRoot(g, p, pMinusOne);
			}
		} while (!found);

		return g;
	}

	public static boolean checkIfPrimitiveRoot(BigInteger g, BigInteger p, BigInteger pMinusOne) {
		BigInteger exponent = pMinusOne.divide(BigInteger.TWO); // (p-1)/2

		BigInteger result = g.modPow(exponent, p);

		return !result.equals(BigInteger.ONE);
	}

	public BigInteger correspondingPublicKey(BigInteger privateKey, BigInteger base, BigInteger modulus) {
		return base.modPow(privateKey, modulus);
	}

	public static void main(String[] args) {
    	
    	DiffieHellmanModel diffieHellmanModel = new DiffieHellmanModel();
    	
        // Example usage:
        BigInteger sharedSecretA = diffieHellmanModel.getSharedSecretA();
        BigInteger sharedSecretB = diffieHellmanModel.getSharedSecretB();

        System.out.println("Shared secret A: " + sharedSecretA);
        System.out.println("Shared secret B: " + sharedSecretB);
    }

	public BigInteger getBase() {
		return base;
	}

	public void setBase(BigInteger base) {
		this.base = base;
	}

	public BigInteger getModulus() {
		return modulus;
	}

	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}

	public BigInteger getPrivateKeyA() {
		return privateKeyA;
	}

	public void setPrivateKeyA(BigInteger privateKeyA) {
		this.privateKeyA = privateKeyA;
	}

	public BigInteger getPrivateKeyB() {
		return privateKeyB;
	}

	public void setPrivateKeyB(BigInteger privateKeyB) {
		this.privateKeyB = privateKeyB;
	}

	public BigInteger getPublicKeyA() {
		return publicKeyA;
	}

	public void setPublicKeyA(BigInteger publicKeyA) {
		this.publicKeyA = publicKeyA;
	}

	public BigInteger getPublicKeyB() {
		return publicKeyB;
	}

	public void setPublicKeyB(BigInteger publicKeyB) {
		this.publicKeyB = publicKeyB;
	}

	public BigInteger getSharedSecretA() {
		return sharedSecretA;
	}

	public void setSharedSecretA(BigInteger sharedSecretA) {
		this.sharedSecretA = sharedSecretA;
	}

	public BigInteger getSharedSecretB() {
		return sharedSecretB;
	}

	public void setSharedSecretB(BigInteger sharedSecretB) {
		this.sharedSecretB = sharedSecretB;
	}
}
