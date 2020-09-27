//package binarysearch.step2;

import java.util.Scanner;

public class A {
  static Scanner in = new Scanner(System.in);
  static long x, y, n;
  public static void main(String[] args) {
    x = in.nextLong();
    y = in.nextLong();
    n = in.nextLong();
    System.out.println(solve());
  }

  private static long solve() {
    long le = Math.max(x, y);
    long ri = n * le;
    long ans = ri;
    while (le <= ri) {
      long mid = (le + ri) / 2;
      long h = mid / x;
      long w = mid / y;
      if (h >= 1.0 * n / w) {
        ans = mid;
        ri = mid - 1;
      } else {
        le = mid + 1;
      }
    }
    return ans;
  }
}
