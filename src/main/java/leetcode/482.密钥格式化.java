///*
// * @lc app=leetcode.cn id=482 lang=java
// *
// * [482] 密钥格式化
// */
//
//// @lc code=start
//class Solution {
//  public String licenseKeyFormatting(String S, int K) {
//    S = S.toUpperCase();
//    int num = 0;
//    int len = S.length();
//    for (int i = 0; i < len; i++) {
//      if (S.charAt(i) != '-') {
//        num++;
//      }
//    }
//    int index = num % K;
//    index  *= -1;
//    StringBuilder sb = new StringBuilder();
//    char ch;
//    for (int i = 0; i < len; i++) {
//      ch = S.charAt(i);
//      if (ch == '-') {
//        continue;
//      }
//      if (sb.length() > 0 && index % K == 0) {
//        sb.append("-");
//      }
//      sb.append(ch);
//      index++;
//    }
//    return sb.toString();
//  }
//}
//// @lc code=end
//
