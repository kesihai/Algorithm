#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 100005;
const double esp = 0.000001;
int a[N], n, k;

bool check(double len) {
  int ans = 0;
  for (int i = 0; i < n; i++) {
    ans += (int) (a[i] / len);
  }
  //printf("%.0lf %d\n", len, ans);
  return ans >= k;
}

int main() {
  scanf("%d%d", &n, &k);
  double le = 0;
  double ri = 0;
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
    ri = max(ri, a[i] * 1.0);
  }
  while (ri - le >= esp) {
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
