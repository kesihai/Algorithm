#include <bits/stdc++.h>
using namespace std;

const int N = 50005;
const int M = 100005;

int n, m;
int f[N];
int vis[M];
long long s;
vector<int> v;

struct Node {
  int from;
  int to;
  int value;
  int pos;
  bool operator < (const Node& a) const {
    return value > a.value;
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
  memset(vis, 0, sizeof(vis));
}

void solve() {
  init();
  int count = 0;
  for (int i = 0; i < m; i++) {
    int x = findFather(a[i].from);
    int y = findFather(a[i].to);
    if (x == y) continue;
    f[x] = y;
    count++;
    vis[i] = 1;
    if (count == n - 1) break;
  }
  v.clear();
  for (int i = m - 1; i >= 0; i--) {
    if (vis[i]) continue;
    if (s < a[i].value) break;
    v.push_back(a[i].pos);
    s -= a[i].value;
  }
  printf("%lu\n", v.size());
  for (int i = 0; i < v.size(); i++) {
    if (i != 0)  {
      printf(" ");
    }
    printf("%d", v[i]);
  }
  printf("\n");

}

int main() {
  scanf("%d%d%lld", &n, &m, &s);
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &a[i].from, &a[i].to, &a[i].value);
    a[i].pos = i + 1;
  }
  sort(a, a + m);
  solve();
  return 0;
}
