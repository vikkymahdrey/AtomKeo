<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page import="com.agiledge.atom.entities.TripDetail"%>
<%@page import="com.agiledge.atom.entities.VendorTripSheet"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Atom</title>
	
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
	
	
	<link rel="stylesheet" href="css/atom.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>
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
		<section class="popup">
	<h6 class="text-uppercase text-semi-bold">&nbsp;&nbsp; CAB DETAILS</h6>

 	<div class="vehicle-driver-details mar-top-15">
 
	 <table class="table table-bordered" width="100%">
	 
	 <%TripDetail td = (TripDetail) request.getAttribute("tripdetails"); %>
					
					<tbody>
						<tr>
							<td><p>
									<b>Trip Code: </b>
									<%=td.getTripCode() %>
								</p></td>
							<td><p>
									<b>Trip Date:</b>
									<%=td.getTripDate().toString().split(" ")[0]%></p></td>
									<td><p>
									<b>Trip Time:</b>
									<%=td.getTripTime()%></p></td>
									
						</tr>
						<tr>
						<td><p>
									<b>Vehicle No:</b>
									<%=td.getVehicleBean().getRegNo()%></p></td>
							<td><p>
									<b>Driver: </b>
									<%=td.getDriver().getName()%>
								</p></td>
							<td><p>
									<b>Driver Number: </b>
									<%=td.getDriver().getContact()%>
								</p></td>
						</tr>
						<tr>
						<td><p>
									<b>Employee  Name</b>
									</p></td>
							<td><p>
									<b> Employee contact </b>
									
								</p></td>
							<td><p >
								<b>In time     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    Out Time </b>
								
								</p></td>
						</tr>

						<%
						int i=1;
							List<VendorTripSheet>  list =td.getVendorTripSheets();
						     for(VendorTripSheet vt:  list){
						    	 
						    	 
						
						%>
						<tr>
							<td><p>
									<b> <%=i %>.</b>
									<%=vt.getEmployee().getDisplayname()%>
								</p></td>
							<td><p>
									
									<%=vt.getEmployee().getContactNumber1()%>
								</p></td>
							<td><p >
									<%=vt.getInTime()==null?" ":vt.getInTime()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=vt.getOutTime()==null?" ":vt.getInTime()%>
								</p></td>
							
						</tr>
						<% i++;} %>

					</tbody>
				</table>
  
    </div>
	</div>
	</div>
	
	
</body>
</html>