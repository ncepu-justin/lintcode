package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * Created by Justin
 * 2019/8/14  8:45
 */
public class SpiralOrder {

    /**
     * 54. 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     示例 1:
     输入:
     [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
     ]
     输出: [1,2,3,6,9,8,7,4,5]
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list=new ArrayList<>();
        if(matrix==null||matrix.length==0)
            return list;
        int col=matrix.length;   //行
        int row=matrix[0].length;  //列
        int i = 0;

        //统计矩阵从外向内的层数，如果矩阵非空，那么它的层数至少为1层
        int count = (Math.min(col, row)+1)/2;
        //从外部向内部遍历，逐层打印数据
        while(i < count) {
            for (int j = i; j < row-i; j++) {   //从左向右
                list.add(matrix[i][j]);
            }
            for (int j = i+1; j < col-i; j++) { //从上往下
                list.add(matrix[j][(row-1)-i]);
            }

            for (int j = (row-1)-(i+1); j >= i && (col-1-i != i); j--) { //从右往左，如果这一层只有1行，
                                                                    // 那么第一个循环已经将该行打印了，这里就不需要打印了
                list.add(matrix[(col-1)-i][j]);
            }
            for (int j = (col-1)-(i+1); j >= i+1 && (row-1-i) != i; j--) { //从下往上，如果这一层只有1列，那么第2个循环已经将该列打印了，这里不需要打印
                list.add(matrix[j][i]);
            }
            i++;
        }
        return list;
    }


    /**
     * 59. 螺旋矩阵 II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     示例:
     输入: 3
     输出:
     [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
     ]
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int [][] res=new int[n][n];
        if(n==0)
            return res;
        int i = 0;
        int index=0;
        //统计矩阵从外向内的层数，如果矩阵非空，那么它的层数至少为1层
        int count = (n+1)/2;
        //从外部向内部遍历，逐层打印数据
        while(i < count) {
            for (int j = i; j < n-i; j++) {   //从左向右
                res[i][j]=index;
                index++;
            }
            for (int j = i+1; j < n-i; j++) { //从上往下
               res[j][(n-1)-i]=index;
               index++;
            }

            for (int j = (n-1)-(i+1); j >= i && (n-1-i != i); j--) { //从右往左，如果这一层只有1行，
                // 那么第一个循环已经将该行打印了，这里就不需要打印了
                res[(n-1)-i][j]=index;
                index++;
            }
            for (int j = (n-1)-(i+1); j >= i+1 && (n-1-i) != i; j--) { //从下往上，如果这一层只有1列，那么第2个循环已经将该列打印了，这里不需要打印
                res[j][i]=index;
                index++;
            }
            i++;
        }
        return res;
    }
}
