package slidwindow;

import java.util.Arrays;

/**
 * leetcode 滑动窗口，要保证QWER 出现的次数都是n/4, 请问最小的修改字符串的长度
 * https://leetcode.com/problems/replace-the-substring-for-balanced-string/
 */
class Solution {
  int[] a = new int[4];
  int[] b = new int[4];
  final String ss = "QWER";

  public int balancedString(String s) {
    Arrays.fill(a, 0);
    for (char ch : s.toCharArray()) {
      for (int i = 0; i < ss.length(); i++) {
        if (ss.charAt(i) == ch) {
          a[i]++;
        }
      }
    }

    for (int i = 0; i < a.length; i++) {
      a[i] = Math.max(0, a[i] - s.length() / 4);
    }
    Arrays.fill(b, 0);
    int ans = s.length();
    int le = 0, ri = 0;
    while (ri < s.length()) {
      for (int i = 0; i < ss.length(); i++) {
        if (ss.charAt(i) == s.charAt(ri)) {
          b[i]++;
        }
      }
      ri++;
      while (check() && le < s.length()) {
        for (int i = 0; i < ss.length(); i++) {
          if (ss.charAt(i) == s.charAt(le)) {
            b[i]--;
          }
        }
        ans = Math.min(ans, ri - le);
        le++;
      }
    }
    return ans;
  }

  private boolean check() {
    for (int i = 0; i < a.length; i++) {
      if (!(b[i] >= a[i])) return false;
    }
    return true;
  }
}

public class QwerEqualProblem1234 {
}
