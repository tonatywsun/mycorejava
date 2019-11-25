package com.yang.core.list;

@SuppressWarnings("all")
public class MyLinkenList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    public void add(E e) {
        Node<E> nowNode;
        if (first == null) {
            nowNode = new Node<>(e, null, null);
            first = nowNode;
        } else {
            nowNode = new Node<>(e, last, null);
            last.next = nowNode;
        }
        last = nowNode;
        size++;
    }

    public E get(int i) {
        if (size <= i || i < 0) {
            return null;
        }
        Node<E> n = first;
        for (int j = 0; j != i; j++) {
            n = first.next;
        }
        return n.e;
    }

    public void unLink(Node n) {

        if (size <= 0) {
            return;
        }
        Node<E> next = n.next;
        Node<E> prev = n.prev;
        if (n == first) {
            if (next == null) {
                first = null;
                last = null;
            } else {
                next.prev = null;
                first = next;
            }
        } else if (n == last) {
            prev.next = null;
            last = prev;
        } else {
            next.prev = prev;
            prev.next = next;
        }
        n.next = null;
        n.prev = null;
        n.e = null;
    }

    public void remove(E e) {
        if (e == null) {
            for (Node<E> n = first; n != null; n = n.next) {
                if (n.e == null) {
                    unLink(n);
                }
            }
        } else {
            for (Node<E> n = first; n != null; n = n.next) {
                if (n.e.equals(e)) {
                    unLink(n);
                }
            }
        }
    }

    public int size() {
        return size;
    }

    private static class Node<E> {
        E e;
        Node<E> next;
        Node<E> prev;

        public Node(E e, Node<E> prev, Node<E> next) {
            super();
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

    }
}
