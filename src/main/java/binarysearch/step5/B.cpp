#include <bits/stdc++.h>
using namespace std;

const int N = 100005;

long long n;
long long k;
struct Node {
  long long x;
  long long y;
}f[N];

bool check(long long m) {
  long long tmp = 0;
  for (int i = 1; i <= n; i++) {
    long long t = m / i;
    tmp += min(t, n);
    if (tmp >= k) return true;
  }
  return false;
}

int main() {
  scanf("%lld%lld", &n, &k);
  for (int i = 1; i <= n; i++) {
    f[i].x = i;
    f[i].y = i * n;
  }
  long long ans = -1;
  long long le = 1, ri = n * n;
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
