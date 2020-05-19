package ciphers.Interface;

public interface Cipher {
    default public void encrypt(String plainText, String key) {
    }

    default public void decrypt(String cipherText, String key) {
    }

}
