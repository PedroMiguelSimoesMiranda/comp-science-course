package fanzone;

import java.io.Serializable;
import java.util.*;


public abstract class EventInfo implements Serializable, Comparable<EventInfo>
{
	public EventInfo(){}
	
	private static final long serialVersionUID = 1L;
	
	public abstract String getID();
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract String getCommunity();
	
	public abstract void setName( String str);
	
	public abstract void setDescription( String str);
	// deve devolver nao null para URL exterior ao sistema
	
	public abstract ArrayList<ResourceInfo> getResourceInfo();
	
	public abstract List<String> getCommunities();
	
	public abstract void setCommunity(String name);
	
	public abstract void addResource(ResourceInfo ri);

	public abstract void setResources(ArrayList<ResourceInfo> resources);

	public abstract String getId();
	
	public abstract void setId(String id);

	public abstract ArrayList<ResourceInfo> getResources();

	public abstract String getMainCommunity();

	public abstract void setMainCommunity(String mainCommunity);

	public abstract void setCommunities(List<String> communities);

}
