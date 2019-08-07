package com.company;

/**
 * leetcode 42--接雨水
 * Created by Justin
 * 2019/8/5  16:50
 */
public class Trap {

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     * 示例:
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     * @param height
     * @return 暴力破解：时间复杂度： O(n^2)数组中的每个元素都需要向左向右扫描。
     * 空间复杂度 O(1)的额外空间
     */
    public int trap(int[] height) {
        int len = height.length;
        int res = 0;
        for (int i = 1; i < len; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) {
                max_left = Math.max(max_left, height[j]);  //当前坑位左边最高墙
            }
            for (int j = i; j < len; j++) {
                max_right = Math.max(max_right, height[j]);  //当前坑位右边最高墙
            }
            res += Math.min(max_left, max_right) - height[i];      //根据两边最高墙（木桶效应）取低的减去当前坑位即为当前坑位容积
        }
        return res;
    }

    /**
     * 使用空间来存储左右最高墙--时间复杂度：O(n)
     * 空间复杂度：O(n) 额外空间
     *
     * @param height
     * @return
     */
    public int trapDp(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 使用双指针
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1) 的额外空间。
     *
     * @param height
     * @return
     */
    public int trapTp(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int res = 0;
        int left = 0, right = height.length - 1;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    res += (left_max - height[left]);
                }
                left++;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    res += right_max - height[right];
                }
                --right;
            }
        }
        return res;
    }
}
