#include<stdio.h>

// PARIDADE

// AULA 01 EXERCICIO 01

int par(int num){
	if(num%2==0){
		num=1;
	}
	else{
		num=0;
	}
return num;
}

int main(){
	int n;
	printf("introduza o numero:\n");
	scanf("%d",&n);
	if(par(n)==0){
		printf("%d e' impar.\n",n);
	}
	else{
		printf("%d e' par.\n",n);
	}
return 0;
}
