#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 200005;
const double esp = 1e-6;

char a[N], b[N];
int c[N], v[N];

int n, m;

bool check(int mid) {
  memset(v, 0, sizeof(v));
  for (int i = 0; i <= mid; i++) {
    v[c[i]] = 1;
  }
  int aLen = 0, bLen = 0;
  while (bLen < m) {
    //printf("test :%d %d %d\n", mid, aLen, bLen);
    if (aLen >= n){
      return false;
    } 
    if (v[aLen] == 1) {
      aLen++;
      continue;
    }
    if (a[aLen] == b[bLen]) {
      aLen++;
      bLen++;
    } else {
      aLen++;
    }
  }
  return true;
}

int main() {
  scanf("%s%s", a, b);
  n = strlen(a);
  m = strlen(b);
  for (int i = 0; i < n; i++) {
    scanf("%d", c + i);
    c[i]--;
  }
  int le = 0, ri = n - 1;
  int ans = -1;
  //printf("%d %d\n", n, m);
  while (le <= ri) {
    int mid  = (le + ri) >> 1;
    if (check(mid)) {
      ans = mid;
      le = mid + 1;
    } else {
      ri = mid - 1;
    }
  }
  printf("%d\n", ans + 1);
  return 0;
}

