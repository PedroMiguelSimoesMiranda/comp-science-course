//MY STRING LENGHT

//AULA 03 EXERCICIO 02
 
#include<stdio.h>

int mystrlen(char k[]){
	int i=0;
	while(k[i]!='\0')){
	i++;
	}
	return i;
}
void s_replace(char s[], int n, char rep[]){
	int i,k;
	k=0;
	for(i=n;i<mystrlen(rep)-1;i++){
		s[i]=rep[k];
		k++;
	}

}

int main(){
int temp,num;
char s[100];
char rep[100];
printf("Introduza s: \n");
scanf("%s\n",s);
printf("Introduza rep: \n");
scanf("%s\n",rep);
printf("Introduza o numero de caracteres que pretende copiar: ");
scanf("%d\n",num);
sreplace(s,rep,num);
printf("%s",s);
scanf("%d",&temp);


return 0;
}