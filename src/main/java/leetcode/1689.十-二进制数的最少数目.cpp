/*
 * @lc app=leetcode.cn id=1689 lang=cpp
 *
 * [1689] 十-二进制数的最少数目
 */

// @lc code=start
class Solution {
public:
    int minPartitions(string n) {
      int ma = 0;
      for (int i = 0; i < n.length(); i++) {
        ma = max(ma, n[i] - '0');
      }
      return ma;
    }
};
// @lc code=end

