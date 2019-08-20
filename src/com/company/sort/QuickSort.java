package com.company.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.FutureTask;

/**
 * Created by Justin
 * 2018/8/3  17:34
 */
public class QuickSort {

    public static int sortArray[] = {15, 11, 3, 6, 14, 12, 4, 7, 5, 9,12,13,15,11,10};

    /*双路快排*/
    public static void quickSort(int[] arr, int low, int high) {
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;
        Random random = new Random();
        int randomPostion = random.nextInt(right - left) + left;
        swap(arr, left, randomPostion);
        int temp = arr[left];
        System.out.println(temp);//挖坑1：保存基准的值
        while (left < right) {
            while (left < right && arr[right] >= temp) {  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 单路快排写法*/
    public static void quickSort_V1(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int position = partition(arr, low, high);
        quickSort_V1(arr, low, position - 1);
        quickSort_V1(arr, position + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        //随机位置选取，使整个排序算法整体时间复杂度为nlogn
        Random random = new Random();
        int randomPostion = random.nextInt(high - low) + low;
        swap(arr, low, randomPostion);
        int temp = arr[low];
        //arr[low+1...position]<temp;arr[postion+1...j]>temp
        int postion = low;
        for (int i = low + 1; i <= high; i++) {
            if (arr[i] < temp) {
                swap(arr, postion + 1, i);
                postion++;
            }
        }
        swap(arr, low, postion);
        return postion;
    }

    public static void main(String[] args) {

    }
}
