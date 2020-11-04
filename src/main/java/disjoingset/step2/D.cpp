#include <bits/stdc++.h>
using namespace std;

const int N = 300005;

int n, m;
int f[N];
int deep[N];

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
    deep[i] = 0;
  }
}

int findFather(int x) {
  if (x == f[x]) {
    return x;
  }
  int xx = f[x];
  f[x] = findFather(xx);
  if (f[x] != xx) {
    deep[x] += deep[xx];
  }
  return f[x];
}

void handleMerge(int x, int y) {
  f[x] = y;
  deep[x] = 1;
}

void handleQuery(int x) {
  findFather(x);
  printf("%d\n", deep[x]);
}

void debug() {
  for (int i = 1; i <= n; i++) {
    findFather(i);
    printf("root: %d %d %d\n", i, f[i], deep[i]);
  }
}

int main() {
  scanf("%d%d", &n, &m);
  init();
  int op, x, y;
  while (m--) {
    scanf("%d", &op);
    if (op == 1) {
      scanf("%d%d", &x, &y);
      handleMerge(x, y);
    } else {
      scanf("%d", &x);
      handleQuery(x);
    }
  }
  //debug();
  return 0;
}
