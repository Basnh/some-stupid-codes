double Average (List L)                          //* hàm trả về trung bình cộng của các phần tử trong danh sách L
void DeleteX (ElementType X, List *L)            //* hàm xóa tất cả phần tử có giá trị bằng X trong danh sách L



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
