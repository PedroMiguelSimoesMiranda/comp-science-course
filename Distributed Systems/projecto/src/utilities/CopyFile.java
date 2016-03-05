package utilities;

import java.io.*;

public class CopyFile {

	public static void copyfile(InputStream in, OutputStream out){
		System.err.println("A TENTAR COPIAR");
		try
		{
			int length;
			byte[] buf = new byte[1024];

			while ((length = in.read(buf)) > 0)
				out.write(buf, 0, length);

			in.close();
			out.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());  
		}
	}
}