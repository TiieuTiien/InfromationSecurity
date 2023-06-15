// AES.js

// Function to generate a random encryption key
function generateKey() {
    return crypto.getRandomValues(new Uint8Array(32)); // 32 bytes = 256 bits key size
}

// Function to encrypt plaintext
async function encrypt(plaintext, key) {
    const encoder = new TextEncoder();
    const data = encoder.encode(plaintext);
    const cryptoKey = await crypto.subtle.importKey('raw', key, 'AES-CBC', true, ['encrypt']);
    const iv = crypto.getRandomValues(new Uint8Array(16));
    const encryptedData = await crypto.subtle.encrypt(
        {
            name: 'AES-CBC',
            iv: iv,
        },
        cryptoKey,
        data
    );
    const encryptedBytes = new Uint8Array(encryptedData);
    const encryptedHex = Array.prototype.map.call(encryptedBytes, (byte) => ('00' + byte.toString(16)).slice(-2)).join('');
    return {
        iv: Array.prototype.map.call(iv, (byte) => ('00' + byte.toString(16)).slice(-2)).join(''),
        encryptedData: encryptedHex,
    };
}

// Function to decrypt ciphertext
async function decrypt(ciphertext, key, iv) {
    const cryptoKey = await crypto.subtle.importKey('raw',key,'AES-CBC',true,['decrypt']);
    const ciphertextBytes = new Uint8Array(ciphertext.match(/.{1,2}/g).map((byte) => parseInt(byte, 16)));
    const decryptedData = await crypto.subtle.decrypt(
        {
            name: 'AES-CBC',
            iv: new Uint8Array(
                iv.match(/.{1,2}/g).map((byte) => parseInt(byte, 16))
            ),
        },
        cryptoKey,
        ciphertextBytes
    );
    const decoder = new TextDecoder();
    return decoder.decode(decryptedData);
}
