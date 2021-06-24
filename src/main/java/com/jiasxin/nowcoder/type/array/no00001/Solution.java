package com.jiasxin.nowcoder.type.array.no00001;

public class Solution {

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