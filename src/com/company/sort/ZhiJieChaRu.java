package com.company.sort;

/**
 * 直接插入 从小到大排序
 * <p>
 * 插入排序
 * <p>
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤2~5
 * Created by Justin
 * 2018/8/3  10:10
 */
public class ZhiJieChaRu {

    public static final int sortArray[] = {8, 6, 12, 4, 7, 5, 2, 45, 32, 105};

    public static int sortResult[];

    /*优化直接插入*/

    private static int[] insertionSort(int arr[]) {
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int temp = arr[i];   //先存储临时变量arr[i]
            int j = i;
            for (; j > 0 && arr[j - 1] > temp; j--) {      //如果temp<arr[j-1],则将arr[j-1]位置向后移动，继续比较 寻找temp最终位置
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = insertionSort(sortArray);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
