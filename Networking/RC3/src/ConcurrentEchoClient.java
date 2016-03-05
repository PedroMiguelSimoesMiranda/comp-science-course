import java.io.*;
import java.net.*;
import java.util.*;

public class ConcurrentEchoClient implements Runnable {

	int port;
	//InetAddress server;
	//DatagramSocket socket;
	Socket socketServer;

	// Guardar a localizacao do servidor...
	/*ConcurrentEchoClient(InetAddress server, int port) {
		this.server = server;
		this.port = port;
	}*/
	ConcurrentEchoClient(Socket socket) {
		this.socketServer = socket;
	}

	// Método onde corre o thread de leitura e que
	// afixa no terminal as mensagens ecoadas pelo servidor
	public void run() {
		for (;;) {
			try {
			InputStream is = socketServer.getInputStream();

				// afixa no terminal a mensagem de echo
				int n ;
				byte[] buf = new byte[1024] ;
				while( (n = is.read( buf )) > 0 )
				System.out.printf(">%s\n", new String(buf));	
			} catch (IOException x) {
			}
		}
	}

	// Metodo principal do cliente
	// Depois das inicializacoes envia as mensagens lidas da  	consola para o
	// servidor ate o utilizador escrever "!fim"
	void doIt() {
		try {

	// Cria um thread para fazer a leitura das mensagens do
	// servidor, enquanto o thread principal faz a leitura
	// das mensagens introduzidas na consola pelo utilizador
			Thread t = new Thread(this);
			t.setDaemon(true); // opcional...
			t.start(); 
			// arranca com o thread, que corre no metodo run()

			// Cria um objecto auxiliar para ler linhas 				completas do canal de
			// entrada standard
			Scanner in = new Scanner(System.in);
			OutputStream os = socketServer.getOutputStream() ;
			String request;
			do {
				// Le uma mensagem da consola
				request = in.nextLine();

				// envia para o servidor o datagrama
				byte[] requestData = request.getBytes();
				os.write(requestData);

				// testa se deve terminar.
			} while (!request.equals("!exit"));

			socketServer.close();
		} catch (IOException x) {
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("usage: java 	ConcurrentEchoClient maq_do_servidor porto_do_servidor");
			System.exit(0);
		}

		int port = Integer.parseInt(args[1]);
		InetAddress server = InetAddress.getByName(args[0]);

	new ConcurrentEchoClient(new Socket(server, port)).doIt();
	}
}
