#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 205;
const double esp = 1e-6;

int a[N], n, k;

bool check(int mid) {
  int index = n - 1; 
  for (int i = 0; i < mid; i++) {
    
  }
}

int main() {
  scanf("%d%d", &k, &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
  }
  sort(a, a + n);
  int le = 0, ri = n;
  int ans = 0;
  while (le <= ri) {
    mid = (le + ri) >> 1;
    if (check(mid))) {
      ans = mid;
      le = mid + 1;
    } else {
      ri = mid - 1;
    }
  }
  printf("%d\n", ans);
  return 0;
}

