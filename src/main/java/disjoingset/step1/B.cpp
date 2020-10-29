#include <bits/stdc++.h>
using namespace std;

const int N = 300005;
char c[20];

int n ,m; 

struct Node {
  int x;
  int mi;
  int ma;
  int num;
}f[N];

int findFather(int x) {
  if (f[x].x != x) {
    f[x].x = findFather(f[x].x);
  }
  return f[x].x;
}

void unionSet(int x, int y) {
  if (x == y) return;
  f[x].x = f[y].x;
  f[y].mi = min(f[y].mi, f[x].mi);
  f[y].ma = max(f[y].ma, f[x].ma);
  f[y].num += f[x].num;
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i <= n; i++) {
    f[i].x = f[i].mi = f[i].ma = i;
    f[i].num = 1;
  }
  int x, y;
  while (m--) {
    scanf("%s", c);
    if (c[0] == 'g') {
      scanf("%d", &x);
      int index = findFather(x);
      printf("%d %d %d\n", f[index].mi, f[index].ma, f[index].num);
    } else {
      scanf("%d%d", &x, &y);
      int xx = findFather(x);
      int yy = findFather(y);
      unionSet(xx, yy);
    }
  }
  return 0;
}
