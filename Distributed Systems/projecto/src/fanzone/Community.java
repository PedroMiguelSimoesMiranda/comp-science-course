package fanzone;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import fanzone.EventInfo;


public abstract class Community implements Serializable {

	private static final long serialVersionUID = 1L;

	public Community(){}

	public abstract String getName();
	
	
	public abstract String getPassword();
	
	
	public abstract String getURL();
	
	
	public abstract void join( String user );
	
	
	public abstract void unjoin( String user );
	
	
	public abstract Map<String, EventInfo> getEvents();
	
	
	public abstract void addEvent( EventInfo event );
	
	
	public abstract void removeEvent( String eventID );
	
	
	public abstract EventInfo getEventByID(String eventID);


	public abstract void setUrl(String url);


	public abstract Set<String> getUsers();


	public abstract void setUsers(Set<String> users);


	public abstract void setPassword(String password);


	public abstract void setName(String name);


	public abstract void setEvents(Map<String, EventInfo> events);

	public abstract String getUrl();
}
