<%--
    Document   : emp_subscription
    Created on : Aug 28, 2012, 12:51:01 PM
    Author     : 123
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.agiledge.atom.constants.SettingsConstant"%>
<%@page import="com.agiledge.atom.entities.Employee"%>
<%@page import="com.agiledge.atom.entities.Site"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
	errorPage="error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subscription</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/coin-slider.css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />

<link href="css/validate.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-latest.js"></script>

<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>

<script src="js/dateValidation.js"></script>

<!-- Beginning of compulsory code below -->
<link href="css/dropdown/dropdown.css" media="screen" rel="stylesheet"
	type="text/css" />

<link href="css/dropdown/dropdown.vertical.css" media="screen"
	rel="stylesheet" type="text/css" />
<link href="css/dropdown/themes/default/default.advanced.css"
	media="screen" rel="stylesheet" type="text/css" />


<!--[if lte IE 7]>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.dropdown.js"></script>
        <![endif]-->


<script>
	$(document).ready(function() {

		setActiveMenuItem();
		$("#sameToBelow").click(sameToBelowClicked);
	});

	// function to be same supervisor as spoc
	function sameToBelowClicked() {
		if ($("#sameToBelow").is(":checked")) {
			$("input[name=supervisorID2]").val(
					$("input[name=supervisorID1]").val());
			$("input[name=supervisorName2]").val(
					$("input[name=supervisorName1]").val());
		} else {
			$("input[name=supervisorID2]").val("");
			$("input[name=supervisorName2]").val("");
		}
	}

	function setActiveMenuItem() {
		var url = window.location.pathname;
		var filename = url.substring(url.lastIndexOf('/') + 1);
		//  $("li[class=active']").removeAttr("active");

		$("a[href='" + filename + "']").parent().attr("class", "active");
		$("a[href='" + filename + "']").parent().parent().parent('li').attr(
				"class", "active");

	}
</script>
<script type="text/javascript">
	function showPopup(url) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		size = "height=450,width=520,top=200,left=300," + params;
		if (url == "searchLandmark") {
			size = "height=450,width=600,top=200,left=300," + params;
		} else if (url == "supervisorSearch"
				|| url == "employeeSearch2") {
			size = "height=450,width=700,top=200,left=300," + params;
		} else if (url == "termsAndConditions.html") {
			size = "height=450,width=520,top=200,left=300," + params;
		}
		var site=document.getElementById("site").value;
		if (url == "searchLandmark") {
			if(site.length<1)
			{
			alert("Choose Site");
			return false;
			}
			url+="?site="+site;
		}
		
		newwindow = window.open(url, 'name', size);

		if (window.focus) {
			newwindow.focus();
		}
	}
</script>
<script type="text/javascript">
	$(function() {
		$('#fromDate').datepick();

	});
</script>
<script type="text/javascript">
	//  validate to prompt user about the payment
	function confirmValidate() {
		var flag = true;
		var currentDate = new Date();
		var currentDatevar = currentDate.getDate() + "/"
				+ currentDate.getMonth() + "/" + currentDate.getFullYear();
		var fromDate = $("input[name=fromDate]").val();
		var site = $("select[name=site]").val();
		if(fromDate=="")
			{
			flag=false;
			alert("Please Specify Effective Date!");
			}
		else if (!$("input[name=termsAndConditions]").is(":checked")) {
			alert("Please accept terms and conditions");
			flag = false;
		} else if ($("input[name=employeeName]").val() == "") {
			alert("Please specify Employee");
			flag = false;
		} else if ($("input[name=supervisorName1]").val() == "") {
			alert("Please specify Reporting Officer");
			flag = false;
		} else if ($("input[name=supervisorName2]").val() == "") {
			alert("Please specify SPOC");
			flag = false;
		} else if ($("input[name=landMark]").val() == "") {
			alert("Please specify Landmark");
			flag = false;
		} else if (site == "") {
			alert("Please choose your site");
			flag = false;
		} else if (isNaN($("input[name=contactNo]").val())
				|| ($("input[name=contactNo]").val()).length != 10) {
			alert("Please specify 10 digit contact number");
			flag = false;
		}

		else if (CompareTwoDatesddmmyyyy(fromDate, currentDatevar)) {
			alert("Effective date must be after current date !");
			flag = false;
		}

		if (flag == true) {
			if (!confirm(" Please verify your subscription details before submitting\n Click OK to proceed Cancel to review")) {
				flag = false;
			}
		}

		return flag;

	}
</script>

<script type="text/javascript">
	function displayaddress() {
		document.getElementById("addresstd").innerHTML = document
				.getElementById("address").innerHTML;
	}
