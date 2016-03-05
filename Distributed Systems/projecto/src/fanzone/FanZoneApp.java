package fanzone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lfm.LastFMEvents;
import lfm.LastFMImages;

import fanzone.web.WPWebServer;
import filetransfer.FileClient;
import filetransfer.FileServer;
import filetransfer.FileServerClass;

public abstract class FanZoneApp implements FanZoneProcessor {

	private static final String FILENAME = "FanZoneApp.dat";
	protected int port;
	protected String webappdir;
	private WPWebServer server;

	protected String[] importEventLst = { "Input", "Last.fm" };
	protected String[] importResourceLst = { "File-system", "Last.fm" };

	protected static String COMMUNITIESDIR = "/communities/";
	protected FileServer fs;
	protected Map<String, Map<String, List<EventInfo>>> localEvents;
	protected User currentUser;

	protected FanZoneApp(int port, String webappdir){
		try 
		{
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILENAME)); 
			this.localEvents = (Map<String, Map<String, List<EventInfo>>>) file.readObject();
		}
		catch( Exception e)
		{
			this.localEvents = new TreeMap<String,Map<String,List<EventInfo>>>();
		}
		this.port = port;
		this.webappdir = webappdir;
		try 
		{
			this.fs = new FileServerClass();
			this.fs.start();
		} 
		catch (RemoteException e1) {e1.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
	}

	public void doit() {
		server = new WPWebServer( port, webappdir, this);
		try 
		{
			server.start();		// incia servidor web para fazer apresentacao da informacao
		} catch( FZException e) {
			System.err.println( e.getMessage());
			e.printStackTrace();
		} 
	}

	@Override
	public String[] listEventImports() {
		return this.importEventLst;
	}

	@Override
	public String[] listResourceImports() {
		return this.importResourceLst;
	}


	public List<EventInfo> searchLocalEvents(String com,String query){
		String newQuery=query.toLowerCase();
		List<EventInfo>search=new ArrayList<EventInfo>();
		Iterator<List<EventInfo>> it=this.localEvents.get(currentUser.getUsername()).values().iterator();
		if(com.compareTo("All")==0){
			while(it.hasNext()){
				List<EventInfo> le = it.next();
				Iterator<EventInfo>ie=le.iterator();
				while(ie.hasNext()){
					EventInfo e=ie.next();
					Iterator<ResourceInfo>ir=e.getResourceInfo().iterator();
					while(ir.hasNext()){
						ResourceInfo r=ir.next();
						String name=r.getName().toLowerCase();
						if(name.contains(newQuery)){
							search.add(e);
							break;
						}
					}
				}

			}
		}
		else {
			List<EventInfo> le = localEvents.get(currentUser.getUsername()).get(com);
			Iterator<EventInfo>ie=le.iterator();
			while(ie.hasNext()){
				EventInfo e=ie.next();
				Iterator<ResourceInfo>ir=e.getResourceInfo().iterator();
				while(ir.hasNext()){
					ResourceInfo r=ir.next();
					String name=r.getName().toLowerCase();
					if(name.contains(newQuery)){
						search.add(e);
						break;
					}
				}
			}

		}
		return search;
	}

	// IMPORTACAO DE RECURSOS A PARTIR DO SISTEMA DE FICHEIROS
	protected List<ResourceInfo> importResourceFromFileSystem( String query ) {
		if( query.equals(""))
			query = ".";
		List<ResourceInfo> res = new ArrayList<ResourceInfo>();
		File f = new File( query );
		File []arr = f.listFiles();
		if( arr != null)
			for( int i = 0; i < arr.length; i++) {
				if( arr[i].getName().startsWith("."))
					continue;
				if( arr[i].isDirectory())
					continue;
				ResourceInfo ri = new LocalResourceInfo( arr[i], true);
				res.add( ri );
			}
		return res;
	}


	/**
	 * Devolve lista de potenciais eventos a importar.
	 * @param imp Tipo de importacao
	 * @param query Query apresentada na importacao
	 */
	public List<EventInfo> searchEventImport( String imp, String query) {
		System.err.println( "searchEventImport : imp = " + imp + "; query = " + query);

		if( imp.equalsIgnoreCase( importEventLst[0]))		// INPUT
			return importEventFromInput( query );

		if( imp.equalsIgnoreCase( importEventLst[1]))		// IMPORT FROM LASTFM
			return LastFMEvents.getLFMEvents(query);

		return new ArrayList<EventInfo>();
	}


	/**
	 * Devolve lista de potenciais recursos a importar.
	 * @param imp Tipo de importacao
	 * @param query Query apresentada na importacao
	 * @throws RemoteException 
	 */
	@Override
	public List<ResourceInfo> searchResourceImport( String imp, String query) throws RemoteException {
		System.err.println( "searchResoruceImport : imp = " + imp + "; query = " + query);

		if( imp.equalsIgnoreCase( importResourceLst[0]))		// FILE-SYSTEM
			return importResourceFromFileSystem( query );
		if( imp.equalsIgnoreCase( importResourceLst[1]))	
			return LastFMImages.getLFMImages(query);
		return new ArrayList<ResourceInfo>();
	}

	// IMPORTACAO DOS EVENTOS ATRAVES DO INPUT
	protected List<EventInfo> importEventFromInput( String query ) {
		List<EventInfo> events = new ArrayList<EventInfo>();
		EventInfo ev = new EventClass( "" + new Date().getTime(), "Novo evento", "Descricao novo evento", new ArrayList<ResourceInfo>());
		events.add(ev);
		return events;
	}

	// APAGAR DIRECTORIA (QUANDO COMUNIDADE E' DEIXADA)
	protected static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) 
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
					return false;
			}
		}
		// The directory is now empty -> lets delete it
		return dir.delete();
	}
	
	// DOWNLOAD DE RECURSOs
	protected void downloadResourceFromIP(String resource, String ip) throws Exception {
		FileClient.downloadFile("//"+ip+"/FileServer", resource);
	}
	
	protected void writeInFile(){
		try 
		{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FILENAME));
			file.writeObject(this.localEvents);
			file.flush(); 
			file.close(); 
		} 
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}