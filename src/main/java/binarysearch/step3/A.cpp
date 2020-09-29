#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
using namespace std;

const int N = 100005;
const double eps = 1e-6;
double pos[N], v[N];

int n;

bool check(double mid) {
  double le = min(pos[0] + v[0] * mid, pos[0] - v[0] * mid);
  double ri = max(pos[0] + v[0] * mid, pos[0] - v[0] * mid);
  for (int i = 0; i < n; i++) {
    double tempLe = min(pos[i] + v[i] * mid, pos[i] - v[i] * mid);
    double tempRi = max(pos[i] + v[i] * mid, pos[i] - v[i] * mid);
    if (tempLe > ri) return false;
    if (tempRi < le) return false;
    le = max(le, tempLe);
    ri = min(ri, tempRi);
  }
  return true;
}

int main() {
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%lf%lf", pos + i, v + i);
  }
  double mi = pos[0], ma = pos[0];
  for (int i = 1; i < n; i++) {
    mi = min(mi, pos[i]);
    ma = max(ma, pos[i]);
  }
  double ri = 0;
  for (int i = 0; i < n; i++) {
    ri = max(ri, abs(pos[i] - mi) / v[i]);
    ri = max(ri, abs(pos[i] - ma) / v[i]);
  }
  double le = 0;
  while (ri - le >= eps){
    double mid = (le + ri) / 2;
    if (check(mid)){
      ri = mid;
    } else {
      le = mid;
    }
  }
  printf("%lf\n", le);
  return 0;
}
