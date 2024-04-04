Giả sử kiểu dữ liệu ngăn xếp Stack đã được khai báo. Các phép toán cơ bản trên ngăn xếp được hỗ trợ trong file thư viện AStack.c

```
void makenullStack(Stack *pS)
int emptyStack(Stack S)
ElementType top(Stack S)
void pop(Stack *pS)
void push(ElementType x, Stack *pS)  //ElementType là kiểu số thực
```
Bằng cách sử dụng kiểu dữ liệu trừu tượng Stack đã cho, hãy viết chương trình tính giá trị của một biểu thức hậu tố.

Dữ liệu đầu vào là 1 dòng duy nhất ghi chuỗi biểu thức hậu tố. Các ký tự cho phép gồm: số ('0' - '9'), các ký tự phép toán hai ngôi ('+', '-', '*', '/') và có thể có các ký tự khoảng trắng. Ví dụ: chuỗi biểu diễn cho biểu thức hậu tố 3 4 + 2 / có thể là 34+2/ hoặc 3 4 + 2 /

Dữ liệu đầu ra là 1 dòng duy nhất ghi giá trị biểu thức ở dạng số thực (lấy 2 số lẻ).
