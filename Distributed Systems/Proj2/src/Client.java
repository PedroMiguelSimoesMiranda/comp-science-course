import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import security.KeyPair;
import security.PrivateKey;
import security.PublicKey;

public class Client {

	private static final String GROUP = "233.233.233.1";
	private static final int PORT = 9675;

	public static void main(String agrs[]) throws Exception {
		new File("files/user/").mkdirs();
		new File("files/keys/").mkdirs();
		boolean exitNow = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		KeyPair keys;
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("files/keys/cpub"));
			PublicKey pub = (PublicKey) file.readObject();
			file = new ObjectInputStream(new FileInputStream("files/keys/cprv"));
			PrivateKey prv = (PrivateKey) file.readObject();
			keys = new KeyPair(pub, prv);
			file.close();
		} catch(FileNotFoundException e) {
			keys = KeyPair.createKeyPair();
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("files/keys/cpub"));
			file.writeObject(keys.getPublic());
			file.flush();
			file = new ObjectOutputStream(new FileOutputStream("files/keys/cprv"));
			file.writeObject(keys.getPrivate());
			file.flush();
			file.close();
		}
		
		System.out.println("Insira o IP do servidor: ");
		System.out.println("(String vazia para o ip default)");
		String ip = reader.readLine();
		ip = ip.equals("") ? "0.0.0.0": ip;
		
		IServer server = null;
		InetAddress group = InetAddress.getByName(GROUP) ;
		if(!group.isMulticastAddress())
			System.out.println(GROUP + " is not a Multicast Address");

		DatagramSocket ds = new DatagramSocket();
		// send client's pubkey
		byte[] pubkey = keys.getPublic().exportKey();
		DatagramPacket cpk = new DatagramPacket(pubkey, pubkey.length);
		cpk.setAddress(InetAddress.getByName(ip));
		cpk.setPort(PORT);
		ds.send(cpk);
		// get stub
		DatagramPacket ss = new DatagramPacket(new byte[65536], 65536);
		ds.setSoTimeout(3000);
		try {
			ds.receive(ss);
			ByteArrayInputStream bais = new ByteArrayInputStream(ss.getData());
			ObjectInputStream ois = new ObjectInputStream(bais);
			server = (IServer) ois.readObject();
		} catch(SocketTimeoutException e1) {
			System.out.println("Trying multicast servers");
			// send client's pubkey
			cpk.setAddress(group);
			cpk.setPort(PORT);
			ds.send(cpk);
			// get stub
			ss = new DatagramPacket(new byte[65536], 65536);
			ds.setSoTimeout(3000);
			try {
				ds.receive(ss);
				ByteArrayInputStream bais = new ByteArrayInputStream(ss.getData());
				ObjectInputStream ois = new ObjectInputStream(bais);
				server = (IServer) ois.readObject();
			} catch(SocketTimeoutException e2) {
				System.out.println("Launching local server");
				server = new Server();
				new Thread(new Receiver(group, PORT, server)).start();
			}
		}

		ICertAuthority verizign = null;
		try {
			verizign = (ICertAuthority) Naming.lookup("//localhost/Verizign");
			PublicKey vpub = verizign.get_pubkey();
			Certificate cert = server.get_cert();
			if(cert != null) {
				byte[] spub = server.get_pubkey();
				if(cert.check(spub, vpub))
					System.out.println("Certified Server");
				else 
					System.out.println("Uncertified Server");
			}
			else System.out.println("Uncertified Server");

		} catch (Exception e) {
			System.out.println("Uncertified Server");
		}

		do {
			System.out.println( "\nOpcoes:");
			System.out.println( "\t1 - Registar");
			System.out.println( "\t2 - Login");
			System.out.println( "\t0 - Sair");

			System.out.print( "opcao > ");
			System.out.flush();

			String opcaoStr = reader.readLine();
			int opcao;
			try {
				opcao = Integer.parseInt(opcaoStr);
			} catch(Exception e) {
				opcao = -1;
			}
			if( opcao == 1) {
				System.out.print("Nome do utilizador > ");
				System.out.flush();
				String username = reader.readLine();
				System.out.print("Password > ");
				System.out.flush();
				String hashpass = "" + reader.readLine().hashCode();
				try {
					UserInfo user = server.add_user(username, hashpass);
					session(server, user);
				} catch( RemoteException e) {
					System.err.println("Erro ao contactar o servidor");
				} catch (UserAlreadyExistsException e) {
					System.err.println(e.getMessage());
				}
			} else if(opcao == 2) {
				System.out.print("Nome do utilizador > ");
				System.out.flush();
				String username = reader.readLine();
				System.out.print("Password > ");
				System.out.flush();
				String hashpass = "" + reader.readLine().hashCode();
				try {
					UserInfo user = server.login(username, hashpass);
					session(server, user);
				} catch (RemoteException e) {
					System.err.println("Erro ao contactar o servidor");
				} catch (WrongLoginException e) {
					System.err.println(e.getMessage());
				} catch (UserNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (BannedException e) {
					System.out.println(e.getMessage());
				}
			} else if( opcao == 0) {
				exitNow = true;
			} else
				System.out.println("Opcao invalida");
		} while(!exitNow);
	}

	private static void session(IServer server, UserInfo user) throws IOException, ClassNotFoundException {
		boolean logout = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String path = "files/user/" + user.path();
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();

		sync(server, user);
		sync_read(server, user);
		sync_write(server, user);

		do {
			System.out.println("\nOpcoes:");
			System.out.println("\t1 - Sincronizar");
			System.out.println("\t2 - Listar");
			System.out.println("\t3 - Dar Permissao de Leitura");
			System.out.println("\t4 - Dar Permissao de Escrita");
			System.out.println("\t5 - Remover Permissao de Leitura");
			System.out.println("\t6 - Remover Permissao de Escrita");
			System.out.println("\t7 - Reportar Utilizador");
			System.out.println("\t0 - Logout");

			System.out.print("opcao > ");
			System.out.flush();

			String opcaoStr = reader.readLine();
			int opcao;
			try {
				opcao = Integer.parseInt(opcaoStr);
			} catch(Exception e) {
				opcao = -1;
			}
			try {
			if( opcao == 1) {
				sync(server, user);
				sync_read(server, user);
				sync_write(server, user);
			} else if(opcao == 2) {
				System.out.println("Path: " + path);
				String[] files = dir.list();
				for(int i = 0; i < files.length; i++) 
					System.out.println(path + files[i]);
			} else if(opcao == 3) {
				System.out.println("Permitir leitura");
				System.out.println("Nome do utilizador: ");
				String to = reader.readLine();
				System.out.println("Nome do ficheiro: ");
				String file = reader.readLine();
				server.give_read(to, user, file);
			} else if(opcao == 4) {
				System.out.println("Permitir escrita");
				System.out.println("Nome do utilizador: ");
				String to = reader.readLine();
				System.out.println("Nome do ficheiro: ");
				String file = reader.readLine();
				server.give_write(to, user, file);
			} else if(opcao == 5) {
				System.out.println("Revogar leitura");
				System.out.println("Nome do utilizador: ");
				String to = reader.readLine();
				System.out.println("Nome do ficheiro: ");
				String file = reader.readLine();
				server.take_read(to, file);
			} else if(opcao == 6) {
				System.out.println("Revogar escrita");
				System.out.println("Nome do utilizador: ");
				String to = reader.readLine();
				System.out.println("Nome do ficheiro: ");
				String file = reader.readLine();
				server.take_write(to, file);
			} else if(opcao == 7) {
				System.out.println("Reportar Utilizador");
				System.out.println("Nome do utilizador: ");
				String ban = reader.readLine();
				server.report(ban, user.name());
			} else if(opcao == 0) {
				sync(server, user);
				logout = true;
			} else
				System.out.println("Opcao invalida");
			} catch(UserNotFoundException e) {
				System.out.println(e.getMessage());
			} catch(FileNotFoundException e) {
				System.out.println("Ficheiro nao existe");
			}
		} while(!logout);
	}

	public static void sync(IServer server, UserInfo user) throws IOException {
		String path = "files/user/" + user.path();
		System.out.println("Path: " + path);
		File dir = new File(path);
		String[] myfiles = dir.list();
		if(myfiles == null)
			myfiles = new String[0];
		Arrays.sort(myfiles);
		String[] serv_files = server.server_list(user.name());

		// Get files only server has
		for(int i = 0; i < serv_files.length; i++) {
			if(Arrays.binarySearch(myfiles, serv_files[i]) < 0) {
				FileContent fc = server.download(user.name(), serv_files[i]);
				System.out.println("Downloading New "+path+serv_files[i]);

				long lastmod = server.last_update(user.name(), serv_files[i]);
				writeToDisk(fc, path, lastmod);
			}
		}

		// Update files
		for(int i = 0; i < myfiles.length; i++) {
			String filepath = path + myfiles[i];
			File file = new File(filepath);
			if(file.isDirectory())
				continue;
			
			byte[] data = readFromDisk(file);

			long lastmod = server.last_update(user.name(), file.getName());
			if(file.lastModified() < lastmod) {
				System.out.println("Downloading "+filepath);
				FileContent fc = server.download(user.name(), myfiles[i]);

				writeToDisk(fc, path, lastmod);
			}
			else if(file.lastModified() > lastmod) {
				System.out.println("Uploading "+filepath);
				FileContent fc = new FileContent(file, data);

				server.upload(user.name(), fc, file.lastModified());
			}
			else System.out.println("Synchronized "+filepath);
		}
	}

	private static void sync_read(IServer server, UserInfo user) throws IOException {
		String path = "files/user/" + user.name() + "/";
		Map<String, UserInfo> read = user.read();
		if(read == null) return;
		Iterator<Entry<String, UserInfo>> it = read.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, UserInfo> e = it.next();
			String file = e.getKey();
			UserInfo owner = e.getValue();

			String filepath = path+owner.name()+"'s repo/";

			File f = new File(filepath+file);
			long lastmod = server.last_update(owner.name(), file);

			if(lastmod != f.lastModified()) {
				FileContent fc = server.download(owner.name(), file);
				writeToDisk(fc, filepath, lastmod);
				System.out.println("Downloading(Read) " + filepath + file);
			}
			else System.out.println("Synchronized(Read) " + filepath + file);
		}
	}

	private static void sync_write(IServer server, UserInfo user) throws IOException {
		String path = "files/user/" + user.name() + "/";
		Map<String, UserInfo> write = user.write();
		if(write == null) return;
		Iterator<Entry<String, UserInfo>> it = write.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, UserInfo> e = it.next();
			String file = e.getKey();
			UserInfo owner = e.getValue();

			String filepath = path+owner.name()+"'s repo/";

			File f = new File(filepath+file);
			long lastmod = server.last_update(owner.name(), file);

			if(f.lastModified() < lastmod) {
				System.out.println("Downloading(Write) " + filepath + file);
				FileContent fc = server.download(owner.name(), file);

				writeToDisk(fc, filepath, lastmod);
			}
			else if(f.lastModified() > lastmod) {
				System.out.println("Uploading(Write) " + filepath + file);
				byte[] data = readFromDisk(f);
				FileContent fc = new FileContent(f, data);

				server.upload(owner.name(), fc, f.lastModified());
			}
			else System.out.println("Synchronized(Write) "+ filepath + file);
		}
	}

	private static byte[] readFromDisk(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();

		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int num_read = 0;
		while (offset < bytes.length
				&& (num_read=is.read(bytes, offset, bytes.length-offset)) >= 0)
			offset += num_read;

		is.close();
		return bytes;
	}

	private static void writeToDisk(FileContent fc, String path, long lastmod) throws IOException {
		(new File(path)).mkdirs();
		FileOutputStream fos = new FileOutputStream(path + fc.file().getName());
		fos.write(fc.data());
		fos.close();

		(new File(path + fc.file().getName())).setLastModified(lastmod);
	}
}
