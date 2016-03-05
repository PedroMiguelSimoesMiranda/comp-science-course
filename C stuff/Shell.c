#include<stlib.h>
#include<stdio.h>
#include<string.h>

int pos;
char *argv[100];
char linha[100];



void buildVec(char in[])
{
char *apont = strtok(in," \n");
while(apont)
{
argv[pos]=apont;
apont=strtok(NULL," \n");
pos++;
}

}


int main(){
pos=0;
int i=0;
while(1)
{
printf("-> ");
fgets(linha,100,stdin);
buildVec(linha);

if(argv[0]==NULL)
	printf("idiota\n");

else
	if(strcmp(argv[0],"ECO")==0)
		while(argv[i]!=NULL)
			{
			i++;
			printf("%s\n",argv[i]);
			}
}



