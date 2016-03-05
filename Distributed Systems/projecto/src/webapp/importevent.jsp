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

<div id="cmenu">
<table width="100%">
<td align=left width="25%">
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

FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
String[] imports = proc.listEventImports();
%>


<p>Search events for importing:</p>
<form method="get" action="/ImportServlet" name="query">
  <table style="text-align: left;" border="0" cellpadding="2" cellspacing="2">
    <tbody>
     
     <tr> 
        <td style="text-align: right;">Import from:</td>
        <td><select name="<%=FZConstants.ATTRIB_IMPORT%>"">
        <%
        for( int i = 0; i < imports.length; i++) {
                	String name = imports[i];
                	if( i== 0)
        	        	out.println( "<option value=\"" + name + "\" selected=\"selected\">" + name + "</option>" );
                	else
        	        	out.println( "<option value=\"" + name + "\">" + name + "</option>" );
                }
        %>
	</select>
</td>
</tr>
      <tr>
        <td style="text-align: right;">Query:</td>
        <td><input size="30" name="<%=FZConstants.ATTRIB_QUERY%>"></td>
      </tr>
      <tr>
        <td colspan="2" style="width: 50%; text-align: center;"><center><button type="submit" value="Import" name="query">Search</button></center></td>
      </tr>
    </tbody>
  </table>

</form>
</div>

</body>
</html>
