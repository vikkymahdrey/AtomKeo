<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Scheduling For Transportation services</title>
<!-- <script type="text/javascript" src="js/jquery-latest.js"></script> -->
<script  src="https://code.jquery.com/jquery-2.2.0.js"></script>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom_siemens.css" rel="stylesheet">
<link rel="icon" href="images/agile.png" type="image/x-icon" />
<script type="text/javascript" src="js/jquery-2.2.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<!--  <script type="text/javascript" src="js/jquery.datepick.js"></script>-->
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
  <script src="js/jquery-ui.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("#travelDate1").datepicker({ minDate: 0,dateFormat: 'dd/mm/yy' }); 
		$("#travelDate").datepicker({ minDate: 0,dateFormat: 'dd/mm/yy' }); 
		getTripTime();
		/* $("#tripTime").change(function() {
			validateTime($("#tripTime").val());
		}); */
	});
	
	 	
	function validate() {
		var siteId=document.getElementById("siteId").value;
		var fromDate = document.getElementById("travelDate").value;
		var toDate = document.getElementById("travelDate1").value;
		var time = document.getElementById("tripTimeId").value;
		var log=document.getElementById("tripMode").value;
		var currentDate = new Date();
		var currentDatevar = currentDate.getDate() + "/"+ currentDate.getMonth() + "/" + currentDate.getFullYear();
		var daybeforeDatevar = currentDate.getDate()-1 + "/"+ currentDate.getMonth() + "/" + currentDate.getFullYear();
		var monthP =currentDate.getMonth() +1;
		var dateT = currentDate.getDate()+1;
		var fmDt=fromDate.split("/");
		var toDt=toDate.split("/");
		var fm=fmDt[1];
		var td=toDt[1];
				
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
		
		if(siteId==0){
			document.getElementById("errortag").innerHTML = "Choose Site";
			 $('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("siteId").focus();
					return false;
			
		}else if (fromDate.length < 1) {
			document.getElementById("errortag").innerHTML = "Choose From Date";
	 $('.validation-required').removeClass("validation-required").addClass("form-control");
	 $('.san').show();
	 document.getElementById("travelDate").focus();
			return false;

		} else if (toDate.length < 1) {
			document.getElementById("errortag").innerHTML = "Choose To Date";
			 $('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("travelDate1").focus();
			//  date.focus();
			return false;

		
		} else if (toDate<fromDate || fm>td ) {
		
			document.getElementById("errortag").innerHTML = "To Date must be same as or after From Date. Please update to proceed.";
			 $('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("travelDate1").focus();
			//  date.focus();
			return false;

		} else if (log == null || log == "") {
			document.getElementById("errortag").innerHTML = "Choose Pick/Drop";
			 $('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("tripMode").focus();
			//  date.focus();
			return false;
		}else if (time == null || time == "ALL" || time=="") {
			document.getElementById("errortag").innerHTML = "Choose time";
			 $('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("tripTimeId").focus();
			//  date.focus();
			return false;
		}else if(fromDate < nxtDatevar){
		
			if( startTimeMIn < cutoffTime  ) {
				document.getElementById("errortag").innerHTML = "Booking is permitted 1 hour before the scheduled departure time";
				 $('.validation-required').removeClass("validation-required").addClass("form-control");
				 $('.san').show();
				 document.getElementById("tripTimeId").focus();
			//  date.focus();
			return false;
			}
		} 
		else if($('#branchId').val()!=1){//currently 1 for bangalore
			document.getElementById("errortag").innerHTML = "Currently, this transport facility is operational only at our Bangalore";
			$('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("branchId").focus();
			 return false;
		}
	    flag=ajaxresult(fromDate,toDate,time);
	    	
	    	return flag;
	    	}	    
	
	function ajaxresult(fromDate,toDate,time){
		var flag=false;
		$.ajax
		({
		    type: "POST",
		    url:  "getBookingValidation?fromDate=" + fromDate +"&toDate="+toDate+"&time="+time,
		    async: false, 
		    success: function(data) {
		    if(data==""){
		    	flag=true;
		    	}
		    	else{
		    		//alert(data);
		    		 document.getElementById("errortag").innerHTML = data;
		    		$('.validation-required').removeClass("validation-required").addClass("form-control");
					 $('.san').show(); 
		    		flag=false;
		    	}
		    }
		});
		return flag;
	}
	
	function ajaxSiteByBranchId(branchId){
				
		$.ajax
		({
		    type: "POST",
		    url:  "getSitesByBranch?branchId=" + branchId,
		    async: false, 
		    success: function(data) {
		    if(data==""){
		    	
		    	//alert("Currently only Bangalore site is opertional");
		    	document.getElementById("siteId").innerHTML='<select name="siteId" id="siteId"> <option value="0" >Select Site</option></select>';
		    	
		    	$('.validation-required').removeClass("validation-required").addClass("form-control");
				 $('.san').show();
				 document.getElementById("siteId").focus();
		    	}
		    	else{
		    		
		    		 document.getElementById("siteId").innerHTML=data;
		    		$('.validation-required').removeClass("validation-required").addClass("form-control");
					 $('.san').show(); 
					 document.getElementById("siteId").focus();
		    	}
		    }
		});
		if($('#branchId').val()!=1){//currently 1 for bangalore
			document.getElementById("errortag").innerHTML = "Currently, this transport facility is operational only at our Bangalore";
			$('.validation-required').removeClass("validation-required").addClass("form-control");
			 $('.san').show();
			 document.getElementById("branchId").focus();
		}
		else{
			 $('.san').hide();
		}
		
	}
	
	
	
	function getTripTime()
    {     
    	try{
    	
        var logtype=document.getElementById("tripMode").value;
        if(logtype=="ALL")
        	{
        	var tripTimeId=document.getElementById("tripTimeId");
        	tripTimeId.innerHTML='<select name="tripTime" id="tripTime"> <option value="ALL" >Select time</option></select>';
        	return;
        	}
        var site=document.getElementById("siteId").value;
        var url="getLogTime?logtype="+logtype+"&site="+site;                                    
        xmlHttp=GetXmlHttpObject()
        if (xmlHttp==null)
        {
            alert ("Browser does not support HTTP Request");
            return
        }                    
        xmlHttp.onreadystatechange=setLogTime	
        xmlHttp.open("POST",url,true)                
        xmlHttp.send(null)
    }catch(e){alert(e);}
    }
        
    function GetXmlHttpObject()
    {
        var xmlHttp=null;
        if (window.XMLHttpRequest) 
        {
            xmlHttp=new XMLHttpRequest();
        }                
        else if (window.ActiveXObject) 
        { 
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }

        return xmlHttp;
    }

    function setLogTime() 
    {                      
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        { 
            var returnText=xmlHttp.responseText;
            var tripTimeId=document.getElementById("tripTimeId");
            tripTimeId.innerHTML='<select name="tripTime" id="tripTime"> <option value="ALL" >Select time</option>'+returnText+'</select>';                                             
        }
    }

	
</script>
</head>
<body>
	<%
	Employee emp = (Employee) session.getAttribute("userLoggedIn");	
	if (emp == null) {
		String param = request.getServletPath().substring(1) + "___"
				+ request.getQueryString();
		response.sendRedirect("/?page=" + param);
	} else {
		// empid = Long.parseLong(employeeId); 
	}
	
	String site=request.getParameter("siteId");
	

	%>
	<div class="wrapper">
		<div class="header-wrap">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 text-right">
						<img src="images/user_iocn_header.png" />&nbsp;Welcome
						<%=emp.getEmployeeFirstName()+""+emp.getEmployeeLastName()%>
						&nbsp;&nbsp;&nbsp;<a href="logout"><img
							src="images/logout_icon_header.png" />&nbsp;Log Out</a>
					</div>
				</div>
			</div>
		</div>
		<div class="main-page-container">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="breadcrumb-wrap">
						<% if(emp.getRole().getId()==1){%>
							<a href="employeeHomeKeo"><img src="images/home.png" /></a>
							<a href="employeeHomeKeo">My Information </a> 
							<a href=""	class="current">Book Transport</a>
							<a href="employeeviewtrip" >View Booking </a>
							<a href="changeapp_pass" >Change app password</a>
						<%}else{ %>	
						<a href="employeeHome"><img src="images/home.png" /></a>
						<a
								href="employeeHome">My Information </a>
							<%} %>
							
						<a href="helpContact" >Help</a>
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
							
							<div class="section-heading">
								<div class="row">
									<div class="col-sm-12 bold">
										<Strong>Book Your Transport</Strong>
									</div>
								</div>
							</div>
							<div class="row mar-top-20">
								<div class="col-sm-12">
									<div class="alert alert-danger san" hidden="hidden"
										style="color: red">
										<p id="errortag"></p>
									</div>
								</div>
							</div>
							<form id="frm1" method="POST" name="form1"
								action="transportBooking" onsubmit="return validate()">
								<div class="push-15 login-input-wrap">
								
								<div class="row">
					<div class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">From City</div>
					<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
						 <%  @SuppressWarnings("unchecked")
							 List<Branch> branchList=(List<Branch>)request.getAttribute("branchList");
						 %>
						 <select name="branchId" id="branchId" onchange="ajaxSiteByBranchId(this.value)" style="width: 160px" class="form-control">
									<%if(branchList!=null && !(branchList.isEmpty())){
										for (Branch branch : branchList) {
									%>
									<option value="<%=branch.getId()%>"><%=branch.getLocation()%></option>
            						<%
										 }
									  }
									%> 
						</select> 
				</div>
						</div>	
								
								
								
								<div class="row">
					<div class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">From Office</div>
					<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
						 <%  @SuppressWarnings("unchecked")
							 List<Site> sites=(List<Site>)request.getAttribute("sites");
						 %>
						 <select name="siteId" id="siteId" onchange="getTripTime()" style="width: 160px" class="form-control">
						 <!-- <option value="0">Select Site</option> -->
									 <%if(sites!=null && !(sites.isEmpty())){
										for (Site s : sites) {
									%>
									<option value="<%=s.getId()%>">	<%=s.getSiteName()%></option>
            						<%
										 }
									  }
									%>
						</select> 
				</div>
						</div>	
							 
								
									<div class="row">
										<div
											class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">From
											Date</div>
										<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
											<input type="text" name="travelDate" id="travelDate"
												style="width: 160px" class="form-control" />
										</div>

									</div>
									<div class="row">
										<div
											class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">To
											Date</div>
										<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
											<input type="text" name="travelDate1" id="travelDate1"
												style="width: 160px" class="form-control" />
										</div>

									</div>


									<div class="row">
										<div
											class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">Service Type
											</div>
										<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
											<select name="tripMode" id="tripMode" style="width: 160px"
												class="form-control" onchange="getTripTime()">
												<option value="OUT">Drop</option>

											</select>
										</div>
									</div>
									<div class="row">
										<div
											class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey " style="text-align: left;">Time</div>
										<div class="col-md-6 col-sm-7 col-xs-6 mar-top-15">
											<select name="tripTime" id="tripTimeId" style="width: 160px"
												class="form-control">
												<option value="ALL">Select time</option>

											</select>
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-sm-12 text-red text-12" style="color: grey">
									Note:
										<p>The cut off time for making a transport booking is one (1) hour before the scheduled departure time.</p>
										<p>This transport facility is not available on Saturday and Sunday.</p>
										<p> Currently, this transport facility is operational only at our Bangalore offices</p>
										</div>
								</div>

								<div class="row text-center mar-btm-30">
									<div class="col-sm-12">
										<input type="submit" class="btn btn-blue save-btn"
											value="Book" />
									</div>
								</div>
							</form>
						</div>
						<div class="footer-wrap">
							<div class="row">
								<div class="col-sm-12 text-center">
									<p class="text-12">
										The information stored on this website is maintained in
										accordance with the organization's Data Privacy Policy. </span><br/>Copyright
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
	

</body>


</html>