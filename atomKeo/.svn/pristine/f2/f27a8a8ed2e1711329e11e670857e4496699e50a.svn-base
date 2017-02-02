<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shift Time</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/InputMask.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	maskLogTime();
	$("#logTable").tablesorter();	 
});
 

function maskLogTime() {
 
	$("#logTime").each(
			function() {
				try{ 
				  
				var name = $(this).attr("name");
				new InputMask([ fieldBuilder.inputNumbers(2, 2),
								fieldBuilder.literal(":"),
								fieldBuilder.inputNumbers(2, 2)], name);
				}catch(e){alert(e);}
			});

}

function deleteItem(id)
{
	var page="DeleteLogModify?id="+ id;
	if(confirm("Are you sure you want to delete this shift time ?"))
		{
		 document.location=page;
		 
		} 
	}
function enableStatus(id)
{
	
		var page="deleteLogModify?id="+ id+"&status=enable";
		if(confirm("Are you sure you want to enable this shift time ?"))
			{
			 document.location=page;		 
			} 				 			
}
function disableStatus(id)	
	{
	var page="deleteLogModify?id="+ id+"&status=disable";
	if(confirm("Are you sure you want to disable this shift time ?"))
		{
 		 document.location=page;		 
		}
	}
function validate()
{
		var flag = true;
		var logTime=document.getElementById("logTime");
		 
		if(trim(logTime.value)=="")
		{
			alert("Time can't be empty");
			flag=false;
		}
		else if (/^([0-1]?[0-9]|[2]?[0-3]):([0-5]?[0-9])$/.test(logTime.value)==false) {
			alert ("Invalid time format. Use 24 hour format (HH:mm)");
			flag=false;
		}
return flag;
}
function showAuditLog(relatedId,moduleName){
	var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	var size = "height=450,width=900,top=200,left=300," + params;
	var url="showAuditLog?relatedNodeId="+relatedId+"&moduleName="+moduleName;	
    newwindow = window.open(url, 'AuditLog', size);

	if (window.focus) {
		newwindow.focus();
	}
}
</script>
</head>
<body>
	<%
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
		List<Logtime> generalLogTimes=(List<Logtime>) request.getAttribute("generalLogtimes");
		System.out.println("IN JPS FILEEEEE");
	%>
	<div id="body">
		<div class="content">

			<h3>Add New Shift Time</h3>
			<form name="log_time" action="addLogTime" onsubmit="return validate()"
				method="POST">
				<table  width="40%">
					<tr>
						<td align="center" colspan="2"><b2></b2></td>
					</tr>
					<tr>
						<td>Time</td>
						<td><input type="text" name="logTime" id="logTime" /> hh:mm</td>
					</tr>
					<tr>
						<td>Log Type</td>
						<td><select name="logtype">
								<option value="IN">IN</option>
								<option value="OUT">OUT</option>
						</select></td>
					</tr>
		</tbody>
					<tr>
						<td></td>
						<td><input class="formbutton" type="submit" value="Add" /> <input
							type="button" class="formbutton"
							onclick="javascript:history.go(-1);" value="Back" /></td>
					</tr>

				</table>
			</form>
			<hr />
			<h3>Shift Time Details</h3>			
			<table id="logTable" class="tablesorter">
								<thead>
					<tr>
						<th>Shift Time</th>
						<th>IN/OUT</th>
						<th>Shift type</th>
						<th>Status</th>
						<th colspan="2" align="center">Action</th>
						<th align="center">Audit Log</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Logtime eachLogTIme : generalLogTimes) {
							System.out.println("IN JPS FILEEEEE2");
					%>
					<tr>
						<td><%=eachLogTIme.getLogtime()%></td>
						<td><%=eachLogTIme.getLogtype()%></td>
						<% String shiftType=(String)request.getAttribute("shiftType");%>
						<%if(shiftType == null || shiftType.equals("")) {%>
									<td>Shared</td>
						<%} else { %>
						            <td>Project</td>
						<%} %>
						<%
							if (eachLogTIme.getStatus().equals("active")) {
						%>
						<td>Enabled</td>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#"
							onclick="disableStatus(<%=eachLogTIme.getId()%>)">Disable</a> <%
								} else {
							%>
						<td>Disabled</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#"
							onclick="enableStatus(<%=eachLogTIme.getId()%>)">Enable</a> <%
								}
							%>
							</td>
							<td>
							
							 <a href="assignShiftToProcess?id=<%=eachLogTIme.getId()%>">Assign
								<%=SettingsConstant.PROJECT_TERM%>&nbsp;</a>
						<td align="center"><input type="button" class="formbutton"
							onclick="showAuditLog(<%=eachLogTIme.getId()%>,'<%=AuditLogConstants.SHIFTTIME_MODULE%>');"
							value="Audit Log" /></td>
					</tr>
					<%
						}
					%>

				</tbody>

			</table>


			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
