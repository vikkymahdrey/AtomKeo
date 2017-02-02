<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="css/style.css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Add Location</title>
</head>
<body>
	<script type="text/javascript">
		function displaytxtDiv(serial)
		{
			document.getElementById("txtdiv"+serial).style.display = 'block';
			document.getElementById("btndiv"+serial).style.display = 'none';
		}
		
		function showSites(companyId,branchId)
		{
			window.location="showSite?companyId="+companyId+"&branchId="+branchId+"";
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
		
		function validateForm(val)
		{
			try{
	if(val=="add")
		{
		var location=document.getElementById("branchLocation").value;			
		}
	else 
		{
		var location=document.getElementById("mbranchLocation"+val).value;
		}				
		if(location!=null&&location.length>1)
			{
			return true;
			}
		else
			{
			alert("Please enter Location");			
			}	
			}
			catch(e){alert(e);}
		return false;
		}
	</script>
	<div id='body'>
		<div class='content'>
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
			        @SuppressWarnings("unchecked")
					// List<Branch> branchList =(List<Branch>)request.getServletContext().getAttribute("branchesByCompId");
			        Company comp=(Company)request.getServletContext().getAttribute("company");
			        List<Branch> branchList = (List<Branch>)request.getServletContext().getAttribute("branchesByCompanyId");
			        String cmName=comp.getName();
			        int compid=comp.getId();
			      		if(branchList!=null && branchList.size()>0){
			%>
			<h3>
				<%=cmName%>
				Locations
			</h3>
			<table>
				<tr>
					<th>Id</th>
					<th width='20%'>Location</th>
					<th colspan="2"></th>
				</tr>
				<%
					int serial = 1;
						for (Branch branch : branchList) {
									%>
				<tr>
					<td><%=branch.getId()%></td>
					<td width='13%'><%=branch.getLocation()%></td>
					<td id="btndiv<%=serial%>" colspan="3"><input type="Button"
						name="showsites" class="formbutton" value="Show Sites"
						onclick="showSites('<%=compid%>','<%=branch.getId()%>')" />
						<input type="Button" name="modify" class="formbutton"
						value="Modify" onclick="displaytxtDiv('<%=serial%>')" /> <input
						type="button" class="formbutton"
						onclick="showAuditLog(<%=branch.getId()%>,'<%=AuditLogConstants.COMPANY_BRANCH_MODULE%>');"
						value="Audit Log" /></td>
					<td id="txtdiv<%=serial%>" colspan="3" style="display: none;">
						<form name="modifybranch" action="modifyBranch" method="post"
							onsubmit="return validateForm(<%=serial%>)">
							<input type="hidden" name="id" value="<%=branch.getId()%>" />
							<input type="hidden" name="companyId" value="<%=compid%>" />
							<input type="text" name="branchLocation"
								id="mbranchLocation<%=serial%>"
								value="<%=branch.getLocation()%>" /> <input type="submit"
								value="Update" class="formbutton" />
						</form>
					</td>
				</tr>

				<%
					serial++;
						}
				%>
			</table>
			<%
				}
					
			%>




			<h3>Add Location</h3>
			
			<hr>
			<div>
				<form name="AddBranchForm" action="addBranch" method="post"
					onsubmit="return validateForm('add')">


					<table>
						<tr>

							<td width='13%'><input type="text" name="branchLocation"
								id="branchLocation" /></td>
							<td><input type="hidden" name="companyId"
								value="<%=comp.getId()%>" /></td>
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
			
			<%@include file="Footer.jsp"%> 
		</div>
	</div>


</body>
</html>
