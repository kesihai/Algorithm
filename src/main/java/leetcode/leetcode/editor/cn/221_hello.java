package leetcode.leetcode.editor.cn;
//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix =
// [["1","0","1","0","0"],
// ["1","0","1","1","1"],
// ["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 动态规划 
// 👍 642 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  int[][] a;
  int []b;
  public int maximalSquare(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    a = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        a[i][j] = matrix[i][j] - '0';
      }
    }
    for (int i = 0; i < a.length; i++) {
      for (int j = 1; j < a[i].length; j++) {
        a[i][j] += a[i][j - 1];
      }
    }
    b = new int[a.length];
    int ans = 0;
    int len = a[0].length;
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
        ans = Math.max(ans, handle(i, j));
      }
    }
    return ans;
  }

  private int handle(int from, int to) {
    for (int i = 0; i < a.length; i++) {
      b[i] = from == 0 ? a[i][to]  : a[i][to] - a[i][from - 1];
      if (b[i] != to - from + 1) {
        b[i] = 0;
      }
    }
    int tmp = 0;
    int ans = 0;
    for (int i = 0; i < b.length; i++) {
      if (b[i] != 0) {
        tmp += b[i];
      } else {
        tmp = 0;
      }
      if (tmp == (to - from + 1) * (to - from + 1)) {
        ans = Math.max(ans, tmp);
      }
    }
    return ans;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
