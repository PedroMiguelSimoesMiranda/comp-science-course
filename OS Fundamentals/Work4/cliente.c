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
int nargs;
msg carta;
msg received;
int num;
int queueID;
char status ='o'; //'o'significa que o cliente ainda está ligado, 't' significa que terminou
		//serve para efeitos de comunicação com o thread
char* args[2];

void resetBody(msg* buffer)
{
int i;
int size = strlen((*buffer).conteudo.corpo);
for(i=0;i<size;i++)
{
(*buffer).conteudo.corpo[i]='\0';
}
}

void *recebeMensagens(void *dummy)
{
while(status!='f')
{
int x=msgrcv(queueID,&received,sizeof(int)*2 + 20,2,0);
if(x==-1)
{
printf("não há mensagens para receber\n");
sleep(3);
}
if(x!=-1)
{
printf("\nRecebeste a seguinte mensagem do cliente %d:\n%s\n",(int)received.conteudo.myNumber,received.conteudo.corpo);
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
if(x==-1)
{
printf("Envio de mensagem sem sucesso\n");
}
}

void enviaMensagemPrivada(char** argumentos)
{
if(strcmp(argumentos[0],"fim")==0)
{
carta.conteudo.cab=-1;
msgsnd(queueID,&carta, sizeof(int)*2 + 20,IPC_NOWAIT);
status='f';
exit(1);
}
else
{
carta.conteudo.cab=atoi(args[0]);
strcpy(carta.conteudo.corpo,args[1]);
}
int x=msgsnd(queueID,&carta, sizeof(int)*2 + 20,IPC_NOWAIT);
if(x==-1)
{
printf("Envio de mensagem sem sucesso\n");
}
}

void divideString(char* string)
{ 
nargs=0;
int i=0;
char* temp = strtok(string," \n");
while(temp!=NULL)
{
args[i]=temp;
i++;
temp=strtok(NULL,"\n");
nargs++;
}
}


int main(int argc, char** argv)
{
pthread_t receptor;
char buffer[30];
num = (int)atoi(argv[1]);
int connected=0;
while(!connected)
{
queueID=msgget(base-1+num,0666);
if(queueID==-1)
{
printf("A tentar ligar á fila de mensagens: %d\n",(int) base-1+num);
printf("Não consegui ligar-me à fila, a tentar de novo dentro de 3 segundos\n");
sleep(3);
}
else
{
printf("Ligação efectuada com sucesso!\n");
connected=1;
}
}
if( pthread_create(&receptor, NULL, recebeMensagens, NULL ) < 0 )
	{
		perror( "Criação do receptor" );
		exit(0);
	}
while(1)
{
resetBody(&carta);
printf("->");
fgets(buffer,30,stdin);
divideString(buffer);
carta.conteudo.corpo[19]='\0';
carta.type=1;
carta.conteudo.myNumber=num;
if(nargs!=0)
{
if(strcmp(args[0],"todos")==0)
{
enviaMensagem(args);
}
else
{
enviaMensagemPrivada(args);
}
}
else
{}
}
return 0;
}