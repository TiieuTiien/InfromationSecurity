// AES.js

// Import the required module
const CryptoJS = require('crypto-js');

// Function to generate a random encryption key
function generateKey() {
  return CryptoJS.lib.WordArray.random(32); // 32 bytes = 256 bits key size
}

// Function to encrypt plaintext using AES cipher
function encryptAES(plaintext, key) {
  const encrypted = CryptoJS.AES.encrypt(plaintext, key);
  return encrypted.toString();
}

// Function to decrypt ciphertext using AES cipher
function decryptAES(ciphertext, key) {
  const decrypted = CryptoJS.AES.decrypt(ciphertext, key);
  return decrypted.toString(CryptoJS.enc.Utf8);
}

// Usage example
const plaintext = 'Hello, World!';
const key = generateKey();

console.log('Plaintext:', plaintext);

const ciphertext = encryptAES(plaintext, key);
console.log('Ciphertext:', ciphertext);

const decryptedText = decryptAES(ciphertext, key);
console.log('Decrypted Text:', decryptedText);
