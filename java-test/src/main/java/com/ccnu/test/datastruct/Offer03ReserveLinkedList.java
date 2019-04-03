package com.ccnu.test.datastruct;

public class Offer03ReserveLinkedList {


    static class ListNode {
        private Integer data;
        private ListNode next;

        public ListNode(Integer data) {
            this.data = data;
        }

        public static ListNode buildLinkedList(Integer[] dataArray) {
            ListNode head = new ListNode(dataArray[0]);
            ListNode tail = head;
            for (int i = 1; i < dataArray.length; i++) {
                ListNode node = new ListNode(dataArray[i]);
                tail.next = node;
                tail = node;
            }

            System.out.println("check----------");
            for (ListNode tmpNode = head; tmpNode != null; tmpNode = tmpNode.next) {
                System.out.print(tmpNode.getData() + "\t");
            }

            System.out.println();

            return head;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static void reservedPrint(ListNode head) {
        if (null == head) {
            return;
        }
        reservedPrint(head.next);
        System.out.print(head.data + "\t");
    }

    public static void main(String[] args) {
        ListNode head = ListNode.buildLinkedList(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("reservedPrint--------");
        reservedPrint(head);
    }
}
