int edgeCount(Graph *G){
    int i,j, count = 0;
    for (i = 1; i<=G->n; i++){
        for (j=1; j<=G->n; j++){
        if(G->A[i][j] != 0) count = count + G->A[i][j];
    }
    }
    return count/2;
}
