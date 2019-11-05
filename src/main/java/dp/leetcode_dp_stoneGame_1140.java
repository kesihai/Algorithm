package dp;

import java.util.Arrays;

class Solution {
  int[][] dp;
  int[] piles;
  int[] sum;

  public int stoneGameII(int[] piles) {
    dp = new int[piles.length][piles.length];
    this.piles = piles;
    this.sum = new int[piles.length];
    int value = 0;
    for (int i = 0; i < piles.length; i++) {
      value += piles[i];
      sum[i] = value;
    }
    for (int[] a : dp) {
      Arrays.fill(a, -1);
    }
    return dfs(0, 1);
  }

  private int dfs(int pos, int len) {
    if (pos >= piles.length) {
      return 0;
    }
    if (dp[pos][len] != -1) {
      return dp[pos][len];
    }
    int le = Math.min(len * 2, piles.length - pos);
    int ans = 0;
    for (int temp = 1; temp <= le; temp++) {
      int tryValue = getValue(pos, pos + temp - 1) + getValue(pos + temp,
          piles.length - 1) - dfs(pos + temp, Math.max(temp, len));
      if (tryValue > ans) {
        ans = tryValue;
      }
    }
    return dp[pos][len] = ans;
  }

  private int getValue(int le, int ri) {
    if (le == 0) {
      return sum[ri];
    }
    return sum[ri] - sum[le - 1];
  }
}

/**
 * leetcode dp 记忆化搜索.
 */
public class leetcode_dp_stoneGame_1140 {
}
