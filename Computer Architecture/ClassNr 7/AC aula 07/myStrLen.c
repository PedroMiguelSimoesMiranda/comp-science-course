//exercicio B


#include <stdio.h>
extern int myStrLen (char * s);


int main(){
    char st[100];
     printf("Introduza a string:" );
     scanf( "%s", st ); // qd e string n e necessario &
     printf( "tamanho(%s) = %d\n", st, myStrLen(st));
     return 0;
}

