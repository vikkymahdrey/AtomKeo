<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />

<title>JSP Page</title>
<script type="text/javascript">
	function getVehicles(chosenSite) {
		if (chosenSite.length > 0) {
			try {
				var xmlhttp;
				if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var fullvehicle = xmlhttp.responseText;
						fullvehicle = fullvehicle.split("$");
						document.getElementById("vehicleTypelistd").innerHTML = "<select name='vehicleType' id='vehicleType' multiple=''>"+fullvehicle[0]+"</select>";
						document.getElementById("vehicleTypeAdded").innerHTML = "<select name='chosenVehicleType' id='chosenVehicleType' multiple=''>"+fullvehicle[1]+"</select>";
					}
				}
				xmlhttp.open("POST", "getVehicleNotInSite?siteId=" + chosenSite, true);
				xmlhttp.send();
			} catch (e) {

				alert(e);
			}
		}

	}

	function formSubmit(form) {
		chosenVehicleType = document.getElementById("chosenVehicleType");
		var vehicleTypeLength = document.getElementById("chosenVehicleType").options.length;
		for ( var i = 0; i < vehicleTypeLength; i++) {
			chosenVehicleType.options[i].selected = true;
		}
		if (vehicleTypeLength < 1) {
			alert("No Vehicle Added")
		} else {
			form.submit();
		}
	}

	function listMoveRight() {

		var vehicleType = document.getElementById("vehicleType");
		var optionSelected = vehicleType.selectedIndex;
		var chosenVehicleType = document.getElementById("chosenVehicleType");
		var optionNew = document.createElement('option');
		optionNew.value = vehicleType.options[optionSelected].value;
		optionNew.text = vehicleType.options[optionSelected].innerHTML;
		try {
			chosenVehicleType.add(optionNew, null);
		} catch (e) {
			chosenVehicleType.add(optionNew);

		}
		vehicleType.remove(vehicleType.selectedIndex);

	}
	function listMoveLeft() {
		var chosenVehicleType = document.getElementById("chosenVehicleType");
		var optionSelected = chosenVehicleType.selectedIndex;
		var vehicleType = document.getElementById("vehicleType");
		var optionNew = document.createElement('option');
		optionNew.value = chosenVehicleType.options[optionSelected].value;
		optionNew.text = chosenVehicleType.options[optionSelected].innerHTML;
		try {
			vehicleType.add(optionNew, null);
		} catch (e) {
			vehicleType.add(optionNew);

		}
		chosenVehicleType.remove(chosenVehicleType.selectedIndex);
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
				/* List<VehicleType> vtList=(List<VehicleType>)request.getAttribute("vehicleTypes"); */
				List<Site> siteList = (List<Site>)request.getAttribute("sites");
			%>
			<br /> <br> <br>
			<h3>&nbsp;&nbsp;&nbsp;&nbsp;Choose Vehicle To Site</h3>

			<p></p>
			<div></div>


			<form name="sitevehicle" action="siteVehicle">
				<table border="0">
					<tr>
						<td width="10%">Choose Site</td>
						<td colspan="2"><select name="siteId" id="chosenSite"
							onchange="getVehicles(this.value)">      
								<option value="">Select</option>
								<%
									for (Site site : siteList) {
								%>
								<option value="<%=site.getId()%>"><%=site.getSiteName()%></option>
								<%
									}
								%>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="6">Type</td>
						<td width="10%" id="vehicleTypelistd"><select
							name="vehicleType" id="vehicleType" multiple="">
						</select></td>
						<td width="3%"><input type="button" name="right"
							value="&rArr;" onclick="listMoveRight()" /><br> <input
							type="button" name="left" value="&lArr;" onclick="listMoveLeft()" /><br></td>
						<td id="vehicleTypeAdded"><select multiple
							name="chosenVehicleType" id="chosenVehicleType" height="4"></select></td>
					</tr>
					<tr>
						<td><input type="button" class="formbutton"
							onclick="formSubmit(this.form)" value="Update" /></td>
					</tr>
				</table>
			</form>
			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
