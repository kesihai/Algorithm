package binarysearch.step1;

import java.util.Scanner;

public class A {
  static Scanner in = new Scanner(System.in);
  static int[] a;
  static int n, k;
  public static void main(String[] args) {
    n = in.nextInt();
    k = in.nextInt();
    a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int x;
    while (k-- > 0) {
      x = in.nextInt();
      System.out.println(check(x) ? "YES" : "NO");
    }
  }

  private static boolean check(int x) {
    int le = 0, ri = n - 1;
    int mid;
    while (le <= ri) {
      mid = (le + ri) / 2;
      if (a[mid] == x) {
        return true;
      } else if (a[mid] < x) {
        le = mid + 1;
      } else {
        ri = mid - 1;
      }
    }
    return false;
  }
}
