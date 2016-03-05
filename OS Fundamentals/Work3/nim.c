#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <wait.h>
#include "shm.h"

#define N_NIM 5
int shmID;
int exmut;
int block;
int nfilhos;
int *ptr;

void nim( int i ) ;

int main(int argc, char **argv)
{
	int i;

	if( argc != 2 )
	{	printf( "uso %s num_filhos\n", argv[0] );
		exit(1);
	}

	shmID=createSharedArea(8);
	ptr = (int*) attachArea (shmID );
	*ptr=0;


	exmut=createSem(1);
	block=createSem(0);
	nfilhos = atoi(argv[1]);

	for( i = 0; i < nfilhos; i++ )
		if( fork() == 0 )
		{	nim(i);
			exit(0);
		}

	for( i = 0; i < nfilhos; i++ )
		wait(NULL);
	deleteSharedArea( shmID );
	printf( "fim!\n" );
	return 0;
}


void nim(int i)
{
	int currentCat=1;
	int pid = getpid();
	int n;
	for( n = 1; n <= N_NIM; n++ )
	{
	printf("Filho %d - <%d> Mensagem %d\n", i, pid, n );
	barreira();
	
	
	}
	
	}

void barreira(){

P(exmut);
(*ptr)++;
if((*ptr)<nfilhos)
{
V(exmut);
P(block);
P(exmut);
}
if((*ptr)==nfilhos)
{
V(exmut);
P(exmut);
int i;
for(i=1;i<=nfilhos-1;i++)
{
V(block);
}
(*ptr)=0;
}
V(exmut);
}




