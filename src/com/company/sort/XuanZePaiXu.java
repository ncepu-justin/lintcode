package com.company.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 选择排序
 * 1. 从待排序序列中，找到关键字最小的元素；
 * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
 * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
 *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * Created by Justin
 * 2018/8/3  10:10
 */
public class XuanZePaiXu {

    public  static final int sortArray[]={8,6,12,4,7,5,2,45,32,105,56,18};

    public  static  int sortResult[];

    private static int[] selectionSort(int arr[]) {
        int len = arr.length;
        for (int i = 0; i <len-1 ; i++) {
            int min=i;
                    for (int j = i+1; j <len ; j++) {
                             if(arr[j]<arr[min])
                               min=j;
                            }
                            if (min!=i){
                             int temp=arr[min];
                             arr[min]=arr[i];
                             arr[i]=temp;
                            }
                }
        return arr;
    }

    public static void main(String[] args) {
        int [] arr= selectionSort(sortArray);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
