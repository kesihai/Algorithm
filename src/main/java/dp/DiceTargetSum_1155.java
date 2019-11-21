package dp;

public class DiceTargetSum_1155 {

  class Solution {
    public int mod = 1000000007;
    public int numRollsToTarget(int d, int f, int target) {
      int[][] dp = new int[d + 1][target + 1];
      dp[0][0] = 1;
      for (int i = 1; i <= d; i++) {
        for (int v = target; v >= 1; v--) {
          for (int k = 1; k <=f && v >= k; k++) {
            dp[i][v] = (dp[i][v] + dp[i-1][v - k]) % mod;
          }
        }
      }
      return dp[d][target];
    }
  }

  public static void main(String[] args) {
    DiceTargetSum_1155 sum = new DiceTargetSum_1155();
    System.out.println(sum.new Solution().numRollsToTarget(30, 30, 500));
  }
}
