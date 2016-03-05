/*aula 03 APONTADORES*/

// EXERCICIO 01


#include <stdio.h>
int main()
{
int i1 = -1;
int i2 = -2;
int i3 = -3;
int *pi1 = &i1;
int *pi2 = &i2;
int *pi3 = &i3;
*pi1 = -4;
*pi2 = *pi1;
pi2 = pi1;
pi3 = pi2;
*pi3 = -5;
printf("%d\n%d\n%d\n", i1, i2, i3);
return 0;
}

/*OUTPUT:*/

	/* -> */ i1 = -5 

	/* -> */ i2 = -4

	/* -> */ i3 = -3

