#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 1005;
const double esp = 0.000001;

int n, m;

struct {
  int t, y, z;
}f[N];

inline long long get(long long len, int t, int y, int z) {
  long long tmp = len / (t * y + z) * y;
  long long rest = len % (t * y + z);
  tmp += min(rest / t, 1LL * y);
  return tmp; 
}

bool check(long long len, bool p = false) {
  long long total = 0;
  long long ret = 0;
  long long tmp = 0;
  for (int i = 0; i < n; i++) {
    tmp = get(len, f[i].t, f[i].y, f[i].z);
    if (p) {
      if (i) {
        printf(" ");
      }
      tmp = min(tmp, m - total);
      printf("%lld", tmp);
     total += tmp;
    }
    ret += tmp;
  }
  if (p) {
    printf("\n");
  }
  return ret >= m;
}

int main() {
  scanf("%d%d", &m, &n);
  long long le, ri = 0;
  for (int i = 0; i < n; i++) {
    scanf("%d%d%d", &f[i].t, &f[i].y, &f[i].z);
    ri = max(ri, 1LL * f[i].t * (f[i].y + f[i].z) * m);
  }

  long long ans = 0;
  le = 0;
  while (le <= ri) {
    long long mid = (le + ri) / 2;
    if (check(mid)) {
      ans = mid;
      ri = mid - 1;
    } else {
      le = mid + 1;
    }
  }
  printf("%lld\n", ans);
  check(ans, true);
  return 0;
}

