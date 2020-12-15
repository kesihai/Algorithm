/*
 * @lc app=leetcode.cn id=79 lang=cpp
 *
 * [79] 单词搜索
 */

// @lc code=start
const int N = 205;
int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
class Solution {
public:
    int vis[N][N];
    vector<vector<char>> board;
    string word;
    bool exist(vector<vector<char>>& board, string word) {
      if (word.length() == 0) {
          return true;
      }
      this -> board = board;
      this -> word = word;
      memset(vis, 0, sizeof(vis));
      for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
          if (board[i][j] == word[0]) {
            if (dfs(i, j, 0)) return true;
          }
        }
      }
      return false;
    }

    bool dfs(int x, int y, int k) {
      if (board[x][y] != word[k]) {
        return false;
      }
      if (k == word.length() - 1) {
        return true;
      }
      vis[x][y] = 1;
      for (int i = 0; i < 4; i++) {
        int xx = x + dir[i][0];
        int yy = y + dir[i][1];
        if (xx < 0 || xx >= board.size() || yy < 0 || yy >= board[0].size()) {
          continue;
        }
        if (vis[xx][yy]) continue;
        if (dfs(xx, yy, k + 1)) {
          return true;
        }
      }
      vis[x][y] = 0;
      return false;
    }
};
// @lc code=end

