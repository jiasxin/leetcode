package com.jiasxin.leetcode.type.array.no00003;

import java.util.Arrays;

/**
 * @author: jiasx
 * @date: 2021年12月06日22:27:17
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 多次反转
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);//先反转全部的元素
        reverse(nums, 0, k - 1);//在反转前k个元素
        reverse(nums, k, length - 1);//接着反转剩余的
    }

    //把数组中从[start，end]之间的元素两两交换,也就是反转
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 临时数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int temp[] = new int[length];
        //把原数组值放到一个临时数组中，
        for (int i = 0; i < length; i++) {
            temp[i] = nums[i];
        }
        //然后在把临时数组的值重新放到原数组，并且往右移动k位
        for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = temp[i];
        }
    }


    /**
     * 环形旋转
     *
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int hold = nums[0];
        int index = 0;
        int length = nums.length;
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            index = (index + k) % length;
            if (visited[index]) {
                //如果访问过，再次访问的话，会出现原地打转的现象，不能再访问当前元素了，我们直接从他的下一个元素开始
                index = (index + 1) % length;
                hold = nums[index];
                i--;
            } else {
                //把当前值保存在下一个位置，保存之前要把下一个位置的值给记录下来
                visited[index] = true;
                int temp = nums[index];
                nums[index] = hold;
                hold = temp;
            }
        }
    }


    /**
     * 环形旋转
     *
     * @param nums
     * @param k
     */
    public void rotate4(int[] nums, int k) {
        k = k % nums.length;
        int[] rightpart = Arrays.copyOfRange(nums, nums.length - k, nums.length);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(rightpart, 0, nums, 0, k);
    }

    /**
     * 环形旋转
     *
     * @param nums
     * @param k
     */
    public void rotate5(int[] nums, int k) {
        k = k % nums.length;
        int temp[] = new int[k];
        int j = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            temp[j++] = nums[i];
        }
        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0, 1, 2, 3, 4};
        solution.rotate(nums, 2);
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

}
