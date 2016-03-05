import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.TreeMap;

public class Server implements IServer{

	private Map<String, UserInfo> users;
	private Map<String, Repository> repos;

	public Server() {
		users = new TreeMap<String, UserInfo>();
		repos = new TreeMap<String, Repository>();
	}

	public UserInfo add_user(String name, String pass) throws RemoteException, UserAlreadyExistsException {
		System.err.println("add_user called");
		if(!users.containsKey(name))
			return users.put(name, new UserInfo(name, pass));
		else throw new UserAlreadyExistsException("Username em uso");
	}

	public void rem_user(String name) throws RemoteException, UserNotFoundException {
		System.err.println("rem_user called");
		if(users.containsKey(name))
			users.remove(name);
		else
			throw new UserNotFoundException("Utilizador "+ name + " nao existe.");
	}

	public UserInfo login(String username, String hashpass) throws RemoteException, WrongLoginException {
		System.err.println("login called");
		if(get_user(username).match_pass(hashpass))
			return get_user(username);
		else throw new WrongLoginException("Login Errado");
	}

	public File sync(String user, File newf) throws IOException {
		Repository repo = repos.get(user);
		File repo_dir = repo.getDirectory();
		

		if(repo_dir.lastModified() < newf.lastModified()){//upload
			repo_dir=newf;
			char buffer[] = new char[newf.getAbsolutePath().length()]; 

			newf.getAbsolutePath().getChars(0, newf.getAbsolutePath().length(), buffer, 0);

			FileWriter f1=new FileWriter(repo_dir.getAbsolutePath())  ;
			for (int i=0; i < buffer.length; i += 2) {
				f1.write(buffer[i]);
			} 
			f1.close();
			return newf;
		}
		else {//if(repo_dir.lastModified() > newf.lastModified()){//download
			// send to client and sync
			char buffer[] = new char[repo_dir.getAbsolutePath().length()]; 
			repo_dir.getAbsolutePath().getChars(0, repo_dir.getAbsolutePath().length(), buffer, 0);
			FileWriter f1=new FileWriter(newf.getAbsolutePath())  ;
			for (int i=0; i < buffer.length; i += 2) {
				f1.write(buffer[i]);
			} 
			f1.close();
			return repo_dir;

		}		
	}




	public File get_files(String path) throws RemoteException {
		String loc = "serv1" + File.separatorChar + path;
		File file = new File(loc);

		return file;
	}

	private UserInfo get_user(String name) {
		if(users.containsKey(name))
			return users.get(name);
		else return null;
	}

	public static void main(String args[]) {
		try { 
			Server sync = new Server();
			// export myInfo creating its skeleton
			IServer server = (IServer) UnicastRemoteObject.exportObject(sync,0);
			// start internal rmiregistry
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);  
			// default RMI registry port = 1099

			registry.bind("SyncServer", server); // register server as "myInfoServer"
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("InfoServer bound in registry");
	}



	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
	
	
	public void addFiles(File[] files , String user){
		
		
		
		
	}

}