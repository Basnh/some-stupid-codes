#include <stdio.h>
#define Maxlength 50
typedef int ElementType;
typedef struct {
  ElementType Elements[Maxlength];
  int front, rear;
}Queue;

void MakeNull_Queue (Queue *Q)                                        //* hàm tạo rỗng hàng đợi Q
int Empty_Queue (Queue Q)                                             //* hàm trả về 1 nếu hàng đợi rỗng, ngược lại trả về 0
int Full_Queue (Queue Q)                                              //* hàm trả về 1 nếu hàng đợi đầy, ngược lại trả về 0
ElementType Front (Queue Q)                                           //* hàm trả về nội dung đầu của hàng đợi
void DeQueue (Queue *Q)                                               //* hàm xóa phần tử cuối cùng của hàng đợi
void EnQueue (Queue *Q)                                               //* hàm thêm phần tử vào vị trí cuối cùng của hàng đợi


void MakeNull_Queue (Queueu *Q){
  Q->front = -1;
  Q->rear = -1;
}

int Empty_Queue (Queue Q){
    return ((Q.front = -1) && (Q.rear = -1)) ? 1 : 0;
}

int Full_Queue (Queue Q){
    return (Q.front = Q.rear) ? 1 : 0;
}

ElementType Front (Queue Q){
  return Q.Elements[Q.front];
}

void DeQueue (Queue *Q){
  Q->front = (Q->front + 1) % Maxlength;  
}

void EnQueue (ElementType X, Queue *Q){
    if (Empty_Queue(*Q)) Q->front = 0;
    Q->rear = (Q->rear+1) % Maxlength;
    Q->Elements[Q->rear] = X;
}








