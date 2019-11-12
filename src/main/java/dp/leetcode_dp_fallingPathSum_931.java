package dp;

import java.util.Arrays;

class Solution {
  int[][] dp;

  public int minFallingPathSum(int[][] a) {
    dp = new int[a.length][a[0].length];
    for (int[] b : dp) {
      Arrays.fill(b, Integer.MAX_VALUE);
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < a[0].length; i++) {
      ans = Math.min(ans, dfs(a, 0, i));
    }
    return ans;
  }

  private int dfs(int[][] a, int x, int y) {
    if (dp[x][y] != Integer.MAX_VALUE) {
      return dp[x][y];
    }
    if (x == a.length - 1) {
      return a[x][y];
    }
    int value = Integer.MAX_VALUE;
    for (int i = -1; i <= 1; i++) {
      int le = y + i;
      if (le < 0 || le >= a[0].length) {
        continue;
      }
      value = Math.min(value, dfs(a, x + 1, le));
    }
    return dp[x][y] = value + a[x][y];
  }
}

/**
 * leetcode dp 记忆化搜索.
 */
public class leetcode_dp_fallingPathSum_931 {
}
