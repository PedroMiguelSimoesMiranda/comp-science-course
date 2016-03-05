package fanzone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.xml.namespace.QName;

import sd1.security.Key;
import security.CipherInputStream;
import security.CipherOutputStream;

import clt.ws.FanServerWS;
import clt.ws.FanServerWSService;

public class FanZoneAppWS extends FanZoneApp {

	private static String REMOTE_SERVICE_IP = "localhost";
	private static final String PORT_AND_SERVICE = ":7070/FanZoneServerWS";


	private clt.ws.User currentWSUser;
	private FanServerWSService wsserver;
	private FanServerWS wsport;

	protected FanZoneAppWS( int port, String webappdir, String datadir) {
		super(port, webappdir);
		try 
		{
			wsserver = 	new FanServerWSService
					(
							new URL("http://"+ REMOTE_SERVICE_IP + PORT_AND_SERVICE), 
							new QName("http://fanserverinfo/", "FanServerWSService")
							);
			wsport = wsserver.getFanServerWSPort();
		} 
		catch ( Exception e )
		{
			e.printStackTrace();
			System.err.println("NAO FOI POSSIVEL ENCONTRAR O SERVIDOR");
			System.exit(0);
		}
	}



	@Override
	public boolean login(String user, String pwd) throws RemoteException {
		String ip ="10.171.240.128";
		try 
		{
			ip = InetAddress.getLocalHost().getHostAddress();
		} 
		catch (Exception e){}

		this.currentUser = new User(user, pwd, ip);

		this.currentWSUser = new clt.ws.User();
		this.currentWSUser.setUsername(user);
		this.currentWSUser.setPassword(pwd);
		this.currentWSUser.setIp("");

		boolean logged = wsport.login(user, pwd, ip);
		if( logged && this.localEvents.get(user)== null )
		{
			this.localEvents.put(user, new TreeMap<String, List<EventInfo>>());
			this.writeInFile();
		}
		return logged;
	}

	@Override
	public boolean createCommunity(String comm, String pwd)
			throws RemoteException {
		boolean created = wsport.createCommunity(currentWSUser, comm, pwd, "");
		if( created )
		{
			this.localEvents.get(currentUser.getUsername()).put(comm, new ArrayList<EventInfo>());
			new File(System.getProperty("user.dir") + COMMUNITIESDIR + comm).mkdirs();
			this.writeInFile();
		}
		return created;
	}

	@Override
	public boolean joinCommunity(String comm, String pwd, String extraInfo)
			throws RemoteException {
		boolean joined = wsport.joinCommunity(currentWSUser, comm, pwd, extraInfo);
		if( joined ) 
		{
			this.localEvents.get(currentUser.getUsername()).put(comm, new ArrayList<EventInfo>());
			new File(System.getProperty("user.dir") + COMMUNITIESDIR + comm).mkdirs();
			this.writeInFile();
		}
		return joined;
	}

	@Override
	public boolean leaveCommunity(String comm) throws RemoteException {
		boolean left =  wsport.leaveCommunity(currentWSUser, comm);
		if ( left ) 
		{
			this.localEvents.get(currentUser.getUsername()).remove(comm);
			this.writeInFile();
		}
		return left;
	}

	@Override
	public List<String> listCommunities() throws RemoteException {
		return wsport.listCommunities(currentWSUser);
	}

