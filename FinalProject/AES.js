const crypto = require('crypto');

// Function to generate a random encryption key
function generateKey() {
    return crypto.randomBytes(32); // 32 bytes = 256 bits key size
}

// Function to generate round keys based on AES key schedule
function generateRoundKeys(key) {
    const roundKeys = [];
    const cipher = crypto.createCipheriv('aes-256-ecb', key, null);
    const zeroBlock = Buffer.alloc(16);

    let currKey = Buffer.from(key);
    for (let i = 0; i < 14; i++) {
        currKey = cipher.update(currKey);
        roundKeys.push(currKey);
    }

    return roundKeys;
}

// Function to perform AES encryption with 14 rounds in AES-ECB mode
function encryptAES(plaintext, key) {
    const roundKeys = generateRoundKeys(key);
    let ciphertext = Buffer.from(plaintext);

    for (let i = 0; i < 13; i++) {
        const cipher = crypto.createCipheriv('aes-256-ecb', roundKeys[i], null);
        ciphertext = Buffer.concat([cipher.update(ciphertext), cipher.final()]);
    }

    const finalCipher = crypto.createCipheriv('aes-256-ecb', roundKeys[13], null);
    const finalCiphertext = Buffer.concat([finalCipher.update(ciphertext), finalCipher.final()]);

    return finalCiphertext;
}

// Function to perform AES decryption with 14 rounds in AES-ECB mode
function decryptAES(ciphertext, key) {
    const roundKeys = generateRoundKeys(key);
    let decryptedText = Buffer.from(ciphertext);

    const finalDecipher = crypto.createDecipheriv('aes-256-ecb', roundKeys[13], null);
    decryptedText = Buffer.concat([finalDecipher.update(decryptedText), finalDecipher.final()]);

    for (let i = 12; i >= 0; i--) {
        const decipher = crypto.createDecipheriv('aes-256-ecb', roundKeys[i], null);
        decryptedText = Buffer.concat([decipher.update(decryptedText), decipher.final()]);
    }

    return decryptedText;
}

const plaintext = 'Hello, World!';
const key = generateKey();

console.log('Plaintext:', plaintext);

const ciphertext = encryptAES(plaintext, key);
console.log('Ciphertext:', ciphertext.toString('base64'));

const decryptedText = decryptAES(ciphertext, key);
console.log('Decrypted Text:', decryptedText.toString('utf8'));
