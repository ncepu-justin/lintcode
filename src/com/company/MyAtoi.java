package com.company;

/**
 *leetcode 第8题
 * Created by Justin
 * 2019/7/27  20:34
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

 示例 1:

 输入: "42"
 输出: 42
 示例 2:

 输入: "   -42"
 输出: -42
 解释: 第一个非空白字符为 '-', 它是一个负号。
      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 示例 3:

 输入: "4193 with words"
 输出: 4193
 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 示例 4:

 输入: "words and 987"
 输出: 0
 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 因此无法执行有效的转换。
 示例 5:

 输入: "-91283472332"
 输出: -2147483648
 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
      因此返回 INT_MIN (−231) 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyAtoi {
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
                //如果是等于max，则需要根据当前数是否大于7做判断
                if (res == max) {
                    if (isNegtive && (c - '0') > 7) {
                        return Integer.MIN_VALUE;
                    } else if ((c - '0') > 7) {
                        return Integer.MAX_VALUE;
                    } else {
                        return isNegtive ? -(res * 10 + (c - '0')) : res * 10 + (c - '0');
                    }
                }
                //如果是大于max 则标识越界，直接返回边界值
                if (res > max) {
                    if (isNegtive) {
                        return Integer.MIN_VALUE;
                    }
                    return Integer.MAX_VALUE;
                }
                res = res * 10 + (c - '0');
                continue;
            }
            //空格处理--针对前一个是空格，而后一个是字母的处理
            if (i > 0 && str.charAt(i - 1) == ' ' && c != str.charAt(i - 1))
                break;
        }
        return isNegtive ? -res : res;
    }
}
