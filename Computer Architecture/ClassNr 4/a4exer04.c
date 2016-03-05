#include <stdio.h>
// AULA 04 EXERCICIO 04
// MYSTRLEN + compacto

int mystrlen(char *p){
	int i=0;
	while((p*)!='\0' && p++)){
	/*p++;*/i++;
return i;
}

int main(){
char in[MAXSIZE];
scanf("%s",in);
mystrlen(in);
prinf("A sua string tem: %d caracteres.",mystrlen(in) );
return 0;
}
