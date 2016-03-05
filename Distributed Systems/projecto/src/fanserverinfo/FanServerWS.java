package fanserverinfo;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import fanzone.EventClass;
import fanzone.EventInfo;
import fanzone.LocalResourceInfo;
import fanzone.RemoteResourceInfo;
import fanzone.ResourceInfo;
import fanzone.User;

@WebService
public class FanServerWS {

	public static final String SERVICENAME = "/FanZoneServerWS";
	private static String MY_IP = "localhost";
	private FanServer fs;

	public FanServerWS(){
		fs = new FanServerClass();
	}
	
	public FanServerWS( FanServer fs )  {
		this.fs = fs;
	}

	@WebMethod
	public boolean login(String user, String pwd, String ip) {
		return fs.login(user, pwd, ip);
	}

	@WebMethod
	public boolean createCommunity(User user, String comm, String pwd, String url) {
		return fs.createCommunity(user, comm, pwd, url);
	}

	@WebMethod
	public boolean joinCommunity(User user, String comm, String pwd, String url) {
		return fs.joinCommunity(user, comm, pwd, url);
	}

	@WebMethod
	public boolean leaveCommunity(User user, String comm) {
		return fs.leaveCommunity(user, comm);
	}

	@WebMethod
	public TreeSet<String> listCommunities(User user)  {
		return (TreeSet<String>) fs.listCommunities(user);
	}

	@WebMethod
	public boolean importEvent(User user, EventClass event) {
		return true;
	}

	@WebMethod
	public ArrayList<EventInfo> listAllEvents(User user)  {
		return (ArrayList<EventInfo>) fs.listAllEvents(user);
	}

	@WebMethod
	public boolean addEventToCommunity(User user, String comm, EventClass event)	{
		return fs.addEventToCommunity(user, comm, event);
	}

	@WebMethod
	public ArrayList<EventInfo> listEventsFromCommunity(User user, String comm) {
		return (ArrayList<EventInfo>) fs.listEventsFromCommunity(user, comm);
	}

	@WebMethod
	public ArrayList<EventInfo> importEventFromLastFM(User user, String query) {
		return (ArrayList<EventInfo>) fs.importEventFromLastFM(user, query);
	}

	@WebMethod
	public boolean addResourceToEvent(User user, String comm, String eventID,
			ResourceInfo info)  {		
		return fs.addResourceToEvent(user, info.getCommunity(), eventID, info);
	}

	@WebMethod
	public ArrayList<String> getPeer(User user, String resourcePath) {
		return fs.getPeer(user, resourcePath);
	}

	@WebMethod
	public ArrayList<EventInfo> searchPhotos(User user, String comm, String query) {
		return (ArrayList<EventInfo>) fs.searchPhotos(user, comm, query);
	}
	
	@WebMethod
	public void tellIHaveResource(User user, String resource){
		this.fs.tellIHaveResource(user, resource);
	}
	
	
	
	// APENAS PARA GERAS AS CLASSES ...
	@WebMethod
	public void doNothing(RemoteResourceInfo rri, LocalResourceInfo lri){}
	
	@WebMethod(exclude=true)
	public static void main(String[] args){
		Endpoint.publish("http://" + MY_IP + ":7070/FanZoneServerWS", new FanServerWS());
	}
}
