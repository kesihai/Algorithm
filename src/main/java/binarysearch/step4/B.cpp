#include <bits/stdc++.h>
using namespace std;

const int N = 100005;
const double eps = 1e-6;

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
int pre[N];
vector<int> path;

double dfs(int pos, double avg) {
  if (pos == n) {
    return dp[pos] = 0;
  }
  if (vis[pos] != -1) {
    return dp[pos];
  }
  dp[pos] = 1000 * 1000;
  for (int i = 0; i < g[pos].size(); i++) {
    int to = g[pos][i].to;
    if (vis[pos] == -1) {
      vis[pos] = 1;
      dp[pos] = g[pos][i].num - avg + dfs(to, avg);
      pre[pos] = to;
    } else {
      double tmp = g[pos][i].num - avg + dfs(to, avg);
      if (tmp < dp[pos]) {
        dp[pos] = tmp;
        pre[pos] = to;
      }
    }
  }
  return dp[pos];
}

void getAns(int pos) {
  path.push_back(pos);
  if (pos == n) {
    return;
  }
  getAns(pre[pos]);
}

bool check(double mid) {
  memset(vis, -1, sizeof(vis));
  double tmp = dfs(1, mid);
  return tmp <= 0;
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


  double le = 0, ri = 1000;
  while (le + eps < ri) {
    double mid = (le + ri) / 2;
    if (check(mid)) {
      ri = mid;
    } else {
      le = mid;
    }
  }
  path.clear();
  getAns(1);
 // printf("%.3lf\n", le);
  //printf("\n");
  printf("%lu\n", path.size() - 1);
  for (unsigned int i = 0; i < path.size(); i++) {
    if (i == 0) {
      printf("%d", path[i]);
    } else {
      printf(" %d", path[i]);
    }
  }
  printf("\n");
  return 0;
}


