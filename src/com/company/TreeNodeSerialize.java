package com.company;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Justin
 * 2019/4/1  11:25
 */
public class TreeNodeSerialize {
    public String serialize(TreeNode root) {
        // write your code here
        String res = "";
        if (root == null) {
            return "#!";
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<TreeNode>();
        res = root.val + "!";
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();
            if (node.left != null) {
                res += node.left.val + "!";
                treeNodeQueue.offer(node.left);
            } else {
                res += "#!";
            }
            if (node.right != null) {
                res += node.right.val + "!";
                treeNodeQueue.offer(node.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }


    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        Queue<TreeNode> treeNodeQueue = new LinkedList<TreeNode>();
        String[] values = data.split("!");
        int index = 0;
        TreeNode head = generateTreeNodeByString(values[index++]);
        if (head != null) {
            treeNodeQueue.offer(head);
        }
        TreeNode node = null;
        while (!treeNodeQueue.isEmpty()) {
            node = treeNodeQueue.poll();
            node.left = generateTreeNodeByString(values[index++]);
            node.right = generateTreeNodeByString(values[index++]);
            if (node.left != null) {
                treeNodeQueue.offer(node.left);
            }
            if (node.right != null) {
                treeNodeQueue.offer(node.right);
            }
        }
        return head;
    }



    public TreeNode generateTreeNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }
}
