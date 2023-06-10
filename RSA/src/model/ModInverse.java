package model;

public class ModInverse {
    public static int calculateD(int e, int p, int q) {
        int phiN = (p - 1) * (q - 1);
        int d = modInverse(e, phiN);
        
        while (d < 0) {
            d += phiN;
        }
        
        return d;
    }
    
    public static int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            int q = a / m;
            int t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }
    
    public static void main(String[] args) {
        int e = 11; // Replace with the actual value of the public exponent (e)
        int p = 61; // Replace with the actual value of the prime p
        int q = 53; // Replace with the actual value of the prime q
        
        int d = modInverse(e, 26);
        System.out.println("Private exponent (d): " + d);
    }
}

