// hdu 2460
#pragma comment(linker, "/STACK:102400000,102400000") 
#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <set>
#include <map>
#include <stack>
#include <vector>
using namespace std;

const int N = 100005;
int belong[N];
int cnt;
int n, m;
vector<int>g[N];
int instack[N];
stack<int> s;
int vis[N];
int low[N];
int path[N];
int total;
int deep[N];
int f[N];

void init() {
  for (int i = 0; i <= n; i++) {
    belong[i] = i;
    g[i].clear();
    instack[i] = 0;
  }
  cnt = 0;
  total = 0;
  memset(path, 0, sizeof(path));
  memset(vis, 0, sizeof(vis));
}

void tarjan(int cur, int pre) {
  if (instack[cur]) return;
  deep[cur] = deep[pre] + 1;
  instack[cur] = 1;
  vis[cur] = cnt;
  low[cur] = cnt++;
  f[cur] = pre;
  s.push(cur);
  for (int i = 0; i < g[cur].size(); i++) {
    int to = g[cur][i];
    if (to == pre) continue;
    tarjan(to, cur);
    if (low[to] <= low[cur]) {
      low[cur] = low[to];
    } else {
      path[to] = 1;
    }
  }

  if (vis[cur] == low[cur]) {
    total++;
    int tmp = -1;
    do {
      tmp = s.top();
      s.pop();
      instack[tmp] = 0;
      belong[tmp] = cur;
    } while (tmp != cur);
  }
}

inline void handle(int from, int to) {
  if (from == to) {
    return;
  }
  if (deep[from] > deep[to]) return handle(to, from);
  while (deep[to] > deep[from]) {
    if (path[to]) {
      total--;
      path[to] = 0;
    }
    to = f[to];
  }
  while (from != to) {
    if (path[to]) {
      total--;
      path[to] = 0;
    }
    to = f[to];

    if (path[from]) {
      total--;
      path[from] = 0;
    }
    from = f[from];
  }
}

int main() {
  // printf("%d\n", 1 << 15);
  int ca = 0;
  while(~scanf("%d%d", &n, &m)) {
    if (n + m == 0) break;
    if (ca) {
      printf("\n");
    }
    printf("Case %d:\n", ++ca);
    init();
    int from, to;
    while (m--) {
      scanf("%d%d", &from, &to);
      g[from].push_back(to);
      g[to].push_back(from);
    }
    deep[0] = 0;
    tarjan(1, 0);
    scanf("%d", &m);
    while (m--) {
      scanf("%d%d", &from, &to);
      if (total != 1) {
        handle(from, to);
      }
      printf("%d\n", total - 1);
    }
  }
  return 0;
}
