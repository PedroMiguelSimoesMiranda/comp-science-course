void send_serial(unsigned char b){
	unsigned char c;	
	do{
		c= inByte(0x3f8 + 5);
		 
	}while((c & 0x20)==0); // qd c!=0 || LSR + 5 O registo de transmiss�o (THR) est� livre. (Podemos pedir o envio de um
						//novo car�cter) avaliamos o registo de comando do PIC com o do UART
	outByte(0x3f8,b);
}

unsigned char receive_serial( ){
	unsigned char s;
	do {
		s = in_port(0x3fd); 
	} while(s & 0x01) == 0; // LSR + 0 - Chegou um car�cter que est� dispon�vel no registo RBR
	return in_port(0x3f8);
}