	@Override
	public List<EventInfo> listEvents(String comm, boolean localOnly)
			throws RemoteException {
		List<EventInfo> listOfEvents = new ArrayList<EventInfo>();

		if( !localOnly )
		{
			List<clt.ws.EventInfo> events;

			if( comm.equalsIgnoreCase("All"))
				events = this.wsport.listAllEvents(currentWSUser);
			else
				events = this.wsport.listEventsFromCommunity(currentWSUser, comm);

			for( clt.ws.EventInfo ev : events)
			{
				ArrayList<clt.ws.ResourceInfo>  theResources = (ArrayList<clt.ws.ResourceInfo>) ev.getResources();
				ArrayList<ResourceInfo> newResources = new ArrayList<ResourceInfo>();
				for ( clt.ws.ResourceInfo res : theResources)
				{
					ResourceInfo newRes;
					URL url = null;
					if( res.isExternal())
						newRes = new RemoteResourceInfo();
					else 
						newRes = new LocalResourceInfo();

					try 
					{
						url = new URL (res.getMainURL());
					} catch ( Exception e) {System.err.println("URL MAL FORMADO");}

					newRes.setFilename(res.getFilename());
					newRes.setCommunity(res.getCommunity());
					newRes.setExternal(res.isExternal());
					newRes.setLocal(res.isLocal());
					newRes.setPath(res.getPath());
					newRes.setUrls(res.getUrls());
					newRes.setPassword(res.getPassword());
					newRes.setMainURL(url);
					newResources.add(newRes);

				}
				EventInfo x = new EventClass(ev.getId(), ev.getName(), ev.getDescription(), newResources );
				x.setCommunity(ev.getCommunity());
				listOfEvents.add(x);
			}
		}

		else
		{
			Iterator<EventInfo> it;
			if (comm.equalsIgnoreCase("All"))
			{
				Iterator<String> comus = this.localEvents.get(currentUser.getUsername()).keySet().iterator();
				while ( comus.hasNext() )
				{
					it = this.localEvents.get(currentUser.getUsername()).get(comus.next()).iterator();
					while (it.hasNext())
						listOfEvents.add(it.next());
				}
			}
			else
			{
				it = this.localEvents.get(currentUser.getUsername()).get(comm).iterator();
				while (it.hasNext())
					listOfEvents.add(it.next());
			}
		}

		return listOfEvents;

	}

	@Override
	public void doEventImport(EventInfo event) throws RemoteException {
		// TODO Auto-generated method stub
	}

	public void doResourceImport( EventInfo event, String comm, ResourceInfo info, boolean makeCopy ) throws RemoteException, FileNotFoundException {
		System.err.println("EVENT COMM: " + event.getCommunity());
		System.err.println("COMM: " + comm);
		System.err.println("INFO COMM: " + info.getCommunity());

		info.setCommunity(event.getCommunity());
		System.err.println( "do resource import : event = " + event + "; resource name = " + info.getFilename() + "; makeCopy = " + makeCopy);
		System.err.println("INFO PATH: "+info.getPath()+"; ID:");
		System.out.println(info.getPath());

		String inFile =info.getPath();
		String outFile = System.getProperty("user.dir")  + COMMUNITIESDIR + info.getCommunity() +"/" + info.getFilename();
		(new File(System.getProperty("user.dir")  + COMMUNITIESDIR + info.getCommunity())).mkdirs();
		InputStream in = null;

		clt.ws.ResourceInfo realResource;

		if ( info.isExternal() )// info.isInInternet()
		{ 
			realResource = new clt.ws.RemoteResourceInfo();
			try 
			{
				in = new URL(info.getHTTPURL()).openStream();
			} 
			catch (MalformedURLException e) { e.printStackTrace();} 
			catch (IOException e) { e.printStackTrace();}

			if(makeCopy)
			{
				try 
				{
					Key secretKey = sd1.security.SymetricKey.createKey(info.getPassword());
					OutputStream out = new CipherOutputStream( new FileOutputStream( new File(outFile)), secretKey);
					utilities.CopyFile.copyfile(in, out);
				}
				catch( Exception e ) {System.err.println("ERRO AO ESCREVER"); }
			}
			info.setExternal(true);
		}
		else
		{
			realResource = new clt.ws.LocalResourceInfo();
			if (!inFile.equals(outFile))
			{
				in = new FileInputStream( new File(inFile) );
				try 
				{
					Key secretKey = sd1.security.SymetricKey.createKey(info.getPassword());
					OutputStream out = new CipherOutputStream( new FileOutputStream( new File(outFile)), secretKey);
					utilities.CopyFile.copyfile(in, out);
				}  catch (Exception e) {System.err.println("ERRO AO ESCREVER");	}
				info.setPath(System.getProperty("user.dir") + COMMUNITIESDIR + comm +"/" + info.getFilename());
				info.setLocal(false);
			}
		}

		realResource.setCommunity(info.getCommunity());
		realResource.setExternal(info.isExternal());
		realResource.setFilename(info.getFilename());
		realResource.setLocal(info.isLocal());
		realResource.setMainURL(info.getHTTPURL());
		realResource.setPath(info.getPath());
		realResource.setPassword(info.getPassword());
		event.addResource(info);
		wsport.addResourceToEvent(currentWSUser, info.getCommunity(), event.getID(), realResource);
		this.writeInFile();
	}


