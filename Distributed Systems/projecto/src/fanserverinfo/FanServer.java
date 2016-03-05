package fanserverinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


import exceptions.UserNotBelongsToCommunityException;
import exceptions.UserNotLoggedException;
import fanzone.EventInfo;
import fanzone.ResourceInfo;
import fanzone.User;

public interface FanServer extends Serializable {

	boolean login( String user, String pwd , String ip );
	
	boolean createCommunity( User user, String comm, String pwd, String url )
			throws UserNotLoggedException;
	
	boolean joinCommunity( User user, String comm, String pwd, String url ) 
			throws UserNotLoggedException;
	
	boolean leaveCommunity( User user, String comm ) 
			throws UserNotLoggedException, UserNotBelongsToCommunityException;
	
	SortedSet<String> listCommunities( User user ) 
			throws UserNotLoggedException;
	
	List<EventInfo> listAllEvents( User user ) 
			throws UserNotLoggedException;
	
	boolean addEventToCommunity( User user, String comm, EventInfo event ) 
			throws UserNotLoggedException, UserNotBelongsToCommunityException;
	
	List<EventInfo> listEventsFromCommunity( User user, String comm )
			throws UserNotLoggedException, UserNotBelongsToCommunityException;
	
	List<EventInfo> importEventFromLastFM( User user, String query )
			throws UserNotLoggedException;
	
	boolean addResourceToEvent( User user, String comm, String eventID, ResourceInfo info )
			throws UserNotLoggedException, UserNotBelongsToCommunityException;
			
	ArrayList<String> getPeer( User user, String resourcePath )
			throws UserNotLoggedException;
	
	List<EventInfo> searchPhotos( User user, String comm, String query)
			throws UserNotLoggedException, UserNotBelongsToCommunityException;

	List<ResourceInfo> importResourceFromLastFM(User user, String query);

	void tellIHaveResource(User user, String resource);
}
