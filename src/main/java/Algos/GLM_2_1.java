package Algos;

import java.util.HashSet;

class LNode {

    LNode next = null;
    int data;

    public LNode(int d) {
        data = d;
    }

    void appendToTail(int d) {
        LNode end = new LNode(d);
        LNode n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
}

class LinkedList {

    LNode head = null;

    LinkedList() {}

    void dump() {
        LNode n = head;
        if (n != null) System.out.print("->");
        while (n != null) {
            if (n.next == null) System.out.println(n.data);
            else System.out.print(n.data + "->");
            n = n.next;
        }
    }

    void appendToTail(int d) {
        if (head == null) {
            head = new LNode(d);
        } else {
            head.appendToTail(d);
        }
    }

    boolean deleteNode(int d) {
        // Check for empty list first
        if (head == null) return false; // Not found/deleted

        LNode n = head;
        if (n.data == d) head = head.next; // Moved head

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return true; // Found/deleted
            }
            n = n.next;
        }
        return false; // Not found/deleted
    }

    // Runs in O(1) time but not O(1) space
    void deleteDups() {
        if (head == null) return;
        HashSet<Integer> seen = new HashSet<>();
        LNode n = head;
        LNode previous = null;

        while (n != null) {
            if (seen.contains(n.data)) {
                previous.next = n.next;
            } else {
                seen.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    // Runs in O(1) space but O(N^2) time
    void deleteDupsNoBuffer() {
        LNode current = head;
        while (current != null) {
            // Remove all future nodes that have the same value
            LNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) runner.next = runner.next.next;
                else runner = runner.next;
            }
            current = current.next;
        }

    }

}

public class GLM_2_1 {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.appendToTail(9);
        ll.appendToTail(11);
        ll.appendToTail(5);
        ll.appendToTail(10);
        ll.appendToTail(9);
        ll.appendToTail(5);
        ll.deleteDupsNoBuffer();
        ll.dump();
    }


}
