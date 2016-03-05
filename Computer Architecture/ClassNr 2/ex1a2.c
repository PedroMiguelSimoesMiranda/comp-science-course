#include <stdio.h>

// ASCII

// AULA 02 EXERCICIO 01


int main()
{
	for(;;){
	char ascii[100];
	int c;
	c=0;
	printf("Introduza a string: ");
	scanf("%s", &ascii);
	int len=strlen(ascii);
	while(c<len)
	{
		printf("%d %x %c", ascii[c], ascii[c], ascii[c]);
	}
	return 0;
	}

}