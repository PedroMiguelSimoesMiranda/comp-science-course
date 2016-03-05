#include<stdio.h>
/*aula exercicio01*/ 
int a[2];
a[0]=2:
a[1]=0;

int main(){

	a[0] == a[1]; // false
	!a[0] || a[1]; // not a[0] or a[1] -> true
	*a; // valor de a[0]
	*a+1; // valor de a[0] + inteiro 1
	*( a+1 );  // retorna o valor de a[1]
	(*a) * (*(a+1)); // a[0] * a[1]
 	!!*a; // *a valor de a[0]
	a[0] = a[1]; //  a[0] = 0

return 0;
}
