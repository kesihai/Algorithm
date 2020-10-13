#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>
using namespace std;

const int N = 100005;
const double eps = 1e-6;
int a[N];

int n, k;

bool check(long long mid) {
  int pre= -1;
  int count = 0;
  for (int i = 0; i < n; i++) {
    if (pre == -1 || a[i] - pre >= mid) {
      count++;
      pre = a[i];
      if (count >= k) return true;
    }
  }
  return count >= k;
}

int main() {
  scanf("%d%d", &n, &k);
  int ri = 0;
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
    ri = max(ri, a[i]);
  }
  int mid = 0;
  int ans = 0;
  int le = 0;
  while(le <= ri) {
    mid = (le + ri) >> 1;
    if (check(mid)) {
      ans = mid;
      le = mid + 1;
    } else {
      ri = mid - 1;
    }
  }
  printf("%d\n", ans);
  return 0;
}
