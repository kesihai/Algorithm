#include <bits/stdc++.h>
using namespace std;

const int N = 200005;

int n, m;
int f[N];

struct Node {
  int from;
  int to;
  int value;
  bool operator < (const Node& a) const {
    return value < a.value;
  }
}a[N];

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }
}

int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

void solve() {
  long long ans = 0;
  for (int i = 0; i < m; i++) {
    int from = findFather(a[i].from);
    int to = findFather(a[i].to);
    if (from != to) {
      ans += a[i].value;
      f[from] = to;
    }
  }
  printf("%lld\n", ans);
}

int main() {
  scanf("%d%d", &n, &m);
  init();
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &a[i].from, &a[i].to, &a[i].value);
  }
  sort(a, a + m);
  solve();
  return 0;
}
