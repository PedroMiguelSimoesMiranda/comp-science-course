package fanzone;

import java.io.Serializable;
import java.net.URL;
import java.util.*;

public abstract class ResourceInfo implements Serializable, Comparable<ResourceInfo>
{
	
	private static final long serialVersionUID = 1L;
	protected URL url;
	protected String path;
	protected String filename;
	protected String community;
	protected boolean islocal;
	protected boolean external;
	protected List<String> urls;
	protected String password;
	
	public ResourceInfo(){}

	public String getID(){
		return this.path;
	}
	
	public String getName(){
		return this.filename;
	}
	
	public boolean isLocal(){
		return this.islocal;
	}
	
	/**
	 * Devolve lista de URLs 
	 * @return
	 */
	
	public List<String> getURL(){
		return this.urls;
	}
	/**
	 * Devolve URL para apresenta��o por HTTP 
	 * Caso retorne null, ser� usada a aproxima��o habitual
	 */
	
	public String getHTTPURL(){
		return url.toExternalForm();
	}
	
	public String getPath(){
		return this.path;
	}
	
	public void setCommunity(String comm){
		this.community = comm;
	}

	public void setPath(String path){
		this.path = path;
	}
	
	public void setLocal(boolean b){
		this.islocal = b;
	}
	
	public boolean equals(Object o){
		return false;
	}

	public String getFilename(){
		return this.filename;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public void setUrls(List<String> urls){
		this.urls = urls;
	}

	public List<String> getUrls(){
		return this.urls;
	}

	public String getCommunity(){
		return this.community;
	}

	public void setExternal(boolean b){
		this.external = b;
	}
	
	public boolean isExternal(){
		return this.external;
	}

	public void setMainURL(URL url){
		this.url = url;
	}

	public URL getMainURL() {
		return this.url;
	}

	@Override
	public int compareTo(ResourceInfo o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
		
	

}
