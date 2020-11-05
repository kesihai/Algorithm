#include <bits/stdc++.h>
using namespace std;

const int N = 300005;
int n, m;
int f[N];
int deep[N];

struct Node {
  int from;
  int to;
}a[N];

int findFather(int x) {
  if (x == f[x]) {
    return x;
  }
  int xx = f[x];
  f[x] = findFather(xx);
  deep[x] += deep[xx];
  return f[x];
}

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }
  memset(deep, 0, sizeof(deep));
}

inline bool check(int x, int y) {
  x = ((deep[x] % 2) + 2) % 2;
  y = ((deep[y] % 2) + 2) % 2;
  return x == y;
}

void solve() {
  init();
  for (int i = 0; i < m; i++) {
    int x = a[i].from;
    int y = a[i].to;
    int xx = findFather(x);
    int yy = findFather(y);
    if (xx == yy) {
      if (check(x, y)) {
        printf("%d\n", i + 1);
        return;
      }
    } else {
      deep[xx] = -deep[x] + 1;
      f[xx] = y;
    }
  }
  printf("-1\n");
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) {
    scanf("%d%d", &a[i].from, &a[i].to);
  }
  solve();
  return 0;
}
