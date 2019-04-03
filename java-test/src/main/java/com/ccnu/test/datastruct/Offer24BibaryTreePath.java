package com.ccnu.test.datastruct;

import java.util.ArrayList;

public class Offer24BibaryTreePath {
    static ArrayList<Integer> path = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if (root == null || target < 0) {
            return list;
        }
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            //注意要重新new一个  否则后面的操作会影响这个结果  所以最后回退到把根节点回退了，path就为空
            list.add(new ArrayList<Integer>(path));
        }
        findPath(root.left, target);
        findPath(root.right, target);

        //回退一个节点
        path.remove(path.size() - 1);

        return list;
    }

    /**
     * 1
     * /  \
     * 2    3
     * / \   /\
     * 4  5  6  7
     * / \
     * 8   9
     * \
     * 10
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node10 = new TreeNode(10, null, null);
        TreeNode node8 = new TreeNode(8, null, null);
        TreeNode node9 = new TreeNode(9, null, node10);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(5, node8, node9);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node1 = new TreeNode(1, node2, node3);

        ArrayList<ArrayList<Integer>> paths = findPath(node1, 7);
        for (ArrayList<Integer> path : paths) {
            System.out.println(path);
        }
    }


    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
