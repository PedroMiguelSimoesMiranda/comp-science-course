package fanzone;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
import java.io.*;

import sd1.security.Key;
import security.CipherInputStream;
import security.CipherOutputStream;

import fanserverinfo.FanServerRMI;
import fanserverinfo.FanServerRemote;


public class FanZoneAppRMI extends FanZoneApp
{
	private static String REMOTE_SERVICE_IP = "localhost";
	private FanServerRemote servidor;

	protected FanZoneAppRMI( int port, String webappdir, String datadir) {
		super(port, webappdir);
		this.port = port;
		this.webappdir = webappdir;
		try 
		{
			servidor = (FanServerRemote) Naming.lookup("//" + REMOTE_SERVICE_IP + FanServerRMI.SERVICENAME);
		} 
		catch ( Exception e )
		{
			e.printStackTrace();
			System.err.println("NAO FOI POSSIVEL ENCONTRAR O SERVIDOR");
			System.exit(0);
		}
	}

	/*===================================================================================
	 * Metodos da interface FanZoneProcessor
	===================================================================================*/
	@Override
	public boolean login( String user, String pwd ) throws RemoteException {
		String ip ="";
		try 
		{
			ip = InetAddress.getLocalHost().getHostAddress();
		} 
		catch (UnknownHostException e) 
		{e.printStackTrace();}

		this.currentUser = new User( user, pwd ,ip);
		boolean logged = servidor.login( user, pwd, ip );
		if ( logged && this.localEvents.get(user)== null )
			this.localEvents.put(user, new TreeMap<String, List<EventInfo>>());
		this.writeInFile();
		return logged;
	}

	@Override
	public boolean createCommunity( String comm, String pwd ) throws RemoteException {
		System.err.println( "create community : comm = " + comm + "; pwd = " + pwd);
		boolean created = servidor.createCommunity( currentUser, comm, pwd, "");
		if ( created )
		{
			this.localEvents.get(currentUser.getUsername()).put(comm, new ArrayList<EventInfo>());
			new File(System.getProperty("user.dir") + COMMUNITIESDIR + comm).mkdirs();
			this.writeInFile();
		}
		return created;
	}

	@Override
	public boolean joinCommunity( String comm, String pwd, String url ) throws RemoteException {
		System.err.println( "join community : comm = " + comm + "; pwd = " + pwd + "; url = " + url);
		boolean joined = servidor.joinCommunity( currentUser, comm, pwd, url );
		if( joined ) 
		{
			this.localEvents.get(currentUser.getUsername()).put(comm, new ArrayList<EventInfo>());
			new File(System.getProperty("user.dir") + COMMUNITIESDIR + comm).mkdirs();
			this.writeInFile();
		}
		return joined;
	}

	@Override
	public boolean leaveCommunity( String comm ) throws RemoteException {
		System.err.println( "leave community : comm = " + comm);
		boolean left = servidor.leaveCommunity( currentUser, comm );
		if (left)
		{
			//deleteDir(new File(System.getProperty("user.dir") + COMMUNITIESDIR + comm));
			this.localEvents.get(currentUser.getUsername()).remove(comm);
			this.writeInFile();
		}
		return left;
	}

	@Override
	public List<String> listCommunities() throws RemoteException {
		System.err.println( "list community :");		
		SortedSet<String> comm = servidor.listCommunities( currentUser );

		Iterator<String> it = comm.iterator();
		List<String> lst = new ArrayList<String>();
		// FAZER ESTE PROCEDIMENTO APENAS NO LOGIN
		while( it.hasNext() )
		{
			String next = it.next();
			lst.add( next );
			if (!this.localEvents.get(currentUser.getUsername()).containsKey(next))
				this.localEvents.get(currentUser.getUsername()).put(next, new ArrayList<EventInfo>());
		}
		return lst;
	}
	/**
	 * Lista alternativas de importacao de eventos
	 * NOTA: para adicionar nova opcao deve alterar resultado desta funcao
	 */
	@Override
	public String[] listEventImports( ) {
		System.err.println( "list event imports");
		return importEventLst;
	}
	/**
	 * Lista alternativas de importacao de recursos
	 * NOTA: para adicionar nova opcao deve alterar resultado desta funcao
	 */
	@Override
	public String[] listResourceImports( ) {
		System.err.println( "list resource imports");
		return importResourceLst;
	}

