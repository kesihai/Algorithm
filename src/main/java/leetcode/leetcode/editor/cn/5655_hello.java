//package leetcode.leetcode.editor.cn;
////给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
////
//// 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
////
////
////
//// 示例 1：
////
////
////
////
////输入：matrix = [[0,0,1],[1,1,1],[1,0,1]]
////输出：4
////解释：你可以按照上图方式重新排列矩阵的每一列。
////最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
////
////
//// 示例 2：
////
////
////
////
////输入：matrix = [[1,0,1,0,1]]
////输出：3
////解释：你可以按照上图方式重新排列矩阵的每一列。
////最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
////
////
//// 示例 3：
////
////
////输入：matrix = [[1,1,0],[1,0,1]]
////输出：2
////解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。
////
//// 示例 4：
////
////
////输入：matrix = [[0,0],[0,0]]
////输出：0
////解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
////
////
////
//// 提示：
////
////
//// m == matrix.length
//// n == matrix[i].length
//// 1 <= m * n <= 105
//// matrix[i][j] 要么是 0 ，要么是 1 。
////
//// Related Topics 贪心算法 排序
//// 👍 18 👎 0
//
///*
//一个有意思的题：
// 思路：生成这样一个数组： sum[i][j]: 代表 第i行，j这个位置1高度.
//     对于每一行，将sum[i] 排序，那么变成了一维数组求最大面积.
//     取最大值即可.
// */
//
//import java.util.Arrays;
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//  int[] sum;
//  public int largestSubmatrix(int[][] matrix) {
//    for (int i = 1; i < matrix.length; i++) {
//      for (int j = 0; j < matrix[i].length; j++) {
//        if (matrix[i][j] == 1) {
//          matrix[i][j] += matrix[i - 1][j];
//        }
//      }
//    }
//    int ans = 0;
//    for (int i = 0; i < matrix.length; i++) {
//      Arrays.sort(matrix[i]);
//      for (int j = 0; j < matrix[i].length; j++) {
//        ans = Math.max(ans, matrix[i][j] * (matrix[i].length - j));
//      }
//    }
//    return ans;
//  }
//}
////leetcode submit region end(Prohibit modification and deletion)
