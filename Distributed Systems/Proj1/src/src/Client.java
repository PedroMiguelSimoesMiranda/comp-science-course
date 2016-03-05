import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;


public class Client {

	public static void main(String agrs[]) throws IOException {
		boolean exitNow = false;
		boolean login = false;
		File sdUser=new File("sd/usr");
		File sdServer=new File("sd/server");
		IServer server = null;
		String serverHost = "127.0.0.1";

		try {
			server = (IServer) Naming.lookup("//"+serverHost+"/SyncServer");
		} catch (RemoteException e) {
			System.err.println("Comunication error: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader( System.in));
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
					// add new user
					UserInfo user = server.add_user(username, hashpass);
					session(server, user);
				} catch( RemoteException e) {
					System.err.println("Erro ao contactar o servidor");
				} catch (UserAlreadyExistsException e) {
					System.err.println("Username em uso");
				}
			} else if( opcao == 2) {
				//apresentar informacao relativa a um utilizador
				System.out.print("Nome do utilizador > ");
				System.out.flush();
				String username = reader.readLine();
				System.out.print("Password > ");
				System.out.flush();
				String hashpass = "" + reader.readLine().hashCode();

				try {
					UserInfo user = server.login(username, hashpass);
					session(server, user);
				} catch( RemoteException e) {
					System.err.println("Erro ao contactar o servidor");
				} catch (WrongLoginException e) {
					System.err.println("Login Errado");
				}
			} else if( opcao == 0) {
				exitNow = true;
			} else
				System.out.println("Opcao invalida");
		} while(!exitNow);
	}

	private static void session(IServer server, UserInfo user) throws IOException {
		boolean loggout = false;

		BufferedReader reader = new BufferedReader(new InputStreamReader( System.in));

		String path = user.path();
		File mine = new File(path);
		File repo = server.get_files(path);
		if(mine.lastModified() < repo.lastModified()) {
			System.out.println("Server's file repository is newer"); // download
		}
		else if(mine.lastModified() > repo.lastModified()) {
			System.out.println("Local repository is newer"); // upload
		}

		do {
			System.out.println("\nOpcoes:");
			System.out.println("\t1 - Sincronizar");
			System.out.println("\t2 - Listar");
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
			if( opcao == 1) {

				// Sincronizar ficheiros
					
					if(server.sync(user.name(), mine)!=mine)
					{
						mine=server.sync(user.name(), mine);
						
					}
		

				} else if(opcao == 2) {
					// Listar Ficheiros do servidor
					for(File f: repo.listFiles()){
						//if(f.isDirectory()){
						//for(File g: f.listFiles()){
						System.out.print(f.list());
						//}
						//}
					}


				} else if(opcao == 0) {
					loggout = true;
				} else
					System.out.println("Opcao invalida");
			} while(!loggout);
		}







	}
