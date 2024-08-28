#include <stdio.h>
#define maxv 50
#define idx 100
typedef struct{
    int matrix[maxv][maxv];
    int n;
}Graph;

void initGraph(Graph *g, int n){
    g->n=n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            g->matrix[i][j]=0;
        }
    }
}

void addGraph(Graph *g, int x, int y){
    g->matrix[x][y]=1;
    g->matrix[y][x]=1;
}

int degree(Graph g, int x){
    int count=0;
    for(int i=1;i<=g.n;i++){
        if(g.matrix[x][i]==1) count++;
    }
    return count;
}

int main(){
    Graph g;
	int n, m, u, v;
	scanf("%d%d", &n, &m);
	initGraph(&g, n);
	for (int e = 0; e < m; e++) {
		scanf("%d%d", &u, &v);
		addGraph(&g, u, v);
	}

	int max=0,index=0;
	for(int i=1;i<=g.n;i++){
	    int temp = degree(g,i);
	    if(temp > max){
	        max = temp;
	        index = i;
	    }
	}
    
	printf("%d %d\n",index,max);
    return 0;
}
