import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Repository {

	private String owner;
	private File directory;
	
	public Repository(String o, String path) {
		owner = o;
		directory = new File(path);
	}
	
	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File new_dir) {
		directory = new_dir;
	}
		
}
