package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 最长的等差数列.
 */
public class LongestArithmeticSeq_1027 {

  class Solution {
    public int longestArithSeqLength(int[] A) {
      Map<Integer, Integer> dp[] = new HashMap[A.length];
      for (int i = 0; i < A.length; i++) {
        dp[i] = new HashMap<>();
      }
      int ans = 0;
      for (int i = 0; i < A.length; i++) {
        for (int j = i + 1; j < A.length; j++) {
          int diff = A[j] - A[i];
          dp[j].put(diff, Math.max(dp[j].getOrDefault(diff, 1),
              dp[i].getOrDefault(diff, 1) + 1));
          ans = Math.max(ans, dp[j].get(diff));
        }
      }
      return ans;
    }
  }
}
