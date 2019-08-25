package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-996 正方形数组的数目
 * Created by Justin
 * 2019/8/20  9:53
 */
public class NumSquarefulPerms {

    private int count;

    /**
     * 给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。
     返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。
     示例 1：
     输入：[1,17,8]
     输出：2
     解释：
     [1,8,17] 和 [17,8,1] 都是有效的排列。
     示例 2：
     输入：[2,2,2]
     输出：1
     * @param a
     * @return
     */
    public int numSquarefulPerms(int[] a) {
        doPermutation(a, 0);
        return count;
    }

    private void doPermutation(int[] nums, int index) {

        if (index >= 2 && index <= nums.length) {
            if (!isPerfectSquare(nums[index - 2] + nums[index - 1])) {
                return;
            }
        }

        if (index == nums.length) {
            count++;
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = index; i < nums.length; i++) {
            if (index != i && map.get(nums[i]) != null) {
                continue;
            }
            map.put(nums[i], nums[index]);
            swap(nums, index, i);
            doPermutation(nums, index + 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int index, int index1) {
        int tmp = nums[index];
        nums[index] = nums[index1];
        nums[index1] = tmp;
    }

    public boolean isPerfectSquare(int num) {
        double d = Math.sqrt(num);
        return d - (int) d == 0;
    }
}
