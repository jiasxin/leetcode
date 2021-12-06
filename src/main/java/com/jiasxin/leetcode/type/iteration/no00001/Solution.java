package com.jiasxin.leetcode.type.iteration.no00001;

/**
 * 菲波那切数列
 */
public class Solution {

    /**
     * 菲波那切数列
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int fibonacci = solution.Fibonacci(5);
        System.out.println(fibonacci);
    }

}