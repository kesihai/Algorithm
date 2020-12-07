/*
 * @lc app=leetcode id=37 lang=cpp
 *
 * [37] Sudoku Solver
 */

// @lc code=start
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool yes;
    void solveSudoku(vector<vector<char> >& board) {
      yes = false;
      dfs(board, 0, 0);
    }

    void dfs(vector<vector<char> >& board, int x, int y) {
      if (yes) {
        return;
      }
      if (y == board[0].size()) {
        dfs(board, x + 1, 0);
        return;
      }

      if (x == board.size()) {
        yes = true;
        return;
      }
      if (board[x][y] != '.') {
        dfs(board, x, y + 1);
        return;
      }
      for (int i = 1; i < 10; i++) {
        if (check(board, x, y, i)) {
          board[x][y] = (char)(i + '0');
          dfs(board, x, y + 1);
          if (yes) {
            return;
          }
        }
      }
      board[x][y] = '.';
    }

    bool check(vector<vector<char> >&board, int x, int y, int v) {
      char ch = (char)(v + '0');
      //printf ("ch = %c\n", ch);
      for (int i = 0; i < 9; i++) {
        if (y != i && board[x][i] == ch) {
          return false;
        }
        if (x != i && board[i][y] == ch) {
          return false;
        }
      }
      int xx = x / 3 * 3;
      int yy = y / 3 * 3;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          int tx = xx + i;
          int ty = yy + j;
          if (tx == x && ty == y) continue;
          if (board[tx][ty] == ch) return false;
        }
      }
      return true;
    }
  
};
// @lc code=end

int main() {
  Solution s;
  vector<vector<char> > ss;
  for (int i = 0; i < 9; i++) {
    vector<char> s;
    for (int j = 0; j < 9; j++) {
      s.push_back('.');
    }
    ss.push_back(s);
  }
  s.solveSudoku(ss);
  for (int i = 0; i < ss.size(); i++) {
    for (int j = 0; j < ss[i].size(); j++) {
      printf("%c ", ss[i][j]);
    }
    printf("\n");
  }
}