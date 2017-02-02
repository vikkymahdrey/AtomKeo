<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.dto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Trip Sheet</title>
<style>
.hd {
	background-color: #F4F0F5;
}
</style>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript">
	function saveTrip() {

		if (confirm("Are you sure you want to save this tripsheet?") == true) {
			var tripDate = document.getElementById("tripDate").value;
			var tripTime = document.getElementById("tripTime").value;
			var tripMode = document.getElementById("tripMode").value;
			var siteId = "1";
			window.location = "saveTripSheet?siteId=" + siteId + "&tripDate="
					+ tripDate + "&tripTime=" + tripTime + "&tripMode="
					+ tripMode + "";
		}
	}
	function printPage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = "1";
		window.location = "printTrips?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "";
	}
	function printPagenew() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = "1";
		window.location = "print_tripsnew.jsp?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "";
	}
	function printPageUnsaved() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = "1";
		window.location = "printTrips?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "&notSaved=something";
	}
	function printBoardPage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId ="1";
		window.location = "assignVendor?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "";
	}
	function comparePage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = document.getElementById("siteId").value;
		window.location = "compareTrips?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "";
	}
	function saveSelected() {
		var total = 0;
		var tripIds = new Array();
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = document.getElementById("siteId").value;
		
			 $("input[name=tripidcheck]:checked").each(function () {
				 tripIds.push($(this).val());
				 total = total + 1;
				 
				 
			 });
			 if (total < 1) {
				 alert("No trips Are Selected")
			 }
			 else
				 {
				 if (confirm("Are you sure you want to save selected trips?")) {
			 window.location = "saveTrip?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode="
				+ tripMode + "&tripids="+tripIds;
				 }
		}		
					
	}
	function exportPage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = "1";
		var newwindow = window.open("export2PDF?siteId=" + siteId
				+ "&tripDate=" + tripDate + "&tripTime=" + tripTime
				+ "&tripMode=" + tripMode + "", 'name', 'location=no');
		if (window.focus) {
			newwindow.focus()
		}
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
	function alterEscort(relatedId)
	{
		var Status=document.getElementById("escortchbutton"+relatedId).value;
		var chstatus="NO";
		if(Status=="Add Escort")
			chstatus="YES";
		
			try {
				var xmlhttp;
				if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var i = xmlhttp.responseText;
						if(i>0)
							{
							alert("Escort Status Changed Succesfully!");
							document.getElementById("escortstatus"+relatedId).innerHTML=chstatus;
							if(Status=="Cancel Escort"){
								document.getElementById("escortchbutton"+relatedId).value="Add Escort";
							}else{
								document.getElementById("escortchbutton"+relatedId).value="Cancel Escort";
							}}
						else
							{
							alert("Failed to Change Escort Status");
							}
					}
				}
				xmlhttp.open("POST", "EscortStatusUpdate?TripId="
						+ relatedId + "&Status=" + Status, true);
				xmlhttp.send();
			} catch (e) {

				alert(e);
			}
	}
