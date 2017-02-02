<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script src="js/dateValidation.js"></script>
<script type="text/javascript">

var flag = true;
var ajaxResult = false;
$(document).ready(function()
        {                                                                        
            $("#dateofjoin").datepick();
        });
    function checkExist(data) {
    	alert(data);
    	flag = false;
    }
    
        
function ajaxPost( urlParam, lookup, retFunction) {
	//alert(urlParam   );
	ajaxResult=false;
	$.ajax(
			{
				type:"POST",
				url: urlParam,
				data:lookup,
				async: false,
				 
				success: function (data) {
					ajaxResult=true;				  
					 retFunction( data);
				},
			 error:function (xhr, ajaxOptions, thrownError){
				 ajaxResult=true;
		            alert("in error" + xhr);
		        } 
			}		
		);
}

function showPopup(url) {

    var params="toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
    size="height=450,width=520,top=200,left=300,"+params;
    newwindow=window.open(url,'name',size);
    

     if (window.focus) {newwindow.focus();}
    }

        
function confirmValidate() {
		
		//ajaxPost("CheckLoginId"+param, "", checkExist);
	var personnelno=$("input[name=personnelno]").val();
	var fname = $("input[name=firstname]").val();
	var lname = $("input[name=lastname]").val();
	var dname = $("input[name=displayname]").val();
	var phno=$("input[name=phoneno]").val();
	var eid=$("input[name=email]").val();
	var doj=$("input[name=dateofjoin]").val();
	var address=$("textarea[name=address]").val();
	var loginid=$("input[name=loginid]").val();
	var project=$("#project").val();
	var supervisorName=$("#supervisorName1").val();	
	flag=true;
	// Regular expressions

var nameRegx = /^[A-Za-z][A-Za-z0-9 ]*$/;
var phoneRegx = /^\+?\d?\d?\d{10}$/;
var emailRegx = /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;

	   if(fname=="")
		{
		alert("Please Specify First Name");
		flag=false;
		} else if(nameRegx.test(fname)==false ) {
			
			alert("Invalid first Name");
			flag = false;
		}  else if(lname=="")
		{
			alert("Please Specify Last Name");
			flag=false;
			} else if(nameRegx.test(lname)==false ) {
				
				alert("Invalid last Name");
				flag = false;
			} else if(phno=="")
				{
					alert("Please Specify Phone Number");
					flag=false;
					}	
				else if(phoneRegx.test(phno)==false ) {
					
					alert("Invalid phone");
					flag = false;					
				}  else if(eid=="")
					{
						alert("Please Specify Email Id");
						flag=false;
						}
						else if(emailRegx.test(eid)==false ) {
							
							alert("Invalid email");
							flag = false;
		 
						} 
					else if(address=="")
							{
								alert("Please Specify Address");
								flag=false;
								}else if(loginid=="")
								{
									alert("Please Specify Login Id");
									flag=false;
									}
								
								
	/* var jsonData="";
	var param="?loginid="+loginid;
	try{
	 jsonData = $.ajax(
		jsonData =	 {
          url: "CheckLoginId"+param,
          dataType:"json",
          async: false
          }
			 ).responseText;
	 
	}catch (e)
	{
		 alert(" error " + e.message);
	}
	try{ 
		 
	var ans = jQuery.parseJSON(jsonData);
	}catch(e) {
	alert(e);	
	}
	 
	if(ans.result=="true")
		{
		flag=false;
		alert("Login Id Entered Already Exists.Please Specify Another Login Id!");
		} */
	
	return flag;
		
}
</script>
<title>Add External Employee</title>
</head>
<body>
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
	%>
	<div id="body">
		<div class="content">
<h3 align="center">Add Employee</h3>

<div>
<form name="addextemployee" method="post" action="addEmployee" onsubmit="return confirmValidate()">
<table align="center">

<tr>
<td align="left">First Name</td><td><input type="text" name="firstname" id="firstname"/></td>
</tr>
<tr>
<td align="left">Last Name</td><td><input type="text" name="lastname" id="lastname"/></td>
</tr>
<tr>
<td align="left">Login Id</td><td><input type="text" name="loginid" id="loginid"/></td>
</tr>
<tr>
<td align="left">Gender</td><td><select name="gender" id="gender"><option value="M">Male</option><option value="F">Female</option></select></td>
<tr>
<td align="left">Phone Number</td><td><input type="text" name="phoneno" id="phoneno"/></td>
<td></td><td></td>
</tr>
<tr>
<td align="left">Email Id</td><td><input type="text" name="email" id="email"/></td>
<td></td><td></td>
</tr>
<tr>
<td align="left">Address</td><td><textarea rows="4" cols="50" type="text" name="address" id="address"/></textarea></td>
<td></td><td></td>
</tr>
</table>
<table>
<td align="center">
<input type="submit" class="formbutton"
			value="Save"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="formbutton" value="Reset"/>
			</td>
</table>
</form>
<div>
<%@include file='Footer.jsp'%>
		</div>
		</div>
		</div>
</body>
</html>