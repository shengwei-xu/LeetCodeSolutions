package leetcode;

public class T0019_Remove_Nth_Node_From_End_of_List {
    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * 说明：
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre 指向要删除节点的前一个节点
        ListNode pre = dummy, fast = dummy;
        // fast 指针先移动 n + 1 步, 这样 pre 可以指向需要删除节点的前一个节点
        for (int i = 0; i < n + 1 && fast != null; ++i) fast = fast.next;

        while (fast != null) {
            pre = pre.next;
            fast = fast.next;
        }

        pre.next = pre.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
