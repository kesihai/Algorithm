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
vector<int>gg[N];
int instack[N];
stack<int> s;
int vis[N];
int low[N];
int dp[N][25];
int path[N][1];
int total;
int deep[N];

void init() {
  for (int i = 0; i <= n; i++) {
    belong[i] = i;
    g[i].clear();
    gg[i].clear();
    instack[i] = 0;
  }
  cnt = 0;
  total = 0;
  memset(dp, -1, sizeof(dp));
  memset(path, 0, sizeof(path));
  memset(vis, 0, sizeof(vis));
}

void tarjan(int cur, int pre) {
  if (instack[cur]) return;
  instack[cur] = 1;
  vis[cur] = cnt;
  low[cur] = cnt++;
  s.push(cur);
  for (int i = 0; i < g[cur].size(); i++) {
    int to = g[cur][i];
    if (to == pre) continue;
    if (vis[to] == 0) {
      tarjan(to, cur);
      low[cur] = min(low[cur], low[to]);
    } else if (instack[to]) {
      low[cur] = min(low[cur], low[to]);
    }
  }

  if (vis[cur] == low[cur]) {
    total++;
    int tmp = -1;
    do {
      tmp = s.top();
      s.pop();
      belong[tmp] = cur;
      instack[tmp] = 0;
    } while (tmp != cur);
  }
}

void dfs(int cur, int pre) {
  if (instack[cur]) return;
  instack[cur] = 1;
  int bCur = belong[cur];
  for (int i = 0; i < g[cur].size(); i++) {
    int to = g[cur][i];
    int bto = belong[to];
    if (bCur != bto) {
      gg[bCur].push_back(bto);
      gg[bto].push_back(bCur);
    }
    if (to != pre) {
      dfs(to, cur);
    }
  }
}

void dfs_lca(int cur, int pre, int dep) {
  dp[cur][0] = pre;
  path[cur][0] = 1;
  deep[cur] = dep;
  for (int i = 1; (1 << i) <= dep; i++) {
    dp[cur][i] = dp[dp[cur][i-1]][i - 1];
  }
  for (int i = 0; i < gg[cur].size(); i++) {
    int to = gg[cur][i];
    if (to == pre) {
      continue;
    }
    dfs_lca(to, cur, dep + 1);
  }
}

inline int lca(int from, int to) {
  if (from == to) return from;
  if (deep[from] > deep[to]) return lca(to, from);
  for (int i = 24; i >= 0; i--) {
    if (dp[to][i] != -1 && deep[dp[to][i]] >= deep[from]) {
      to = dp[to][i];
    }
  }
  if (from == to) return from;

  for (int i = 24; i >= 0; i--) {
    if (dp[to][i] != -1 && dp[to][i] != dp[from][i]) {
      from = dp[from][i];
      to = dp[to][i];
    }
  }
  return dp[from][0];
}

inline void build(int from, int to, int p) {
  while (from != p) {
    if (path[from][0]) {
      total--;
      path[from][0] = 0;
    }
    from = dp[from][0];
  }
  while (to != p) {
    if (path[to][0]) {
      total--;
      path[to][0] = 0;
    }
    to = dp[to][0];
  }
}

void debug() {
  for (int i = 1; i <= n; i++) {
    printf("belong: %d %d\n", i, belong[i]);
  }
}

inline void handle(int from, int to) {
  int p = lca(from, to);
  build(from, to, p);
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
    tarjan(1, -1);
    //debug();
    memset(instack, 0, sizeof(instack));
    dfs(1, -1);
    int root = belong[1];
    dfs_lca(root, -1, 0);
    scanf("%d", &m);
    while (m--) {
      scanf("%d%d", &from, &to);
      if (total != 1) {
        handle(belong[from], belong[to]);
      }
      printf("%d\n", total - 1);
    }
  }
  return 0;
}


