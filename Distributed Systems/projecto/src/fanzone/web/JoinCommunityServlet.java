package fanzone.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fanzone.FZConstants;
import fanzone.FanZoneProcessor;

public class JoinCommunityServlet extends HttpServlet
{
	private static final long serialVersionUID = -7250858026881628789L;

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

			String type = request.getParameter( "join");
			boolean isJoin = false;
			String com = request.getParameter( FZConstants.ATTRIB_COM);
			String compwd = request.getParameter( FZConstants.ATTRIB_COM_PWD);
			String comurl = request.getParameter( FZConstants.ATTRIB_COM_URL);
			String info = "";
			if( type.equalsIgnoreCase( "join")) {
				isJoin = true;
				info = " while joining community.";
			} else if( type.equalsIgnoreCase( "create")) {
				info = " while creating community.";
			} else {
				session.setAttribute( "error", "Inavlid community operation.");
				request.getRequestDispatcher("/communities.jsp").forward(request,response);
				return;
			}
			
			if( com == null  || com.trim().length() == 0) {
				session.setAttribute( "error", "Inavlid community name" + info);
			} else if( compwd == null) {
				session.setAttribute( "error", "Inavlid community password" + info);
			} else if( comurl == null) {
				session.setAttribute( "error", "Inavlid community contact" + info);
			} else {
				if( isJoin) {
					boolean result = proc.joinCommunity( com, compwd, comurl);
					if( ! result) {
						session.setAttribute( "error", "Error joining community : " + com);
					}
				} else {
					boolean result = proc.createCommunity( com, compwd);
					if( ! result) {
						session.setAttribute( "error", "Error creating community : " + com);
					}
				}
			}
			request.getRequestDispatcher("/communities.jsp").forward(request,response);

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
