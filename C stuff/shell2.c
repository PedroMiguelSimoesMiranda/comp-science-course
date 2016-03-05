#include<stdio.h>
#include<string.h>

char *argv[100];
char linha[100];



void buildVec(char in[])
{
int pos=0;
char *apont = strtok(in," \n");
while(apont)
{
argv[pos]=apont;
apont=strtok(NULL," \n");
pos++;
}
argv[pos]=NULL;

}


int main(){
while(1)
{
printf("-> ");
fgets(linha,100,stdin);
buildVec(linha);

if(argv[0]==NULL)
	printf("idiota\n");

else
	if(strcmp(argv[0],"ECO")==0)
	{
		int i=1;
		while(argv[i]!=NULL)
			{
			printf("%s ",argv[i]);
			i++;
			}
			printf("\n");
			}
printf("\n");
}

}

