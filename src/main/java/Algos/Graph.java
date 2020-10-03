package Algos;

import java.util.ArrayList;

class Graph {
    Node[] nodes;

    Graph(Node[] nodes) {
        this.nodes = nodes;
    }

    void dump() {
        for (Node nd : nodes) {
            System.out.print("Node: " + nd.name + ", Children: ");
            for (Node chld : nd.children) System.out.print(chld.name + " ");
            System.out.println();
        }
    }

    @SuppressWarnings("unchecked")
    Graph gclone() {
        Node[] nodesClon = nodes.clone();

        for (int i = 0; i < nodesClon.length; i++) nodesClon[i] = new Node(nodes[i].name + "'");

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].children != null) {
                nodesClon[i].children = (ArrayList<Node>) nodes[i].children.clone();
                for (int j = 0; j < nodesClon[i].children.size(); j++) {
                    nodesClon[i].children.set(j, new Node(nodes[i].children.get(j).name + "'"));
                }
            } else
                nodesClon[i].children = null;
        }
        return(new Graph(nodesClon));
    }

    public void initNodes() {
        for (Node nd : nodes) {
            nd.visited = false;
            nd.predecessor = null;
        }
    }
}

