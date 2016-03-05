package fanserverinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import lfm.LastFMEvents;
import lfm.LastFMImages;


import exceptions.UserNotBelongsToCommunityException;
import exceptions.UserNotLoggedException;
import fanzone.Community;
import fanzone.CommunityClass;
import fanzone.EventInfo;
import fanzone.ResourceInfo;
import fanzone.User;

public class FanServerClass implements FanServer {

	// SERIALIZACAO
	private static final long serialVersionUID = 1L;
	public static final String FILENAME = "fanserver.dat";

	// ESTADO DOS CLIENTE (UTILIZADORES, COMUNDIDADES..)
	private Map<String, User> users;
	private SortedMap<String, Community> communities;
	private Map<String, SortedSet<String>> joinedCommunities;
	private Map<String, List<String>> peers;

	public FanServerClass() {
		this.users = new HashMap<String, User>();
		this.communities = new TreeMap<String, Community>();
		this.joinedCommunities = new HashMap<String, SortedSet<String>>();
		this.peers = new HashMap<String, List<String>>();
	}


	private boolean checkIfUserExists( String user, String pwd ) {
		User real = this.users.get(user);
		if ( real == null )
			return false;
		return true;
	}

	private boolean checkUser( String user, String pwd ) {
		User real = this.users.get(user);
		if ( real == null )
			return false;
		User toCheck = new User( user, pwd,"" );
		if ( toCheck.equals(real) )
			return true;
		return false;
	}

	private boolean checkIfUserIsLogged( User user ) {
		User real = this.users.get(user.getUsername());
		if ( real == null )
			return false;
		if ( real.equals(user) )
			return real.logged();
		return false;
	}

	private void registUser( String user, String pwd, String ip, boolean login ){
		User u = new User( user, pwd, ip );
		this.users.put( user ,u );
		this.joinedCommunities.put( user, new TreeSet<String>() );
		if (login)
			u.login();
		RunServer.writeInFile();
	}

	private boolean userBelongsToCommunity( String user, String comm ){
		SortedSet<String> commFromUser = this.joinedCommunities.get(user);
		if ( commFromUser == null )
			return false;
		return commFromUser.contains(comm);
	}

	@Override
	public boolean login(String user, String pwd, String ip) {
		if ( this.checkIfUserExists(user, pwd) ) 
			if ( this.checkUser(user, pwd) )
			{
				this.users.get(user).setIp(ip);
				this.users.get(user).login();
			}
			else 
				return false;
		else 
			this.registUser(user, pwd, ip, true);
		return true;
	}

