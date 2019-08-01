package com.company;

/**
 * leetcode-4 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {

    /**
     * 暴力破解，基于归并排序思路直接合并两个数组，然后根据数组长度求中位数
     * 时间复杂度：遍历全部数组 (m+n)
     * 空间复杂度：开辟了一个数组，保存合并后的两个数组 O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] res = new int[nums1.length + nums2.length];
        int len = res.length;
        int l = 0, mid = nums1.length, r = 0;
        int end = nums2.length;
        for (int k = 0; k < len; k++) {
            if (l >= mid) { //nums1已经排列好
                res[k] = nums2[r];
                r++;
            } else if (r >= end) {//nums2已经排列好
                res[k] = nums1[l];
                l++;
            } else if (nums1[l] < nums2[r]) {
                res[k] = nums1[l];
                l++;
            } else {
                res[k] = nums2[r];
                r++;
            }
        }
        if (len % 2 == 0)
            return (double) (res[len / 2 - 1] + res[len / 2]) / 2;
        else
            return (double) res[len / 2];

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        FindMedianSortedArrays fs = new FindMedianSortedArrays();
        double result = fs.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
