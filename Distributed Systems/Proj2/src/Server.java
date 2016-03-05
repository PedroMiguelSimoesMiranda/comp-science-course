import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import secsocket.SecClientSocketFactory;
import secsocket.SecServerSocketFactory;
import security.KeyPair;
import security.PrivateKey;
import security.PublicKey;
import security.SymetricKey;

public class Server implements IServer, Serializable{

	private static final long serialVersionUID = 1L;

	private SymetricKey file_cipher;
	private String secret;
	private KeyPair keys;
	private Certificate cert;
	
	private Map<String, UserInfo> users;

	public Server() throws Exception {
		super();
		read_users();
		secret = "YcITtudNQpseZG79eBJ6Y3Mgw4HpzDNo" ;
		file_cipher = SymetricKey.createKey(secret.getBytes());
		
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/keys/spub"));
			PublicKey pub = (PublicKey) file.readObject();
			file = new ObjectInputStream(new FileInputStream("files/keys/sprv"));
			PrivateKey prv = (PrivateKey) file.readObject();
			keys = new KeyPair(pub, prv);
			file.close();
		} catch(FileNotFoundException e) {
			keys = KeyPair.createKeyPair();
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("files/keys/spub"));
			file.writeObject(keys.getPublic());
			file.flush();
			file = new ObjectOutputStream(new FileOutputStream("files/keys/sprv"));
			file.writeObject(keys.getPrivate());
			file.flush();
			file.close();
		}
		
		ICertAuthority verizign = null;
		try {
			verizign = (ICertAuthority) Naming.lookup("//localhost/Verizign");
			cert = verizign.make_certificate(keys.getPublic().exportKey());
			System.out.println("Got certificate");
		} catch (Exception e) {
			System.out.println("Failed to get certificate");
		}
	}

	public UserInfo add_user(String name, String pass) throws RemoteException, UserAlreadyExistsException {
		System.err.println("add_user called");
		if(!users.containsKey(name)) {
			Repository repo = new Repository(name);
			UserInfo ui = new UserInfo(name, pass, repo);
			users.put(name, ui);
			save_users();
			new File("files/server/"+ui.path()).mkdirs();
			return ui;
		}
		else throw new UserAlreadyExistsException("Username em uso");
	}

	public void rem_user(String name) throws RemoteException, UserNotFoundException {
		System.err.println("rem_user called");
		if(users.containsKey(name)) {
			users.remove(name);
			save_users();
		}
		else
			throw new UserNotFoundException("Utilizador "+ name + " nao existe.");
	}

	public UserInfo login(String username, String hashpass) throws RemoteException, WrongLoginException, UserNotFoundException, BannedException {
		System.err.println("login called");
		if(users.containsKey(username))
			if(get_user(username).match_pass(hashpass)) {
				UserInfo ui = get_user(username);
				if(ui.isBanned())
					throw new BannedException();
				new File("files/server/"+ui.path()).mkdirs();
				return ui;
			}
			else throw new WrongLoginException("Login Errado");
		else throw new UserNotFoundException("Utilizador " + username + " nao existe");
	}

	public void report(String reported, String user) throws RemoteException, IOException {
		if(users.containsKey(reported))
			users.get(reported).report(user);
		save_users();
	}
	
	private UserInfo get_user(String name) {
		if(users.containsKey(name))
			return users.get(name);
		else return null;
	}

	public long last_update(String user, String filename) throws RemoteException {
		String path = "files/server/" + users.get(user).path();
		File f = new File(path + File.separatorChar + filename);
		long lastmod = f.lastModified();

		return lastmod;
	}

	public FileContent download(String user, String filename) throws IOException {
		String path = "files/server/" + users.get(user).path();
		File file = new File(path + filename);
		byte[] data = readFromDisk(file);
		FileContent fc = new FileContent(file, file_cipher.decrypt(data));
		System.out.println("Sending "+path +  fc.file().getName());

		return fc;
	}

	public void upload(String user, FileContent fc, long lastmod) throws RemoteException, IOException {
		Repository repo = users.get(user).repo();
		String path = "files/server/" + repo.path();
		synchronized(repo) {
			writeToDisk(fc, path, lastmod);
		}
		System.out.println("Stored "+path + fc.file().getName());
	}

	public void give_read(String to, UserInfo from, String file) throws RemoteException, IOException, UserNotFoundException {
		UserInfo ui = users.get(to);
		if(ui == null)
			throw new UserNotFoundException("Utilizador "+to+" nao existe");
		ui.add_read(file, from);
		save_users();
	}

	public void take_read(String to, String file) throws RemoteException, IOException, UserNotFoundException {
		UserInfo ui = users.get(to);
		if(ui == null)
			throw new UserNotFoundException("Utilizador "+to+" nao existe");
		ui.rem_read(file);
		save_users();
	}

	public void give_write(String to, UserInfo from, String file) throws RemoteException, IOException, UserNotFoundException {
		UserInfo ui = users.get(to);
		if(ui == null)
			throw new UserNotFoundException("Utilizador "+to+" nao existe");
		ui.add_write(file, from);
		save_users();
	}

	public void take_write(String to, String file) throws RemoteException, IOException, UserNotFoundException {
		UserInfo ui = users.get(to);
		if(ui == null)
			throw new UserNotFoundException("Utilizador "+to+" nao existe");
		ui.rem_write(file);
		save_users();
	}

	public String[] server_list(String username) throws RemoteException {
		String path = "files/server/" + users.get(username).path();
		String[] files = new File(path).list();

		if(files == null) 
			files = new String[0];
		return files;
	}

	
	public Certificate get_cert() throws RemoteException {
		return cert;
	}

	public byte[] get_pubkey() throws IOException {
		return keys.getPublic().exportKey();
	}
	
	public void read_users() {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/server/users"));
			users = (Map<String, UserInfo>) file.readObject();
			file.close();
		} catch ( IOException e ) { 
			users = new TreeMap<String,UserInfo>(); 
		} catch ( ClassNotFoundException e ) { 
			e.printStackTrace();
		}
	}

	private byte[] readFromDisk(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();

		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int num_read = 0;
		while (offset < bytes.length
				&& (num_read=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += num_read;
		}

		is.close();
		return bytes;
	}

	private void writeToDisk(FileContent fc, String path, long lastmod) throws IOException {
		FileOutputStream fos = new FileOutputStream(path + File.separatorChar + fc.file().getName());
		fos.write(file_cipher.encrypt(fc.data()));
		fos.close();

		(new File(path + File.separatorChar + fc.file().getName())).setLastModified(lastmod);
	}

	public synchronized void save_users(){
		try	{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("files/server/users") );
			file.writeObject(users);
			file.flush();
			file.close();
		} catch ( IOException e ) {}
	}

	/*************************************/

	public static void main(String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Insira o IP desta maquina:");
		System.out.println("(String vazia para o ip default)");
		String ip = reader.readLine();
		ip = ip.equals("") ? "0.0.0.0": ip;
		System.setProperty("java.rmi.server.hostname", "0.0.0.0");
		
		String GROUP = "233.233.233.1";
		int PORT = 9675;

		InetAddress group = InetAddress.getByName(GROUP) ;
		if(!group.isMulticastAddress())
			System.out.println(GROUP + " is not a Multicast Address");
		new Thread(new Receiver(group, PORT, new Server())).start();
		
		DatagramSocket ds = new DatagramSocket();
		for(;;) {
			DatagramPacket p = new DatagramPacket(new byte[162], 162); 
			ds.receive(p); 
			System.out.println("server received packet");

			// process
			PublicKey cpk = PublicKey.createKey(p.getData());
			SymetricKey symkey = SymetricKey.createKey();
			RMIClientSocketFactory csf = new SecClientSocketFactory();
			RMIServerSocketFactory ssf = new SecServerSocketFactory(symkey, cpk);
			IServer stub = (IServer) UnicastRemoteObject.exportObject(new Server(), 0, csf, ssf);

			// reply
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(UnicastRemoteObject.toStub(stub));
			oos.close();
			byte[] arr = baos.toByteArray();
			p = new DatagramPacket(arr, arr.length, p.getAddress(), p.getPort());
			ds.send(p);
			System.out.println("server sent packet");
		}
	}
}


