#include <stdio.h>
extern cifra_cesar (char  s[] , int n );


int main(){

     char s[100];
     int n;
     printf("Introduza a frase:" );
     scanf( "%s",s );
     printf("Introduza a numero:" );
     scanf( "%d",&n );
     cifra_cesar(s,n);
     printf( "cifra(s) = %s\n",s);
     return 0;
}
