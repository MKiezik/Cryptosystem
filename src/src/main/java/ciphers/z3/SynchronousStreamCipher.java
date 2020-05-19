package ciphers.z3;

public class SynchronousStreamCipher {

    private static String polynomial;
    private static int degree;
    private static int[] bits;
    private static String[] result;

    private static void shiftBits() {
        int xorValue = 0;
        for (int i = 0; i < degree; ++i)
            if (SynchronousStreamCipher.polynomial.charAt(i + 1) == '1')
                xorValue ^= bits[i];
        for (int i = degree - 1; i >= 1; --i)
            bits[i] = bits[i - 1];
        bits[0] = xorValue;
    }

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
        String result = "";

        while(index < text.length()) {
            String temp = text.substring(index, index+8);
            Integer num = Integer.parseInt(temp,2);
            char letter =(char) (int)num;
            result +=letter;
            index +=8;
        }
        return result;
    }

    public static String streamCipher(String text, String polynomial, String key) {

        SynchronousStreamCipher.polynomial = polynomial;
        degree = SynchronousStreamCipher.polynomial.length() - 1;

        //            long seed = new Date().getTime();
        long seed = 1589627503590L;

        bits = new int[degree];
        for (int i = 0; i < degree; ++i) {
            bits[i] = (int)seed & 1;
            seed >>= 1; //+
        }

        String returnText = "";
        for (int i = 0; i < text.length(); ++i) {
            shiftBits();
            returnText += (char)('0' + ((text.charAt(i) - '0') ^ bits[0]));
        }
        return returnText;
    }
}
