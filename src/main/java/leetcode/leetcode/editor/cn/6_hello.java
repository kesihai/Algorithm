//package leetcode.leetcode.editor.cn;
////将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
////
//// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
////
////
////P   A   H   N
////A P L S I I G
////Y   I   R
////
//// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
////
//// 请你实现这个将字符串进行指定行数变换的函数：
////
////
////string convert(string s, int numRows);
////
////
////
//// 示例 1：
////
////
////输入：s = "PAYPALISHIRING", numRows = 3
////输出："PAHNAPLSIIGYIR"
////
////示例 2：
////
////
////输入：s = "PAYPALISHIRING", numRows = 4
////输出："PINALSIGYAHRPI"
////解释：
////P     I    N
////A   L S  I G
////Y A   H R
////P     I
////
////
//// 示例 3：
////
////
////输入：s = "A", numRows = 1
////输出："A"
////
////
////
////
//// 提示：
////
////
//// 1 <= s.length <= 1000
//// s 由英文字母（小写和大写）、',' 和 '.' 组成
//// 1 <= numRows <= 1000
////
//// Related Topics 字符串
//// 👍 995 👎 0
//
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//  int[][] dir = new int[][]{{1, 0}, {-1, 1}};
//
//  public String convert(String s, int numRows) {
//    if (numRows == 1) {
//      return s;
//    }
//    char[][] ch = new char[numRows][s.length()];
//    for (int i = 0; i < ch.length; i++) {
//      for (int j = 0; j < ch[i].length; j++) {
//        ch[i][j] = ' ';
//      }
//    }
//    int x = 0, y = 0;
//    int step = 0;
//    int pos = 0;
//    while (pos < s.length()) {
//      ch[x][y] = s.charAt(pos);
//      pos++;
//      int xx = x + dir[step][0];
//      int yy = y + dir[step][1];
//      if (xx < 0 || xx >= numRows) {
//        step ^= 1;
//        xx = x + dir[step][0];
//        yy = y + dir[step][1];
//      }
//      x = xx;
//      y = yy;
//    }
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < ch.length; i++) {
//      for (int j = 0; j < ch[i].length; j++) {
//        if (ch[i][j] != ' ') {
//          sb.append(ch[i][j]);
//        }
//      }
//    }
//    return sb.toString();
//  }
//
//  public static void main(String[] args) {
//    Solution s = new Solution();
//    System.out.println(s.convert("PAYPALISHIRING", 3));
//  }
//}
////leetcode submit region end(Prohibit modification and deletion)
