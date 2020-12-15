/*
 * @lc app=leetcode.cn id=1690 lang=cpp
 *
 * [1690] 石子游戏 VII
 */

// @lc code=start
class Solution {
public:
    int dp[1005][1005];
    int vis[1005][2005];
    int sum[1005];
    vector<int> stones;
    int stoneGameVII(vector<int>& stones) {
      if (stones.size() == 0) return 0;
      this->stones = stones;
      memset(dp, 0, sizeof(dp));
      memset(vis, 0, sizeof(vis));
      sum[0] = stones[0];
      for (int i = 1; i < stones.size(); i++) {
        sum[i] =sum[i - 1] + stones[i];
      }
      return dfs(0, stones.size() - 1);
    }

    int dfs(int le, int ri) {
      if (le == ri) {
        return 0;
      }
      if (vis[le][ri]) {
        return dp[le][ri];
      }
      int ma = max(getSum(le + 1, ri) - dfs(le + 1, ri), getSum(le, ri - 1) - dfs(le, ri - 1));
      vis[le][ri] = 1;
      return dp[le][ri] = ma;
    }

    int getSum(int le, int ri) {
      return le == 0 ? sum[ri] : sum[ri] - sum[le - 1];
    }
};
// @lc code=end

