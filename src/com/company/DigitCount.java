package com.company;

/**
 * 统计数字,K为：0~9
 * Created by Justin
 * 2019/3/31  21:16
 */
public class DigitCount {

    /**
     * 计算数字 k 在 0 到 n 中的出现的次数，k 可能是 0~9 的一个值。
     样例
     样例 1：
     输入：
     k = 1, n = 1
     输出：
     1
     解释：
     在 [0, 1] 中，我们发现 1 出现了 1 次 (1)。
     样例 2：
     输入：
     k = 1, n = 12
     输出：
     5
     解释：
     在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12] 中，我们发现 1 出现了 5 次 (1, 10, 11, 12)(注意11中有两个1)。
     * @param k
     * @param n
     * @return
     */
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


