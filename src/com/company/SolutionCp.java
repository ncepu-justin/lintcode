package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SolutionCp {

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 输入: 123
     * 输出: 321
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean isNev=false;
        if (x<0){
            isNev=true;
            x=-1*x;
        }
        int res=0;
        int limit=Integer.MAX_VALUE/10;
        while (x>0){
            int temp=x%10;
            if (res>limit){
                return 0;
            }
            res=res*10+temp;
            x/=10;
        }
        if (isNev)
            res*=-1;
        return res;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
           if (s.length()==0||s==null){
               return -1;
           }
        int[] m = new int[256];

        int res = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            left = Math.max(left, m[s.charAt(i)]);

            res = Math.max(res, i - left + 1);

            m[s.charAt(i)] = i + 1;
        }
        return res;

    }

    public int lengthOfLongestSubstring1(String s) {
        int res = 0, left = 0, right = 0;
        HashSet<Character> t = new HashSet<Character>();

        while (right < s.length()) {
            if (!t.contains(s.charAt(right))) {
                t.add(s.charAt(right));
                right++;
                res = Math.max(res, t.size());
            } else {
                t.remove(s.charAt(left));
                left++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        SolutionCp solutionCp=new SolutionCp();
        solutionCp.lengthOfLongestSubstring("abcabcbb");
    }
}
