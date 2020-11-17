#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <set>
#include <map>
#include <vector>
using namespace std;

const int N = 10005;
const int M = 15;
int father[N];
int dis[N];
int deep[N];
int dp[N][M];

struct Node {
  Node(int to, int val) {
    this-> to = to;
    this-> val = val;
  }
  int to;
  int val;
};

vector<Node>g[N];
int n, m, q;

void init() {
  for (int i = 0; i <= n; i++) {
    father[i] = i;
    g[i].clear();
  }
  memset(dp, -1, sizeof(dp));
}

int findFather(int x) {
  if (x != father[x]) {
    father[x] = findFather(father[x]);
  }
  return father[x];
}

inline void merge(int from, int to) {
  from = findFather(from);
  to = findFather(to);
  father[from] = to;
}

void dfs(int cur, int pre, int len, int dep) {
  dis[cur] = len;
  deep[cur] = dep;
  dp[cur][0] = pre;
  for (int i = 1; (1 << i) <= dep; i++) {
    dp[cur][i] = dp[dp[cur][i - 1]][i - 1];
  }

  for (int i = 0; i < g[cur].size(); i++) {
    Node node = g[cur][i];
    if (node.to == pre) {
      continue;
    }
    dfs(node.to, cur, len + node.val, dep + 1);
  }
}

inline int lca(int from, int to) {
  if (from == to) return from;
  if (deep[from] > deep[to]) {
    return lca(to, from);
  }
  for (int i = M - 1; i >= 0; i--) {
    if (dp[to][i] != -1 && deep[dp[to][i]] >= deep[from]) {
      to = dp[to][i];
    }
  }
  if (from == to) return from;
  for (int i = M - 1; i >= 0; i--) {
    if (dp[from][i] != dp[to][i]) {
      from = dp[from][i];
      to = dp[to][i];
    }
  }
  return dp[from][0];
}

int main() {
  while (scanf("%d%d%d", &n, &m, &q) != EOF) {
    init();
    int from, to, val;
    while (m--) {
      scanf("%d%d%d", &from, &to, &val);
      g[from].push_back(Node(to, val));
      g[to].push_back(Node(from, val));
      merge(from, to);
    }

    for (int i = 1; i <= n; i++) {
      if (father[i] == i) {
        dfs(i, -1, 0, 0);
      }
    }

    while (q--) {
      scanf("%d%d", &from, &to);
      int tmp1 = findFather(from);
      int tmp2 = findFather(to);
      if (tmp1 != tmp2) {
        printf("Not connected\n");
        continue;
      }
      int p = lca(from, to);
      printf("%d\n", dis[from] + dis[to] - dis[p] * 2);
    }
  }
  return 0;
}
