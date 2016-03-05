//MY STRING DUP

//AULA 03 EXERCICIO 07

#include<stdio.h>

char *mystrdup(char *s){
	int i=0;
	char *p=*s;
	while((*p)!='\0'){
		p++;i++;
	}
	char *newString=malloc(n+1);//reservar espaco para nova string com 
	while((*s)!='\0'){
		*newString=*s;//tbem da newString=&s?
		s++;newString++;
	}
	
	newString=(newString)-n;//devolver o endereço da copia
return newString;
	
}

int main(){
	int temp;
	char string[100];
	printf("Introduza a string: \n");
	scanf("%s",string);
	mystrdup(string);
	printf("%p",mystrdup(string));
	scanf("%d",&temp);
return 0;
}

