package 线性表;

import java.util.Random;

/**
 * @title: homework01 随机生成20个元素的链表，用快慢指针快速查找中间结点
 * @Author zxwyhzy
 * @Date: 2023/7/17 17:30
 * @Version 1.0
 */
public class homework01 {

    public static void main(String[] args) {
        ListNode listNode = init();

        listNode.print();// 3	41	6	87	30	71	90	93	99	96	70	5	88	17	16	58	97	60	5	99
        System.out.println("中间数");
        System.out.println(selectMid(listNode));// 96


    }

    public static ListNode init() {
        Random random = new Random();
        ListNode listNode = new ListNode();
        for (int i = 0; i < 5; i++) {
            Node node = new Node();
            node.setData(random.nextInt(100));
            listNode.addNode(node);
        }
        return listNode;
    }

    public static int selectMid(ListNode listNode) {
        Node j = listNode.getHead();
        Node k = listNode.getHead();
        while (k != null && k.getNext() != null) {
            j = j.getNext();
            k = k.getNext().getNext();
        }
        return j.getData();
    }

}

class ListNode {
    private Node head;

    public ListNode() {
        head = new Node();
    }

    public Node getHead() {
        return head;
    }

    public boolean addNode(Node node) {
        Node temp = head.getNext();
        head.setNext(node);
        node.setNext(temp);
        return true;
    }

    public void print() {
        Node node = head.getNext();
        while (node != null) {
            System.out.print(node.getData() + "\t");
            node = node.getNext();
        }
    }

}

class Node {
    private int data;
    private Node next;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
