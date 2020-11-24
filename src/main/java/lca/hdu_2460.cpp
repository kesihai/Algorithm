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
int deep[N];
vector<int> v;

void init() {
  for (int i = 0; i <= n; i++) {
    vis[i] = 0;
    g[i].clear();
    f[i] = i;
    bridge[i] = 0;
    deep[i] = 0;
  }
  cnt = 0;
  total = 0;
  while (!s.empty()) {
    s.pop();
  }
}

void tarjan(int cur, int pre) {
  if (vis[cur]) return;
  if (pre == -1) {
    deep[cur] = 0;
  } else {
    deep[cur] = deep[pre] + 1;
  }
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

void handle(int from, int to) {
  v.clear();
  while (from != to) {
    //printf("from  to: %d %d\n", from, to);
    if (deep[from] > deep[to]) {
      if (bridge[from]) {
        bridge[from] = 0;
        total--;
      }
      v.push_back(from);
      from = f[from];
    } else if (deep[from] < deep[to]) {
      if (bridge[to]) {
        bridge[to] = 0;
        total--;
      }
      v.push_back(to);
      to = f[to];
    } else {
      if (bridge[from]) {
        bridge[from] = 0;
        total--;
      }
      v.push_back(from);
      from = f[from];

      if (bridge[to]) {
        bridge[to] = 0;
        total--;
      }
      v.push_back(to);
      to = f[to];
    }
  }
  for (int i = 0; i < v.size(); i++) {
    f[v[i]] = from;
  }
}

int main() {
  int ca = 0;
  while(scanf("%d%d", &n, &m)) {
    if (n + m == 0) break;
    printf("Case %d:\n", ++ca);
    init();
    int from, to;
    for (int i = 0; i < m; i++) {
      scanf("%d%d", &from, &to);
      g[from].push_back(to);
      g[to].push_back(from);
     // printf("hello \n");
    }

    tarjan(1, -1);
    scanf("%d", &m);
    while (m--) {
      scanf("%d%d", &from, &to);
      //printf("should print\n");
      handle(from, to);
      printf("%d\n", total);
    }
    printf("\n");
  } 
  return 0;
}
