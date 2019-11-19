package dp;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
  private HashSet<Integer> mustVisitDays;
  private int[] dp;
  private int[] costsDays = {1, 7, 30};
  public int mincostTickets(int[] days, int[] costs) {
    Arrays.sort(days);
    dp = new int[days[days.length - 1] + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    mustVisitDays = new HashSet<>();
    for (int v : days) {
      mustVisitDays.add(v);
    }
    return dfs(days[days.length - 1], costs);
  }

  private int dfs(int day, int[] cost) {
    if (day < 0) {
      return 0;
    }
    if (!mustVisitDays.contains(day)) {
      return dp[day] = dfs(day - 1, cost);
    }
    if (dp[day] != Integer.MAX_VALUE) {
      return dp[day];
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < cost.length; i++) {
      int temp = cost[i] + dfs(day - costsDays[i], cost);
      if (temp < ans) {
        ans = temp;
      }
    }
    return dp[day] = ans;
  }
}

/**
 * leetcode 983.
 */
public class minCostTraveling_983 {
  public static void main(String[] args) {
    System.out.println(new Solution().mincostTickets(new int[]{}, new int[]{}));
  }
}
