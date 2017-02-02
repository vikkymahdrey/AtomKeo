<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Time Slots</title>
</head>
<body>
	<%
	System.out.println("Hii Jspppp");
	Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
if (userLoggedIn == null) {
		String param = request.getServletPath().substring(1) + "___"
				+ request.getQueryString();
		response.sendRedirect("/?page=" + param);
	} else {
		// empid = Long.parseLong(employeeId); 
%>
<%@include file="Header.jsp"%> 
<%
	 }
	        
    %>
	<br />
	<div class="content">
		<div class="content_resize">
			<%
			
      List<Timesloat> timeSloats=(List<Timesloat>)request.getAttribute("timeslots");

%>
			<h3>Time Slot</h3>
			<hr />
			<div style="float:left; margin-left: 20px; padding: 5px;  "><button class="formbutton" onclick="location.href='timeSloat.jsp'"  >Modify Slots</button></div>
			<table>
				<thead>

					<tr>
						<th align="center">Traffic</th>
						<th align="center">Start Time</th>
						<th align="center">End Time</th>
						<th align="center">Speed</th>
					</tr>
				</thead>
				<%
for(Timesloat ts : timeSloats)
{
%>

				<tr>
					<td align="center"><%=ts.getTraffic()%></td>

					<td align="center"><%=ts.getStartTime()%></td>
					<td align="center"><%=ts.getEndTime()%></td>
					<td align="center"><%=ts.getSpeedpkm()%></td>					
				</tr>
				<%
}        %>
			</table>

		</div>
	</div>
</body>
</html>
