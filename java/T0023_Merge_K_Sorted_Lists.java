package leetcode;

public class T0023_Merge_K_Sorted_Lists {

    /**
     * 23. 合并K个排序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int lo, int hi) {
        if (lo == hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = mergeKLists(lists, lo, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, hi);
        return mergeTwoList(l1, l2);
    }

    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy, p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
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

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
