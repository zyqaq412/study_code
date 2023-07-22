package 线性表;

/**
 * @title: homework02 // 使用循环链表模拟约瑟夫问题（41人 循环报数1-3 报到3的出列下一个继续从1开始报数）
 * @Author zxwyhzy
 * @Date: 2023/7/17 17:48
 * @Version 1.0
 */
public class homework02 {


    public static void main(String[] args) {
        ListNode01 listNode01 = init();
        // 1	2	3	4	5	6	7	8	9	10	11	12
        // 13	14	15	16	17	18	19	20	21	22	23	24
        // 25	26	27	28	29	30	31	32	33	34	35	36
        // 37	38	39	40	41
        listNode01.print();
        // 3	6	9	12	15	18	21	24	27	30	33	36
        // 39	1	5	10	14	19	23	28	32	37	41	7
        // 13	20	26	34	40	8	17	29	38	11	25	2
        // 22	4	35 (没有16和31)
        test(listNode01);


    }

    public static ListNode01 init(){
        ListNode01 listNode = new ListNode01();

        for (int i = 1 ;i<=41 ;i++){
            Node01 node = new Node01();
            node.data = i ;
            listNode.addNode(node);
        }
        return listNode;
    }
    public static void test(ListNode01 listNode){
        Node01 node = listNode.head;
        int  i = 1 ;
        while (listNode.length>=3){
            node = node.next;
            i++;
            if (i==2){
                Node01 temp = node;
                node = node.next.next;
                i = 1;
                listNode.delNode(temp);
            }
        }
    }
}
class Node01{
    int data;
    Node01 next;
}
// 循环链表
class ListNode01{
    Node01 head;

    int length;
    public boolean addNode(Node01 node){
        if (head == null){
            head = node;
            node.next = head;
        }else {
            Node01 temp = head;
            while (temp.next != head){
                temp=temp.next;
            }
            temp.next = node;
            node.next = head;
        }
        length++;
        return true;
    }

    public boolean delNode(Node01 node ){
        System.out.print(node.next.data+"\t");
        node.next = node.next.next;
        length--;
        return true;
    }
    public void print() {
        Node01 node = head;

        while (node.next != head) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
        System.out.println(node.data);
    }
}