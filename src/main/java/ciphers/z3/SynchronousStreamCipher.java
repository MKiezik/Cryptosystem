package ciphers.z3;

public class SynchronousStreamCipher {

    public static String stringToBinary(String text) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            StringBuilder temp = new StringBuilder();
            String currentBinary = Integer.toBinaryString(text.charAt(i));
            if(currentBinary.length() < 8) {
                temp = new StringBuilder(currentBinary);
                for(int j = currentBinary.length(); j < 8 ;j++) {

                    temp.insert(0, 0);
                }
            }
            result.append(temp);
        }
        return result.toString();
    }

    public static String binaryToString(String text) {

        int index = 0;
        StringBuilder result = new StringBuilder();

        while(index < text.length()) {
            String temp = text.substring(index, index+8);
            int num = Integer.parseInt(temp,2);
            char letter =(char) (int)num;
            result.append(letter);
            index +=8;
        }
        return result.toString();
    }

    public static String streamCipher(String text, String key) {

        StringBuilder returnText = new StringBuilder();

        for (int i = 0; i < text.length(); ++i) {
            int textBit = Character.getNumericValue(text.charAt(i));
            int keyBit = Character.getNumericValue(key.charAt(i));

            returnText.append(textBit ^ keyBit);
        }
        return returnText.toString();
    }
}
