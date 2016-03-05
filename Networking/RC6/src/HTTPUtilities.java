import java.net.*;
import java.util.*;
import java.io.*;


/**
 * Classe com metodos auxiliares para processar pedidos HTTP
 */
public class HTTPUtilities {


	/*
	* Consome uma linha de texto do canal de entrada dado terminada por CR e retorna-a como uma String
	*/
	public static String readLine( InputStream is ) throws IOException {
		StringBuffer sb = new StringBuffer() ;
		
		int c ;
		while( (c = is.read() ) >= 0 ) {
			if( c == '\r' ) continue ;
			if( c == '\n' ) break ;
			sb.append( new Character( (char)c) ) ;
		}
		return sb.toString() ;
	} 
	/**
	 * A partir da linha de pedido HTTP devolve um array com o metodo invocado
	 * na posicao 0, o URL na posicao 1, e a versao na posicao 2 Por ex., para
	 * "GET http://www.unl.pt/index.html HTTP/1.0", devovle [0]->"GET";
	 * [1]->"http://www.unl.pt/index.html"; [2]->"HTTP/1.0" Caso request nao
	 * seja um pedido HTTP valido, devolve null.
	 * 
	 * NOTA: Caso a versao HTTP nao seja especificada, [2]-> null
	 */
	public static String[] parseHttpRequest( String request) {
		String[] result = new String[3];
		int pos0 = request.indexOf( ' ');
		if( pos0 == -1)
			return null;
		result[0] = request.substring( 0, pos0).trim();
		pos0++;
		int pos1 = request.indexOf( ' ', pos0);
		if( pos1 == -1) {
			result[1] = request.substring( pos0).trim();
			result[2] = null;
		} else {
			result[1] = request.substring( pos0, pos1).trim();
			result[2] = request.substring( pos1 + 1).trim();
		}
		return result;
	}

	/**
	 * A partir de um header HTTP, devovle um array com o nome do header na
	 * posicao 0 e o valor indicado na posicao 1 Por ex., para "Connection:
	 * Keep-alive", devovle [0]->"Connection"; [1]->"Keep-alive" Caso header nao
	 * seja um header HTTP valido, devolve null.
	 */
	public static String[] parseHttpHeader( String header) {
		String[] result = new String[2];
		int pos0 = header.indexOf( ':');
		if( pos0 == -1)
			return null;
		result[0] = header.substring( 0, pos0).trim();
		result[1] = header.substring( pos0 + 1).trim();
		return result;
	}

	/**
	 * A partir de uma string com o conteúdo de um form submetido no formato
	 * application/x-www-form-urlencoded, devolve um objecto do tipo Properties,
	 * associando a cada elemento do form o seu valor
	 */
	public static Properties parseHttpPostContents( String contents)
			throws IOException {
		Properties props = new Properties();
		Scanner scanner = new Scanner( contents).useDelimiter( "&");
		while( scanner.hasNext()) {
			Scanner inScanner = new Scanner( scanner.next()).useDelimiter( "=");
			String propName = URLDecoder.decode( inScanner.next(), "UTF-8");
			String propValue = "";
			try {
				propValue = URLDecoder.decode( inScanner.next(), "UTF-8");
			} catch( Exception e) {
				// do nothing
			}
			props.setProperty( propName, propValue);
		}
		return props;
	}

}
