<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page import="com.agiledge.atom.entities.TripDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="com.agiledge.atom.entities.VendorTripSheet" %>
<%@page import="com.agiledge.atom.entities.Employee" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom_siemens.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trace Vehicle</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>


</head>
<body>
<script type="text/javascript">
function Validate() {
	var flag =true;
	var cause=$("#causeOfalarm").val()  
	var action=$("#actionDesc").val()
	if(cause=="")
	{
		alert("please choose cause of alarm");
		
	flag=false;
	}
	else if(action=="" ||action==" " )
	{
		alert("please enter the primary action details");
		flag=false;
	}
	
	
	
	return flag;
	
	
}
</script>
<div class="wrapper">
	<div class="header-wrap">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 text-right">
				</div>
			</div>
		</div>
	</div>

	<%
	
		String tripId = "";
		tripId = request.getParameter("tripId");
	%>


	<hr />

	<div id="body">
		<div class="content">
			
			

	
	<br/>

			<form name="panicAction" action="PanicAlarmStop" method="post" onsubmit="return Validate()">
				<input type="hidden" value="<%=tripId%>" id="tripId" name="tripId">
				<%
					TripDetail dto = (TripDetail) request.getAttribute("tripdetails");
					try {
				%>

				<table align="center">
				
				<tr>
						<td colspan="4" align="center"><h6 ><b>Panic Trip Details</b></h6></td>
					</tr>
				
						
					<tr>
						<td colspan="4" align="center">TripDate: <%=dto.getTripDate().toString().split(" ")[0]%></td>
					</tr>
					<tr>
						<td colspan="4" align="center">TripTime: <%=dto.getTripTime()%></td>
					</tr>	
						
						<tr>
						<td colspan="4" align="center">Vehicle: <%=dto.getVehicleBean().getRegNo()%></td>
					</tr>
					<tr>
						<td colspan="4" align="center"><b>Driver Details</b></td>
					</tr>
					<tr>
						<td colspan="4" align="center">Name: <%=dto.getDriver().getName()%></td>
					</tr>
					<tr>
						<td colspan="4" align="center">Contact: <%=dto.getDriver().getContact()%></td>

					</tr>
						
					<tr>
						<td colspan="4" align="center"><b>Employees Details</b></td>
					</tr>
					<tr>
					
						
				     	<td><b>Name&nbsp;&nbsp;</b></td>
						<td><b>Code&nbsp;&nbsp;</b></td>
						<td><b>Contact&nbsp;&nbsp;</b></td>
						<td><b>In Time&nbsp;&nbsp;</b></td>
						<td><b>Out Time&nbsp;&nbsp;</b></td>
					</tr>

					<%List<VendorTripSheet>  list =dto.getVendorTripSheets();
				   
					for(VendorTripSheet vt:  list){
					
					
					%>
					<tr>
						<td><%=vt.getEmployee().getDisplayname() %>&nbsp;&nbsp;</td>
						<td><%=vt.getEmployee().getPersonnelNo() %>&nbsp;&nbsp;</td>
						<td><%=vt.getEmployee().getContactNumber1() %>&nbsp;&nbsp;</td>
						<td><%=vt.getInTime()==null?" ":vt.getInTime()%>&nbsp;&nbsp;</td>
						<td><%=vt.getOutTime()==null?" ":vt.getOutTime()%>&nbsp;&nbsp;</td>
					</tr>
					<% } %>	
				</table>
				<br/>
				<table align="center">



						<tr>
							<td>Cause of Alarm <select name="causeOfalarm"
								id="causeOfalarm"><option  value="">Select Alarm cause</option>
									<option value="False Alarm">False Alarm</option>
									<option value="Accident">Accident</option>
									<option value="Rash Driving">Rash Driving</option>
									<option value="Driver Issue">Driver Issue</option>
									<option value="Break Down">Break Down</option></select></td>
						</tr>

						<tr>
							<td>Primary Action <textarea name="actionDesc"
									id="actionDesc" rows="1" cols="50"></textarea></td>
						</tr>
						<tr>

							<td align="center"><input type="submit" value="Submit"
								class="formbutton">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td></td>
						</tr>



					</table>
			</form>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
			<%
				} catch (Exception e) {
					System.out.println("Error" + e);
				}
			%>

		</div>
	</div>
	</div>
	
	
</body>
</html>