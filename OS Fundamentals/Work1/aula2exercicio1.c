#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include<unistd.h>
#include <sys/wait.h>


char *argv[100]; // array de apontadores para char's 
char *myargv2[100]; // array de apontadores para char's 
char linha [100]; // array de char's
int status; //vari�vel que vai guardar o valor retornado pelo processo filho

//M�todo que recebe um array de apontadores para chars e 
//percorre esse vector � procura do & se o encontrar coloca NULL no endere�o "i" do vector e retorna 0
//sen�o retorna 1
int check(char *myargv[])
{
int i;
for(i=0;myargv[i]!=NULL;i++)
{
if(strcmp(myargv[i],"&")==0)
{
myargv[i]=NULL;
return 0;
}
}
return 1;
}

//M�todo que vai operar sobre a "string", ou o array de char's 
//vai "partir" cada caracter, com delimitadores( " " e "\n") 
//e quando no final o token seguinte apontar para null coloca-se 
//o valor da mesma posi�ao no vector a null
void runcmd(char in[]){
	int i=0;

	char *st = strtok(in," \n"); 

		while(st!=NULL){
		argv[i]=st; 
		st=strtok(NULL," \n"); 
		i++;		
		}
		argv[i]=NULL;
}

//o processo cria um outro processo filho, se a fun��o fork retornar 0, senao aborta,
// a variavel temp vai ter 0 ou 1 conforme tenha encontrado ou n�o & no vector
// se n�o encontrou entao vai correr o comando em background

void runcommand(char *myargv[]){
	int temp = check(myargv);
	int n=fork();
	if(n==-1){
		perror("fork");
		exit(1);
	}           
	if(n==0){
		//filho
		execvp(myargv[0],myargv);
		perror("exec");
		exit(1);
	}
	else
	{
	if(temp)//n�o encontra encontra &
	wait(&status);
	}
}

//M�todo que recebe como argumento um array de apontadores do tipo char
//Se ele encontra "|" coloca null como valor na posi��o i desse vector, porque haver� pipe, 
//e ao mesmo tempo vai contruir um segundo argv com os valores restantes do original,
//colocando null no final porque o ciclo acaba sem o fazer
//se h� pipe retorna 1 sen�o retorna 0
int checkPipe( char *myargv[]){
int i;
int c=0;
int j=0;
for(i=0;myargv[i]!=NULL;i++)
{
	if(strcmp(myargv[i],"|")==0)
	{
		myargv[i]=NULL;
		for(c=i+1;myargv[c]!=NULL;c++)
		{
		myargv2[j]=myargv[c];
		j++;
		}
		myargv2[j]=NULL;
		return 1;
	}
}
return 0;
}

//1o metodo que � chamado, supostamento o filho, vai criar um novo descritor com o valor do canal de escrita, e fecha o velho canal
//fecha o canal inutil de leitura, e o de escrita porque j� fizems o que queriamos

void filho1( int p[] )
{
dup2(p[1],1);
close(p[0]);
close(p[1]);
execvp(argv[0],argv);
}

//2o metodo que � chamado, supostamento o neto, vai criar um novo descritor com o valor do canal de leitura, e fecha o velho canal
//fecha o canal inutil, e o de leitura porque j� fizems o que queriamos

void filho2( int p[] )
{
dup2(p[0],0);
close(p[1]);
close(p[0]);
execvp(myargv2[0],myargv2);
} 

//M�todo executado quando h� um pipe
// ao contr�rio do m�todo runcommand este vai utilizar canais para que o 2o comando v� ler os valores do 1o
void runWithPipe()
{
int fd[2];
int pid;

if (pipe(fd)==-1)
{ 
perror("piping");
abort();
}
pid=fork();
if(pid==-1)
{
perror("fork");
abort();
}
else
if(pid==0) 
{
	pid=fork();
	if(pid==-1)
	{
	perror("fork");
	abort();
	}
	if(pid==0)
	{
	filho2(fd);
	exit(1);
 	}
	else 
	{
	filho1(fd);
	exit(1);
	}

	}

close(fd[0]);
close(fd[1]);
wait(NULL);
wait(NULL);
}

//guarda-se espa�o em disco do tamnha do char
//opera-se o vector e acha-se se h� um pipe
//se houver executa-se o m�todo runWithPipe sen�o opera-se aqui
//sen�o for reconhecido aqui � porque � um comando externo
//no fim liberta-se o estado ocupado

int main (){

	while(1){//ciclo infinito

		char *temp = malloc(sizeof(char)*1000);
		getcwd (temp,1000 );
		printf("%s>",temp);
		fgets(linha,100,stdin); 
		runcmd(linha);
		if(checkPipe(argv))
		{
		runWithPipe();
		}
		else
		{

		if(argv[0]==NULL);
		else if(strcmp(argv[0],"eco")==0)
		{                             
			int i=1; 
			while(argv[i]!=NULL)  
				{                
				printf("%s ",argv[i]);
				i++;
				}
			printf("\n");
		}

		else if(strcmp(argv[0],"pwd")==0){
			
			char *buf=malloc(sizeof(char)*1000); 
			printf("%s",getcwd (buf,1000 ));
			free(buf); 
			printf("\n")

		}
		else if(strcmp(argv[0],"cd")==0){
			chdir(argv[1]);     
			
		}

		else if(strcmp(argv[0],"exit")==0)
			break;
		else
			runcommand(argv);
			
		free(temp);
	}
}
exit(0);
return 0;
}



		
	
