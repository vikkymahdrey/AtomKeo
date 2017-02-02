<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<Script>
function showAuditLog(relatedId,moduleName){
	var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	var size = "height=450,width=900,top=200,left=300," + params;
	var url="showAuditLog?relatedNodeId="+relatedId+"&moduleName="+moduleName;	
    newwindow = window.open(url, 'AuditLog', size);

	if (window.focus) {
	newwindow.focus();
}
}
</Script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Vehicle Type List</title>
</head>
<body>
	<%
	List<VehicleType> vehicleTypeList=(List<VehicleType>)request.getAttribute("vehicleTypeList");
	        
	Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
	if (userLoggedIn == null) {
			String param = request.getServletPath().substring(1) + "___"
					+ request.getQueryString();
			response.sendRedirect("/?page=" + param);
		} else {
					
	%>
	<%@include file="Header.jsp"%> 
	<%
		 } %>

	<div id="body">
		<div class="content">
			<h3>Vehicle List</h3>
			<hr />
			<table width="60%">
				<thead>
					<th>Vehicle</th>
					<th>Seat</th>
					<th>Sitting Capacity</th>
					<th></th>
					<th align="center">Audit Log</th>
				</thead>
				<tbody>
					<tr>
						<%

                    for (VehicleType vehicleType : vehicleTypeList) {
                        %>
						<td align="center"><%=vehicleType.getType()%></td>
						<td align="center"><%=vehicleType.getSeat()%></td>
						<td align="center"><%=vehicleType.getSitCap()%></td>
						<td>
							<form>
								<input type="button" class="formbutton" value="Modify"
									onclick="javascript: window.location='modifyVehicleType?id=<%=vehicleType.getId()%>';"
									value="Modify" />
							</form>
						</td>
						<td align="center"><input type="button" class="formbutton"
							onclick="showAuditLog(<%=vehicleType.getId() %>,'<%=AuditLogConstants.VEHICLE_MODULE%>');"
							value="Audit Log" /></td>

					</tr>
					<%}
            %>
					<tr>
						<td align="center" colspan="4">
							<form>
								<input type="button" class="formbutton"
									onclick="javascript:history.go(-1);" value="Back" />
									&nbsp;
									<input type="button" class="formbutton"
									onclick="location.href='newVehicleType'" value="New" />
							</form>
						</td>
					</tr>
				</tbody>
			</table>

			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
