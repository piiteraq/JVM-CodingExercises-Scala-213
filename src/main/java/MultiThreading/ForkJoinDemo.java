package MultiThreading;

// A simple example of the basic divide-and-conquer strategy
// using 'RecursiveAction'.

import java.util.concurrent.*;

// A ForkJoin task that transforms the elements in
// an array of doubles into their square roots.
class SqrtTransform extends RecursiveAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    final int seqThreshold = 1000;
    double[] data;
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    // This is the method in which parallel computation will occur
    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++)
                data[i] = Math.sqrt(data[i]);
        } else { // Continue breaking problem into smaller pieces
            int middle = (start + end) / 2;
            //TEST -- TBD
            invokeAll( new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end)	);
        }
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) {
        // Create a task pool
        ForkJoinPool fjp = ForkJoinPool.commonPool() ;//new ForkJoinPool();

        double[] nums = new double[100000000];

        // Give nums some values
        for (int i = 0; i < nums.length; i++)
            nums[i] = (double)i;

        System.out.println("A portion of the original sequence:");
        for (int i = 0; i < 10; i++)
            System.out.print(nums[i] + " ");
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        task.invoke(); //fjp.invoke(task);
        System.out.println( "A portion of the transformed sequence" +
                " to 4 decimal places:");

        for (int i = 0; i < 10 ; i++)
            System.out.format("%.4f ", nums[i]);
        System.out.println();
    }
}
