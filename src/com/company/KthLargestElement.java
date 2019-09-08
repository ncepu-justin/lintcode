package com.company;

import java.util.Random;

/**
 * 第K大元素
 * Created by Justin
 * 2019/3/31  21:41
 */
public class KthLargestElement {
    public static int sortArray[] = {1, 3, 4, 2};

    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        return partition2(nums, 0, nums.length - 1, nums.length - k);
    }


    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        if (k <= right) {
            return partition(nums, start, right, k);
        }
            return partition(nums, left, end, k);

    }

    /*结合双路快排--相对容易理解*/
    private int partition2(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        int left = start, right = end;
        Random random = new Random();
        int randomPostion = random.nextInt(end - start)+start;
        swap(nums, left, randomPostion);
        int temp = nums[left];   //挖坑1：保存基准的值
        while (left < right) {
            while (left < right && nums[right] >= temp) {  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= temp) {   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        if (k <right) {
            return partition2(nums, start, right-1, k);
        }
        else if(k >left){
            return partition2(nums, left + 1, end, k);
        }
        return nums[k];
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        KthLargestElement kthLargestElement = new KthLargestElement();
        System.out.println( kthLargestElement.kthLargestElement(1, sortArray));
    }
}


