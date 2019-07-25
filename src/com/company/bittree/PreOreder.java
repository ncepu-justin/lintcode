package com.company.bittree;

import com.company.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * Created by Justin
 * 2018/9/9  9:51
 */
public class PreOreder {

    public void PreOrderBitree(TreeNode head){
        if (head!=null){
            Stack<TreeNode> stack=new Stack<>();
            stack.add(head);
            while(!stack.isEmpty()){
               head= stack.pop();
                System.out.println("head = " + head.val);
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
    }
}
