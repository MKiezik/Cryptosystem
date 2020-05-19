package ciphers.z3;

public class LFSR {

    private static String polynomial;
    private static String result = "";
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

    public static String generate(String polynomial, String resultArray, int iteration) {

        resultArray = "";
        if (iteration == 0) {
            result = "";
            LFSR.polynomial = polynomial;
            degree = polynomial.length();

            bits = new int[degree];

//            long seed = new Date().getTime();
            long seed = 1589627503590L;
            for (int i = 0; i < degree; ++i) {
                bits[i] = (int) seed & 1;
                resultArray += bits[i];
                seed >>= 1;

            }
            return resultArray;
        }

        LFSR.shiftBits();

        for (int i = 0; i < bits.length; i++) {
            resultArray += bits[i];
        }

        return resultArray;
    }
}
