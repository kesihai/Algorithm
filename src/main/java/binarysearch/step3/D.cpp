#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;

const int N = 100005;

const double eps = 1e-6;
int a[N];
int n, m, d;
vector<int> result;

struct Node {
public:
  Node(int to, int num) {
    this->to = to;
    this->num = num;
  }
  int to, num;
};

vector<Node>g[N];
vector<int>tmp;

bool dfs(int pos, int maxNum, vector<int>& temp, int count, int curMax) {
  if (count > d) return false;
  if (pos == n) {
    result.clear();
    for (int i = 0; i < temp.size(); i++) {
      result.push_back(temp[i]);
    }
    return true;
  }
  for (int i = 0; i < g[pos].size(); i++) {
    Node cur = g[pos][i];
    if (cur.num > maxNum) continue;
    temp.push_back(cur.to);
    if (dfs(cur.to, maxNum, temp, count + 1, max(curMax, cur.num))) {
      return true;
    }
    temp.pop_back();
  }
  return false;
}

bool check(int mid) {
  tmp.clear();
  tmp.push_back(1);
  return dfs(1, mid, tmp, 0, 0);
}

int main() {
  int from, to, num;
  int ri = 0;
  scanf("%d%d%d", &n, &m, &d);
  for (int i = 0; i < m; i++){
    scanf("%d%d%d", &from, &to, &num);
    if (from > to) {
      swap(from, to);
    }
    g[from].push_back(Node(to, num));
    ri = max(ri, num);
  }
  int le = 0, ans = -1;
  int mid = 0;
  while (le <= ri) {
    mid = (le + ri) >> 1;
    if (check(mid)) {
      ans = mid;
      ri = mid - 1;
    } else {
      le = mid + 1;
    }
  }
  if (ans == -1) {
    puts("-1");
  } else {
    printf("%d\n", (int)result.size() - 1);
    for (int i = 0; i < result.size(); i++) {
      if (i != 0) {
        printf(" ");
      }
      printf("%d", result[i]);
    }
    printf("\n");
  }
  return 0;
}