class Receiver implements Runnable {

	private int port ;
	private InetAddress group ;
	private IServer server;

	public Receiver(InetAddress g, int p, IServer s) {
		group = g ; port = p ; server = s;
	}

	public void run() {
		try {
			new File("files/keys/").mkdirs();
			new File("files/server/").mkdirs();
			MulticastSocket ms = new MulticastSocket(port) ;
			ms.joinGroup(group) ;
			System.out.println("server joins group");

			for(;;) {
				server = new Server();
				
				DatagramPacket p = new DatagramPacket(new byte[162], 162); 
				ms.receive(p); 
				System.out.println("server received packet");

				// process
				PublicKey cpk = PublicKey.createKey(p.getData());
				SymetricKey symkey = SymetricKey.createKey();
				RMIClientSocketFactory csf = new SecClientSocketFactory();
				RMIServerSocketFactory ssf = new SecServerSocketFactory(symkey, cpk);
				IServer stub = (IServer) UnicastRemoteObject.exportObject(server, 0, csf, ssf);

				// reply
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(UnicastRemoteObject.toStub(stub));
				oos.close();
				byte[] arr = baos.toByteArray();
				p = new DatagramPacket(arr, arr.length, p.getAddress(), p.getPort());
				ms.send(p);
				System.out.println("server sent packet");
			}
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
