package Algos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
    Node[] nodes;

    Graph(Node[] nodes) {
        this.nodes = nodes;
    }

    public void initNodes() {
        for (Node nd : nodes) {
            nd.visited = false;
            nd.predecessor = null;
        }
    }
}

class Node {
    public String name;
    public ArrayList<Node> children;
    public boolean visited;
    public Node predecessor;

    Node(String name) {
        this.name = name;
        this.children = new ArrayList<Node>(8);
        this.visited = false;
        this.predecessor = null;
    }

    void addChildren(Node ... childArr) {
        for (Node nd : childArr) this.children.add(nd);
    }

    void printPredecessorPath() {
        Node pd = this;
        ArrayList<Node> path = new ArrayList<>();

        while (pd != null) {
            path.add(pd);
            pd = pd.predecessor;
        }

        for (int i = path.size()-1; i >=0; i--) {
            if (i == 0) System.out.print(path.get(i).name);
            else System.out.print(path.get(i).name + "->");
        }
    }
}

public class GLM_4_1 {

    // Depth-first solution
    private static boolean findPathDF(ArrayList<Node> path, Node target) {

        boolean found = false;

        if (path.get(path.size()-1).visited) return found;

        if (path.get(path.size()-1) == target) {
            System.out.println("Path from node " + path.get(0).name + " to node " + target.name + " found: ");
            for (int i = 0; i < path.size(); i++) {
                if (i < path.size()-1)
                    System.out.print(path.get(i).name + "->");
                else
                    System.out.println(path.get(i).name);
            }
            target.visited = true; // OBS: There could be multiple paths to target, but we're only interested in the first found
            return true;
        } else {
            for (Node nd : path.get(path.size()-1).children) {
                path.add(nd);
                found = findPathDF(path, target);
                if (found) break;
            }
            path.get(path.size()-1).visited = true;
        }

        return found;
    }

    // Breadth-first solution
    private static boolean findPathBF(Graph graph, Node startNode, Node targetNode) {

        if (startNode == targetNode) return true;

        boolean found = false;
        graph.initNodes();
        Queue<Node> q = new LinkedList<>();
        q.add(startNode);

        startNode.visited = true;

        while (!q.isEmpty()) {
            Node nd = q.remove();

            for (Node ch : nd.children) {
                if (!ch.visited) {

                    ch.predecessor = nd; // Add parent node to predescessor field

                    if (ch == targetNode) {
                        return true;
                    } else {
                        ch.visited = true;
                        q.add(ch);
                    }
                }
            }
        }

        return found;
    }


    public static void main(String[] args) {

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");

        Node[] narr = {A, B, C, D, F, G};
        Graph graph = new Graph(narr);

        A.addChildren(B,C);
        B.addChildren(D,G);
        C.addChildren(A);
        D.addChildren(F,B);
        E.addChildren(C, D);
        F.addChildren(G,B);
        //G.addChildren(A);

        Node n1 = A;
        Node n2 = G;

        System.out.println("A. ===== Depth-first search =====");
        ArrayList<Node> path = new ArrayList<>(10);
        path.add(n1);
        if (!findPathDF(path, n2)) {
            System.out.println("No path from node " + n1.name + " to node " + n2.name);
            path.clear();
            graph.initNodes();
            path.add(n2);
            if (!findPathDF(path, n1)) {
                System.out.println("No path from node " + n2.name + " to node " + n1.name + " either.");
            }
        }

        System.out.println("B. ===== Breadth-first search =====");
        if (!findPathBF(graph, n1, n2)) {
            System.out.println("No path from node " + n1.name + " to node " + n2.name);
            if (!findPathBF(graph, n2, n1)) {
                System.out.println("No path from node " + n2.name + " to node " + n1.name + " either.");
            } else n1.printPredecessorPath();
        } else {
            for (Node nd : graph.nodes) {
                System.out.println("Node: " + nd.name + ", Pred: " + ((nd.predecessor != null) ? nd.predecessor.name : "NULL"));
            }
            n2.printPredecessorPath();
        }

    }


}
