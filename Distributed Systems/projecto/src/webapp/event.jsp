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
		session.setAttribute( FZConstants.ATTRIB_RES_INIMPORT, new Boolean(false));
		session.setAttribute( FZConstants.ATTRIB_RES_INLISTIMPORT, new Boolean(false));

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
EventInfo event = (EventInfo)session.getAttribute( FZConstants.ATTRIB_CUREVENT);

List<ResourceInfo> resources = event.getResourceInfo();

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
session.setAttribute( FZConstants.ATTRIB_RESOURCE_LIST, event.getResourceInfo());

String community = (String)session.getAttribute( FZConstants.ATTRIB_COM);

List<String> communities = (List<String>)session.getAttribute( FZConstants.ATTRIB_COMMUNITIES);

int resultSize = resources.size();
int pageSize = FZConstants.RESOURCE_NUM_LINES * FZConstants.RESOURCE_NUM_COLS;
int numPages = resultSize / pageSize + ( (resultSize % pageSize) > 0 ? 1 : 0);
int curPage = first / pageSize;
boolean hasPrevious = curPage > 0;
boolean hasNext = numPages > (curPage + 1);

Boolean inImportS = (Boolean)session.getAttribute( FZConstants.ATTRIB_EVT_INIMPORT);
boolean inImport = (inImportS != null) && inImportS;
Boolean inListImportS = (Boolean)session.getAttribute( FZConstants.ATTRIB_EVT_INLISTIMPORT);
boolean inListImport = (inListImportS != null) && inListImportS;
%>


<div id="cmenu">
<table width="100%">
<td align=left width="25%">
  <%
  	if( inListImport) {
  %>
<a href="importevtlist.jsp">Back to import events list</a>
  <%
  	} else {
  %>
<a href="listevents.jsp">Back to events list</a>
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
<form width="100%" method="get" action="/DoImportEventServlet" name="event">
  <%
  	} else {
  %>
<form width="100%" method="get" action="importresource.jsp" name="event">
  <%
  	} 
  %>
  <table border="0" cellpadding="2" cellspacing="2" width="100%">
    <%
  	if( inImport) {
  %>
        <tr>
        <td colspan="2" style="text-align: center;"><center><button type="submit" value="Import" name="doit">Do import</button></center></td>
      </tr>
  <%
  	} else {
  %>
        <tr>
        <td colspan="2" style="text-align: center;"><center><button type="submit" value="Import" name="doit">Add resource</button></center></td>
      </tr>
  <%
  	}
  %>
  
  <tr>
  <td colspan="2" style="text-align: left;"><b>Event:</b></td>
  </tr>
  <%
  	if( inImport) {
  %>
       <tr> 
        <td style="text-align: right;"><b>Community:</b></td>
        <td><select name="<%=FZConstants.ATTRIB_COM%>"">
        <%
                Iterator<String> it = communities.iterator();
        boolean firstCom = true;
                while( it.hasNext()) {
                	String name = it.next();
                	if( firstCom ) {
        	        	out.println( "<option value=\"" + name + "\" selected=\"selected\">" + name + "</option>" );
        	        	firstCom = false;
                	} else
        	        	out.println( "<option value=\"" + name + "\">" + name + "</option>" );
                }
        %>
	</select>
</td>
</tr>
  
  <tr>
  <td style="text-align: right;vertical-align: top;" width="100"><b>Name:</b></td><td><input size="40" name="<%=FZConstants.ATTRIB_EV_NAME%>" value="<%=event.getName()%>"></td>
  </tr>
  <tr>
  <td style="text-align: right;vertical-align: top;" width="100"><b>Description:</b></td><td><textarea cols="80" rows="5" name="<%=FZConstants.ATTRIB_EV_DESCRIPTION%>"><%=event.getDescription()%></textarea></td>
  </tr>
  <%
  	} else {
  %>
  <tr>
  <td style="text-align: right;vertical-align: top;" width="100"><b>Name:</b></td><td><%=event.getName()%></td>
  </tr>
  <tr>
  <td style="text-align: right;vertical-align: top;" width="100"><b>Description:</b></td><td><%=event.getDescription()%></td>
  </tr>
  <%
  	} 
  %>  
  
</table>
  </form>
  <table border="0" cellpadding="5" cellspacing="5" width="800">
  <td colspan="2" style="text-align: left;"><b>Resources:</b></td>
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
        <td style="text-align: right;">Resources page:</td>
        <%
        	if( hasPrevious) {
        %>
        <td><a href="event.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%=(curPage - 1) * pageSize%>">Previous</a></td>
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
        <td><a href="event.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%=(ip + firstLink) * pageSize%>"><%=ip + firstLink%></a></td>
		
			<%
						}
					%>
		<%
			}
		%>
        <%
        	if( hasNext) {
        %>
        <td><a href="event.jsp?<%=FZConstants.ATTRIB_RESOURCE_POS%>=<%= (curPage + 1) * pageSize %>">next</a></td>
		<% } %>
</tr>
</table>
</div>

</body>
</html>
