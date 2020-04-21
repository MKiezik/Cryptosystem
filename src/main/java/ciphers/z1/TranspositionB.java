package ciphers.z1;

import java.util.*;
import java.io.*;
import java.lang.*;

public class TranspositionB {

    public static String encrypt(String plainText, String key){

        plainText = plainText.toUpperCase().replace(" ", "");
        StringBuilder plainSB = new StringBuilder(plainText);
        key = key.toUpperCase();

        int extraLetters = plainSB.length() % key.length();
        int dummyCharacters = key.length() - extraLetters;

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                plainSB.append(".");
            }
        }

        int numOfRows = plainSB.length() / key.length();
        int numOfCols = key.length();
        char[][] cipherArray = new char[numOfRows][numOfCols];
        int[] sortedKey = getSortedKey(key);
        int[] locationIndex = getKeyIndex(sortedKey);

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < key.length(); j++) {
                cipherArray[i][j] = plainSB.charAt(z);
                z++;
            }
        }

        String cipherText = "";

        for(int i=0;i<locationIndex.length;i++) {
            for(int j=0;j<numOfRows;j++) {
                cipherText += (cipherArray[j][locationIndex[i]]);
                if(j == numOfRows - 1 && i != locationIndex.length -1)
                    cipherText += ' ';
            }
        }
        cipherText = cipherText.replace(".", "");
        return cipherText;
    }

    public static String decrypt(String cipherText, String key){

        String cipherTextTmp = cipherText.toUpperCase().replace(" ", "");
        StringBuilder cipherSB = new StringBuilder(cipherTextTmp);
        key = key.toUpperCase();

        int extraLetters = cipherSB.length() % key.length();
        int dummyCharacters = key.length() - extraLetters;

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                cipherSB.append(".");
            }
        }

        int numOfRows = cipherSB.length() / key.length();
        int numOfCols = key.length();
        int[] sortedKey = getSortedKey(key);
        char[][] cipherArray = new char[numOfRows][numOfCols];
        int[] locationIndex = getKeyIndex(sortedKey);

        int z = 0;
        for (int i = 0; i < locationIndex.length; i++) {
            for (int j = 0; j < numOfRows; j++) {
                if(z == cipherText.length())
                    break;
                char currentLetter = cipherText.charAt(z);
                if (currentLetter == ' ' && j < numOfRows - 1) {
                    z++;
                    j = 0;
                }
                if(z == cipherText.length())
                    break;

                currentLetter = cipherText.charAt(z);
                cipherArray[j][locationIndex[i]] = currentLetter;
                z++;
            }
        }
        String decryptedText = "";

        for(int i=0;i<numOfRows;i++) {
            for(int j=0;j<key.length();j++) {
                decryptedText += (cipherArray[i][j]);
            }
        }
        decryptedText = decryptedText.replace(" ", "");

        return decryptedText;
    }

    private static int[] getKeyIndex(int[] sortedKey) {

        int[] locationIndex = new int[sortedKey.length];
        for (int i = 1; i < locationIndex.length + 1; i++) {
            for (int j = 0; j < sortedKey.length; j++) {
                if (sortedKey[j] == i){
                    locationIndex[i-1] = j;
                    break;
                }
            }
        }
        return locationIndex;
    }

    private static int[] getSortedKey(String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] sortedKey = new int[key.length()];

        int init = 0;
        for (int i = 0; i < alphabet.length(); i ++){
            for (int j = 0; j < key.length(); j++) {
                if (alphabet.charAt(i) == key.charAt(j)){
                    init++;
                    sortedKey[j] = init;
                }
            }
        }
        return sortedKey;
    }
}
