package Algos;

import java.util.Arrays;

public class GLM_1_1 {

    // Replace all spaces in a string with '%20'
    // Assume: input array has room for extra characters
    static char[] urlify(char[] input, int size) {

        // Check for invalid input, corner cases, etc.
        // ...

        // Make a working copy in linear time
        char[] tmp = Arrays.copyOf(input, input.length);

        //for (char c : tmp) System.out.print(c);
        //System.out.println();

        int i = 0, t = 0;
        while (i < size) {
            //System.out.println("t: " + t + ", i: " + i);

            if (input[i] == ' ') {
                tmp[t] = '%';
                tmp[t+1] = '2';
                tmp[t+2] = '0';
                t += 3;
            } else {
                tmp[t] = input[i];
                t += 1;
            }
            i += 1;
        }

        return tmp;
    }


    // Gayle's solution. void return, since done in-place
    static void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i=0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0'; // End array
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index -= 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }


    public static void main(String[] args) {
        String input = "Mr John Smith    ";
        char[] inpAsCharArr = input.toCharArray();
        replaceSpaces(inpAsCharArr, 13);
        for (char c : inpAsCharArr) System.out.print(c);
        System.out.println();
    }

}
