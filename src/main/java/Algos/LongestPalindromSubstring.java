package Algos;

public class LongestPalindromSubstring {


    // Def: 1. strings of length 0 are not palindromes
    // Def: 2. strings of length 1 are always palindromes
    private static boolean isPalindrome(String input) {

        if (input.length() == 0 ) return false;
        else if (input.length() == 1) return true;
        else {
            for (int i = 0; i < input.length()/2; i++) {
                if (input.charAt(i) != input.charAt(input.length() - i - 1)) return false;
            }
            return true;
        }

    }

    // ASSUME: input.length() <= 1000)
    private static String longestPalSubStr(String input) {
        int start = 0, end = 0; // Indexes of longest palindrome seen so far
        for (int i = 0; i < input.length(); i++) {
            int len1 = expandAroundCenter(input, i, i);
            int len2 = expandAroundCenter(input, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return input.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalSubStr("eqfbabadsst"));
    }
}