	/**
	 * Adiciona evento a lista de eventos
	 * @throws RemoteException 
	 */
	@Override
	public void doEventImport(EventInfo event) throws RemoteException {
		System.err.println( "do event import : event = " + event);
		//this.servidor.importEvent(currentUser, event);
	}

	@Override
	public boolean addToCommunity( String comm, EventInfo event ) throws RemoteException {
		System.err.println( "addToCommunity : comm = " + comm + "; event = " + event);
		event.setCommunity(comm);
		List<EventInfo> lstev = this.localEvents.get(currentUser.getUsername()).get(comm);
		if (lstev == null)
		{
			lstev = new ArrayList<EventInfo>();
			this.localEvents.get(currentUser.getUsername()).put(comm, lstev);
		}
		lstev.add(event);
		this.writeInFile();
		return this.servidor.addEventToCommunity(currentUser, comm, event);
	}

	/**
	 * Executa importacao de recurso. Se necessario copia recurso para directoria local
	 * @throws RemoteException 
	 * @throws FileNotFoundException 
	 */
	@Override
	public void doResourceImport( EventInfo event, String comm, ResourceInfo info, boolean makeCopy ) throws RemoteException, FileNotFoundException {
		info.setCommunity(event.getCommunity());
		System.err.println( "do resource import : event = " + event + "; resource name = " + info.getName() + "; makeCopy = " + makeCopy);
		System.err.println("INFO PATH: "+info.getPath()+"; ID:");
		System.out.println(info.getPath());
		String inFile =info.getPath();

		String outFile = System.getProperty("user.dir")  + COMMUNITIESDIR + info.getCommunity() +"/" + info.getName();
		(new File(System.getProperty("user.dir")  + COMMUNITIESDIR + info.getCommunity())).mkdirs();
		InputStream in = null ;
		if ( info instanceof RemoteResourceInfo )// info.isInInternet()
		{
			try 
			{
				in = new URL(info.getHTTPURL()).openStream();
			} 
			catch (MalformedURLException e) { e.printStackTrace();} 
			catch (IOException e) { e.printStackTrace();}

			if(makeCopy)
			{
				try {
					Key secretKey = sd1.security.SymetricKey.createKey(info.getCommunity());
					OutputStream out = new CipherOutputStream( new FileOutputStream( new File(outFile)), secretKey);
					utilities.CopyFile.copyfile(in, out);
				} catch (Exception e) {System.err.println("ERRO AO ESCREVER");	}

			}
			info.setExternal(true);
		}
		else
		{
			if (!inFile.equals(outFile))
			{
				in = new FileInputStream( new File(inFile) );
				Key secretKey;
				try {
					secretKey = sd1.security.SymetricKey.createKey(info.getPassword());
					OutputStream out = new CipherOutputStream( new FileOutputStream( new File(outFile)), secretKey);
					utilities.CopyFile.copyfile(in, out);
				} catch (Exception e) {System.err.println("ERRO AO ESCREVER");	}
				info.setPath(System.getProperty("user.dir") + COMMUNITIESDIR + info.getCommunity() +"/" + info.getName());
				info.setLocal(false);
			}
		}
		event.addResource(info);
		servidor.addResourceToEvent(currentUser, info.getCommunity(), event.getID(), info);
		this.writeInFile();
	}


