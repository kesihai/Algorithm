#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;

const int N = 100005;

const double eps = 1e-6;
int a[N];
double p[N];
int n, d;
int start, endPos;

bool check(double mid) {
  for (int i = 1; i <= n; i++) {
    p[i] = p[i-1] + a[i] - mid;
  }
  int pos = 0;
  for (int i = d; i <= n; i++) {
    if (p[i] >= p[pos]) {
      start = pos + 1;
      endPos = i;
      return true;
    }
    if (p[i - d + 1] < p[pos]) {
      pos = i - d + 1;
    }
  }
  return false;
}

int main() {
  scanf("%d%d", &n, &d);
  for (int i = 1; i <= n; i++) {
    scanf("%d", a + i);
  }

  double le = 0, ri = 100;
  while (le + eps < ri) {
    double mid = (le + ri) / 2;   
    if (check(mid)) {
      le = mid;
    } else {
      ri = mid;
    }
  }
  printf("%d %d\n", start, endPos);
  return 0;
}


