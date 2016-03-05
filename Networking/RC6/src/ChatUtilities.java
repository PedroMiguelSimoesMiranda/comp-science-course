import java.net.*;
import java.util.*;
import java.io.*;

/**
 * Classe que mantem informacao sobre varios chat room e gera paginas HTML para apresentacao
 * dos mesmos
 */
public class ChatUtilities {
	private static final byte[] errorPage = ("<HTML><BODY>Not Found</BODY></HTML>")
			.getBytes();

	private static final String formPagePrefix = "<html><body onload=\"document.forms[0].elements[0].focus()\">"
			+ "<form name=\"message\" method=\"post\" action=\"";

	private static final String formPageMiddle = "\">"
			+ "<input name=\"messageText\" type=\"text\" style=\"width:100%\"><br>"
			+ "Nick: <input readonly name=\"nickName\" type=\"text\" size=\"10\" value=\"";

	private static final String formPageSuffix = "\">"
			+ "<input name=\"Submit\" value=\"Enviar\" type=\"submit\">"
			+ "</form>" + "</body></html>";

	private static final String mainPagePrefix = "<html><head>"
			+ "<META HTTP-EQUIV=\"REFRESH\" CONTENT=\"3;URL=";

	private static final String mainPageMiddle = "\"></head><body>"
			+ "<textarea style=\"width:100%;height:100%\"  readonly>";

	private static final String mainPageSuffix = "</textarea>"
			+ "</body></html>";

	protected Map<String, List<String>> chatMessages;

	protected int serverPort;

	public ChatUtilities( int serverPort) {
		this.serverPort = serverPort;
		chatMessages = new HashMap<String,List<String>>();
	}

	/**
	 * Adiciona a mensagem message, enviada pelo utilicador nickName, ano chat
	 * room chatName
	 */
	public void addChatMessage( String chatName, String nickName,
			String message) {
		List<String> msgs = chatMessages.get( chatName);
		if( msgs == null) {
			msgs = new LinkedList<String>();
			chatMessages.put( chatName, msgs);
		}
		msgs.add( nickName + " > " + message);
	}

	/**
	 * Devolve um input stream com o conteudo HTML que pode ser usado como
	 * resposta em caso de erro.
	 */
	public InputStream getErrorPageInputStream() {
		return new ByteArrayInputStream( errorPage);
	}

	/**
	 * Devolve um input stream com o conteudo HTML da pagina do form
	 */
	public InputStream getFormPageInputStream( String chatName,
			String nickName) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append( formPagePrefix);
		buffer.append( "http://");
		buffer.append( InetAddress.getLocalHost().getHostName());
		buffer.append( ":");
		buffer.append( "" + serverPort);
		buffer.append( chatName);
		buffer.append( formPageMiddle);
		buffer.append( nickName);
		buffer.append( formPageSuffix);
		return new ByteArrayInputStream( buffer.toString().getBytes());
	}

	/**
	 * Devolve um input stream com o conteudo HTML da pagina principal
	 */
	public InputStream getMainPageInputStream( String chatName)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append( mainPagePrefix);
		buffer.append( "http://");
		buffer.append( InetAddress.getLocalHost().getHostName());
		buffer.append( ":");
		buffer.append( "" + serverPort);
		buffer.append( chatName);
		buffer.append( mainPageMiddle);

		List<String> msgs = chatMessages.get( chatName);
		if( msgs != null) {
			Iterator<String> it = msgs.iterator();
			while( it.hasNext()) {
				buffer.append( it.next());
				buffer.append( "\r\n");
			}
		}
		buffer.append( mainPageSuffix);
		return new ByteArrayInputStream( buffer.toString().getBytes());
	}

}
