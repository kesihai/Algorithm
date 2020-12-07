/*
 * @lc app=leetcode id=32 lang=cpp
 *
 * [32] Longest Valid Parentheses
 */

// @lc code=start
class Solution {
public:
    int longestValidParentheses(string s) {
      stack<int> ss;
      int len = s.length();
      for (int i = 0; i < len; i++) {
        if (s[i] == '(') {
          ss.push(i);
        } else {
          if (ss.empty() || s[ss.top()] != '(') {
            ss.push(i);
          } else {
            ss.pop();
          }
        }
      }
      int pre = len;
      int ans = 0;
      while (!ss.empty()) {
        ans = max(ans, pre - ss.top() - 1);
        pre = ss.top();
        ss.pop();
      }
      ans = max(ans, pre - 0);
      return ans;
    }
};
// @lc code=end

