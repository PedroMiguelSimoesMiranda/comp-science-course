#include <string.h>
#include <atoi.h>
#include <stdio.h>
#include "mensagem.h"
int n;
int fila;
int end=0;

void enviaMensagem(char *mensagem)
{
msg mess;
if(end)
{
mess.tag=1;
mess.cab=-1;
msgsnd(fila,mess,28,0);
exit(1);
}

mess.tag=1;
mess.ma.cab = 0;
strcpy(mess.ma.corpo,mensagem);
msgsnd(fila, mess, 28,0);
}

void enviaMensagemPrivada(char *mensagem, int proc)
{
msg mess;
mess.tag=1;
mess.ma.cab=proc;
strcpy(mess.ma.corpo,mensagem);
msgsnd(fila,msn,28,0);
}

msg recebeMensagem()
{
return msgrcv(fila, msg, 28, 1, 0);
}

void cliente()
{
char linha[30];
fila=msgget(base -1 + n, 0644);

	while(1)
	{
	printf("$ ");
	fgets(linha,30,stdin);

	char *apont = strtok(in," \n");
	if(strcmp(apont,"Fim")==0)
	{
	end=1;
	enviarMensagem(apont);
	}
	if(strcmp(apont,"todos")==0)
	enviaMensagem(strtok(null," \n"));
	}
	else
	enviaMensagemPrivada(strtok(null," \n"),atoi(apont));
}

}


void servidor()
{
int i=n;
while(i>=1)
{
fila=msgget(base -1 + i, 0644 | IPC_CREATE);
i--;
}



}


int main(int argc, char* argv)
{
n=atoi(argv[1]);
if(strcmp(argv[0],"servidor")==0)
{
servidor();
}
else
if(strcmp(argv[0],"cliente")==0)
{
cliente();
}
else
printf("És louco");
exit(1);
}




















