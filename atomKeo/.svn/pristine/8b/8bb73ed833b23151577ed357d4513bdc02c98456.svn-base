<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employee Registeration</title>
	   <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom_siemens.css" rel="stylesheet">
   
  </head>
  <body class="login-bg">
  <% Employee emp=(Employee)(request.getSession().getAttribute("userLoggedIn"));%>
<div class="wrapper">
	
	<div class="login-triangle"></div>
	
	<div class="header-wrap-login">
	<div class="container-fluid">
		<div class="col-sm-6 col-xs-8">
			<img src="images/siemens_logo.png" />
		</div>
	</div>
	</div>
	
	<div class="container">
		<div class="login-input-wrap">
		<div class="row mar-top-20">
								<div class="col-sm-12">
									<div class="alert alert-danger san" hidden="hidden"
										style="color: red">
										<p id="errortag"></p>
									</div>
								</div>
							</div>
			<div >	
			<form action="addEmployee" onsubmit="return validate();" method="post">	
				<div class="row">
					<div class="col-sm-10">
											
						<p align="center"><h5><b> <i>Register for transport service</i></b></h5></p>
						<%  @SuppressWarnings("unchecked")
							 List<Branch> branchList=(List<Branch>)request.getAttribute("branchList");
						 %>
						 <select name="branchId" id="branchId" onchange="ajaxSiteByBranchId(this.value)" class="form-control login-fields username mar-top-20">
									<%if(branchList!=null && !(branchList.isEmpty())){
										for (Branch branch : branchList) {
									%>
									<option value="<%=branch.getId()%>"><%=branch.getLocation()%></option>
            						<%
										 }
									  }
									%> 
						</select> 
						
						<%  @SuppressWarnings("unchecked")
							 List<Site> sites=(List<Site>)request.getAttribute("sites");
						 %>
						 <select name="siteId" id="siteId" onchange="getTripTime()" class="form-control login-fields username mar-top-20">
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
						<input type="text" id="fname" name="fname" value="<%=emp.getEmployeeFirstName()%>" placeholder="First Name" class="form-control login-fields username mar-top-20" />
						<input type="text" id="lname" name="lname" value="<%=emp.getEmployeeLastName()%>" placeholder="Last Name"  class="form-control login-fields password mar-top-10" />
						Male&nbsp;&nbsp;<input type="radio" name="gender" id="gender"
							value="M"  />&nbsp;&nbsp;&nbsp;&nbsp;Female&nbsp;&nbsp;<input
							type="radio" name="gender" id="gender" value="F"  checked="checked"/>
							<% if(emp.getContactNumber1()==null || emp.getContactNumber1().equals("")){ %>
						<input type="text" id="mob" name="mob"  placeholder="Mobile No."  class="form-control login-fields password mar-top-10" />
						<%} else { %>
						<input type="text" id="mob" name="mob" value="<%=emp.getContactNumber1()%>" placeholder="Contact Number"  class="form-control login-fields password mar-top-10" />
						<%} %>
						<input type="text" id="personnelNo" value="<%=emp.getPersonnelNo()%>" name="personnelNo" disabled="disabled" placeholder="<%=emp.getPersonnelNo()%>"  class="form-control login-fields password mar-top-10" />
						<input type="text" id="email" name="email" value="<%=emp.getEmailAddress()%>" placeholder="Email Address"  class="form-control login-fields password mar-top-10" />
						<%if(emp.getAddress()!=null){%>
						<textarea class="addresstextarea form-control login-fields mar-top-10" name="addr" id="addr" value="<%=emp.getAddress()%>" placeholder="Address"></textarea>
						<%}else{ %>
						<textarea class="addresstextarea form-control login-fields mar-top-10" name="addr" id="addr"  placeholder="Address"></textarea>
						<%} %>
						<input name="termsAndConditions" type="checkbox" />
							Accept <a href="#"  class="map-popup-link">
								Terms And Conditions </a>
					</div>
				</div>
				
				<div class="row">
					
					<div class="text-center  mar-top-10" >
						<input type="submit" value="Next" class="btn btn-blue"/> &nbsp;
						<input type="reset" value="Reset" class="btn btn-blue"/> 
					</div>
				</div>
				</form>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" role="dialog"
			id="validationAlertModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">

					<div class="modal-body">
						<p class="alert alert-warning" id="alertwarn">
							<img src="images/alert_icon.png" />
						</p>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-blue" data-dismiss="modal">OK</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</div>
	
	<div class="login-footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-9 text-12 mar-top-10">
					<p>Note: Best viewed in Google Chrome 48.0 and above at resolution 1024 X 768</p>
					<p class="">© ATOm: Agiledge transport optimization manager (TM) All Rights Reserved: 2016 Agiledge Process Solutions Private Limited.</p>
				</div>
				<div class="col-sm-3 text-right mar-top-10">
					<img src="images/atom_logo.png" alt="" />
				</div>
			</div>
		</div>
	</div>
	<div class="popup-wrapper" style="height: 75%;">
		<div class="popup-content-wrap">
			<div class="popup-header">
				Terms and Conditions
				<a href="#" class="close-btn">X</a>
			</div>
			
			<div class="popup-inner-content-wrap">
				<div class="row">
					<div class="col-sm-6 mar-top-15">
						<h4>TERMS & CONDITIONS FOR TRANSPORT SERVICE</h4>
					</div>
					
				</div>
				
				<p>	
