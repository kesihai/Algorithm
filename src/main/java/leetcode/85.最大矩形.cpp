/*
 * @lc app=leetcode.cn id=85 lang=cpp
 *
 * [85] 最大矩形
 */

// @lc code=start
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
      if (matrix.size() == 0 || matrix[0].size() == 0) {
        return 0;
      }
      vector<vector<int>> a(matrix.size(), vector<int>(matrix[0].size(), 0));
      int n = matrix.size();
      int m = matrix[0].size();
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          a[i][j] = matrix[i][j] - '0';
        }
      }
      for (int i = 0; i < n; i++) {
        for (int j = 1; j < m; j++) {
          a[i][j] += a[i][j - 1]; 
        }
      }
      int ans = 0;
      for (int i = 0; i < m; i++) {
        for (int j = i; j < m; j++) {
          vector<int> v;
          for (int k = 0; k < n; k++) {
            v.push_back(i == 0 ? a[k][j] : a[k][j] - a[k][i - 1]);
          }
          for (int k = 0; k < v.size(); k++) {
            v[k] = v[k] == j - i + 1 ? 1 : 0;
          }
          int tmp = 0;
          for (int k = 0; k < v.size(); k++) {
            tmp = v[k] ? tmp + v[k] : 0;
            ans = max(tmp * (j - i + 1), ans);
          }
        }
      }
      return ans;
    }
};
// @lc code=end

