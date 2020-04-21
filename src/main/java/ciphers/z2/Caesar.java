package ciphers.z2;

public class Caesar {

    public static String encrypt(String plainText, String keyString) {

        Integer key = Integer.parseInt(keyString);

        if (key > 26)
            key = key % 26;
        else if (key < 0)
            key = (key % 26) + 26;

        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) (ch + key);
                    if (c > 'z') {
                        cipherText += (char) (ch - (26 - key));
                    } else {
                        cipherText += c;
                    }
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) (ch + key);
                    if (c > 'Z') {
                        cipherText += (char) (ch - (26 - key));
                    } else {
                        cipherText += c;
                    }
                }

            } else {
                cipherText += ch;
            }
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, String keyString) {

        Integer key = Integer.parseInt(keyString);

        if (key > 26)
            key = key % 26;
        else if (key < 0)
            key = (key % 26) + 26;

        String decryptedText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) (ch - key);
                    if (c < 'a') { decryptedText += (char) (ch + (26 - key));
                    } else { decryptedText += c;
                    }
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) (ch - key);
                    if (c < 'A') { decryptedText += (char) (ch + (26 - key));
                    } else {
                        decryptedText += c;
                    }
                }

            } else {
                decryptedText += ch;
            }
        }

        return decryptedText;
    }
}
