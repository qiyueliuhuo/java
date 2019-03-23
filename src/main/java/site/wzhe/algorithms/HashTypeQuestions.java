package site.wzhe.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2019/3/1.
 */
public class HashTypeQuestions {

    /**
     *  题目1：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     *  你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     *  注：此解决方法有问题，会申请大量内存，优化方向为动态hash表即官方解法。
     *  e.g: 给定 nums = [2, 7, 11, 15], target = 9
     *       因为 nums[0] + nums[1] = 2 + 7 = 9
     *       所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution01(int[] nums, int target) {
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
     * 题目1 官方解答
     * @param nums
     * @param target
     * @return
     */
    private static int[] solution02(int[] nums, int target) {
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
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        int[] result = solution01(nums, target);
    }
}
