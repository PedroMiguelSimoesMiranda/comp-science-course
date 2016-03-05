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
	FanZoneProcessor proc = (FanZoneProcessor)session.getServletContext().getAttribute( "FZProcessor");
if( proc == null) {
	out.println("<p><font color =\"red\">INTERNAL ERROR 0000</font></p><br>");
	return;
}
List<String> communities = proc.listCommunities();
session.setAttribute( FZConstants.ATTRIB_COMMUNITIES, communities);
%>

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
%>
<center>

  <table border="1" cellpadding="5" cellspacing="2" width="100%">
  <tr valign="top">
  <td width="49%">

<form method="get" action="/LeaveCommunityServlet" name="leave">
  <table style="text-align: left;" border="0" cellpadding="2" cellspacing="2" width="100%">
    <tbody>
  <tr>
        <td style="text-align: center" colspan="2"><h2>Communities</h2></td>
        </tr>
        <%
        	Iterator<String> it = communities.iterator();
                while( it.hasNext()) {
                	String name = it.next();
        %>
        
        <tr>
        <td style="text-align: center" width="50%" style="background-color=#EEEEEE;"><%=name%></td>
        <td align="center" style="background-color=#EEEEEE;"><button type="submit" value="Unjoin <%=name%>" name="<%=FZConstants.ATTRIB_COM%>">Unjoin <%=name%></button></td>
        
        <%
                	}
                %>
	</select>
</td>
</tr>
</table>
</form>

</td><td>

<form method="get" action="/JoinCommunityServlet" name="join">
  <table style="text-align: left;" border="0" cellpadding="2" cellspacing="2">
    <tbody>
      <tr>
        <td style="text-align: right;">Community:</td>
        <td><input size="30" name="<%=FZConstants.ATTRIB_COM%>"></td>
      </tr>
      <tr>
        <td style="text-align: right;">Password:</td>
        <td><input type="PASSWORD"  size="30" name="<%=FZConstants.ATTRIB_COM_PWD%>"></td>
      </tr>
      <tr>
        <td style="text-align: right;">URL for join:</td>
        <td><input size="30" name="<%=FZConstants.ATTRIB_COM_URL%>"></td>
      </tr>
      <tr>
      <td>
      &nbsp;
      </td>
      <td>
      &nbsp;
      </td>
      </tr>
      <tr>
        <td style="width: 50%; text-align: center;"><button type="submit" value="Create" name="join">Create</button></td>
        <td style="width: 50%; text-align: center;"><button type="submit" value="Join" name="join">Join</button></td>
      </tr>
    </tbody>
  </table>
</form>
</td>
</tr>
</table>

</center>
</div>

</body>
</html>
