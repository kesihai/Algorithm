#include<iostream>
#include<cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <cmath>
using namespace std;

const int N = 1005;
const double esp = 1e-6;

double c;

int main() {
  scanf("%lf", &c);
  double le = 0, ri = c;
  while (ri - le >= esp) {
    double mid = (le + ri) / 2;
    if (mid * mid + sqrt(mid) >= c) {
      ri = mid;
    } else {
      le = mid;
    }
  }
  printf("%lf", le);
  return 0;
}

