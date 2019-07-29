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
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean isNev = false;
        if (x < 0) {
            isNev = true;
            x = -1 * x;
        }
        int res = 0;
        int limit = Integer.MAX_VALUE / 10;
        while (x > 0) {
            int temp = x % 10;
            if (res > limit) {
                return 0;
            }
            res = res * 10 + temp;
            x /= 10;
        }
        if (isNev)
            res *= -1;
        return res;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
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

    /**
     * 字符串转整数
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str.length() == 0 || str == null) {
            return 0;
        }
        char fisrt = str.charAt(0);
        if (fisrt != '-' && fisrt != ' ' && fisrt != '+' && !Character.isDigit(fisrt))
            return 0;
        boolean isNegtive = false;//是否是负数
        boolean isModify = false;//是否标识操作过该数
        int len = str.length();
        int res = 0;
        int max = Integer.MAX_VALUE / 10;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && isModify)
                break;
            if (c == '-') {
                isNegtive = true;
                isModify = true;
                continue;
            }
            if (c == '+') {
                isModify = true;
                continue;
            }

            if (Character.isDigit(c)) {
                isModify = true;
                if (res == max) {
                    if (isNegtive && (c - '0') > 7) {
                        return Integer.MIN_VALUE;
                    } else if ((c - '0') > 7) {
                        return Integer.MAX_VALUE;
                    } else {
                        return isNegtive ? -(res * 10 + (c - '0')) : res * 10 + (c - '0');
                    }
                }
                if (res > max) {
                    if (isNegtive) {
                        return Integer.MIN_VALUE;
                    }
                    return Integer.MAX_VALUE;
                }
                res = res * 10 + (c - '0');
                continue;
            }
            //空格处理
            if (i > 0 && str.charAt(i - 1) == ' ' && c != str.charAt(i - 1))
                break;
        }
        return isNegtive ? -res : res;
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1:
     *
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     *
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){
            return "";
        }
        int length=strs.length;
        int index=0;
        int maxLen=strs[index].length();
        for (int i = 0; i <length ; i++) {
            if (strs[i].length()<maxLen){
                maxLen=strs[i].length();
                index=i;
            }
        }
        if (maxLen==0){
            return "";
        }
        StringBuilder res=new StringBuilder();
        for (int i = 0; i <maxLen ; i++) {
            for (int j = 0; j <length ; j++) {
                if (strs[index].charAt(i)!=strs[j].charAt(i)){
                    return res.toString();
                }
            }
            res.append(String.valueOf(strs[index].charAt(i)));
        }
        return res.toString();
    }

    /**
     * leetcode 5--最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    int maxLen=0;
    String sub="";
    public String longestPalindrome(String s) {
        if (s==null||s.length()==0){
            return "";
        }
        int len=s.length();
        for (int i = 0; i <len ; i++) {
            findLongestPalindrome(s,i,i);
            findLongestPalindrome(s,i,i+1);
        }
        return sub;

    }

    private void findLongestPalindrome(String s, int start, int end) {
        while (start>=0&&end<=s.length()-1){
            if (s.charAt(start)==s.charAt(end)){
                if (end-start+1>maxLen){
                    maxLen=end-start+1;
                    sub=s.substring(start,end+1);
                }
                start--;
                end++;
            }else {
                break;
            }
        }
    }



    public static void main(String[] args) {
        SolutionCp solutionCp = new SolutionCp();
        solutionCp.myAtoi("-2147483647");
    }
}
