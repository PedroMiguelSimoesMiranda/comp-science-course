package fanzone.web;

import java.io.*;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fanzone.*;

public class ResourceServlet extends HttpServlet
{
	private static final long serialVersionUID = 9149898847523978814L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		WPWebServer.tlSession.set(session);
		try {
			String id = request.getParameter( "id");
			String posS = request.getParameter( FZConstants.ATTRIB_RESOURCE_POS);
			int pos = 0;
			try {
				pos = Integer.parseInt(posS);
			} catch( Exception e) {
				// do nothing
			}
			
			String type = request.getParameter( "type" );
			String length = request.getParameter( "length");
			
			if( type != null && type.trim().length() > 0)
				response.setContentType( type);
			if( length != null && length.trim().length() > 0)
				response.setContentLength( Integer.parseInt( length));
			
			byte[] arr = new byte[1024];
			FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
			List<ResourceInfo> lst = (List<ResourceInfo>)session.getAttribute( FZConstants.ATTRIB_RESOURCE_LIST);
			ResourceInfo curResource = null;
			try {
				curResource = lst.get(pos);
			} catch( Exception e) {
				curResource = (ResourceInfo)session.getAttribute( FZConstants.ATTRIB_RESOURCE_CURRENT);
				
			}
			
			
			String name = curResource.getName().toLowerCase();
			MimetypesFileTypeMap map = new MimetypesFileTypeMap();
			String ct = map.getContentType(name);
			
			if( ct.equalsIgnoreCase( "application/octet-stream")) {
				if( name.endsWith(".mp3")) {
					ct = "audio/mpeg";
				}
				
			}
			response.setContentType(ct);
			
			InputStream stream = proc.getResourceStream( id, curResource);
			OutputStream out = response.getOutputStream();
			for( ; stream != null; ) {
				int n = stream.read( arr);
				if( n <= 0)
					break;
				out.write( arr, 0, n);
			}
			out.close();
			stream.close();
		} catch( IOException e) {
			response.setContentLength( 0);
		}
	}

}
