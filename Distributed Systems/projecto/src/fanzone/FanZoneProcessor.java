package fanzone;

import java.rmi.RemoteException;
import java.util.*;
import java.io.*;


/**
 * Interface das operacoes a implementar no aplicacao local.
 * Estas operacoes sao invocadas pelo interface em consequencia da utilizacao da 
 * aplicacao pelo utilizador
 */
public interface FanZoneProcessor
{
	//=====================================================================================
	// Operacoes genericas
	//=====================================================================================
	/**
	 * Devolve verdadeiro se o utilizador indicado esta autorizado a utilizar o sistema
	 * @throws RemoteException 
	 */
	boolean login( String user, String pwd) throws RemoteException;
	/**
	 * Cria comunidade com o nome e password indicadas
	 * @throws RemoteException 
	 */
	boolean createCommunity( String comm, String pwd) throws RemoteException;
	/**
	 * Junta-se a uma comunidade com o nome e password indicadas 
	 * (o que e a etraInfo deve ser definido em cada sistema)
	 * @throws RemoteException 
	 */
	boolean joinCommunity( String comm, String pwd, String extraInfo) throws RemoteException;
	/**
	 * Sai de uma dada comunidade
	 * @throws RemoteException 
	 */
	boolean leaveCommunity( String comm) throws RemoteException;
	/**
	 * Lista comunidades a que o utilizador local pertence
	 * @throws RemoteException 
	 */
	List<String> listCommunities( ) throws RemoteException;
	/**
	 * Lista de alternativas de importacao de eventos
	 * NOTA: para adicionar nova opcao deve alterar resultado desta funcao
	 */
	String[] listEventImports( );
	/**
	 * Lista de alternativas de importacao de recursos
	 * NOTA: para adicionar nova opcao deve alterar resultado desta funcao
	 */
	String[] listResourceImports( );

	
	//=====================================================================================
	// Operacoes sobre eventos
	//=====================================================================================
	/**
	 * Lista eventos de uma dada comunidade. 
	 * Caso comm == "All", apresenta os recursos de todas as comunidades a que o utilizador
	 * pertence
	 * @throws RemoteException 
	 */
	List<EventInfo> listEvents( String comm, boolean localOnly) throws RemoteException;
	/**
	 * Devolve lista de potenciais eventos a importar.
	 * @param imp Tipo de importacao
	 * @param query Query apresentada na importacao
	 * @throws RemoteException 
	 */
	List<EventInfo> searchEventImport( String imp, String query) throws RemoteException;
	/**
	 * Executa importacao de evento
	 * @throws RemoteException 
	 */
	void doEventImport( EventInfo event) throws RemoteException;
	/**
	 * Devolve lista de potenciais recursos a importar.
	 * @param imp Tipo de importacao
	 * @param query Query apresentada na importacao
	 * @throws RemoteException 
	 */
	List<ResourceInfo> searchResourceImport( String imp, String query) throws RemoteException;
	/**
	 * Executa importacao de recurso. Se necessario copia recurso para directoria local
	 * @param comm 
	 * @throws RemoteException 
	 * @throws FileNotFoundException 
	 */
	void doResourceImport( EventInfo event, String comm, ResourceInfo info, boolean makeCopy) throws RemoteException, FileNotFoundException;

	/**
	 * Adiciona evento a uma dada comunidade
	 * @throws RemoteException 
	 */
	boolean addToCommunity( String comm, EventInfo event) throws RemoteException;
	/**
	 * Remove evento de uma dada comunidade
	 * @throws RemoteException 
	 */
	boolean remFromCommunity( String comm, String eventID) throws RemoteException;
	/**
	 * Pesquisa entre as fotografias de uma dada comunidade.
	 * Caso comm == "All", a pesquisa deve ser efectuada em todas as comunidades a que o 
	 * utilizador pertence
	 * @throws RemoteException 
	 */
	List<EventInfo> searchEvents( String comm, boolean localOnly, String query) throws RemoteException;
	/**
	 * Devolve InputStream para o recurso indicado  
	 */
	InputStream getResourceStream( String resourceID, ResourceInfo info );
}
