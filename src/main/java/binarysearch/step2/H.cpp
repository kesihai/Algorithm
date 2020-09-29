#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 205;
const double esp = 1e-6;

long long pb, ps, pc;
long long nb, ns, nc;
long long mb, ms, mc;
long long total;
long long money;
char a[N];

bool check(long long mid) {
  long long total = 0;
  total += max(0LL, mid * pb - nb) * mb;
  if (total > money) {
    return false;
  }

  total += max(0LL, mid * pc - nc) * mc;
  if (total > money) {
    return false;
  }

  total += max(0LL, mid * ps - ns) * ms;
  if (total > money) {
    return false;
  }
  return true;
}

int main() {
  scanf("%s", a);
  pb = ps = pc = 0;
  int len = strlen(a);
  for (int i = 0; i < len; i++) {
    if (a[i] == 'B') pb++;
    if (a[i] == 'S') ps++;
    if (a[i] == 'C') pc++; 
  }
  scanf("%lld%lld%lld", &nb, &ns, &nc);
  scanf("%lld%lld%lld", &mb, &ms, &mc);
  scanf("%lld", &money);
  long long le = 0;
  long long ri = max(max(money / mb + nb, money / ms + ns), money / mc + nc);
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

