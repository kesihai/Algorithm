#include <bits/stdc++.h>
using namespace std;

const int N = 2005;
const int M = 400005;

int n, m;
int f[N];

struct Node {
  int from;
  int to;
  int value;
  bool operator < (const Node& a) const {
    return value < a.value;
  }
}a[M];

int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }
}

void solve() {
  init();
  int count = 0;
  int ans = 0;
  for (int i = 0; i < m; i++) {
    int x = findFather(a[i].from);
    int y = findFather(a[i].to);
    if (x == y) continue;
    f[x] = y;
    count++;
    ans = a[i].value;
    if (count == n - 1) break;
  }
  printf("%d\n", ans);
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &a[i].from, &a[i].to, &a[i].value);
  }
  sort(a, a + m);
  solve();
  return 0;
}
