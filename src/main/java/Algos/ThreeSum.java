package Algos;

import java.util.*;

// Given an array S of n integers, are there elements a, b, c in S
// such that a + b + c = 0? Find all unique triplets in the array which
// gives the sum of zero.
// For example, given array S = [-1, 0, 1, 2, -1, -4],
//
//        A solution set is:
//        [
//        [-1, 0, 1],
//        [-1, -1, 2]
//        ]

// Assume:
// - Multiple instances of a number is allowed
// Corner cases:
// - Only positive or only negative numbers in array: no solution
// Note: The solution set must not contain duplicate triplets.

public class ThreeSum {

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { nums[i], nums[map.get(complement)] };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    static ArrayList<ArrayList<Integer>> threeSum(int[] nums) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        // Sanity check: nums must have size >= 3.
        if (nums.length < 3) {
            System.out.println("Invalid input: array must have at least size 3");
            return res;
        }

        // Pre-sort the array in O(n*log(n)) time
        Arrays.sort(nums);
        System.out.println("Sorted: "); // -4, -1, -1, 0, 1, 2

        // Trivial case: only positive or only negative numbers in array: No solution
        if (nums[0] > 0 || nums[nums.length-1] < 0) return res;

        // Pick one of the numbers in the array and then call "Two-Sum" on
        // the rest of the array, using the the sign-reversed selected number
        // as the target
        for (int i = 0; i < nums.length - 2; i++) {
            try {
                int[] twoSumSol = twoSum(Arrays.copyOfRange(nums, i + 1, nums.length), -nums[i]);
                System.out.print(nums[i] + ", ");
                for (int j : twoSumSol) System.out.print(j + ", ");
                System.out.println();
            } catch (Exception e) {
                // Ignore
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int [] nums = { -1, 0, 1, 2, -1, -4 };
        ArrayList<ArrayList<Integer>> res = threeSum(nums);
        System.out.println("Result:");
        for (List<Integer> lst : res) {
            System.out.println("\t" + lst);
        }
    }

}
