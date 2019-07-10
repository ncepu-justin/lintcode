package com.company.sort;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Justin
 * 2018/8/3  15:27
 */
public class ShellSort {
    public static final int sortArray[] = {8, 6, 12, 4, 7, 5, 2, 45, 32, 105, 56, 15, 11, 0, 23};


    private static int[] Shell_Sort(int arr[]) {
        int len = arr.length;
        int gap = 1;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                for (int j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = Shell_Sort(sortArray);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
