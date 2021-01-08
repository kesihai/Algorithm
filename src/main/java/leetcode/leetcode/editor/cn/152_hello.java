//package leetcode.leetcode.editor.cn;
////给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
////
////
////
//// 示例 1:
////
//// 输入: [2,3,-2,4]
////输出: 6
////解释: 子数组 [2,3] 有最大乘积 6。
////
////
//// 示例 2:
////
//// 输入: [-2,0,-1]
////输出: 0
////解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
//// Related Topics 数组 动态规划
//// 👍 886 👎 0
//
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//  public int maxProduct(int[] nums) {
//    if (nums.length == 0) {
//      return 0;
//    }
//    int ans = nums[0];
//    // 负数
//    int le = nums[0];
//    // 正数
//    int ri = nums[0];
//
//    for (int i = 1; i < nums.length; i++) {
//      if (nums[i] == 0) {
//        le = ri = 0;
//      } else if (nums[i] > 0) {
//        ri = ri * nums[i];
//        le = le * nums[i];
//      } else {
//        int tmpRi = le * nums[i];
//        int tmpLe = ri * nums[i];
//        ri = tmpRi;
//        le = tmpLe;
//      }
//      le = Math.min(le, nums[i]);
//      ri = Math.max(ri, nums[i]);
//      ans = Math.max(ans, ri);
//    }
//    return ans;
//  }
//}
////leetcode submit region end(Prohibit modification and deletion)
