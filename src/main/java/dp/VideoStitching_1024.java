package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1024, 现在有一首音乐的多个片段, 片段之间可能有重合, 求得到前T秒的音乐最少需要多少个片段.
 */
public class VideoStitching_1024 {
  class Solution {
    public int videoStitching(int[][] clips, int T) {
      int[] dp = new int[T + 1];
      Arrays.sort(clips, (a, b) -> a[0] - b[0]);
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      for (int[] clip : clips) {
        for (int le = clip[0]; le < clip[1]; le++) {
          if (le < T && dp[le] != Integer.MAX_VALUE) {
            for (int to = le + 1; to <= clip[1] && to <= T; to++) {
              dp[to] = Math.min(dp[to], dp[le] + 1);
            }
          }
        }
      }
      return dp[T] == Integer.MAX_VALUE ? -1 : dp[T];
    }
  }

  public static void main(String[] args) {
  }
}
