package site.wzhe.jdbc;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2019/2/20.
 */
class Solution {

        int size = 2048;
        int[] map = new int[size];
        int length = 2047;
        int index;

        public  int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                index = nums[i]&length; //

                if (map[index] != 0) {
                    return new int[] { map[index] - 1, i };
                } else {
                    map[(target - index)&length ] = i + 1;
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }

    public static void main(String[] args) {
        int[] nums = new int[]{-3,4,2047, -2049};
        int target = -2045;
        int[] result = new Solution().twoSum(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
