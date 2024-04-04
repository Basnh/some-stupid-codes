#include "BSTLib.c"
#include <stdio.h>

int main(){
    BST T;
    makeNullBST(&T);
    int n;
    scanf ("%d", &n);
    for (int i = 0; i<=n; i++){
        keyType key;
        scanf("%d ", &key);
        insertNode(key, &T);
    }
    LNR(T);
    printf ("\n");
}
