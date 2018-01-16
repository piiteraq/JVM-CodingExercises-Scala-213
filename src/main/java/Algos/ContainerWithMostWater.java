package Algos;

public class ContainerWithMostWater {

    // My solution - not optimal - still O(N^2)
    // Assume: array 'height' has at least 2 elements.
    private static int maxArea(int[] height) throws Exception {

        // Handle invalid input and corner cases
        if (height.length < 2) {
            throw new Exception("Invalid input: input array must have at least two elements");
        }
        if (height.length == 2) {
            return(Math.min(height[0], height[1]));
        }

        int aMax = 0;
        int llMax = -1;
        int rlMax = -1;

        for (int ll=0; ll < height.length-1; ll++) {
            int aMaxThisll = 0;
            for (int rl=height.length-1; rl > ll; rl--) {
                if (height[rl] >= height[ll]) {
                    aMaxThisll = height[ll] * (rl - ll);
                    if (aMaxThisll > aMax) {
                        aMax = aMaxThisll;
                        llMax = ll;
                        rlMax = rl;
                    }
                    break; // We can't do better than this for current ll ..
                } else {
                    aMaxThisll = Math.max(aMaxThisll, Math.min(height[ll], height[rl]) * (rl - ll));
                    if (aMaxThisll > aMax) {
                        aMax = aMaxThisll;
                        llMax = ll;
                        rlMax = rl;
                    }
                }
            }
        }
        System.out.println("Max Area:" + aMax + ", ll=" + llMax + ", rl=" + rlMax);
        return aMax;
    }


    // Solution from LeetCode. O(N).
    private static int maxAreaLeetCode(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }


    public static void main(String[] args) {
      int[] height = {2,5,4,8,1,0,3};
      try {
          System.out.println(maxAreaLeetCode(height));
      } catch (Exception e) {
          System.out.println("Caught exception: " + e);
      }
    }

}
