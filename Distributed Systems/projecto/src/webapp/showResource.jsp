 <%@ page import="fanzone.*" %>
 <%@ page import="java.util.*" %>
<%
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
		javax.servlet.ServletContext srvContext = (javax.servlet.ServletContext)session.getServletContext();
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt">
<head>
<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="cache-control" content="no-store">
  <link href="fanzone.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="titulo">
<h1>FanZone</h1>

</div>


<%
ResourceInfo info = (ResourceInfo)session.getAttribute( FZConstants.ATTRIB_RESOURCE_CURRENT);
String nextPhotoS = request.getParameter( FZConstants.ATTRIB_RESOURCE_POS);
int nextPhoto = 0;
if( nextPhotoS != null)
	try {
		nextPhoto = Integer.parseInt( nextPhotoS);
	} catch( Exception e) {
		// do nothing
	}
		

String url = info.getHTTPURL();
if( url == null)
	url = "/GetResourceServlet?id=" + info.getID() + "&" + FZConstants.ATTRIB_RESOURCE_POS + "=" + nextPhoto;
Boolean inImportS = (Boolean)session.getAttribute( FZConstants.ATTRIB_RES_INIMPORT);
boolean inImport = (inImportS != null) && inImportS;
Boolean inListImportS = (Boolean)session.getAttribute( FZConstants.ATTRIB_RES_INLISTIMPORT);
boolean inListImport = (inListImportS != null) && inListImportS;
%>
<div id="cmenu">
<table width="100%">
<td align=left width="25%">
  <%
  	if( inListImport) {
  %>
<p><a href="importreslist.jsp">Back to import resource list</a></p>
  <%
  	} else {
  %>
<p><a href="event.jsp">Back to event</a></p>
  <%
  	}
  %>
</td>
<td align=right width="75%">
<a href="communities.jsp">Communities</a>&nbsp;|&nbsp;
<a href="ListEventsServlet">Events</a>&nbsp;|&nbsp;
<a href="importevent.jsp">Import events</a>&nbsp;|&nbsp;
<a href="search.jsp">Search</a>&nbsp;|&nbsp;
</td>
</table>
</div>

<div id="main">
<%
String error = (String)session.getAttribute( "error");
String errorCustom = (String)session.getAttribute( "error-custom");
session.setAttribute( "error", null);
session.setAttribute( "error-custom", null);
if( error != null && error.length() > 0) {
	out.println("<p><font color =\"red\">ERROR:&nbsp;" + error + "</font></p><br>");
}
if( errorCustom != null && errorCustom.length() > 0) {
	out.println("<p><b>ERROR:&nbsp;" + errorCustom + "</b></p><br>");
}
%>


  <%
  	if( inImport) {
  %>
<form method="get" action="/DoImportResourceServlet" name="get">
<input type="submit" value="Import">
<input type="checkbox" name="<%=FZConstants.ATTRIB_COPY%>" value="true"> Make Copy</input>
</form>
  <%
  	}
  %>
<object data="<%= url %>"></object>
</div>

</body>
</html>
