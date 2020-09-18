package segmenttree.part2.step2;

import java.util.Scanner;

public class B {
  static int n, m;
  static int[] a;
  private static final long mod = 1000000007;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    n = in.nextInt();
    m = in.nextInt();
    Tree tree = new Tree();
    tree.build(0, n - 1);
//    System.out.println(tree.value);
    int op, x, y, v;
    while (m-- > 0) {
      op = in.nextInt();
      if (op == 1) {
        x = in.nextInt();
        y = in.nextInt();
        v = in.nextInt();
        tree.update(x, y - 1, v);
      } else {
        x = in.nextInt();
        y = in.nextInt();
        System.out.println(tree.query(x, y - 1));
      }
    }
  }

  static class Tree {
    int le;
    int ri;
    long value;
    long multiple = 1;
    Tree left;
    Tree right;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      this.value = 1;
      multiple = 1;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
      pushUp();
    }

    public void update(int L, int R, int v) {
      if (L == le && ri == R) {
        value = (value * v) % mod;
        multiple = multiple * v % mod;
        return;
      }

      pushDown();
      if (left.ri >= R) {
        left.update(L, R, v);
      } else if (right.le <= L) {
        right.update(L, R, v);
      } else {
        left.update(L, left.ri, v);
        right.update(right.le, R, v);
      }
      pushUp();
    }

    long query(int L, int R) {
      if (le == L && ri == R) {
        return value;
      }
      pushDown();
      if (left.ri >= R) {
        return left.query(L, R);
      } else if (right.le <= L) {
        return right.query(L, R);
      } else {
        return (left.query(L, left.ri) + right.query(right.le, R)) % mod;
      }
    }

    private void pushDown() {
      if (multiple == 1) {
        return;
      }
      left.value = (left.value * multiple) % mod;
      right.value = (right.value * multiple) % mod;
      right.multiple = (right.multiple * multiple) % mod;
      left.multiple = (left.multiple * multiple) % mod;
      multiple = 1;
    }

    private void pushUp() {
      value = (left.value + right.value) % mod;
    }
  }
}

