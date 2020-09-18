package leetcode;

public class T0142_Linked_List_Cycle_II {

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */

    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        // determine whether there is a ring
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        // make fast pointer be slow and reset to head
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
