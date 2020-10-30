#include <bits/stdc++.h>
using namespace std;

const int N = 1000005;
int f[N];
int n, m;
int x;
char c[2];

int findFather(int x) {
  if (x == -1) return x;
  if (x != f[x]) {
    f[x] = findFather(f[x]);
  }
  return f[x];
}


void handleDelete(int x) {
  if (x == n) {
    f[x] = -1;
    return;
  }
  f[x] = findFather(f[x + 1]);
}

int query(int x) {
  return findFather(x);
}


int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i <= n; i++) {
    f[i] = i;
  }

  while (m--) {
    scanf("%s%d", c, &x);   
    if (c[0] == '-') {
      handleDelete(x);
    } else {
      printf("%d\n", query(x));
    }
  }
  return 0;
}
