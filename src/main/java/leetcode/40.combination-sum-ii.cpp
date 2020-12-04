/*
 * @lc app=leetcode id=40 lang=cpp
 *
 * [40] Combination Sum II
 */

// @lc code=start
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
      sort(candidates.begin(), candidates.end());
      reverse(candidates.begin(), candidates.end());
      return dfs(candidates, 0, 0, target);
    }

    vector<vector<int>> dfs(vector<int> &nums, int pos, int cur, int target) {
      vector<vector<int>> ans;
      if (cur > target) {
        return ans;
      }
      if (cur == target) {
        vector<int> tmp;
        ans.push_back(tmp);
        return ans;
      }
      for (int i = pos ; i < nums.size(); i++) {
        vector<vector<int>> tmp = dfs(nums, i + 1, cur + nums[i], target);
        for (int j = 0; j < tmp.size(); j++) {
          tmp[j].push_back(nums[i]);
          ans.push_back(tmp[j]);
        }
        while (i + 1 < nums.size() && nums[i] == nums[i + 1]) i++;
      }
      return ans;
    }
};
// @lc code=end

