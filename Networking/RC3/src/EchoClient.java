import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {

	public static void main(String[] args ) throws Exception {
		
		if( args.length != 2 ) {
		System.out.println("usage: java EchoClient 					maq_do_servidor porto_do_servidor") ;
				System.exit(0) ;
		}
		String server = args[0] ;
		int port = Integer.parseInt(args[1]) ;

		// Cria uma conexao para o servidor 
		Socket socket = new Socket( server, port ) ;
		// Obtem o canal de saida associado ao socket.
		OutputStream os = socket.getOutputStream();
		// Obtem o canal de entrada associado ao socket
		// donde se lera o ECHO enviado pelo servidor.
		InputStream is = socket.getInputStream() ;
		 
		// Criar um scanner auxiliar para ler linhas completas do 		//canal de entrada standard.
		Scanner in = new Scanner( System.in ) ;
		
		String echoRequest;
		do {
			
		 // Le da consola uma linha de texto e repoe o "\n" no 		//fim.
		 echoRequest = in.nextLine() ;
		 echoRequest += "\n" ;	

		 // Escreve os bytes do pedido no canal de saida do 			//socket
		 os.write( echoRequest.getBytes() ) ;
		
		 // Le uma linha de texto do canal de entrada do socket,
		 // usando um objecto Scanner para detectar o "\n" e 			//fazer
		 // a conversao para String automaticamente...
		 String echoReply = new Scanner( is ).nextLine();
		 System.out.printf("A resposta foi: \"%s\"\n",echoReply);
		
		} while( ! echoRequest.equals("!fim\n") ) ;
		
		// Fecha o socket, quebrando a ligacao com o servidor.
		//socket.getOutputStream().close() ;
		//socket.getInputStream().close() ;
		socket.close() ;
	}
}
