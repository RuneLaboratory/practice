package com.runelaboratory.module1.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P4_LevelOrderBinaryTreeTraversal {

    /*
    Given a binary tree, return the level order traversal of its nodes' values.
    (from left to right, level by level).
    */

    public static void main(String[] args) {
        TreeNode level4_1 = new TreeNode(12);
        TreeNode level4_2 = new TreeNode(1);

        TreeNode level3_1 = new TreeNode(16);
        TreeNode level3_2 = new TreeNode(4);
        level3_1.left = level4_1;
        level3_1.right = level4_2;

        TreeNode level3_3 = new TreeNode(15);
        TreeNode level3_4 = new TreeNode(7);

        TreeNode level2_1 = new TreeNode(9);
        TreeNode level2_2 = new TreeNode(20);

        level2_1.left = level3_1;
        level2_1.right = level3_2;
        level2_2.left = level3_3;
        level2_2.right = level3_4;

        TreeNode root = new TreeNode(3);
        root.left = level2_1;
        root.right = level2_2;

        System.out.println("Solution " + solution1(root));
        System.out.println("Ans 1 " + answer1(root));
        System.out.println("Ans 2 " + answer2(root));

    }

    private static ArrayList<Integer> solution1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<TreeNode> curLevel = new ArrayList<>();

        curLevel.add(root);
        while (!curLevel.isEmpty()) {
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : curLevel) {
                result.add(node.val);
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            curLevel = nextLevel;
        }
        return result;
    }

    private static ArrayList<ArrayList<Integer>> answer1(TreeNode root) {
        ArrayList<ArrayList<Integer>> al = new ArrayList<>();
        ArrayList<Integer> nodeValues = new ArrayList<>();
        if (root == null)
            return al;

        LinkedList<TreeNode> current = new LinkedList<>();
        LinkedList<TreeNode> next = new LinkedList<>();
        current.add(root);

        while (!current.isEmpty()) {
            TreeNode node = current.remove();

            if (node.left != null)
                next.add(node.left);
            if (node.right != null)
                next.add(node.right);

            nodeValues.add(node.val);
            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<>();
                al.add(nodeValues);
                nodeValues = new ArrayList<>();
            }

        }
        return al;
    }

    private static List<List<Integer>> answer2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();

        nodeQueue.offer(root);
        levelQueue.offer(1);//start from 1

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();

            List<Integer> l = null;
            if (result.size() < level) {
                l = new ArrayList<>();
                result.add(l);
            } else {
                l = result.get(level - 1);
            }

            l.add(node.val);

            if (node.left != null) {
                nodeQueue.offer(node.left);
                levelQueue.offer(level + 1);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                levelQueue.offer(level + 1);
            }
        }

        return result;
    }
}
