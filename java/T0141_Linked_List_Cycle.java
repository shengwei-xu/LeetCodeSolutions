package leetcode;

public class T0141_Linked_List_Cycle {

    /**
     * 给定一个链表，判断链表中是否有环。
     */

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
