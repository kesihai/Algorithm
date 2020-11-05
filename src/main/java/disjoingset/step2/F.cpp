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

long long tryPos(int pos) {
  init();
  int count = 0;
  int mi = 0;
  for (int i = pos; i < m; i++) {
    int x = findFather(a[i].from);
    int y = findFather(a[i].to);
    if (x == y) continue;
    f[x] = y;
    if (count == 0) mi = a[i].value;
    count++;
    if (count == n - 1) return a[i].value - mi;
  }
  return -1;
}

void solve() {
  long long ans = -1;
  for (int i = 0; i < m; i++) {
    long long tmp = tryPos(i);
    if (i == 0 && tmp == -1) {
      printf("NO\n");
      return;
    }
    if (tmp == -1) break;
    if (ans == -1 || ans > tmp) {
      ans = tmp;
    }
  }
  if (ans == -1) {
    printf("NO\n");
  } else {
    printf("YES\n%lld\n", ans);
  }
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
