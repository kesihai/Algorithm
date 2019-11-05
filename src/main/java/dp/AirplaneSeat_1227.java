package dp;

/**
 * n个人上飞机, 每个人都有自己的座位, 但是现在每个人上飞机, 如果自己的位置被占了,
 * 就随机选择位置, 否则选自己的位置,现在第一个人的票掉了, 第一个人随便占了一个位置,
 * 后面的人还是遵守前面的规则，请问, 最后第n个人坐在自己的位置的概率为多少?
 * <p>
 * 分析， 假设有n个人:
 * 1. 第一个人有两种情况,
 *    a. 1/n 的概率选中自己的位置.
 *    b. (n-2)/n 的概率选中非自己和非最后一个人的座位 即 (n-2)*f(n-1).
 *      即总概率为 1/n + (n-2)*f(n-1).
 * 2. 对于后面的人, 假设某一时刻还有k 个人, k个人中有一个座位是被抢占的,
 *    第k个人是这个被抢占的概率是 1/k, 不是的概率是 (k-2)/k.
 *    而不是并且满足第n个人坐到自己的概率是 (k-2)/k * f(k-1),
 *    所以最终的f(k) = 1/k + (k-2)/k * f(k-1).
 * 3. 因为前面1、2 都满足 f(n) = 1/n + (n-2)/n * f(n-1), 所以这就是公式.
 */
class Solution {
  public double nthPersonGetsNthSeat(int n) {
    if (n == 1) {
      return 1.0d;
    }
    return 1.0 / n + (n - 2) * 1.0 / n * nthPersonGetsNthSeat(n - 1);
  }
}

/**
 * leetcode.
 * https://leetcode.com/problems/airplane-seat-assignment-probability/
 */
public class AirplaneSeat_1227 {
}
