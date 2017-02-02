<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.dto.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
		function goHome(){
			window.location="employeeHome";
		}
		
	</script>
	<div class="header">
		<table width="100%">
			<tr>
				<td width="10%"><a href="http://www.agiledgesolutions.com"><img
						src="images/agile.png" /></a></td>

				<td width="90%">
					<div style="float: left">
						<h1 style="color: #FF4000;">ATOm</h1>

						<h4>Agiledge transport optimization manager</h4>
					</div>
					<div id="#header_user_details"
						style="float: right; height: 100%; vertical-align: bottom; font-size: 9;">

						Welcome
						<%

						EmployeeDto empDtoInHeader=null; 
						try{
							
								String role=session.getAttribute("role").toString();
								if(empDtoInHeader==null) {
									empDtoInHeader = (EmployeeDto)request.getAttribute("empDto");
								}
								
							}catch(NullPointerException ne) {
						 
								empDtoInHeader = (EmployeeDto)request.getAttribute("empDto");
							}
						session.setAttribute("userDto",empDtoInHeader);
						 Role role=(Role)request.getAttribute("role");
						%>
						<%=empDtoInHeader.getDisplayName() %>
						(
						<%=role.getName() %>
						)


					</div>
				</td>
		</table>
		<div class="clear"></div>
	</div>
	<%
	Employee e = (Employee) session.getAttribute("userLoggedIn");	
	if (e == null) {
			String param = request.getServletPath().substring(1) + "___"
					+ request.getQueryString();
			response.sendRedirect("/?page=" + param);
		} else {
			// empid = Long.parseLong(employeeId); 
	}
	
	
		String loginId=request.getParameter("loginId");
		
		
	%>

	<%
	
	%>
<h3 align="center"> Register in ATOm</h3>
<%
		String message = "";

		try {
			message = session.getAttribute("status").toString();

		} catch (Exception ex) {
			message = "";
		}
		session.setAttribute("status", "");
		if (message != null && !message.equals("")) {
	%>
	<div id="statusMessage">

		<%=message%>

	</div>
	<%
		}
	%>
	<form id="regietremployee" action="registerEmployeeSubmit" method="post">
		<table style="width: 40%;margin-left: auto;margin-right: auto;border: outset;">
		<tr><th colspan="3" align="center">To Use ATOm please Register 
		<input type="hidden" name="loginId" value="<%=loginId %>"/>
		</th></tr>
		<%try{ 	
		%>

		<tr><td align="right">Employee Code</td><td>&nbsp;</td><td><%=e.getPersonnelNo()%></td></tr>
		
		<tr><td align="right">Name </td><td>&nbsp;</td><td><%=e.getDisplayname() %></td></tr>
		<tr><td align="right">Reporting Officer</td><td>&nbsp;</td><td><%=e.getEmployeeSubscriptions2().get(0).getEmployee2().getDisplayname()%></td></tr>
		<tr><td align="right"><%=SettingsConstant.PROJECT_TERM%> Name</td><td>&nbsp;</td><td><%=e.getAtsconnect().getProject()%></td></tr>		
		<tr><td align="right"></td><td>&nbsp;</td><td><% %></td></tr>
		<tr><td align="right"></td><td>&nbsp;</td><td><% %></td></tr>
		<tr><td align="right"></td><td>&nbsp;</td><td><% %></td></tr>
		<tr><td align="right"></td><td>&nbsp;</td><td><% %></td></tr>
		<tr><td align="right"></td><td>&nbsp;</td><td><% %></td></tr>
		<%}catch(Exception ex) {System.out.println("Error"+e);}%>
		<tr><td></td><td>&nbsp;</td><td>
		<%if(empDtoInHeader.getRegisterStatus()!=null && empDtoInHeader.getRegisterStatus().equalsIgnoreCase("a")) {%>
		<input type="button" value="Go Home" name="home" class="formbutton" onclick="goHome()"/>
		<%} else {%>
		<input type="submit" value="Register" name="register" class="formbutton"/>
		<%} %>		
		</td></tr>
		</table>
		</form>
</body>
</html>