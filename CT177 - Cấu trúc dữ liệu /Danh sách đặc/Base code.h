#define Maxlength 50
typedef int ElementType;
typedef int Position;
typedef struct {
  ElementType Elements[Maxlength];
  Position    Last;
}List;


void makeNull_List (List *L);                             //* hàm tạo danh sách rỗng
int Empty_List (List L)                                   //* hàm trả về 1 nếu là danh sách rỗng, ngược lại trả về 0
Position First_List (List L)                              //* hàm trả về vị trí phần tử đầu tiên trong danh sách L
Position End_List (List L)                                //* hàm trả về vị trí phần tử cuối trong danh sách L
Position Next (Position P, List L)                        //* hàm trả về vị trí sau vị trí P trong danh sách L
void Insert_List (ElementType X, Position P, List *L)     //* hàm xen phần tử X vào vị trí P trong danh sách L
void Delete_List (Position P, List *L)                    //* hàm xóa phần tử tại vị trí P trong danh sách L
void Read_List (int n, List *L)                           //* hàm đọc n số nguyên, lần lượt thêm vào danh sách L theo thứ tự đó
void Print_List (List L)                                  //* hàm in ra nội dung danh sách L, giữa 2 phần tử cách nhau một khoảng trắng
ElementType Retrieve(Position P, List L)                  //* hàm trả về nội dung phần tử tại vị trí P trong danh sách L
Position Locate(ElementType X, List L)                    //* hàm trả về vị trí đầu tiên tìm thấy X trong trong danh sách L. Nếu không tìm thấy, trả về End_List




void makeNull_List(List *L){
    L->Last = 0;
}

int Empty_List (List L){
    return (L.Last == 0) ? 1 : 0;
}

Position First_List (List L){
    return 1;
}

Position End_List (List L){
    return L.Last+1;
}

Position Next(Position P, List L) {
  return P+1;
}

void Insert_List (ElementType X, Position P,List *L){
    Position Q;
    for (Q = L->Last; Q>P-1; Q--){
        L->Elements[Q] = L->Elements[Q-1];}
        L->Elements[P-1] = X;
        L->Last++;
}

void Delete_List ( Position P,List *L){
    Position Q;
    for (Q=P; Q<L->Last; Q++){
        L->Elements[Q-1] = L->Elements[Q];
    }
    L->Last--;
}

void Print_List (List L){
    Position P;
    for (P = First_List(L); P<End_List(L); P++)
    {
        printf("%d ",L.Elements[P-1]);
    }
}
void Read_List (int n, List *L){
    int x;
    for (int i = 1; i<=n; i++){
        scanf("%d", &x);
        Insert_List(x, i, L);
    }
}

ElementType Retrieve (Position P,List L){
    return L.Elements[P-1];
}

Position Locate (ElementType X,List L){
    Position(P) = 1;
    while (P!= L.Last+1){
        if (L.Elements[P-1] == X)
        return P;
        else P++;
    }
    return P;
}









