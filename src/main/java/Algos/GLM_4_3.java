//package Algos;
//
//import sun.awt.image.ImageWatched;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//
//class DistTreeNode {
//    public int val;
//    public int level; // Distance from root ~ level
//    public DistTreeNode left;
//    public DistTreeNode right;
//
//    DistTreeNode(int val) {
//        this.val = val;
//        level = 0;
//        left = null;
//        right = null;
//    }
//}
//
//
//public class GLM_4_3 {
//
//    // Creates a min height bin tree
//    static DistTreeNode createMinimalBST(int array[]) {
//        return createMinimalBST(array, 0, array.length-1);
//    }
//
//    static DistTreeNode createMinimalBST(int arr[], int start, int end) {
//        if (end < start) return null;
//        int mid = (start + end) / 2;
//        DistTreeNode n = new DistTreeNode(arr[mid]);
//        //System.out.println("Added node: " + n.val);
//        n.left = createMinimalBST(arr, start, mid-1);
//        n.right = createMinimalBST(arr, mid + 1, end);
//        return n;
//    }
//
//    // Creates a max height bin tree (:linked list)
//    static DistTreeNode createMaxBST(int array[]) {
//        return createMaxBST(array, 0, array.length-1);
//    }
//
//    static DistTreeNode createMaxBST(int arr[], int start, int end) {
//        if (end < start) return null;
//        DistTreeNode n = new DistTreeNode(start);
//        n.left = createMaxBST(arr, start+1, end);
//        n.right = null;
//        return n;
//    }
//
//
//    static ArrayList<LinkedList<DistTreeNode>> createLevelListBF(DistTreeNode root) {
//
//        ArrayList<LinkedList<DistTreeNode>> levelArr = new ArrayList<>();
//        Queue<DistTreeNode> q = new LinkedList<DistTreeNode>();
//        int currLevel;
//
//        if (root == null) return levelArr;
//
//        q.add(root);
//        while (!q.isEmpty()) {
//            DistTreeNode currNd = q.remove();
//            currLevel = currNd.level;
//            // Add current node to linked list, using currLevel as index
//            if (levelArr.size() < currLevel+1) {
//                levelArr.add(new LinkedList<DistTreeNode>(Arrays.asList(currNd)));
//            } else {
//                levelArr.get(currLevel).add(currNd);
//            }
//
//            if (currNd.left != null) {
//                currNd.left.level = currLevel+1;
//                q.add(currNd.left);
//            }
//
//            if (currNd.right != null) {
//                currNd.right.level = currLevel+1;
//                q.add(currNd.right);
//            }
//
//        }
//
//        return levelArr;
//    }
//
//
//    // Actually, it is not necessary to traverse the tree level wise (BF) - depth firs
//    // will also work as long as we keep track of the levels
//    static void createLevelListDF( DistTreeNode root,
//                                   ArrayList<LinkedList<DistTreeNode>> lists,
//                                   int level)
//    {
//        if (root == null) return;
//
//        LinkedList<DistTreeNode> list = null;
//        if (lists.size() == level) { // Level not contained in list, since lists.size is one beyond last valid index of array
//            list = new LinkedList<DistTreeNode>();
//            lists.add(list);
//        } else {
//            list = lists.get(level);
//        }
//        list.add(root);
//        createLevelListDF(root.left, lists, level + 1);
//        createLevelListDF(root.right, lists, level + 1);
//    }
//
//    static ArrayList<LinkedList<DistTreeNode>> createLevelListDF(DistTreeNode root) {
//        ArrayList<LinkedList<DistTreeNode>> lists = new ArrayList<LinkedList<DistTreeNode>>();
//        createLevelListDF(root, lists, 0);
//        return lists;
//    }
//
//
//    public static void main(String[] args) {
//
//        // Create a binary tree, using code from GLM 4.2
//        int[] arr = { 2, 4, 5, 8, 11, 12, 15, 20, 25, 30 };
//        DistTreeNode root = createMinimalBST(arr);
//
//        // Create the level list of tree nodes
//        ArrayList<LinkedList<DistTreeNode>> levelArr = createLevelListDF(root);
//
//        for (int i = 0; i < levelArr.size(); i++) {
//            System.out.print("Level" + i + ": ");
//            for (DistTreeNode nd : levelArr.get(i)) {
//                System.out.print(nd.val + " ");
//            }
//            System.out.println();
//        }
//    }
//
//}
