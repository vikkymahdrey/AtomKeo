<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.entities.AuditLog"%>
<%@page import="com.agiledge.atom.entities.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/agile.png" type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>AuditLog</title>
</head>
<body>
	<h3 align="center">Audit Log Information</h3>
	<hr />
	<%
	    @SuppressWarnings("unchecked")
		List<AuditLog> auditEntries = (List<AuditLog>)request.getServletContext().getAttribute("auditEntries");
	    String employeename = null;
	    if(auditEntries==null &&auditEntries.isEmpty())
        {%>
	<center>Sorry,No Records Found For This Request</center>
	<%
       }
        else
        {
        %>
	<table>
		<tr>
			<th align="center">Id</th>
			<th align="center">Related Id</th>
			<th align="center">Module Name</th>
			<th align="center">Date Changed</th>
			<th align="center">Changed By</th>
			<th align="center">Previous State</th>
			<th align="center">Current State</th>
			<th align="center">Action</th>
		</tr>
		<%for(AuditLog al:auditEntries)
		{ %>

			<tr>
			<td align="center"><%=al.getId()%></td>
			<td align="center"><%=al.getReferenceId()%></td>
			<td align="center"><%=al.getModuleName()%></td>
			<td align="center">
		     <%=al.getDateChanged() %>
			</td>
			<%
			Employee emp=al.getEmployee();
			
			if(emp.getId()==null)
			{
				employeename="System";
			}
			else
			{
				employeename=emp.getDisplayname();
			}
			%>
            <td align="center"><%=employeename %></td>
            <td align="center"><%=al.getPreviousState()%></td>
			<td align="center"><%=al.getCurrentState()%></td>
			<td align="center"><%=al.getAction()%></td>
			</tr>
<%}
        }
%>
	</table>
	<table>
		<td align="center"><input type="button" class="formbutton"
			value="Close" onclick="javascript:window.close();" /></td>
	</table>




</body>
</html> 