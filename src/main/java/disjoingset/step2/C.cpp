#include <bits/stdc++.h>
using namespace std;

const int N = 200005;

int f[N];
int n, m;
set<int> s;

int findFather(int x) {
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

void init() {
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }
  s.clear();
  for (int i = 1; i <= n; i++) {
    s.insert(i);
  }
}

void merge(int x, int y) {
  x = findFather(x);
  y = findFather(y);
  f[x] = y;
}

inline void mergeRange(int x, int y) {
  /*int xx = x;
  while (xx <= y) {
    set<int>::iterator it = s.lower_bound(xx);
    if (it == s.end()) break;
    if (*it > y) break;
    merge(xx, *it);
    xx = *it;
    s.erase(it);
  }
  */
  int pos = x;
  while (*s.lower_bound(pos) < y) {
    pos = *s.lower_bound(pos);
    s.erase(pos);
    merge(pos, pos + 1);
  }
}

int main() {
  scanf("%d%d", &n, &m);
  init();
  int op, x, y;
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", &op, &x, &y);
    if (op == 1) {
      merge(x, y);
    } else if (op == 2) {
      mergeRange(x, y);
    } else {
      printf("%s\n", findFather(x) == findFather(y) ? "YES" : "NO");
    }
  }
  return 0;
}
