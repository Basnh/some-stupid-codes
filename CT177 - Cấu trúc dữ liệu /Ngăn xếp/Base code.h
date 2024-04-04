#define Maxlength 50
typedef int ElementType;
typedef struct {
  ElementType Elements[Maxlength];
  int top_index;
}Stack;

void MakeNull_Stack (Stack *S)                        //* hàm tạo rỗng ngăn xếp S
int Empty_Stack (Stack S)                             //* hàm trả về 1 nếu ngăn xếp S rỗng, ngược lại trả về 0
int Full_Stack (Stack S)                              //* hàm trả về 1 nếu ngăn xếp S đầy, ngược lại trả về 0
ElementType Top (Stack S)                             //* hàm trả về nội dung trên đỉnh 
void Pop (Stack *S)                                   //* hàm xóa phần tử trên đỉnh ngăn xếp S
void Push (ElementType X, Stack *S)                   //* hàm thêm phần tử X vào đỉnh ngăn xếp S

void MakeNull_Stack (Stack *S){
  S->top_index = Maxlength;
}

int Empty_Stack (Stack S){
  return S.top_index == Maxlength;
}

int Full_Stack (Stack S){
  return S.top_index == 0;
}

ElementType Top (Stack S){
  return S.Elements[S.top_index];
}

void Pop (Stack *S){
  S->top_index = S->top_index +1;
}

void Push (ElementType X, Stack *S){
  S->top_index = S->top_index -1;
  S->Elements[S->top_index] = X;
}
