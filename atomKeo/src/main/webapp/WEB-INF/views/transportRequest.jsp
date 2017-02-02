<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.*"%>
<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduling For Transportation services</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>
<script src="js/dateValidation.js"></script>
<script type="text/javascript">
	function showPopup3(url) {
		try {

			var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
			var size = "height=124,width=300,top=200,left=300," + params;

			var site = document.getElementById("siteId").value;

			if (url == "LandMarkSearchInEmergency.jsp") {

				size = "height=450,width=600,top=200,left=300," + params;
				if (site.length < 1) {
					alert("Choose Site");
					return false;
				}
				url += "?siteId=" + site;
			}
			newwindow = window.open(url, 'name', size);

			if (window.focus) {
				newwindow.focus();
			}
		} catch (e) {
			alert(e);
		}
	}
</script>
<script type="text/javascript">
	var validationStatus = 0;
	$(document).ready(function() {
		$("#travelDate").datepick();
		$("#travelDate1").datepick();
		$("#shiftTime").change(function() {
			validateTime($("#shiftTime").val());
		});
		$("#bookingFor").change(function() {
			if ($("#bookingFor").val() == "other") {
				$("#empSet").show();
			}
			if ($("#bookingFor").val() == "self") {
				$("#empSet").hide();
			}
		});

	});

	function validate() {
	
		var fromDate = document.getElementById("travelDate").value;
		var toDate = document.getElementById("travelDate1").value;
		var time = document.getElementById("shifttime").value;
		var log=document.getElementById("pickdrop").value;
		var currentDate = new Date();
		var currentDatevar = currentDate.getDate() + "/"+ currentDate.getMonth() + "/" + currentDate.getFullYear();
		var daybeforeDatevar = currentDate.getDate()-1 + "/"+ currentDate.getMonth() + "/" + currentDate.getFullYear();
		var monthP =currentDate.getMonth() +1;
		var dateT = currentDate.getDate()+1
		if(dateT < 10){
			dateT = "0"+dateT;
		}
		if(monthP < 10){
			monthP = "0"+monthP;
		}
		var nxtDatevar = dateT+ "/"
		+ monthP+ "/" + currentDate.getFullYear();
		
		
		var currentTime = currentDate.getHours()*60+currentDate.getMinutes();
		
		var cutoffTime = currentTime+60;
		var startTimesplit = time.split(":");
		var startTimeMIn = parseInt(startTimesplit[0]*60 )+parseInt(startTimesplit[1]);
		if (fromDate.length < 1) {
			alert("Choose From Date");
			//  date.focus();
			return false;

		} else if (toDate.length < 1) {
			alert("Choose To Date");
			//  date.focus();
			return false;

		
		} else if (toDate<fromDate) {
			alert("FromDate should be less than ToDate");
			//  date.focus();
			return false;

		} else if (time == null || time == "") {
			alert("Choose Pick/Drop");
			//  date.focus();
			return false;
		}else if(fromDate < nxtDatevar){
			if( startTimeMIn < cutoffTime  ) {
			alert("!booking request is available only before 1 hour...");
			//  date.focus();
			return false;}
		} 
	        
	    else
	    	{
	    	return true;
	    	}
	   
	}
</script>
</head>
<body>


	<%	Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
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
			
		String travelDate = request.getParameter("travelDate");
		String travelDate1 = request.getParameter("travelDate1");
		String time = request.getParameter("shifttime");
		String log = request.getParameter("pickdrop");
	%>


	<br />
	<h3 style="margin-left: 100px">Cab Booking... </h3>
	<form id="frm1" method="POST" name="form1"
		action="transportBooking"
		id="transportBooking" onsubmit="return validate()">

		<table>
					
			<tr>

				<td width="20%" align="right">From Date</td>
				<td><input type="text" name="travelDate" id="travelDate"> 
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>

				<td width="20%" align="right">To Date</td>
				<td><input type="text" name="travelDate1" id="travelDate1" />
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="20%" align="right">Pick Up/Drop</td>
				<td><select name="pickdrop" id="pickdrop">
						<option>Select</option>		
						<option value="IN">pickUp</option> 
						  <option value="OUT">Drop</option>
				</select></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>; 
				<td width="20%" align="right"> Time</td>
				<td><select name="shifttime" id="shifttime">
						<%=OtherFunctions.FullTimeInIntervalOptions()%>
				</select><br>
				<label for="">Remark: transportation services availability cutoff timing should be 1 hour before.... </label></td>
			</tr>
			

			<tr>
				<td></td>
				<td><input type="submit" value="Book" class="formbutton" /> 


		</table>
	</form>

	<%@include file="Footer.jsp"%>


</body>
</html>