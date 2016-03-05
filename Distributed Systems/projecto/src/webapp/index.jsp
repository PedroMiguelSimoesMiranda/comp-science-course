<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
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
<a href="login.jsp">Login</a>
</td>
<td align=right width="75%">
</td>
</table>
</div>

<div id="main">
<%String error = (String)session.getAttribute( "error");
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

<p>Aplica&ccedil;&atilde;o de partilha de informação sobre artistas.</p>
<p>Funcionalidades:</p>
<ul>
  <li>cria&ccedil;&atilde;o de comunidades para partilha de informação</li>
  <li>eventos associados a cada comunidade (e.g. notícia sobre album, notí­cia sobre concerto, etc.)</li>
  <li>recursos associados a evento (e.g. fotografias, vídeos, músicas)</li>
  <li>obtenção de cópias dos recursos</li>
</ul>
<p>Entre na aplica&ccedil;&atilde;o seguindo <a href="login.jsp">este link</a>.</p>


</div>

</body>
</html>
