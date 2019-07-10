package com.company;

/**
 * 最长回文字符串
 * Created by Justin
 * 2019/4/22  16:24
 * <p>
 * 输入:"abcdzdcab"
 * 输出:"cdzdc"
 */
public class LongestPalindrome {

    int maxLen = 0;
    String sub = "";

    //O(n*n)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            findLongestPalindrome(s, i, i); //单核回文子串
            findLongestPalindrome(s, i, i + 1); //双核回文子串
        }
        return sub;
    }

    private void findLongestPalindrome(String s, int low, int high) {
        while (low >= 0 && high <= s.length() - 1) {
            if (s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > maxLen) {
                    maxLen = high - low + 1;
                    sub = s.substring(low, high + 1);
                }
                low--;
                high++;
            } else {
                break;
            }
        }
    }

    //O(n*n*n)
    public static String longestPalindrome1(String s) {
        if (s.length() <= 1)
            return s;
        for (int i = s.length(); i > 0; i--) {//子串长度
            for (int j = 0; j <= s.length() - i; j++) {
                String sub = s.substring(j, i + j);//子串位置
                int count = 0;//计数，用来判断是否对称
                for (int k = 0; k < sub.length() / 2; k++) {//左右对称判断
                    if (sub.charAt(k) == sub.charAt(sub.length() - k - 1))
                        count++;
                }
                if (count == sub.length() / 2)
                    return sub;
            }
        }
        return "";//表示字符串中无回文子串

    }
    //O(n*n)--动态规划
    public String longestPalindrome2(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int left = 0;
        int right = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) &&( j-i<3||dp[i+1][j-1]);//小于3是因为aba一定是回文
                if(dp[i][j]&&right-left<j-i){
                    left=i;
                    right=j;
                }
            }
        }
        return s.substring(left,right+1);
    }


    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        lp.longestPalindrome("abcdzdcab");

    }
}
