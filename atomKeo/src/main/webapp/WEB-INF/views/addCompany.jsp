<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%> 
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.entities.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="css/style.css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Add Company</title>
</head>
<body>
	<script type="text/javascript">
		function dsiplayModifyDiv()

		{
			document.getElementById("editDiv").style.display = 'block'
		}
		function gotoBranchPage()
		{
		  window.location='showBranches';	
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
	<div id='body'>
		<div class='content'>
			<%
			Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
			if (userLoggedIn == null) {
					String param = request.getServletPath().substring(1) + "___"
							+ request.getQueryString();
					response.sendRedirect("index.jsp?page=" + param);
				} else {
					// empid = Long.parseLong(employeeId); 
			%>
			<%@include file="Header.jsp"%> 
			<%
				 }
				
			
                       Company comp=(Company)request.getServletContext().getAttribute("company");
					if (comp != null) {
							%>
			<h3>Company Information</h3>
			<table>
				<tr>
					<td width='20%'>Name</td>
					<td width='13%'><%=comp.getName()%></td>
					<td></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><%=comp.getAddress()%></td>
					<td></td>
				</tr>
				<tr>
					<td>Web site</td>
					<td><%=comp.getWebsite()%></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"><b>Contact Person Information</b></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><%=comp.getContactpersonName()%></td>
					<td></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><%=comp.getContactPersonNumber()%></td>
					<td></td>
				</tr>
				<tr>
				 
					<td colspan="3"><input type="Button" name="add"
						class="formbutton" value="Modify" onclick="dsiplayModifyDiv()" />
						<input type="Button" name="branches" class="formbutton"
						value="Locations"
						onclick="gotoBranchPage()" />
						
						<input type="button" class="formbutton"
							 onclick="showAuditLog(<%=comp.getId()%>,'<%=AuditLogConstants.COMPANY_SETUP_MODULE%>');" 
							value="Audit Log" /></td>
                         
				</tr>
			</table>

 		<div id="editDiv" style="display: none;">
				<form name="UpdateForm" action="modifyCompany" method="POST">
					<table>
						<tr>
							<td width='20%'>Name</td>
							<td width='13%'><input type="text" name="name"
								value="<%=comp.getName()%>" /></td>
							<td><input type="hidden" name="id"
								value="<%=comp.getId()%>" /></td>
						</tr>
						<tr>
							<td width='10%'>Address</td>
							<td width='13%'><input type="text" name="address"
								value="<%=comp.getAddress()%>" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'>Web site</td>
							<td width='13%'><input type="text" name="website"
								value="<%=comp.getWebsite()%>" /></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><b>Contact Person Details</b></td>
						</tr>
						<tr>
							<td width='10%'>Name</td>
							<td width='13%'><input type="text" name="contactPersonName"
								value="<%=comp.getContactpersonName()%>" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'>contact</td>
							<td width='13%'><input type="text"
								name="contactPersonNumber"
								value="<%=comp.getContactPersonNumber()%>" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'></td>
							<td width='13%'><input type="Submit" name="add"
								class="formbutton" value="Update" /></td>
							<td></td>
						</tr>
					</table>

				</form>
			</div>
			<%
				} else {
					
			%>


			<h3>Add Company</h3>
			<hr>
			<div>
				<form name="CompanyForm" action="addingCompany">


					<table>
						<tr>
							<td width='20%'>Name</td>
							<td width='13%'><input type="text" name="name" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'>Address</td>
							<td width='13%'><input type="text" name="address" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'>Web site</td>
							<td width='13%'><input type="text" name="website" /></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><b>Contact Person Details</b></td>
						</tr>
						<tr>
							<td width='10%'>Name</td>
							<td width='13%'><input type="text" name="contactPersonName" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'>contact</td>
							<td width='13%'><input type="text"
								name="contactPersonNumber" /></td>
							<td></td>
						</tr>
						<tr>
							<td width='10%'></td>
							<td width='13%'><input type="Submit" name="add"
								class="formbutton" value="Add" /> <input type="Button"
								name="add" class="formbutton" value="Back"
								onclick="javascript:history.go(-1);" /></td>
							<td></td>
						</tr>
					</table>

				</form>
			</div>
			<%
				}
			%>
 			
 <%@include file="Footer.jsp"%> 
		</div>
	</div>


</body>
</html>