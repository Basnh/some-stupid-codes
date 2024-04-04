typedef int ElementType;
struct Node{
 ElementType Element;
 struct Node* Next;
};
typedef struct Node*    Position;
typedef Position List;

void MakeNull_List (List *L)                              //* hàm tạo rỗng danh sách L
int Empty_List (List L)                                   //* hàm trả về 1 nếu L là danh sách rỗng, ngược lại trả về 0
Position First_List (List L)                              //* hàm trả về vị trí của phần tử đầu tiên trong danh sách L
Position End_List (List L)                                //* hàm trả về vị trí sau phần tử cuối trong danh sách L
Position Next (Position p, List L)                        //* hàm trả về vị trí sau vị trí p trong danh sách L
ElementType Retriveve (Position p, List L)                //* hàm trả về giá trị phần tử tại vị trí p trong danh sách L
void Insert_List (ElementType X, Position p, List *L)     //* hàm xen phần tử X vào vị trí p trong danh sách L







void MakeNull_List(List* L) {
 	(*L) = (struct Node*)malloc(sizeof(struct Node));
	(*L)->Next = NULL;
}

int Empty_List(List L){
    return (L->Next == NULL);
}

Position First_List (List L){
  return L;
}

Position End_List (List L){
    Position C = L;
    while(C->Next!=NULL){
		C =C->Next;
	}
	return C;
}

Position Next (Position p, List L){
  return p != NULL ? p->Next : NULL;
}

ElementType Retrieve (Position p, List L){
  return p->Next->Element;
}

void Insert_List (ElementType X, Position p, List *L){
  Position T = (Position)

