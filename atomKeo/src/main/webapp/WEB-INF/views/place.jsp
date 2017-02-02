<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Place</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("form[name=place]").hide();
		$("form[name=editPlace]").hide();
		$("#showAddPlaceDiv_a").click(showAddPlace);
		$("#closeAddPlace_img").click(closeAddPlace);
		$(".editButton").click(showEditPlace);
		$("form[name=place]").draggable();

	});

	//--------------
	function showAddPlace() {
		$("#place").val("");
		$("form[name=editPlace]").attr("action", "addPlace");
		$("#windowTitle").text("Add Place");
		$("#showAddPlaceDiv").hide();
		$("form[name=place]").show();
		$("#submitbtn").val("Add");
	}
	function closeAddPlace() {

		$("#showAddPlaceDiv").show();
		$("form[name=place]").hide();
		$("form[name=place]").attr("action", "#");
	}

	function showEditPlace() {
		try {
			$("form[name=place]").show();
			$("form[name=place]").attr("action", "updatePlace");
			$("#windowTitle").text("Edit Place");
			$("#submitbtn").val("Update");

			$("input[name=placeId]").val(
					$(this).parent().parent().children().children(".placeId")
							.val());
			$("#place").val(
					$(this).parent().parent().children().children(".place")
							.val());

		} catch (e) {
			alert(e);
		}
	}

	//-----validation

	//--------------

	function validatePlace() {

		var flag = true;

		try {
			if ($("input[name=place]").val().trim() == "") {
				alert("Place should not be blank");
				flag = false;
			} else if(/^[A-Za-z0-9]+[A-Za-z0-9&\s\.\,\/\-\_:\(\)\[\]]*$/.test($("input[name=place]").val().trim())==false) {
				alert("Place includes invalid data");
				flag = false;
			}
		} catch (e) {

			alert(e.message);
			flag = false;
		}
		return flag;

	}
	function showAuditLog(relatedId,moduleName){
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		var size = "height=450,width=900,top=200,left=300," + params;
		var url="ShowAuditLog.jsp?relatedNodeId="+relatedId+"&moduleName="+moduleName+"&current=PLACE";	
	    newwindow = window.open(url, 'AuditLog', size);

		if (window.focus) {
			newwindow.focus();
		}
	}
</script>
</head>
<body>

	<%
		/* long empid = 0;
		String employeeId = OtherFunctions.checkUser(session);
		empid = Long.parseLong(employeeId); */
	%>
	<%@include file="Header.jsp"%>
	<div id="body">
		<div class="content">

			<%
				int areaId = Integer.parseInt("" + request.getParameter("areaId"));

				List<Place> placesUnderArea=(List<Place>)request.getAttribute("placeList");
				Area ar=(Area)request.getAttribute("areas");
			%>


			<h3>
				Places under
				<%=ar.getArea()%></h3>

			<hr />

			<table style="width: 50%;float: left;">
				<tr>
					<td style="width: 70%; vertical-align: top;">
						<table>
							<thead>
								<tr>
									<th align="center">Id</th>
									<th colspan="2">Place</th>
									<th align="center">Audit Log</th>

								</tr>
							</thead>
							<%
								for (Place place : placesUnderArea) {
							%>
							<tr>
								<td align="center"><%=place.getId()%></td>
								<td align="center"><a
									href="showLandmark?placeId=<%=place.getId()%>"><%=place.getPlace()%></a>

									<input type="hidden" value="<%=place.getId()%>"
									class="placeId" /> <input type="hidden"
									value="<%=place.getPlace()%>" class="place" /></td>
								<td><img src="images/edit.png" class="editButton"
									title="Edit" /></td>
								<td align="center"><input type="button" class="formbutton"
									onclick="showAuditLog(<%=place.getId()%>,'<%=AuditLogConstants.APL_MODULE%>');"
									value="Audit Log" /></td>
							</tr>
							<%
								}
							%>


						</table>
					</td>
					<td style="width: 30%; vertical-align: top;">
						<div id="showAddPlaceDiv" style="padding-top: 3px;">
							<input type="button" class="formbutton" id="showAddPlaceDiv_a"
								value="Add Place" />

						</div>
						<form name="place" action="addPlace"
							onsubmit="return validatePlace();">


							<table style="border-style: outset; width: 20%;">
								<thead>
									<tr>
										<th colspan="2"><label id="windowTitle">Add Place</label>
											<div style="float: right;" id="closeAddPlace">
												<img id="closeAddPlace_img" style="float: right;"
													id="closeAddPlace" src="images/close.png" title="Close" />
											</div></th>
									</tr>
								</thead>
								<tr>
									<td align="center">Place</td>
									<td align="center"><input type="text" name="place"
										id="place" /> <input type="hidden" name="placeId" /> <input
										type="hidden" value="<%=areaId%>" name="areaId" /></td>
								</tr>
								<tr>
									<td align="center">&nbsp;</td>
									<td align="center"><input type="submit" class="formbutton"
										value="Add" name="submitbtn" id="submitbtn" /></td>
								</tr>

							</table>
						</form>

					</td>
				</tr>
			</table>
			<iframe  style="float: right; width: 48%" height="500px" src="marklandmark?area=<%=areaId%>" ></iframe>
			<%@include file="Footer.jsp"%>

		</div>
	</div>
</body>
</html>

