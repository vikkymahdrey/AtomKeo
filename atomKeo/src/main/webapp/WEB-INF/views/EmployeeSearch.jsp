<%@page import="com.agiledge.atom.constants.SettingsConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script type="text/javascript">
	function loadEmployeeDetails()
	{		
		if (confirm()) {

			try {

				var xmlhttp;
				if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						document.getElementById("EmployeeDetails").innerHTML = xmlhttp.responseText;
					}
				}
				xmlhttp.open("POST", "searchEmployees?employeeID="
						+ document.SearchForm.employeeID.value + "&firstName="
						+ document.SearchForm.firstName.value + "&lastName="
						+ document.SearchForm.lastName.value, true);
				xmlhttp.send();
			} catch (e) {
				alert(e.message);
			}

		} else {

			alert("Please type texts in search fields");
			//	alert("No records found !");

		}

	}

	function confirm() {
		var flag = true;

		if ($("input[name=employeeID]").val().trim() == ""
				&& $("input[name=firstName]").val().trim() == ""
				&& $("input[name=lastName]").val().trim() == "") {
			flag = false;

		}

		return flag;

	}

	function onsubmitfn() {			
		loadEmployeeDetails();
		return false;
	}
	function selectRow(id) {

		var name = document.getElementById("name-" + id).value;
		// var  id=document.getElementById("personnelNoCell-" + id).innerHTML;
		opener.document.getElementById("employeeID").value = id;
		opener.document.getElementById("employeeName").value = name;
		
		try{
			//alert(document.getElementById("address-" + id).value);
			
			opener.document.getElementById("addresstd").innerHTML=document.getElementById("address-" + id).value;
			setEmployeeDetails(id);
		}catch(e)
		{
			//alert(e);
		}


	}
	
	function setEmployeeDetails(id)
	{
		try {
			
	
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					
					var data = xmlhttp.responseText;
							
					var dataControls = data.split("|");				
					

					opener.document.getElementById("site").value=dataControls[0];
		    	opener.document.getElementById("supervisorName1").value=dataControls[1];
				opener.document.getElementById("supervisorID1").value=dataControls[2];				
			        opener.document.getElementById("supervisorName2").value=dataControls[3];
					opener.document.getElementById("supervisorID2").value=dataControls[4];										
					opener.document.getElementById("contactNo").value=dataControls[5];										
					this.close();
					
					//alert(data);
				}
			}
			var url="GetEmployeeDetails?employeeID="+id+"&isSub=true";
			xmlhttp.open("POST", url , true);
			xmlhttp.send();
		} catch (e) {
			alert(e.message);
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search <%=SettingsConstant.hrm %></title>
</head>
<body>
	<form name="SearchForm" action="" onsubmit="return onsubmitfn()">


		<table border="0">

			<tr>
				<td>Employee ID</td>
				<td><input type="text" name="employeeID" /></td>
				<td></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" /></td>
				<td></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" /></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" class="formbutton" name="Search"
					value="Search" /></td>
				<td></td>
			</tr>


		</table>
		<div id="EmployeeDetails"></div>

	</form>

</body>
</html>




