<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt">
<head>

  <link href="fanzone.css" rel="stylesheet" type="text/css">
<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="cache-control" content="no-store">

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
<% String error = (String)session.getAttribute( "error");
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

<p>Login information:</p>
<form method="get" action="/LoginServlet" name="login">&nbsp;<span style="font-weight: bold;"><br>
  </span>
  <table style="text-align: left;" border="0" cellpadding="2" cellspacing="2">
    <tbody>
      <tr>
        <td style="text-align: right;">Name:</td>
        <td><input size="30" name="nome"></td>
      </tr>
      <tr>
        <td style="text-align: right;">Password:</td>
        <td><input type="PASSWORD"  size="30" name="segredo"></td>
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
        <td style="width: 50%; text-align: center;"><button type="submit" value="Login" name="login">Login</button></td>
        <td><button type="submit" value="Cancelar" name="login">Cancel</button></td>
      </tr>
    </tbody>
  </table>

</div>

</body>
</html>
