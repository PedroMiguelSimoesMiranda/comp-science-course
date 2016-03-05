import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final String password;
	private Repository repo;
	private Map<String, UserInfo> read, write;
	private Set<String> reports;
	private boolean banned;
	
	public UserInfo(String u, String p, Repository r) {
		name = u;
		password = p;
		repo = r;
		read = new TreeMap<String, UserInfo>();
		write = new TreeMap<String, UserInfo>();
		reports = new TreeSet<String>();
		banned = false;
	}
	
	public boolean match_pass(String pass) {
		return pass.equals(password);
	}
	
	public String name() { return name; }
	
	public Repository repo() { return repo; }
	
	public String path() { return repo.path(); }
	
	public void add_read(String file, UserInfo owner) { read.put(file, owner);	}
	
	public void add_write(String file, UserInfo owner) { write.put(file, owner); }
	
	public void rem_read(String file) { read.remove(file); }
	
	public void rem_write(String file) { write.remove(file); }
	
	public Map<String, UserInfo> read() { return read; }
	
	public Map<String, UserInfo> write() { return write; }

	public boolean isBanned() { return banned; }
	
	public void report(String user) {
		reports.add(user);
		if(reports.size() >= 3)
			banned = true;
	}
}
