package binarysearch.step1;

import java.util.Scanner;

public class B {
  static Scanner in = new Scanner(System.in);
  static int[] a;
  static int n, k;
  public static void main(String[] args) {
    n = in.nextInt();
    k = in.nextInt();
    a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = in.nextInt();
    }
    int x;
    while (k-- > 0) {
      x = in.nextInt();
      System.out.println(check(x));
    }
  }

  private static int check(int x) {
    int le = 1, ri = n;
    int mid;
    int ans = 0;
    while (le <= ri) {
      mid = (le + ri) / 2;
      if (a[mid] <= x) {
        ans = mid;
        le = mid + 1;
      } else {
        ri = mid - 1;
      }
    }
    return ans;
  }
}
