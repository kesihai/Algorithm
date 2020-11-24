#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;
#define pb push_back

const int N = (int)5e5 + 10;
int n, m, tot, tim, top, scc, ans; //点，边，链式前向星，时间戳，栈，连通数
int head[N], dfn[N], low[N], scc_no[N], s[N], fa[N], boss[N];
//链式前向星，dfn，low，联通块编号，栈，父节点，源点
struct node
{
    int to;
    int nxt;
} e[N << 1];
struct _cut
{
    int x, y;
};
vector<_cut> cut; //桥
vector<int> poi;  //lca

void init()
{
    for (int i = 0; i <= n; ++i)
    {
        head[i] = -1;
        dfn[i] = 0;
    }
    cut.clear();
    scc = tim = tot = 0;
}

inline void add(int u, int v)
{
    e[tot].to = v;
    e[tot].nxt = head[u];
    head[u] = tot++;
}

void tarjan(int now, int pre)
{
    dfn[now] = low[now] = ++tim;
    s[top++] = now;

    int to, pre_cnt = 0;
    for (int o = head[now]; ~o; o = e[o].nxt)
    {
        to = e[o].to;
        if (to == pre && pre_cnt == 0)
        {
            pre_cnt = 1;
            continue;
        }
        if (!dfn[to])
        {
            tarjan(to, now);
            low[now] = min(low[now], low[to]);
            if (dfn[now] < low[to])
                cut.pb(_cut{now, to});
        }
        else if (low[now] > dfn[to])
            low[now] = dfn[to];
    }

    if (dfn[now] == low[now])
    {
        int p;
        ++scc;
        fa[now] = now;
        boss[scc] = now; //记录该scc的源点
        do
        {
            p = s[--top];
            scc_no[p] = scc;
        } while (now != p);
    }
}
inline int _boss(int x)
{
    return boss[scc_no[x]];
}
//重建图   boss进行并查集
void rebuild()
{
    ans = cut.size();
    int x, y;
    for (int i = 0; i < ans; ++i)
    {
        x = _boss(cut[i].x);
        y = _boss(cut[i].y);
        //dfn上小，下大的树
        if (dfn[x] > dfn[y])
            swap(x, y);
        fa[y] = x;
    }
}

void lca(int x, int y)
{
    int fax = _boss(x);
    int fay = _boss(y);
    if (dfn[fax] == dfn[y])
        return;

    poi.pb(fax);
    poi.pb(fay);
    while (dfn[fax] != dfn[fay])
    {
        while (dfn[fax] > dfn[fay])
        {
            --ans;
            fax = fa[fax];
            poi.pb(fax);
        }
        while (dfn[fax] < dfn[fay])
        {
            --ans;
            fay = fa[fay];
            poi.pb(fay);
        }
    }

    int n = poi.size(); //所有boss点dfn改变为祖先的dfn
    for (int i = 0; i < n; ++i)
        dfn[poi[i]] = dfn[fax];
    poi.clear();
}

int main()
{

    int _case = 0;
    while (~scanf("%d%d", &n, &m) && (n + m))
    {
        init();
        int u, v;
        for (int i = 0; i < m; ++i)
        {
            scanf("%d%d", &u, &v);
            add(u, v);
            add(v, u);
        }

        tarjan(1, 1);
        rebuild();

        int q;
        scanf("%d", &q);
        printf("Case %d:n", ++_case);
        while (q--)
        {
            scanf("%d%d", &u, &v);
            lca(u, v);
            printf("%dn", ans);
        }
    }

    return 0;
}