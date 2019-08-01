package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Definition for ListNode
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {


    /**
     * 判断是不是回文串
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s.length() == 0)
            return true;
        int l = 0, r = s.length() - 1;
        while(l < r){
            //确定指定的字符是否为字母或数字
            if(!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }else if(!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }else{
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                    return false;
                l++;
                r--;
            }
        }
        return true;
    }


    /**
     * 分割回文串
     *
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if(s==null||s.length()==0)
            return res;
        dfs(s,new ArrayList<String>(),0);
        return res;
    }
    /*在分割的过程中对于每一个字符串而言都可以分为两部分：左边一个回文串加右边一个子串，比如 “abc” 可分为 “a” + “bc” 。 然后对"bc"分割仍然是同样的方法，分为"b"+“c”。

    在处理的时候去优先寻找更短的回文串，然后回溯找稍微长一些的回文串分割方法，不断回溯，分割，直到找到所有的分割方法。

    举个🌰：分割"aac"。

    分割为 a + ac
    分割为 a + a + c，分割后，得到一组结果，再回溯到 a + ac
    a + ac 中 ac 不是回文串，继续回溯，回溯到 aac
    分割为稍长的回文串，分割为 aa + c 分割完成得到一组结果，再回溯到 aac
    aac 不是回文串，搜索结束*/
    public void dfs(String s,List<String> remain,int left){
        if(left==s.length()){  //判断终止条件
            res.add(new ArrayList<String>(remain));
            System.out.println("结果res为："+ Arrays.toString(res.toArray()));//添加到结果中
            return;
        }
        for(int right=left;right<s.length();right++){  //从left开始，依次判断left->right是不是回文串
            if(isPalindroom(s,left,right)){  //判断是否是回文串
                remain.add(s.substring(left,right+1));
                System.out.println("add当前remain 包含："+ Arrays.toString(remain.toArray())+"left:"+left+"right:"+right);//添加到当前回文串到list中
                dfs(s,remain,right+1);  //从right+1开始继续递归，寻找回文串
                remain.remove(remain.size()-1);  //回溯，从而寻找更长的回文串
                System.out.println("remove当前remain 包含："+ Arrays.toString(remain.toArray())+"left:"+left+"right:"+right);//添加到当前回文串到list中
            }
        }
    }
    /**
     * 判断是否是回文串
     */
    public boolean isPalindroom(String s,int left,int right){
        while(left<right&&s.charAt(left)==s.charAt(right)){
            left++;
            right--;
        }
        return left>=right;
    }

    /**
     * 单词拆分
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int max_length=0;
        for(String temp:wordDict){
            max_length = temp.length() > max_length ? temp.length() : max_length;
        }
        // memo[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        boolean[] memo = new boolean[n + 1];
        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i-1; j >= 0 && max_length >= i - j; j--) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    System.out.println(Arrays.toString(memo)+"j:"+j+","+"i:"+i+s.substring(j, i));
                    break;
                }
            }
        }
        return memo[n];
    }

    /**
     * 字符串转整数
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }



    /**
     * 合并两个有序链表--通过先构建一个头结点，然后遍历两个有序链表,按照升序链在新的头结点后边，
     * 当处理到有一个链表为空时，则将另外一个链表直接链接到新链表后面
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsSimple(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(0);
        ListNode temp=dummy;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                temp.next=l1;
                l1=l1.next;
            }
            else {
                temp.next=l2;
                l2=l2.next;
            }
            temp=temp.next;
        }
        if (l1!=null){
            temp.next=l1;
        }if (l2!=null){
            temp.next=l2;
        }
        return dummy.next;
    }

    /**
     * 合并K个有序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        // write your code here
        ListNode curl = null;
        ListNode result = null;
        int pos = 0;
        while (lists.size() != 0) {
            int temp = Integer.MAX_VALUE;
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i) == null) {
                    lists.remove(i);
                }
                if (lists.size() > i && temp > lists.get(i).val) {
                    pos = i;
                    temp = lists.get(i).val;
                }
            }
            if (lists.size() == 0) {
                break;
            }
            ListNode node = lists.get(pos);
            if (result == null) {
                curl = node;
                result = curl;
            } else {
                curl.next = node;
                curl = curl.next;
            }
            node = node.next;
            if (node != null) {
                lists.remove(pos);
                lists.add(pos, node);
            }
            if (node == null) {
                lists.remove(pos);
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // write your code here
        if (head == null) {
            return null;
        }
        ListNode curl = head;
        ListNode temp = head;
        while (head != null) {
            //针对当前第一个节点为val且curl为空时的处理情况
            if (head.val == val && head == temp) {
                curl = head.next;
                head = head.next;
                temp = head;
                continue;
            }
            if (head.val == val && head != temp) {
                temp.next = temp.next.next;
                head = head.next;
                continue;
            }
            if (head.next != null) {
                if (head.next.val != val) {
                    head = head.next;
                } else {
                    head.next = head.next.next;
                    temp = head;
                    head = head.next;
                }
            } else head = head.next;
        }
        return curl;
    }

    /**
     * \
     * 移除链表元素简单版--通过先构建一个头结点，将待处理的链表加入该头结点，做遍历即可
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsLeetcode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(0);
        ListNode res = first;
        first.next = head;
        while (first.next != null) {
            if (first.next.val == val) {
                first.next = first.next.next;
            } else {
                first = first.next;
            }
        }
        return res.next;
    }

    /**
     * 基于二分查找求解x的平方根
     *
     * @param x
     * @return
     */
    public int sqrt(int x) {
        // write your code here
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x/2;
        int mid = 0;

        while (left < right) {
            //跳出循环--防止死循环
            if (mid == (left + right) / 2) {
                break;
            }
            mid = (left + right) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return mid;
    }

    /**
     * 最大子序列--使用max变量保存当前最大值，另外一个sum变量用来累加：如果目前累加和小于0则抛弃该结果，只保留大于0的结果
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        if (nums == null) {
            return 0;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            sum = sum < 0 ? 0 : sum;
        }
        return max;
    }

    /**
     * 落单的数--通过^运算来得到唯一落单的数
     * @param A
     * @return
     */
    public int singleNumber(int[] A) {
        // write your code here
        if (A == null) {
            return Integer.MIN_VALUE;
        }
        int res = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            res ^= A[i];
        }
        return res;
    }

    /**
     * 翻转字符串
     * @param s
     * @return
     */
    /*单词的构成：无空格字母构成一个单词，有些单词末尾会带有标点符号
    输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
    如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个*/
    /*示例：
    输入:  "the sky is blue"
    输出:  "blue is sky the"*/
    /*解决思路：
    先整体翻转---eulb si yks eht
    然后对每个字符串单独翻转，注意空格处理
    */
    public String reverseWords(String s) {
        // write your code here
        if (s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        int length = s.length();
        reverseWord(str, 0, length - 1);
        int start = -1;
        int end = -1;
        for (int i = 0; i < length; i++) {
            if (str[i] != ' ') {           //遍历字符串当遇到不为空格时，尝试更新 start和end 的位置
                start = i == 0 || str[i - 1] == ' ' ? i : start;            //确定start的位置，当前一位不是空格时，则表示这个单词还不是一个新开始单词，start不变
                end = i == length - 1 || str[i + 1] == ' ' ? i : end;       //确定end的位置，当后一位不是空格时，则表示这个单词未结束，end不变
            }
            if (start != -1 && end != -1) {   //当start和end都不为-1时，则表示有一个新的单词位置被确定，这时候可以进行翻转
                reverseWord(str, start, end);
                start = -1;
                end = -1;
            }

        }
        return String.valueOf(str);
    }

    private void reverseWord(char[] str, int start, int end) {
        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 数组划分:---依据快排思路解决
     * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
     * 所有小于k的元素移到左边
     * 所有大于等于k的元素移到右边
     * 返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            while (start <= end && nums[end] >= k) {
                end--;
            }
            while (start <= end && nums[start] < k) {
                start++;
            }
            if (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }

        }
        return start;

    }

    /**
     * 三数之和--给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 解决方法：首先对该数组排序，然后拆分，对要求三个元素之和拆解为：
     * 通过for循环遍历一个外围元素（做判重操作，如果这个元素等于前一个元素，则抛弃）+两数之和
     * 对于求两数之和，通过首尾相加，如果相加之和小于target，则首++，大于则尾++；
     * 当相加之和等于target,也需要做判重操作，如果这个元素等于前一个元素，则抛弃，使首++；
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length==0||nums==null){
            return new ArrayList<>();
        }
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        int len=nums.length;
        for (int i = 0; i <len; i++) {
            if (i==0||nums[i]!=nums[i-1]){
                List<List<Integer>> integers=twoSums(nums,i,0-nums[i]);
                res.addAll(integers);
            }
        }
        return res;
    }

    private List<List<Integer>>  twoSums(int[] nums,int index,int target){
        List<List<Integer>> integerLists=new ArrayList<>();
        int left=index+1;
        int right=nums.length-1;
        while (left<right){
            if (nums[left]+nums[right]<target){
                left++;
                continue;
            }
            else if (nums[left]+nums[right]>target){
                right--;
                continue;
            }else {
                if (left==index+1||nums[left-1]!=nums[left]){
                    List<Integer> integerList=new ArrayList<>(3);
                    integerList.add(nums[index]);
                    integerList.add(nums[left]);
                    integerList.add(nums[right]);
                    integerLists.add(integerList);
                    left++;
                    right--;
                    continue;
                }else {
                    left++;
                }
            }
        }
        return integerLists;
    }

    /**
     * 链表划分：----两次遍历--第一次遍历寻找小于x节点并依次插入临时节点，第二次遍历寻找大于x节点并依次插入到之前临时节点
     * 给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。
     * 你应该保留两部分内链表节点原有的相对顺序。
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null) {
            return null;
        }
        ListNode curl = head;
        ListNode temp = new ListNode(0);
        ListNode result = temp;
        while (curl != null) {   //第一次遍历--寻找比x小的节点
            if (curl.val < x) {
                temp.next = new ListNode(curl.val);
                temp = temp.next;
            }
            curl = curl.next;
        }

        while (head != null) {   //第二次遍历--寻找比x大的节点
            if (head.val >= x) {
                temp.next = new ListNode(head.val);
                temp = temp.next;
            }
            head = head.next;
        }
        temp.next = null;

        return result.next;
    }

    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }
        int[] record = new int[256];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (record[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return '0';
    }

    /*[-1, -2, -3, 4, 5, 6]，重新排序之后，变成[-1, 5, -2, 4, -3, 6]*/

    /**
     *
     * lintcode:144. 交错正负数
     * 给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
     *
     * 样例
     * 样例 1
     *
     * 输入 : [-1, -2, -3, 4, 5, 6]
     * 输出 : [-1, 5, -2, 4, -3, 6]
     * 解释 : 或者仍和满足条件的答案
     *
     * 基于快排，以0为基准，进行一次分治，将整个数组分为比0小和比0大两部分
     * 然后将两边元素 交叉互换
     *
     * @param A
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null) {
            return;
        }
        int temp = 0;
        int length = A.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            while (left <= right && A[left] > temp) {
                left++;
            }
            while (left <= right && A[right] < temp) {
                right--;
            }
            if (left <= right) {
                swap(A, left, right);
                right--;
                left++;
            }
        }
        if (left >= length - left) {
            for (int i = 1; i < left; i += 2) {
                swap(A, i, length - i - 1);
            }
        } else {
            for (int i = 0; i < left; i += 2) {
                swap(A, i, length - i - 1);
            }
        }

    }

    public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs.length == 0) {
            return "";
        }
        int length = strs.length;
        int minStrLenght = strs[0].length();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < length; i++) {
            if (minStrLenght > strs[i].length()) {
                minStrLenght = strs[i].length();
            }
        }
        int i = 0;
        for (; i < minStrLenght; i++) {
            int j = 0;
            for (; j < length - 1; j++) {
                if (strs[j].charAt(i) == strs[j + 1].charAt(i)) {
                    continue;
                } else {
                    return result.toString();
                }
            }
            result.append(strs[j].charAt(i));
        }

        return result.toString();
    }

    /**
     * 链表排序--基于快排
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null) {
            return head;
        }
        quickSort(head, null);
        return head;
    }

    private void quickSort(ListNode start, ListNode end) {
        if (start == end) {
            return;
        }
        ListNode temp = partition(start, end);
        quickSort(start, temp);
        quickSort(temp.next, end);

    }

    private ListNode partition(ListNode start, ListNode end) {
        int flag = start.val;                 //开始节点做标识位
        ListNode p1 = start, p2 = p1.next;    //
        while (p2 != end) {
            if (p2.val < flag) {
                p1 = p1.next;
                swap(p1, p2);    //
            }
            p2 = p2.next;
        }
        swap(p1, start);
        return p1;
    }

    private void swap(ListNode node1, ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    int arr[];

    public int integerBreak(int n) {
        arr = new int[n + 1];
        return _integerBreak(n);
    }

    public int _integerBreak(int n) {
        if (n == 1) {
            return 1;
        }
        if (arr[n] != 0) {
            return arr[n];
        }
        int res = -1;
        for (int i = 1; i <= n; i++) {
            res = max3(res, i * (n - i), i * _integerBreak(n - i));
        }
        arr[n] = res;
        return res;
    }

    private int max3(int res, int i, int i1) {
        return Math.max(res, Math.max(i, i1));
    }

    /**
     * 链表求和--使用一个sum变量来存储每次两个链表相加的和，先进行%取余构建新节点，然后使用/取是否进位
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * * 输出：7 -> 0 -> 8
     * * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode curl = new ListNode(0);
        ListNode res = curl;
        int sum = 0;
        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            curl.next = new ListNode(sum % 10);
            sum /= 10;
            curl = curl.next;
        }
        return res.next;
    }


    public int rob(int[] nums) {
        arr = new int[nums.length];
        converterArr(arr);
        int res = tryRob(nums, 0);
        return res;
    }

    private void converterArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1;
        }
    }

    private void converter2DArr(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = -1;
            }
        }
    }

    private int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        ;
        int res = 0;
        if (arr[index] != -1) {
            return arr[index];
        }
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        arr[index] = res;
        return res;
    }

    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int arrs[] = new int[n];
        arrs[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            for (int j = i; j < n; j++) {
                arrs[i] = Math.max(arrs[i], nums[j] + (j + 2 < n ? arrs[j + 2] : 0));
            }
        return arrs[0];
    }

    int arrs[][];

    /**
     * 背包问题--解决 通过二维数组来保存曾经递归计算出来的值，然后自顶向下递归搜索
     * @param m
     * @param A
     * @return
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (m == 0) {
            return 0;
        }
        int index = A.length - 1;
        arrs = new int[m + 1][index + 1];
        converter2DArr(arrs);
        return __backPack(m, A, index);
    }

    private int __backPack(int m, int[] a, int index) {
        if (index < 0 || m <= 0) {
            return 0;
        }
        if (arrs[m][index] != -1) {
            return arrs[m][index];
        }
        int res = __backPack(m, a, index - 1);
        if (m >= a[index]) {
            res = Math.max(res, a[index] + __backPack(m - a[index], a, index - 1));
        }
        arrs[m][index] = res;
        return res;
    }

    //1.背包问题动态规划
    public int backPack1(int m, int[] A) {
        // write your code here
        if (m == 0) {
            return 0;
        }
        int index = A.length - 1;
        arr = new int[m + 1];
        for (int i = 0; i < m; i++) {
            arr[i] = A[0] <= i ? A[0] : 0;
        }
        for (int i = 1; i <= index; i++) {
            for (int j = m; j >= A[i]; j--) {
                arr[j] = Math.max(arr[j], A[i] + arr[j - A[i]]);
            }
        }
        return arr[m];
    }

    /**
     * 最长上升子序列
     * @param nums
     * @return
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int arrs[] = new int[n];
        converterArr1(arrs);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    arrs[i] = Math.max(arrs[i], 1 + arrs[j]);
            }
        }
        int res = 1;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, arrs[i]);
        }
        return res;
    }

    private void converterArr1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1;
        }
    }

    int memo[][];

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        memo = new int[nums.length][(sum % 2) + 1];
        converter2DArr(memo);
        return tryPartition(nums, nums.length - 1, sum % 2);
    }

    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0 || index < 0) {
            return false;
        }
        if (memo[index][sum] != -1) {
            return memo[index][sum] == 1;
        }
        memo[index][sum] = (tryPartition(nums, index - 1, sum) ||
                tryPartition(nums, index - 1, sum - nums[index])) ? 1 : 0;
        return memo[index][sum] == 1;
    }

    /*最长公共子序列--记忆化搜索--递归*/
    int lcsmemo[][];

    public int longestCommonSubsequence_recursive(String A, String B) {
        // write your code here
        if (A == null || B == null) {
            return 0;
        }
        int alen = A.length();
        int blen = B.length();
        lcsmemo = new int[alen][blen];
        converter2DArr(lcsmemo);
        return lcs(A, alen - 1, B, blen - 1);
    }

    private int lcs(String a, int aIndex, String b, int bIndex) {
        if (aIndex < 0 || bIndex < 0) {
            return 0;
        }
        if (lcsmemo[aIndex][bIndex] != -1) {
            return lcsmemo[aIndex][bIndex];
        }
        if (a.charAt(aIndex) == b.charAt(bIndex)) {
            lcsmemo[aIndex][bIndex] = 1 + lcs(a, aIndex - 1, b, bIndex - 1);
            return lcsmemo[aIndex][bIndex];
        }
        lcsmemo[aIndex][bIndex] = Math.max(lcs(a, aIndex - 1, b, bIndex), lcs(a, aIndex, b, bIndex - 1));
        return lcsmemo[aIndex][bIndex];
    }

    /*最长公共子序列--动态规划*/
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if (A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int alen = A.length();
        int blen = B.length();
        lcsmemo = new int[alen][blen];
        if (A.charAt(0) == B.charAt(0)) {
            lcsmemo[0][0] = 1;
        }
        for (int i = 1; i < alen; i++) {
            lcsmemo[0][i] = Math.max(A.charAt(0) == B.charAt(i) ? 1 : 0, lcsmemo[0][i - 1]);
        }
        for (int i = 1; i < blen; i++) {
            lcsmemo[i][0] = Math.max(A.charAt(i) == B.charAt(0) ? 1 : 0, lcsmemo[i - 1][0]);
        }
        for (int i = 1; i < alen; i++) {
            for (int j = 1; j < blen; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    lcsmemo[i][j] = 1 + lcsmemo[i - 1][j - 1];
                    continue;
                }
                lcsmemo[i][j] = Math.max(lcsmemo[i - 1][j], lcsmemo[i][j - 1]);
            }
        }
        return lcsmemo[alen - 1][blen - 1];
    }

    /*最大容量背包问题--动态规划*/
    public long getMaxValue(int s, int[] v, int[] c) {
        // Write your code here
        if (s == 0) {
            return 0;
        }
        int n = v.length;
        long[] memo = new long[s + 1];
        for (int i = 0; i <= s; i++) {
            memo[i] = c[0] <= i ? v[0] : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = s; j >= c[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - c[i]]);
            }
        }
        return memo[s];
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int asc = 0;
        int desc = 0;
        int n = nums.length;
        int[] arrs = new int[n];
        converterArr1(arrs);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; i++) {
                if (nums[j] < nums[i] && asc == 0) {
                    arrs[i] = Math.max(arrs[i], 1 + arrs[j]);
                    asc = 1;
                    desc = 0;
                    continue;
                }
                if (nums[j] > nums[i] && desc == 0) {
                    arrs[i] = Math.max(arrs[i], 1 + arrs[j]);
                    desc = 1;
                    asc = 0;
                    continue;
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, arrs[i]);
        }
        return res;
    }

    String[] str = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    ArrayList<String> strs = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return strs;
        }
        findCombination(digits, 0, "");
        return strs;
    }

    private void findCombination(String digits, int index, String s) {
        if (index == digits.length()) {
            strs.add(s);
            return;
        }
        char num = digits.charAt(index);
        String letters = str[num - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
        return;
    }

    /**
     * 合并K个排序链表--通过归并排序-递归对两个有序链表做合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }
        int start = 0;
        int end = lists.length - 1;
        ListNode res = mergeKListHelper(lists, start, end);
        return res;
    }

    private ListNode mergeKListHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListHelper(lists, start, mid);
        ListNode right = mergeKListHelper(lists, mid + 1, end);
        ListNode res = mergeTwoLists(left, right);
        return res;
    }


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
               res.next=l1;
               l1=l1.next;
            }
            else  {
                res.next=l2;
                l2=l2.next;
            }
            res=res.next;
        }
        if (l1 != null) {
            res.next = l1;
        }
        if (l2 != null) {
            res.next = l2;
        }
        return dummy.next;
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean flag = false;//false 表示正数
        if (x < 0) {
            flag = true;//保存x正负位级别
            x *= (-1);
        }
        int res = 0;
        while (x > 0) {
            int temp = x % 10;
            x /= 10;
            res = (int) (res * Math.pow(10, 1) + temp);
        }
        if (res >= Integer.MAX_VALUE) {
            res = 0;
        }
        if (flag) {
            res *= -1;
        }
        return res;
    }

    /**
     * 给定一个字符串，请找出其中无重复字符的最长子字符串。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 最长子串是 "abc".
     * 样例 2:
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 最长子串是 "b".
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] m = new int[256];
        int res = 0, left = 0;
        //基于字符在数组中对应的ASCII码的特性--较难理解
        for (int i = 0; i < s.length(); i++) {
            left = Math.max(left, m[s.charAt(i)]);//left确定当前无重复字符串左下标
            res = Math.max(res, i - left + 1);//目前最长的无重复字符串
            m[s.charAt(i)] = i + 1; //在该字符位置存储该字符在字符串中的最新下标，
        }
        return res;
    }


    /**
     * 空间换时间  使用hashset做去重处理,使用两个位置标记记录，right记录遍历到的位置，
     * left保证（right-left）位置的单词不包含重复单词，使用res记录当前位置，无重复字符的最长长度
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
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
       /* int[] numbers = {9, 3, 2, 4, 8, 13, 1, 11, 10};
        Solution sl = new Solution();
        System.out.println(sl.kthLargestElement(3, numbers));*/
        ListNode listnode1 = new ListNode(0);
        ListNode test = listnode1;

       /* listnode1.next = new ListNode(1);
        listnode1 = listnode1.next;
        listnode1.next = new ListNode(7);*/

        /*listnode1 = listnode1.next;
        listnode1.next = new ListNode(5);
        listnode1 = listnode1.next;
        listnode1.next = new ListNode(3);
        listnode1 = listnode1.next;*/

        // ListNode listnode2 = new ListNode(-1);
        //ListNode listnode3 = null;
        ListNode listnode2 = new ListNode(9);
        ListNode test2 = listnode2;
        listnode2.next = new ListNode(9);
        /*listnode2 = listnode2.next;
        listnode2.next = new ListNode(5);*/
        Solution sl = new Solution();
        sl.lengthOfLongestSubstring3("abcadbca");

    }
}


