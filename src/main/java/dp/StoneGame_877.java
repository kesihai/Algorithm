package dp;

import java.util.Arrays;

class Solution {
  int[] sum;

  public boolean stoneGame(int[] piles) {
    int[][] dp = new int[piles.length][piles.length];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    sum = new int[piles.length];
    for (int i = 0; i < piles.length; i++) {
      sum[i] = piles[i] + (i == 0 ? 0 : sum[i - 1]);
    }
    return dfs(0, piles.length - 1, piles, dp) * 2 > sum[piles.length - 1];
  }

  private int dfs(int le, int ri, int[] piles, int[][] dp) {
    if (le > ri) {
      return 0;
    }
    if (le == ri) {
      return piles[le];
    }
    if (dp[le][ri] != -1) {
      return dp[le][ri];
    }
    int values = sum[ri] - (le == 0 ? 0 : sum[le - 1]);
    int leSum = piles[le] + values - dfs(le + 1, ri, piles, dp);
    int riSum = piles[ri] + values - dfs(le, ri - 1, piles, dp);
    return dp[le][ri] = Math.max(leSum, riSum);
  }
}

/**
 * Leetcode stone game.
 * https://leetcode.com/problems/stone-game/
 */
public class StoneGame_877 {
}
