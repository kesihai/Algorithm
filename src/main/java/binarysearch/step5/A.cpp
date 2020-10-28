#include <bits/stdc++.h>
using namespace std;

int n;
long long k;
struct Node {
  long long x;
  long long y;
}f[55];

bool check(long long mid) {
  long long m =  mid;
  long long tmp = 0;
  for (int i = 0; i < n; i++) {
    if (f[i].x > m) {
      continue;
    }
    tmp += min(m, f[i].y) - f[i].x + 1;
    if (tmp >= k) {
      return true;
    }
  }
  return false;
}

long long getAns(long long mid) {
  bool has= false;
  long long tmp = mid;
  for (int i = 0; i < n; i++) {
    if (f[i].x <= mid && f[i].y >= mid) return mid;
    if (f[i].y < mid) continue;
    if (f[i].x > mid) {
      if (!has) {
        tmp = f[i].x;
        has = true;
      } else {
        tmp = min(f[i].x, tmp);
      }
    }
  }
  return tmp;
}

int main() {
  scanf("%d%lld", &n, &k);
  k++;
  long long le, ri;
  for (int i = 0; i < n; i++) {
    scanf("%lld%lld", &f[i].x, &f[i].y);
    le = i == 0 ? f[i].x : min(le, f[i].x);
    ri = i == 0 ? f[i].y : max(ri, f[i].y);
  }
  long long ans = -1;
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
