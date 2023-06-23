// Generate RSA key pair
function generateKeys() {
    const rsa = new JSEncrypt({ default_key_size: 2048 });
    const publicKey = rsa.getPublicKey();
    const privateKey = rsa.getPrivateKey();
    return { publicKey, privateKey };
}

// Encrypt plaintext using RSA public key
function encrypt(message, publicKey) {
    const rsa = new JSEncrypt();
    rsa.setPublicKey(publicKey);
    const encrypted = rsa.encrypt(message);
    return encrypted;
}

// Decrypt ciphertext using RSA private key
function decrypt(ciphertext, privateKey) {
    const rsa = new JSEncrypt();
    rsa.setPrivateKey(privateKey);
    const decrypted = rsa.decrypt(ciphertext);
    return decrypted;
}

// Function to remove header from private key
function removePrivateKeyHeader(privateKey) {
    const headerRegex = /-----BEGIN [^-]+-----/g;
    const footerRegex = /-----END [^-]+-----/g;
    return privateKey.replace(headerRegex, "").replace(footerRegex, "").trim();
}

export { generateKeys, encrypt, decrypt, removePrivateKeyHeader };  