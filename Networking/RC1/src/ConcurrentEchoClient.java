import java.io.*;
import java.net.*;
import java.util.*;

public class ConcurrentEchoClient implements Runnable {

	int port;
	InetAddress server;
	DatagramSocket socket;

	// Guardar a localização do servidor...
	ConcurrentEchoClient(InetAddress server, int port) {
		this.server = server;
		this.port = port;
	}

	// Método onde corre o thread de leitura e que
	// afixa no terminal as mensagens ecoadas pelo servidor
	public void run() {
		for (;;) {
			try {
				// espera a chegada de um datagrama do servidor
				byte[] buf = new byte[65536];
				DatagramPacket datagram = new DatagramPacket(buf, buf.length);
				socket.receive(datagram);

				// afixa no terminal a mensagem de echo
				String msg = new String(datagram.getData(), 0, datagram
						.getLength());
				System.out.printf(">%s\n", msg);
			} catch (IOException x) {
			}
		}
	}

	// Método principal do cliente
	// Depois das inicializações envia as mensagens lidas da consola para o
	// servidor até o utilizador escrever "!fim"
	void doIt() {
		String request = "!enter";
		try {
			socket = new DatagramSocket();
			// envia para o servidor o datagrama
			byte[] requestData = request.getBytes();
			DatagramPacket req = new DatagramPacket(requestData,
					requestData.length, server, port);
			socket.send(req);
		} catch (IOException e) {}

		try {

			// Cria um thread para fazer a leitura das mensagens do
			// servidor, enquanto o thread principal faz a leitura
			// das mensagens introduzidas na consola pelo utilizador
			Thread t = new Thread(this);
			t.setDaemon(true); // opcional...
			t.start(); // arranca com o thread, que corre no método run()

			// Cria um objecto auxiliar para ler linhas completas do canal de
			// entrada standard
			Scanner in = new Scanner(System.in);

			//String request;
			do {
				// Lê uma mensagem da consola
				request = in.nextLine();

				// envia para o servidor o datagrama
				byte[] requestData = request.getBytes();
				DatagramPacket req = new DatagramPacket(requestData,
						requestData.length, server, port);
				socket.send(req);

				// testa se deve terminar.
			} while (!request.equals("!exit"));

			socket.close();
		} catch (IOException x) {
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("usage: java ConcurrentEchoClient maq_do_servidor porto_do_servidor");
			System.exit(0);
		}

		int port = Integer.parseInt(args[1]);
		InetAddress server = InetAddress.getByName(args[0]);

		new ConcurrentEchoClient(server, port).doIt();
	}
}
