/*
24. Swap Nodes in Pairs
Medium

Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:
Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        // no node or only one node
        if (head == nullptr || head -> next == nullptr) return head;
        
        // only two nodes
        if (head -> next -> next == nullptr) {
            ListNode* k = head;
            head = head -> next;
            k -> next = nullptr;
            head -> next = k;
            return head;
        }
        
        // three or more nodes
        
        // swap first two nodes
        ListNode* k = head;
        head = head -> next;
        k -> next = head -> next;
        head -> next = k;

        // swap other nodes
        ListNode* pre = head -> next;
        ListNode* p = pre -> next;
        ListNode* q = p -> next;

        while (p != nullptr && q != nullptr) {
            p -> next = q -> next;
            pre -> next = q;
            q -> next = p;
            // move pointers
            pre = pre -> next -> next;
            if (pre -> next == nullptr) break;
            p = pre -> next;
            q = p -> next;
        }
        
        return head;
    }

    // simplified version
    ListNode* swapPairs_2(ListNode* head) {
        if (!head) return nullptr;

        ListNode* pre = nullptr; // pre node
        ListNode* p = head;
        ListNode* q = nullptr;
        ListNode* n = nullptr; // next node

        while (p) {
            q = p -> next;
            if (q) {
                n = q -> next;
            } else { // only one nodes
                break;
            }

            if (pre) { // existing 3 or more nodes
                pre -> next = q;
                q -> next = p;
                p -> next = n;
            } else { // fisrt two nodes
                p -> next = n;
                q -> next = p;
                head = q;
            }

            pre = p;
            p = p -> next;
        }

        return head;
    }

    void printLinkedNode(ListNode* head) {
        ListNode* p = head;
        while (p != nullptr) {
            cout << p -> val << " ";
            p = p -> next;
        }
        cout << endl;
    }

};

int main() {
    Solution* s = new Solution();

    ListNode* head = new ListNode(1);
    ListNode* p = head;
    for (int i = 2; i <= 5; ++i) {
        p -> next = new ListNode(i);
        p = p -> next;
    }
    s -> printLinkedNode(head);
    // s -> printLinkedNode(s -> swapPairs(head));
    s -> printLinkedNode(s -> swapPairs_2(head));

    return 0;
}
