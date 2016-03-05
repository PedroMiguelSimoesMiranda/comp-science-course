package fanzone;

import java.util.ArrayList;
import java.util.List;
import fanzone.EventInfo;
import fanzone.ResourceInfo;


public class EventClass extends EventInfo {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private ArrayList<ResourceInfo> resources;
	private String mainCommunity;
	private List<String> communities;
	
	
	public EventClass(){}
	
	public EventClass(String id ,String name, String description, ArrayList<ResourceInfo> arrayList  ){
		this.id=id;
		this.name=name;
		this.description = description;
		this.resources = arrayList;
		this.communities = new ArrayList<String>();
	}
	
	
	@Override
	public String getID() {
		return this.id;
	}

	
	@Override
	public String getName() {
		return this.name;
	}

	
	@Override
	public String getDescription() {
		return this.description;
	}

	
	@Override
	public ArrayList<ResourceInfo> getResourceInfo() {
		return this.resources;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public ArrayList<ResourceInfo> getResources() {
		return resources;
	}

	@Override
	public void setResources( ArrayList<ResourceInfo> resources) {
		this.resources = resources;
	}

	@Override
	public String getMainCommunity() {
		return mainCommunity;
	}

	@Override
	public void setMainCommunity(String mainCommunity) {
		this.mainCommunity = mainCommunity;
	}

	@Override
	public void setCommunities(List<String> communities) {
		this.communities = communities;
	}

	@Override
	public List<String> getCommunities() {
		return this.communities;
	}

	
	@Override
	public void setName(String str) {
		this.name=str;
		
	}

	
	@Override
	public void setDescription(String str) {
		this.description=str;
		
	}
	
	
	@Override
	public void addResource(ResourceInfo ri) {
		if( !this.resources.contains(ri) )
			this.resources.add(ri);
	}
	
	
	@Override
	public int compareTo(EventInfo o) {
		return this.id.compareTo(o.getID());
	}
	
	
	@Override
	public boolean equals(Object o) {
		if ( !(o instanceof EventClass) )
			return false;
		return this.id.equals( ((EventClass)o).getID() );
	}
	
	
	@Override
	public void setCommunity(String name) {
		this.mainCommunity = name;		
	}
	
	
	@Override
	public String getCommunity() {
		return this.mainCommunity;
	}

}