	@Override
	public boolean createCommunity(User user, String comm, String pwd,
			String url) throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();
		if ( comm.equalsIgnoreCase("All") || this.communities.containsKey(comm) )
			return false;
		// CREATING THE COMMUNITY
		User realUser = this.users.get(user.getUsername());
		Community c = new CommunityClass( comm, pwd );
		c.join( realUser.getUsername() );
		this.communities.put( comm, c );
		// ADDING THE USER TO THE COMMUNITY
		this.joinedCommunities.get( user.getUsername() ).add( comm );
		RunServer.writeInFile();
		return true;
	}

	@Override
	public boolean joinCommunity(User user, String comm, String pwd, String url)
			throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();
		// JOINING THE COMMUNITY
		Community c = this.communities.get( comm );
		if (c == null) return false;
		if ( c.getPassword().equals( pwd ) )
		{
			if (this.joinedCommunities.get(user.getUsername()).contains(comm))
				return false; // The user is already in the community
			c.join( user.getUsername() );
			this.joinedCommunities.get( user.getUsername() ).add( comm );
			RunServer.writeInFile();
			return true;
		}
		return false;
	}

	@Override
	public boolean leaveCommunity(User user, String comm)
			throws UserNotLoggedException, UserNotBelongsToCommunityException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();
		if ( !this.userBelongsToCommunity(user.getUsername(), comm) )
			throw new UserNotBelongsToCommunityException();
		Community c = this.communities.get( comm );
		c.unjoin( user.getUsername() );
		this.joinedCommunities.get( user.getUsername() ).remove( comm );
		RunServer.writeInFile();
		return true;
	}

	@Override
	public SortedSet<String> listCommunities(User user)
			throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();		
		return this.joinedCommunities.get( user.getUsername() );
	}

	@Override
	public List<EventInfo> listAllEvents(User user)
			throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	

		List<EventInfo> evs = new ArrayList<EventInfo>();
		Iterator<String> comms = this.joinedCommunities.get(user.getUsername()).iterator();
		// FOR EACH COMMUNITY
		while (comms.hasNext())
		{
			Community comm = this.communities.get(comms.next());
			Iterator<EventInfo> itcommEvents = comm.getEvents().values().iterator();
			// FOR EACH EVENT FROM A COMMUNITY
			while(itcommEvents.hasNext())
				evs.add(itcommEvents.next());
		}
		return evs;
	}

	@Override
	public boolean addEventToCommunity(User user, String comm, EventInfo event)
			throws UserNotLoggedException, UserNotBelongsToCommunityException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		if ( !this.userBelongsToCommunity(user.getUsername(), comm))
			throw new UserNotBelongsToCommunityException();
		this.communities.get(comm).addEvent(event);
		RunServer.writeInFile();
		return true;
	}

	@Override
	public List<EventInfo> listEventsFromCommunity(User user, String comm)
			throws UserNotLoggedException, UserNotBelongsToCommunityException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		if ( !this.userBelongsToCommunity(user.getUsername(), comm))
			throw new UserNotBelongsToCommunityException();

		List<EventInfo> list = new ArrayList<EventInfo>();
		Community c = this.communities.get(comm);
		if (c == null)
			return list;
		Iterator<EventInfo> it = c.getEvents().values().iterator();
		while( it.hasNext() )
			list.add((EventInfo) it.next());

		return list;
	}

	@Override
	public List<EventInfo> importEventFromLastFM( User user, String query )
			throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		return LastFMEvents.getLFMEvents(query);
	}

	@Override
	public boolean addResourceToEvent(User user, String comm, String eventID,
			ResourceInfo info) throws UserNotLoggedException,
			UserNotBelongsToCommunityException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		if ( !this.userBelongsToCommunity(user.getUsername(), comm))
			throw new UserNotBelongsToCommunityException();
		Community c = this.communities.get(comm);
		EventInfo ev = c.getEventByID(eventID);
		if(ev == null)
			return false;

		List<ResourceInfo> list = ev.getResourceInfo();
		List<String> l = new ArrayList<String>();
		l.add(user.getUsername());
		// POR ALGUMA RAZAO A LISTA ESTA' A FICAR A NULL ... ???
		if( list == null ) 
		{
			ev.setResources(new ArrayList<ResourceInfo>());
			list = ev.getResourceInfo();
		}
		this.peers.put("/communities/" + comm +"/" + info.getName(), l);
		if (!list.contains(info))
		{
			list.add(info);  // MUDEI AQUI
			RunServer.writeInFile();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> getPeer( User user, String resourcePath )
			throws UserNotLoggedException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		ArrayList<String> avaiablePeers = new ArrayList<String>();
		List<String> peerlist = peers.get(resourcePath);
		
		if(peerlist != null)
		for( String s : peerlist )
		{
			User peeruser = this.users.get(s);
			if( peeruser.logged() && !peeruser.equals(user) )
				avaiablePeers.add(peeruser.getIp());
		}
		return avaiablePeers;
	}

	@Override
	public List<EventInfo> searchPhotos( User user, String comm, String query )
			throws UserNotLoggedException, UserNotBelongsToCommunityException {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();	
		if ( !comm.equalsIgnoreCase("All") && !this.userBelongsToCommunity(user.getUsername(), comm))
			throw new UserNotBelongsToCommunityException();

		List<EventInfo>evs=new ArrayList<EventInfo>();
		String newQuery=query.toLowerCase();
		ArrayList<Community>arrL = new ArrayList<Community>();

		if(comm.equalsIgnoreCase("All")) {// SE TODAS AS COMUNIDADES
			Iterator<String>it=this.joinedCommunities.get(user.getUsername()).iterator();
			while(it.hasNext()){
				arrL.add(this.communities.get(it.next()));
			}
			Iterator<Community>itt=arrL.iterator();
			while(itt.hasNext()){//PARA CADA UMA DAS COMUNIDADES SEARCH
				Community nextCom=itt.next();
				Iterator<EventInfo>ie=nextCom.getEvents().values().iterator();
				while(ie.hasNext()){ // PARA CADA UM DOS EVENTOS SEARCH DO RECURSO
					EventInfo e = ie.next();
					Iterator<ResourceInfo>ir=e.getResourceInfo().iterator();
					while(ir.hasNext()){ // VER SE RESOURCE NAME � O QUE ANDAMOS A PROCURA
						ResourceInfo r=ir.next();
						String searchedName=r.getName().toLowerCase();
						if(searchedName.contains(newQuery)){
							evs.add(e);
							break;
						}}}}
		}
		else{
			Iterator<EventInfo>ie=this.communities.get(comm).getEvents().values().iterator();
			while(ie.hasNext()){ // PARA CADA UM DOS EVENTOS SEARCH DO RECURSO
				EventInfo e=(EventInfo) ie.next();
				Iterator<ResourceInfo>ir=e.getResourceInfo().iterator();
				while(ir.hasNext()){ // VER SE RESOURCE NAME � O QUE ANDAMOS A PROCURA
					ResourceInfo r=ir.next();
					String searchedName=r.getName().toLowerCase();
					if(searchedName.contains(newQuery)){
						evs.add(e);
						break;
					}}}}
		return evs;
	}


	@Override
	public List<ResourceInfo> importResourceFromLastFM(User user, String query) {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();
		return LastFMImages.getLFMImages(query);
	}


	@Override
	public void tellIHaveResource(User user, String resource) {
		if ( !this.checkIfUserIsLogged(user) )
			throw new UserNotLoggedException();
		List<String> list = this.peers.get(resource);
		if( list == null)
		{
			list = new ArrayList<String>();
			this.peers.put(resource, list);
		}
		list.add(user.getUsername());
	}

}
