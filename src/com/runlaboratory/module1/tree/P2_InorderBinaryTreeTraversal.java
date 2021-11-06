package com.runlaboratory.module1.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P2_InorderBinaryTreeTraversal {

    /*
    Inorder binary tree traversal.
    Given a tree, and it will return the list of value in inorder (Left, Root, Right)
     */

    private static final List<Integer> answer2Result = new ArrayList<>();

    public static void main(String[] args) {

        TreeNode level4_1 = new TreeNode(6);
        TreeNode level4_2 = new TreeNode(7);

        TreeNode level3_1 = new TreeNode(4);
        TreeNode level3_2 = new TreeNode(5);
        level3_2.left = level4_1;
        level3_2.right = level4_2;


        TreeNode level2_1 = new TreeNode(2);
        TreeNode level2_2 = new TreeNode(3);

        level2_1.left = level3_1;
        level2_1.right = level3_2;

        TreeNode root = new TreeNode(1);
        root.left = level2_1;
        root.right = level2_2;

        System.out.println("===== solution 1 =====");
        System.out.println(solution1(root));
        System.out.println("===== solution 2 =====");
        System.out.println(solution2(new ArrayList<>(), root));

        System.out.println("===== answer 1 =====");
        System.out.println(answer1(root));
        System.out.println("===== answer 2 =====");
        answer2(root);
        System.out.println(answer2Result);
        System.out.println("===== answer 3 =====");
        System.out.println(answer3(root));
    }

    // Push node to stack and then jump to 'next node' (node.left or stack.right)
    private static ArrayList<Integer> solution1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode nextNode = root;

        while (nextNode != null || !stack.empty()) {
            if (nextNode == null) {
                TreeNode p = stack.pop();
                result.add(p.val);
                nextNode = p.right;
            }
            if (nextNode != null) {
                stack.push(nextNode);
                nextNode = nextNode.left;
            }
        }

        return result;
    }

    // Use recursive to collect values
    private static ArrayList<Integer> solution2(ArrayList<Integer> result, TreeNode node) {
        if (node == null) {
            return result;
        }

        solution2(result, node.left);
        result.add(node.val);
        solution2(result, node.right);

        return result;
    }

    // Keep repeat ~ (push left to stack until the end, then only go back to parent right)
    private static ArrayList<Integer> answer1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            result.add(t.val);

            t = t.right;
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
        }

        return result;
    }

    // The recursive solution is trivial.
    private static void answer2(TreeNode p) {
        if (p.left != null)
            answer2(p.left);

        answer2Result.add(p.val);

        if (p.right != null)
            answer2(p.right);
    }

    // Changes the tree structure, remove left after pushed to stack
    private static List<Integer> answer3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (top.left != null) {
                stack.push(top.left);
                top.left = null;
            } else {
                result.add(top.val);
                stack.pop();
                if (top.right != null) {
                    stack.push(top.right);
                }
            }
        }

        return result;
    }
}
