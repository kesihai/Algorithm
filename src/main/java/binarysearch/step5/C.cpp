#include <bits/stdc++.h>
using namespace std;

const int N = 100005;

long long n;
long long k;
long long a[N], b[N];


int get(long long m) {
  int le = 0, ri = n - 1;
  int ans = -1;
  while (le <= ri) {
    int mid = (le + ri) >> 1;
    if (b[mid] <= m) {
      ans = mid;
      le = mid + 1;
    } else {
      ri = mid - 1;
    }
  }
  return ans + 1;
}
bool check(long long mid) {
  long long tmp = 0;
  for (int i = 0; i < n; i++) {
    tmp += get(mid - a[i]);
    if (tmp >= k) return true;
  }
  //printf("hello: %lld %lld %lld\n", mid, tmp, k);
  return tmp >= k;
  //return false;
}

int main() {
  scanf("%lld%lld", &n, &k);
  for (int i = 0; i < n; i++) {
    scanf("%lld", a + i);
  }
  for (int i = 0; i < n; i++) {
    scanf("%lld", b + i);
  }
  sort(a, a + n);
  sort(b, b + n);
  long long le = a[0] + b[0];
  long long ri = a[n - 1] + b[n - 1];
  long long ans = -1;
  long long mid;
  while (le <= ri) {
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
