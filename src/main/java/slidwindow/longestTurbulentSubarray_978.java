package slidwindow;

public class longestTurbulentSubarray_978 {
  class Solution {
    public int maxTurbulenceSize(int[] A) {
      if (A.length < 2) {
        return A.length;
      }
      int[][] dp = new int[A.length][2];
      int ans = 1;
      dp[0][0] = dp[0][1] = 1;
      for (int i = 1; i < A.length; i++) {
        dp[i][0] = A[i] < A[i - 1] ? dp[i - 1][1] + 1 : 1;
        dp[i][1] = A[i] > A[i - 1] ? dp[i - 1][0] + 1 : 1;
        ans = Math.max(ans, dp[i][0]);
        ans = Math.max(ans, dp[i][1]);
      }
      return ans;
    }
  }
}
