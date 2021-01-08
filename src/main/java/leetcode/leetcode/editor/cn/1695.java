//package leetcode.leetcode.editor.cn;
////给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
////
//// 返回 只删除一个 子数组可获得的 最大得分 。
////
//// 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
////
////
////
//// 示例 1：
////
////
////输入：nums = [4,2,4,5,6]
////输出：17
////解释：最优子数组是 [2,4,5,6]
////
////
//// 示例 2：
////
////
////输入：nums = [5,2,1,2,5,2,1,2,5]
////输出：8
////解释：最优子数组是 [5,2,1] 或 [1,2,5]
////
////
////
////
//// 提示：
////
////
//// 1 <= nums.length <= 105
//// 1 <= nums[i] <= 104
////
//// Related Topics 双指针
//// 👍 19 👎 0
//
//
//import java.util.Arrays;
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    private int vis[] = new int[10005];
//    public int maximumUniqueSubarray(int[] nums) {
//        Arrays.fill(vis, 0);
//        int le = 0, ri = -1;
//        int ans = 0, tmp = 0;
//        while (ri < nums.length) {
//            ri++;
//            if (ri >= nums.length) {
//                break;
//            }
//            tmp += nums[ri];
//            vis[nums[ri]]++;
//            while (vis[nums[ri]] > 1) {
//                vis[nums[le]]--;
//                tmp -= nums[le];
//                le++;
//            }
//            ans = Math.max(ans, tmp);
//        }
//        return ans;
//    }
//}
////leetcode submit region end(Prohibit modification and deletion)
