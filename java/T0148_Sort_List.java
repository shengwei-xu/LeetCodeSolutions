package leetcode;

import java.util.LinkedList;

public class T0148_Sort_List {

    /**
     * 148. 排序链表
     * Medium
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * <p>
     * 示例 1:
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * <p>
     * 示例 2:
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     */

    // Time O(nlgn), Space O(1)
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = head;
        int len = 0;
        while (p != null) {
            ++len;
            p = p.next;
        }

        for (int step = 1; step < len; step *= 2) {
            // tail 是上一步的尾节点
            ListNode tail = dummy, cur = dummy.next;
            int num;
            while (cur != null) {
                ListNode left = cur;
                num = step - 1;
                while (cur != null && num != 0) {
                    --num;
                    cur = cur.next;
                }
                if (cur == null) {
                    tail.next = left;
                } else {
                    // 断左右
                    ListNode right = cur.next;
                    cur.next = null;
                    cur = right;

                    // 断右与右的后续节点
                    num = step - 1;
                    while (cur != null && num != 0) {
                        --num;
                        cur = cur.next;
                    }
                    if (cur != null) {
                        ListNode tmp = cur;
                        cur = cur.next;
                        tmp.next = null;
                    }

                    // 左右合并
                    merge(tail, left, right);
                }
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dummy.next;
    }

    private static void merge(ListNode tail, ListNode left, ListNode right) {
        if (right == null) {
            tail.next = left;
        } else {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            ListNode p = tail.next;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    p.next = left;
                    left = left.next;
                } else {
                    p.next = right;
                    right = right.next;
                }
                p = p.next;
            }
            if (left != null) p.next = left;
            if (right != null) p.next = right;
        }
    }

    // Time O(nlgn), Space O(lgn)
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightPartHead = slow.next;
        slow.next = null;

        ListNode p1 = sortList(head);
        ListNode p2 = sortList(rightPartHead);

        // if (p1 == null || p2 == null) return p1 == null ? p2 : p1; // no need

        if (p1.val < p2.val) {
            head = p1;
            p1 = p1.next;
        } else {
            head = p2;
            p2 = p2.next;
        }

        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 != null) p.next = p1;
        if (p2 != null) p.next = p2;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        sortList(head);
    }
}
