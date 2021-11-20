package com.runlaboratory.module1.tree;

import java.util.ArrayList;
import java.util.Stack;

public class P3_PostorderBinaryTreeTraversal {

    /*
    Inorder binary tree traversal.
    Given a tree, and it will return the list of value in postorder (Left, Right, Root)
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
    }

    private static ArrayList<Integer> solution1(TreeNode root) {

        ArrayList<Integer> output = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode previousNode = null;
        while (!stack.isEmpty()) {

            if (stack.peek().right != null && stack.peek().right == previousNode) {
                previousNode = stack.pop();
                output.add(previousNode.val);
                continue;
            }

            if (stack.peek().left != null && stack.peek().left == previousNode) {
                if (stack.peek().right != null) {
                    stack.push(stack.peek().right);
                } else {
                    previousNode = stack.pop();
                    output.add(previousNode.val);
                    continue;
                }
            }

            if (stack.peek().left != null) {
                stack.push(stack.peek().left);
            } else if (stack.peek().right != null) {
                stack.push(stack.peek().right);
            } else {
                previousNode = stack.pop();
                output.add(previousNode.val);
            }
        }

        return output;
    }

    // Check the previously visited node. If the current node is the left or right child of the previous node, then keep
    // going down the tree, and add left/right node to stack when applicable
    private static ArrayList<Integer> answer1(TreeNode root) {

        ArrayList<Integer> lst = new ArrayList<Integer>();

        if (root == null)
            return lst;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode prev = null;
        while (!stack.empty()) {
            TreeNode curr = stack.peek();

            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if (prev == null || prev.left == curr || prev.right == curr) {
                //prev == null is the situation for the root node
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from left node
                //need to check if there is a right child
                //if yes, push it to stack
                //otherwise, process parent and pop stack
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from right node
                //after coming back from right node, process parent node and pop stack.
            } else if (curr.right == prev) {
                stack.pop();
                lst.add(curr.val);
            }

            prev = curr;
        }

        return lst;
    }

    // Tree's structure gets changed since children are set to null
    private static ArrayList<Integer> answer2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if (temp.left == null && temp.right == null) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
            } else {
                if (temp.right != null) {
                    stack.push(temp.right);
                    temp.right = null;
                }

                if (temp.left != null) {
                    stack.push(temp.left);
                    temp.left = null;
                }
            }
        }

        return res;
    }
}
