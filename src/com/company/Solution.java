package com.company;

import java.util.ArrayList;
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
        int left = 1, right = 46341;
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
     * 落单的数--通过^运算来的到唯一的落单的数
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
            if (str[i] != ' ') {
                start = i == 0 || str[i - 1] == ' ' ? i : start;
                end = i == length - 1 || str[i + 1] == ' ' ? i : end;
            }
            if (start != -1 && end != -1) {
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

    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null) {
            return null;
        }
        ListNode curl = head;
        ListNode temp = new ListNode(0);
        ListNode result = temp;
        while (curl != null) {
            if (curl.val < x) {
                temp.next = new ListNode(curl.val);
                temp = temp.next;
            }
            curl = curl.next;
        }

        while (head != null) {
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
        int flag = start.val;
        ListNode p1 = start, p2 = p1.next;
        while (p2 != end) {
            if (p2.val < flag) {
                p1 = p1.next;
                swap(p1, p2);
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

    //1.背包问题记忆化搜索
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
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = new ListNode(l1.val);
                temp = temp.next;
                l1 = l1.next;
                continue;
            }
            if (l1.val > l2.val) {
                temp.next = new ListNode(l2.val);
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }
        return res.next;
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

    public int lengthOfLongestSubstring1(String s) {

        int[] m = new int[256];

        int res = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            left = Math.max(left, m[s.charAt(i)]);

            res = Math.max(res, i - left + 1);

            m[s.charAt(i)] = i + 1;
        }
        return res;
    }

    String sub = "";
    int maxLen = 0;

    public String longestPalindrome(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            findLongestPalindrome(s, i, i);
            findLongestPalindrome(s, i, i + 1);
        }
        return sub;
    }

    private void findLongestPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length()) {
            if (s.charAt(start) == s.charAt(end)) {
                maxLen = Math.max(maxLen, end - start + 1);

            } else {
                break;
            }
        }
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
        //  sl.lengthOfLongestSubstring1("abcadbca");

    }
}


