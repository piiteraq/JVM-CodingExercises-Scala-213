package Algos;

  // Definition for singly-linked list.
  class ListNode {
      int val;
      ListNode next;
    ListNode(int x) { val = x; }
  }

public class AddTwoNumbers {

      // My solution ..
      public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = null;

        // Check for corner cases
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        else { // Traverse l1 and l1, forming the sum ..

            int carry = 0;
            ListNode p1 = l1;
            ListNode p2 = l2;

            while (p1 != null && p2 != null) {

                System.out.println("p1: " + p1.val + ", p2: " + p2.val + ", c: " + carry);
                int tmp = p1.val + p2.val + carry;
                int sum = tmp % 10;
                System.out.println("sum: " + sum);
                carry = tmp / 10;
                System.out.println("carry: " + carry);
                ListNode nn = new ListNode(sum);
                if (res == null) {
                    nn.next = null;
                    res = nn;
                } else {
                    ListNode rp = res;
                    while (rp.next != null) rp = rp.next;
                    rp.next = nn;
                }
                p1 = p1.next;
                p2 = p2.next;
            }

            // If one addend has more than the others, complete the addition with the remainder of the longer addend
            ListNode pr = (p1 != null) ? p1 : (p2 != null) ? p2 : null;
            while (pr != null) {
                int tmp = pr.val + carry;
                int sum = tmp % 10;
                carry = tmp / 10;
                ListNode nn = new ListNode(sum);
                ListNode rp = res;
                while (rp.next != null) rp = rp.next;
                rp.next = nn;
                pr = pr.next;
            }

            // If the very last addition generated a carry, we need to add it here
            if (carry == 1) {
                ListNode nn = new ListNode(1);
                ListNode rp = res;
                while (rp.next != null) rp = rp.next;
                rp.next = nn;
            }
        }

        return(res);
    }

    // Solution from "Leetcode"
    public ListNode addTwoNumbersLC(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }



//    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//    Output: 7 -> 0 -> 8
//    Explanation: 342 + 465 = 807.
    public static void main(String[] args) {

        ListNode l11 = new ListNode(3);
        l11.next = null;
        ListNode l12 = new ListNode(4);
        l12.next = l11;
        ListNode l13 = new ListNode(2);
        l13.next = l12;

        ListNode l21 = new ListNode(4);
        l21.next = null;
        ListNode l22 = new ListNode(6);
        l22.next = l21;
        ListNode l23 = new ListNode(5);
        l23.next = l22;

        ListNode res = addTwoNumbers(l13, l23);

        ListNode pp = res;
        while(pp != null) {
            System.out.print(pp.val);
            pp = pp.next;
        }
        System.out.println();

        ListNode ll11 = new ListNode(5);
        ll11.next = null;

        ListNode ll21 = new ListNode(5);
        ll21.next = null;

        ListNode ress = addTwoNumbers(ll11, ll21);

        pp = ress;
        while(pp != null) {
            System.out.print(pp.val);
            pp = pp.next;
        }
        System.out.println();

    }

}
