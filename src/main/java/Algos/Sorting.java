/**
 * Created by petec on 6/18/16.
 */

package Algos;

class Sorting {

    // 1. Insertion sort
    static void insertionSort(int [] a) {
        for (int j=1; j < a.length; j++) {
            int key = a[j];
            int i = j-1;
            while (i > -1 && a[i] > key) {
                a[i+1] = a[i];
                i = i - 1;
            }
            a[i+1] = key;
        }
    };
    static void isDriv() {
        int [] a = {1,6,9,2,4,8,5,9,2};

        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();

        insertionSort(a);
        for (int i = 0; i < a.length; ++i) {
            if (i == 0) System.out.print("Insertion sort: ");
            System.out.print(a[i] + ", ");
        }
        System.out.println();
    }

    // 2. Merge sort
    static void merge(int [] a, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;
        int [] L = new int[n1+1] ;
        int [] R = new int[n2+1];

        for (int i = 0; i < n1; ++i) { L[i] = a[p+i]; }
        for (int i = 0; i < n2; ++i) { R[i] = a[q+i+1]; }
        // Add sentinels (assume only positive ints in orig. array)
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
        }
    }

    static void mergeSort(int [] a, int p, int r) {
        if (p < r) {
            int q = (int)(Math.floor((double)((p + r)/2)));
            mergeSort(a, p, q);
            mergeSort(a, q+1, r);
            merge(a, p, q, r);
        }
    }

    static void msDriv() {
        int [] a = {6,1,9,2,4,8,5,9,2};

        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();

        mergeSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; ++i) {
            if (i == 0) System.out.print("Merge sort: ");
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String [] args) {
        isDriv(); // run insert sort
        msDriv(); // run merge sort
    }
}
