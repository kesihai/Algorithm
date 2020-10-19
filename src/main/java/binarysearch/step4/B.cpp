#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;

const int N = 100005;
const double eps = 1e-6;
double ans = 10000;

int n, m;
struct Node {
public:
  Node(int to, int num) {
    this->to = to;
    this->num = num;
  }
  int to;
  int num;
};

vector<Node>g[N];
double dp[N];
int vis[N];


double dfs(int pos, double avg) {
  if (pos == n) {
    return dp[N] = 0;
  }
  if (vis[N]) {
    return dp[N];
  }
  for (int i = 0; i < g[pos].size(); i++) {
    int to = g[pos][i].to;
    if (!vis[N]) {
      vis[N] = true;
      dp[N] = g[pos][i].num - avg + dfs(to, avg);
    } else {
      dp[N] = min(dp[N], g[pos][i].num - avg + dfs(to, avg));
    }
  }
  return dp[N];
}

void getAns(double mid) {
  
}

bool check(double mid) {
  memset(vis, -1, sizeof(vis));
  return dfs(1, mid) <= 0; 
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 1; i <= n; i++) {
    g[i].clear();
  }
  int from, to, num;
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &from, &to, &num);
    g[from].push_back(Node(to, num));
  }

  ans = 1000;

  double le = 0, ri = ans;
  while (le + eps < ri) {
    double mid = (le + ri) / 2;
    if (check(mid)) {
      ri = mid;
    } else {
      le = mid;
    }
  }

  return 0;
}


