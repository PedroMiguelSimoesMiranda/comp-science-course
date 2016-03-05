//exercicio A


#include <stdio.h>
extern int quadrado (int n );
int main(){
     int n;
     printf("Introduza n:" );
     scanf( "%d", &n );
     printf( "quadrado(%d) = %d\n", n, quadrado(n));
     return 0;
}

