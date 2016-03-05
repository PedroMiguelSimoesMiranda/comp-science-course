#include <string.h>
#include <stdio.h>
#include "mensagem.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
int num;
int fila;
msg temp;
int* queues;
int sender;
int NPA;

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
	if((*mensagem).conteudo.cab!=(*mensagem).conteudo.myNumber)
	{
//int msgsnd(int msqid, const void *msgp, size_t msgsz, int msgflg);
//
		msgsnd(getFila(proc),(void*)mensagem,sizeof(int)*2+strlen((*mensagem).conteudo.corpo),0); 
		printf("enviei a mensagem %s para o cliente %d\n",(*mensagem).conteudo.corpo,(*mensagem).conteudo.cab);
	}
}

void enviaMensagem(msg* mensagem)
{
	int j=0;
	printf("CHEGUEI AO ENVIO GLOBAL\n");
	while(j<num)
	{
		printf("MAS PASSEI POR AKI");
		printf("iterei\n");
		enviaMensagemPrivada(mensagem,j+1);
		printf("MAS PASSEI POR AKI");
		j++;
	}
	printf("MAS PASSEI POR AKI");
	resetBody();
	printf("MAS PASSEI POR AKI");
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
		if((*mensagem).conteudo.cab==0)
		{
			printf("vou enviar a mensagem global\n");
			sender=(*mensagem).conteudo.myNumber;
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

int main(int argc, char** argv)
{
	int i;
	int num = (int) atoi(argv[1]);
	NPA=num;
	queues = malloc(sizeof(int)*num);
	for(i=0;i<num;i++)
	{
		queues[i]=msgget(base+i,0666 | IPC_CREAT);
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










