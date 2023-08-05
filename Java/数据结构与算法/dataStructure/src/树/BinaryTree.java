package 树;

/**
 * @title: BinaryTree 二插树前序，中序，后序遍历
 * @Author zxwyhzy
 * @Date: 2023/8/5 22:26
 * @Version 1.0
 */


public class BinaryTree {
    private String data;
    private BinaryTree left;
    private BinaryTree right;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public BinaryTree(String data){
        this.data = data;
    }

    // 前序遍历
    public static void preOrder(BinaryTree root){
        if (null == root){
            return;
        }
        System.out.print(root.data+"\t");
        preOrder(root.left);
        preOrder(root.right);

    }
    // 中序遍历
    public static void inOrder(BinaryTree root){
        if (null == root){
            return;
        }

        inOrder(root.left);
        System.out.print(root.data+"\t");
        inOrder(root.right);

    }
    // 后序遍历
    public static void posOrder(BinaryTree root){
        if (null == root){
            return;
        }

        posOrder(root.left);
        posOrder(root.right);
        System.out.print(root.data+"\t");

    }
}
