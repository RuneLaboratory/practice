package com.runelaboratory.module1.tree;

import java.util.*;
import java.util.stream.Collectors;

public class P5_VerticalOrderBinaryTreeTraversal {

    /*
    Given a binary tree, return the vertical order traversal of its nodes' values.
    (from top to bottom, column by column from left to right)
    */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        TreeNode level2_1 = new TreeNode(2);
        TreeNode level2_2 = new TreeNode(3);
        root.left = level2_1;
        root.right = level2_2;

        TreeNode level3_1 = new TreeNode(4);
        TreeNode level3_2 = new TreeNode(5);
        level2_1.left = level3_1;
        level2_1.right = level3_2;
        TreeNode level3_3 = new TreeNode(6);
        TreeNode level3_4 = new TreeNode(7);
        level2_2.left = level3_3;
        level2_2.right = level3_4;

        TreeNode level4_1 = new TreeNode(8);
        TreeNode level4_2 = new TreeNode(9);
        level3_3.right = level4_1;
        level3_4.right = level4_2;

        System.out.println(solution1(root));
        System.out.println(ans1(root));
        System.out.println(ans2(root));
    }

    private static List<Integer> solution1(TreeNode root) {
        HashMap<Integer, ArrayList<Integer>> resultMap = solution1_recursiveFunction(root, 0);
        return resultMap.keySet().stream().sorted().flatMap(a -> resultMap.get(a).stream()).collect(Collectors.toList());
    }

    private static HashMap<Integer, ArrayList<Integer>> solution1_recursiveFunction(TreeNode root, int index) {
        HashMap<Integer, ArrayList<Integer>> resultMap = new HashMap<>();
        resultMap.put(index, new ArrayList<>(Arrays.asList(root.val)));

        if (root.left != null) {
            HashMap<Integer, ArrayList<Integer>> subMap = solution1_recursiveFunction(root.left, index - 1);
            subMap.forEach((k, v) -> {
                if (resultMap.containsKey(k)) {
                    resultMap.get(k).addAll(v);
                } else {
                    resultMap.put(k, v);
                }
            });
        }

        if (root.right != null) {
            HashMap<Integer, ArrayList<Integer>> subMap = solution1_recursiveFunction(root.right, index + 1);
            subMap.forEach((k, v) -> {
                if (resultMap.containsKey(k)) {
                    resultMap.get(k).addAll(v);
                } else {
                    resultMap.put(k, v);
                }
            });
        }

        return resultMap;
    }

    private static List<List<Integer>> ans1(TreeNode root) {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        helper(root, map);
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }

    private static void helper(TreeNode t, TreeMap<Integer, ArrayList<Integer>> map) {
        if (t == null) {
            return;
        }

        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        q1.offer(t);
        q2.offer(0);

        while (!q1.isEmpty()) {
            TreeNode node = q1.poll();
            int order = q2.poll();

            //add to map
            ArrayList<Integer> list = map.get(order);
            if (list == null) {
                list = new ArrayList<>();
                map.put(order, list);
            }
            list.add(node.val);

            if (node.left != null) {
                q1.offer(node.left);
                q2.offer(order - 1);
            }

            if (node.right != null) {
                q1.offer(node.right);
                q2.offer(order + 1);
            }
        }
    }

    // cal the min and max tree index first, directly put the result list index correctly, start from 0 (no negative)
    public static List<List<Integer>> ans2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }

        int[] mm = new int[2];
        getMinMax(root, mm, 0);

        int len = mm[1]-mm[0]+1;

        for(int i=0; i<len; i++){
            result.add(new ArrayList<Integer>());
        }

        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();

        q1.offer(root);
        q2.offer(0);

        while(!q1.isEmpty()){
            TreeNode h = q1.poll();
            int order = q2.poll();

            result.get(order-mm[0]).add(h.val);

            if(h.left!=null){
                q1.offer(h.left);
                q2.offer(order-1);
            }
            if(h.right!=null){
                q1.offer(h.right);
                q2.offer(order+1);
            }
        }

        return result;
    }


    private static void getMinMax(TreeNode node, int[] mm, int order){
        if(node == null){
            return;
        }

        mm[0] = Math.min(mm[0], order);
        mm[1] = Math.max(mm[1], order);

        getMinMax(node.left, mm, order-1);
        getMinMax(node.right, mm, order+1);
    }
}
