<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="css/style.css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<title>Add Company</title>
</head>
<body>
	<script type="text/javascript">
	
			$(document).ready(function(){
				$(".modify").click(setUpdateValues);
				$("#addNew").click(clearUpdateValues);
				$("select[name=night_shift_start_hour]").click(function(){
					concateNightShiftStartHour();
				});
				$("select[name=night_shift_start_minute]").click(function(){
					concateNightShiftStartMinute();
				});
				
				$("select[name=night_shift_end_hour]").click(function(){
					concateNightShiftEndHour();
				});
				$("select[name=night_shift_end_minute]").click(function(){
					concateNightShiftEndMinute();
				});
			});
		 
		function displaytxtDiv(serial)

		{
			document.getElementById("txtdiv"+serial).style.display = 'block';
			document.getElementById("btndiv"+serial).style.display = 'none';
		}
		function showSites(companyId,branchId)
		{
			window.location="site.jsp?companyId="+companyId+"&branchId="+branchId+"";
		}	
		
		  function showPopup(url) {

		         var branchId=document.getElementById("branchId").value;
		        var params="toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		         var size="height=124,width=300,top=200,left=300," + params;
		        if(url=="searchLandmark")
		            {
		                size="height=450,width=520,top=200,left=300," + params;
		            }
		       

		        newwindow=window.open(url+"?branchId="+branchId,'name',size);

		         if (window.focus) {newwindow.focus()}
		  }
		
		function setUpdateValues()
		{
			var s=$(this).attr("name").split("-");
			var index=s[1];
			
			$("input[name=branchId]").val($("input[name=branchId-" + index + "]").val());
			$("input[name=id]").val($("input[name=id-" + index + "]").val());
			$("input[name=siteName]").val($("input[name=siteName-" + index + "]").val());
			$("input[name=landMarkID]").val($("input[name=landmarkId-" + index + "]").val());
			$("input[name=landMark]").val($("input[name=landmark-" + index + "]").val());
			
			$("input[name=night_shift_start]").val($("input[name=night_shift_start-" + index + "]").val());
		
			$("input[name=night_shift_end]").val($("input[name=night_shift_end-" + index + "]").val());
			$("select[name=lady_security]").val($("input[name=lady_security-" + index + "]").val());
			 
			setHoursAndMinute();
			$("form[name=form1]").attr("action","updateSite");
			$("#actionButton").val("Update");
			
		}
		function concateNightShiftStartMinute()
		{
			var nightShiftStart=$("input[name=night_shift_start]").val().split(":");
			
			$("input[name=night_shift_start]").val(nightShiftStart[0]+":"+$("select[name=night_shift_start_minute]").val());
		}
		function concateNightShiftStartHour()
		{
			var nightShiftStart=$("input[name=night_shift_start]").val().split(":");
			
			$("input[name=night_shift_start]").val($("select[name=night_shift_start_hour]").val()+":"+nightShiftStart[1]);
		}
		
		function concateNightShiftEndMinute()
		{
			var nightShift=$("input[name=night_shift_end]").val().split(":");
			
			$("input[name=night_shift_end]").val(nightShift[0]+":"+$("select[name=night_shift_end_minute]").val());
		}
		function concateNightShiftEndHour()
		{
			var nightShift=$("input[name=night_shift_end]").val().split(":");
			
			$("input[name=night_shift_end]").val($("select[name=night_shift_end_hour]").val()+":"+nightShift[1]);
		}
		
		function setHoursAndMinute()
		{
			 
			var nightShiftStart=$("input[name=night_shift_start]").val().split(":");
			$("select[name=night_shift_start_hour]").val(nightShiftStart[0]);
			$("select[name=night_shift_start_minute]").val(nightShiftStart[1]);
			 
			var nightShiftEnd=$("input[name=night_shift_end]").val().split(":");
			$("select[name=night_shift_end_hour]").val(nightShiftEnd[0]);
			$("select[name=night_shift_end_minute]").val(nightShiftEnd[1]);
			
			 
			
		}
		
		function clearUpdateValues()
		{
			
			$("input[name=id]").val("");
			$("input[name=siteName]").val("");
			$("input[name=landMarkID]").val("");
			$("input[name=landMark]").val("");
			$("#actionButton").val("Add");
			$("form[name=form1]").attr("action","addSite");
		}
		
		function validateFields()
		{
			var flag=true;
			if($("input[name=siteName]").val().trim()=="")
				{
					alert("Site Name is empty");
					flag=false;
					
				}
			else if($("input[name=landMark]").val().trim()=="")
				{
				alert("Land mark is empty");
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
%>
	<div id='body'>
		<div class='content'>
			<%				
				    @SuppressWarnings("unchecked")
					List<Site> sList=(List<Site>)request.getAttribute("siteList");
				    Branch br=(Branch)request.getServletContext().getAttribute("branchById");
				    Company comp=(Company)request.getServletContext().getAttribute("companyById");
					if (sList!= null && sList.size() > 0) {
				%>
			<h3>
				Sites of
				 <%=br.getLocation()%></h3>

			<table>
				<%
					int serial = 1;
							if (sList != null && sList.size() > 0) {
				%>
				<tr>
					<th>Site</th>
					<th>Landmark</th>
					<th>Night shift start</th>
					<th>Night shift end</th>
					<th>Lady Security</th>
					<th>Actions</th>

				</tr>
				<%
					for (Site ss : sList) {
				%>

				<tr>

					<td width='13%'><%=ss.getSiteName()%> <input type="hidden"
						name="siteName-<%=ss.getId()%>"
						value="<%=ss.getSiteName()%>" /> <input type="hidden"
						name="id-<%=ss.getId()%>" value="<%=ss.getId()%>" /> <input
						type="hidden" name="branchId-<%=ss.getId()%>"
						value="<%=ss.getBranchBean().getId()%>" /></td>
					<td><%=ss.getLandmarkBean().getLandmark()%> <input type="hidden"
						name="landmark-<%=ss.getId()%>"
						value="<%=ss.getLandmarkBean().getLandmark()%>" /> <input type="hidden"
						name="landmarkId-<%=ss.getId()%>"
						value="<%=ss.getLandmarkBean().getId()%>" /></td>
					<td><%=ss.getNightShiftStart()%> <input type="hidden"
						name="night_shift_start-<%=ss.getId()%>"
						value="<%=ss.getNightShiftStart()%>" /></td>
					<td><%=ss.getNightShiftEnd()%> <input type="hidden"
						name="night_shift_end-<%=ss.getId()%>"
						value="<%=ss.getNightShiftEnd()%>" /></td>
					<%-- <td><%=siteDto.getHasLadySecurity()%> <input type="hidden"
						name="lady_security-<%=siteDto.getId()%>"
						value="<%=siteDto.getLady_securiy()%>" /></td> --%>
					<td id="btndiv<%=serial%>"><input type="Button"
						name="modify-<%=ss.getId()%>" class="modify formbutton"
						value="Modify" /> <input type="Button"
						name="addVendors-<%=ss.getId()%>"
						class="addVendors formbutton" value="Add Vendors"
						onclick="javascript: document.location='vendorSite?siteId=<%=ss.getId()%>'" />

						<input type="button" class="formbutton"
						onclick="showAuditLog(<%=ss.getId()%>,'<%=AuditLogConstants.SITE_MODULE%>');"
						value="Audit Log" /></td>
				</tr>

				<%
					serial++;
								}
							}
				%>
			</table>
			<%
				}
					%>





		 <h3></h3>
			<hr>
			<div>
				<form name="form1" action="addUpdateSite" method="post">
				<table>
						<tr>
							<th><input type="hidden" name="companyId"
								value="<%=comp.getId()%>" /> Site</th>
							<th>Landmark</th>
							<th>Night shift start</th>
							<th>Night shift end</th>
							<th>Lady Security</th>
							<th></th>
						</tr>
						<tr>

							<td><input type="hidden" name="id" /> <input type="hidden"
								name="branchId" id="branchId" value="<%=br.getId()%>" /> <input type="text"
								name="siteName" /></td>
							<td><input type="text" name="landMark" id="landmark"
								onclick="showPopup('searchLandmark') " value=""  readOnly />
								<input type="hidden" name="landMarkID" id="landMarkID" /> <input
								type="hidden" readonly name="area" id="area" /> <input
								type="hidden" readonly name="area" id="place" /></td>
							<td>
								<%
		
								%> <input type="hidden" name="night_shift_start"
								id="night_shift_start" value="00:00" /> <select
								name="night_shift_start_hour" id="night_shift_start_hour">
									<%
										for (int hour = 0; hour < 24; hour++) {
											String hourString = (hour < 10 ? "0" + hour : "" + hour);
									%>
									<option value="<%=hourString%>"><%=hourString%></option>
									<%
										}
									%>
							</select> <select name="night_shift_start_minute"
								id="night_shift_start_minute">
									<%
										for (int minute = 0; minute < 60; minute++) {
											String minuteString = (minute < 10 ? "0" + minute : "" + minute);
									%>
									<option value="<%=minuteString%>"><%=minuteString%></option>
									<%
										}
									%>
							</select>

							</td>
							<td>
								<%
								%> <input type="hidden" name="night_shift_end"
								id="night_shift_end" value="00:00" /> <select
								name="night_shift_end_hour" id="night_shift_end_hour">
									<%
										for (int hour = 0; hour < 24; hour++) {
											String hourString = (hour < 10 ? "0" + hour : "" + hour);
									%>
									<option value="<%=hourString%>"><%=hourString%></option>
									<%
										}
									%>
							</select> <select name="night_shift_end_minute"
								id="night_shift_end_minute">
									<%
										for (int minute = 0; minute < 60; minute++) {
											String minuteString = (minute < 10 ? "0" + minute : "" + minute);
									%>
									<option value="<%=minuteString%>"><%=minuteString%></option>
									<%
										}
									%>
							</select>


							</td>
							<td><select name="lady_security">
									<option value="1">Yes</option>
									<option value="0">No</option>
							</select></td>
							<td><input type="submit" value="Update" id="actionButton"
								class="formbutton" /> <input id="addNew" type="button"
								value="Add New" class="formbutton" /></td>
							<td></td>
						</tr>
					</table>
					<br /> <input type="Button" class="formbutton" value="Back"
						onclick="javascript:history.go(-1);" /> <br />
				</form>

			</div>
			<%@include file="Footer.jsp"%>
		</div>
	</div>


</body>
</html>
 