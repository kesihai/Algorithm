#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <set>
#include <map>
#include <vector>
using namespace std;

const int N = 80005;
const int M = 25;
int father[N];
int deep[N];
int dp[N][M];
int val[N];

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
int ans[N];

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

void dfs(int cur, int pre, int dep) {
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
    dfs(node.to, cur, dep + 1);
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

void work(int from, int to, int k) {
  int p = lca(from, to);
  int count = 0;
  while (from != p) {
    ans[count++] = val[from];
    from = dp[from][0];
  }
  while (to != p) {
    ans[count++] = val[to];
    to = dp[to][0];
  }
  ans[count++] = val[p];
  if (count < k) {
    printf("invalid request!\n");
  } else {
    sort(ans, ans + count);
    printf("%d\n", ans[count - k]);
  }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++) {
      scanf("%d", val + i);
    }
    init();
    int from, to, op;
    for (int i = 0; i < n - 1; i++) {
      scanf("%d%d", &from, &to);
      g[from].push_back(Node(to, 0));
      g[to].push_back(Node(from, 0));
    }
    dfs(1, -1, 0);

    while (m--) {
      scanf("%d%d%d", &op, &from, &to);
      if (op == 0) {
        val[from] = to;
        continue;
      }
      work(from, to, op);
    }
    return 0;
}
