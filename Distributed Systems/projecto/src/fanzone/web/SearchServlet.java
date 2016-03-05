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

public class SearchServlet extends HttpServlet
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

			String query = request.getParameter( FZConstants.ATTRIB_QUERY);
			String com = request.getParameter( FZConstants.ATTRIB_COM);
			if( com == null  || com.trim().length() == 0)
				com = FZConstants.COM_ALL;
			String global = request.getParameter( FZConstants.ATTRIB_GLOBAL);
			if( global == null  || global.trim().length() == 0)
				global = "false";
			
			session.setAttribute( FZConstants.ATTRIB_COM, com);
			session.setAttribute( FZConstants.ATTRIB_GLOBAL, global);

			if( query == null || query.trim().length() == 0) {
				request.getRequestDispatcher("/search.jsp").forward(request,response);
				return;
			}
			session.setAttribute( FZConstants.ATTRIB_QUERY, query);
			
			
			FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
			if( proc == null) {
				session.setAttribute( "error", "INTERNAL ERROR");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			
			List<EventInfo> events = proc.searchEvents( com, ! Boolean.parseBoolean( global), query);
			session.setAttribute( FZConstants.ATTRIB_EVENTS_LIST, events);
			session.setAttribute( FZConstants.ATTRIB_EVENTS_POS, 0);
			session.setAttribute( FZConstants.ATTRIB_EVT_INIMPORT, new Boolean(false));
			session.setAttribute( FZConstants.ATTRIB_EVT_INLISTIMPORT, new Boolean(false));
			session.setAttribute( FZConstants.ATTRIB_RES_INIMPORT, new Boolean(false));
			session.setAttribute( FZConstants.ATTRIB_RES_INLISTIMPORT, new Boolean(false));

			session.setAttribute( FZConstants.ATTRIB_COMMUNITIES, proc.listCommunities());

			request.getRequestDispatcher("/listevents.jsp").forward(request,response);

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
