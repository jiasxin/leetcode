package com.jiasxin.leetcode.type.array.no00001;

/**
 * @author: jiasx
 * @date: 2021年12月06日21:33:11
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 双指针解决
     * 使用两个指针，右指针始终往右移动，
     * 如果右指针指向的值等于左指针指向的值，左指针不动。
     * 如果右指针指向的值不等于左指针指向的值，那么左指针往右移一步，然后再把右指针指向的值赋给左指针。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //边界条件判断
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            //如果左指针和右指针指向的值一样，说明有重复的， 这个时候，左指针不动，右指针继续往右移。如果他俩指向的值不一样就把右指针指向的值往前挪
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return left + 1;
    }

    /**
     * 双指针解决
     *
     * @param nums
     * @return
     */
    private int removeDuplicates2(int[] nums) {
        int count = 0;//重复的数字个数
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] == nums[right - 1]) {
                //如果有重复的，count要加1
                count++;
            } else {
                //如果没有重复，后面的就往前挪
                nums[right - count] = nums[right];
            }
        }
        //数组的长度减去重复的个数
        return nums.length - count;
    }

    /**
     * 常规操作，先去除异常场景。把数组是null和数组为空的情况排除。
     * 由于题目要求只能在原数组上删除，且该数组是个排好序的数组。那么我们可以定义一个游标 index，来记录需要替换为后面数据的位置。
     * 由于第一个元素肯定是要保留在数组中的，因此起始，我们将游标指向数组的第二个元素（此位置为可能替换元素的位置），数组下标指向第二个元素。
     * 判断游标指向位置的前一个元素的值和当前获取的数组下标值是否相同。若相同，则游标位置不变，数组下标后移。
     * 若不同，代表需要把新元素替换到游标指向的位置。则替换元素，并且游标指向下一个位置，计数项加一。
     * 按此操作循环整个数组可得到最终结果。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 1;
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[index - 1]) {
                continue;
            }
            nums[index] = nums[i];
            index++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = solution.removeDuplicates(nums);
        System.out.println(i);

    }


}
