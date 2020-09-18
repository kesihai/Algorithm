package segmenttree.part2.step1;

import java.util.Scanner;

public class Seg_part2_A {
  static int n, m;
  static int[] a;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    n = in.nextInt();
    m = in.nextInt();
    a = new int[n];
//    for (int i = 0; i < n; i++) {
//      a[i] = in.nextInt();
//    }
    Tree tree = new Tree();
    tree.build(0, n - 1);
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
        System.out.println(tree.query(x));
      }
    }
  }

  static class Tree {
    int le;
    int ri;
    long value;
    Tree left;
    Tree right;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      this.value = 0;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R, int v) {
      if (L == le && ri == R) {
        this.value += v;
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
    }

    long query(int pos) {
      if (le == ri) {
        return value;
      }
      pushDown();
      if (left.ri >= pos) {
        return left.query(pos);
      }
      return right.query(pos);
    }

    private void pushDown() {
      if (value == 0) {
        return;
      }
      left.value += value;
      right.value += value;
      value = 0;
    }
  }
}
