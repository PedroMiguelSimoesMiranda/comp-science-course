#include<stdio.h>

// PRIMOS 

// AULA 01 EXERCICIO 03

int prim(int num){
	int i;
	int count;
	for(i=0;i<num;i++){
		if(num%i==0){
		count++;}
	}
	return count;
}

int main(){
	int n;
	printf("introduza o numero:\n");
	scanf("%d",&n);
	int i;	
	for(i=0;i<n;i++){
		if(prim(i)==2){
			printf("%d",i);
		}
	}
return 0;
}
