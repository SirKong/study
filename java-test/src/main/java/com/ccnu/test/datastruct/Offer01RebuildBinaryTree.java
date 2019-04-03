package com.ccnu.test.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer01RebuildBinaryTree {

    static class BinaryTreeNode {
        private Integer data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode(Integer data, BinaryTreeNode left, BinaryTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
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

    public static BinaryTreeNode rebuildWithPreAndMiddle(List<Integer> preOrder, List<Integer> middleOrder) {
        if (null == preOrder || null == middleOrder || preOrder.size() != middleOrder.size() || preOrder.size() == 0) {
            return null;
        }

        int rootData = preOrder.get(0);
        int index = middleOrder.indexOf(rootData);
        BinaryTreeNode root = new BinaryTreeNode(rootData, null, null);
        //表明跟节点左子树为空
        if (index == 0) {
            root.setLeft(null);
        } else {
            root.setLeft(rebuildWithPreAndMiddle(preOrder.subList(1, 1 + index), middleOrder.subList(0, index)));
        }

        if (index == middleOrder.size() - 1) {
            root.setRight(null);
        } else {
            root.setRight(rebuildWithPreAndMiddle(preOrder.subList(1 + index, preOrder.size()), middleOrder.subList(index + 1,
                    middleOrder.size())));
        }

        return root;
    }

    public static BinaryTreeNode rebuildWithMiddleAndPost(List<Integer> middle, List<Integer> postOrder) {
        if (null == middle || null == postOrder || postOrder.size() != middle.size() || postOrder.size() == 0) {
            return null;
        }

        /**
         *         List<Integer> middleOrder = Arrays.asList(4, 2, 8, 5, 9, 10, 1, 6, 3, 7);
         *
         *         List<Integer> postOrder = Arrays.asList(4, 8, 10, 9, 5, 2, 6, 7, 3, 1);
         */

        int rootData = postOrder.get(postOrder.size() - 1);
        int index = middle.indexOf(rootData);

        BinaryTreeNode root = new BinaryTreeNode(rootData, null, null);
        if (index == postOrder.size() - 1) {
            root.setRight(null);
        } else {
            root.setRight(rebuildWithMiddleAndPost(middle.subList(index + 1, middle.size()),
                    postOrder.subList(index, middle.size() - 1)));
        }

        if (index == 0) {
            root.setLeft(null);
        } else {
            root.setLeft(rebuildWithMiddleAndPost(middle.subList(0, index), postOrder.subList(0, index)));
        }

        return root;
    }

    //后序遍历采用递归的方式
    public static void post(BinaryTreeNode root) {
        if (null == root) {
            return;
        }

        post(root.getLeft());
        post(root.getRight());
        System.out.print(root.getData() + "\t");
    }

    public static void level(BinaryTreeNode root) {
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

    public static void pre(BinaryTreeNode root) {
        if (null == root) {
            return;
        }
        System.out.print(root.getData() + "\t");
        pre(root.getLeft());
        pre(root.getRight());
    }

    public static void main(String[] args) {
        List<Integer> preOrder = Arrays.asList(1, 2, 4, 5, 8, 9, 10, 3, 6, 7);
        List<Integer> middleOrder = Arrays.asList(4, 2, 8, 5, 9, 10, 1, 6, 3, 7);

        List<Integer> postOrder = Arrays.asList(4, 8, 10, 9, 5, 2, 6, 7, 3, 1);

        BinaryTreeNode rebuildWithPreAndMiddle = rebuildWithPreAndMiddle(preOrder, middleOrder);

        //4->8->10->9->5->2->6->7->3->1
        post(rebuildWithPreAndMiddle);
        System.out.println();
        //1->2->3->4->5->6->7->8->9->10
        level(rebuildWithPreAndMiddle);
        System.out.println();

        BinaryTreeNode rebuildWithMiddleAndPost = rebuildWithMiddleAndPost(middleOrder, postOrder);

        pre(rebuildWithMiddleAndPost);
        //1->2->4->5->8->9->10->3->6->7
        System.out.println();

        level(rebuildWithPreAndMiddle);
        //1->2->3->4->5->6->7->8->9->10
        System.out.println();
    }
}
