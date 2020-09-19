//package segmenttree.part2.step3;

import java.util.Scanner;

public class C {
  static int n, m;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    n = in.nextInt();
    m = in.nextInt();
    Tree tree = new Tree();
    tree.build(0, n - 1);
    int op, x, y;
    long v;
    while (m-- > 0) {
      op = in.nextInt();
      if (op == 1) {
        x = in.nextInt();
        y = in.nextInt();
        v = in.nextLong();
        tree.update(x, y - 1, v);
      } else {
        v = in.nextLong();
        x = in.nextInt();
        System.out.println(tree.query(x, v));
      }
    }
  }

  static class Tree {
    int le;
    int ri;
    long max;
    long add;
    Tree left;
    Tree right;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      add = 0;
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
        add += v;
        max += v;
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

    long query(int L, long v) {
      if (max < v) {
        return -1;
      }
      if (le == ri) {
        return le;
      }
      pushDown();
      long ans = -1;
      if (left.ri >= L && left.max >= v) {
        ans = left.query(L, v);
        if (ans != -1) {
          return ans;
        }
      }
      return right.query(L, v);
    }

    private void pushDown() {
      if (add == 0) {
        return;
      }
      left.max += add;
      right.max += add;
      left.add += add;
      right.add += add;
      add = 0;
    }

    private void pushUp() {
      max = Math.max(left.max, right.max);
    }
  }
}

