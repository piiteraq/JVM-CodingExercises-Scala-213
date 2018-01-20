package Algos;

public class GLM_1_7 {

    static void dump(int[][] mat, final int M) {
        System.out.println("-----");
        for (int i=0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Build up rotated matrix in a working copy.
    static int[][] rotateNinetyClkw(int[][] mat, final int M) {
        int[][] res = new int[M][M];
        for (int i=0; i < M; i++) {
            for (int j=0; j < M; j++) {
                res[j][M-1-i] = mat[i][j];
            }
        }
        return res;
    }

    // Could also be done by affine transform, i.e. multiply by MxM of unit vector transforms.
    static void rotateNinetyClkwInPlace(int[][] mat, final int M) {

        for (int ridx=1; ridx <= M/2; ridx++) { // Ring index

            for (int i = ridx - 1; i < M - ridx; i++) { // Start position on upper edge of ring

                int or = ridx - 1, oc = ridx - 1 + i, nr = oc, nc = M - 1 - or;
                int buffer = mat[or][oc]; // Need one buffer element to back up overwritten matrix entry - O(1) space.

                while (true) {
                    int tmp = mat[nr][nc];
                    mat[nr][nc] = buffer;
                    buffer = tmp;
                    or = nr;
                    oc = nc;
                    if (or == ridx - 1 && oc == ridx - 1 + i) break; // We're back where we started ..
                    nr = oc;
                    nc = M - 1 - or;
                }
            }
        }
    }

    public static void main(String[] args) {

        final int M = 5;

        int mat[][] = new int[M][M];

        for (int i=0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                mat[i][j] = i*M + j;
            }
        }

        dump(mat, M);
        //dump(rotateNinetyClkw(mat, M), M);
        rotateNinetyClkwInPlace(mat, M);
        dump(mat,M);
    }

}
