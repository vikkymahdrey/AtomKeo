<%@page import="java.util.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="css/style.css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		$("#moveLeft").click(moveLeft);
		$("#moveRight").click(moveRight);
	});
	function moveLeft() {
		//$("#vendorInSite").add($("#vendorNotInSite option:selected").)

		if (($("#vendorsNotInSite option:selected").val() == undefined ? false
				: true)) {
			var text = $("#vendorsNotInSite option:selected").html();
			var value = $("#vendorsNotInSite option:selected").val();
			var s1 = $("#vendorsInSite ").html();
			s1 = s1 + " <option name='vendorInSite' value='" + value+ "'>"
					+ text + " </option>";
			$("#vendorsInSite ").html(s1);
			$("#vendorsNotInSite option:selected").remove();
		}

	}

	function moveRight() {
		if (($("#vendorsInSite option:selected").val() == undefined ? false
				: true)) {
			var text = $("#vendorsInSite option:selected").html();
			var value = $("#vendorsInSite option:selected").val();
			var s1 = $("#vendorsNotInSite ").html();
			s1 = s1 + " <option value='" + value+ "'>" + text + " </option>";
			$("#vendorsNotInSite ").html(s1);
			$("#vendorsInSite option:selected").remove();
		}
	}

	function validate() {
		flag = true;

		$("#vendorsInSite option").attr("multiselect", ":multiselect");
		$("#vendorsInSite option").attr("selected", ":selected");

		return flag;
	}
</script>
<title>Vendor Site</title>
</head>

<body>	

	<div id='body'>
		<div class='content'>
			<%
				long empid = 0;
				String employeeId = OtherFunctions.checkUser(session);
			%>
<%@include file="Header.jsp"%>			
			<%
				Site site=(Site)request.getAttribute("site");
			List<Vendor> vendorInSiteList=(List<Vendor>)request.getAttribute("vendorInSiteList");
			List<Vendor> vendorInNotSiteList=(List<Vendor>)request.getAttribute("vendorInNotSiteList");
			%>
			
			
			<div>
			<h3>
				Site :
				<%=site.getSiteName()%></h3>
<hr>

				<form name="AddVendorToSiteForm" action="AddVendorToSite"
					onsubmit="return validate()" method="post">


					<table style="width: 50%">
						<tr>




							<th width='40%'>Vendor In Site <input type="hidden"
								name="siteId" value="<%=site.getId()%>" />
							</th>
							
							<th></th>
							<th width='40%'>Vendor List</th>

						</tr>
						<tr>
							<td><select id="vendorsInSite" name="vendorsInSite"
								multiple="multiple" size="6">

									<%
										if (vendorInSiteList != null && vendorInSiteList.size() > 0) {
											for (Vendor vdto : vendorInSiteList) {
									%>
									<option name="vendorInSite" value="<%=vdto.getId()%>">

										<%=vdto.getName()%>
									</option>

									<%
										}
										}
									%>
							</select></td>
							<td><input type="button" value="<=" id="moveLeft"  class="formbutton"/> <br />
								<input type="button" value="=>" id="moveRight"  class="formbutton" /></td>
							<td><select name="vendorsNotInSite" id="vendorsNotInSite" 
								multiple="multiple" size="6">

									<%
										if (vendorInNotSiteList != null && vendorInNotSiteList.size() > 0) {
											for (Vendor vdto : vendorInNotSiteList) {
									%>
									<option value="<%=vdto.getId()%>">

										<%=vdto.getName()%>
									</option>

									<%
										}
										}
									%>
							</select></td>
						</tr>

					</table>
				<p style="margin-left: 19%">	<input type="submit" value="Update"  class="formbutton" />
				<input type="Button" class="formbutton" value="Back"
						onclick="javascript:history.go(-1);" />
				</p>
				</form>
			</div>
			<%@include file="Footer.jsp"%>
		</div>
	</div>


</body>
</html>
