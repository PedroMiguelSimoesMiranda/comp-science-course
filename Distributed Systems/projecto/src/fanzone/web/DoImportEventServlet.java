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


public class DoImportEventServlet extends HttpServlet
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
			
			String com = request.getParameter( FZConstants.ATTRIB_COM);
			String evName = request.getParameter( FZConstants.ATTRIB_EV_NAME);
			String evDescription = request.getParameter( FZConstants.ATTRIB_EV_DESCRIPTION);
			
			String menuOption = request.getParameter( "doit");

			Boolean inListImport = (Boolean)session.getAttribute( FZConstants.ATTRIB_EVT_INLISTIMPORT);
			
			FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
			if( proc == null) {
				session.setAttribute( "error", "INTERNAL ERROR");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			if( com == null  || com.trim().length() == 0) {
				com = proc.listCommunities().get(0);
			}
			if( ! menuOption.equalsIgnoreCase("Import")) {
				if( inListImport == null || inListImport.booleanValue() == false)
					request.getRequestDispatcher("/ListEventsServlet").forward(request,response);
				else
					request.getRequestDispatcher("/importevtlist.jsp").forward(request,response);
				return;
			}
			
			EventInfo event = (EventInfo)session.getAttribute( FZConstants.ATTRIB_CUREVENT);
			event.setName(evName);
			event.setDescription(evDescription);
			List<String> comms = event.getCommunities();
			comms.clear();
			comms.add(com);
			
			proc.doEventImport( event);
                                                     proc.addToCommunity(com, event);
			
			if( inListImport == null || inListImport.booleanValue() == false)
				request.getRequestDispatcher("/ListEventsServlet").forward(request,response);
			else
				request.getRequestDispatcher("/importevtlist.jsp").forward(request,response);
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
