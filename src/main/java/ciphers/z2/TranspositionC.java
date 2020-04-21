package ciphers.z2;

import java.lang.*;
import java.util.Arrays;
import java.util.Comparator;

public class TranspositionC {

    public static String encrypt(String plainText, String key){

        key = key.toUpperCase();
        plainText = plainText.toUpperCase().replace(" ", "");

        int sum = 0, k = key.length();
        while (k > 0) {
            sum = sum + k;
            k--;
        }
        String fail = "Klucz jest za krótki";
        if (sum < plainText.length())
            return fail;

        int[] sortedKey = getSortedKey(key);
        int[] locationIndex = getKeyIndex(sortedKey);
        int numOfRows = key.length();
        int numOfCols = key.length();
        char[][] cipherArray = new char[numOfRows][numOfCols];

        for(int i=0;i<numOfRows;i++) {
            for (int j=0;j<key.length();j++) {
                cipherArray[i][j] = '*';
            }
        }

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j <= locationIndex[i]; j++) {
                char variable = plainText.charAt(z);
                cipherArray[i][j] = variable;
                z++;
                if (z == plainText.length())
                    break;
            }
            if (z == plainText.length())
                break;
        }
        String cipherText = "";

        for(int c=0;c<locationIndex.length;c++) {
            for(int j=0;j<numOfRows;j++) {
                if(cipherArray[j][locationIndex[c]] != '*')
                    cipherText += (cipherArray[j][locationIndex[c]]);
                if(j == numOfRows - 1 && c != locationIndex.length -1)
                    cipherText += ' ';
            }
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, String key){

        int sum = 0, k = key.length();
        while (k > 0) {
            sum = sum + k;
            k--;
        }
        String fail = "Klucz jest za krótki";
        if (sum < cipherText.length())
            return fail;

        key = key.toUpperCase();
        int[] sortedKey = getSortedKey(key);
        int[] locationIndex = getKeyIndex(sortedKey);
        int numOfRows = getArrayHeight(cipherText);
        int numOfCols = key.length();

        char[][] cipherArray = getArrayWithIllegalCells(locationIndex, numOfRows, numOfCols);

        int z = 0;
        for (int i = 0; i < locationIndex.length; i++) {
            for (int j = 0; j < numOfRows; j++) {
                if(z == cipherText.length())
                    break;
                char currentLetter = cipherText.charAt(z);
                if (currentLetter == ' ' ) {
                    z++;
                    break;
                }
                currentLetter = cipherText.charAt(z);

                if (cipherArray[j][locationIndex[i]] != '*') {
                    cipherArray[j][locationIndex[i]] = currentLetter;
                    z++;
                }
                if(j == numOfRows -1) {
                    i--;
                }
            }
        }

        String decryptedText = "";

        for(int c=0;c<numOfRows;c++) {
            for(int j=0;j<key.length();j++) {
                if(cipherArray[c][j] != '*')
                    decryptedText += (cipherArray[c][j]);
            }
        }
        return decryptedText;
    }

    private static char[][] getArrayWithIllegalCells (int[] locationIndex, int rows, int columns) {

        char[][] cipherArray = new char[rows][columns];
        for (int i=0;i < rows; i++) {
            for (int j=0;j < columns; j++) {
                if(j > locationIndex[i]) {
                    cipherArray[i][j] = '*';
                }
            }
        }
        return cipherArray;
    }

    private static int getArrayHeight(String cipherText) {

        String longest = Arrays.stream(cipherText.split(" ")).max(Comparator.comparingInt(String::length)).orElse(null);

        return longest.length();
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
