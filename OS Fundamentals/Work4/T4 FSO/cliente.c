#include <string.h>
#include <stdio.h>
#include "mensagem.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>
msg carta;
msg received;
int num;
int queueID;
char status ='o'; //'o'significa que o cliente ainda está ligado, 'f' significa que terminou
		//serve para efeitos de comunicação com o thread
char* args[2];

void resetBody(msg* buffer)
{
	int i;
	int size = strlen((*buffer).conteudo.corpo);
	for(i=0;i<size;i++)
	{
		carta.conteudo.corpo[i]='\0';
	}
}

void *recebeMensagens(void *dummy)
{
	while(status!='f')
	{
		int x=msgrcv(queueID,&received,sizeof(int)*2 + 20,2,0); //msgrcv(msgqid, msg, size, type, options)
		if(x==-1)
		{
			printf("não há mensagens para receber\n");
			sleep(3);
		}
		if(x!=-1)
		{
			printf("\nRecebeste a seguinte mensagem do cliente %d:\n%s\n",(int)received.conteudo.myNumber,received.conteudo.corpo);
			printf("->");
			resetBody(&received);
		}
	}
return dummy;
}

void enviaMensagem(char** argumentos)
{
	carta.conteudo.cab=0;
	strcpy(carta.conteudo.corpo,args[1]);
	int x=msgsnd(queueID,&carta, sizeof(int)*2 + 20,IPC_NOWAIT);
	if(x==-1){
		printf("Envio de mensagem sem sucesso\n");
	}
	else{
		printf("Mensagem enviada com sucesso\n");
	}
}

void enviaMensagemPrivada(char** argumentos)
{
	if(strcmp(argumentos[0],"fim")==0){
		carta.conteudo.cab=-1;
		msgsnd(queueID,&carta, sizeof(int)*2 + 20,IPC_NOWAIT);
		status='f';
		exit(1);
	}
	else{
		carta.conteudo.cab=atoi(args[0]);
		strcpy(carta.conteudo.corpo,args[1]);
	}
	int x=msgsnd(queueID,&carta, sizeof(int)*2 + 20,IPC_NOWAIT);
	if(x==-1){
		printf("Envio de mensagem sem sucesso\n");
	}
	else{
		printf("Mensagem enviada com sucesso\n");
	}
}

void divideString(char* string)
{ 
//char *strtok(char *restrict s1, const char *restrict s2);
//A sequence of calls to strtok() breaks the string pointed to by s1 into a sequence of tokens, each of which is delimited by a byte from the string pointed to by s2. The first call in the sequence has s1 as its first argument,
// and is followed by calls with a null pointer as their first argument. The separator string pointed to by s2 may be different from call to call.
	int i=0;
	char* temp = strtok(string," \n");
	while(temp!=NULL){
		args[i]=temp;
		i++;
		temp=strtok(NULL,"\n");
	}
	printf("%s\n",args[0]);
	printf("%s\n",args[1]);
}


int main(int argc, char** argv)
{
	pthread_t receptor;
	char buffer[30];
	num = (int)atoi(argv[1]);
	int connected=0;
	while(!connected){
		queueID=msgget(base-1+num,0666);
		if(queueID==-1)
		{
			printf("A tentar ligar á fila de mensagens: %d\n",(int) base-1+num);
			printf("Não consegui ligar-me à fila, a tentar de novo dentro de 3 segundos\n");
			sleep(3);
		}
		else{
			printf("Ligação efectuada com sucesso!\n");
			connected=1;
		}
	}
//The pthread_create() function is used to create a new thread, with attributes specified by attr, within a process. If attr is NULL, 
//the default attributes are used. If the attributes specified by attr are modified later, the thread's attributes are not affected.
// Upon successful completion, pthread_create() stores the ID of the created thread in the location referenced by thread. 
//int pthread_create(pthread_t *thread, const pthread_attr_t *attr,void *(*start_routine)(void*), void *arg);

	if( pthread_create(&receptor, NULL, recebeMensagens, NULL ) < 0 )
	{
		perror( "Criação do receptor" );
		exit(0);
	}
	while(1)
	{
		resetBody(&carta);
		printf("->");
//char *fgets(char *restrict s, int n, FILE *restrict stream);
//The fgets() function shall read bytes from stream into the array pointed to by s, until n-1 bytes are read, 
//or a <newline> is read and transferred to s, or an end-of-file condition is encountered. The string is then terminated with a null byte.
		fgets(buffer,30,stdin);
		divideString(buffer);
		carta.conteudo.corpo[19]='\0';
		carta.type=1;
		carta.conteudo.myNumber=num;
		if(strcmp(args[0],"todos")==0)
		{
			enviaMensagem(args);
		}
		else
		{
			enviaMensagemPrivada(args);
		}
	}
return 0;
}
