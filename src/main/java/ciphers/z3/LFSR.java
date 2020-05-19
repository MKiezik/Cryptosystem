package ciphers.z3;

public class LFSR {

    private static String polynomial;
    private static int degree;
    private static int[] bits;

    private static void shiftBits() {
        int xorValue = 0;

        for (int i = 0; i < degree; ++i) {
            if (polynomial.charAt(i) == '1') {
                xorValue ^= bits[i];
            }
        }

        for (int i = degree - 1; i >= 1; --i)
            bits[i] = bits[i - 1];
        bits[0] = xorValue;
    }

    public static String generate(String polynomial, int iteration, String randomSeed) {

        StringBuilder resultArray2 = new StringBuilder();
        if (iteration == 0) {

            LFSR.polynomial = polynomial;
            degree = polynomial.length();

            bits = new int[degree];

            for(int i=0 ; i<randomSeed.length(); i++) {
                bits[i] = Character.getNumericValue(randomSeed.charAt(i));
                resultArray2.append(bits[i]);
            }

            return resultArray2.toString();
        } else {
            LFSR.shiftBits();

            for (int bit : bits) {
                resultArray2.append(bit);
            }

            return resultArray2.toString();
        }
    }
}
