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
		// se aceitam as conex�es dos clientes

		ServerSocket serverSocket = new ServerSocket( PORT );

		for(;;) { // ciclo infinito de atendimento

			// Espera at� que um cliente se ligue ao servidor,
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
		// Obt�m os canais de entrada/sa�da do socket para 
		// comunicar com o cliente
		try {
			InputStream is = client.getInputStream();
			//OutputStream os = client.getOutputStream() ;

			// Implementa a fun��o ECHO, copiando os dados da 			//canal de entrada,
			// directamente, para o canal de sa�da, at� que o 			//cliente feche a liga��o
			// Nota: o read retorna o n�mero de bytes lidos ou 0, 
			// quando n�o h� mais nada para ler do canal de 			//entrada do socket,
			// situa��o que resulta do socket ter sido fechado do 			//outro lado
			// da liga��o...
			
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
