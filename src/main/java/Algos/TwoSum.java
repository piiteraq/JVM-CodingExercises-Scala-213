package Algos;

import java.util.Map;
import java.util.HashMap;


// Given an array of integers, return indices of the two numbers such that they
// add up to a specific target. You may assume that each input would have exactly
// one solution, and you may not use the same element twice.
public class TwoSum {

    // Brute force approach: O(N^2)
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int i=0, j=0;
        for (i=0; i < nums.length; i++) {
            for (j=i+1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return(res);
                }
            }
        }

        res[0] = -1;
        res[1] = -1;
        return res;
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int [] nums = { 2, 6, 9, 3 };
        int[] res = twoSum1(nums, 15);
        System.out.println(res[0] + ", " + res[1]);
    }

}
