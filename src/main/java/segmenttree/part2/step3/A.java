package segmenttree.part2.step3;

import java.util.Scanner;

public class A {
  static int n, m;
  private static final long mod = 1000000007;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    n = in.nextInt();
    m = in.nextInt();
    Tree tree = new Tree();
    tree.build(0, n - 1);
    int op, x, y;
    long v;
    while (m-- > 0) {
      x = in.nextInt();
      y = in.nextInt();
      v = in.nextLong();
      tree.update(x, y - 1, v);
      System.out.println(tree.max);
    }
  }

  static class Tree {
    int le;
    int ri;
    long max;
    long pre;
    long suf;
    long value;
    long sum;
    boolean assign;
    Tree left;
    Tree right;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      pre = suf = max = sum = 0;
      value = 0;
      assign = false;
      max = 0;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R, long v) {
      if (L == le && ri == R) {
        assign = true;
        value = v;
        sum = 1L * (R - L + 1) * v;
        pre = max = suf = Math.max(0, (R - L + 1) * v);
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

//    long query(int L, int R) {
//      if (le == L && ri == R) {
//        return max;
//      }
//      pushDown();
//      if (left.ri >= R) {
//        return left.query(L, R);
//      } else if (right.le <= L) {
//        return right.query(L, R);
//      } else {
//        return (left.query(L, left.ri) + right.query(right.le, R));
//      }
//    }

    private void pushDown() {
      if (!assign) {
        return;
      }

      left.suf = left.pre = left.max = Math.max(0, 1L * (left.ri - left.le + 1) * value);
      left.sum = (left.ri - left.le + 1) * value;

      right.suf = right.pre  = right.max = Math.max(0, (right.ri - right.le + 1) * value);
      right.sum = (right.ri - right.le + 1) * value;


      left.assign = right.assign = true;
      left.value = right.value = value;

      assign = false;
    }

    private void pushUp() {
      pre = Math.max(left.pre, left.sum + right.pre);
      suf = Math.max(right.suf, right.sum + left.suf);
      sum = left.sum + right.sum;
      max = Math.max(left.max, right.max);
      max = Math.max(max, left.suf + right.pre);
    }
  }
}

