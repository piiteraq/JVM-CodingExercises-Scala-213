package Algos;

import java.util.*;

public class GLM_1_6 {

    static String compress(String input) {

        // Assume: Alphabet(input) = [a-zA-Z]

        // Important: Use StringBuilder for building result string to avoid O(n + k^2) runtime,
        // where k is the number of character sequences
        StringBuilder compressed = new StringBuilder();

        int count = 0;
        char currChar = '1'; // Initialize to arbitrary char not in [a-zA-Z]

        for (int i=0; i < input.length(); i++) {

            if (currChar != input.charAt(i)) {
                if (i != 0) {
                    compressed.append(currChar);
                    compressed.append(count);
                    count = 0;
                }
                count++;
                currChar = input.charAt(i);
            } else {
                count++;
            }

            if (i == input.length() - 1) {
                compressed.append(currChar);
                compressed.append(count);
            }

        }

        return (compressed.length() >= input.length()) ? input : compressed.toString();
    }


    public static void main(String[] args) {
        String input = "aabcccccaaa";
        System.out.println("compr(" + input + ") = " + compress(input));
    }

}
