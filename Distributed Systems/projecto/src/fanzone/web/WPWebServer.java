package fanzone.web;

import java.io.*;


import org.mortbay.jetty.*;
import org.mortbay.jetty.webapp.*;
import org.mortbay.jetty.handler.*;
import org.mortbay.jetty.servlet.*;

import fanzone.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class WPWebServer
{
	public static ThreadLocal<HttpSession> tlSession = new ThreadLocal<HttpSession>();
	int port;
	String docbase;
	Server server;
	FanZoneProcessor proc;
	WebAppContext webapp;
	
	public WPWebServer( int port, String docbase, FanZoneProcessor proc) {
		this.port = port;
		this.docbase = docbase;
		this.proc = proc;
	}
	
	public void setCustomMessage( String str) {
		tlSession.get().setAttribute("error-custom", str);
	}

	public void stop() throws FZException {
		if( server != null)
			try {
				server.stop();
			} catch( Exception e) {
				throw new FZException( "Impossivel terminar servidor.", e);
			}
	}
	
	public void start() throws FZException {
		try {
			server = new Server(port);
			WebAppContext webapp = new WebAppContext(docbase, "/");
			webapp.setAttribute( "FZProcessor", proc);
			HandlerList hl = new HandlerList();   
			hl.setHandlers(new Handler[]{webapp});   
			server.setHandler(hl);   
		
			server.start();
		} catch( Exception e) {
			throw new FZException( "Impossivel inicar servidor.", e);
		}
	}

}