	@Override
	public boolean addToCommunity(String comm, EventInfo event)
			throws RemoteException {
		event.setCommunity(comm);

		clt.ws.EventClass x = new clt.ws.EventClass();
		x.setName(event.getName());
		x.setDescription(event.getDescription());
		x.setCommunity(event.getCommunity());
		x.setId(event.getId());
		List<EventInfo> lstev = this.localEvents.get(currentUser.getUsername()).get(comm);
		if (lstev == null)
		{
			lstev = new ArrayList<EventInfo>();
			this.localEvents.get(currentUser.getUsername()).put(comm, lstev);
		}
		lstev.add(event);
		this.writeInFile();
		return wsport.addEventToCommunity(currentWSUser, comm, x);
	}

	@Override
	public boolean remFromCommunity(String comm, String eventID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EventInfo> searchEvents(String comm, boolean localOnly,
			String query) throws RemoteException {
		if (localOnly)
			return searchLocalEvents(comm,query);

		List<clt.ws.EventInfo>  events = wsport.searchPhotos(currentWSUser, comm, query);
		List<EventInfo> toReturn = new ArrayList<EventInfo>();

		for( clt.ws.EventInfo ev : events){
			ArrayList<clt.ws.ResourceInfo>  theResources = (ArrayList<clt.ws.ResourceInfo>) ev.getResources();
			ArrayList<ResourceInfo> newResources = new ArrayList<ResourceInfo>();
			for ( clt.ws.ResourceInfo res : theResources)
			{
				ResourceInfo newRes;
				URL url = null;
				if( res.isExternal())
					newRes = new RemoteResourceInfo();
				else 
					newRes = new LocalResourceInfo();

				try 
				{
					url = new URL (res.getMainURL());
				} catch ( Exception e) {System.err.println("URL MAL FORMADO");}

				newRes.setFilename(res.getFilename());
				newRes.setCommunity(res.getCommunity());
				newRes.setExternal(res.isExternal());
				newRes.setLocal(res.isLocal());
				newRes.setPath(res.getPath());
				newRes.setUrls(res.getUrls());
				newRes.setMainURL(url);
				newRes.setPassword(res.getPassword());
				newResources.add(newRes);

			}
			EventInfo x = new EventClass(ev.getId(), ev.getName(), ev.getDescription(), newResources );
			x.setCommunity(ev.getCommunity());
			toReturn.add(x);
		}
		return toReturn;
	}

	@Override
	public InputStream getResourceStream(String resourceID, ResourceInfo info) {
		try {
			if( info == null )								// SE POR ALGUMA RAZAO O ResourceInfo E' NULL
				return new FileInputStream( new File(resourceID) );
			if( info.isLocal())								// E' LOCAL, ENTAO VAMOS BUSCA'-LO
				return new FileInputStream( info.getPath() );
			if( !(new File(System.getProperty("user.dir") + COMMUNITIESDIR + info.getCommunity() + "/" + info.getName()).exists()))
			{
				this.downloadResource(COMMUNITIESDIR + info.getCommunity() +"/" + info.getName()); // O RECURSO ESTA' NOUTRO PC
				this.wsport.tellIHaveResource(currentWSUser, COMMUNITIESDIR + info.getCommunity() +"/" + info.getName());
			}
			Key secretKey = sd1.security.SymetricKey.createKey(info.getPassword());
			return new CipherInputStream(
					new FileInputStream( new File(System.getProperty("user.dir") + COMMUNITIESDIR + info.getCommunity() + "/" + info.getName())), secretKey);

		} 
		catch( FileNotFoundException e) {} 
		catch (Exception e) {System.err.println("ERRO AO OBTER STREAM");}
		return null;
	}

	private void downloadResource(String resource) {

		List<String> ips = null;
		try 
		{
			ips = wsport.getPeer(currentWSUser, resource);
		} 
		catch (Exception e1) 
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
		if( args.length != 1) 
		{
			System.err.println("Usage: java FanZoneAppWS server_ip");
			System.exit(0);
		}
		REMOTE_SERVICE_IP = args[0];

		int port = 8070;
		String webappdir = "webapp";
		String datadir = "data";
		if( args.length > 1)
			port = Integer.parseInt( args[1]);
		if( args.length > 2)
			webappdir = args[2];
		if( args.length > 3)
			datadir = args[3];

		new File(datadir).mkdirs();
		new FanZoneAppWS( port, webappdir, datadir).doit();
	}

}
