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
#include <signal.h>
int num;
int fila;
msg temp;
int* queues;
int sender;
int NPA;
pthread_t* threads;

void tratactrlc(int signal)
{
int i;
for(i=0;i<num;i++)
{
if(queues[i]!=-1)
msgctl(queues[i],IPC_RMID,NULL);
}
exit(1);
}

void resetBody()
{
int i;
int size = strlen(temp.conteudo.corpo);
for(i=0;i<size;i++)
{
temp.conteudo.corpo[i]='\0';
}
}

int getFila(int pNum)
{
return queues[pNum-1];
}

void enviaMensagemPrivada(msg* mensagem, int proc)
{
if(proc!=sender)
{
msgsnd(getFila(proc),(void*)mensagem,sizeof(int)*2+strlen((*mensagem).conteudo.corpo),0); 
printf("enviei a mensagem %s para o cliente %d\n",(*mensagem).conteudo.corpo,(*mensagem).conteudo.cab);
resetBody();
}
}

void enviaMensagem(msg* mensagem)
{
int i=0;
printf("CHEGUEI AO ENVIO GLOBAL\n");
while(i<num)
{
enviaMensagemPrivada(mensagem,i+1);
i++;
}
printf("passei pa fora do ciclo\n");
resetBody();
}


void trata(msg* mensagem)
{
	(*mensagem).type=2;
	if((*mensagem).conteudo.cab==-1)
	{
	printf("detectei o fim\n");
	NPA--;
	queues[(*mensagem).conteudo.myNumber -1]=-1; //queueIDs a -1 significam que o cliente com ID Posição +1 se desligou
	}
	else
	{
	sender=(*mensagem).conteudo.myNumber;
	if((*mensagem).conteudo.cab==0)
	{
	printf("vou enviar a mensagem global\n");
	enviaMensagem(mensagem);
	}
	else
	{
	printf("vou enviar a mensagem privada\n");
	enviaMensagemPrivada(mensagem,(*mensagem).conteudo.cab);
	resetBody();
	}
}

}

int recebeMensagem(int fila)
{
//os servidores enviam mensagens de tipo 2 e recebem do tipo 1
int x=msgrcv(fila,&temp , sizeof(int)*2+20 , 1 , IPC_NOWAIT );
if(x==-1 && errno==ENOMSG && NPA==0)
{
perror("Erro\n");
exit(errno);
}
if(x==-1)
{
return x;
}
else
{
printf("recebi a mensagem\n");
trata(&temp);
return 0;
}
}

void* trataumafila(void* args)
{
int* qnum =(int*) args;
while(*qnum!=-1)
{
recebeMensagem(*qnum);
}
return args;
}

int main(int argc, char** argv)
{
signal(SIGINT,tratactrlc);
int i;
num = (int) atoi(argv[1]);
NPA=num;
queues = malloc(sizeof(int)*num);

for(i=0;i<num;i++)
{
queues[i]=msgget(base+i,0666 | IPC_CREAT);
}

threads = malloc(sizeof(pthread_t)*num);

	for(i=0;i<num;i++)
	{
	if(pthread_create(threads+i,NULL,trataumafila, (void*)&(queues[i]))<0)
	{
	printf("Problema a criar thread");
	exit(errno);
	}

}
while(NPA>0)
{
for(i=0;i<num;i++)
{
if(queues[i]!=-1)
{
recebeMensagem(queues[i]);
resetBody();
}
sleep(1);
}
}
printf("Não há mais clientes ligados ao servidor, a iniciar SHUTDOWN\n");
return 0;
}











