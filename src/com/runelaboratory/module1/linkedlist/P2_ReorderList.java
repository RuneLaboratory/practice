package com.runelaboratory.module1.linkedlist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P2_ReorderList {

    /*
     * Given a singly linked list L: L0→L1→ ... →Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...
     * 
     * For example, given {1,2,3,4}, reorder it to {1,4,2,3}. You must do this
     * in-place without altering the nodes' values.
     */

    public static void main(String[] args) {
    
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);
        
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = a7;
        
        System.out.println("======= solution =======");
        ListNode.printList(a1);
        ListNode.printList(solution(a1));
        
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        ListNode b5 = new ListNode(5);
        ListNode b6 = new ListNode(6);
        ListNode b7 = new ListNode(7);
        
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        b4.next = b5;
        b5.next = b6;
        b6.next = b7;
        
        System.out.println("======= ans =======");
        ListNode.printList(b1);
        ans(b1);
        ListNode.printList(b1);
    }

    private static ListNode solution(ListNode input) {

        Queue<ListNode> queue = new LinkedList<>();
        Stack<ListNode> stack = new Stack<>();

        int totalSize = 0;

        ListNode temp = input;
        while (temp != null) {
            queue.add(temp);
            stack.push(temp);
            temp = temp.next;
            totalSize++;
        }

        ListNode head = queue.remove();
        temp = head;

        int loop = 0;
        while (loop < totalSize / 2) {
            temp.next = stack.pop();
            temp = temp.next;

            temp.next = queue.remove();
            temp = temp.next;
            loop++;
        }

        temp.next = null;

        return head;
    }

    private static void ans(ListNode head) {

        if (head != null && head.next != null) {

            ListNode slow = head;
            ListNode fast = head;

            // use a fast and slow pointer to break the link to two parts. the speed x2
            // faster, slow stop at middle
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode second = slow.next;
            slow.next = null;// need to close first part

            // now should have two lists: head and fast

            // reverse order for second part
            second = reverseOrder(second);

            ListNode p1 = head;
            ListNode p2 = second;

            // merge two lists here
            while (p2 != null) {
                ListNode temp1 = p1.next;
                ListNode temp2 = p2.next;

                p1.next = p2;
                p2.next = temp1;

                p1 = temp1;
                p2 = temp2;
            }
        }
    }

    private static ListNode reverseOrder(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        ListNode curr = head.next;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        // set head node's next
        head.next = null;

        return pre;
    }
}