<ul>
<ol>  
 
<li>The employee is solely responsible for providing any personal information, boarding and de-boarding locations</li>
<li>The company is not liable for any indirect, incidental loss arising due to changes made by the employee with regards to the boarding and de-boarding locations</li>
</ol>
</ul>
</p>
				</div>
			</div>
			
		</div>
	
</div>

    jQuery (necessary for Bootstrap's JavaScript plugins)
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    Include all compiled plugins (below), or include individual files as needed
    <script src="js/bootstrap.min.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script>
	
	var docHeight = $(window).height();
	//alert(docHeight);
	var footerHeight = $(".login-footer").outerHeight();
	var headerHeight = $(".header-wrap-login").outerHeight() + 50;
	var contentheight = $(".login-bg .container").outerHeight();
	
	
	
	$(window).on('resize', function () {	
		$(".wrapper").css({
			"min-height" : $(window).height(),
			"padding-bottom" : (footerHeight + 30)
		});				
		var marginValue = ($(window).height() - footerHeight - headerHeight - contentheight)/2;		
		function loginMargin() {
			if (marginValue > 0) {
				$(".login-bg .container").css({
					"margin-top": marginValue
				});
			}
		}
		loginMargin();
	});			
	$( document ).ready(function() {		
		$(window).trigger('resize');
		$(".map-popup-link").click(function(e){
			e.preventDefault();
			$(".popup-wrapper").fadeIn("slow");
		//	initialize();
		});
		
		$(".popup-wrapper .close-btn").click(function(e){
			e.preventDefault();
			$(".popup-wrapper").fadeOut("slow")
		});
		
		/* 
		 $(".save-btn").click(function(e){
			e.preventDefault();
			$('#validationAlertModal').modal();
		});
		  */		
	});	
</script>
<script type="text/javascript">
	function validate(){
		var siteId=document.getElementById("siteId").value;
		var fname=document.getElementById("fname").value;
		var lname=document.getElementById("lname").value;
		var personnelno=document.getElementById("personnelNo").value;
		var email=document.getElementById("email").value;
		var mob=document.getElementById("mob").value;
		var addr=document.getElementById("addr").value;
		var atpos = email.lastIndexOf("@");
	    var dotpos = email.lastIndexOf(".");
	 
	    
	    
		if(siteId==0){
			
			document.getElementById("alertwarn").innerHTML ="Choose Site";
			$('#validationAlertModal').modal();
			return false;
		}else if(fname=="" || /^\s+$/.test(fname)){
			
			document.getElementById("alertwarn").innerHTML ="Please enter your first name";
			$('#validationAlertModal').modal();
			return false;
		}else if(lname=="" || /^\s+$/.test(lname)){
			
			document.getElementById("alertwarn").innerHTML = "Please enter your last name";
			$('#validationAlertModal').modal();
			return false;
		}else if(personnelno=="" || /^\s+$/.test(personnelno)){
			
			document.getElementById("alertwarn").innerHTML = "Please enter your employee code";
			$('#validationAlertModal').modal();
			return false;
		}else if(mob=="" ||!(/^[1-9]{1}[0-9]{9}$/.test(mob))){
		
			document.getElementById("alertwarn").innerHTML = "Please enter a valid mobile number";
			$('#validationAlertModal').modal();
			return false;
		}else if(atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length || /^\s+$/.test(email)){
			
			document.getElementById("alertwarn").innerHTML = "Please enter a valid email address";
			$('#validationAlertModal').modal();
			return false;
		}else if(addr=="" || /^\s+$/.test(addr)){
		
			document.getElementById("alertwarn").innerHTML ="Please enter your address";
			$('#validationAlertModal').modal();
			return false;
		}
		else if (!$("input[name=termsAndConditions]").is(":checked")) {
			
			document.getElementById("alertwarn").innerHTML = "Please accept terms and conditions";
			$('#validationAlertModal').modal();
			return false;
		}
		return true;
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
		    	
		    	document.getElementById("alertwarn").innerHTML = "Currently only Bangalore site is opertional";
				$('#validationAlertModal').modal();
				 document.getElementById("siteId").focus();
		    	}
		    	else{
		    		
		    		 document.getElementById("siteId").innerHTML=data;
		    		 //$('#validationAlertModal').modal();
					 document.getElementById("siteId").focus();
		    	}
		    }
		});
		
	}
	
	function showPopup(url) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		size = "height=450,width=520,top=200,left=300," + params;
		if (url == "termsAndConditions") {
			size = "height=450,width=520,top=200,left=300," + params;
		}
		
		newwindow = window.open(url, 'name', size);

		if (window.focus) {
			newwindow.focus();
		}
	}
</script>
  </body>
</html>