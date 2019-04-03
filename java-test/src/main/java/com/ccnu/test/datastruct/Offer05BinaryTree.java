package com.ccnu.test.datastruct;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Offer05BinaryTree {
    //前序遍历递归的方式
    public void preOrder(BinaryTreeNode root) {
        if (null != root) {
            System.out.print(root.getData() + "\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    //前序遍历非递归的方式
    public void preOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            while (root != null) {
                System.out.print(root.getData() + "\t");
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            root = root.getRight();
        }
    }

    //中序遍历采用递归的方式
    public void inOrder(BinaryTreeNode root) {
        if (null != root) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + "\t");
            inOrder(root.getRight());
        }
    }

    //中序遍历采用非递归的方式
    public void inOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            System.out.print(root.getData() + "\t");
            root = root.getRight();
        }
    }

    //后序遍历采用递归的方式
    public void postOrder(BinaryTreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + "\t");
        }
    }

    //后序遍历采用非递归的方式
    public void postOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                if (stack.isEmpty()) {
                    return;
                }

                if (null == stack.lastElement().getRight()) {
                    root = stack.pop();
                    System.out.print(root.getData() + "\t");
                    while (root == stack.lastElement().getRight()) {
                        System.out.print(stack.lastElement().getData() + "\t");
                        root = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!stack.isEmpty()) {
                    root = stack.lastElement().getRight();
                } else {
                    root = null;
                }
            }
        }
    }

    //层序遍历
    public void levelOrder(BinaryTreeNode root) {
        BinaryTreeNode temp;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.getData() + "\t");
            if (null != temp.getLeft()) {
                queue.offer(temp.getLeft());
            }
            if (null != temp.getRight()) {
                queue.offer(temp.getRight());
            }
        }
    }

    //树的深度
    public int deepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(deepth(root.getLeft()), deepth(root.getRight()));
        }

    }


    /**
     * <pre>
     *          1
     *        /  \
     *       2    3
     *      / \   /\
     *     4  5  6  7
     *       / \
     *      8   9
     *           \
     *           10
     * preOrder:    1->2->4->5->8->9->10->3->6->7
     * midOrder:    4->2->8->5->9->10->1->6->3->7
     * postOrder:   4->8->10->9->5->2->6->7->3->1
     * levelPrint:  1->2->3->4->5->6->7->8->9->10
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);
        BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
        BinaryTreeNode node9 = new BinaryTreeNode(9, null, node10);
        BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
        BinaryTreeNode node5 = new BinaryTreeNode(5, node8, node9);
        BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
        BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        Offer05BinaryTree tree = new Offer05BinaryTree();
        //采用递归的方式进行遍历
        System.out.println("-----前序遍历------");
        tree.preOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.preOrderNonRecursive(node1);
        System.out.println();
        tree.pre(node1);
        System.out.println();


        //采用递归的方式进行遍历
        System.out.println("-----中序遍历------");
        tree.inOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.inOrderNonRecursive(node1);
        System.out.println();
        tree.middle(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----后序遍历------");
        tree.postOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.postOrderNonRecursive(node1);
        System.out.println();
        tree.post(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----层序遍历------");
        tree.levelOrder(node1);
        System.out.println();
        tree.level(node1);
        System.out.println();

        System.out.println("-----树的深度------");
        System.out.println(tree.deepth(node1));
        System.out.println(tree.deepth(node9));
        System.out.println(tree.deepth(node5));
        System.out.println(tree.deepth(node10));
    }

    public void pre(BinaryTreeNode root) {
        if (null == root) {
            return;
        }
        System.out.print(root.getData() + "\t");
        pre(root.getLeft());
        pre(root.getRight());
    }

    public void middle(BinaryTreeNode root) {
        if (null == root) {
            return;
        }
        middle(root.getLeft());
        System.out.print(root.getData() + "\t");
        middle(root.getRight());
    }

    public void post(BinaryTreeNode root) {
        if (null == root) {
            return;
        }

        post(root.getLeft());
        post(root.getRight());
        System.out.print(root.getData() + "\t");
    }

    public void level(BinaryTreeNode root) {
        if (null == root) {
            return;
        }


        List<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
        list.add(root);

        while (true) {
            if (list.size() == 0) {
                break;
            }
            BinaryTreeNode binaryTreeNode = list.get(0);
            list.remove(0);
            System.out.print(binaryTreeNode.getData() + "\t");
            if (null != binaryTreeNode.getLeft()) {
                list.add(binaryTreeNode.getLeft());
            }
            if (null != binaryTreeNode.getRight()) {
                list.add(binaryTreeNode.getRight());
            }
        }
    }

    static class BinaryTreeNode {
        private int data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode() {
        }

        public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
            super();
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode left) {
            this.left = left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode right) {
            this.right = right;
        }
    }
}