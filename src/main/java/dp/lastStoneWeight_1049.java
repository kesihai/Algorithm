package dp;

import java.util.Arrays;

/**
 * leetcode 1049,
 * 有一堆石头，每次挑选两个，小的消失，大的值为减去小的, 问最后可能剩下来的最小值.
 *
 * 思路: 我们不妨将石头分为两边, 首先放一个石头到一边, 这个时候需要挑一个石头放在另
 * 一个边， 将他们碰撞后, 结果就是一边还有石头(尽管可能都没有), 接下来要干的事情
 * 就是挑选一个石头放在剩余石头的对面, 重复这个操作，直到最后一个石头.
 *
 * 这个时候我们可以发现其实剩下来的就是两边石头差的绝对值, 所以答案就是找不超过sum/2
 * 的前提下，石头的和最多是多少.
 */
public class lastStoneWeight_1049 {
  class Solution {
    public int lastStoneWeightII(int[] stones) {
      int sum = Arrays.stream(stones).sum();
      boolean[] dp = new boolean[sum / 2 + 1];
      dp[0] = true;
      for (int s : stones) {
        for (int v = dp.length - 1; v >= s; v--) {
          if (dp[v - s]) {
            dp[v] = true;
          }
        }
      }
      for (int i = dp.length - 1; i >= 0; i--) {
        if (dp[i]) {
          return sum - i * 2;
        }
      }
      return 0;
    }
  }
}
