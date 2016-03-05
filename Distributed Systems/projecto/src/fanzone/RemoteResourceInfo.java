package fanzone;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoteResourceInfo extends ResourceInfo {
	private static final long serialVersionUID = 1L;
	private String path, comm, name;
	private boolean local;
	URL url;
	List<String> urls;
	private boolean external;

	public RemoteResourceInfo(){}
	
	public RemoteResourceInfo( URL url, String name, String path,String comm) {
		local=false;
		this.name=name;
		this.url = url;
		this.path=path;
		this.comm = comm;
		this.external = true;
		urls = new ArrayList<String>();
		urls.add( url.toExternalForm());
		this.password = "" + (new Random()).nextInt();
	}

	public String getName() {
		return this.name;
	}
	
	public boolean isLocal() {
		return local;
	}

	public List<String> getURL() {
		return urls;
	}
	public String getID() {
		return url.toExternalForm();
	}

	public String getHTTPURL() {
		return url.toExternalForm();
	}

    public String getPath() {
       return this.path;//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCommunity(String comm) {
       this.comm = comm;//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPath(String path) {
        this.path=path;//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLocal(boolean b) {
      this.local=b; // throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(ResourceInfo o) {
       throw new UnsupportedOperationException("Not supported yet.");
    }


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilename() {
		return this.name;
	}

	@Override
	public void setFilename(String filename) {	
		this.name = filename;
	}

	@Override
	public void setUrls(List<String> urls) {
	}

	@Override
	public List<String> getUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommunity() {
		return this.comm;
	}

	@Override
	public void setExternal(boolean b) {
		this.external = b;
	}

	@Override
	public boolean isExternal() {
		return this.external;
	}	

	@Override
	public void setMainURL(URL url){
		this.url = url;
	}
	
	@Override
	public URL getMainURL(){
		return this.url;
	}

}