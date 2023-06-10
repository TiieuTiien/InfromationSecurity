package model;

import java.util.Random;

public class RSAModel {

	// KEY GENERATION
	
	// STEP 1: Choose two distinct prime numbers, p and q.
	
	// Check if number is a prime
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

    /** 
     * <This function take in an a argument>
	 * 
	 * @param a - <description of parameter1> An integer that could be a prime or not
	 * 
	 * @return <what the method returns (only if applicable)> If a is not prime or less than 100 return a 3 digits prime. If a is a prime return a 3 digits prime different than a
	 * 
	 * 
	 */
     
    public static int generatePrimeWithThreeDigits(int a) {
    	
    	Random random = new Random();
        int prime;
        
        do {
            prime = random.nextInt(900) + 100;
        } while (!isPrime(prime) || prime == a);

        return prime;
    }

    // STEP 2: Compute their product, n = p * q. This is the modulus for both the public and private keys.
	// So dont need function for that
	
	// STEP 3: Compute Euler's totient function, φ(n) = (p - 1) * (q - 1),
	// which represents the number of positive integers less than n that are coprime to n.

    // Find GCD to do many things
	public static int gcd(int a, int b) {
        // Ensure positive values
        a = Math.abs(a);
        b = Math.abs(b);

        // Perform the Euclidean algorithm
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a; // Return the GCD
    }
	
	//  Compute the Carmichael's totient function of the product as φ(n) = lcm((p - 1) * (q - 1))
    public static int lcm(int a, int b) {
        int gcd = gcd(a, b);
        int lcm = (a * b) / gcd;
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

	// STEP 5: Compute the modular multiplicative inverse of e modulo φ(n).
	// This is an integer d, such that (d * e) % φ(n) = 1 or d = e^-1 % φ(n)
	// d is the private exponent, forming the private key.
	public static int findInverse(int modulus, int number) {
		int r0 = modulus, r1 = number, s0 = 1, s1 = 0, t0 = 0, t1 = 1;
	
		while (r1 != 0) {
			int quotient = r0 / r1;
			int remainder = r0 % r1;
	
			int s2 = s0 - quotient * s1;
			int t2 = t0 - quotient * t1;
	
			r0 = r1;
			r1 = remainder;
			s0 = s1;
			s1 = s2;
			t0 = t1;
			t1 = t2;
		}
	
		if (r0 != 1) {
			return -1;
		}
	
		// Adjusting the modular multiplicative inverse to be positive
		int inverse = (t0 + modulus) % modulus;
	
		return inverse;
	}
}
