package fanzone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import fanzone.EventInfo;


public class CommunityClass extends Community {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private String url;
	private Set<String> users;
	private Map<String,EventInfo> events;
	
	public CommunityClass(){}
	
	public CommunityClass( String comm, String pwd ){
		this.name = comm;
		this.password = pwd;
		this.users = new HashSet<String>();
		this.events = new HashMap<String, EventInfo>();
	}
	
	public CommunityClass( String comm, String pwd, String url ){
		this( comm, pwd );
		this.url = url;
	}
	
	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public Set<String> getUsers() {
		return users;
	}

	@Override
	public void setUsers(Set<String> users) {
		this.users = users;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setEvents(Map<String, EventInfo> events) {
		this.events = events;
	}

	
	@Override
	public String getName() {
		return this.name;
	}

	
	@Override
	public String getPassword() {
		return this.password;
	}

	
	@Override
	public void join( String user ) {
		this.users.add( user );
	}

	
	@Override
	public void unjoin( String user ) {
		this.users.remove( user );
	}

	
	@Override
	public String getURL() {
		return this.url;
	}

	
	@Override
	public Map<String, EventInfo> getEvents() {
		return this.events;
	}
	
	
	public void setURL( String url ) {
		this.url = url;
	}

	
	@Override
	public void addEvent( EventInfo event ) {
		event.setCommunity( this.name );
		this.events.put( event.getID(), event );
	}

	
	@Override
	public void removeEvent(String eventID) {
		this.events.remove(eventID);
		
	}

	
	@Override
	public EventInfo getEventByID(String eventID) {
		return this.events.get(eventID);
	}

}
