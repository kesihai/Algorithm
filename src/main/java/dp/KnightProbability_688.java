package dp;

import java.util.Arrays;

/**
 * leetcode 688.
 */
public class KnightProbability_688 {
  class Solution {
    private static final double P = 1.0 / 8;
    private final int[][] dirs = {
        {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
        {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    private double[][][] dp;
    private int n;

    public double knightProbability(int n, int k, int r, int c) {
      dp = new double[n][n][k];
      this.n = n;
      for (int i = 0; i < dp.length; i++) {
        for (int j = 0; j < dp[i].length; j++) {
          Arrays.fill(dp[i][j], -1);
        }
      }
      return dfs(r, c, 0, k);
    }

    private double dfs(int r, int c, int curK, int k) {
      if (isOut(r, c)) {
        return 0;
      }
      if (curK == k) {
        return 1;
      }
      if (dp[r][c][curK] > -0.5) {
        return dp[r][c][curK];
      }
      double ans = 0;
      for (int i = 0; i < dirs.length; i++) {
        int xx = r + dirs[i][0];
        int yy = c + dirs[i][1];
        ans += P * dfs(xx, yy, curK + 1, k);
      }
      return dp[r][c][curK] = ans;
    }

    private boolean isOut(int r, int c) {
      return !(r >= 0 && r < n && c >= 0 && c < n);
    }
  }
}
