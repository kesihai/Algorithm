#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <vector>
#include <set>
using namespace std;

const int N = 40005;
int deep[N];
int parent[N][20];
int n, m;
int dis[N];

struct Node {
  int to;
  int val;
  Node(int to, int val) {
    this -> to = to;
    this -> val = val;
  }
};
vector<Node>g[N];

void dfs(int cur, int len, int pre, int dep) {
  dis[cur] = len;
  deep[cur] = dep;
  parent[cur][0] = pre;
  for (int j = 1; (1 << j) <= dep; j++) {
    parent[cur][j] = parent[parent[cur][j - 1]][j - 1];
  }
  for (int i = 0; i < g[cur].size(); i++) {
    Node node = g[cur][i];
    if (node.to == pre) continue;
    dfs(node.to, len + node.val, cur, dep + 1);
  }
}

int lca(int from, int to) {
  if (from == to) return from;
  if (from == 1 || to == 1) return 1;
  if (from == to) return from;
  if (deep[from] > deep[to]) {
    return lca(to, from);
  }
  for (int i = 19; i >= 0; i--) {
    if (parent[to][i] == -1) continue;
    int tmp = parent[to][i];
    if (deep[tmp] >= deep[from]) to = tmp;
  }
  if (from == to) return from;
  // 现在deep[from] 和 deep[to] 是一样的.
  for (int i = 19; i >= 0; i--) {
    if (parent[from][i] == -1) continue;
    if (parent[from][i] != parent[to][i]) {
      from = parent[from][i];
      to = parent[to][i];
    }
  }
  return parent[from][0];
}

int main() {
  int t;
  scanf("%d", &t);
  while (t--) {
    memset(deep, -1, sizeof(-1));
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++) {
      g[i].clear();
    }
    int from, to, len;
    for (int i = 0; i < n - 1; i++) {
      scanf("%d%d%d", &from, &to, &len);
      g[from].push_back(Node(to, len));
      g[to].push_back(Node(from, len));
    }

    dfs(1, 0, -1, 0);
    while (m--) {
      scanf("%d%d", &from, &to);
      int p = lca(from, to);
      printf("%d\n", dis[from] + dis[to] - dis[p] * 2);
    }
    if (t > 0) {
      printf("\n");
    }
  }
  return 0;
}
