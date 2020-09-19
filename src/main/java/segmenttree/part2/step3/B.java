//package segmenttree.part2.step3;

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
    long v;
    while (m-- > 0) {
      op = in.nextInt();
      if (op == 1) {
        x = in.nextInt();
        y = in.nextInt();
        tree.update(x, y - 1);
      } else {
        x = in.nextInt();
        System.out.println(tree.query(x + 1));
      }
    }
  }

  static class Tree {
    int le;
    int ri;
    boolean reverse;
    int oneNum = 0;
    Tree left;
    Tree right;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      reverse = false;
      oneNum = 0;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      left = new Tree();
      right = new Tree();
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R) {
      if (L == le && ri == R) {
        reverse = !reverse;
        oneNum = (R - L + 1) - oneNum;
        return;
      }

      pushDown();
      if (left.ri >= R) {
        left.update(L, R);
      } else if (right.le <= L) {
        right.update(L, R);
      } else {
        left.update(L, left.ri);
        right.update(right.le, R);
      }
      pushUp();
    }

    long query(int num) {
      if (le == ri) {
        return le;
      }
      pushDown();
      if (left.oneNum >= num) {
        return left.query(num);
      } else {
        return right.query(num - left.oneNum);
      }
    }

    private void pushDown() {
      if (!reverse) {
        return;
      }

      left.oneNum = (left.ri - left.le + 1) - left.oneNum;
      right.oneNum = (right.ri - right.le + 1) - right.oneNum;
      left.reverse = !left.reverse;
      right.reverse = !right.reverse;
      reverse = false;
    }

    private void pushUp() {
      oneNum = right.oneNum + left.oneNum;
    }
  }
}

