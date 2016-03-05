package fanzone.web;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fanzone.*;

public class AddToCommunityServlet extends HttpServlet
{
	private static final long serialVersionUID = -6777571719955722068L;

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
			
			EventInfo event = (EventInfo)session.getAttribute( FZConstants.ATTRIB_CUREVENT);

			String com = request.getParameter( FZConstants.ATTRIB_COM);
			if( com == null  || com.trim().length() == 0) {
				session.setAttribute( "error", "Inavlid community name while adding event to community.");
			} else if( event == null) {
				session.setAttribute( "error", "Internal error : unknown event.");
			} else {
				boolean result = proc.addToCommunity( com, event);
				if( result) {
					List<String> lst = (List<String>)event.getCommunities();
					lst.add( com);
				}
			}
			request.getRequestDispatcher("/event.jsp").forward(request,response);

		} catch( Exception e) {
			session.setAttribute( "error", "Internal error : " + e.getMessage());
			request.getRequestDispatcher("/").forward(request,response);
		}
	}

}
