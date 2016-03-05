import java.io.* ;
import java.net.* ;
import java.util.Vector;

public class EchoServer implements Runnable{

	public static final int PORT = 8000 ;
	public static Vector<Socket> clientSockets = new Vector<Socket();
	Socket client;

	public EchoServer(Socket clientSocket) {
		client = clientSocket;
	}

	public static void main(String args[] ) throws Exception {

		// Cria o socket de atendimento do servidor, de onde
		// se aceitam as conexões dos clientes

		ServerSocket serverSocket = new ServerSocket( PORT );

		for(;;) { // ciclo infinito de atendimento

			// Espera até que um cliente se ligue ao servidor,
			// retorna um novo socket para comunicar com o 				//cliente
			Socket clientSocket = serverSocket.accept() ;

			new EchoServer(clientSocket).doIt();

		}
	}

	private void doIt() {
		clientSockets.add(client);
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	public void run() {
		// Obtém os canais de entrada/saída do socket para 
		// comunicar com o cliente
		try {
			InputStream is = client.getInputStream();
			//OutputStream os = client.getOutputStream() ;

			// Implementa a função ECHO, copiando os dados da 			//canal de entrada,
			// directamente, para o canal de saída, até que o 			//cliente feche a ligação
			// Nota: o read retorna o número de bytes lidos ou 0, 
			// quando não há mais nada para ler do canal de 			//entrada do socket,
			// situação que resulta do socket ter sido fechado do 			//outro lado
			// da ligação...
			
			int n ;
			byte[] buf = new byte[1024] ;

			//synchronized (clientSockets) {

				while( (n = is.read( buf )) > 0 )
					for(int i=0; i<clientSockets.size();i++)
					clientSockets.get									(i).getOutputStream().write(buf,0,n);

				// Fecha e liberta o socket que usou para 				//comunicar com este cliente...
				clientSockets.remove(client);
				client.close();

			//}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
