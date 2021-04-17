package com.jiasxin.category.elementary.no01array;

/**
 * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class BestTimeToBuyAndSellShares2 {

    public static void main(String[] args) {

        int[] nums = new int[]{5,1,4,3,2};
        int res = maxProfit2(nums);
        System.out.println(res);

        nums = new int[]{1,2,3,4,5};
        res = maxProfit2(nums);
        System.out.println(res);

        nums = new int[]{5,4,3,2,1};
        res = maxProfit2(nums);
        System.out.println(res);

    }

    /**
     * 动态规划解决
     * 定义dp[i][0]表示第i+1天交易完之后手里没有股票的最大利润，dp[i][1]表示第i+1天交易完之后手里持有股票的最大利润。
     *
     * 当天交易完之后手里没有股票可能有两种情况：
     * 一种是当天没有进行任何交易，又因为当天手里没有股票，所以当天没有股票的利润只能取前一天手里没有股票的利润。
     * 一种是把当天手里的股票给卖了，既然能卖，说明手里是有股票的，所以这个时候当天没有股票的利润要取前一天手里有股票的利润加上当天股票能卖的价格。
     * 这两种情况我们取利润最大的即可，所以可以得到
     *
     * dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);
     *
     * 当天交易完之后手里持有股票也有两种情况：
     * 一种是当天没有任何交易，又因为当天手里持有股票，所以当天手里持有的股票其实前一天就已经持有了。
     * 一种是当天买入了股票，当天能卖股票，说明前一天手里肯定是没有股票的。
     * 我们取这两者的最大值，所以可以得到
     *
     * dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i]);
     *
     * 动态规划的递推公式有了，那么边界条件是什么，就是第一天
     * 如果买入：dp[0][1]=-prices[0];
     * 如果没买：dp[0][0]=0;
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
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
     * 动态规划解决-优化
     * 上面计算的时候我们看到当天的利润只和前一天有关，没必要使用一个二维数组，只需要使用两个变量，一个记录当天交易完之后手里持有股票的最大利润，一个记录当天交易完之后手里没有股票的最大利润
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
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
        //最后一天肯定是手里没有股票的时候利润才会最大，
        //所以这里返回的是noHold
        return noHold;
    }

    /**
     * 贪心算法解决
     * 如果股票一直上涨，只需要找到股票上涨的最大值和股票开始上涨的最小值，计算他们的差就是这段时间内股票的最大利润。
     * 如果股票下跌就不用计算，最终只需要把所有股票上涨的时间段内的利润累加就是我们所要求的结果。
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
     * 贪心算法解决-优化
     *
     * 贪心算法是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，算法得到的是在某种意义上的局部最优解。
     * 那么这道题使用贪心算法也是最容易解决的，只要是上涨的我们就要计算他们的差值进行累加，不需要再找开始上涨的最小值和最大值。
     *
     * @param prices
     * @return
     */
    public static int maxProfit4(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //原数组中如果后一个减去前一个是正数，说明是上涨的，我们就要累加，否则就不累加
            total += Math.max(prices[i + 1] - prices[i], 0);
        }
        return total;
    }

}
