package com.company;

import java.util.*;

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
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs.length;
        int index = 0;
        int maxLen = strs[index].length();
        for (int i = 0; i < length; i++) {
            if (strs[i].length() < maxLen) {
                maxLen = strs[i].length();
                index = i;
            }
        }
        if (maxLen == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            for (int j = 0; j < length; j++) {
                if (strs[index].charAt(i) != strs[j].charAt(i)) {
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
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    int maxLen = 0;
    String sub = "";

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            findLongestPalindrome(s, i, i);
            findLongestPalindrome(s, i, i + 1);
        }
        return sub;

    }

    private void findLongestPalindrome(String s, int start, int end) {
        while (start >= 0 && end <= s.length() - 1) {
            if (s.charAt(start) == s.charAt(end)) {
                if (end - start + 1 > maxLen) {
                    maxLen = end - start + 1;
                    sub = s.substring(start, end + 1);
                }
                start--;
                end++;
            } else {
                break;
            }
        }
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 经典解法：（双指针）
     * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j是快指针。
     * 只要 nums[i] = nums[j]，我们就增加 j 以跳过重复项。
     * 当我们遇到 nums[j] 不等于nums[i]跳过重复项的运行已经结束，因此我们必须把它（nums[j]）的值复制到 nums[i + 1]。
     * 然后递增 i，接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止。
     * <p>
     * 时间复杂度：O(n)，假设数组的长度是 n，那么 i和 j分别最多遍历 nn 步。
     * 空间复杂度：O(1)。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * leetcode 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 思路基于栈实现--先进后出
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        char match;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            else {
                if (stack.size() == 0)
                    return false;
                char pop = stack.pop();
                if (pop == '(')
                    match = ')';
                else if (pop == '{')
                    match = '}';
                else {
                    if (pop != '[')
                        return false;
                    match = ']';
                }
                if (s.charAt(i) != match)
                    return false;
            }
        }
        if (stack.size() != 0)
            return false;
        return true;
    }

    /**
     * leetcode 104-二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。

     二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

     说明: 叶子节点是指没有子节点的节点。

     示例：
     给定二叉树 [3,9,20,null,null,15,7]，

     3
     / \
     9  20
     /  \
     15   7
     返回它的最大深度 3 。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    /**
     * leetcode 111-二叉树的最大深度
     * 给定一个二叉树，找出其最小深度。
     最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     说明: 叶子节点是指没有子节点的节点。
     示例:
     给定二叉树 [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     返回它的最小深度  2.
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    /**
     * leetcod-226 翻转二叉树
     * 示例：

     输入：

     4
     /   \
     2     7
     / \   / \
     1   3 6   9
     输出：

     4
     /   \
     7     2
     / \   / \
     9   6 3   1
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * leetcode:100 相同的树
     *   示例 1:

     输入:       1         1
     / \       / \
     2   3     2   3

     [1,2,3],   [1,2,3]

     输出: true
     示例 2:

     输入:      1          1
     /           \
     2             2

     [1,2],     [1,null,2]

     输出: false
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;
        if(p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * leetcode:101 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。

     例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

     1
     / \
     2   2
     / \ / \
     3  4 4  3
     但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

     1
     / \
     2   2
     \   \
     3    3
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
    public static void main(String[] args) {
        int nums[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        SolutionCp solutionCp = new SolutionCp();
        solutionCp.isValid("()[]{}");
    }
}
