#include <stdio.h>

typedef struct {
    int u, v;    
} Edge;

typedef struct{
    int n, m;
    Edge edges[500];
} Graph;

void init_graph(Graph *G, int n){
    G->n = n;
    G->m = 0;
}

void add_edge(Graph *G, int u, int v){
    G->edges[G->m].u = u;
    G->edges[G->m].v = v;
    G->m++;
}

void print_graph(Graph *G) {
    int A[G->n][G->n];
    for(int i = 1; i <= G->n; i++){
        for(int j = 1; j <= G->n; j++){
            A[i][j] = 0;
        }        
    }    
    for(int i = 0; i < G->m; i++){
        A[G->edges[i].u][G->edges[i].v] = 1;
        A[G->edges[i].v][G->edges[i].u] = 1;
    }    
    for(int i = 1; i <= G->n; i++){
        for(int j = 1; j <= G->n; j++){
            printf("%d ", A[i][j]);
        }
        printf("\n");
    }
}

int main(){
    freopen("dt1.txt", "r", stdin);
    Graph G;
    int n, m;
    scanf("%d %d", &n, &m);
    init_graph(&G, n);
    for(int i = 0; i < m; i++){
        int u, v;
        scanf("%d %d", &u, &v);
        add_edge(&G, u, v);
    }
    print_graph(&G);
}
