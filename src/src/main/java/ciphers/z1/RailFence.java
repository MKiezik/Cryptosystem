package ciphers.z1;

public class RailFence {

    public static String encrypt(String plainText, String keyString) {

        Integer key = Integer.parseInt(keyString);

        String cipherText = "";

        boolean flag = false;
        int rows = key;
        int j = 0;
        int columns = plainText.length();
        char[][] fenceArray = new char[rows][columns];

        for(int i=0;i<columns;i++) {
            if(j == 0 || j == rows-1)
                flag = !flag;
            fenceArray[j][i] = plainText.charAt(i);

            if(flag) {
                j++;
            } else {
                j--;
            }
        }

        for(int i=0;i<rows;i++) {
            for(int k=0;k<columns;k++) {
                if(fenceArray[i][k] != 0 )
                    cipherText += fenceArray[i][k];
            }
        }

        return cipherText;
    }

    public static String decrypt(String cipherText, String keyString) {

        Integer key = Integer.parseInt(keyString);

        String decryptedText = "";

        boolean flag = false;
        int j = 0;
        int rows = key;
        int columns = cipherText.length();

        char[][] a = new char[rows][columns];

        for(int i=0;i<columns;i++) {
            if(j == 0 || j == key-1)
                flag = !flag;
            a[j][i] = '*';

            if(flag) {
                j++;

            } else {
                j--;
            }
        }

        int index = 0;
        flag = false;

        for(int i=0;i< rows; i++) {
            for(int k=0;k<columns;k++) {
                if(a[i][k] == '*' && index <columns) {
                    a[i][k] = cipherText.charAt(index++);
                }
            }
        }

        j =0;
        for(int i=0;i<columns;i++) {
            if(j==0 || j==key-1)
                flag = !flag;
            decryptedText += a[j][i];

            if(flag) {
                j++;
            } else {
                j--;
            }
        }
        return decryptedText;
    }
}

