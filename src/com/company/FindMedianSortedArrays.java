package com.company;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 */
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int [] res=new int[nums1.length+nums2.length];
        int len=res.length;
        int l=0,mid=nums1.length,r=0;
        int end=nums2.length;
        for (int k = 0; k <len ; k++) {
            if (l>=mid){ //nums1已经排列好
                res[k]=nums2[r];
                r++;
            }else if(r>=end){//nums2已经排列好
                res[k]=nums1[l];
                l++;
            }else if (nums1[l]<nums2[r]){
                res[k]=nums2[r];
                r++;
            }else {
                res[k]=nums1[l];
                l++;
            }
        }
        if (len%2==0)
            return (res[len/2-1]+res[len/2])/2;
        else
            return res[len/2];
    }

    public static void main(String[] args) {
        int[] nums1={1,3};
        int[] nums2={2};
        FindMedianSortedArrays fs=new FindMedianSortedArrays();
        fs.findMedianSortedArrays(nums1,nums2);
    }
}
