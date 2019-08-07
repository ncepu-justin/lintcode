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
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * leetcode 111-二叉树的最大深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最小深度  2.
     *
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
     * <p>
     * 输入：
     * <p>
     * 4
     * /   \
     * 2     7
     * / \   / \
     * 1   3 6   9
     * 输出：
     * <p>
     * 4
     * /   \
     * 7     2
     * / \   / \
     * 9   6 3   1
     *
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
     * 示例 1:
     * <p>
     * 输入:       1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:      1          1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * 输出: false
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * leetcode:101 对称二叉树--递归版本
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * 该问题可以转化为：两个树在什么情况下互为镜像？
     * 如果同时满足下面的条件，两个树互为镜像：
     * 它们的两个根结点具有相同的值。
     * 每个树的右子树都与另一个树的左子树镜像对称。
     *
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

    /**
     * leetcode:101 对称二叉树--非递归版本--基于队列
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    /**
     * leetcode 110 -平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * 示例 1:
     * 给定二叉树 [3,9,20,null,null,15,7]
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回 true
     *
     * @param root
     * @return
     */
    boolean isBalance = true;

    public boolean isBalanced(TreeNode root) {
        height(root);
        return isBalance;
    }


    //后序遍历方式
    private int height(TreeNode root) {
        if (root == null)
            return 0;
        int left = height(root.left);

        int right = height(root.right);

        if (Math.abs(left - right) > 1)
            isBalance = false;
        return 1 + Math.max(left, right);
    }

    /**
     * leetcode 112--路径总和
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * @param root
     * @param sum
     * @return 注意递归终止条件---必须是根节点到叶子节点的路径
     * ---所有只能是满足root.left==null&&root.right==null 的节点才是叶子节点 这时候直接判断是否等于sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        if (hasPathSum(root.left, sum - root.val))
            return true;
        if (hasPathSum(root.right, sum - root.val))
            return true;
        return false;
    }

    /**
     * leetcode 404. 左叶子之和
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     * @param root
     * @return
     */
    int ans = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        if (node.left != null && node.left.left == null && node.left.right == null)
            ans += node.left.val;
        dfs(node.left);
        dfs(node.right);
    }


    public void reverseString(char[] s) {
        if (s == null || s.length == 0)
            return;
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            Character temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * leetcode-657 机器人能否返回原点
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0)
            return true;
        int lCount = 0, rCount = 0, uCount = 0, dCount = 0;
        int len = moves.length();
        for (int i = 0; i < len; i++) {
            if ('L' == moves.charAt(i))
                lCount++;
            if ('R' == moves.charAt(i))
                rCount++;
            if ('U' == moves.charAt(i))
                uCount++;
            if ('D' == moves.charAt(i))
                dCount++;
        }
        return lCount == rCount && uCount == dCount;
    }


    /**
     * leetcode:121. 买卖股票的最佳时机
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * <p>
     * 思路：使用minprice保存最低买入价格，然后基于当前价格去减minprice判断是否是最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 考虑到紧跟谷的每一个峰值以最大化利润。
     * 如果我们试图跳过其中一个峰值来获取更多利润，
     * 那么我们最终将失去其中一笔交易中获得的利润，从而导致总利润的降低。
     *
     * @param prices
     * @return
     */
    public int maxProfitNbusiness(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];  //峰值
            maxprofit += peak - valley;
        }
        return maxprofit;
    }


    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:
     * 输入:
     * 1
     * /   \
     * 2     3
     * \
     * 5
     * 输出: ["1->2->5", "1->3"]
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        if (root.left == null && root.right == null)
            res.add("" + root.val);
        List<String> leftRes = binaryTreePaths(root.left);
        for (int i = 0; i < leftRes.size(); i++) {
            res.add(root.val + "->" + leftRes.get(i));
        }
        List<String> rightRes = binaryTreePaths(root.right);
        for (int i = 0; i < rightRes.size(); i++) {
            res.add(root.val + "->" + rightRes.get(i));
        }
        return res;
    }

    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7   2  5   1
     * 返回:
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> lists = new ArrayList<>();
        List<List<Integer>> sumLists = new ArrayList<>();
        if (root == null)
            return sumLists;
        if (root.left == null && root.right == null)
            if (sum == root.val) {
                lists.add(root.val);
                sumLists.add(lists);
            }
        List<List<Integer>> leftLists = pathSum(root.left, sum - root.val);
        for (int i = 0; i < leftLists.size(); i++) {
            leftLists.get(i).add(0, root.val);
        }
        List<List<Integer>> rightLists = pathSum(root.right, sum - root.val);
        for (int i = 0; i < rightLists.size(); i++) {
            rightLists.get(i).add(0, root.val);
        }
        sumLists.addAll(leftLists);
        sumLists.addAll(rightLists);
        return sumLists;
    }

    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 输入: [4,9,0,5,1]
     * 4
     * / \
     * 9   0
     *  / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sum(root, 0);
    }

    private int sum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = 10 * sum + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return sum(root.left, sum) + sum(root.right, sum);
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * 示例：
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     * 返回 3。和等于 8 的路径有:
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum3(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = findPath(root, sum);
        res += pathSum3(root.left, sum);
        res += pathSum3(root.right, sum);
        return res;
    }

    private int findPath(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = 0;
        if (sum == root.val)
            res += 1;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * 5
     * / \
     * 1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * @param root
     * @return
     */

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            //key==root.val
            if (root.left == null) {
                TreeNode right = root.right;
                root.right = null; //GC回收
                return right;
            }
            if (root.right == null) {
                TreeNode left = root.left;
                root.left = null;   //GC回收
                return left;
            }
            //root.right!=null&&root.left!=null
            TreeNode successor = new TreeNode(minmum(root.right).val);
            successor.left = root.left;
            successor.right = removeMin(root.right);
            root.left = null;    //GC回收
            root.right = null;  //GC回收
            return successor;
        }
    }

    private TreeNode removeMin(TreeNode root) {
        if (root.left == null) {
            // 就是那个我们要删除的节点
            TreeNode rightNode = root.right;
            root.right = null;
            return rightNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    private TreeNode minmum(TreeNode root) {
        if (root.left == null)
            return root;
        return minmum(root.left);
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int count = 1;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (count == k)
                return root.val;
            count++;
            root = root.right;
        }
        return -1;
    }



    /**
     * 174. 地下城游戏
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon == null)
            return -1;
        int col = dungeon.length;
        int row = dungeon[0].length;
        int[][] memo = new int[col + 1][row + 1];
        for (int i = row - 1; i >= 0; i--) {
            memo[col - 1][i] = memo[col - 1][i + 1] + dungeon[col - 1][i] > 0 ? 0 : memo[col - 1][i + 1] + dungeon[col - 1][i];
        }
        for (int j = col - 1; j >= 0; j--) {
            memo[j][row - 1] = memo[j + 1][row - 1] + dungeon[j][row - 1] > 0 ? 0 : memo[j + 1][row - 1] + dungeon[j][row - 1];
        }
        for (int i = col - 2; i >= 0; i--) {
            for (int j = row - 2; j >= 0; j--) {
                memo[i][j] = dungeon[i][j] + Math.max(memo[i + 1][j], memo[i][j + 1]) > 0 ? 0 : dungeon[i][j] + Math.max(memo[i + 1][j], memo[i][j + 1]);
            }
        }
        return memo[0][0] > 0 ? 1 : -1 * memo[0][0] + 1;
    }


    /**
     * 64. 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int col = grid.length;
        int row = grid[0].length;
        int memo[] = new int[row];  //O(n)空间复杂度
        memo[0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            memo[i] = memo[i - 1] + grid[0][i];
        }
        for (int i = 1; i < col; i++) {
            memo[0] = memo[0] + grid[i][0];
            for (int j = 1; j < row; j++) {
                memo[j] = Math.min(memo[j], memo[j - 1]) + grid[i][j];
            }
        }
        return memo[row - 1];
    }


    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;
        int col = obstacleGrid.length;
        int row = obstacleGrid[0].length;
        int memo[][] = new int[col][row];
        memo[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < row; i++) {
            memo[0][i] = obstacleGrid[0][i] == 1 ? 0 : memo[0][i-1];
        }
        for (int j = 1; j < col; j++) {
            memo[j][0] = obstacleGrid[j][0] == 1 ? 0 : memo[j-1][0];
        }
        for (int i = 1; i < col; i++) {
            for (int j = 1; j < row; j++) {
                memo[i][j] = obstacleGrid[i][j] == 1 ? 0 : memo[i][j - 1] + memo[i - 1][j];
            }
        }
        return memo[col - 1][row - 1];
    }

    public static void main(String[] args) {
        int nums[][] = {{1, 0}};
        SolutionCp solutionCp = new SolutionCp();
        solutionCp.uniquePathsWithObstacles(nums);
    }
}
