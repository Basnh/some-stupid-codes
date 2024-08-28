int deg(Graph *G, int x) {
    int count=0;
    for(int i=1;i<=G->n;i++){
        if(G->A[i][x]==1){
            count++;
        }
    }
    return count;
}
