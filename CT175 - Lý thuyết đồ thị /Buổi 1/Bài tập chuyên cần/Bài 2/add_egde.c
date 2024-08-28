void add_edge(Graph *G, int x, int y){
	if(x < 1 || x > G->n || y < 1 || y > G->n){
		return;
	}
	G->edges[G->m].x = x;
	G->edges[G->m].y = y;
	G->m++;
}
