Cài đặt cấu trúc cây tìm kiếm nhị phân với khóa là số nguyên. Cho trước các khai báo và các phép toán như sau:

```
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

Hãy viết hàm

keyType maxBST(BST T)
trả về khóa lớn nhất trên cây TKNP T
