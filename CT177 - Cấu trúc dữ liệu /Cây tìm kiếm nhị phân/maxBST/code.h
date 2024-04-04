keyType maxBST(BST T){
    if (T == NULL) return -1;
    while (T->right != NULL){
        T = T->right;
    }
    return T->key;
}
