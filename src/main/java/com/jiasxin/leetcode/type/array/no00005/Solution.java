package com.jiasxin.leetcode.type.array.no00005;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: jiasx
 * @date: 2021年12月06日23:41:23
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 使用异或运算，将所有值进行异或 , 异或运算，相异为真，相同为假，所以 a^a = 0 ;0^a = a
     * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
     * 1^1=0;
     * 1^0=1;
     * 0^1=1;
     * 0^0=0;
     * a^a=0；自己和自己异或等于0
     * a^0=a；任何数字和0异或还等于他自己
     * a^b^c=a^c^b；异或运算具有交换律
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce ^= num;
        }
        return reduce;
    }

    /**
     * 使用异或
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    /**
     * 使用集合Set解决
     * 遍历数组中的元素，然后在一个个添加到集合Set中，如果添加失败，说明以前添加过，就把他给移除掉。
     * 当我们把数组中的所有元素都遍历完的时候，集合Set中只会有一个元素，这个就是我们要求的值。
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                //如果添加失败，说明这个值 在集合Set中存在，我们要 把他给移除掉
                set.remove(num);
            }
        }
        //最终集合Set中只有一个元素，我们直接返回
        return (int) set.toArray()[0];
    }

    /**
     * 先排序后查找
     *
     * @param nums
     * @return
     */
    public int singleNumber4(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if ((i < nums.length - 1) && (nums[i] == nums[i + 1])) {
                i++;
            } else {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 1, 2, 3, 3};
        System.out.println(solution.singleNumber(nums));
    }

}
