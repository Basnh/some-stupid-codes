typedef int DataType;
struct Node{
  DataType data;
  struct Node *left, *right;
};
typedef struct Node* Tree;

void MakeNull_Tree (Tree *T)                            //* hàm tạo rỗng cây T
int Empty_Tree (Tree T)                                 //* hàm trả về 1 nếu cây T rỗng, ngược lại trả về 0
Tree LeftChild (Tree N)                                 //* hàm trả về con trái của nút N
Tree RightChild (Tree N)                                //* hàm trả về con phải của nút N
void NLR (Tree T)                                       //* hàm in ra màn hình biểu thức duyệt tiền tự cây T
void LNR (Tree T)                                       //* hàm in ra màn hình biểu thức duyệt trung tự cây T
void LRN (Tree T)                                       //* hàm in ra màn hình biểu thức duyệt hậu tự cây T


void MakeNull_Tree (Tree *T){
  (*T) = NULL;
}

int Empty_Tree (Tree T){
  return T == NULL;
}

Tree LeftChild (Tree N){
  if (N != NULL) return N->left;
  else return NULL;
}

Tree RightChild (Tree N){
  if (N != NULL) return N->right;
  else return NULL;

void NLR (Tree T){
    if (T != NULL){
        printf ("%d ", T->data);
        NLR(T->left);
        NLR(T->right);
    }
}

void LNR(Tree T){
    if (T != NULL){
        LNR(T->left);
        printf ("%d ", T->data);
        LNR (T->right);
    }
}

void LRN(Tree T){
    if (T != NULL){
        LRN(T->left);
        LRN(T->right);
        printf ("%d ", T->data);
    }
}


    
