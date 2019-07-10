package com.company.sort;

import java.util.Arrays;

/**
 * 排序算法复习
 * Created by Justin
 * 2019/3/20  15:20
 */
public class SortTest {

    public static final int sortArray[] = {8, 6, 12, 4, 7, 5, 2, 45, 32, 105};

    /**
     * 冒泡升序--稳定
     *
     * @param arrs
     * @return
     */
    public static int[] bubble(int[] arrs) {
        int length = arrs.length;
        Boolean flag = false;
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                return arrs;
            }

        }
        return arrs;
    }

    /**
     * 选择升序--不稳定
     *
     * @param arrs
     * @return
     */
    public static int[] choose(int[] arrs) {
        int length = arrs.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arrs[j] < arrs[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arrs[i];
                arrs[i] = arrs[min];
                arrs[min] = temp;
            }
        }
        return arrs;
    }

    /**
     * 插入升序--稳定
     *
     * @param arrs
     * @return
     */
    public static int[] insertSort(int[] arrs) {
        int length = arrs.length;
        Boolean flag = false;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (arrs[j] < arrs[j - 1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j - 1];
                    arrs[j - 1] = temp;
                    flag = true;
                }
                if (flag == false) {
                    break;
                }
            }
            flag = false;
        }
        return arrs;
    }

    /**
     * 快排升序--不稳定
     *
     * @param arrs
     * @return
     */
    public static void quickSort(int[] arrs, int low, int high) {
        if (arrs.length == 0) {
            return;
        }
        int length = arrs.length;
        int left = low;
        int right = high;
        int temp = arrs[left];
        while (left < right) {
            while (left < right && temp <= arrs[right]) {
                right--;
            }
            arrs[left] = arrs[right];
            while (left < right && arrs[left] <= temp) {
                left++;
            }
            arrs[right] = arrs[left];
        }
        arrs[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arrs));
        quickSort(arrs, low, left - 1);
        quickSort(arrs, left + 1, high);

    }

    /**
     * 堆排序（大顶堆）--不稳定
     *
     * @param arrs
     * @return
     */
    public static int[] heapSort(int[] arrs) {
        int length = arrs.length;
        for (int i = length; i > 0; i--) {
            max_heapfy(arrs, i);
            int temp = arrs[0];
            arrs[0] = arrs[i-1];
            arrs[i-1] = temp;
        }
        return arrs;
    }

    public static void max_heapfy(int[] arrs, int limit) {
        int parentIdx = (limit / 2) - 1;
        for (; parentIdx >=0; parentIdx--) {
            int left=2*parentIdx;
            int right=(left + 1) >= limit ? left : (left + 1);
            int maxChildId = arrs[left] >= arrs[right] ? left : right;
            if(arrs[maxChildId] > arrs[parentIdx]){
                int temp = arrs[parentIdx];
                arrs[parentIdx] = arrs[maxChildId];
                arrs[maxChildId] = temp;
            }
        }
    }

    public static void main(String[] args) {
       quickSort(sortArray,0,sortArray.length-1);
        System.out.println(sortArray);
    }
}
