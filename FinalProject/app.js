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
var publicKeyArea = document.getElementById("publicKeyArea");
// Encrypt
const encryptButt = document.getElementById("encryptButt")
function encryptButtonClickHandler() {

    key = generateKey();

    aesKeyArea.value = key;

    plaintext = plaintextArea.value;

    ciphertext = encryptAES(plaintext, aesKeyArea.value);

    ciphertextArea.value = ciphertext;

    encryptedKeyArea.value = encrypt(aesKeyArea.value, publicKey);
    publicKeyArea.value = removePrivateKeyHeader(publicKey);
    privateKey = removePrivateKeyHeader(privateKey);
    privateKeyArea.value = privateKey;

}
encryptButt.addEventListener("click", encryptButtonClickHandler);

// Decrypt
var message = "";
const decryptButt = document.getElementById("decryptButt");
decryptButt.addEventListener("click", function () {

    ciphertext = ciphertextArea.value;

    const aesKey = decrypt(encryptedKeyArea.value, privateKeyArea.value);

    aesKeyArea.value = aesKey;

    if (aesKey == false) {
        plaintextArea.style.color = "#fc0c00";
        aesKeyArea.style.color = "#fc0c00";
        plaintextArea.value = "Invalid private key";
        aesKeyArea.value = "Invalid private key";
    } else {
        // Apply the renew effect for successful decryption
        applyRenewEffect();
        setTimeout(function () {
            plaintextArea.style.color = "white"; // Show the new content
            plaintextArea.value = decryptAES(ciphertext, aesKey);

            aesKeyArea.style.color = "white"; // Show the new content
            aesKeyArea.value = aesKey;
        }, 100); // Adjust the delay time (in milliseconds) as desired
    }
});

function applyRenewEffect() {
    plaintextArea.style.color = "transparent"; // Make the textarea content transparent
    plaintextArea.placeholder = ""; // Remove the placeholder

    aesKeyArea.style.color = "transparent"; // Make the aesKeyArea content transparent
    aesKeyArea.placeholder = ""; // Remove the placeholder
}

