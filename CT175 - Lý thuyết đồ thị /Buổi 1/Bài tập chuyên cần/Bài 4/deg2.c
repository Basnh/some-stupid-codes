int deg(Graph* G, int x){
    int i, bac = 0;
    for (i = 1; i<= G->n; i++){
        if (G->A[x][i] != 0) bac++;
    }
    return bac;
}

int deg2(Graph *G){
    int i, count = 0;
    for (i = 1; i<=G->n; i++){
        if(deg(G,i)%2 == 0) count++;
    }
    return count;
}
