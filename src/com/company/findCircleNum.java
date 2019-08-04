package com.company;

/**
 * leetcode 547 --朋友圈--深度优先搜索
 * Created by Justin
 * 2019/8/4  9:38
 */
public class findCircleNum {

    public int findCircleNum(int[][] M) {//使用深度优先搜索，类似岛屿个数的题目
        int length = M.length;//二维数组长度，即所有人的个数
        int count = 0;//统计朋友圈个数
        boolean[] flag = new boolean[length];//访问标志
        for(int i = 0;i < length;i++){//对于每个人
            if(flag[i] == false){//如果未被访问
                DFS(i,M,flag);//深度优先搜索，访问
                count++;//朋友圈个数+1
            }
        }
        return count;
    }

    //深度优先搜索--遍历寻找每一个人的朋友 直到没有朋友为止---访问标志防止重复访问
    public void DFS(int i,int[][] M,boolean[] flag){
        flag[i] = true;
        for(int j = 0;j < M[i].length;j++){
            if(flag[j] == false && M[i][j] == 1){
                DFS(j,M,flag);
            }
        }
    }

}
