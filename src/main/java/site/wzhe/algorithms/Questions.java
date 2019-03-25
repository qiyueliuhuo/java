package site.wzhe.algorithms;

import java.util.*;

/**
 * @author: wangzhe.
 * @description: 所有题目
 * @date: 2019/2/27.
 */
public class Questions {


    /**
     * 题目1：给定一个字符串s，请计算输出含有连续两个s作为子串的最短字符串
     * e.g：1. 输入abc，输出abcabc  2. 输入abcdab，输出abcdabcd，3. 输入aaa，输出aaaa
     *
     * @param s
     * @return result
     */
    private static char[] solution01(char[] s) {
        int length = s.length;
        // dp记录上一次迭代s(0...i-1)字符串头尾有相同的字符串的字符个数
        int dp = 0;
        int i = 1;
        while (i < length) {
            if (s[dp] == s[i]) dp++;
            else dp = 0;
            i++;
        }
        if (dp == length - 1) {
            // s = "a" 或者 s = "aaaa"
            char[] result = new char[length + 1];
            System.arraycopy(s, 0, result, 0, length);
            result[length] = s[0];
            return result;
        } else {
            char[] result = new char[2 * length - dp];
            System.arraycopy(s, 0, result, 0, length);
            System.arraycopy(s, dp, result, length, length - dp);
            return result;
        }
    }

    /**
     * 题目1
     * 暂时只满足s中只有26个英文字母
     *
     * @param s
     * @return
     */
    private static char[] solution02(char[] s) {

        int length = s.length;
        int i = 0, j = length - 1;
        int head = 0, tail = 0, dp = 0;
        while (i < length - 1) {
            head = head * 26 + (s[i] - 'a' + 1);
            tail = tail + (s[j] - 'a' + 1) * (int) Math.pow(26, i);
            if (head == tail) {
                dp = i + 1;
            }
            i++;
            j--;
        }

        if (dp == length - 1) {
            // s = "a" 或者 s = "aaaa"
            char[] result = new char[length + 1];
            System.arraycopy(s, 0, result, 0, length);
            result[length] = s[0];
            return result;
        } else {
            char[] result = new char[2 * length - dp];
            System.arraycopy(s, 0, result, 0, length);
            System.arraycopy(s, dp, result, length, length - dp);
            return result;
        }
    }


