package Algos;

import java.util.ArrayList;

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
