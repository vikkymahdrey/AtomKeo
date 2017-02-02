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
    <title>Change Password</title>
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
			<div class="login-input-border-wrap">
			<form action="changePasswordSubmit"  name="changePasswordSubmit" onsubmit="return confirmValidate();" method="post">	
				<div class="row">
					<div class="col-sm-10">
						
						<p align="center"><h3><b> <i>Change Password</i></b></h3></p>&nbsp;&nbsp;&nbsp;
						<p>Old Password</p><input type="password" id="oldpwd" name="oldpwd"  class="form-control login-fields username mar-top-20" />
						<p>Enter New Password</p><input type="password" id="pwd" name="pwd"  class="form-control login-fields password mar-top-10" />
						<p>Confirm New Password</p><input type="password" id="conpwd" name="conpwd"  class="form-control login-fields password mar-top-10" />
						
					</div>
				</div>
				
				<div class="row">
					
					<div class="text-center left mar-top-10">
						<input type="submit" value="Save"  class="btn btn-blue" onSubmit="return confirmValidate()"/> &nbsp;&nbsp;&nbsp;
						<input type="reset" value="Reset" class="btn btn-blue"/> 
					</div>
				</div>
				</form>
				</div>
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
	});	
</script>
<script type="text/javascript">
function confirmValidate() {
	var flag = true;
	var oldpwd=$("input[name=oldpwd]").val();
	var newpwd = $("input[name=pwd]").val();
	var conpwd = $("input[name=conpwd]").val();
	var ogpwd = $("input[name=ogpwd]").val();
	if(oldpwd=="")
		{
		document.getElementById("alertwarn").innerHTML ="Please Specify Old Password1!";
		$('#validationAlertModal').modal();
		flag=false;
		}
	else if(newpwd=="")
		{
		document.getElementById("alertwarn").innerHTML ="Please Enter New Password!";
		$('#validationAlertModal').modal();
		flag=false;
	
		}
	else if(conpwd=="")
		{
		document.getElementById("alertwarn").innerHTML ="Please confirm your Password!";
		$('#validationAlertModal').modal();
		flag=false;
		
		}
	else if(newpwd!= "" && newpwd == conpwd) {
	      if(newpwd.length < 6) {
	    	  document.getElementById("alertwarn").innerHTML ="New Password must contain at least six characters!";
	  		$('#validationAlertModal').modal();
	        flag=false;
	      }
	} 
	else if(newpwd!=conpwd)
		{
		flag=false;
		alert("New Password And Confirm Password Are Different!");
		
		}
	
	/*
	if(ogpwd!=oldpwd)
		{
		flag=false;
		alert("Old Password Entered Is Wrong!");
		}
	*/
	return flag;
		}
		</script>
</script>
  </body>
</html>