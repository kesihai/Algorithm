package dp;

import java.util.Arrays;

public class bookShalve_1105 {
  class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
      int[] dp = new int[books.length + 1];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      for (int i = 0; i < books.length; i++) {
          int maxValue = 0;
          int width = shelf_width;
          for (int j = i + 1;  j <= books.length; j++) {
            int bookIndex = j - 1;
            width -= books[bookIndex][0];
            maxValue = Math.max(maxValue, books[bookIndex][1]);
            if (width < 0) {
              break;
            }
            dp[j] = Math.min(dp[j], dp[i] + maxValue);
          }
      }
      return dp[books.length];
    }
  }
}
