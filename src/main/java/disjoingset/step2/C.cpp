#include <bits/stdc++.h>
using namespace std;

const int N = 200005;

struct Node {
  int le;
  int ri;
  int len;
}a[N * 4];

int f[N];
int num[N];
int n, m;


int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

void build(int pos, int le, int ri) {
  f[pos].le = le;
  f[pos].ri = ri;
  f[pos].len = 0;
  if (le == ri) return;
  int mid = (le + ri) >> 1;
  build(pos * 2, le, mid);
  build(pos * 2 + 1, mid + 1, ri);
}

void pushUp(int pos) {

}

void update(int pos. int le) {
  if (f[pos].le == f[pos].ri) {
    f[pos].len = 1;
    return;
  }
  if (f[pos * 2].ri >= le) {
    update(pos * 2, le);
  } else {
    update(pos * 2 + 1, le);
  }
  
}

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
    num[i] = 1;
  }
  build(1, 1, n);
}

inline void mergeRange(int x, int y) {
  int xx = findFather(x);
  x = xx;
  while (x <= y) {
    x = x + num[x];
    f[x] = xx;
    num[xx] += num[x];
  }
}

int main() {
  scanf("%d%d", &n, &m);
  init();
  int op, x, y;
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &op, &x, &y);
    if (op == 1) {
      merge(x, y);
    } else if (op == 2) {
      mergeRange(x, y);
    } else {
      printf("%s\n", findFather(x) == findFather(y) ? "YES" : "NO");
    }
  }
  return 0;
}
