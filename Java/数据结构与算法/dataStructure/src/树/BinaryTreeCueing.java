package 树;

/**
 * @title: BinaryTreeBinaryTreeCueing 二叉树线索化
 * @Author zxwyhzy
 * @Date: 2023/8/5 22:45
 * @Version 1.0
 */
public class BinaryTreeCueing {
    private String data;
    private BinaryTreeCueing left;
    private BinaryTreeCueing right;

    // 左结点类型 0 子树，1 前驱
    private int ltype;
    // 右结点类型 0 子树，1 后驱
    private int rtype;

    public static BinaryTreeCueing pre = null;

    // 前序线索化二叉树
    public static void preCueing(BinaryTreeCueing root){
        if (root == null){
            return;
        }
        if (root.getLeft() == null){
            root.setLeft(pre);
            root.setLtype(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRtype(1);
        }
        pre = root;

        // 加一个判断防止陷入死循环

        if (root.getLtype() == 0){
            preCueing(root.getLeft());
        }
        // 比如 root = d 的时候上面的代码会让 d.right = b ,这里就会死循环
        if (root.getRtype() == 0){
            preCueing(root.getRight());
        }

    }

    // 前序遍历线索化二叉树
    public static void preOrder(BinaryTreeCueing root){
        while (root != null){
            while (root.getLtype() == 0){
                System.out.print(root.getData()+"\t");
                root = root.getLeft();
            }
            System.out.print(root.getData()+"\t");
            root = root.getRight();

        }
    }





    // 中序线索化二叉树
    public static void inCueing(BinaryTreeCueing root) {
        if (null == root) {
            return;
        }
        inCueing(root.getLeft());
        if (root.getLeft() == null) {
            root.setLeft(pre);
            root.ltype = 1;
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRtype(1);
        }
        pre = root;
        inCueing(root.getRight());
    }

    // 中序遍历线索化二叉树
    public static void inOrder(BinaryTreeCueing root) {
/*        while (root.getLtype() != 1) {
            root = root.getLeft();
        }*/
        while (root != null) {
            while (root.getLtype() != 1) {
                root = root.getLeft();
            }
            System.out.print(root.getData() + "\t");
            while (root.getRight() != null && root.getRtype() == 1) {
                root = root.getRight();
                System.out.print(root.getData() + "\t");
            }
            root = root.getRight();
        }
    }


    public BinaryTreeCueing(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BinaryTreeCueing getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeCueing left) {
        this.left = left;
    }

    public BinaryTreeCueing getRight() {
        return right;
    }

    public void setRight(BinaryTreeCueing right) {
        this.right = right;
    }

    public int getLtype() {
        return ltype;
    }

    public void setLtype(int ltype) {
        this.ltype = ltype;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }
}
