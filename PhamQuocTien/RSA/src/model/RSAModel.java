package model;

import java.util.Random;

public class RSAModel {

	// STEP 1: Choose two distinct prime numbers, p and q.
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Generate a prime
    public static int generatePrimeWithThreeDigits() {
    	return generatePrimeWithThreeDigits(0);
    }

    // Generate a different prime
    public static int generatePrimeWithThreeDigits(int a) {
        Random random = new Random();
        int prime;
        
        do {
            prime = random.nextInt(900) + 100;
        } while (!isPrime(prime) || prime == a);

        return prime;
    }
    
	// STEP 2: Compute their product, n = p * q.
	// This is the modulus for both the public and private keys. NO NEED TO WRITE FUNCTION
	
	// STEP 3: Compute Euler's totient function, φ(n) = lcm((p - 1),(q - 1)),
	// which represents the number of positive integers less than n that are coprime to n.
    public static int gcd(int a, int b) {
        // Ensure positive values
        a = Math.abs(a);
        b = Math.abs(b);

        // Swap if b is greater than a
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }

        // Perform the Euclidean algorithm
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a; // Return the GCD
    }

    public static int lcm(int p, int q) {
        int gcd = gcd(p, q);
        int lcm = (p * q) / gcd;
        return lcm; // Return the LCM
    }
    
	// STEP 4: Choose an integer e such that 1 < e < φ(n), and e is coprime with φ(n).
	// This is the public exponent, which forms the public key.
    public static int findPublicExponent(int phiN) {
        int e = 2; // Start with a small prime number as the initial value of e
        
        while (e < phiN) {
            if (gcd(e, phiN) == 1) {
                break; // Found a suitable value of e
            }
            e++;
        }
        
        return e;
    }
	
	// STEP 5: Compute the modular multiplicative inverse of e * modulo φ(n).
	// This is an integer d, such that (d * e) % φ(n) = 1.
	// d is the private exponent, forming the private key.
    public static int modInverse(int e, int phiN) {
        int m0 = phiN;
        int y = 0, x = 1;

        if (phiN == 1)
            return 0;

        while (e > 1) {
            int q = e / phiN;
            int t = phiN;

            phiN = e % phiN;
            e = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }
	

}