</script>
</head>
<body>


	<%
	Employee userLoggedIn = (Employee) request.getSession().getAttribute("userLoggedIn");	%>
	<%@include file="Header.jsp"%>
	<div id="body">
		<div class="content">
			<form action="subscribe" name="subscriptionForm"
				onsubmit="return confirmValidate();" method="post">
				<h3>Add Subscription</h3>
				<table align="center">
					<tr>
						<td align="right">Employee</td>
						<td><input type="hidden" name="employeeID" id="employeeID" />
							<input type="text" readonly name="employeeName" id="employeeName"
							onclick="showPopup('employeeSearch')" /> <label
							for="employeeID" class="requiredLabel">*</label> <input
							class="formbutton" type="button" value="..."
							onclick="showPopup('employeeSearch')" /></td>
					</tr>
					<tr>
						<td align="right">Effective Date</td>
						<td><input type="text" name="fromDate" id="fromDate"
							class="required" readOnly
							class="{showOtherMonths: true, firstDay: 1, dateFormat: 'yyyy-mm-dd', minDate: new Date(2012,  1, 25)}" />
							<label for="fromDate" class="requiredLabel">*</label></td>
					</tr>
					<tr>
						<td align="right">Site</td>
						<td>
						 <select name="site" id="site">
								<option value="">---------- Select -----------</option>
                                <c:forEach items="${sites}" var="site">
								<option value="${site.id}">
									${site.siteName}
								</option>
								</c:forEach>
						</select> <label for="site" class="requiredLabel">*</label>
						</td>
					</tr>
					<tr>
						<td align="right"><%=SettingsConstant.hrm %></td>
					<td><input type="hidden" name="supervisorID1"
							id="supervisorID1" /> <input type="text" readonly
							name="supervisorName1" id="supervisorName1"
							onclick="showPopup('supervisorSearch')" /> <label
							for="supervisorID1" class="requiredLabel">#</label> <input
							class="formbutton" type="button" value="..."
							onclick="showPopup('supervisorSearch')" /></td>

					</tr>
					<tr>
						<td align="right">SPOC</td>
						<td><input type="hidden" name="supervisorID2"
							id="supervisorID2" /> <input type="text" readonly
							name="supervisorName2" id="supervisorName2"
							onclick="showPopup('spocSearch')" /> <label
							for="supervisorID2" class="requiredLabel">#</label> <input
							class="formbutton" type="button" value="..."
							onclick="showPopup('spocSearch')" /> <input
							type="checkbox" id="sameToBelow" />Same as Manager</td>
					</tr>
					<tr>
						<td colspan="2"><input style="margin-left: 36%;"
							type="button" value="Select APL" class="formbutton"
							onclick="showPopup('searchLandmark') " /></td>
					</tr>
					<tr>
						<td align="right">Area</td>
						<td><input type="text" value=""
							onclick="showPopup('searchLandmark') " readonly name="area"
							id="area" /> <input type="hidden" id="landMarkID"
							name="landMarkID" /> <label for="area" class="requiredLabel">*</label>
						</td>
					</tr>
					<tr>
						<td align="right">Place</td>
						<td><input type="text" value=""
							onclick="showPopup('searchLandmark') " readonly name="place"
							id="place" /> <label for="place" class="requiredLabel">*</label>
						</td>
					</tr>
					<tr>
						<td align="right">Landmark</td>
						<td><input type="text"
							onclick="showPopup('searchLandmark') " readonly
							name="landMark" id="landmark" /> <label for="landMark"
							class="requiredLabel">*</label></td>
					</tr>
					<tr>
						<td align="right">Contact No</td>
						<td><input type="text" name="contactNo" id="contactNo"
							maxlength="10" /> <label for="contactNo" class="requiredLabel">*</label>
						</td>
					</tr>
					<tr>
						<td align="right" rowspan="4" valign="middle">Address</td>
						<td rowspan="4" id='addresstd'></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td></td>
						<td><input name="termsAndConditions" type="checkbox" />
							Accept <a href="#" onclick="showPopup('termsAndConditions.html')">
								Terms And Conditions </a></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="formbutton" type="submit" name="subscribe"
							value="Submit" /> <input type="button" class="formbutton"
							onclick="javascript:history.go(-1);" value="Back" /></td>
					</tr>
					<tr>
						<td colspan="2"><label style="margin-left: 30%" for="site"
							class="requiredLabel">*</label> Indicates mandatory field</td>
					</tr>
					<tr>
						<td colspan="2"><label style="margin-left: 30%" for="site"
							class="requiredLabel">#</label> <%=SettingsConstant.hrm %> is your transport
							approving authority.
					</tr>
					<tr>
						<td colspan="2"><label style="margin-left: 30%" for="site"
							class="requiredLabel">#</label> Spoc is your transport scheduling
							authority.
					</tr>
				</table>
			</form>



			<%@include file="Footer.jsp"%>
		</div>
	</div>

</body>
</html>
