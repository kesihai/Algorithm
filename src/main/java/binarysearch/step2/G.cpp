#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 205;
const double esp = 1e-6;

long long a[N], n, k;

bool check(long long mid) {
  long long ans = 0;
  for (int i = 0; i < n; i++) {
    ans +=min(mid, a[i]);
  }
  return ans >= mid * k;
}

int main() {
  scanf("%lld%lld", &k, &n);
  long long sum = 0;
  for (int i = 0; i < n; i++) {
    scanf("%lld", a + i);
    sum += a[i];
  }
  long long le = 0, ri = sum / k;
  long long ans = 0;
  while (le <= ri) {
    long long mid = (le + ri) >> 1;
    if (check(mid)) {
      ans = mid;
      le = mid + 1;
    } else {
      ri = mid - 1;
    }
  }
  printf("%lld\n", ans);
  return 0;
}

