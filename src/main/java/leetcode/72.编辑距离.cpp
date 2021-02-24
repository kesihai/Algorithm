/*
 * @lc app=leetcode.cn id=72 lang=cpp
 *
 * [72] 编辑距离
 */

// @lc code=start
const int N = 1005;
class Solution {
public:
    int minDistance(string word1, string word2) {
      int len1 = word1.length();
      int len2 = word2.length();
      vector<vector<int>> dp(len1 + 1, vector<int>(len2 + 1, 0));
      for (int i = 1; i <= len1; i++) {
        dp[i][0] = i;
      }
      for (int i = 0; i <= len2; i++) {
        dp[0][i] = i;
      }

      for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
          // 先删除i个字符, 然后插入j个字符
          dp[i][j] = i + j;

          // 插入字符
          dp[i][j] = min(dp[i][j], dp[i][j - 1] + 1);
          // 删除字符
          dp[i][j] = min(dp[i][j], dp[i - 1][j] + 1);
          // 替换字符
          dp[i][j] = min(dp[i][j], dp[i - 1][ j - 1] + 1);
          if (word1[i - 1] == word2[j - 1]) {
            dp[i][j] = min(dp[i][j], dp[i - 1][j - 1]);
          }
        }
      }
      return dp[len1][len2];
    }
};
// @lc code=end

