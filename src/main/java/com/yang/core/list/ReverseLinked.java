package com.yang.core.list;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/11/19 11:38
 */
public class ReverseLinked {
    public static void main(String[] args) {
        new ReverseLinked().test();
    }

    public void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        reverse(node1);
    }

    class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
        }
    }

    public static Node reverse(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node cur = root;
        while (cur.next != null) {
            cur = cur.next;
        }
        helper(root, root.next);
        return cur;
    }

    //反转两个Node，并把curr的next置位空，
    public static void helper(Node curr, Node next) {
        if (next == null) {
            return;
        }
        helper(next, next.next);
        curr.next = next.next;
        next.next = curr;
    }
}
