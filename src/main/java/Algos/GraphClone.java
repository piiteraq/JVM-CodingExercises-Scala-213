package Algos;




public class GraphClone {

    // Method for cloning a graph
    // TODO --


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

        System.out.println("Old Graph:");
        graph.dump();
        Graph cloneGraph = graph.gclone();
        System.out.println("New Graph:");
        cloneGraph.dump();
        System.out.println("Old Graph, once more:");
        graph.dump();

        // Test
        System.out.println("cloneGraph == graph: " + (cloneGraph == graph));
        System.out.println("cloneGraph.nodes[0] == graph.nodes[0]: " +
                (cloneGraph.nodes[0].children == graph.nodes[0].children));
        System.out.println((cloneGraph.nodes[0].children.get(0) == graph.nodes[0].children.get(0)));
    }

}
