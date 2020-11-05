#include <bits/stdc++.h>
using namespace std;

const int N = 200005;
int n, m;
int f[N];
int shift;
int deep[N];

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
  shift = 0;
}

void merge(int x, int y) {
  int xx = findFather(x);
  if (deep[x] % 2 == 0) {
    deep[xx] = 1;
  }
  f[xx] = y;
}

void query(int x, int y) {
  findFather(x);
  findFather(y);
  if (deep[x] % 2 == deep[y] % 2) {
    printf("YES\n");
    shift++;
  } else {
    printf("NO\n");
  }
  //printf("query: %d %d %d %d\n", x, y, deep[x], deep[y]);
}

int main() {
  scanf("%d%d", &n, &m);
  init();
  int op, x, y;
  while (m--) {
    scanf("%d%d%d", &op, &x, &y);
    x = (x + shift) % n;
    y = (y + shift) % n;
    if (op == 0) {
      merge(x, y);
    } else {
      query(x, y);
    }

  }
  return 0;
}
