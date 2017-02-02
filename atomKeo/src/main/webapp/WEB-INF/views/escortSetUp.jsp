<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.nfl.com" prefix="disp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Add Vehicle</title>
<% request.setAttribute("contextPath", request.getContextPath()); %>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/displaytag.css" />
<script type="text/javascript" src="js/dispx.js"></script>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script src="js/JavaScriptUtil.js"></script>

<script type="text/javascript">
	function validate() {
		var name = $("input[id=name]").val();
//		var escortId = $("input[id=escortId]").val();
		var escortClock = $("input[id=escortClock]").val();
		//var email = $("input[id=email]").val();
		//var address = $("input[id=address]").val();
		var site = $("input[id=site]").val();
		var phone = $("input[id=phone]").val();
		 
		 
		
		 
		try {
			if (name == "") {
				alert("Name should not be blank !");
				return false;
			}
			if (escortClock == "") {
				alert("Escort Id should not be blank !");
				return false;
			} 
	
			if (phone == "") {
				alert("Contact No  should not be blank !");
				return false;
			}  
			var phoneRegx = /^\+?\d?\d?\d{10}$/;
			if (phoneRegx.test(phone) == false) {
				alert("Contact No  is invalid !");
				return false;
			}

			if (site== "") {
				alert("Site should not be blank !");
				return false;
			}
			
			/* if (address== "") {
				alert("Address should not be blank !");
				return false;
			}  
			if (email== "") {
				alert("Email address should not be blank !");
				return false;
			}  
			var emailRegx = /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
			
			if (emailRegx.test(email)==false) {
				alert("Email address is invalid !");
				return false;
			}
			
			

			
			  
			else {
				return true;
				 
			} */
			return false;
		} catch (e) {
			alert(e)
			return false;
		}
	}
	
	function fillFields(id) {
		 
		
		$("#name").val($("#name-"+id).val());
		
		$("#escortClock").val($("#escortClock-"+id).val());
		//$("#email").val($("#email-"+id).val());
		$("#phone").val($("#phone-"+id).val());
		//$("#address").val($("#address-"+id).val());
		
		$("#site").val($("#site-"+id).val());
		 
		$("#id").val($("#id-"+id).val());
		$("form[name='escortSetUpForm']").attr("action","updateEscort");
		$("input[name='submitbtn']").val("Update");
	}
	

	function clearFields() {
		 
		
		$("#name").val("");
		
		$("#id").val("");
		//$("#email").val("");
		$("#phone").val("");
		$("#site option:selected").removeAttr("selected");
	 
		//$("#address").val("");
		$("#id").val("");
		$("form[name='escortSetUpForm']").attr("action","addEscort");
		$("input[name='submitbtn']").val("Submit");
	}
	
	
	
</script>
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
		List<Escort> escortList= (List<Escort>)request.getAttribute("escorts");
		try {
	%>

	<div id="body">
		<div class="content">


			<h3>Security Setup</h3>
			<hr />
			<form name="escortSetUpForm" action="addEscort" method="post"
				onsubmit="return validate()">

				<table width="70%">
					<tr>
						<td align="center">Name</td>
						<td><input type="text" name="name" id="name" />
						<input type="hidden" name="id" id="id" />
						
						</td>
					</tr>
					<tr>
						<td align="center">Security Code</td>
						<td><input type="text" name="escortClock" id="escortClock" /></td>
					</tr>
					<!-- <tr>
						<td align="center">Address</td>
						<td><textarea    rows="5"   name="address" id="address" >
						</textarea></td>
					</tr> -->
					<tr>
						<td align="center">Contact No</td>
						<td><input type="text" maxlength="10" name="phone" id="phone" /></td>
					</tr>
					 
					<!-- <tr>
						<td align="center">Password</td>
						<td><input type="text" name="password" id="password" /></td>
					</tr>	 -->
					
					<!-- <tr>
						<td align="center">Email</td>
						<td><input type="text" name="email" id="email" /></td>
					</tr> -->
					<tr>
						<td>Site</td>
						<td>
 
															<%								
									
 
									List<Site> sites = (List<Site>)request.getAttribute("sites");
									 
								%> <select name="site" id="site" onchange="return searchForm();">
									<option value="">--select--</option>
									<%
									
										for (Site s : sites) {
  									%>
									<option value="<%=s.getId()%>">
										<%=s.getSiteName()%>
									</option>


									<%
										}
									%>
							</select>
						</td>
					</tr>					
			 		<tr>
						<td></td>
						<td><input type="submit" class="formbutton" name="submitbtn"
							value="Submit" /> <input type="button" class="formbutton"
							onclick="javascript:history.go(-1);" value="Back" />
							<input type="button" class="formbutton"
							onclick="clearFields()" value="Clear" />
							</td>
					</tr>
				</table>
			</form>
			<p>Driver</p>
 
			<disp:dipxTable id="row" list="<%=escortList%>" style="width:60%;" >
 
			<disp:dipxColumn group="1" title="Site" sortable="true" >
			<c:if test="${row.siteBean!=null}">
					${row.siteBean.siteName}
				</c:if>
			</disp:dipxColumn>
				<disp:dipxColumn  title="Name" sortable="true" property="name" >
				<input type="hidden" id="escortClock-${rowCount+1}" value="${row.escortClock}"/>
				<input type="hidden" id="id-${rowCount+1}" value="${row.id }"/>
				<input type="hidden" id="name-${rowCount+1}" value="${row.name}"/>
				<input type="hidden" id="phone-${rowCount+1}" value="${row.phone }"/>
				<%-- <input type="hidden" id="email-${rowCount+1}" value="${row.email }"/>
				<input type="hidden" id="address-${rowCount+1}" value="${row.address }"/> --%>
				<input type="hidden" id="site-${rowCount+1}" value="${row.siteBean.id}"/>
				
				 				 
				</disp:dipxColumn>
				<disp:dipxColumn  title="Escort#" sortable="true" property="escortClock" ></disp:dipxColumn>
				<%-- <disp:dipxColumn  title="Address" sortable="true" property="address" ></disp:dipxColumn> --%>
				<disp:dipxColumn  title="Contact No" sortable="true" property="phone" ></disp:dipxColumn>
				<%-- <disp:dipxColumn  title="Email" sortable="true" property="email" ></disp:dipxColumn> --%>
				<disp:dipxColumn  title="Action"   ><a onclick="fillFields('${rowCount+1}')">Edit</a></disp:dipxColumn>
			</disp:dipxTable>
			<%}catch(Exception e ) {
			  System.out.println("Error : " + e);	
			}
		%>
		 
			<%@include file="Footer.jsp" %>
			
		</div>
	</div>
</body>

</html>
