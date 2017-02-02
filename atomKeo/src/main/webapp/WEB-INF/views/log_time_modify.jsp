<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>

<script>
	$(document).ready(function() {
		$("#closeAddProject_img").click(closeAddProject);
		$("#projectSpecific").click(checkProjectSpecific);

	});

	function checkProjectSpecific() {

		openAddProject();

	}
	function openAddProject() {

		$("#addNewProjectToTime").show();
		$("#projectSpecific").hide();

	}
	function closeAddProject() {

		$("#addNewProjectToTime").hide();
		$("#projectSpecific").show();

	}

	function openWinodw(url) {
		window.open(url, 'Ratting',
				'width=400,height=350,left=150,top=200,toolbar=1,status=1,');

	}
	function validateLogTime() {

		var flag = true;
		if (document.getElementById("logTime").value.trim() == "") {
			alert("Project is not selected");
			flag = false;
		}
		return flag;
	}

	function validateProject() {
		var flag = true;
		if (document.getElementById("project").value.trim() == "") {
			alert("Process is not selected");
			flag = false;
		}
		return flag;
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
		Logtime lt=(Logtime) request.getAttribute("logtime");
	%>
	<div id="body">
		<div class="content">
			<table>
				<tr>
					<td style="width: 30%; vertical-align: bottom;">
						<form action="logTimeModify" method="POST"
							onsubmit="return validateLogTime()">
							<h3>Modify Shift Time</h3>

							<table style="width: 100%; display: inline;">
								<tr>
									<td>Time <input type="hidden"
										value="<%=lt.getId()%>" name="id" />
									</td>
									<td><input type="text" name="logTime" id="logTime"
										readonly="readonly" value="<%=lt.getLogtime()%>" />hh:mm</td>
									<td>
										<div
											style="border: thin; float: right; background-color: #F07726;"
											id="projectSpecific">Assign Projects &gt;&gt;</div>
									</td>
								</tr>

								<tr>

									<td colspan="2" width="50%"><input class="formbutton"
										style="float: none;" type="submit" value="Update"
										onsubmit="return validateLogTime()" /> &nbsp;&nbsp; <input
										class="formbutton" type="submit" value="Back"
										onsubmit="javascript:history.go(-1);" /></td>
									<td></td>

								</tr>

							</table>

						</form>
					</td>
					<td style="vertical-align: bottom;" align="left">
						<%
							if (lt.getLogtime() != null
									&& !lt.getLogtime().equals("")) {
						%>

						<form method="POST" name="addNewProjectToTime"
							id="addNewProjectToTime" style="display: none; padding-left: 2%;"
							action="addProjectToTime" onsubmit="return validateProject()">
							<table
								style="width: 50%; display: inline; vertical-align: bottom">
								<tr>
									<th colspan="2"><label id="windowTitle">Add
											<%=SettingsConstant.PROJECT_TERM%></label>
										<div style="float: right;" id="closeAddArea">
											<img id="closeAddProject_img" style="float: right;"
												id="closeAddProject" src="images/close.png" title="Close" />
										</div></th>
								</tr>
								<tr>
									<td><%=SettingsConstant.PROJECT_TERM%></td>
									<td><input type="text" name="projectdesc" id="projectdesc"
										readonly="readonly" /> <input type="hidden" name="project"
										id="project" /> <input type="hidden"
										value="<%=lt.getId()%>" name="id" id="timeID" /> <input
										type="button" class="formbutton" value="..."
										onclick="openWinodw('getProjectCall' ); " /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" class="formbutton"
										value="Add" onsubmit="return validateProject()" /></td>
								</tr>
							</table>
						</form> <%
 	}
 %>
					</td>
				</tr>
			</table>
			<%
				if (lt.getAtsconnects().size()>0) {
			%>
			<form action="#">
				<table>
					<tr>
						<th><%=SettingsConstant.PROJECT_TERM%> Code</th>
						<th>Description</th>
						<th></th>
					</tr>
					<%
					List<Atsconnect> atsConnectList=lt.getAtsconnects();
						
							if (atsConnectList == null || atsConnectList.size() <= 0) {
					%>
					<tr>
						<td colspan="3">No Projects found</td>

					</tr>
					<%
						} else {
								for (Atsconnect ats : atsConnectList) {
					%>
					<tr>
						<td><%=ats.getProject()%></td>
						<td><%=ats.getDescription()%></td>
						<td><a
							href="removeAssignment?projectId=<%=ats.getId()%>&id=<%=lt.getId()%>">Remove</a>
						</td>
					</tr>

					<%
						}

							}
					%>
				</table>
			</form>
			<%
				}
			%>

		</div>
	</div>
</body>
</html>
