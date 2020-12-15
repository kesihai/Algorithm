/*
 * @lc app=leetcode.cn id=1688 lang=cpp
 *
 * [1688] 比赛中的配对次数
 */

// @lc code=start
class Solution {
public:
    int numberOfMatches(int n) {
      int ans = 0;
      while (n != 1) {
        ans += n / 2;
        n = (n + 1) / 2;
      }
      return ans;
    }
};
// @lc code=end

