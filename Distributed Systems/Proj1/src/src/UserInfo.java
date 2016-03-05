import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class UserInfo {

	private final String username;
	private final String password;
	private String path;
	
	public UserInfo(String u, String p) {
		username = u;
		password = p;
		path = File.pathSeparator + username + File.pathSeparator;
	}
	
	public String name() {
		return username;
	}
	
	public String path() {
		return path;
	}
	
	public boolean match_pass(String pass) {
		return pass.equals(password);
	}
	
}
