package Algos;

public class IntToRoman {


    // Assume: Input is guaranteed to be within the range from 1 to 3999.
    private static String intToRoman(int num) throws Exception {

        // Check for invalid input
        if (num < 1 || num > 3999) {
            throw new Exception("Input is out of range [1,3999]: " + num);
        }

        StringBuilder res = new StringBuilder("");

        // Break into thousands, hundreds, tens, and ones
        final int thousands = num / 1000;
        final int hundreds  = (num - thousands * 1000) / 100;
        final int tens = (num - thousands * 1000 - hundreds * 100) / 10;
        final int ones = (num - thousands * 1000 - hundreds * 100 - tens * 10);

        System.out.println(thousands + ", " + hundreds + ", " + tens + ", " + ones);

        // Build thousands ..
        for (int i=0; i < thousands; i++) res.append('M');

        // Build hundreds ..
        if (hundreds < 4) {
            for (int i=0; i < hundreds; i++) res.append('C');
        } else if (hundreds == 4) {
            res.append("CD");
        } else if (hundreds == 5) {
            res.append('D');
        } else if (hundreds > 5 && hundreds < 9) {
            res.append('D');
            for (int i=0; i < hundreds - 5; i++) {
                res.append('C');
            }
        } else {
            res.append("CM");
        }

        // Build tens ..
        if (tens < 4) {
            for (int i=0; i < hundreds; i++) res.append('X');
        } else if (tens == 4) {
            res.append("XL");
        } else if (tens == 5) {
            res.append('L');
        } else if (tens > 5 && tens < 9) {
            res.append('L');
            for (int i=0; i < tens - 5; i++) {
                res.append('X');
            }
        } else {
            res.append("XC");
        }

        // Build ones ..
        if (ones < 4) {
            for (int i=0; i < ones; i++) res.append('I');
        } else if (ones == 4) {
            res.append("IV");
        } else if (ones == 5) {
            res.append('V');
        } else if (ones > 5 && ones < 9) {
            res.append('V');
            for (int i=0; i < ones - 5; i++) {
                res.append('I');
            }
        } else {
            res.append("IX");
        }

        return res.toString();
    }

    public static void main(String[] args) {
        int num = 3999;
        try {
            System.out.println("Arabic: " + num + ", Roman: " + intToRoman(num));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}


// Approach:
// 1. Break up into thousands, hundreds, tens, and ones
// 2. Due to upper range limit, it is safe to immediately generate
// thousands part of string M{1,4}
// 3. Now, generate hundreds part of output:
//    a) Less than four hundred: C{1,3}
//    b) Four hundred: CD
//    c) Five hundred: D
//    d) six-eight hundred: DC{1,3}
//    e) nine hundred: CM
// 4. Now, cover tens part of output:
//    a) Less than fourty: X{1,3}
//    b) Fourty: XL
//    c) Fifty: L
//    d) Sixty-eighty: LX{1,3}
//    e) Ninety: XC
// 5. Now, cover ones part of output:
//    a) Less than four: I{1,3}
//    b) Four: IV
//    c) Five: V
//    d) Six-eight: VI{1,3}
//    e) Nine: IX
