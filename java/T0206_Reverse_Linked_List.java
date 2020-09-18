package leetcode;

public class T0206_Reverse_Linked_List {
    /**
     * 206. 反转链表
     * Easy
     *
     * 反转一个单链表。
     *
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, node = head, next;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

}
