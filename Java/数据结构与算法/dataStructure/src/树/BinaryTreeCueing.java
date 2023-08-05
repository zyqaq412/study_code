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


    // 中序线索化二叉树
    public static void cueing(BinaryTreeCueing root) {
        if (null == root) {
            return;
        }
        cueing(root.getLeft());
        if (root.getLeft() == null) {
            root.setLeft(pre);
            root.ltype = 1;
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRtype(1);
        }
        pre = root;
        cueing(root.getRight());
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
