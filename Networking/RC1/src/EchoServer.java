
import java.net.* ;


public class EchoServer {

	//static final int PORT = 8000 ;

	public static void main(String[] args) throws Exception {

		int PORT = Integer.parseInt(args[0]);

		// inicializar o socket com o porto de atendimento
		DatagramSocket socket = new DatagramSocket( PORT ) ;

		for(;;) { //ciclo infinito de atendimento...

			//preparar uma mensagem (datagrama) vazia para recepcao...
			byte[] buffer = new byte[65536] ;
			DatagramPacket echoRequest = new DatagramPacket( buffer, buffer.length ) ;

			//receber a mensagem...
			socket.receive( echoRequest ) ;

			//obter o conteudo da mensagem recebida...
			byte[] echoRequestData = echoRequest.getData();
			int echoRequestLength = echoRequest.getLength() ;

			System.out.println( new String( echoRequestData, 0, echoRequestLength));
			/*
			//preparar uma mensagem para envio com a resposta...
			DatagramPacket echoReply = new DatagramPacket( echoRequestData, echoRequestLength ) ;

			//indicar o destino da mensagem... (maquina + porto)
			echoReply.setAddress( echoRequest.getAddress() ) ;
			echoReply.setPort( echoRequest.getPort() ) ;

			//enviar a mensagem...
			socket.send( echoReply ) ;
			 */
		}
	}
}
