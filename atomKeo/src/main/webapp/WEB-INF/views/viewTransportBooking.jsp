<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bookings</title>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    
<link rel="stylesheet" href="css/font-awesome.min.css">
  
 <!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	
<link rel="stylesheet" type="text/css" href="css/easy-responsive-tabs.css " />
<link href="css/jquery.sidr.dark.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css" />
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom_siemens.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>


		<div class="main-page-container">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">

						<div class="breadcrumb-wrap">
							<a href="employeeHomeKeo"><img src="images/home.png" /></a> 
							<a href="employeeHomeKeo">My Information </a>
							<a href="transportRequest" >Book Transport</a>
							<a href="#" class="current">View Booking </a>
							
						</div>

						<div class="content-wrap">
							<%
								if (session.getAttribute("status") != null) {
							%>
							<div class="row mar-top-40">
								<div class="col-sm-12">
									<div class="alert alert-success"><%=session.getAttribute("status")%></div>
								</div>
							</div>
							<%
								}
							%>

							<div class="row">
								<div class="col-sm-8 page-heading mar-top-20">
									<i class="page-heading-icon"><img
										src="images/user_icon.png" /></i>
									<h5 class="text-blue text-semi-bold">My Bookings</h5>
								</div>
								<!-- <div class="col-sm-4 text-right  mar-top-20">
							<a href="#" class="btn btn-light-blue has-icon"><i><img src="images/edit_icon.png" /></i>Edit Information</a>
						</div>	 -->
							</div>


							<div class="recurring-manage-outer">

								<div class="table-responsive">
									<table id="example"
										class="collaptable table table-striped recurring-manage "
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th align="center" class="no-sort">Sl No</th>
												<th class="no-sort">Site</th>
												<th class="no-sort">From Date</th>
												<th class="no-sort">To Date</th>
												<th class="no-sort">Time</th>
												<th class="no-sort">Status</th>
												<th class="no-sort">Booking type</th>
												<th class="no-sort">Edit</th>
											</tr>
											<%
											int i=1;
											%>
										<c:forEach var="sch" items="${list}" >
										<tr>
											<td><%=i %></td>
											<td>${sch.employee1.siteBean.siteName}</td>
											<td> <fmt:formatDate type="date" value="${sch.fromDate}" /></td>
											<td><fmt:formatDate type="date" value="${sch.toDate}" /></td>
											<c:choose>
   											 <c:when test="${sch.logStatus=='IN'}">
   											<td>${sch.loginTime}</td>
   											</c:when>    
    										<c:otherwise>
    										<c:if test="${fn:length(sch.employeeScheduleAlters) eq 0}">
    										    <c:if test="${sch.fromDate.time eq sch.toDate.time}">															
    															<td>${sch.logoutTime}</td>
    															<td>Active</td>
    															<td>Single</td>
    										 </c:if>
    										 <c:if test="${sch.toDate.time gt sch.fromDate.time}">															
    															<td>${sch.logoutTime}</td>
    															<td>Active</td>
    															<td>Multiple</td>
    										 </c:if> 
    										 <%-- <td>${sch.logoutTime}</td>
    											<td>Active</td>
    											<td></td> --%>
    										 </c:if>
    										
    										  <c:if test="${fn:length(sch.employeeScheduleAlters) gt 1}">
    										 <td>${sch.logoutTime}</td>
    										 <td></td>
    										 <td>Multiple</td>
    										 </c:if>
    										 
    										 <c:if test="${fn:length(sch.employeeScheduleAlters) eq 1}">
    										 <c:if test="${sch.fromDate.time eq sch.toDate.time}">
    										 <c:choose>
    															<c:when test="${sch.employeeScheduleAlters[0].logoutTime=='Cancel'}">
    																<td>${sch.logoutTime}</td>
    																<td>Cancelled</td>
    																<td>Single</td>
    															</c:when>
    															<c:otherwise>
    															<td>${sch.employeeScheduleAlters[0].logoutTime}</td>
    															<td>Active</td>
    															<td>Single</td>
    															</c:otherwise>
    										</c:choose>
    										</c:if>
    										
    										 <c:if test="${sch.toDate.time gt sch.fromDate.time}">
    										 
    															<td>${sch.logoutTime}</td>
    															<td></td>
    															<td>Multiple</td>
    															
    										</c:if>
    										
    										
    										 </c:if>
    										 
    										    															
  										 </c:otherwise>
										</c:choose>
																					
											<td><div class="ops-actions">
                <a href="modifytripview?schid=${sch.id}" title="Edit"><i class="fa fa-pencil"></i></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <!--    <a href="" onClick="cancelbooking(${sch.id})" ><i ><img src="images/delete.png" /></i></a> --> 
                </div></td>
										</tr>
										<%i++; %>
										</c:forEach>
										</thead>
									</table>
								</div>
							</div>






						</div>
						<div class="row">
									<div class="col-sm-12 text-red text-12" style="color: red">
									*Note:
										<p>Status column shows single day active/ cancelled booking. To see cancellation under multiple booking, click on edit icon</p>
										<p>To edit time / cancel booking, click on edit icon</p>
										</div>
								</div>

						<div class="footer-wrap">
							<div class="row">
								<div class="col-sm-12 text-center">
									<p class="text-12">
										The information stored on this website is maintained in
										accordance with the organization's Data Privacy Policy. </span><br />Copyright
										© 2016 Siemens
								</div>
							</div>

						</div>


					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-2.2.0.js" ></script>
<script type="text/javascript">
function cancelbooking(id){
	 $.ajax({url: "canceltrip?schid="+id, success: function(result){
		 
	        if(result=="true"){
	        	alert("Booking Cancelled succesfully");
	        	location.reload(true);
	        	//window.location.reload();
	        }
	    }});
}
</script>
</body>
</html>