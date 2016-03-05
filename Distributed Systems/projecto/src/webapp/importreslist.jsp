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
List<ResourceInfo> resources = (List<ResourceInfo>)session.getAttribute( FZConstants.ATTRIB_RESOURCE_LIST);

Integer firstI = (Integer)session.getAttribute( FZConstants.ATTRIB_RESOURCE_POS);
int first = 0;
if( firstI != null)
	first = firstI.intValue();
String firstS = request.getParameter( FZConstants.ATTRIB_RESOURCE_POS);
if( firstS != null && firstS.trim().length() > 0) {
	try {
		first = Integer.parseInt( firstS);
	} catch( Exception e) {
		// do nothing
	}
}
session.setAttribute( FZConstants.ATTRIB_RESOURCE_POS, new Integer( first));

String community = (String)session.getAttribute( FZConstants.ATTRIB_COM);

List<String> communities = (List<String>)session.getAttribute( FZConstants.ATTRIB_COMMUNITIES);

boolean global = Boolean.parseBoolean( (String)session.getAttribute( FZConstants.ATTRIB_GLOBAL));

int resultSize = resources.size();
int pageSize = FZConstants.ALBUM_NUM_LINES * FZConstants.ALBUM_NUM_COLS;
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
<a href="event.jsp">Back to event</a>
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
<p>Select resource to import:</p>


  <table border="0" cellpadding="5" cellspacing="5" width="800">
  <%
  	for( int nextPhoto = first; nextPhoto < resources.size() && nextPhoto - first < FZConstants.ALBUM_NUM_COLS * FZConstants.ALBUM_NUM_LINES; ) {
  %>
  <tr>

  <%
  	int auxNextPhoto = nextPhoto;
      for( int rowPos = 0; rowPos < FZConstants.ALBUM_NUM_COLS; rowPos++ ) {
  %>
  <td width="105">
  
  <%
    	if( nextPhoto < resources.size() ) {
        	  ResourceInfo info = resources.get(nextPhoto);
           	  String url = info.getHTTPURL();
        	  if( url == null)
        	  	url = "/GetResourceServlet?id=" + info.getID() + "&" + FZConstants.ATTRIB_RESOURCE_POS + "=" + nextPhoto;
        	  out.println( "<a href=\"/SingleResourceServlet?" + FZConstants.ATTRIB_RESOURCE_POS + "=" + nextPhoto + "\"><img width=60 length=60 src=\"" + url + "\"></a>");
        	  nextPhoto++;
          }
    %>
  
  
  </td>
  
  <%
    	}
    %>
  
  </tr>
  <tr>
  <%
  	nextPhoto = auxNextPhoto;
      for( int rowPos = 0; rowPos < 7; rowPos++ ) {
  %>
  <td width="105">
  
  <%
    	if( nextPhoto < resources.size() ) {
        	  ResourceInfo info = resources.get(nextPhoto);
        	  out.write( info.getName());
        	  nextPhoto++;
          }
    %>
  
  
  </td>
  
  <%
    	}
    %>
  </tr>
  <tr>
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
        <td><a href="importreslist.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%=(curPage - 1) * pageSize%>">Previous</a></td>
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
        <td><a href="importreslist.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%=(ip + firstLink) * pageSize%>"><%=ip + firstLink%></a></td>
		
			<%
						}
					%>
		<%
			}
		%>
        <%
        	if( hasNext) {
        %>
        <td><a href="importreslist.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%= (curPage + 1) * pageSize %>">next</a></td>
		<% } %>
</tr>
</table>
</div>

</body>
</html>
