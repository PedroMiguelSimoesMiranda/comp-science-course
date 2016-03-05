#include<stdio.h>
#define MAXSIZE 1000 

// CALCULAR MENOR DO VECTOR

// AULA 02 EXERCICIO 07 

int calc(char in[], int size){
	int menor=in[0];
	int i;
	for(i=0;i<size;i++){
		if(in[i]<menor){
			menor=in[i];
		}
	}
return menor;
}

int main(){
	char in[MAXSIZE];
	printf("introduza os numeros");
	int i=0;
	while(getchar()!='q'){
		scanf("%d",in[i]);
	}i++;
	calc(in);
}
