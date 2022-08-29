import java.util.Random;

public class MergeLL {
    private class ListNode {
        public Comparable info;
        public ListNode next;
        public ListNode(Comparable info_) {
            info = info_;
            next = null;
        }
    }
    public ListNode head;

    public boolean less(Comparable a, Comparable b) { 
        return a.compareTo(b) < 0;
    }

    public void traverse(ListNode h) {
        if (h != null) {
            System.out.print(h.info + " ");
            traverse(h.next);
        } else {
            System.out.println();
        }
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode t = dummy;
        while (a != null && b != null) {
            if (less(a.info, b.info)) {
                t.next = a;
                a = a.next;
                t = t.next;
            } else {
                t.next = b;
                b = b.next;
                t = t.next;
            }
        }
        t.next = (a != null) ? a:b;
        return dummy.next;
    }

    public ListNode mergesort(ListNode h) {
        if (h == null || h.next == null)
            return h;
        ListNode fast = h.next;
        ListNode slow = h;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode front = h;
        ListNode back = slow.next;
        slow.next = null;
        System.out.print("Front: ");
        traverse(front);
        System.out.print("Back: ");
        traverse(back);
        return merge(mergesort(front), mergesort(back));
    }
    public void sort() {
        head = mergesort(head);
    }
    public void makeRandomList() {
        Random rng = new Random();
        for (int i = 0; i < 10; i++) {
            ListNode t = new ListNode(rng.nextInt(100));
            t.next = head;
            head = t;
        }
    }
    public static void main(String[] args) {
        MergeLL mll = new MergeLL();
        mll.makeRandomList();
        mll.traverse(mll.head);
        mll.sort();
        mll.traverse(mll.head);
    }
}