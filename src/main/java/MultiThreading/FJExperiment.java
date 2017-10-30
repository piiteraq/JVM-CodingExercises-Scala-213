package MultiThreading;

import java.util.concurrent.*;

//A ForkJoin task that transforms the elements in
//an array of doubles into their square roots.
class Transform extends RecursiveAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    final int seqThreshold;
    double[] data;
    int start, end;

    Transform(double[] vals, int s, int e, int t) {
        data = vals;
        start = s;
        end = e;
        seqThreshold = t;
    }

    // This is the method in which parallel computation will occur
    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                if ((data[i] % 2) == 0)
                    data[i] = Math.sqrt(data[i]);
                else
                    data[i] = Math.cbrt(data[i]);
            }
        } else { // Continue breaking problem into smaller pieces
            int middle = (start + end) / 2;
            invokeAll( new Transform(data, start, middle, seqThreshold),
                    new Transform(data, middle, end, seqThreshold)	);
        }
    }
}


public class FJExperiment {
    public static void main(String[] args) {
        int pLevel = 4;
        int threshold = 1000;
        long beginT, endT;

        // Create a task pool
        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[100000000];

        // Give nums some values
        for (int i = 0; i < nums.length; i++)
            nums[i] = (double)i;

        System.out.println("A portion of the original sequence:");
        for (int i = 0; i < 10; i++)
            System.out.print(nums[i] + " ");
        System.out.println("\n");

        Transform task = new Transform(nums, 0, nums.length, threshold);

        // Start timing
        beginT = System.nanoTime();

        // Start the main ForkJoin task
        //fjp.invoke(task);
        fjp.execute(task);

        // Display the state of the pool while waiting
        while (!task.isDone()) {
            System.out.println(fjp);
            try {
                Thread.sleep(500); //TimeUnit.MICROSECONDS.sleep(5000);
            } catch (InterruptedException e) {
                ;
            };
        }


        // End timing
        endT = System.nanoTime();

        System.out.println( "A portion of the transformed sequence" +
                " to 4 decimal places:");

        for (int i = 0; i < 10 ; i++)
            System.out.format("%.4f ", nums[i]);
        System.out.println();

        System.out.println("Level of parallelism: " + pLevel);
        System.out.println("Sequential threshold: " + threshold);
        System.out.println("Elapsed time: " + (endT - beginT) + " ns");
        System.out.println();
    }
}
