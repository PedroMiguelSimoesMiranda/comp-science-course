package fanzone.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fanzone.*;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 4429422389056947855L;

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
			session.setAttribute( "user", null);
			session.setAttribute( "password", null);
			session.setAttribute( "error", null);

			String value = request.getParameter( "login");
			if( value != null && value.equalsIgnoreCase( "Login")) {
				String name = request.getParameter( "nome");
				if( name == null)
					name = "";
				name.trim();
				String segredo = request.getParameter( "segredo");
				if( segredo == null)
					segredo = "";
				name.trim();
				if( name == null || name.length() == 0) {
					session.setAttribute( "error", "Name must be non null.");
					request.getRequestDispatcher("/login.jsp").forward(request,response);
				} else if( segredo == null || segredo.length() == 0) {
					session.setAttribute( "error", "Password must be non null.");
					request.getRequestDispatcher("/login.jsp").forward(request,response);
				} else {
					
					FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
					boolean validUser = proc.login( name, segredo);
					if( ! validUser) {
						session.removeAttribute( "user");
						session.removeAttribute( "password");
						srvContext.removeAttribute( "user");
						session.setAttribute( "error", "Invalid login/password.");
						request.getRequestDispatcher("/login.jsp").forward(request,response);
						return;
					}

					srvContext.setAttribute( "user", name);
					session.setAttribute( "user", name);
					session.setAttribute( "password", segredo);
										
					request.getRequestDispatcher("/ListEventsServlet").forward(request,response);
				}
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
				
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
