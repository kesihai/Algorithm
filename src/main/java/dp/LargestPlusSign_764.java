package dp;

/**
 * leetcode 764.
 */
public class LargestPlusSign_764 {

  class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
      int[][] temp = new int[n][n];
      for (int[] t : mines) {
        temp[t[0]][t[1]] = 1;
      }
      mines = temp;
      if (mines.length == 0 || mines[0].length == 0) {
        return 0;
      }
      // left, down,  right, up
      int[][][] dp = new int[4][mines.length][mines[0].length];
      for (int i = 0; i < mines.length; i++) {
        for (int j = 0; j < mines[0].length; j++) {
          if (mines[i][j] == 1) {
            dp[0][i][j] = dp[1][i][j] = 0;
          } else {
            dp[0][i][j] = (j > 0 ? dp[0][i][j - 1] : 0) + 1;
            dp[1][i][j] = (i > 0 ? dp[1][i - 1][j] : 0) + 1;
          }
        }
      }

      for (int i = mines.length - 1; i >= 0; i--) {
        for (int j = mines[0].length - 1; j >= 0; j--) {
          if (mines[i][j] == 1) {
            dp[2][i][j] = dp[3][i][j] = 0;
          } else {
            dp[2][i][j] = (j < mines[0].length - 1 ? dp[2][i][j + 1] : 0) + 1;
            dp[3][i][j] = (i < mines.length - 1 ? dp[3][i + 1][j] : 0) + 1;
          }
        }
      }
      int ans = 0;
      for (int i = 0; i < mines.length; i++) {
        for (int j = 0; j < mines[0].length; j++) {
          int tmp = mines.length;
          for (int k = 0; k < 4; k++) {
            tmp = Math.min(tmp, dp[k][i][j]);
          }
          ans = Math.max(ans, tmp);
        }
      }
      return ans;
    }
  }
}
