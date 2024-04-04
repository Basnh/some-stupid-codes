int negNodes (Tree T)                            //* hàm trả về kết quả là số lượng nút số âm của cây T
int nbNodes (Tree T)                             //* hàm trả về kết quả là số lượng nút của cây T


int negNodes (Tree T){
    int count = 0;
    if (Empty_Tree(T)) return count;
    else {
        if (T->data < 0) {
            count ++;
        }
        count += negNodes(T->left);
        count += negNodes (T->right);
    }
    return count;

  int nbNodes (Tree T){
    if (Empty_Tree(T)) return 0;
    else return 1 + nbNodes(T->left) + nbNodes(T->right);
}
}

