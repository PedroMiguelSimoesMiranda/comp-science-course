package fanserverinfo;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import fanzone.EventInfo;
import fanzone.ResourceInfo;
import fanzone.User;

public interface FanServerRemote extends Remote, Serializable {

	boolean login( String user, String pwd , String ip) throws RemoteException;

	boolean createCommunity( User user, String comm, String pwd, String url ) throws RemoteException;

	boolean joinCommunity( User user, String comm, String pwd, String url ) throws RemoteException;

	boolean leaveCommunity( User user, String comm ) throws RemoteException;

	SortedSet<String> listCommunities( User user ) throws RemoteException;

	boolean importEvent( User user, EventInfo event ) throws RemoteException;

	List<EventInfo> listAllEvents( User user ) throws RemoteException;

	boolean addEventToCommunity( User user, String comm, EventInfo event ) throws RemoteException;

	List<EventInfo> listEventsFromCommunity( User user, String comm ) throws RemoteException;

	List<EventInfo> importEventFromLastFM( User user, String query ) throws RemoteException;

	boolean addResourceToEvent( User user, String comm, String eventID, ResourceInfo info ) throws RemoteException;

	ArrayList<String> getPeer( User user, String resourcePath )throws RemoteException;

	List<EventInfo> searchPhotos( User user, String comm, String query)throws RemoteException;

	List<ResourceInfo> importResourceFromLastFM(User currentUser, String query) throws RemoteException;

	void tellIHaveResource(User user, String resource) throws RemoteException;

}
