package com.jiasxin.leetcode.type.array.no00004;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: jiasx
 * @date: 2021年12月06日23:31:33
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 使用set集合
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }


    /**
     * 使用set集合
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {//因为集合set中不能有重复的元素，如果有重复的元素添加，就会添加失败
                return true;
            }
        }
        return false;
    }


    /**
     * 使用set集合
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //如果有重复的，set中会覆盖，导致size减小，如果没有重复的，set的大小等于nums的长度
        return set.size() != nums.length;
    }

    /**
     * 暴力求解
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 先对数组进行排序，然后再比较
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate5(int[] nums) {
        Arrays.sort(nums);
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] == nums[right - 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0, 1, 1, 3, 4};
        System.out.println(solution.containsDuplicate(nums));
    }

}
