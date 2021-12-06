package com.jiasxin.leetcode.type.array.no00002;

/**
 * @author: jiasx
 * @date: 2021年12月06日22:08:48
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 动态规划解决
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        //初始条件
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for (int i = 1; i < length; i++) {
            //递推公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候，利润才会最大，
        //只需要返回dp[length - 1][0]即可
        return dp[length - 1][0];
    }

    /**
     * 动态规划解决-代码优化
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int length = prices.length;
        //初始条件
        int hold = -prices[0];//持有股票
        int noHold = 0;//没持有股票
        for (int i = 1; i < length; i++) {
            //递推公式转化的
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候利润才会最大，所以这里返回的是noHold
        return noHold;
    }

    /**
     * 贪心算法解决
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int total = 0, index = 0, length = prices.length;
        while (index < length) {
            //如果股票下跌就一直找，直到找到股票开始上涨为止
            while (index < length - 1 && prices[index] >= prices[index + 1])
                index++;
            //股票上涨开始的值，也就是这段时间上涨的最小值
            int min = prices[index];
            //一直找到股票上涨的最大值为止
            while (index < length - 1 && prices[index] <= prices[index + 1])
                index++;
            //计算这段上涨时间的差值，然后累加
            total += prices[index++] - min;
        }
        return total;
    }

    /**
     * 贪心算法解决
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //原数组中如果后一个减去前一个是正数，说明是上涨的，我们就要累加，否则就不累加
            total += Math.max(prices[i + 1] - prices[i], 0);
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = solution.maxProfit4(nums);
        System.out.println(i);
    }

}
