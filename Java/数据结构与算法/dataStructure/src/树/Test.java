package 树;

/**
 * @title: Test
 * @Author zxwyhzy
 * @Date: 2023/8/5 22:28
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        test2();
    }


    public static void test2(){
        BinaryTreeCueing root = new BinaryTreeCueing("a");
        BinaryTreeCueing b =    new BinaryTreeCueing("b");
        BinaryTreeCueing c =    new BinaryTreeCueing("c");
        BinaryTreeCueing d =    new BinaryTreeCueing("d");
        BinaryTreeCueing e =    new BinaryTreeCueing("e");
        BinaryTreeCueing f =    new BinaryTreeCueing("f");
        BinaryTreeCueing g =    new BinaryTreeCueing("g");
        BinaryTreeCueing h =    new BinaryTreeCueing("h");
        root.setLeft(b);
        root.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        d.setLeft(h);
        /*
         *               a
         *       b               c
         *   d       e     f         g
         * h
         * */
        // 中序线索化
        BinaryTreeCueing.cueing(root);
        // 中序遍历线索化 h-d-b-e-a-f-c-g
        BinaryTreeCueing.inOrder(root); // h	d	b	e	a	c	g
        // 修改后 h	d	b	e	a	f	c	g
        System.out.println();

    }

    public static void test1(){
        BinaryTree root = new BinaryTree("a");
        BinaryTree b = new BinaryTree("b");
        BinaryTree c = new BinaryTree("c");
        BinaryTree d = new BinaryTree("d");
        BinaryTree e = new BinaryTree("e");
        BinaryTree f = new BinaryTree("f");
        BinaryTree g = new BinaryTree("g");
        BinaryTree h = new BinaryTree("h");
        root.setLeft(b);
        root.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        d.setLeft(h);
        /*
         *               a
         *       b               c
         *   d       e     f         g
         * h
         * */


        // 前序遍历测试  a-b-d-h-e-c-f-g
        BinaryTree.preOrder(root);// a	b	d	h	e	c	f	g
        System.out.println();
        // 中序遍历 h-d-b-e-a-f-c-g
        BinaryTree.inOrder(root);// h	d	b	e	a	f	c	g
        System.out.println();
        // 后序遍历 h-d-e-b-f-g-c-a
        BinaryTree.posOrder(root);// h	d	e	b	f	g	c	a
    }
}
