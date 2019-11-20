package slidwindow;

import java.util.Arrays;

public class MovingStonesUntilConsecutive_1040 {

  /**
   * 给出一个数组，数字代表在坐标轴位置，每次只能将两边的数字移到中间的空位置，求最少和最多的步数将这n个数移动到数轴上实现连续的效果
   *
   * 思路: 既然每次都只能将石子移动到左右两边的中间, 不如用一个长度为n的滑动窗口来看看能不能将n个点都移过来, 注意一点是滑动窗口的右边一定是在stones[i] 中的一个点
   */
  class Solution {
    public int[] numMovesStonesII(int[] stones) {
      Arrays.sort(stones);
      int n = stones.length;
      int ma = Math.max(stones[n - 1] - stones[1] + 1 - n + 1, stones[n - 2] - stones[0] + 1 - n + 1);
      int mi = stones.length;
      int le = 0, ri = 0;
      while (ri < stones.length) {
        while (stones[ri] - stones[le] + 1 > n) {
          le++;
        }
        if (stones[ri] - stones[le] == ri - le && (ri - le == n - 2)) {
          mi = Math.min(mi, 2);
        } else {
          mi = Math.min(mi, n - (ri - le + 1));
        }
        ri++;
      }
      return new int[] {mi, ma};
    }
  }
}
