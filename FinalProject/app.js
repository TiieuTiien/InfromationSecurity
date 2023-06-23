import { generateKey, encryptAES, decryptAES } from "./AES.js";
import { generateKeys, encrypt, decrypt, removePrivateKeyHeader } from "./RSA.js"

// Usage example
var key = generateKey();
var aesKeyArea = document.getElementById("aesKeyArea");

// Plaintext
const plaintextArea = document.getElementById("plaintextArea");
var plaintext;

var ciphertext = "";
var ciphertextArea = document.getElementById("ciphertextArea");

var { publicKey, privateKey } = generateKeys();

var encryptedKeyArea = document.getElementById("encryptedKeyArea");
var privateKeyArea = document.getElementById("privateKeyArea");

// Encrypt
const encryptButt = document.getElementById("encryptButt")
encryptButt.addEventListener("click", function () {

    key = generateKey();

    aesKeyArea.value = key;

    plaintext = plaintextArea.value;

    ciphertext = encryptAES(plaintext, aesKeyArea.value);

    ciphertextArea.value = ciphertext;

    encryptedKeyArea.value = encrypt(aesKeyArea.value, publicKey);
    privateKey = removePrivateKeyHeader(privateKey);
    privateKeyArea.value = privateKey;

});

// Decrypt
var message = "";
const decryptButt = document.getElementById("decryptButt");
decryptButt.addEventListener("click", function () {

    ciphertext = ciphertextArea.value;

    const aesKey = decrypt(encryptedKeyArea.value, privateKeyArea.value);

    aesKeyArea.value = aesKey;

    if (aesKey == false) {
        plaintextArea.value = "error";
        plaintextArea.style.color = "#fc0c00";
        
        aesKeyArea.value = "Invalid private key";
        aesKeyArea.style.color = "#fc0c00";
    }
    else {

        plaintextArea.style.color = "white";
        aesKeyArea.style.color = "white";

        message = decryptAES(ciphertext, aesKey);
        plaintextArea.value = message;
    }

});