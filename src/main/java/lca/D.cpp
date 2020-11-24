#pragma comment(linker, "/STACK:102400000,102400000")
#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <set>
#include <map>
#include <stack>
#include <vector>
#include <cstring>
using namespace std;

const int N = 100005;

int vis[N], f[N], low[N];
int bridge[N];
int n, m;
vector<int> g[N];
int cnt;
int total = 0;
stack<int> s;

void init() {
  for (int i = 0; i <= n; i++) {
    vis[i] = 0;
    g[i].clear();
    f[i] = i;
    bridge[i] = 0;
  }
  cnt = 0;
  total = 0;
  s.clear();
}

void tarjan(int cur, int pre) {
  if (vis[cnt]) return;
  vis[cur] = low[cur] = ++cnt; 
  f[cur] = pre;
  int pre_cnt = 0;
  s.push(cur);
  for (int i = 0; i < g[cur].size(); i++) {
    int to = g[cur][i];
    if (to == pre && pre_cnt == 0) {
      pre_cnt++;
      continue;
    }
    tarjan(to, cur);
    low[cur] = min(low[cur], low[to]);
    if (vis[cur] < low[to]) {
      bridge[to] = 1;
      total++;
    }
  }
  if (low[cur] == vis[cur]) {
    int tmp = s.top();
    do {
      tmp = s.top();
      s.pop();
      if (tmp != cur) {
        f[tmp] = cur;
      }
    } while(tmp != cur);
  }
}

int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

void handle(int from, int to) {

}

int main() {
  int ca = 0;
  while(scanf("%d%d", &n, &m)) {
    if (n + m == 0) break;
    init();
    int from, to;
    for (int i = 0; i < m; i++) {
      g[from].push_back(to);
      g[to].push_back(from);
    }

    tarjan(1, -1);
    scanf("%d", &m);
    while (m--) {
      scanf("%d%d", &from, &to);
      handle(from, to);
      printf("%d\n", total);
    }
  } 
  return 0;
}
