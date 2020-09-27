#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 100005;
const double esp = 0.000001;
long long n, x, y;

bool check(long long len) {
  if (len <= 0) {
    return n == 0;
  }
  long long all = 1 + (len - y) / x + (len - y) / y;
  return all >= n;
}

int main() {
  scanf("%lld%lld%lld", &n, &x, &y);
  if (x < y) {
    swap(x, y);
  }
  long long ri = n * max(x, y);
  long long le = y;
  long long ans = 0;
  while (le <= ri) {
    long long mid = (le + ri) >> 1;
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

