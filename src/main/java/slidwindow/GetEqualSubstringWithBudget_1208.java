package slidwindow;

/**
 * 给定两个等长的字符串, 将s 变为t, 每一个字符串的花费是abs(s[i] - t[i]), 最多的花费为maxCost, 问在maxCost 花费内, 可以让st的最大长度为多少.
 */
public class GetEqualSubstringWithBudget_1208 {

  class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
      int cost = 0;
      int le = 0, ri = 0;
      int ans = 0;
      while (ri < t.length()) {
        cost += Math.abs(s.charAt(ri) - t.charAt(ri));
        while (cost > maxCost) {
          cost -= Math.abs(s.charAt(le) - t.charAt(le));
          le++;
        }
        ans = Math.max(ans, ri - le + 1);
        ri++;
      }
      return ans;
    }
  }
}
