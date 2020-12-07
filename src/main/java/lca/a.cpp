#include <cstdio>
#include <algorithm>
using namespace std;
typedef struct edge
{
    int to;
    edge *next;
} edge;
edge *last[100005], *g[100005], *b[100005];
int dfn[100005], low[100005], n, m, num, btch, belone[100005], st[100005], top, Q, u, v, ans, bin[100005], level[100005];
bool flag, vis[100005], iscut[100005];
void tarjan(int i, int fa)
{
    dfn[i] = low[i] = ++num;
    st[top++] = i;
    int flag = 1;
    last[i] = g[i];
    while (last[i])
    {
        int v = last[i]->to;
        last[i] = last[i]->next;
        if (fa == v && flag)
        {
            flag = 0;
            continue;
        }
        if (dfn[v] == -1)
        {
            tarjan(v, i);
            low[i] = min(low[v], low[i]);
            if (dfn[i] < low[v])
            {
                int x;
                btch++;
                do
                {
                    x = st[--top];
                    belone[x] = btch;
                } while (x != v);
            }
        }
        else
        {
            low[i] = min(low[i], dfn[v]);
        }
    }
}
void dfs(int u, int d)
{
    int i;
    level[u] = d;
    vis[u] = true;
    last[u] = b[u];
    while (last[u] != NULL)
    {
        int v = last[u]->to;
        if (!vis[v])
        {
            bin[v] = u;
            iscut[v] = true;
            dfs(v, d + 1);
        }
        last[u] = last[u]->next;
    }
}
void tarjan_lca(int u, int v)
{
    while (level[u] > level[v])
    {
        if (iscut[u])
        {
            ans--;
            iscut[u] = false;
        }
        u = bin[u];
    }
    while (level[v] > level[u])
    {
        if (iscut[v])
        {
            ans--;
            iscut[v] = false;
        }
        v = bin[v];
    }
    while (u != v)
    {
        if (iscut[u])
        {
            ans--;
            iscut[u] = 0;
        }
        if (iscut[v])
        {
            ans--;
            iscut[v] = 0;
        }
        u = bin[u];
        v = bin[v];
    }
}
int main()
{
    int i, j, x, y, t = 1;
    edge *p;
    while (scanf("%d %d", &n, &m) && (m + n))
    {
        top = num = btch = 0;
        for (i = 0; i <= n; i++)
        {
            dfn[i] = -1;
            g[i] = b[i] = NULL;
            belone[i] = 0;
            bin[i] = i;
            vis[i] = false;
        }
        for (i = 0; i < m; i++)
        {
            scanf("%d %d", &x, &y);
            p = (edge *)malloc(sizeof(edge));
            p->to = y;
            p->next = NULL;
            if (g[x] == NULL)
            {
                last[x] = g[x] = p;
            }
            else
            {
                last[x]->next = p;
                last[x] = last[x]->next;
            }
            p = (edge *)malloc(sizeof(edge));
            p->to = x;
            p->next = NULL;
            if (g[y] == NULL)
            {
                last[y] = g[y] = p;
            }
            else
            {
                last[y]->next = p;
                last[y] = last[y]->next;
            }
        }
        /*for(i=1;i<=n;i++)
{
printf("%d :",i);
last[i]=g[i];
while(last[i])
{
printf(" %d",last[i]->to);
last[i]=last[i]->next;
}
printf("\n");
}*/
        tarjan(1, 0); //边双连通缩点
        edge *q;
        for (i = 1; i <= n; i++) //建图
        {
            p = g[i];
            while (p != NULL)
            {
                int v = p->to;
                if (belone[i] != belone[v])
                {
                    q = (edge *)malloc(sizeof(edge));
                    q->to = belone[v];
                    q->next = NULL;
                    if (b[belone[i]] == NULL)
                    {
                        last[belone[i]] = b[belone[i]] = q;
                    }
                    else
                    {
                        last[belone[i]]->next = q;
                        last[belone[i]] = last[belone[i]]->next;
                    }
                }
                p = p->next;
            }
        }
        /*for(i=0;i<=btch;i++)
{
printf("%d :",i);
last[i]=b[i];
while(last[i])
{
printf(" %d",last[i]->to);
last[i]=last[i]->next;
}
printf("\n");
}*/
        ans = btch;
        dfs(0, 0);
        printf("Case %d:\n", t++);
        scanf("%d", &Q);
        for (i = 0; i < Q; i++)
        {
            flag = false;
            scanf("%d %d", &u, &v);
            if (belone[u] == belone[v])
                printf("%d\n", ans);
            else
            {
                tarjan_lca(belone[u], belone[v]);
                printf("%d\n", ans);
            }
        }
        printf("\n");
    }
    return 0;
}