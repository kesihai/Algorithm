/*
 * @lc app=leetcode id=18 lang=cpp
 *
 * [18] 4Sum
 */

// @lc code=start
/*
  首选: 计算 2sum 的时候, 就是 排列数组，while(le < ri),
  那么计算 3sum 的时候，就是转化为 3sum, (dfs 的方式)
  那么计算 4sum 的时候，就是转化为 3sum. 复制度为 n^(k-1)
*/
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
      sort(nums.begin(), nums.end());      
      return dfs(nums, 0, 4, target);
    }

    vector<vector<int>> dfs(vector<int>& nums, int pos, int cnt, int target) {
      vector<vector<int>> ans;
      if (pos == nums.size()) {
        return ans;
      }
      if (cnt == 2) {
        int le = pos, ri = nums.size() - 1;
        while (le < ri) {
          if (nums[le] + nums[ri] == target) {
            vector<int> tmp;
            tmp.push_back(nums[le]);
            tmp.push_back(nums[ri]);
            ans.push_back(tmp);
            while (le + 1 < nums.size() && nums[le] == nums[le + 1]) le++;
            le++;
            while (ri - 1 >= 0 && nums[ri] == nums[ri - 1]) ri--;
            ri--;
          } else if (nums[le] + nums[ri] > target) {
            ri--;
          } else {
            le++;
          }
        }
        return ans;
      } else {
        vector<vector<int>> t = dfs(nums, pos + 1, cnt - 1, target - nums[pos]);
        for (int i = 0; i < t.size(); i++) {
          t[i].push_back(nums[pos]);
          ans.push_back(t[i]);
        }
        while (pos + 1 < nums.size() && nums[pos] == nums[pos + 1]) pos++;
        t = dfs(nums, pos + 1, cnt, target);
        for (int i = 0; i < t.size(); i++) {
          ans.push_back(t[i]);
        }
        return ans;
      }
    }
};
// @lc code=end

