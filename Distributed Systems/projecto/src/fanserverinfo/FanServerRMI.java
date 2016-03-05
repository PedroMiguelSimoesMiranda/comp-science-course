package fanserverinfo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import fanzone.EventInfo;
import fanzone.ResourceInfo;
import fanzone.User;

public class FanServerRMI implements FanServerRemote {

	private static final long serialVersionUID = 1L;
	public static final String SERVICENAME = "/FanZoneServer";

	private FanServer fs;
	
	protected FanServerRMI(FanServer fs) throws RemoteException {
		super();
		this.fs = fs;
	}

	@Override
	public boolean login(String user, String pwd, String ip)
			throws RemoteException {
		return fs.login(user, pwd, ip);
	}

	@Override
	public boolean createCommunity(User user, String comm, String pwd, String url)
			throws RemoteException {
		return fs.createCommunity(user, comm, pwd, url);
	}

	@Override
	public boolean joinCommunity(User user, String comm, String pwd, String url)
			throws RemoteException {
		return fs.joinCommunity(user, comm, pwd, url);
	}

	@Override
	public boolean leaveCommunity(User user, String comm)
			throws RemoteException {
		return fs.leaveCommunity(user, comm);
	}

	@Override
	public SortedSet<String> listCommunities(User user) throws RemoteException {
		return fs.listCommunities(user);
	}

	@Override
	public boolean importEvent(User user, EventInfo event)
			throws RemoteException {
		return true;
	}

	@Override
	public List<EventInfo> listAllEvents(User user) throws RemoteException {
		return fs.listAllEvents(user);
	}

	@Override
	public boolean addEventToCommunity(User user, String comm, EventInfo event)
			throws RemoteException {
		return fs.addEventToCommunity(user, comm, event);
	}

	@Override
	public List<EventInfo> listEventsFromCommunity(User user, String comm)
			throws RemoteException {
		return fs.listEventsFromCommunity(user, comm);
	}

	@Override
	public List<EventInfo> importEventFromLastFM(User user, String query)
			throws RemoteException {
		return fs.importEventFromLastFM(user, query);
	}

	@Override
	public boolean addResourceToEvent(User user, String comm, String eventID,
			ResourceInfo info) throws RemoteException {
		return fs.addResourceToEvent(user, comm, eventID, info);
	}

	@Override
	public ArrayList<String> getPeer(User user, String resourcePath)
			throws RemoteException {
		return fs.getPeer(user, resourcePath);
	}

	@Override
	public List<EventInfo> searchPhotos(User user, String comm, String query)
			throws RemoteException {
		return fs.searchPhotos(user, comm, query);
	}

	@Override
	public List<ResourceInfo> importResourceFromLastFM(User user,
			String query) {
		return fs.importResourceFromLastFM(user, query);
	}
	
	@Override
	public void tellIHaveResource(User user, String resource) {
		fs.tellIHaveResource(user, resource);
	}

}
