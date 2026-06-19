import java.util.*;

public class KnapsackDP {

    public static void main(String[] args) {
        int[] weights = {4, 5, 3, 10, 4, 6, 7, 15};
        int[] values  = {8, 5, 6, 7, 7, 5, 6, 8};
        int W = 24;
        int n = weights.length;

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w],
                            values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Value = " + dp[n][W]);

        System.out.print("Selected Items: ");
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print((char)('A' + i - 1) + " ");
                w -= weights[i - 1];
            }
        }
    }
}
