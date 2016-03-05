#include <stdio.h>
extern mystrlen (char  n[] );


int main(){

     char n[100];
     printf("Introduza n:" );
     scanf( "%s",n );
     printf( "tamanho(s) = %d\n", mystrlen(n));
     return 0;
}
