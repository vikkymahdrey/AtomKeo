<%@page import="com.agiledge.atom.constants.SettingsConstant"%>
<%@page import="com.agiledge.atom.entities.*"%>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<div class="container">
<div class="header">
<div style="float: right; font-size: 9;">
</div>
<div style="float: right;">
<div style="display: inline;">
<a href="<%=request.getSession().getAttribute("homePage")%>">Home</a> &nbsp;&nbsp;|&nbsp;&nbsp;<a
href="logout">Logout</a>
</div>
Welcome
<%if(SettingsConstant.showDesigOrRole.equalsIgnoreCase("designation")){ %>
${userLoggedIn.designationName}
<%}else{ 
Role role=(Role)request.getSession().getAttribute("empRole");
out.print(role.getUsertype());%>
<%} %>
</div>
<table>
<tr>
<td><a href="http://www.agiledgesolutions.com"><img
src="images/agile.png" /></a></td>
<td>
<div style="float: left">
<h1 style="color: #FF4000;">ATOm</h1>
<h4>Agiledge Transport Optimization manager</h4>
</div>
</td>
<td style="float: right;">
<%if(SettingsConstant.comp.equalsIgnoreCase("cd")||SettingsConstant.comp.equalsIgnoreCase("cd1")) {%>
<img style="float: right:;" alt="" src="CROSSDOMAIN_logosmall.jpg">
<%} else{%> <img style="float: right:;" alt=""> <%} %>

</td>
</tr>
</table>
<div class="clear"></div>
</div>
<div class="clr"></div>
<div id="cssmenu">
<ul>
${userMenu}
</ul>
</div>
<%
String message = "";
try {
message = request.getAttribute("status").toString();
System.out.println("Statuss"+message);
} catch (Exception e) {
message = "";
}
request.setAttribute("status", "");
if (message != null && !message.equals("")) {
System.out.println("Statuss Error IN IF"+message);
%>
<div id="statusMessage">
<%=message%>
</div>
<%
}
%>
</div>