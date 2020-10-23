#include <bits/stdc++.h>
using namespace std;

const int N = 100005;
const double eps = 1e-7;

struct Node {
  int a;
  int b;
}f[N];

int n, k;
double a[N];

bool com(int a, int b) {
  return a > b;
}

bool check(double mid) {
  for (int i = 0; i < n; i++) {
    a[i] = f[i].a - f[i].b * mid;
  }
  sort(a, a + n);
  double sum = 0;
  for (int i = 0; i < k; i++) {
    sum+=a[n - 1 - i];
  }
  return sum >= 0;
}

int main() {
  double le = 0, ri = 0;
  scanf("%d%d", &n, &k);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", &f[i].a, &f[i].b);
    ri = max(ri, f[i].a * 1.0 / f[i].b);
  }
  while (le + eps < ri) {
    double mid = (le + ri) / 2;
    if (check(mid)) {
      le = mid;
    } else {
      ri = mid;
    }
  }
  printf("%.7lf\n", le);
  return 0;
}

