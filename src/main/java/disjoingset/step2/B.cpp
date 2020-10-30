#include <bits/stdc++.h>
using namespace std;

const int N = 300005;
int f[N];
int n;

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
}

int query(int x) {
  x = findFather(x);
  return x;
}

void occupy(int x) {
  if (x == n) {
    f[x] = findFather(1);
  } else {
    f[x] = findFather(x + 1);
  }
}

int main() {
  scanf("%d", &n);
  init();
  int x;
  bool has = false;
  for (int i = 0; i < n; i++) {
    scanf("%d", &x);
    x = query(x);
    if (has) {
      printf(" ");
    }
    has = true;
    printf("%d", x);
    occupy(x);
  }
  printf("\n");
  return 0;
}
