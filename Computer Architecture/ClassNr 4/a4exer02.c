/*aula 4 exercicio02*/ 

#include<stdio.h>
unsigned char b;
int exp2(int n){
int answer=0;
	if(n==0)
	answer = 1;
	else if(n==1)
	answer=2;
	else answer=n*n;		
return answer;
}

int main(){
//b=0x85;
int i,k;i=0;k=0;
//a
b=b|2;

//b
while(k<=16){
	b=b&i;
	i++;
	k=epx2(i);
}

//c
b=b&~4;

//d
if((b^0)==1)
	printf("o bit 0 de b e' 1");
else if((b^1)==1)
	printf("o bit 0 de b e' 0");
	

return 0;
}
