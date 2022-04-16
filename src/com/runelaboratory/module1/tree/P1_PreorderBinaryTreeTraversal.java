package com.runelaboratory.module1.tree;

import java.util.ArrayList;
import java.util.Stack;

public class P1_PreorderBinaryTreeTraversal {

    /*
    Preorder binary tree traversal is a classic interview problem. The key to solve this problem is using a stack to
    store left and right children, and push right child first so that it is processed after the left child.

    Given a tree, and it will return the list of value in preorder (Root, Left, Right)
    */

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

        System.out.println("===== answer 1 =====");
        System.out.println(answer1(root));
        System.out.println("===== answer 2 =====");
        System.out.println(answer2(root));
        System.out.println("===== answer 3 preOrder =====");
        preOrder(root);
    }

    // Push to stack if node has right, get back the next right node from stack later
    private static ArrayList<Integer> solution1(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode nextNode = root;

        while (nextNode != null) {

            result.add(nextNode.val);

            if (nextNode.right != null) {
                stack.push(nextNode);
            }

            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (!stack.empty()) {
                nextNode = stack.pop().right;
            } else {
                nextNode = null;
            }
            printStack(stack);
        }

        return result;
    }

    private static void printStack(Stack<TreeNode> s) {
        System.out.print("stack ");
        for (TreeNode n : s) {
            System.out.print(n.val + " ");
        }
        System.out.println();
    }

    // Push all to stack in order of right child then left child
    private static ArrayList<Integer> answer1(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<>();

        if (root == null)
            return returnList;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode n = stack.pop();
            returnList.add(n.val);

            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }

        }
        return returnList;
    }

    // Recursive version
    private static ArrayList<Integer> answer2(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<>();
        if (root == null) return returnList;

        returnList.add(root.val);
        returnList.addAll(answer2(root.left));
        returnList.addAll(answer2(root.right));

        return returnList;
    }

    // Recursive version
    private static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
}