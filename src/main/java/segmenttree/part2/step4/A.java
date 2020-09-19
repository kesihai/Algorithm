package segmenttree.part2.step4;

import java.util.Scanner;

public class A {
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
        tree.update(x, y - 1, v, true);
      } else if (op == 2) {
        x = in.nextInt();
        y = in.nextInt();
        v = in.nextLong();
        tree.update(x, y - 1, v, false);
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
    Tree left;
    Tree right;
    boolean assign = false;
    long value;
    long sum;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      assign = false;
      value = sum = 0;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R, long v, boolean set) {
      if (L == le && ri == R) {
        if (set) {
          assign = true;
          value = v;
          sum = (R - L + 1) * 1L * v;
        } else {
          value += v;
          sum += (R - L + 1) * 1L * v;
        }
        return;
      }

      pushDown();
      if (left.ri >= R) {
        left.update(L, R, v, set);
      } else if (right.le <= L) {
        right.update(L, R, v, set);
      } else {
        left.update(L, left.ri, v, set);
        right.update(right.le, R, v, set);
      }
      pushUp();
    }

    long query(int L, int R) {
      if (le == L && ri == R) {
//        System.out.println(String.format("le ri: %d %d %d", le, ri, sum));
        return sum;
      }
      pushDown();
      if (left.ri >= R) {
        return left.query(L, R);
      } else if (right.le <= L) {
        return right.query(L, R);
      } else {
        return (left.query(L, left.ri) + right.query(right.le, R));
      }
    }

    private void pushDown() {
      if (assign) {
        left.sum = value * (left.ri - left.le + 1);
        left.value = value;
        left.assign = true;

        right.sum = value * (right.ri - right.le + 1);
        right.value = value;
        right.assign = true;

        assign = false;
        value = 0;
      } else {
        if (value == 0) {
          return;
        }

        left.sum += value * (left.ri - left.le + 1);
        left.value += value;

        right.sum += value * (right.ri - right.le + 1);
        right.value += value;

        value = 0;
      }
    }
    private void pushUp() {
      sum = left.sum + right.sum;
    }
  }
}

/*
18 18
3 6 9
3 15 18
2 4 9 9
3 6 14
2 0 8 13
1 10 12 0
3 1 17
1 7 10 0
2 3 15 7
1 4 17 2
1 8 15 20
2 12 13 9
2 1 9 14
1 0 8 6
3 3 15
2 1 17 17
3 14 17
3 0 8

 */

