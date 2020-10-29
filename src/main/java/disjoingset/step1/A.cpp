#include <bits/stdc++.h>
using namespace std;

const int N = 100005;
int f[N];

int n, m;

char c[20];

int findFather(int x) {
  if (f[x] != x) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}

bool isSameSet(int x, int y) {
  int xx = findFather(x);
  int yy = findFather(y);
  return xx == yy;
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }
  int x, y;
  while (m--) {
    scanf("%s%d%d", c, &x, &y);
    if (c[0] == 'g') {
      printf("%s\n", isSameSet(x, y) ? "YES" : "NO");
    } else {
      int xx = findFather(x);
      int yy = findFather(y);
      f[xx] = yy;
    }
  }
  return 0;
}
