package ciphers.z1;

public class TranspositionA {

    public static String encrypt(String plainText, String key){

        plainText = plainText.toUpperCase().replace(" ", "");
        String[] keyTmp = key.split("-");

        int[] keyArray = new int[keyTmp.length];

        for (int i = 0; i < keyTmp.length; i++) {
            int num_int = Integer.parseInt(keyTmp[i]);
            keyArray[i] = num_int;
        }

        int extraLetters = plainText.length() % keyArray.length;
        int dummyCharacters = keyArray.length - extraLetters;

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                plainText += '.';
            }
        }

        int numOfRows = plainText.length() / keyArray.length;
        int numOfCols = keyArray.length;

        char[][] cipherArray = new char[numOfRows][numOfCols];

        int[] sortedKey  = new int[keyArray.length];

        for (int i=0; i< sortedKey.length; i++) {
            sortedKey[i] = i+1;
        }

        int[] locationIndex = getKeyIndex(keyArray);

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyArray.length; j++) {
                cipherArray[i][j] = plainText.charAt(z);
                z++;
            }
        }

        String cipherText = "";

        for(int i=0;i< numOfRows;i++) {
            for(int j=0;j<locationIndex.length;j++) {
                cipherText += (cipherArray[i][keyArray[j]-1]);
            }
        }
        cipherText = cipherText.replace(".", "");

        return cipherText;
    }

    public static String decrypt(String cipherText, String key){

        String[] keyTmp = key.split("-");
        int[] keyArray = new int[keyTmp.length];

        for (int i = 0; i < keyTmp.length; i++) {
            int number = Integer.parseInt(keyTmp[i]);
            keyArray[i] = number;
        }
        int extraLetters = cipherText.length() % keyArray.length;
        int dummyCharacters = keyArray.length - extraLetters;

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                cipherText += '.';
            }
        }

        int numOfRows = cipherText.length() / keyArray.length;
        if(cipherText.replace(".", "").length() % keyArray.length == 0 ) {
            numOfRows++;
        }

        int numOfCols = keyArray.length;
        char[][] cipherArray = new char[numOfRows][numOfCols];

        for (int i= 1; i<=dummyCharacters; i++) {
            cipherArray[numOfRows-1][numOfCols-i] = '.';
        }

        int z=0;
        for(int i=0;i< numOfRows;i++) {
            for(int j=0;j<keyArray.length;j++) {
                if(z == cipherText.length())
                    break;
                if( cipherArray[i][keyArray[j]-1] == '.')
                    continue;
                char currentLetter = cipherText.charAt(z);
                cipherArray[i][keyArray[j]-1] = currentLetter;
                z++;
            }
        }

        String decryptedText = "";

        for(int i=0;i<numOfRows;i++) {
            for(int j=0;j<keyArray.length;j++) {
                decryptedText += (cipherArray[i][j]);
            }
        }
        decryptedText = decryptedText.replace(".", "");

        return decryptedText;
    }

    private static int[] getKeyIndex(int[] keyArray) {

        int[] locationIndex = new int[keyArray.length];
        for (int i = 1; i < locationIndex.length + 1; i++) {
            for (int j = 0; j < keyArray.length; j++) {
                if (keyArray[j] == i){
                    locationIndex[i-1] = j;
                    break;
                }
            }
        }
        return locationIndex;
    }
}
