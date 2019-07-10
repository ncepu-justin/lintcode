package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Justin
 * 2019/4/1  22:02
 */
public class Subset {
    public static int sortArray[] = {4, 7, 5, 9};


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }

    // 1. 递归的定义
    // 以 subset 开头的，配上 nums 以 index 开始的数所有组合放到 results 里
    private void dfs(int[] nums,
                     int index,
                     List<Integer> subset,
                     List<List<Integer>> results) {
        // 3. 递归的出口
        if (index == nums.length) {
            results.add(new ArrayList<Integer>(subset));
            return;
        }

        // 2. 递归的拆解
        // (如何进入下一层)

        // 选了 nums[index]
        subset.add(nums[index]);
        dfs(nums, index + 1, subset, results);

        // 不选 nums[index]
        subset.remove(subset.size() - 1);
        dfs(nums, index + 1, subset, results);
    }

    int res = 0;

    public int intergral(ArrayList<Integer> intergral) {
        if (intergral == null) {
            return 0;
        }
        int index = 0;
        return __integral(intergral, 0);
    }

    public int __integral(ArrayList<Integer> reward, int score) {
        if (reward.size() == 2) {
            return Math.max(res, score);
        }
        for (int i = 1; i < reward.size() - 1; i++) {
            score += reward.get(i - 1) * reward.get(i) * reward.get(i + 1);
            reward.remove(i);
            __integral(reward, score);
        }
         return 0;
    }



    public static void main(String[] args) {
        Subset subset = new Subset();
        ArrayList<Integer> reward = new ArrayList<>();
        reward.add(1);
        reward.add(3);
        reward.add(1);
        reward.add(5);
        reward.add(8);
        reward.add(1);
        subset.intergral(reward);
        /*List<List<Integer>> wholelist = subset.subsets(sortArray);
        System.out.println(wholelist.size());
        System.out.println(Arrays.toString(wholelist.toArray()));*/
    }
}

