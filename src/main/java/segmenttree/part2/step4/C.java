package segmenttree.part2.step4;

import java.util.Scanner;

public class C {
  static Scanner in = new Scanner(System.in);
  static int n;
  static int N = 500000;
  public static void main(String[] args) {
    Tree tree = new Tree();
    tree.build(0, N * 2 + 2);
    n = in.nextInt();
    in.nextLine();
    char ch;
    String str;
    String[] a;
    int x, y;
    while (n-- > 0) {
      str = in.nextLine();
      a = str.split(" ");
      x = Integer.parseInt(a[1]);
      y = Integer.parseInt(a[2]);
      y = y + x - 1;
      x += N;
      y += N;
//      System.out.println(String.format("%s %s %s", a, x, y));
      if (a[0].charAt(0) == 'B') {
        tree.update(x, y, Color.black);
      } else {
        tree.update(x, y, Color.white);
      }
      System.out.println(String.format("%d %d", tree.segNum, tree.num));
    }
  }

  static enum Color {
    white,
    black
  }

  public static class Tree {
    int le, ri;
    Color leftColor, rightColor;
    int segNum;
    int num;
    Tree left, right;
    Color sameColor;

    public void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      this.segNum = 0;
      this.num = 0;
      this.leftColor = Color.white;
      this.rightColor = Color.white;
      this.sameColor = null;

      if (le == ri) {
        return;
      }
      left = new Tree();
      right = new Tree();
      int mid = (le + ri) / 2;
      left.build(le, mid);
      right.build(mid + 1, ri);
    }

    public void update(int L, int R, Color color) {
      if (le == L && ri == R) {
        this.segNum = color == Color.black ? 1 : 0;
        this.num = color == Color.black ? (ri - le + 1) : 0;
        this.leftColor = this.rightColor = color;
        this.sameColor = color;
        return;
      }
      pushDown();
      if (left.ri >= R) {
        left.update(L, R, color);
      } else if (right.le <= L) {
        right.update(L, R, color);
      } else {
        left.update(L, left.ri, color);
        right.update(right.le, R, color);
      }
      pushUp();
    }

    public void pushDown() {
      if (sameColor != null) {
        left.segNum = right.segNum = sameColor == Color.black ? 1 : 0;
        left.num = sameColor == Color.black ? (left.ri - left.le + 1) : 0;
        right.num = sameColor == Color.black ? (right.ri - right.le + 1) : 0;
        left.sameColor = right.sameColor = sameColor;
        left.leftColor = left.rightColor = sameColor;
        right.leftColor = right.rightColor = sameColor;
        sameColor = null;
      }
    }

    public void pushUp() {
      num = left.num + right.num;
      segNum = left.segNum + right.segNum;
      if (left.rightColor == right.leftColor) {
        if (left.rightColor == Color.black) {
          segNum--;
        }
      }
      leftColor = left.leftColor;
      rightColor = right.rightColor;
    }

  }
}
