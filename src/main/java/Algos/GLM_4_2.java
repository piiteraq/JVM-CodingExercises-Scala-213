package Algos;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class GLM_4_2 {

    static TreeNode createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length-1);
    }

    static TreeNode createMinimalBST(int arr[], int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        System.out.println("Added node: " + n.val);
        n.left = createMinimalBST(arr, start, mid-1);
        n.right = createMinimalBST(arr, mid + 1, end);
        return n;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 5, 8, 11, 12, 15, 20, 25, 30 };
        TreeNode root = createMinimalBST(arr);
    }

}
