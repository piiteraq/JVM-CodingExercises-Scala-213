package DataStructures;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by petec on 6/28/16.
 */
class Link {
    int val = 0;
    Link next = null;
    Link(int val, Link next) { this.val = val; this.next = next; };
}

public class LinkedList {

    Link head_ = null;
    int size_ = 0;

    LinkedList() { head_ = null; size_ = 0; };

    void insert(int val) {
        Link tmp = new Link(val, head_);
        head_ = tmp;
        size_++;
    }

    void display() {
        Link tmp = head_;
        System.out.print("Size: " + size_ + ": ");
        while (tmp != null) {
            System.out.print(tmp.val + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    void revert() {
        if (size_ <= 1) {
            return;
        } else if (size_ == 2) {
            Link tmp = head_.next;
            tmp.next = head_;
            head_.next = null;
            head_ = tmp;
        } else {
            Link tmp1 = head_;
            Link tmp2 = head_.next;
            Link tmp3 = head_.next.next;

            tmp1.next = null;
            do {
                tmp2.next = tmp1;
                tmp1 = tmp2;
                tmp2 = tmp3;
                tmp3 = tmp3.next;
            } while (tmp3 != null);
            tmp2.next = tmp1;
            head_ = tmp2;
        }
    }

    void remove(int val, boolean allOccurrences) {

        if (head_ == null) { // Empty list
            return;
        } else if (head_.next == null) { // Only one elem in list
            if (head_.val == val) {
                head_ = null;
                size_--;
            }
        } else { // More than one elem in list

            while (head_ != null && head_.val == val) {
                Link tmp = head_;
                head_ = head_.next;
                tmp.next = null;
                size_--;
                if (!allOccurrences) return;
            }

            Link tmp1 = head_;
            Link tmp2 = head_.next;
            while (tmp2 != null) {

                if (tmp2.val == val) {
                    tmp1.next = tmp2.next;
                    tmp2.next = null;
                    tmp2 = tmp1.next;
                    size_--;
                    if (!allOccurrences) break;
                } else {
                    tmp1 = tmp2;
                    tmp2 = tmp2.next;
                }

            }
        }
    }

    // Remove duplicates from list. Assume list is unsorted.
    void removeDups() {

        // HashMap of <value, frequency> pairs
        HashMap<Integer, Integer> seenVals = new HashMap<>();

        // Count the number of occurrences of each value in
        // linked list and store in <value, frequency> hash.
        Link tmp = head_;
        while (tmp != null) {
            if (!seenVals.containsKey(tmp.val)) {
                seenVals.put(tmp.val, 1);
            } else {
                seenVals.put(tmp.val, seenVals.get(tmp.val) + 1);
            }
            tmp = tmp.next;
        }

        // Now, use 'seenVals' to remove duplicates of each value
//        for (HashMap.Entry<Integer, Integer> entry : seenVals.entrySet()) {
//            Integer val = entry.getKey();
//            Integer freq = entry.getValue();
//            for (int i = freq; i > 1; i--) { this.remove(val, false); }
//        }

        // Alternative way: build a new list with duplicates filtered out.
        tmp = head_;
        Link new_head = null;
        int new_size = 0;
        while (tmp != null) {
            if (seenVals.get(tmp.val) != -1) { // Haven't seen this instance before
                Link nl = new Link(tmp.val, new_head);
                new_head = nl;
                new_size++;
                seenVals.put(tmp.val, -1); // Mark this value as seen
            }
            tmp = tmp.next;
        }
        head_ = new_head;
        size_ = new_size;
        this.revert();
    }

    public static void main(String[] args) {
        LinkedList lst = new LinkedList();
        for (int i=0; i < 4; ++i) {
            lst.insert(1);
        }
        for (int i=0; i < 10; ++i) {
            lst.insert(i);
            lst.insert(i+1);
        }
        lst.revert();
        lst.display();
        lst.removeDups();
        lst.display();
        //lst.revert();
        //lst.display();
        //lst.remove(1, true);
        //lst.display();
        System.out.println("Done.");
    }
}
