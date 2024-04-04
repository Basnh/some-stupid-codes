double Average (List L)                          //* hàm trả về trung bình cộng của các phần tử trong danh sách L
void DeleteX (ElementType X, List *L)            //* hàm xóa tất cả phần tử có giá trị bằng X trong danh sách L
void filter (List *L)                            //* hàm xóa các phần tử trùng nhau trong danh sách L
ElementType Max2_List (List L)                   //* hàm trả về giá trị lớn thứ hai trong các phần tử của danh sách L
ElementType Min2_List (List L)                   //* hàm trả về giá trị nhỏ thứ hai trong các phần tử của danh sách L

double Average (List L){
  if (L == NULL) {
      return 0.0;
  }
  double sum = 0.0;
  int count = 0;

  Position current = L;
  while (current != End_List(L)) {
      sum += Retrieve (current, L);
      count ++;
      current = Next (current, L);
  }
return sum / count;

void DeleteX(ElementType X, List *L) {
    Position P = *L;
    Position Pre = NULL;

    while (P != NULL) {
        if (P->Element == X) {
            if (Pre == NULL) {
                *L = P->Next;
                free(P);
                P = *L;
            } else {
                Pre->Next = P->Next;
                free(P);
                P = Pre->Next;
            }
        } else {
            Pre = P;
            P = P->Next;
        }
    }
}

void filter(List *L) {
    Position current = First_List(*L);

    while (current != End_List(*L)) {
        ElementType currentElement = Retrieve(current, *L);
        Position next = Next(current, *L);

        while (next != End_List(*L)) {
            ElementType nextElement = Retrieve(next, *L);
            if (currentElement == nextElement) {
                Delete_List(next, L);
            } else {
                next = Next(next, *L);
            }
        }
        current = Next(current, *L);
    }
}

ElementType Max2_List(List L){
    Position P;
    int max = Retrieve(First_List(L), L);
    int max2 = 0;
    for (P = First_List(L); P<End_List(L); P++){
        if (Retrieve(P, L) > max){
                max2 = max;
                max = Retrieve(P, L);
        }
    }
    return max2;
}

ElementType Min2_List(List L){
    Position P;
    int min = Retrieve(First_List(L), L);
    int min2 = Retrieve(Next(First_List(L), L), L);
    for (P = First_List(L); P<End_List(L); P++){
        if (Retrieve(P, L) < min){
                min2 = min;
                min = Retrieve(P, L);
        }
    }
    return min2;
}
