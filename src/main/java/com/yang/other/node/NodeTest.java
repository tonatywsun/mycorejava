package com.yang.other.node;

public class NodeTest {

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<Integer>();
        Node<Integer> node2 = new Node<Integer>();
        Node<Integer> node3 = new Node<Integer>();
        Node<Integer> node4 = new Node<Integer>();
        Node<Integer> node5 = new Node<Integer>();
        Node<Integer> node6 = new Node<Integer>();
        node1.setValue(5);
        node1.next = node2;
        node2.setValue(7);
        node2.next = node3;
        node3.setValue(9);
        node3.next = node4;
        node4.setValue(4);
        node4.next = node5;
        node5.setValue(2);
        node5.next = node6;
        node6.setValue(6);
        for (Node<Integer> i = node1; i != null; i = i.next) {
            System.out.print(i.getValue() + "-->");
        }
        System.out.println();
        Node<Integer> mergeSort = new NodeTest().mergeSort(node1);
        for (Node<Integer> i = mergeSort; i != null; i = i.next) {
            System.out.print(i.getValue() + "-->");
        }
    }

    private Node<Integer> mergeSort(Node<Integer> head) {
        // 递归退出的条件 当归并的元素为1个的时候 即 head.next 退出递归
        if (head == null || head.next == null) {
            return head;
        }
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        // 寻找 mid 值
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<Integer> left = head;
        Node<Integer> right = slow.next;

        // 拆分两个链表 如果设置链表的最后一个元素指向 null 那么 left 永远等于 head 这链表 也就无法排序
        slow.next = null;

        // 递归的划分链表
        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private Node<Integer> merge(Node<Integer> l, Node<Integer> r) {
        // 创建临时空间
        Node<Integer> aux = new Node<Integer>();
        Node<Integer> cur = aux;
        // 由于链表不能方便的拿到链表长度 所以一般使用 while l == null 表示链表遍历到尾部
        while (l != null && r != null) {
            if (l.value < r.value) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        // 当有一半链表遍历完成后 另外一个链表一定只剩下最后一个元素（链表为基数）
        if (l != null) {
            cur.next = l;
        } else if (r != null) {
            cur.next = r;
        }
        return aux.next;
    }

}
