package com.company;

import java.util.Stack;

/**
 * Created by Justin
 * 2019/4/7  21:33
 */
public class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
        // do intialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();

    }


    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (stack1.empty()&&stack2.empty()){
            throw new RuntimeException("MyQue is Empty");
        }if (!stack1.empty()&&stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();

    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if (stack1.empty()&&stack2.empty()){
            throw new RuntimeException("MyQue is Empty");
        }if (!stack1.empty()&&stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
}
