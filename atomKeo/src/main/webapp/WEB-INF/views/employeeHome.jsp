<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.entities.Employee"%>
<%@page import="com.agiledge.atom.entities.Role"%>
<%@page import="com.agiledge.atom.entities.TripDetail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<title>Employee Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
        <%
    	Employee userLoggedIn = (Employee) request.getSession().getAttribute("userLoggedIn");
        String ob=(String)request.getSession().getAttribute("empDetails");
        Role userRole = (Role) request.getSession().getAttribute("empRole");
       /* atomKeo Testing 
       List<TripDetail> tripDetails = (List<TripDetail>) request.getAttribute("tripDetails"); */
        
        %>
		<%@include file="Header.jsp"%>
		<div id="body">
			<div id="content">
			<%@include file="Footer.jsp"%>
			</div>
		</div>
</body>
</html>
