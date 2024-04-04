int Count_List (ElementType X, List L)                    //* hàm đếm số lần xuất hiện của X trong danh sách L
void Sort_List (List *L)                                  //* hàm sắp xếp danh sách L theo thứ tự không giảm
ElementType Max_List (List L)                             //* hàm trả về giá trị lớn nhất trong các phần tử của danh sách L
ElementType Min_List (List L)                             //* hàm trả về giá trị nhỏ nhất trong các phần tử của danh sách L
int Sum_List (List L)                                     //* hàm trả về tổng giá trị các phần tử trong danh sách L

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

ElementType Max_List(List L){
    Position P;
    int max = 0;
    for (P = First_List(L); P<End_List(L); P++){
        if (Retrieve(P, L) > max){
            max = Retrieve(P, L);
        }
    }
    return max;
}

ElementType Min_List(List L){
    Position P;
    int min = Retrieve(First_List(L), L);
    for (P = First_List(L); P<End_List(L); P++){
        if (Retrieve(P, L) < min){
            min = Retrieve(P, L);
        }
    }
    return min;
}

ElementType Sum_List(List L){
    Position P;
    int sum = 0;
    for (P = First_List(L); P<End_List(L); P++){
        sum += Retrieve(P, L);
        }
    return sum;
}
