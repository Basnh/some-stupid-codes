int Count_List (ElementType X, List L)                    //* hàm đếm số lần xuất hiện của X trong danh sách L
void Sort_List (List *L)                                  //* hàm sắp xếp danh sách L theo thứ tự không giảm



int Count_List(ElementType X, List L){
    Position P;
    int count = 0;
    for (P = First_List(L); P<End_List(L); P++){
        if (Retrieve(P,L) == X){
            count++;
        }
    }
    return count;
}

void Sort_List(List *L) {
    for (Position i = First_List(*L); i < End_List(*L) - 1; ++i) {
        for (Position j = i + 1; j < End_List(*L); ++j) {
            if (L->Elements[i - 1] > L->Elements[j - 1]) {
                Swap(i, j, L);
            }
        }
    }
}
