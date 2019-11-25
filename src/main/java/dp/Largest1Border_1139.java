package dp;

/**
 * leetcode 1139.
 */
public class Largest1Border_1139 {

  class Solution {
    int[][] h;
    int[][] v;

    public int largest1BorderedSquare(int[][] grid) {
      if (grid.length == 0 || grid[0].length == 0) {
        return 0;
      }
      h = new int[grid.length][grid[0].length];
      v = new int[grid.length][grid[0].length];
      for (int i = 0; i < h.length; i++) {
        for (int j = 0; j < h[i].length; j++) {
          if (grid[i][j] == 0) {
            h[i][j] = v[i][j] = 0;
          } else {
            h[i][j] = (j > 0 ? h[i][j - 1] : 0) + 1;
            v[i][j] = (i > 0 ? v[i - 1][j] : 0) + 1;
          }
        }
      }
      int ans = 0;
      for (int i = grid.length - 1; i >= 0; i--) {
        for (int j = grid[0].length - 1; j >= 0; j--) {
          int len = Math.min(h[i][j], v[i][j]);
          while (len > ans) {
            if (v[i][j - len + 1] >= len && h[i - len + 1][j] >= len) {
              ans = len;
              break;
            }
            len--;
          }
        }
      }
      return ans * ans;
    }
  }
}
