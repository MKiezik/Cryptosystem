package ciphers.z2;

public class Vigenere {

    public static String encrypt(String plainText, String key) {
        String cipherText = "";
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            cipherText += (char) (((letter - 65) + (key.charAt(j) - 65)) % 26 + 65);
            j = ++j % key.length();
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, String key) {
        String decryptedText = "";
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            decryptedText += (char) ((letter - key.charAt(j) + 26) % 26 + 65);
            j = ++j % key.length();
        }
        return decryptedText;
    }
}
