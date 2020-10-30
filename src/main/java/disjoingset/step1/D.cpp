#include <bits/stdc++.h>
using namespace std;

const int N = 150005;

struct Node {
  char c[10];
  int u, v;
}f[N];

int n, m, k;
int a[N];
vector<bool>ans;

int findFather(int x) {
  if (x != a[x]) {
    a[x] = findFather(a[x]);
  }
  return a[x];
}

inline void handleAsk(int x, int y) {
  int xx = findFather(x);
  int yy = findFather(y);
  ans.push_back(xx == yy);
}

inline void handleCut(int x, int y) {
  int xx = findFather(x);
  int yy = findFather(y);
  if (xx == yy) return;
  a[xx] = yy;
}

int main() {
  scanf("%d%d%d", &n, &m, &k);
  ans.clear();
  for (int i = 0; i <= n; i++) {
    a[i] = i;
  }
  int x, y, z;
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &x, &y, &z);
  }
  for (int i = 0; i < k; i++) {
    scanf("%s%d%d", f[i].c, &f[i].u, &f[i].v);
  }
  for (int i = k - 1; i >= 0; i--) {
    if (f[i].c[0] == 'a') {
      handleAsk(f[i].u, f[i].v);
    } else {
      handleCut(f[i].u, f[i].v);
    }
  }
  for (int i = ans.size() - 1; i >= 0; i--) {
    printf("%s\n", ans[i] ? "YES" : "NO");
  }
  return 0;
}

