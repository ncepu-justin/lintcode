package com.company.sort;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Justin
 * 2018/8/3  16:13
 */
public class HeapSort {
    public  static final int sortArray[]={8,6,12,4,7,5,2,45,32,105,56};


    public static int[] heapSort(int[] arr){
        for(int i = arr.length; i > 0; i--){
            max_heapify(arr, i);
            int temp = arr[0];      //堆顶元素(第一个元素)与Kn交换
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
        return arr;
    }


    private static void max_heapify(int[] arr, int limit){
        if(arr.length <= 0 ) return;
        int parentIdx = (limit / 2)-1;

        for(; parentIdx >= 0; parentIdx--){

            int left = parentIdx * 2;       //左子节点位置
            int right = (left + 1) >= limit ? left : (left + 1);    //右子节点位置，如果没有右节点，默认为左节点位置

            int maxChildId = arr[left] >= arr[right] ? left : right;
            if(arr[maxChildId] > arr[parentIdx]){   //交换父节点与左右子节点中的最大值
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));

    }


    public static void main(String[] args) {
      //  int [] arr= heapSort(sortArray);
      //  System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

    }
}
