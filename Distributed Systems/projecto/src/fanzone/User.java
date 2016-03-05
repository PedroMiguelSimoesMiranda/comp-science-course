package fanzone;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	private boolean logged;
	private String ip;
	
	public User(){}
	
	public User( String username, String password, String ip ){
		this.username = username;
		this.password = password;
		this.logged = false;
		this.ip=ip;
	}
	
	
	public void login() {
		this.logged = true;	
	}

	
	public void logoff() {
		this.logged = false;
	}
	
	
	public boolean logged() {
		return this.logged;
	}

	
	public int compareTo( User arg0 ) {
		User arg00 = (User)arg0;
		int u = this.username.compareTo( arg00.username );
		int p = this.password.compareTo( arg00.password );
		return u!=0 ? u : p;
	}
	
	
	public boolean equals( Object arg0 )  {
		if ( !(arg0 instanceof User) )
			return false;
		User arg00 = (User)arg0;
		return this.username.equals( arg00.username ) && this.password.equals( arg00.password );		
	}

	
	public String getUsername() {
		return this.username;
	}

	
	public String getIP() {
		return ip;
	}
}
