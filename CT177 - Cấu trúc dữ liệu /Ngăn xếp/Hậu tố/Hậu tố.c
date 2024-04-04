#include <stdio.h>
#include "AStack.c"

float hauTo(char s[], Stack S){
    int i = 0;
    makenullStack(&S);
    float c = 0, d = 0;
    while (s[i] != '\0'){
        if (((int)s[i] - 48) >=0 && ((int)s[i] - 48)<= 9)
            push((int)s[i] - 48, &S);
        else if (s[i] == '+'|| s[i] == '-'|| s[i] == '*' || s[i] == '/' ){
            c = top(S);
            pop(&S);
            d = top(S);
            pop(&S);
            int a = (int)s[i];
            switch (a){
                case '+':
                    push(d+c, &S);
                    break;
                case '-':
                    push (d-c, &S);
                    break;
                case '*':
                    push (d*c, &S);
                    break;
                case '/':
                    push (d/c, &S);
                    break;
                default:
                    break;
            }
        }
        else {
            i++;
            continue;
        }
        i++;
    }
    return top(S);
}

int main(){
    Stack S;
    char s[100];
    fgets (s,99,stdin);
    float x = hauTo(s,S);
    printf ("%.2f", x);
    return 0;
}
