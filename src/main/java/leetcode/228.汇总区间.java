//import java.util.List;
//
///*
// * @lc app=leetcode.cn id=228 lang=java
// *
// * [228] 汇总区间
// */
//
//// @lc code=start
//class Solution {
//  public List<String> summaryRanges(int[] nums) {
//    List<String> ans = new LinkedList<>();
//    Arrays.sort(nums);
//    for (int i = 0; i < nums.length; i++) {
//      int j = i;
//      while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) j++;
//      String str = i == j ? "" + nums[i] : String.format("%s->%s", nums[i], nums[j]);
//      i = j;
//      ans.add(str);
//    }
//    return ans;
//  }
//}
//// @lc code=end
//
