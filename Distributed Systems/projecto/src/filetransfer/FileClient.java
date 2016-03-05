package filetransfer;
import utilities.CopyFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class FileClient {

	
	public static void download(FileServer server, String filepath, File dest) throws IOException {
		CopyFile.copyfile(server.getInputStream(filepath), new FileOutputStream(dest));
	}

	public static void downloadFile(String url, String filepath) throws NotBoundException, IOException{
		System.out.println("DOWNLOAD: "+System.getProperty("user.dir") + filepath);
		System.out.println("NO FILE CLIENT URL: "+url);
		try {
			FileServer server = (FileServer) Naming.lookup(url);
			download(server, filepath, new File(System.getProperty("user.dir") + filepath));
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.err.println("PEER NAO ENCONTRADO");}
	}

}