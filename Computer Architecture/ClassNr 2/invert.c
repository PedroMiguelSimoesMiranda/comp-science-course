#include <stdio.h>
#define MAXSIZE 1000 

// INVERT STRING

// AULA 02 EXERCICIO 02

int invert(char in[], int size){
	char temp[MAXSIZE];
	int i=0;size--;
	while(size>0){
		temp[i]=in[size];
		i++;
		size--;
	}
}

int main(){

	int counter;
	char in[MAXSIZE];
	int c;
	while((c=getchar())!=EOF && (c=getchar()!='\n')){
		in[counter]=c;
		counter++;
	}
	in=revert(in,counter);
	for(c=0;c<=counter;c++){
		putchar(in[c]);	
	}
	scanf("%s",in);
}