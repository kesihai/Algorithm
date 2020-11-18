#include <bits/stdc++.h>
using namespace std;

const int N = 105;

char c[N][N];
int a[N][N];
int n, m;
vector<vector<int> > ans;

struct Node {
  Node() {

  }
  Node(int c1, int c2, int c3, int c4) {
    this-> c1 = c1;
    this -> c2 = c2;
    this -> c3 = c3;
    this -> c4 = c4;
  }

  int c1;
  int c2;
  int c3;
  int c4;
};
int pre[N];
Node path[N];

int getVal(int c1, int c2, int c3, int c4) {
  int ans = 0;
  ans += c1 * 1;
  ans += c2 * 4;
  ans += c3 * 4 * 4;
  ans += c4 * 4 * 4 * 4;
  return ans;
}

void dfs(int c1, int c2, int c3, int c4, int cur, int p) {
  queue<Node> q;
  q.push(Node(c1, c2, c3, c4));
  pre[0] = 0;
  while (!q.empty()) {
    Node node = q.front();
    q.pop();
    int cur = getVal(node.c1, node.c2, node.c3, node.c4);
    c1 = node.c1;
    c2 = node.c2;
    c3 = node.c3;
    c4 = node.c4;

    int val = getVal(c1 ^ 1, c2 ^ 1, c3 ^ 1, c4);
    if (pre[val] == -1) {
      path[val] = Node(1, 1, 1, 0);
      pre[val] = cur;
      q.push(Node(c1 ^ 1, c2 ^ 1, c3 ^ 1, c4));
    }

    val = getVal(c1 ^ 1, c2 ^ 1, c3, c4 ^ 1);
    if (pre[val] == -1) {
      path[val] = Node(1, 1, 0, 1);
      pre[val] = cur;
      q.push(Node(c1 ^ 1, c2 ^ 1, c3, c4 ^ 1));
    }

    val = getVal(c1 ^ 1, c2, c3 ^ 1, c4 ^ 1);
    if (pre[val] == -1) {
      path[val] = Node(1, 0, 1, 1);
      pre[val] = cur;
      q.push(Node(c1 ^ 1, c2, c3 ^ 1, c4 ^ 1));
    }

    val = getVal(c1, c2 ^ 1, c3 ^ 1, c4 ^ 1);
    if (pre[val] == -1) {
      path[val] = Node(0, 1, 1, 1);
      pre[val] = cur;
      q.push(Node(c1, c2 ^ 1, c3 ^ 1, c4 ^ 1));
    }
  }
}

void handle(int x, int y) {
  int c1 = a[x][y], c2 = a[x][y + 1];
  int c3 = a[x + 1][y], c4 = a[x + 1][y + 1];
  a[x][y] = a[x+1][y] = a[x + 1][y + 1] = a[x][y + 1] = 0;

  int val = getVal(c1, c2, c3, c4);
  while (val != 0) {
    Node node = path[val];
    vector<int> v;
    if (node.c1 + node.c2 + node.c3 + node.c4 != 3) {
      printf("bug: %d\n", val);
    }
    if (node.c1) {
      v.push_back(x + 1);
      v.push_back(y + 1);
    }
    if (node.c2) {
      v.push_back(x + 1);
      v.push_back(y + 1 + 1);
    }
    if (node.c3) {
      v.push_back(x + 1 + 1);
      v.push_back(y + 1);
    }
    if (node.c4) {
      v.push_back(x + 1 + 1);
      v.push_back(y + 1 + 1);
    }
    ans.push_back(v);
    val = pre[val];
  }
}

void solve() {
  ans.clear();
  int i = 0;
  for (i = 0; i < n - 1; i+=2) {
    int j = 0;
    for (j = 0; j < m - 1; j+=2) {
      handle(i, j);
    }
    if (j == m - 1) {
      handle(i, j - 1);
    }
  }
  if (i == n - 1) {
    i--;
    int j = 0;
    for (j = 0; j < m - 1; j += 2) {
      handle(i, j);
    }
    if (j == m - 1) {
      handle(i, j - 1);
    }
  }

  printf("%lu\n", ans.size());
  for (int i = 0; i < ans.size(); i++) {
    for (int j = 0; j < ans[i].size(); j++) {
      if (j != 0) {
        printf(" ");
      }
      printf("%d", ans[i][j]);
    }
    printf("\n");
  }
}

int main() {
  int t;
  memset(pre, -1, sizeof(pre));
  dfs(0, 0, 0, 0, 0, -1);
  scanf("%d", &t);
  while(t--) {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; i++) {
      scanf("%s", c[i]);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        a[i][j] = c[i][j] - '0';
      }
    }
    solve();
  }
  return 0;
}
