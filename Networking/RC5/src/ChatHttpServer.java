import java.net.*;
import java.io.*;
import java.util.* ;


/**
 * Servidor de HTTP
 */
public class ChatHttpServer implements Runnable {

	public static void main( String[] args) throws IOException {
		int serverPort = 8080;
		if( args.length > 1) {
			System.err.println( "Use: java ChatHttpServer 								[porto]");
			System.exit(0);
		} else if( args.length == 1)
			serverPort = Integer.parseInt( args[0]);
		new ChatHttpServer( serverPort).doIt();
	}

	protected int serverPort;
	protected ChatUtilities chat;

	ServerSocket serverSocket = new ServerSocket(serverPort);
	Socket socket;
	
	protected ChatHttpServer( int serverPort) throws IOException {
		this.serverPort = serverPort;
		this.chat = new ChatUtilities( serverPort);
	}

	// =====================================================================================
	// ============= funcoes auxiliares
	// ====================================================
	// =====================================================================================
	/**
	 * Copia o conteudo do stream in para o stream out
	 */
	protected void dumpStream( InputStream in, OutputStream out)
			throws IOException {
		byte[] arr = new byte[1024];
		for( ;;) {
			int n = in.read( arr);
			if( n == -1)
				break;
			out.write( arr, 0, n);
		}
	}

	// =====================================================================================
	// ============= funcoes principais
	// ====================================================
	// =====================================================================================

	/**
	 * Processa um dos seguintes pedidos HTTP:
	 * GET /chatRoomName HTTP/1.x - obtem pagina com conteudo da chat room
	 * POST /chatRoomName HTTP/1.x - adiciona mensagem ao chat room: parametros no 
	 *       corpo do pedido HTTP, codificados como "application/x-www-form-urlencoded"
	 *       nickName - nick name do utilizador a executar o chat room
	 *       messageText - mensagem adicionada ao chat room
	 * qualquer outro pedido deve levar a amostragem duma mensagem de erro  
	 */
	void processRequest( Socket socket) {
		
		//TODO: Completar com codigo para atender uma conexao/pedido

		InputStream is;
		try {
			
			is = socket.getInputStream();
				
			String line = HTTPUtilities.readLine(is);
			String[] req = HTTPUtilities.parseHttpRequest(line);

			synchronized (chat) {

				if(req[0].equals("GET")){
					System.out.println("GET");
					while(!HTTPUtilities.readLine(is).isEmpty								()){}//Ler Resto
					byte[] res = "HTTP/1.0 200 OK\r\n\r									\n".getBytes();
					socket.getOutputStream().write(res, 0, 									res.length);
					dumpStream(chat.getMainPageInputStream						(req[1]),socket.getOutputStream());
					System.out.println("/GET");
				}
				else if(req[0].equals("POST")){
					System.out.println("POST");
					String chatroom = req[1];
					byte[] res = "HTTP/1.0 200 OK\r\n\r									\n".getBytes();
					socket.getOutputStream().write(res, 0, 									res.length);
					line = HTTPUtilities.readLine(is);
					req = HTTPUtilities.parseHttpHeader											(line);
					String msg = "";
					int x = 0,clength = 0;
					while(!line.isEmpty()){
						if(req[0].equals("Content-Length"))
							clength = Integer.parseInt										(req[1]);
						line = HTTPUtilities.readLine(is);
						req = HTTPUtilities.parseHttpHeader								(line);}//Ler Resto
					res = new byte[clength];
					while(x < clength){
						x += is.read(res,0,clength);
						msg = msg.concat(new String(res));
						System.out.println("MSG = "+msg);
					}
					Properties prop = 								HTTPUtilities.parseHttpPostContents(msg);
					System.out.println(chatroom+"  / 							"+prop.getProperty("nickName")+ 						" --> "+prop.getProperty							("messageText"));
					chat.addChatMessage(chatroom, 						prop.getProperty("nickName"), 						prop.getProperty("messageText"));
					dumpStream(chat.getFormPageInputStream						(chatroom,prop.getProperty						("nickName")),socket.getOutputStream());
					System.out.println("/POST");
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Funcao principal do servidor - responsavel por criar o socket 	 *do servidor,
	 * atender conexoes e invocar o metodo processRequest que trata o 	 *pedido de
	 * cada conexao
	 */
	void doIt() throws IOException {
		
		//TODO: Completar com codigo com o ciclo principal de 		//atendimento, que ira chamar
                // o metodo processRequest para cada conexao
		//ServerSocket serverSocket = new ServerSocket(serverPort);
		while(true){
			socket = serverSocket.accept();
			
			Thread t = new Thread(this);
			t.setDaemon(true);
			t.start();
			
			socket.close();
		}
	}

	public void run() {
		processRequest(socket);
	}
}

