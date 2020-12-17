/*
 * @lc app=leetcode.cn id=84 lang=cpp
 *
 * [84] 柱状图中最大的矩形
 */

// @lc code=start
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        vector<int> le(heights.size(), 0);
        vector<int> ri(heights.size(), 0);
        stack<int> s;
        for (int i = 0; i < heights.size(); i++) {
          while (!s.empty() && heights[s.top()] >= heights[i]) s.pop();
          le[i] = s.empty() ? -1 : s.top();
          s.push(i);
        }
        while (!s.empty()) {
          s.pop();
        }
        for (int i = heights.size() - 1; i >= 0; i--) {
          while (!s.empty() && heights[i] <= heights[s.top()]) s.pop();
          ri[i] = s.empty() ? heights.size() : s.top();
          s.push(i);
        }
        int ans = 0;
        for (int i = 0; i < heights.size(); i++) {
          int tmp = (ri[i] - le[i] - 1) * heights[i];
          if (tmp > ans) {
            // printf("hi %d %d %d %d\n", i, heights[i], le[i], ri[i]);
            ans = tmp;
          }
        }
        return ans;
    }
};
// @lc code=end

