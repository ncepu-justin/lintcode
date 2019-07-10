package com.company.bittree;

import com.company.TreeNode;

import java.util.Stack;

/**
 * Created by Justin
 * 2018/9/9  9:59
 */
public class InOrder {

    public void InOrderBitTree(TreeNode head) {
        if (head!=null){
            Stack<TreeNode> stack=new Stack<>();
            while (!stack.isEmpty()||head!=null){
                if (head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head=stack.pop();
                    System.out.println("head = " + head.val);
                    head=head.right;
                }
            }
        }

    }
}
