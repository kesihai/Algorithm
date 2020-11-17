import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  int[][][][] dp;
  int len;
  static int[] preArr = new int[5];
  static int[] curArr = new int[5];
  int n;
  int m;
  int intro;
  int extro;
  Map<String, Item> mp;
  static Item[][] cost;

  static {
    int len = (int)Math.pow(3, 5);
    cost = new Item[len][len];
    for (int i = 0; i < cost.length; i++) {
      for (int j = 0; j < cost.length; j++) {
        cost[i][j] = calculate(i, j);
      }
    }
  }
  public int getMaxGridHappiness(int n, int m, int introvertsCount, int extrovertsCount) {
    mp = new HashMap<>();
    if (m > n) {
      int tmp = n;
      n = m;
      m = tmp;
    }
    preArr = new int[m];
    curArr = new int[m];
    this.n = n;
    this.m = m;
    this.intro = introvertsCount;
    this.extro = extrovertsCount;

    len = (int)Math.pow(3, m);
    dp = new int[n][introvertsCount + 1][extrovertsCount + 1][len + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        for (int k = 0; k < dp[i][j].length; k++) {
          Arrays.fill(dp[i][j][k], -2);
        }
      }
    }
    return dfs(0, 0, 0, 0);
  }

  int dfs(int pos, int in, int ex, int pre) {
    if (in > intro) return -1;
    if (ex > extro) return -1;
    if (in >= intro && ex >= extro) {
      return 0;
    }
    if (pos == n) {
      return 0;
    }
    if (dp[pos][in][ex][pre] != -2) {
      return dp[pos][in][ex][pre];
    }
    int ans = Integer.MIN_VALUE;
    for (int cur = 0; cur < len; cur++) {
      Item item = cost[pre][cur];
      int tmp = dfs(pos + 1, in + item.in, ex + item.ex, cur);
      if (tmp != -1) {
        ans = Math.max(ans, tmp + item.happy);
      }
    }
    return dp[pos][in][ex][pre] = (ans == Integer.MIN_VALUE ? -1 : ans);
  }

  private static class Item {
    int in;
    int ex;
    int happy;

    public Item(int in, int ex, int happy) {
      this.in = in;
      this.ex = ex;
      this.happy = happy;
    }

    @Override
    public String toString() {
      return "Item{" +
          "in=" + in +
          ", ex=" + ex +
          ", happy=" + happy +
          '}';
    }
  }

  public static Item calculate(int pre, int cur) {
    for (int i = 0; i < 5; i++) {
      preArr[i] = pre % 3;
      pre /= 3;
      curArr[i] = cur % 3;
      cur /= 3;
    }

    int ans = 0;
    for (int i = 0; i < preArr.length; i++) {
      if (preArr[i] == 0) {
        continue;
      }
      if (preArr[i] == 1) {
        if (curArr[i] != 0) {
          ans -= 30;
        }
      } else {
        if (curArr[i] != 0) {
          ans += 20;
        }
      }
    }
    int in = 0, ex = 0;
    for (int i = 0; i < curArr.length; i++) {
      if (curArr[i] == 0) {
        continue;
      } else if (curArr[i] == 1) {
        in++;
        if (i > 0 && curArr[i - 1] != 0) ans -= 30;
        if (preArr[i] != 0) ans -= 30;
        if (i < curArr.length - 1 && curArr[i + 1] != 0) ans -= 30;
      } else {
        if (i > 0 && curArr[i - 1] != 0) ans += 20;
        if (preArr[i] != 0) ans += 20;
        if (i < curArr.length - 1 && curArr[i + 1] != 0) ans += 20;
        ex++;
      }
    }
    Item item = new  Item(in, ex, ans + in * 120 + ex * 40);
    return item;
  }

//  public static void main(String[] args) {
//    System.out.println(Math.pow(3, 6) * 5 * 6 * 6);
//    Solution s = new Solution();
//    s.preArr = new int[3];
//    s.curArr = new int[3];
////    System.out.println(s.calculate(0, 1 + 2 * 3*3));
////    System.out.println(s.calculate(1 + 2 * 3 * 3, 2 * 3 * 3));
//    System.out.println(s.getMaxGridHappiness(5, 4, 6 , 6));
//  }
}