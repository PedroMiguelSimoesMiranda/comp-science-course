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

String community = (String)session.getAttribute( FZConstants.ATTRIB_COM);

List<String> communities = (List<String>)session.getAttribute( FZConstants.ATTRIB_COMMUNITIES);

boolean global = Boolean.parseBoolean( (String)session.getAttribute( FZConstants.ATTRIB_GLOBAL));

int resultSize = events.size();
int pageSize = FZConstants.EVENTS_NUM_LINES;
int numPages = resultSize / pageSize + ( (resultSize % pageSize) > 0 ? 1 : 0);
int curPage = first / pageSize;
boolean hasPrevious = curPage > 0;
boolean hasNext = numPages > (curPage + 1);

String query = (String)session.getAttribute( FZConstants.ATTRIB_QUERY);
boolean isQuery = (query != null) && (query.length() > 0);
%>

<div id="cmenu">
<table width="100%">
<td align=left width="25%">
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
  	if( isQuery) {
  %>
<form method="get" action="/SearchServlet" name="album">
  <%
  	} else {
  %>
<form method="get" action="/ListEventsServlet" name="album">
  <%
  	}
  %>
  <table border="0" cellpadding="2" cellspacing="2">
  <%
  	if( isQuery) {
  %>
  <tr>
  <td  style="text-align: right;">Query:</td>
  <td colspan="3"><input size="30" name="<%=FZConstants.ATTRIB_QUERY%>" value="<%=query%>"></td>
  </tr>
  <%
  	}
  %>
  <tr>
        <td style="text-align: right;">Community:</td>
        <td><select name="<%=FZConstants.ATTRIB_COM%>"">
        <%
        	if( FZConstants.COM_ALL.equals( community))
                        	out.println( "<option value=\"" + FZConstants.COM_ALL + "\" selected=\"selected\">" + FZConstants.COM_ALL + "</option>" );
                    	else
                        	out.println( "<option value=\"" + FZConstants.COM_ALL + "\">" + FZConstants.COM_ALL + "</option>" );
                        
                        Iterator<String> it = communities.iterator();
                        while( it.hasNext()) {
                        	String name = it.next();
                        	if( name.equals( community))
                	        	out.println( "<option value=\"" + name + "\" selected=\"selected\">" + name + "</option>" );
                        	else
                	        	out.println( "<option value=\"" + name + "\">" + name + "</option>" );
                        }
        %>
	</select>
</td>
<td>Global query: 
<%
	if( global) {
%>
<input type="checkbox" name="<%=FZConstants.ATTRIB_GLOBAL%>" value="true" checked="checked"/>
<%
	} else {
%>
<input type="checkbox" name="<%=FZConstants.ATTRIB_GLOBAL%>" value="true"/>
<%
	}
%>
</td>
<td>
  <%
  	if( isQuery) {
  %>
<input type="submit" value="New query">
<%
	} else {
%>
<input type="submit" value="New list of events">
<%
	}
%>
</td>
  </tr>
  </table>
</form>

  
  <table border="0" cellpadding="5" cellspacing="5" width="800">
  <%
  	for( int nextEvent = first; nextEvent < events.size() && nextEvent - first < FZConstants.EVENTS_NUM_LINES; nextEvent++) {
  %>
  <tr>

  <%
  	int auxNextEvent = nextEvent;
   EventInfo info = events.get(nextEvent);
  %>
  <td width="105">
  <a href="/EventServlet?pos=<%=nextEvent%>"><%=FZUtils.getString( info.getName(), 30)%></a>
  <td>
  <td width="675">
  <%=FZUtils.getString( info.getDescription(), 80)%>
  <td>
  
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
        <td><a href="listevents.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%=(curPage - 1) * pageSize%>">Previous</a></td>
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
        <td><a href="listevents.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%=(ip + firstLink) * pageSize%>"><%=ip + firstLink%></a></td>
		
			<%
						}
					%>
		<%
			}
		%>
        <%
        	if( hasNext) {
        %>
        <td><a href="listevents.jsp?<%=FZConstants.ATTRIB_EVENTS_POS%>=<%= (curPage + 1) * pageSize %>">next</a></td>
		<% } %>
</tr>
</table>
</div>

</body>
</html>
