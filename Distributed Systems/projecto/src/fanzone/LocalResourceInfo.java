package fanzone;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalResourceInfo extends ResourceInfo
{
	private static final long serialVersionUID = 1L;

	public LocalResourceInfo(){}
	
	public LocalResourceInfo(File f, boolean islocal) {
		this.path = f.getAbsolutePath();
		this.filename = f.getName();
		this.islocal = islocal;
		urls = new ArrayList<String>();
		urls.add( "file:" + path);
		this.password = "" + (new Random()).nextInt();
	}
	
	@Override
	public String getFilename() {
		return filename;
	}

	@Override
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public List<String> getUrls() {
		return urls;
	}

	@Override
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	
	@Override
	public String getName() {
		return filename;
	}
	
	@Override
	public boolean isLocal() {
		return islocal;
	}

	@Override
	public List<String> getURL() {
		return urls;
	}
	
	@Override
	public String getID() {
		return path;
	}

	@Override
	public int compareTo(ResourceInfo o) {
		return -1;
	}

	@Override
	public String getHTTPURL() {
		return null;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setLocal(boolean b) {
		this.islocal = b;
		
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCommunity(String comm) {	
		this.community= comm;
	}

	@Override
	public String getCommunity() {
		return this.community;
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
	public void setMainURL(URL url) {
	}

	@Override
	public URL getMainURL() {
		return null;
	}
}
