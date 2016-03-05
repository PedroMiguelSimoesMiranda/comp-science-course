#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include<unistd.h>
#include <sys/wait.h>


char *argv[100];
char *myargv2[100];
char linha [100];
int status;

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
	if(temp)
	wait(&status);
	}
}

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

void filho1( int p[] )
{
dup2(p[1],1);
close(p[0]);
close(p[1]);
execvp(argv[0],argv);
}

void filho2( int p[] )
{
dup2(p[0],0);
close(p[1]);
close(p[0]);
execvp(myargv2[0],myargv2);
} 

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



		
	
