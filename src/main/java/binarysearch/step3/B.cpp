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
  int count = 0;
  long long temp = 0;
  for (int i = 0; i < n; i++) {
    temp += a[i];
    if (temp > mid){
      temp = a[i];
      count++;
      if (count > k) {
        return false;
      }
    }
  }
  count++;
  return count <= k;
}

int main() {
  scanf("%d%d", &n, &k);
  long long sum = 0, le = 0;
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
    sum += a[i];
    le = max(le, 1LL * a[i]);
  }
  long long  ri = sum;
  long long mid = 0;
  long long ans = 0;
  while(le <= ri) {
    mid = (le + ri) >> 1;
    if (check(mid)) {
      ans = mid;
      ri = mid - 1;
    } else {
      le = mid + 1;
    }
  }
  printf("%lld\n", ans);
  return 0;
}
