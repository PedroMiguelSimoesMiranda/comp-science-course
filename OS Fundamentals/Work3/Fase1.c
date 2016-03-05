#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <wait.h>

#define N_NIM	5
int exmut; //semáforo de exclusão mútua

void nim( int i );

int main(int argc, char **argv)
{
	int i;
	int counter;//contador de processos

	if( argc != 2 )
	{	printf( "uso %s num_filhos\n", argv[0] );
		exit(1);
	}
	
	counter = atoi(argv[1]);
	exmut=createSem(1); //criação do semáforo de exclusão mútua
	for( i = 0; i < counter; i++ )
		if( fork() == 0 )
		{	nim(i);
			exit(0);
		}

	for( i = 0; i < counter; i++ )
		wait(NULL);
	printf( "fim!\n" );
	deleteSem( exmut ); //destruição do semáforo
	return 0;
	
}


void nim(int i)
{	
	int pid = getpid();
	int n;

	P(exmut); //uso do semáforo de exclusão mútua

	for( n = 1; n <= N_NIM; n++ )
	{
		printf("Filho %d - <%d> Mensagem %d\n", i, pid, n );
/*	para mono processadores acrescentar a instrução	
		usleep(100);
*/
	}
	V(exmut); //libertação do semáforo de exclusão mútua
}
