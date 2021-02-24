#include <bits/stdc++.h>
using namespace std;

const int N = 2005;
class Solution {
public:
    int dp[N][N];
    string s;
    string p;
    bool isMatch(string s, string p) {
      this -> s = s;
      this -> p = p;
      memset(dp, -1, sizeof(dp));
      return dfs(0, 0) == 1;
    }

    int dfs(int a, int b) {
      // cout << " pos: " << a << " " << b << endl;
      if (dp[a][b] != -1) {
        return dp[a][b];
      }
      int ans = 0;
      if (a == s.length()) {
        if (b == p.length()) {
          return dp[a][b] = 1;
        }
        for (int i = b; i < p.length(); i++) {
          if (p[i] != '*') {
            return dp[a][b] = 0;
          }
        }
        return dp[a][b] = 1;

      }
      if (b == p.length()) {
        return dp[a][b] = 0;
      }

      if (p[b] == '?') {
        return dp[a][b] = dfs(a + 1, b + 1);
      } else if (p[b] != '*') {
        return dp[a][b] = (s[a] == p[b]) ? dfs(a + 1, b + 1) : 0;
      } else {
        for (int i = a; i <= s.length(); i++) {
          if (dfs(i, b + 1)) {
            return dp[a][b] = 1;
          }
        }
        return dp[a][b] = 0;
      }
    }
};
// @lc code=end

int main() {
  Solution s ;
  string a, b;
  while(cin >> a >> b) {
    cout << a << " " << b << endl;
    cout << s.isMatch(a, b) << endl;
  }
  return 0;
}
