package dp;

import java.util.Arrays;

/**
 * leetcode 698 dfs.
 */
class PartitionkEqualSubsets {
  class Solution {
    private boolean[] visit;

    public boolean canPartitionKSubsets(int[] nums, int k) {
      int sum = Arrays.stream(nums).sum();
      if (sum % k != 0) {
        return false;
      }
      int target = sum / k;
      visit = new boolean[nums.length];
      return dfs(0, nums, target, 0, k, 0);
    }

    private boolean dfs(int pos, int[] nums, int targetSum, int curSum, int k,
        int curK) {
      if (curK == k) {
        return true;
      }
      if (curSum > targetSum) {
        return false;
      }
      if (curSum == targetSum) {
        return dfs(0, nums, targetSum, 0, k, curK + 1);
      }
      for (int i = pos; i < nums.length; i++) {
        if (visit[i]) {
          continue;
        }
        visit[i] = true;
        if (dfs(i + 1, nums, targetSum, curSum + nums[i], k, curK)) {
          return true;
        }
        visit[i] = false;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Solution s = new PartitionkEqualSubsets().new Solution();
    System.out.println(s.canPartitionKSubsets(new int[]{
        10, 10, 10, 7, 7, 7, 7, 7, 7, 6, 6, 6}, 3));
  }
}
