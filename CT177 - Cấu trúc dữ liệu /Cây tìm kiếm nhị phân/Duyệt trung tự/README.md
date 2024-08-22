Cài đặt cấu trúc cây tìm kiếm nhị phân với khóa là số nguyên. Cho trước các khai báo và các phép toán trong tập tin **BSTLib.c** như sau:
```ruby
typedef int keyType;
struct node{
 keyType key;
 struct node *left, *right;
};
typedef struct node* BST;


void makeNullBST(BST *T)
int emptyBST(BST T)
BST leftChild(BST N)
BST rightChild(BST N)
void insertNode(keyType X, BST *Root)
BST search(keyType X, BST Root)
void deleteNode(keyType X, BST *Root)
void LNR(BST T)
```
Hãy viết chương trình nhận vào dãy n khóa. Xây dựng cây TKNP bằng cách xen lần lượt các khóa vào cây. In ra màn hình biểu thức duyệt trung tự của cây.

Dữ liệu đầu vào gồm 2 dòng:

Dòng 1: ghi số nguyên n là số lượng phần tử của dãy khóa

Dòng 2: gồm n số nguyên (hai số cách nhau ít nhất 1 khoảng trắng) là dãy khóa cho trước

Dữ liệu đầu ra: một dòng duy nhất ghi biểu thức duyệt trung tự của cây TKNP trên.
