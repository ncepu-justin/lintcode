package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-46. 全排列
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * 递归回溯 求数组全排列
 * Created by Justin
 * 2019/4/1  17:31
 */
public class WholeSort {

    public static int sortArray[] = {1,2,3};

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);
        return results;
    }


    private void dfs(int[] nums,
                     boolean[] visited,
                     List<Integer> permutation,
                     List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            System.out.println("新增："+Arrays.toString(permutation.toArray()));
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
            System.out.println("删除："+Arrays.toString(permutation.toArray()));
            System.out.println("删除后访问："+Arrays.toString(visited));
        }
    }

    public static void main(String[] args) {
        WholeSort wholeSort = new WholeSort();
        List<List<Integer>> wholelist = wholeSort.permute(sortArray);
        System.out.println(wholelist.size());
        System.out.println(Arrays.toString(wholelist.toArray()));
    }
}

