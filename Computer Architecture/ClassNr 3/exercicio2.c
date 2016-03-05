#include <stdio.h>

// MYSTRLEN

// AULA 03 EXERCICIO 02

int mystrlen(char *p){
	int i=0;
	while(p*!='\0')){
	p++;i++;
	}
	return i;
}

int main(){
char in[MAXSIZE];
scanf("%s",in);
mystrlen(in);
prinf("A sua string tem: %d caracteres.",mystrlen(in) );
return 0;
}
/*#include <stdio.h>

int mystrlen(char *pi)
{
int i;
for(i=0;*pi!='\0';i++)
{
pi++;
}
return i;
}

int main()
{
char string[100];
printf("Introduza a string");
scanf("%s", string);
printf("%d",mystrlen(string);
scanf("%d", &i);
return 0;
}
*/