    /**
     * 题目2：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * e.g: 1. 输入: "abcabcbb" 输出: 3
     * 2. 输入: "bbbbb"  输出: 1
     *
     * @param s
     * @return
     */
    private static int solution03(String s) {
        char[] input = s.toCharArray();
        int length = input.length;
        if (0 == length) {
            return 0;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            // 这段代码可以使用hash思想优化下
            int flag = -1;
            int j = dp[i - 1];
            for (; j > 0; j--) {
                if (input[i - j] == input[i]) {
                    flag = j;
                }
            }
            dp[i] = (flag == -1) ? dp[i - 1] + 1 : flag;
        }
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }
        return maxLength;
    }

    /**
     * 题目3：给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * e.g.: 1. 输入: "babad" 输出: "bab"
     * 2. 输入: "cbbd"  输出: "bb"
     * 此解法还是效率太低，看官方解法前再优化优化
     *
     * @param s
     * @return
     */
    private static String solution04(String s) {
        char[] input = s.toCharArray();
        int length = input.length;
        int[][] dp = new int[length][length];
        int max = 0, ri = 0;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                // 后面优化加进去的
                if (max > j - i + 1) {
                    continue;
                }
                if (1 == isAlindrome(input, dp, i, j)) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        ri = i;
                    }
                }
            }
        }

        return String.valueOf(input, ri, max);
    }

    /**
     * 输入要求 end >= start
     * dp[input.length][input.length]
     *
     * @param input
     * @param dp
     * @param start
     * @param end
     * @return int
     */
    private static int isAlindrome(char[] input, int[][] dp, int start, int end) {
        int length = input.length;
        if (start > end || start < -1 || end >= length) {
            return -1;
        }
        if (0 != dp[start][end]) {
            return dp[start][end];
        }
        if (start == end) {
            return 1;
        } else if (1 == end - start || 2 == end - start) {
            if (input[start] == input[end]) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (isAlindrome(input, dp, start + 1, end - 1) == 1) {
                if (input[start] == input[end]) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

    }

    /**
     * 题目4: 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * e.g: 1. 输入: s = "LEETCODEISHIRING", numRows = 3  输出: "LCIRETOESIIGEDHN"
     * 2. 输入: s = "LEETCODEISHIRING", numRows = 4  输出: "LDREOEIIECIHNTSG"
     *
     * @param s
     * @return
     */
    private static String solution05(String s, int numRows) {
        if (1 == numRows) {
            return s;
        }
        char[] input = s.toCharArray();
        int length = input.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows && i < length; i++) {
            // 第一列
            result.append(input[i]);

            for (int j = i + 2 * (numRows - 1); j < length || (j - 2 * i) < length; j = j + 2 * (numRows - 1)) {
                // 非首行和末行
                if (0 != i && (numRows - 1) != i) {
                    result.append(input[j - 2 * i]);
                }
                if (j < length) {
                    result.append(input[j]);
                }
            }
        }
        return result.toString();
    }

    /**
     * 题目5：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * e.g: 1. 输入: 123  输出: 321
     * 2. 输入: -123 输出: -321
     * 思路： 余10，result * 10 加上余10后的结果
     * 需要验证结果是否又可能移除，可以在加操作前，用最大值减去余10后的结果与result * 10比较
     *
     * @param x
     * @return
     */
    private static int solution06(int x) {
        int input = x;
        if (input < 0) {
            input = -input;
        }
        int result = 0;
        int temp = 0;
        while (input > 0) {
            temp = input % 10;
            // 溢出，可能存在的问题：负数和正数不一样
            // 乘可能造成的溢出
            if (Integer.MAX_VALUE / 10 < result) {
                result = 0;
                break;
            }
            // 加可能造成的溢出
            if (Integer.MAX_VALUE - result * 10 < temp) {
                result = 0;
                break;
            }
            result = result * 10 + temp;
            input = input / 10;
        }
        if (x < 0) {
            return -result;
        }
        return result;
    }

    /**
     * 题目6 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * @param str
     * @return
     */
    private static int solution07(String str) {
        if (str.trim().length() == 0) {
            return 0;
        }
        char[] input = str.trim().toCharArray();
        int result = 0, sign = 1;
        if (input[0] == '+' || input[0] == '-' || (input[0] >= '0' && input[0] <= '9')) {
            int i = 0;
            if (input[0] == '+') {
                sign = 1;
                i = 1;
            } else if (input[0] == '-') {
                sign = -1;
                i = 1;
            } else {
                sign = 1;
            }
            for (; i < input.length && input[i] <= '9' && input[i] >= '0'; i++) {
                // 这段处理仍旧有问题，当时处在负数int边界时会出问题
                if (result > (Integer.MAX_VALUE - (input[i] - '0')) / 10) {
                    if (sign == 1) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
                result = result * 10 + (input[i] - '0');
            }
            return result * sign;
        }

        return 0;
    }

    /**
     * 题目7 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * e.g: 1. 输入: 121  输出: true
     * 2. 输入: -121 输出: false
     *
     * @param x
     * @return
     */
    private static boolean solution08(int x) {
        if (x < 0) {
            return false;
        }
        int result = 0, input = x;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result == input;
    }

    /**
     * 题目8
     * 思路：
     * 同时修改s字符串和p字符串，试图变换为相等
     *
     * @param s
     * @param p
     * @return
     */
    private static boolean solution09(String s, String p) {
        return false;
    }

    /**
     * 题目9 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
     * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * e.g: 输入: [1,8,6,2,5,4,8,3,7]  输出: 49
     * 思路：双指针，但证明此方法可靠需要证明
     *
     * @param height
     * @return
     */
    private static int solution10(int[] height) {
        if (height.length < 2) {
            throw new IllegalArgumentException();
        }
        int i = 0, j = height.length - 1, max = Math.min(height[i], height[j]) * (j - i);
        while (i < j) {
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
            int volume = Math.min(height[i], height[j]) * (j - i);
            if (volume > max) {
                max = volume;
            }
        }
        return max;
    }

    /**
     * 题目10 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * 1. I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * 2. X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * 3. C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * e.g: 1. 输入: 58   输出: "LVIII"
     * 2. 输入: 1994  输出: "MCMXCIV"
     *
     * @param num
     * @return
     */
    private static String solution11(int num) {
        StringBuffer sb = new StringBuffer();
        if (num >= 1000) {
            int numOfM = num / 1000;
            while (numOfM-- > 0) {
                sb.append('M');
            }
            num = num % 1000;
        }
        if (num >= 900) {
            sb.append("CM");
            num = num - 900;
        }
        if (num >= 500) {
            sb.append('D');
            num = num - 500;
        }
        if (num >= 400) {
            sb.append("CD");
            num = num - 400;
        }
        if (num >= 100) {
            int numOfM = num / 100;
            while (numOfM-- > 0) {
                sb.append('C');
            }
            num = num % 100;
        }
        if (num >= 90) {
            sb.append("XC");
            num = num - 90;
        }
        if (num >= 50) {
            sb.append('L');
            num = num - 50;
        }
        if (num >= 40) {
            sb.append("XL");
            num = num - 40;
        }
        if (num >= 10) {
            int numOfM = num / 10;
            while (numOfM-- > 0) {
                sb.append('X');
            }
            num = num % 10;
        }
        if (num >= 9) {
            sb.append("IX");
            num = num - 9;
        }
        if (num >= 5) {
            sb.append('V');
            num = num - 5;
        }
        if (num >= 4) {
            sb.append("IV");
            num = num - 4;
        }
        if (num >= 1) {
            int numOfM = num;
            while (numOfM-- > 0) {
                sb.append('I');
            }
        }
        return sb.toString();
    }

    /**
     * 题目11 编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""。
     * e.g: 1. 输入: ["flower","flow","flight"]  输出: "fl"
     * 2. 输入: ["dog","racecar","car"]   输出: ""
     *
     * @param strs
     * @return
     */
    private static String solution12(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int maxIndex = 0;
        while (true) {
            int length = strs.length;
            while (length-- > 1) {
                if (maxIndex >= strs[length].length() || maxIndex >= strs[length - 1].length() || strs[length].charAt(maxIndex) != strs[length - 1].charAt(maxIndex)) {
                    break;
                }
            }
            if (length == 0) {
                maxIndex++;
            } else {
                break;
            }
        }
        return strs[0].substring(0, maxIndex);
    }


    /**
     * 题目12 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *  注意：答案中不可以包含重复的三元组。
     *  e.g: 输入 :nums = [-1, 0, 1, 2, -1, -4]  输出: [[-1, 0, 1],[-1, -1, 2]]
     * @param nums
     * @return
     */
    private static List<List<Integer>> solution13(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<List<Integer>>();
        }

        boolean sign = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                sign = false;
            }
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (sign) {
            List<Integer> temp = new Array();
            temp.add(0);
            temp.add(0);
            temp.add(0);
            result.add(temp);
            return result;
        }
        List<Integer> selectedIndex = new ArrayList<Integer>();
        combination(result, selectedIndex, nums);
        return distinct(result);

    }

    private static List distinct(List<List<Integer>> result) {

        Set<List<Integer>> set = new HashSet(result);
        return new ArrayList(set);
    }

    public static class Array extends ArrayList<Integer> {
        /**
         * 完全相等
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {

            Set<Integer> set1 = new HashSet<Integer>((Array)o);
            Set<Integer> set2 = new HashSet<Integer>(this);
            if (set1.size() != set2.size()) {
                return false;
            }

            ArrayList<Integer> temp = (ArrayList<Integer>) o;
            for (Integer i : temp) {
                if (!this.contains(i)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            Integer sum = 0;
            Integer temp = 1;
            for (Integer i : this) {
                sum += i;
                temp *= i;
            }
            sum += temp;
            return sum;
        }
    }

    private static void combination(List<List<Integer>> result, List<Integer> selectedIndex, int[] nums) {

        if (3 == selectedIndex.size()) {
            int sum = 0;
            for (int i = 0; i < selectedIndex.size(); i++) {
                sum += nums[selectedIndex.get(i)];
            }
            if (0 == sum) {
                List<Integer> temp = new Array() {
                };
                temp.add(nums[selectedIndex.get(0)]);
                temp.add(nums[selectedIndex.get(1)]);
                temp.add(nums[selectedIndex.get(2)]);
                result.add(temp);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (selectedIndex.size() == 0 || i > selectedIndex.get(selectedIndex.size() - 1)) {
                List<Integer> selected = new ArrayList<Integer>(selectedIndex);
                selected.add(i);
                combination(result, selected, nums);
            }

        }
    }

    /**
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> solution14(int[] nums) {
        Arrays.sort(nums);
        return null;
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 题目13：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * e.g：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode solution15(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode result = null, preNode = null, temp = null;
        int carryFlag = 0;
        while (!(l1 == null && l2 == null)) {
            int v1 = 0, v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
            }
            if (l2 != null) {
                v2 = l2.val;
            }
            if ((carryFlag != 0) || (l1 != null && l2 != null)) {
                temp = new ListNode((v1 + v2 + carryFlag) % 10);
                carryFlag = (v1 + v2 + carryFlag >= 10) ? 1 : 0;
            } else {
                if (l1 == null) {
                    temp = l2;
                }
                if (l2 == null) {
                    temp = l1;
                }
            }
            if (preNode == null) {
                preNode = temp;
                result = preNode;
            } else {
                preNode.next = temp;
                preNode = temp;
            }
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        temp = (carryFlag != 0) ? new ListNode(carryFlag) : null;
        if (temp != null) {
            temp.next = null;
            preNode.next = temp;
        } else {
            preNode.next = null;
        }
        return result;
    }

    /**
     *  题目14：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     *  你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     *  注：此解决方法有问题，会申请大量内存，优化方向为动态hash表即官方解法。
     *  e.g: 给定 nums = [2, 7, 11, 15], target = 9
     *       因为 nums[0] + nums[1] = 2 + 7 = 9
     *       所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution016(int[] nums, int target) {
        int length = nums.length;
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        // 先找到nums中最小值和最大值
        int min = nums[0], max = nums[0];
        for (int i = 0; i < length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int offset = -min, tableSize = max - min + 1;
        int[] map = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            map[i] = -1;
        }
        for (int i = 0; i < length * 2; i++) {
            if (i < length) {
                // 在map中记录下nums的位置信息
                map[nums[i] + offset] = i;
            } else {
                int index = target - nums[i - length] + offset;
                if (0 <= index && index < tableSize && map[index] != -1 && map[index] != i - length) {
                    return new int[]{i - length, map[index]};
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 题目14 官方解答
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution17(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");

    }

    public static void main(String[] args) {

        /*
        System.out.println("----------solution01-------------");
        // a
        char[] s1 = "a".toCharArray();
        System.out.println("输入: " + String.valueOf(s1)
                + ", 输出: " + String.valueOf(solution01(s1)));
        // aaa
        char[] s2 = "aaa".toCharArray();
        System.out.println("输入: " + String.valueOf(s2)
                + ", 输出: " + String.valueOf(solution01(s2)));
        // abc
        char[] s3 = "abc".toCharArray();
        System.out.println("输入: " + String.valueOf(s3)
                + ", 输出: " + String.valueOf(solution01(s3)));
        // abcdabc
        char[] s4 = "abcdabc".toCharArray();
        System.out.println("输入: " + String.valueOf(s4)
                + ", 输出: " + String.valueOf(solution01(s4)));

        System.out.println("----------solution02-------------");
        // a
        System.out.println("输入: " + String.valueOf(s1)
                + ", 输出: " + String.valueOf(solution02(s1)));
        // aaa
        System.out.println("输入: " + String.valueOf(s2)
                + ", 输出: " + String.valueOf(solution02(s2)));
        // abc
        System.out.println("输入: " + String.valueOf(s3)
                + ", 输出: " + String.valueOf(solution02(s3)));
        // abcdabc
        System.out.println("输入: " + String.valueOf(s4)
                + ", 输出: " + String.valueOf(solution02(s4)));

        System.out.println("----------solution03-------------");
        //"aab"
        String s5 = "aab";
        System.out.println("输入: " + s5
                + ", 输出: " + solution03(s5));


        System.out.println("----------solution04-------------");
        // 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
        String s6 = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        System.out.println("输入: " + String.valueOf(s6)
                + ", 输出: " + String.valueOf(solution04(s6)));


        System.out.println("----------solution05-------------");
        //
        String s7 = "LEETCODEISHIRING";
        System.out.println("输入: " + String.valueOf(s7)
                + ", 输出: " + String.valueOf(solution05(s7, 3)));

        System.out.println("输入: " + String.valueOf(s7)
                + ", 输出: " + String.valueOf(solution05(s7, 4)));


        System.out.println("----------solution06-------------");

        int i1 = -687;
        System.out.println("输入: " + i1
                + ", 输出: " + String.valueOf(solution06(i1)));
        int i2 = -120;
        System.out.println("输入: " + i2
                + ", 输出: " + String.valueOf(solution06(i2)));
        int i3 = 1534236469;
        System.out.println("输入: " + i3
                + ", 输出: " + String.valueOf(solution06(i3)));


        System.out.println("----------solution06-------------");
        String s8 = "-91283472332";
        System.out.println("输入: " + s8
                + ", 输出: " + String.valueOf(solution07(s8)));
        String s9 = "4193 with words";
        System.out.println("输入: " + s8
                + ", 输出: " + String.valueOf(solution07(s9)));

        System.out.println("----------solution06-------------");
        Integer i4 = 3;
        System.out.println("输入: " + i4
                + ", 输出: " + String.valueOf(solution11(i4)));
        Integer i5 = 1994;
        System.out.println("输入: " + i5
                + ", 输出: " + String.valueOf(solution11(i5)));

        System.out.println("----------solution12-------------");
        String[] s10 = {"flower", "flow", "flight"};
        System.out.println("输入: " + s10.toString()
                + ", 输出: " + String.valueOf(solution12(s10)));
        */
        System.out.println("----------solution12-------------");
        int[] i6 = {-13,5,13,12,-2,-11,-1,12,-3,0,-3,-7,-7,-5,-3,-15,-2,14,14,13,6,-11,-11,5,-15,-14,5,-5,-2,0,3,-8,-10,-7,11,-5,-10,-5,-7,-6,2,5,3,2,7,7,3,-10,-2,2,-12,-11,-1,14,10,-9,-15,-8,-7,-9,7,3,-2,5,11,-13,-15,8,-3,-7,-12,7,5,-2,-6,-3,-10,4,2,-5,14,-3,-1,-10,-3,-14,-4,-3,-7,-4,3,8,14,9,-2,10,11,-10,-4,-15,-9,-1,-1,3,4,1,8,1};
        System.out.println("输入: " + i6.toString()
                + ", 输出: " + String.valueOf(solution13(i6)));
    }

}
