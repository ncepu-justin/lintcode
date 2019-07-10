package com.company;

import java.util.Stack;

/**
 * Created by Justin
 * 2019/4/1  16:30
 */
public class MinStack {

    Stack<Integer> operateStack;
    Stack<Integer> minStacks;

    /*实现一个栈, 支持以下操作:

    push(val) 将 val 压入栈
    pop() 将栈顶元素弹出, 并返回这个弹出的元素
    min() 返回栈中元素的最小值
    要求 O(1) 开销.*/
    public MinStack() {
        // do intialization if necessary
        operateStack = new Stack<>();
        minStacks = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        operateStack.push(number);
        if (minStacks.empty()||minStacks.peek()>=number){
            minStacks.push(number);
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (operateStack.empty()){
            throw new RuntimeException("当前栈为空");
        }
        int resullt=operateStack.pop();
        if (minStacks.peek()==resullt)
        {
            minStacks.pop();
        }
        return resullt;
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        if (operateStack.empty()){
            throw new RuntimeException("当前栈为空");
        }
        return minStacks.peek();
    }

    public static void main(String[] args) {
        MinStack min=new MinStack();
        min.push(152);
        System.out.println(min.pop());
        min.push(163);
        System.out.println(min.min());
    }
}
