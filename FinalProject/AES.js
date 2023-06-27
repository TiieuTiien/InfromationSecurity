// Function to generate a random encryption key
function generateKey() {
  return CryptoJS.lib.WordArray.random(32); // 32 bytes = 256 bits key size
}

function encryptAES(plaintext, key) {
  var iv = CryptoJS.lib.WordArray.random(16);
  const encrypted = CryptoJS.AES.encrypt(plaintext, key, { iv: iv });

  // Concatenate IV and ciphertext
  const encryptedData = iv.toString() + encrypted.toString();
  return encryptedData;
}

function decryptAES(encryptedData, key) {
  // Extract IV from the encrypted data
  const iv = CryptoJS.enc.Hex.parse(encryptedData.substring(0, 32));

  // Extract ciphertext from the encrypted data
  const ciphertext = encryptedData.substring(32);

  const decrypted = CryptoJS.AES.decrypt(ciphertext, key, { iv: iv });
  return decrypted.toString(CryptoJS.enc.Utf8);
}

export { generateKey, encryptAES, decryptAES };