package leetcode.leetcode.editor.cn;
//给你一个整数数组 nums 和一个目标值 goal 。 
//
// 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum -
// goal) 。 
//
// 返回 abs(sum - goal) 可能的 最小值 。 
//
// 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [5,-7,3,5], goal = 6
//输出：0
//解释：选择整个数组作为选出的子序列，元素和为 6 。
//子序列和与目标值相等，所以绝对差为 0 。
// 
//
// 示例 2： 
//
// 输入：nums = [7,-9,15,-2], goal = -5
//输出：1
//解释：选出子序列 [7,-9,-2] ，元素和为 -4 。
//绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
// 
//
// 示例 3： 
//
// 输入：nums = [1,2,3], goal = -7
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 40 
// -107 <= nums[i] <= 107 
// -109 <= goal <= 109 
// 
// Related Topics 分治算法 
// 👍 15 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int minAbsDifference(int[] nums, int goal) {
    if (nums.length == 0) {
      return Math.abs(goal);
    }
    if (nums.length == 1) {
      return Math.abs(goal - nums[0]);
    }

    Set<Long> set1 = new HashSet<>();
    Set<Long> set2 = new HashSet<>();
    int index = nums.length / 2;
    Long[] s1 = set1.toArray(new Long[0]);
    Long[] s2 = set2.toArray(new Long[0]);
    for (Long item : s1) {
      Arrays.binarySearch(s2, goal - item);
    }
  }

  private void dfs(int[] nums, int cur, int pos, int e, Set<Long> set) {
    if (pos > e) {
      return;
    }
    set.add(1L * cur + nums[pos]);
    dfs(nums, cur + nums[pos], pos + 1, e, set);
    dfs(nums, cur, pos + 1, e, set);
  }
}
//leetcode submit region end(Prohibit modification and deletion)