</script>
</head>
<body>
	
	<%		List<TripDetail> tripSheetList = (List<TripDetail>)request.getAttribute("tripSheetList");
			List<TripDetail> tripSheetSaved = (List<TripDetail>)request.getAttribute("tripSheetSaved");	
			
					
			String tripDate = request.getParameter("tripDate");
			String tripMode = "" + request.getParameter("log");
			String tripTime = "" + request.getParameter("tripTime");
					
			boolean flag = false;
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

	<div id="body">
		<div class="content">
			<hr />
			<input type="hidden" name="tripDate" id="tripDate"
				value="<%=tripDate%>" />  <input type="hidden"
				name="tripMode" id="tripMode" value="<%=tripMode%>" /> <input
				type="hidden" name="tripTime" id="tripTime" value="<%=tripTime%>" />
			<%
				if (tripSheetList != null && tripSheetList.size() > 0) {
					flag = true;
			%>
			<br /> <input style="margin-left: 63%" class="formbutton"
				type="button" value="Back" onclick="javascript:history.go(-1);" />

			<form action="tripSheetModify" method="POST">
				<h3>Trip Sheet</h3>
				<hr />
				<p id="top"></p>
				<a href="#top" style="position: fixed;">Top</a> &nbsp;
				<input type="hidden" name="tripDate" value="<%=tripDate%>" />  <input
					type="hidden" name="tripMode" value="<%=tripMode%>" /> <input
					type="hidden" name="tripTime" value="<%=tripTime%>" />
				<div>	<input type="button" class="formbutton" style="float:left"
					value="Export As Excel" onclick="printPageUnsaved()" />  
				<%
					if (!(tripTime.equalsIgnoreCase("all"))) {
				%>
				<p>
					 <input type="submit" class="formbutton" style="float:left" value="Modify" /> 
					 <input	type="button" class="formbutton" value="Save" onclick="saveTrip()"  style="float:left" /> 
					 <!-- <input
						type="button" class="formbutton" value="Save Selected" onclick="saveSelected()" />  -->
						
				</p>
				

				<%
					}
				%>
				</div>
				<table border="1" width="80%">
					<%
						for (TripDetail td : tripSheetList) {
												%>

					<tr class="hd" >
					</tr>
					<tr class="hd">
						<!-- <td> &nbsp </td>
						<td>Vehicle type</td>
						<td colspan="3"></td> -->
						<td colspan="3">Trip ID</td>
						<td>
						<%-- <input type="checkbox" name="tripidcheck"
							id="tripidcheck" value="<%=td.getId()%>"
							 />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
							
						</td>
					</tr>
					<tr class="hd">
						<td>&nbsp</td>
						<td>Vehicle #</td>
						<td colspan="3">&nbsp;</td>
						<td colspan="3">Date</td>
						<td><%=OtherFunctions.changeDateFromatToddmmyyyy(OtherFunctions.changeDateFromat(td.getTripDate()))%></td>
					</tr>
					<tr class="hd">
						<td>&nbsp</td>
						<td>Escort Trip</td>
						<td colspan="3" id="escortstatus<%=td.getId() %>">YES</td>
						<td colspan="3">IN/OUT Time</td>
						<td><%=td.getTripLog() + "  "
							+ td.getTripTime()%></td>
					</tr>
					
					<tr class="hd">
						<td>&nbsp</td>
						<<!-- td>Escort Clock</td> -->
						<td colspan="3">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					
					<tr class="hd" >
						<td>#</td>
						<td>Name</td>
						<td>Contact</td>
						<td>Gender</td>
						<td>Address</td>
						
					</tr>
					<%
						int i = 1;
					if(td.getTripDetailsChilds()!=null){
								for (TripDetailsChild tdc : td.getTripDetailsChilds()) {
					%>
					<tr>
						<td><%=i%></td>
						<td><%=tdc.getEmployee().getEmployeeFirstName() +" "+ tdc.getEmployee().getEmployeeLastName()%>						
						</td>
						<td><%=tdc.getEmployee().getContactNumber1() %></td>
						<td><%=tdc.getEmployee().getGender()%></td>
						
						<td><%=tdc.getEmployee().getAddress()%></td>
							
					</tr>
					<%
						i++;
								}
					}
					%>

					<tr>

						<td colspan= 9 align="right">
						
						
						<%-- <input type="button"
							value="Audit Log" class="formbutton"
							onclick="showAuditLog(<%=td.getId()%>,'<%=AuditLogConstants.TRIPSHEET_MODULE%>');" /> --%></td>

					</tr>
					<%
						}
					%>

					</tbody>
				</table>
				<%
					if (!(tripTime.equalsIgnoreCase("all"))) {
				%>
				<p>
					<!-- <input type="submit" class="formbutton" value="Modify" /> --> <input
						type="button" class="formbutton" value="Save" onclick="saveTrip()" />
						
				</p>
				<%
					}
				%>
			</form>
			<%
				}
			%>
			<%
				if (tripSheetSaved != null && tripSheetSaved.size() > 0) {
					flag = true;
			%>
			<hr />
			<h3>Trip Sheet</h3>
			<br /> <!-- <input style="margin-left: 63%" class="formbutton"
				type="button" value="Back" onclick="javascript:history.go(-1);" /> -->


			<p>
				<!-- <input type="button" class="formbutton" value="Show Compare"
					onclick="comparePage()" /> &nbsp;&nbsp; <input type="button"
					class="formbutton" value="Export As PDF" onclick="exportPage()" />
				&nbsp;&nbsp;  --><input type="button" class="formbutton"
					value="Export As Excel" onclick="printPage()" /> <!-- &nbsp;&nbsp; <input
					type="button" class="formbutton" value="Assign Vendor"
					onclick="printBoardPage()" /> -->

			</p>

			<table border="1" width="80%">
				<%
					for (TripDetail td : tripSheetSaved) {
				%>

				<tr>
				</tr>
				<tr class="hd">
					<td>&nbsp</td>
					<td colspan="3">Trip ID</td>
					<td><%=td.getTripCode()%></td>
					</tr>
				<tr class="hd">
					<td>&nbsp</td>
					<td>Vehicle #</td>
					<%if(td.getVehicleBean()!=null){ %>
					<td><%=td.getVehicleBean().getRegNo() %></td>
					<%} %>
					
					<td>Driver</td>
					<%if(td.getDriver()!=null){%>
					<td><%=td.getDriver().getName()%></td>
					<%} %>
					<td colspan="3">Date</td>
					<td><%=OtherFunctions.changeDateFromatToddmmyyyy(OtherFunctions.changeDateFromat(td.getTripDate()))%></td>
				</tr>
				<tr class="hd">
					<td>&nbsp</td>
					<td>Escort Trip</td>
					<td >YES</td>
					<td>Driver Contact</td>
					<%if(td.getDriver()!=null){%>
					<td><%=td.getDriver().getContact()%></td>
					<%} %>
					<td colspan="3">IN/OUT Time</td>
					<td><%=td.getTripLog() + "  "
							+ td.getTripTime()%></td>
				</tr>
				
				
				<tr class="hd">
					<td>#</td>
					<td>Name</td>
					<td>Contact</td>
					<td>Gender</td>
					<td>Address</td>>
				</tr>
				<%
					int i = 1;
							for (TripDetailsChild tdc : td.getTripDetailsChilds()) {
				%>
				<tr>
					<td><%=i%></td>
					<td><%=tdc.getEmployee().getEmployeeFirstName()+""+tdc.getEmployee().getEmployeeLastName()%></td>
					<td><%=tdc.getEmployee().getContactNumber1() %></td>
					<td><%=tdc.getEmployee().getGender()%></td>
					
					<td><%=tdc.getEmployee().getAddress()%></td>
					
				</tr>
				<%
					i++;
							}
				%>
				<tr>
					<!-- <td height='60px' colspan="4">Security Seal</td>
					<td colspan="3" align="right">Admin Seal</td> -->
				</tr>
				<tr>

					<td colspan=9 align="right"><%-- <input type="button"
						value="Audit Log" class="formbutton" onclick="showAuditLog(<%=td.getId()%>,'<%=AuditLogConstants.TRIPSHEET_MODULE%>');" /> --%></td>

				</tr>

				<%
					}
				%>
				</tbody>
			</table>
			<br /> <!-- <input type="button" class="formbutton" value="Show Compare"
				onclick="comparePage()" /> &nbsp;&nbsp; <input type="button"
				class="formbutton" value="Export As PDF" onclick="exportPage()" />
			&nbsp;&nbsp;  --><input type="button" class="formbutton"
				value="Export As Excel" onclick="printPage()" /> &nbsp;&nbsp; <!-- <input type="button" class="formbutton"
				value="Export Excel New" onclick="printPagenew()" />&nbsp;&nbsp; <input
				type="button" class="formbutton" value="Assign Vendor"
				onclick="printBoardPage()" /> -->

			<%
				}
				if(!flag) {
					System.out.println("Flag IS True/false...");
			%>
			<p>No trip is to display</p>
			<%
				}
			%>
			

			<p>
				<input style="margin-left: 63%" class="formbutton" type="button"
					value="Back"
					onclick="javascript:window.location = 'tripGeneration';" />
			</p>
			<br /> <br />
			<hr />
			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
