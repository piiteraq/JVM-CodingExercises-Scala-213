package Java8Book;

/**
 * Created by petec on 7/12/16.
 */

import java.util.*;
import java.util.stream.*;
import static java.util.Arrays.asList;


class StreamDemo {

    // Create shorthand for 'System.out.println'
    public static void p(Object... args) {
        for (Object arg : args) {
            System.out.println(arg);
        }
    }


    public static void main(String[] args) {

        ArrayList<Integer> myList = new ArrayList<>(asList(7, 18, 10, 24, 17, 5));

        p("Original list:" + myList);

        // Derive a stream from list
        Stream<Integer> myStream = myList.stream();

        // Obtain min and max
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if (minVal.isPresent()) p("Min Value: " + minVal.get());

        // Must obtain new stream ..
        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        if (maxVal.isPresent()) p("Max Value: " + maxVal.get());

        // Sort stream ..
        Stream<Integer> sortedStream = myList.stream().sorted();
        p("Sorted stream: ");
        sortedStream.forEach((n) -> System.out.print(n + " "));
        p("");

        // Display only odd values ..
        Stream<Integer> oddVals =
                myList.stream().sorted().filter(n -> (n % 2) == 1);
        p("oddVals: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        p("");


        // Display odd vals greater than 5
        oddVals = myList.stream().filter(n -> (n % 2) == 1).filter(n -> n > 5);
        p("Odd values greater than 5: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        p("");

        // Reductions ..
        Optional<Integer> productOpt = myList.stream().reduce((a,b) -> a*b);
        if (productOpt.isPresent())
            p("Product as Optional: " + productOpt.get());

        int product = myList.stream().reduce(1, (a,b) -> a*b);
        p("Product as int: " + product);

        int evenProduct = myList.stream().reduce(1, (a,b) -> {
            if (b%2 == 0) return a*b; else return a;
        });
        p("Product of evens: " + evenProduct);

        // Parallel stream demo ..
        double productOfSqrRoots = myList.parallelStream().reduce(
                1.0,
                (a,b) -> a * Math.sqrt(b),
                (a,b) -> a*b);
        p("Parallel product of square roots: " + productOfSqrRoots);

        // Map demo ..
        Stream<Double> sqrtRootStrm = myList.stream().map(a -> Math.sqrt(a));
        productOfSqrRoots = sqrtRootStrm.reduce(1.0, (a,b) -> a*b);
        p("Product of square roots: " + productOfSqrRoots);
    }
}
