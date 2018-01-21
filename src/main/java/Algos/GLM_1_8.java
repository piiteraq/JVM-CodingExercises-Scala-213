package Algos;

import java.util.ArrayList;

public class GLM_1_8 {

    static void dump(int[][] mat) {
        System.out.println("-----");
        for (int i=0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void zeroRow(int[][] mat, final int row) {
        for (int j = 0; j < mat[row].length; j++) mat[row][j] = 0;
    }

    static void zeroCol(int[][] mat, final int col) {
        for (int i = 0; i < mat.length; i++) mat[i][col] = 0;
    }


    static void voidSetZeros(int[][] mat) {

        // Two dynamic arrays for storing rolws, cols to zero
        ArrayList<Integer> rowsToZero = new ArrayList<>();
        ArrayList<Integer> colsToZero = new ArrayList<>();

        // Pass #1: Detect rows, cols to zero
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    rowsToZero.add(i);
                    colsToZero.add(j);
                }
            }
        }

        // Pass #2: Use markings from above to do the zero'ing
        for (Integer row : rowsToZero) {
            zeroRow(mat, row);
        }
        for (Integer col : colsToZero) {
            zeroCol(mat, col);
        }

    }


    public static void main(String[] args) {


        int mat[][] = {
                {1 , 2 , 3,  0,  5},
                {6 , 7 , 8,  9,  10},
                {11, 0,  13, 14, 15},
                {61, 17, 18, 19, 20} };

        dump(mat);
        voidSetZeros(mat);
        dump(mat);
    }

}
