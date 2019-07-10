package com.company;

/**
 * 统计数字,K为：0~9
 * Created by Justin
 * 2019/3/31  21:16
 */
public class DigitCount {

    public int digitCounts(int k, int n) {
        // write your code here
        int result = 0;
        for (int i = k; i <= n; i++) {
            result += singleCount(i, k);
        }
        return result;
    }

    public int singleCount(int i, int k) {
         int count=0;
         if (i==0&&k==0){
             return 1;
         }
         while (i>0){
             if (i%10==k){
                count++;
             }
             i=i/10;
         }
         return count;
    }

    public static void main(String[] args) {
        DigitCount dg = new DigitCount();
        System.out.println(dg.digitCounts(2, 25));
    }
}


