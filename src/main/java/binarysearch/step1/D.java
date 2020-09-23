package binarysearch.step1;

import java.util.Arrays;
import java.util.Scanner;

public class D {
  static Scanner in = new Scanner(System.in);
  static int[] a;
  static int n, k;
  public static void main(String[] args) {
    n = in.nextInt();
    a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    Arrays.sort(a);
    k = in.nextInt();
    int le, ri;
    while (k-- > 0) {
      le = in.nextInt();
      ri = in.nextInt();
      le = findLe(le);
      ri = findRi(ri);
        System.out.print(Math.max(0, ri - le + 1));
      if (k != 0) {
        System.out.print(" ");
      }
    }
    System.out.println();
  }

  private static int findLe(int x) {
    int ans = n;
    int le = 0, ri = n - 1;
    int mid;
    while (le <= ri) {
      mid = (le + ri) >> 1;
      if (a[mid]>= x) {
        ans = mid;
        ri = mid - 1;
      } else {
        le = mid + 1;
      }
    }
    return ans;
  }

  private static int findRi(int x) {
    int ans = -1;
    int le = 0, ri = n - 1;
    int mid;
    while (le <= ri) {
      mid = (le + ri) >> 1;
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
