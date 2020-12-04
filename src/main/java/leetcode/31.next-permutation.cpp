/*
 * @lc app=leetcode id=31 lang=cpp
 *
 * [31] Next Permutation
 */

// @lc code=start
class Solution {
public:
  void nextPermutation(vector<int> &nums) {
    for (int i = nums.size() - 2; i >= 0; i--) {
      if (arrange(nums, i)) {
        return;
      }
    }
    sort(nums.begin(), nums.end());
  }

  bool arrange(vector<int> &nums, int pos) {
    int p = -1;
    for (int i = pos + 1; i < nums.size(); i++) {
      if (nums[i] > nums[pos]) {
        if (p == -1 || nums[i] < nums[p]) {
          p = i;
        }
      }
    }
    if (p == -1) return false;
    swap(nums[p], nums[pos]);
    sort(nums.begin() + pos + 1, nums.end());
    return true;
  }
};
// @lc code=end

