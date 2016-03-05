package fanzone.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fanzone.*;


public class DoImportResourceServlet extends HttpServlet
{
	private static final long serialVersionUID = -1883941235243779131L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( FZConstants.ACCEPT_ONLY_LOCAL_REQUESTS) {
			try {
				java.net.InetAddress addr = java.net.InetAddress.getByName( request.getRemoteHost());
				if( ! (addr.isSiteLocalAddress() || addr.isLoopbackAddress())) {
					request.getRequestDispatcher("/remoteerror.html").forward(request,response);
					return;
				}
			} catch( Exception e) {
				//
			}
		}
		HttpSession session = request.getSession();
		WPWebServer.tlSession.set(session);
		javax.servlet.ServletContext srvContext = (javax.servlet.ServletContext)session.getServletContext();
		try {
			String user = (String)session.getAttribute( FZConstants.ATTRIB_USER);
			String userCtx = (String)srvContext.getAttribute( FZConstants.ATTRIB_USER);
			if( user == null) {
				session.setAttribute( "error", "User not logged in.");
				request.getRequestDispatcher("/login.jsp").forward(request,response);
				return;
			}
			if( userCtx == null || ! userCtx.equalsIgnoreCase( user)) {
				session.setAttribute( "error", "Multiple users trying to access the same server.");
				request.getRequestDispatcher("/login.jsp").forward(request,response);
				return;
			}
			
			Boolean inListImport = (Boolean)session.getAttribute( FZConstants.ATTRIB_RES_INLISTIMPORT);
			EventInfo event = (EventInfo)session.getAttribute( FZConstants.ATTRIB_CUREVENT);
			
			FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
			if( proc == null) {
				session.setAttribute( "error", "INTERNAL ERROR");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			if( event == null) {
				session.setAttribute( "error", "Internal error: Trying to import resource for unknown event.");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			
			ResourceInfo res = (ResourceInfo)session.getAttribute( FZConstants.ATTRIB_RESOURCE_CURRENT);
			if( res == null) {
				session.setAttribute( "error", "Internal error: unknown resource to import.");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			String makeCopyS = request.getParameter( FZConstants.ATTRIB_COPY);
			boolean makeCopy = false;
			if( makeCopyS != null && makeCopyS.equalsIgnoreCase("true"))
				makeCopy = true;
				
            String comm = (String) session.getAttribute(FZConstants.ATTRIB_COM);
			proc.doResourceImport( event, comm, res, makeCopy);
			
			if( inListImport == null || inListImport.booleanValue() == false)
				request.getRequestDispatcher("/event.jsp").forward(request,response);
			else
				request.getRequestDispatcher("/importreslist.jsp").forward(request,response);
		} catch( Exception e) {
			StringBuffer buf = new StringBuffer();
			buf.append("Internal error : " + e.getMessage() + "\n");
			if( FZConstants.DEBUG_STACKTRACE) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream( baos);
				e.printStackTrace( ps);
				ps.flush();
				buf.append("<PRE>");
				buf.append( new String(baos.toByteArray()));
				buf.append("</PRE>");
			}
			
			session.setAttribute( "error", buf.toString());
			request.getRequestDispatcher("/").forward(request,response);
		}
	}

}
