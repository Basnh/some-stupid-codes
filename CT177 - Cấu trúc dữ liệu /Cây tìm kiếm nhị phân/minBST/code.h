keyType minBST (BST T){
    if (T == NULL) return -1;
    while (T->left != NULL){
        T = T->left;
    }
    return T->key;
}
