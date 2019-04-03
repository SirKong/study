package com.ccnu.test.datastruct;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class Offer14 {

    static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public static Node build(int[] array) {
            Node head = new Node(array[0], null);
            Node current = head;
            for (int i = 1; i < array.length; i++) {
                current.next = new Node(array[i], null);
                current = current.next;
            }
            return head;
        }
    }

    public static int find(Node node, int k) {
        int distance = 0;
        if (null == node) {
            return Integer.MAX_VALUE;
        }
        Node tmp = node;
        while (node != null) {
            if (distance < k) {
                distance++;
                node = node.next;
                continue;
            }
            node = node.next;
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public static void main(String[] args) {
        Node node = Node.build(new int[]{1, 2, 3, 4, 5, 6, 7});

        System.out.println(find(node, 1));
        System.out.println(find(node, 6));
    }
}
