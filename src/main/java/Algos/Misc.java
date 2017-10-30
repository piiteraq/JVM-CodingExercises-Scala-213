package Algos;

/**
 * Created by petec on 6/25/16.
 * Class for misc algorithms without a specific category.
 */

import scala.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Misc {

    // Factorial function
    public static long factorial(long N) {

        if (N < 1) return -1; // .. or throw exception

        long res = 1;
        for (long i=1; i <= N; i++)
            res *= i;
        return res;

        //TBD: Either check for overflow her or limit size of N up-front
    }

    // Towers of Hanoi
    enum peg { A, B, C };
    public static void move_disks(peg p_orig, peg p_dest, peg p_other, int N, int id) {

        if (N==1) {
            System.out.println("Moving disk " + id + " from " + p_orig + " to " + p_dest);
        } else {
            move_disks(p_orig, p_other, p_dest, N-1, N-1);
            move_disks(p_orig, p_dest, p_other, 1, N);
            move_disks(p_other, p_dest, p_orig, N-1, N-1);
        }
    }

    // Generate all permutations of N specified objects
    public static void doPerm( ArrayList<Character> objs,
                               int perm[], int k, int N )
    {
        if (k == N) { // Print the permutation
            for (int i = 0; i <= N; i++) {
                System.out.print(objs.get(perm[i]) + " ");
            }
            System.out.println("");
            return;
        }

        // Generate all inversions INV(k,i), k <= i <= n
        for (int i=k; i <= N; i++) {

            // Generate an inversion
            int tmp = perm[k]; perm[k] = perm[i]; perm[i] = tmp;

            // Generate all permutations of perm[l+1..k]
            doPerm(objs, perm, k+1, N);

            // Undo the inversion.
            tmp = perm[k]; perm[k] = perm[i]; perm[i] = tmp;
        }
    }

    // Generate solution for the 8-Queen problem
    // Return true if a queen can be placed in kth row and ith column
    public static boolean place(int k, int i, int x[]) {
        num_attempts++;
        for (int j=0; j < k; j++) {
            if ((x[j]==i) || (Math.abs(x[j]-i) == Math.abs(j-k))) {
                //System.out.println("Invalid placement: (" + k + ", " + i + ")");
                return false;
            }
        }
        return true;
    }

    // Using backtracking, this procedure prints all possible
    // placements of n queens on an nxn chessboard so that they
    // are non-attacking.
    static int sol_count = 0;
    static int num_attempts = 0;
    static int successful_placements = 0;
    public static void NQueens(int k, int n, int x[]) {
        for (int i=0; i < n; i++) {
            if (place(k, i, x)) {
                successful_placements++;
                x[k] = i;
                if (k==n-1) {
                    System.out.print("Solution #" + (++sol_count) + ": ");
                    for (int j=0; j<n; j++)
                        System.out.print(x[j]+1 + " ");
                    System.out.println();
                } else {
                    NQueens(k+1, n, x);
                }
            }
        }
    }


    // Sub-sum problem: Find all sets of 'm' integers in vector that sum up to 'sum'
    public static void sumOfSub(int s, int k, int r, int m, ArrayList<Integer> x, ArrayList<Integer> w) {
        x.set(k,1);
        if (s + w.get(k) == m) { // Subset found
            for (int j=0; j <= k; j++) {
                //System.out.print(x.get(j) + " ");
                if (x.get(j) == 1 && j < k)
                    System.out.print(w.get(j) + " + ");
                else if (x.get(j) == 1)
                    System.out.print(w.get(j) + " = " + m);
            }
            System.out.println();
        } else if (s + w.get(k) + w.get(k+1) <= m) {
            sumOfSub(s+w.get(k), k+1, r-w.get(k), m, x, w);
        }

        if ((s+r-w.get(k) >= m) && (s+w.get(k+1) <= m)) {
            x.set(k,0);
            sumOfSub(s, k+1, r-w.get(k), m, x, w);
        }
    }

    // Fibonacci - naive approach
    public static long fibonacci(long i) {
        if (i == 0) return 0;
        if (i == 1) return 1;
        return fibonacci(i - 1) + fibonacci(i - 2);
    }

    // Fibonacci with memoization
    public static long fibonacci_quick(int n) {
        return fibonacci_quick(n, new long[n + 1]);
    }

    public static long fibonacci_quick(int i, long[] memo) {
        if (i == 0 || i == 1) return i;

        if (memo[i] == 0) {
            memo[i] = fibonacci_quick(i - 1, memo) + fibonacci_quick(i - 2, memo);
        }
        return memo[i];
    }


    // Implementation of famous series by John Conway ..
    public static void lookAndSay() {

        ArrayList<Integer> old_seq = new ArrayList<Integer>();
        old_seq.add(1);
        int count = 0;
        int dig = 1;

        while (old_seq.size() < 100) {

            ArrayList<Integer> new_seq = new ArrayList<Integer>();
            for (int i = 0; i < old_seq.size(); ++i) {
                if (old_seq.get(i) != dig) {
                    new_seq.add(count);
                    new_seq.add(dig);
                    count = 1;
                    dig = old_seq.get(i);
                } else {
                    count++;
                }
            }
            new_seq.add(count);
            new_seq.add(dig);

            System.out.println(new_seq);
            old_seq = new_seq; // OBS: Copy by reference semantics.

            count = 0;
            dig = old_seq.get(0);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Cracking the Code Interview: Ex 1.1
    public static boolean areCharsUnique(String str) {

        Character[] chars = new Character[str.length()];
        for (int i = 0; i < chars.length; i++)
            chars[i] = str.charAt(i);
        Arrays.sort(chars);
        Character prev = chars[0];

        for (int i = 1; i < chars.length; ++i) {
            if (chars[i] == prev) return false;
            else prev = chars[i];
        }
        return true;
    }

    // Given two strings, check whether one is a permutation of the other.
    public static boolean isPermutationofOther(String str1, String str2) {

        if ( (str1.length() != str2.length()) || str1.length() == 0) return false; // Permutation not possible

        // Sort both strings and scan through them in parallel and compare
        Character[] chars1 = new Character[str1.length()];
        Character[] chars2 = new Character[str2.length()];
        for (int i = 0; i < chars1.length; i++) {
            chars1[i] = str1.charAt(i);
            chars2[i] = str2.charAt(i);
        }
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) return false;
        }
        return true;
    }

    public static boolean isPermutationofOtherAlt(String str1, String str2) {

        if (str1.length() != str2.length() || str1.length() == 0) return false; // Permutation not possible

        // For each string, build a hash of <Char, freq> pairs
        HashMap<Character, Integer> strMap1 = new HashMap<>();
        HashMap<Character, Integer> strMap2 = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            if (!strMap1.containsKey(str1.charAt(i))) {
                strMap1.put(str1.charAt(i), 1);
            } else {
                strMap1.put(str1.charAt(i), strMap1.get(str1.charAt(i))  + 1);
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            if (!strMap2.containsKey(str2.charAt(i))) {
                strMap1.put(str2.charAt(i), 1);
            } else {
                strMap1.put(str2.charAt(i), strMap2.get(str2.charAt(i))  + 1);
            }
        }

        for (HashMap.Entry<Character, Integer> entry : strMap1.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (value != strMap2.get(key)) return false;
        }

        return true;
    }

    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Is a permutation: " + isPermutationofOtherAlt("abd", "dba"));

        //System.out.println("Are chars unique: " + areCharsUnique("abs"));

        //lookAndSay();

        //System.out.println(fibonacci_quick(50));

//		if (args.length < 1) {
//			System.out.println("Usage: Main <int>");
//			return;
//		}

        //System.out.println(factorial(10));

        // Test: Sub-sum problem
//		final int m = 19; // Sum we are looking for
//		ArrayList<Integer> w = new ArrayList<Integer>(Arrays.asList(22, 17, 4, 1, 25, 9, 6, 13, 12, 3, 2, 10));
//		w.sort(null); // Algorithm assumes that weights are sorted in non-descending order.
//		ArrayList<Integer> x = new ArrayList<Integer>(w.size()); // x[i] == 0 => w[i] not to be incl. in sum and vice-versa
//		for (int i=0; i < w.size(); i++) x.add(0);
//		// Sum of all weights to be passed along
//		int weight_sum = 0;
//		for (int i=0; i< w.size(); i++) weight_sum += w.get(i);
//		System.out.println("m = " + m + ", weight vector: " + w);
//		sumOfSub(0, 0, weight_sum, m, x, w);

        //Test: NQueens problem
//        final int n = 9;
//        int x[] = new int[n];
//        for (int i=0; i<n; i++) x[i] = -1;
//        NQueens(0, n, x);
//        System.out.println( "#placement attempts = " + num_attempts + ", #succesful placements = " +
//                successful_placements + ", #solutions = " + sol_count );

        // Test: Towers of Hanoi
//		final int num_disks = 3;
//		move_disks(peg.A, peg.C, peg.B, num_disks, num_disks);

        // Test: Calculate permutations
//		System.out.println("===== Permutations =====");
//		int N = 3; //Integer.parseInt(args[0]);
//		int perm[] = new int[N];
//		ArrayList<Character> arr = new ArrayList<Character>();
//		for (int i = 0; i < N; i++) {
//			perm[i] = i;
//			arr.add((char)(i+97));
//		}
//		doPerm(arr, perm, 0, N-1);

//
    }

}
