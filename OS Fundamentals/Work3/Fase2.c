#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <wait.h>
#include "shm.h"

#define N_NIM 5
int sharemem; // variavel com id da memória partilhada
int exmut; //semáforo de exclusão mútua
int block; //semáforo para bloqueio na barreira
int counter; // contador de processos
int *countPtr; //apontador usada como contador

void nim( int i );

int main(int argc, char **argv)
{
	int i;

	if( argc != 2 )
	{	printf( "uso %s num_filhos\n", argv[0] );
		exit(1);
	}

	sharemem=createSharedArea(8);//zona de memória partilhada de 8 bytes
	countPtr = (int*) attachArea (sharemem ); //ligação da zona de sharemem ao processo e atribuição de id ao apontador de countPtr
	*countPtr=0; 

	exmut=createSem(1); 
	block=createSem(0); 
	counter = atoi(argv[1]);

	for( i = 0; i < counter; i++ )
		if( fork() == 0 )
		{	nim(i);
			exit(0);
		}

	for( i = 0; i < counter; i++ )
		wait(NULL);
	deleteSharedArea( sharemem ); //destruição da zona de memória partilhada 
	printf( "fim!\n" );
	return 0;
}


void nim(int i)
{
	int pid = getpid();
	int n;
	for( n = 1; n <= N_NIM; n++ )
	{
	printf("Filho %d - <%d> Mensagem %d\n", i, pid, n );
	barreira(); 
	
	
	}
	
	}

void barreira(){

	P(exmut); //inicio da exclusão mútua
	(*countPtr)++; //incremento da variável contadora de processos que já entraram na barreira
	if((*countPtr)<counter)//enquanto n tiverem tds os processos na barreira
	{
		V(exmut); //libertação de exmut para o proximo processo
		P(block); //bloqueio de barreira
		P(exmut); //bloqueio que garante que o ultimo processo pode abrir a barreira de forma "atómica" aos outros processos
	}
	if((*countPtr)==counter)
	{
		int i;
		for(i=1;i<=counter-1;i++)
		{			//liberta a barreira 
			V(block);
		}                      
		(*countPtr)=0; //reset da variavel pa proxima iteraçao
	}
	V(exmut); //deixar passar processo libertador
}




