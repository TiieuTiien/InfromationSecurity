class RSAModel {
    constructor(digits) {
        this.p = this.generatePrimeNumber(digits);
        this.q = this.generatePrimeNumber(digits);
        this.n = this.p * this.q;
        this.phiN = this.findLCM(this.p - 1, this.q - 1);
        this.e = this.findPublicExponent(this.phiN);
        this.d = this.findModInverse(this.e, this.phiN);
    }

    isPrime(number) {
        if (number <= 1) {
            return false;
        }

        for (let i = 2; i <= Math.sqrt(number); i++) {
            if (number % i === 0) {
                return false;
            }
        }

        return true;
    }

    generatePrimeNumber(numDigits) {
        let prime;

        do {
            prime = this.generateRandomBigInt(numDigits);
        } while (!this.isPrime(prime));

        return prime;
    }

    generateRandomBigInt(numDigits) {
        let randomBigInt = '';

        for (let i = 0; i < numDigits; i++) {
            randomBigInt += Math.floor(Math.random() * 10);
        }

        return BigInt(randomBigInt);
    }

    findLCM(a, b) {
        const absProduct = BigInt(a * b).abs();
        const gcd = this.gcd(a, b);
        const lcm = absProduct / gcd;

        return lcm;
    }

    gcd(a, b) {
        if (b === 0) {
            return a;
        }

        return this.gcd(b, a % b);
    }

    findPublicExponent(phiN) {
        let publicExponent;

        do {
            publicExponent = this.generateRandomBigInt(phiN.bitLength);
        } while (
            publicExponent <= 1 ||
            publicExponent >= phiN ||
            this.gcd(publicExponent, phiN) !== 1
        );

        return publicExponent;
    }

    findModInverse(number, modulus) {
        const [x, _, g] = this.extendedEuclideanAlgorithm(number, modulus);

        if (g === 1n) {
            return (x % modulus + modulus) % modulus;
        }

        throw new Error('Modular inverse does not exist.');
    }

    extendedEuclideanAlgorithm(a, b) {
        if (b === 0n) {
            return [1n, 0n, a];
        }

        const [x, y, g] = this.extendedEuclideanAlgorithm(b, a % b);
        return [y, x - (a / b) * y, g];
    }

    textToNum(plainText) {
        let numbers = '';

        for (const ch of plainText) {
            const text = ch.charCodeAt(0);
            numbers += (text < 100) ? '0' + text : text;
        }

        return numbers;
    }

    convertToAscii(input) {
        const bytes = new Uint8Array(input.match(/.{1,2}/g).map(byte => parseInt(byte, 10)));
        const decoder = new TextDecoder();
        return decoder.decode(bytes);
    }

    encrypt(plainText) {
        plainText = this.textToNum(plainText);
        let cipherNum = BigInt(plainText);

        cipherNum = cipherNum ** this.e % this.n;

        return cipherNum.toString();
    }

    decrypt(cipherText) {
        let message = BigInt(cipherText);

        message = message ** this.d % this.n;

        return this.convertToAscii(message.toString());
    }
}
