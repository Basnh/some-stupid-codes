#include <stdio.h>
#include "Queue.c"

int main(){
	int n;
	scanf("%d", &n);
	Queue jobs, prio;
	MakeNull_Queue(&jobs);
	MakeNull_Queue(&prio);
	for(int i = 0; i < n; i++){
		int x;
		scanf("%d", &x);
		EnQueue(x, &jobs);
	}
	for(int i = 0; i < n; i++){
		int x;
		scanf("%d", &x);
		EnQueue(x, &prio);
	}
	int time = 0;
	while(!Empty_Queue(jobs)){
		if(Front(jobs) != Front(prio))
			EnQueue(Front(jobs), &jobs);		
		else DeQueue(&prio);
		DeQueue(&jobs);
		time++;
	}
	printf("%d", time);
}
