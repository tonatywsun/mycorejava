package com.yang.other.node;

/**
 * 链表（Linked
 * list）是一种常见的基础数据结构，是一种线性表，但是并不会按线性的顺序存储数据，而是在每一个节点里存到下一个节点的指针(Pointer)，简单来说链表并不像数组那样将数组存储在一个连续的内存地址空间里，它们可以不是连续的因为他们每个节点保存着下一个节点的引用（地址），所以较之数组来说这是一个优势。
 * 单链表的特点 链表增删元素的时间复杂度为O(1),查找一个元素的时间复杂度为 O(n); 单链表不用像数组那样预先分配存储空间的大小，避免了空间浪费
 * 单链表不能进行回溯操作，如：只知道链表的头节点的时候无法快读快速链表的倒数第几个节点的值。
 * 
 * @date 2018年3月12日 下午6:24:42
 * @author tonasun
 */
public class Node<E> {
    public E value;
    public Node<E> prev;
    public Node<E> next;

    public Node() {
        super();
    }

    public Node(E value, Node<E> prev, Node<E> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * 获取单链表的长度
     * 由于单链表的存储地址不是连续的，链表并不具有直接获取链表长度的功能，对于一个链表的长度我们只能一次去遍历链表的节点，直到找到某个节点的下一个节点为空的时候得到链表的总长度，注意这里的出发点并不是一个空链表然后依次添加节点后，然后去读取已经记录的节点个数，而是已知一个链表的头结点然后去获取这个链表的长度：
     */
    public int getLength(Node<E> head) {
        if (head == null) {
            return 0;
        }
        int length = 1;
        while (head.next != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    /**
     * 查询指定索引的节点值或指定值得节点值的索引
     * 由于链表是一种非连续性的存储结构，节点的内存地址不是连续的，也就是说链表不能像数组那样可以通过索引值获取索引位置的元素。所以链表的查询的时间复杂度要是O(n)级别的，这点和数组查询指定值得元素位置是相同的，因为你要查找的东西在内存中的存储地址都是不一定的。
     */
    /** 获取指定角标的节点值 */
    public E getValueOfIndex(Node<E> head, int index) throws Exception {
        if (index < 0 || index >= getLength(head)) {
            throw new Exception("角标越界！");
        }
        Node<E> dummyHead = head;
        while (dummyHead.next != null && index > 0) {
            dummyHead = dummyHead.next;
            index--;
        }
        return dummyHead.value;
    }

    /**
     * 获取节点值等于 value 的第一个元素角标
     * 
     * @throws Exception
     */
    public int getNodeIndex(Node<E> head, E value) throws Exception {
        if (head == null) {
            throw new Exception("当前链表为空！");
        }
        int index = -1;
        Node<E> dummyHead = head;
        if (value == null) {
            while (dummyHead != null) {
                index++;
                if (dummyHead.value == null) {
                    return index;
                }
                dummyHead = dummyHead.next;
            }
        } else {
            while (dummyHead != null) {
                index++;
                if (dummyHead.value.equals(value)) {
                    return index;
                }
                dummyHead = dummyHead.next;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + ", prev=" + prev.getValue() + ", next=" + next.getValue() + "]";
    }

}
