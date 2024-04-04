void dec2bin(int num, Stack *S)                      //* hàm đổi số thập phân num ra dạng nhị phân, kết quả lưu trong S
int ktngoac(char st[])                               //* hàm kiểm tra chuỗi dấu ngoặc st, nếu hợp lệ trả về 1, ngược lại trả về 0




void dec2bin (int num, Stack *S){
  if (num == 0) printf ("0");
  else while (num !=0){
      Push (num%2, S);
      num /= 2;
  }
}

int ktngoac(char st[]) {
    Stack stack;
    MakeNull_Stack(&stack);

    int i = 0;
    while (st[i] != '\0') {
        if (st[i] == '(') {
            Push(st[i], &stack);
        } else if (st[i] == ')') {
            if (Empty_Stack(stack)) {
                return 0; 
            Pop(&stack);
        }
        i++;
    }
    return Empty_Stack(stack); 
}
