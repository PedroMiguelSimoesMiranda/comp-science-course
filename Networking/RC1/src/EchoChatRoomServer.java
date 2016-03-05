import java.net.* ;
import java.util.*;

public class EchoChatRoomServer {

	//static final int PORT = 8000 ;

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.err.println("usage: java EchoChatRoomServer porto_do_servidor");
			System.exit(0);
		}
		
		int PORT = Integer.parseInt(args[0]);
		Vector <InetSocketAddress> clients = new Vector<InetSocketAddress>();
		
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
			int echoRequestLength = echoRequest.getLength();
			
			System.out.println(new String(echoRequestData));
			if(new String(echoRequestData,0,echoRequest.getLength()).equals("!enter")){
				System.out.println("Added");
				clients.add(new InetSocketAddress(echoRequest.getAddress(),echoRequest.getPort()));
			}
			else if(new String(echoRequestData).equals("!exit")){
				System.out.println("Removed");
				clients.remove(new InetSocketAddress(echoRequest.getAddress(),echoRequest.getPort()));
			
			}
			System.out.println( new String( echoRequestData, 0, echoRequestLength));
			
			//preparar uma mensagem para envio com a resposta...
			DatagramPacket echoReply = new DatagramPacket( echoRequestData, echoRequestLength ) ;
			for(int i=0; i<clients.size();i++){
				//indicar o destino da mensagem... (maquina + porto)
				echoReply.setAddress( clients.elementAt(i).getAddress()) ;
				echoReply.setPort( clients.elementAt(i).getPort() ) ;

				//enviar a mensagem...
				socket.send( echoReply ) ;
			}
		}
	}
}
