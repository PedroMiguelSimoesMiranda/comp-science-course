#include <stdio.h>
extern binario (char  s[]);


int main(){

     char s[100];
     printf("Introduza o numero:" );
     scanf( "%s",s );
     char a=binario(s);
     if(a==0)
     	printf( "binario = false\n");
     else
	printf("binario = %d\n",a);
     return 0;
}
