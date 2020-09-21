package segmenttree.part2.step4;

import java.util.Scanner;

public class B {
  static int n, m;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    n = in.nextInt();
    m = in.nextInt();
    Tree tree = new Tree();
    tree.build(0, n - 1);
    int op, x, y;
    long a, d;
    while (m-- > 0) {
      op = in.nextInt();
      if (op == 1) {
        x = in.nextInt();
        y = in.nextInt();
        a = in.nextLong();
        d = in.nextLong();
        tree.update(x - 1, y - 1, a, d);
      } else {
        x = in.nextInt();
        System.out.println(tree.query(x - 1));
      }
    }
  }

  static class Tree {
    int le;
    int ri;
    Tree left;
    Tree right;
    boolean assign = false;
    long a;
    long d;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      assign = false;
      a = d =  0;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R, long a, long d) {
      if (L == le && ri == R) {
        this.a += a;
        this.d += d;
        return;
      }

      pushDown();
      if (left.ri >= R) {
        left.update(L, R, a, d);
      } else if (right.le <= L) {
        right.update(L, R, a, d);
      } else {
        left.update(L, left.ri, a, d);
        right.update(right.le, R, a + (left.ri - L + 1) * d, d);
      }
    }

    long query(int L) {
      if (le == ri) {
        return a;
      }
      pushDown();
      if (left.ri >= L) {
        return left.query(L);
      } else {
        return right.query(L);
      }
    }

    private void pushDown() {
      if (a != 0) {
        left.a += a;
        right.a += a;
      }
      a = 0;

      if (d != 0) {
        left.d += d;
        right.a += (left.ri - left.le + 1) * d;
        right.d += d;
      }
      d = 0;
    }
  }
}

/*

 */

