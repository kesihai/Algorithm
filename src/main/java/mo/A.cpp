#include <bits/stdc++.h>
using namespace std;

const int M = 1e6+5;
const int N = 30005;
int cnt[M], a[N];
int block = 1;
struct Node {
  int le, ri;
  int pos;
  bool operator < (const Node& a) const {
    if (le / block != a.le / block) {
      return le / block < a.le / block;
    }
    return ri < a.ri;
  }
}q[200005];
int ans[200005];
int n, m;
int cur = 0;

void add(int pos) {
  if (pos == 0) return;
  pos = a[pos];
  cnt[pos]++;
  if (cnt[pos] == 1) cur++;
}

void del(int pos) {
  if (pos == 0) return;
  pos = a[pos];
  cnt[pos]--;
  if (cnt[pos] == 0) cur--;
}

int main() {
  scanf("%d", &n);
  block = sqrt(n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", a + i);
  }
  scanf("%d", &m);
  for (int i = 0; i < m; i++) {
    scanf("%d%d", &q[i].le, &q[i].ri);
    q[i].pos = i;
  }

  sort(q, q + m);
  int le = 0, ri = 0; 
  for (int i = 0; i < m; i++) {
    while (le < q[i].le) del(le++);
    while (le > q[i].le) add(--le);
    while (ri < q[i].ri) add(++ri);
    while (ri > q[i].ri) del(ri--);
    ans[q[i].pos] = cur;
    //printf("%d [%d %d] ->  %d\n",q[i].pos, q[i].le, q[i].ri, cur);
  }
  for (int i = 0; i < m; i++) {
    printf("%d\n", ans[i]);
  }
  return 0;
}
