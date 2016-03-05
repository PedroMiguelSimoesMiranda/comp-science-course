/* 
 * Trabalho Memoria Partilhada - FSO
 * prog2.c
 * DI-FCT/UNL
 */
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

unsigned long num;
unsigned long niter;
pthread_mutex_t exmut;

void *sum ( void *dummy )
{
	unsigned long j;
// fica mais rápido por aqui
 pthread_mutex_lock( &exmut );
	for (j = 0; j < niter; j++)
	{
	//pthread_mutex_lock( &exmut );
	num++;
	//pthread_mutex_unlock( &exmut);	
		
	}
// fica mais rápido por aqui 
pthread_mutex_unlock( &exmut );



	return dummy;	// keep compiler happy ;-)
}


int
main (int argc, char *argv[])
{

	pthread_t t1, t2;
	
	pthread_mutex_init(&exmut, NULL);

	if (argc != 2)
	{
		printf ("Usage: %s N_ITER \n", argv[0]);
		exit (1);
	}

	niter = strtoul (argv[1], NULL, 10);
	num = 0;

	if( pthread_create(&t1, NULL, sum, NULL ) < 0 )
	{	
		perror( "pthread_create" );
		exit(0);
	}
	
	if( pthread_create(&t2, NULL, sum, NULL ) < 0 )
	{
		perror( "pthread_create" );
		exit(0);
	}
	
	pthread_join( t1, NULL );
	pthread_join( t2, NULL );

	printf( "%lu\n", num );
	pthread_mutex_destroy(&exmut);
	return 0;
}
