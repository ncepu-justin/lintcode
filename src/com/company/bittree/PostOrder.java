package com.company.bittree;

import com.company.TreeNode;

import java.util.Stack;

/**
 * Created by Justin
 * 2018/9/9  9:59
 */
public class PostOrder {

    /*两个栈实现*/
    public void PostOrderBitTree1(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.add(head);
            while (!stack1.empty()) {
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.left);
                }
                if (head.right != null) {
                    stack1.push(head.right);
                }
            }
            while (!stack2.empty()) {
                System.out.println("stack2.pop().val = " + stack2.pop().val);
            }
        }

    }

    /*一个栈实现，需要两个节点：h代表最近弹出并打印的节点初始为头结点，c代表当前栈顶节点*/
    public void PostOrderBitTree2(TreeNode h) {
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(h);
            TreeNode c = null;
            while (!stack.empty()) {
                c = stack.peek();
                if (c.left != null && c.right != h && c.left != null) {
                    stack.push(c.left);
                } else if (c.right != null && c.right != h) {
                    stack.push(c.right);
                } else {
                    System.out.println("stack = " + stack.pop().val);
                    h = c;
                }
            }
        }

    }
}