	@Override
	public List<EventInfo> listEvents( String comm, boolean localOnly ) throws RemoteException {
		System.err.println( "listEvents : comm = " + comm + "; localOnly = " + localOnly);
		// TODO: listar eventos locais
		List<EventInfo> lst = new ArrayList<EventInfo>();
		Iterator<EventInfo> it;

		if (!localOnly)
		{
			if (comm.equalsIgnoreCase("All"))
				it = servidor.listAllEvents(currentUser).iterator();
			else
				it = servidor.listEventsFromCommunity(currentUser, comm).iterator();
			while (it.hasNext())
				lst.add(it.next());	
		}

		else
		{
			if (comm.equalsIgnoreCase("All"))
			{
				Iterator<String> comus = this.localEvents.get(currentUser.getUsername()).keySet().iterator();
				while ( comus.hasNext() )
				{
					it = this.localEvents.get(currentUser.getUsername()).get(comus.next()).iterator();
					while (it.hasNext())
						lst.add(it.next());
				}
			}
			else
			{
				it = this.localEvents.get(currentUser.getUsername()).get(comm).iterator();
				while (it.hasNext())
					lst.add(it.next());
			}
		}

		return lst;


	}

	@Override
	public boolean remFromCommunity(String comm, String eventID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EventInfo> searchEvents( String comm, boolean localOnly, String query ) throws RemoteException {
		System.err.println( "searchEvents : comm = " + comm + "; localOnly = " + localOnly + "; query = " + query);
		// TODO: executar a pesquisa solicitada (possivelmente a partir dum servidor remoto)
		if (localOnly)
			return searchLocalEvents(comm,query);//return listEvents(comm, localOnly);
		return servidor.searchPhotos(this.currentUser, comm, query);
	}

	@Override
	public InputStream getResourceStream( String id, ResourceInfo info) {
		System.err.println( "get resource stream : id = " + id + "; resource = " + info);
		//TODO: obter recurso - implementacao actual assume que existe copis local do recurso
		try {
			if( info == null )								// SE POR ALGUMA RAZAO O ResourceInfo E' NULL
				return new FileInputStream( new File(id) );
			if( info.isLocal())								// E' LOCAL, ENTAO VAMOS BUSCA'-LO
				return new FileInputStream( info.getPath() );
			if( !(new File(System.getProperty("user.dir") + COMMUNITIESDIR + info.getCommunity() + "/" + info.getName()).exists())) // O RECURSO ESTA' NOUTRO PC
			{
				this.downloadResource(info.getCommunity() +"/" + info.getName());
				this.servidor.tellIHaveResource(currentUser, info.getCommunity() +"/" + info.getName());
			}
			Key secretKey = sd1.security.SymetricKey.createKey(info.getPassword());
			return new CipherInputStream(
					new FileInputStream( new File(System.getProperty("user.dir") + COMMUNITIESDIR + info.getCommunity() + "/" + info.getName())), secretKey);
		} 
		catch( FileNotFoundException e) {	} 
		catch (Exception e) {System.err.println("ERRO AO OBTER STREAM");}
		return null;
	}

	private void downloadResource(String resource) {

		ArrayList<String> ips = null;
		try 
		{
			ips = servidor.getPeer(currentUser, resource);
		} 
		catch (RemoteException e1) 
		{ 
			System.err.println("ERRO AO CONTACTAR SERVIDOR");
			return;
		}
		Iterator<String> iter = ips.iterator();
		while (iter.hasNext())
		{
			try 
			{
				this.downloadResourceFromIP(resource, iter.next());
				return;
			} 
			catch (Exception e) 
			{
				System.err.println("ERRO AO OBTER RECURSO. TENTAR OUTRO PEER");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main( String[] args) {
		if( args.length < 1) 
		{
			System.err.println("Usage: java FanZoneAppWS server_ip");
			System.exit(0);
		}
		REMOTE_SERVICE_IP = args[0];

		int port = 8080;
		String webappdir = "webapp";
		String datadir = "data";

		if( args.length > 1)
			port = Integer.parseInt( args[1]);
		if( args.length > 2)
			webappdir = args[2];
		if( args.length > 3)
			datadir = args[3];

		new File(datadir).mkdirs();
		new FanZoneAppRMI( port, webappdir, datadir).doit();
	}
}