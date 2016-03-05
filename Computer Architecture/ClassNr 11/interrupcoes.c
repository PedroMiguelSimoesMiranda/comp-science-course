#include <stdio.h>
#include <dos.h> 
#include "bufcir.f"
#include "inout.h" 
#define BUFSIZ 128
unsigned char buf[BUFSIZ];
unsigned char ch;
int put; // 1a casa livre
int get; // casa ocupada ha mais tempo
int nc;  // numero de bytes no buffer

/*le um byte no porto de I/O com endereço port*/
extern unsigned char inByte (unsigned int port);
/*escreve um byte no porto de I/O com endereço port */
extern void outByte (unsigned int port, unsigned char byte);
unsigned char bufGet(void){
	/* assume que buf não está vazio */
	unsigned char x = buf[get];
	get = (get + 1) % BUFSIZ;
	nc --;
	return x;
}
int bufEmpty(){
	return (nc == 0);
}
void bufPut( unsigned char c){
	/* assume que buf não está cheio */
	buf[put] = c;
	put = (put + 1) % BUFSIZ;
	nc ++;
}
int bufFull(){
	return (nc == BUFSIZ);
}

void ligarUART(){ // COM1
	unsigned char b,c;
	b=inByte(0x3fc); //Registo de Controlo do Modem (MCR): 0x3FC
	b=(b|0x08); // colocar bit 3 a 1 do registo MCR indica que a UART deve gerar interrupcoes 
	outByte(0x3fc,b);
	
	c=inByte(0x3f9); // registo IER
	c=c|0x02; // colocar o bit 1 do registo IER a 1 para permitir gerar interrupcoes apos o envio de um caracter
	outByte(0x3f9,c);
}

void ligarPIC(){
	unsigned char b;
	b=inByte(0x21);
	b=(b&0xef);// colocar o bit 4 do registo mascara( porto 21)  a 0, de modo a deixar passar interrupcoes que vem da porta serie
	outByte(0x21,b);
}

void desligarUART(){
	unsigned char b,c;
	b=inByte(0x3fc);//colocar o bit 3 do registo MCR a 0 
	b=(b&~0x08);
	outByte(0x3fc,b);
	
	c=inByte(0x3f9);
	c=(c|~0x02); // colocar o bit 1 do registo IER a 0
	outByte(0x3f9,c);
}

void desligarPIC(){
	unsigned char b;
	b=inByte(0x21);
	b=(b&~0xef); // colocar o bit 4 do registo máscara (21H) a 1.
	outByte(0x21,b);
}
void send_serial(unsigned char b){
		
	while(bufFull()){}
	if(bufEmpty()){
		bufPut(b)
		ligarUART();
		ligarPIC();
	}
	else{bufPut(b);
	}
}
unsigned char Receber_serie(){
	while (bufEmpty());
		/* esperar */
	disable();//função do TurboC para desligar as interrupções no
		//CPU (coloca interrupt flag a 0);
	ch = bufGet();
	enable(); //função do TurboC para ligar as interrupções no CPU
		//(coloca interrupt flag a 1);
	return ch;
}