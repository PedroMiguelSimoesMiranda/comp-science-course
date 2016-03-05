package fanzone.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fanzone.*;

public class EventServlet extends HttpServlet
{
	private static final long serialVersionUID = 5711650657984287781L;

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

			FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
			if( proc == null) {
				session.setAttribute( "error", "INTERNAL ERROR");
				request.getRequestDispatcher("/").forward(request,response);
				return;
			}
			
			
			String eventPos = (String)request.getParameter("pos");
			int eventPosN = -1;
			List<EventInfo> events = (List<EventInfo>)session.getAttribute( FZConstants.ATTRIB_EVENTS_LIST);
			EventInfo ev = null;
			try {
				eventPosN = Integer.parseInt( eventPos);
				ev = events.get( eventPosN);
			} catch( Exception e) {
				session.setAttribute( "error", "Cannpot get selected event : " + e.getMessage());
				request.getRequestDispatcher("/listevents.jsp").forward(request,response);
				return;
			}

			session.setAttribute( FZConstants.ATTRIB_CUREVENT, ev);
			session.setAttribute( FZConstants.ATTRIB_RESOURCE_LIST, ev.getResourceInfo());
			session.setAttribute( FZConstants.ATTRIB_RESOURCE_POS, new Integer(0));
			
			request.getRequestDispatcher("/event.jsp?" + FZConstants.ATTRIB_RESOURCE_POS + "=\"0\"").forward(request,response);

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
