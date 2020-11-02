#include <bits/stdc++.h>
using namespace std;

const int N = 200005;
char c[20];

int n, m;
int f[N];
int v[N];
int a[N];
vector<int>g[N];

int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

inline void add(int x, int value) {
  int xx = findFather(x);
  a[xx] += value;
}

inline void join(int x, int y) {
  x = findFather(x);
  y = findFather(y);
  if (x == y) return;
  for (int i = 0; i < g[x].size(); i++) {
    v[g[x][i]] += a[x];
  }
  a[x] = 0;
  for (int i = 0; i < g[y].size(); i++) {
    v[g[y][i]] += a[y];
  }
  a[y] = 0;
  if (g[x].size() < g[y].size()) {
    f[x] = y;
    for (int i = 0; i < g[x].size(); i++) {
      g[y].push_back(g[x][i]);
    }
    g[x].clear();
  } else {
    f[y] = x;
    for (int i = 0; i < g[y].size(); i++) {
      g[x].push_back(g[y][i]);
    }
    g[y].clear();
  }
}

inline int get(int x) {
  int xx = findFather(x);
  return v[x] + a[xx];
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i <= n; i++) {
    f[i] = i;
    g[i].clear();
    g[i].push_back(i);
    v[i] = 0;
    a[i] = 0;
  }
  int x, y;
  while (m--) {
    scanf("%s", c);
    if (c[0] == 'a') {
      scanf("%d%d", &x, &y);
      add(x, y);      
    } else if (c[0] == 'j') {
      scanf("%d%d", &x, &y);
      join(x, y);
    } else {
      scanf("%d", &x);
      printf("%d\n", get(x));
    }
  }
  return 0;
}
