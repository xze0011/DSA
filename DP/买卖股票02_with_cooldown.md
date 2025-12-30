# 309. Best Time to Buy and Sell Stock with Cooldown

```java
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        // dp[i][0] 表示：在第 i 天结束时，
        //                手里【持有一支股票】的情况下，
        //                能获得的【最大利润】
        //
        // dp[i][1] 表示：在第 i 天结束时，
        //                手里【不持有股票】的情况下，
        //                能获得的【最大利润】

        for (int i = 1; i < n; i++) {
            if (i < 2) {
                dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            }

        }
        return dp[n - 1][1];
    }
```
