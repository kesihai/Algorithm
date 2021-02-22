/*
 * @lc app=leetcode.cn id=38 lang=java
 *
 * [38] 外观数列
 */

// @lc code=start
class Solution {
    public String countAndSay(int n) {
      String pre = "1";
      for (int i = 1; i < n; i++) {
        pre = convert(pre);
      }
      return pre;
    }

    public String convert(String str) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < str.length(); i++) {
        int j = i;
        while (j + 1 < str.length() && str.charAt(j + 1) == str.charAt(i)) j++;
        sb.append(j - i + 1).append(str.charAt(i));
        i = j;
      }
      return sb.toString();
    }
}
// @lc code=end

