/*
 * @lc app=leetcode.cn id=76 lang=cpp
 *
 * [76] 最小覆盖子串
 */

// @lc code=start
const int N = 256;
class Solution {
public:
    int target[N];
    int vis[N];
    string minWindow(string s, string t) {
      memset(target, 0, sizeof(target));
      memset(vis, 0, sizeof(vis));
      int len2 = t.length();
      for (int i = 0; i < len2; i++) {
        target[t[i]]++;
      }

      int len1 = s.length();
      for (int i = 0; i < len1; i++) {
        vis[s[i]]++;
      }
      if (!check()) {
        return "";
      }

      // find le, ri;
      int le = 0, ri = len1 - 1;
      int ansLen = len1, ansLe, ansRi;
      while (ri >= le) {
        if (vis[s[ri]] == target[s[ri]]) break;
        vis[s[ri]]--;
        ri--;
      }
      ansLen = ri - le + 1;
      ansLe = 0;
      ansRi = ri;
      // printf("first : %d %d\n", ansLe, ansRi);
      while (ri < len1) {
        while (le <= ri) {
          vis[s[le]]--;
          le++;
          if (vis[s[le-1]] >= target[s[le - 1]]) {
            int tmp = ri - le + 1;
            if (tmp < ansLen) {
              ansLen = tmp;
              ansLe = le;
              ansRi = ri;
            }
          } else {
            break;
          }
        }
        while (ri < len1) {
          ri++;
          if (ri > len1) break;
          vis[s[ri]] ++;
          if (vis[s[le - 1]] >= target[s[le - 1]]) {
            break;
          }
        }
      }
      return s.substr(ansLe, ansLen);
    }

    bool check() {
      for (int i = 0; i < N; i++) {
        if (target[i] > vis[i]) return false;
      }
      return true;
    }
};
// @lc code=end

