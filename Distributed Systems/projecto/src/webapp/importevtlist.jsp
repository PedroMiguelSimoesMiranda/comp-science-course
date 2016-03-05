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
List<EventInfo> events = (List<EventInfo>)session.getAttribute( FZConstants.ATTRIB_EVENTS_LIST);

Integer firstI = (Integer)session.getAttribute( FZConstants.ATTRIB_EVENTS_POS);
int first = 0;
if( firstI != null)
	first = firstI.intValue();
String firstS = request.getParameter( FZConstants.ATTRIB_EVENTS_POS);
if( firstS != null && firstS.trim().length() > 0) {
	try {
		first = Integer.parseInt( firstS);
	} catch( Exception e) {
		// do nothing
	}
}
session.setAttribute( FZConstants.ATTRIB_EVENTS_POS, new Integer( first));

List<String> communities = (List<String>)session.getAttribute( FZConstants.ATTRIB_COMMUNITIES);

int resultSize = events.size();
int pageSize = FZConstants.EVENTS_NUM_LINES;
int numPages = resultSize / pageSize + ( (resultSize % pageSize) > 0 ? 1 : 0);
int curPage = first / pageSize;
boolean hasPrevious = curPage > 0;
boolean hasNext = numPages > (curPage + 1);

%>

<div id="cmenu">
<table width="100%">
<td align=left width="25%">
<a href="/ListEventsServlet">Back to events list</a>
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


<p>Select event to import:</p>


  <table border="0" cellpadding="5" cellspacing="5" width="800">
  <%
  	for( int nextEvent = first; nextEvent < events.size() && nextEvent - first < FZConstants.EVENTS_NUM_LINES; nextEvent++) {
  %>
  <tr>

  <%
  	int auxNextEvent = nextEvent;
	if( nextEvent < events.size() ) {
  	  EventInfo info = events.get(nextEvent);
  %>
  <td width="760">
  
  <a href="/EventServlet?pos=<%=nextEvent%>"><%=FZUtils.getString( info.getName(), 100)%></a>
  </td>
  
  <%
    	}
    %>
  
  </tr>
  <%
  	}
  %>
  </table>

<table  border="0" cellpadding="5" cellspacing="5">
<tr>
        <td style="text-align: right;">Results page:</td>
        <%
        	if( hasPrevious) {
        %>
        <td><a href="importevtlist.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%=(curPage - 1) * pageSize%>">Previous</a></td>
		<%
			}
		%>
        <%
        	int firstLink = curPage - 4;
                if( firstLink < 0)
                	firstLink = 0;
                for ( int ip = 0; ip < 10 && ip + firstLink < numPages; ip++) { 
                	if( ip + firstLink == curPage) {
        %>
        <td><%=ip + firstLink%></td>
			<%
				} else {
			%>
        <td><a href="importevtlist.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%=(ip + firstLink) * pageSize%>"><%=ip + firstLink%></a></td>
		
			<%
						}
					%>
		<%
			}
		%>
        <%
        	if( hasNext) {
        %>
        <td><a href="importevtlist.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%= (curPage + 1) * pageSize %>">next</a></td>
		<% } %>
</tr>

</table>
</div>

</body>
</html>
