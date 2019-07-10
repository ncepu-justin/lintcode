package com.company;

/**
 * 交叉字符串
 * Created by Justin
 * 2019/4/2  16:56
 */
public class IsInterleave {

    private static final String s1="aabcc";
    private static final String s2="dbbca";
    private static final String s3="aadbbcbcac";

    /*给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
    输入:
            "aabcc"
            "dbbca"
            "aadbbcbcac"
    输出:
            true
    输入:
            "aabcc"
            "dbbca"
            "aadbbbaccc"
    输出:
            false*/
    public static boolean isInterleave(String s1, String s2, String s3) {
        // write your code here

        if(s3.length()!=(s1.length()+s2.length())){
            return false;
        }
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        dp[0][0]=true;
        for (int i = 1; i <=s1.length() ; i++) {
                    if (s1.charAt(i-1)!=s3.charAt(i-1)){
                        break;
                    }
                    dp[i][0]=true;
                }
         for (int j = 1; j <=s2.length(); j++) {
                     if (s2.charAt(j-1)!=s3.charAt(j-1)){
                         break;
                     }
                     dp[0][j]=true;
                 }
                 for (int i = 1; i <=s1.length() ; i++) {
                    for (int j = 1; j <=s2.length(); j++) {
                                if (s3.charAt(i+j-1)==s2.charAt(j-1)&&dp[i][j-1]||
                                        s3.charAt(i+j-1)==s1.charAt(i-1)&&dp[i-1][j])
                                    dp[i][j]=true;
                            }
                         }
                         return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
      Boolean flag= isInterleave(s1,s2,s3);
        System.out.println(flag);
    }
}
