package Algos;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class LongestSubstringNoDupChars {

    public static int lengthOfLongestSubstring(String s) {

        // Handle trivial case
        if (s.length() == 0) return 0;

        // Store chars that we have seen so far
        Set<Character> seenChars = new HashSet<>();

        // Longest substring seen so far
        int longestSoFar = 0;
        int lcurr = 0;
        String scurr = "";

        int lidx = 0, ridx = 0;
        while (ridx < s.length()) {
            if (! seenChars.contains(s.charAt(ridx))) {
                seenChars.add(s.charAt(ridx)); // Value irrelevant - using map as a set
                ++lcurr;

                if (lcurr > longestSoFar) {
                    longestSoFar = lcurr;
                    scurr = s.substring(lidx, ridx+1);
                    System.out.println("Longest sub-string so far: '" + scurr + "', len: " + longestSoFar);
                }
            } else { // Encountered a duplicate char within current substring
                while (s.charAt(lidx) != s.charAt(ridx)) {
                    seenChars.remove(s.charAt(lidx));
                    ++lidx;
                    --lcurr;
                }
                ++lidx;
            }
            ++ridx;
        }

        return longestSoFar;
    }


    // Leetcode's solution ..
    public static int lengthOfLongestSubstringLC(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // Current index of character
        // Try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1); // If key already exists, only value is updated
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("wpwkew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
    }

}

