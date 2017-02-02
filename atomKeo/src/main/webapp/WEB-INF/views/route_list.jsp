
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<Script>
function showAuditLog(relatedId,moduleName){
	var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	var size = "height=450,width=900,top=200,left=300," + params;
	var url="ShowAuditLog.jsp?relatedNodeId="+relatedId+"&moduleName="+moduleName;	
    newwindow = window.open(url, 'AuditLog', size);

	if (window.focus) {
	newwindow.focus();
}
}
function submitSiteForm() {
	try{
	document.getElementById("siteForm").submit();
	}catch(e)
	{alaert(e);}
	
}
</Script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Routes</title>
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
            String site = request.getParameter("siteId");
            String orderTheRoute = request.getParameter("orderTheRoute");
            
            if(site == null ) site = "";
    		System.out.println("site id = "+site);
    		
            List<Site> sites=(List<Site>)request.getAttribute("siteList");
            %>
	<%@include file="Header.jsp"%>
	<div id="body">
		<div class="content">
			<br />
			<form action="loadShiftRoute" name="siteForm" id="siteForm">
				<table>
					<tr>
						<td>Site</td>
						<td><select name="siteId" onchange="return submitSiteForm();">
								<option value="">--select--</option>
								 	<%
										for (Site s : sites) {
											String siteSelect = "";
											System.out.println();
											if ((String.valueOf(s.getId())).equals(site)) {
													siteSelect = "selected";
											}
									%>

									<option <%=siteSelect%> value="<%=s.getId()%>">
										<%=s.getSiteName()%>
									</option>


									<%
										}
									%> 
						</select></td>
					</tr>
				</table>
			
			<%
      List<Route> routeList=(List<Route>)request.getAttribute("routeList");

%>
		 <h3>Route List</h3>
			
			<input type="submit" name="orderTheRoute" class="formbutton" onclick="submitSiteForm()" value="Order The Route" >
			
			<%-- <%
			if(orderTheRoute!=null &&!orderTheRoute.equals("")&&!orderTheRoute.equals("null") )
			{
				System.out.println("orderTheRoute IN   "+orderTheRoute);
				new RouteService().orderTheRoute(site);
			}			
			%> --%>
			</form>	
			<hr />
			<p align="center"> <a href="downloadroute.jsp?site=<%=site%>">Download all routes</a></p>
			<table>
			
				<thead>

					<tr>
						<th align="center">Id</th>
						<th align="center">Route</th>
						<th align="center">Type</th>
						<th align="center">Site</th>
						<th align="center">Path</th>
						<th align="center">Audit Log</th>
					</tr>
				</thead>
				<%
				if(routeList!=null && routeList.size()>0){
for(Route route:routeList)
{
%>

				<tr>
					<td align="center"><%=route.getId()%></td>

					<td align="center"><a
						href="route_modify.jsp?routeId=<%=route.getId()%>"><%=route.getRouteName()%></a></td>
					<td align="center"><%=route.getRoutetype().getTypeDesc()%></td>
					<td align="center"><%=route.getSite().getSiteName()%></td>
					<td><a
						href="edit_route.jsp?routeId=<%=route.getId()%>">Edit</a></td>
					<td align="center"><input type="button" class="formbutton"
						onclick="showAuditLog(<%=route.getId()%>,'<%=AuditLogConstants.ROUTE_MODULE%>');"
						value="Audit Log" /></td>
				</tr>
				<%
}  
}%>
			</table>
 
		</div>
	</div>
</body>
</html>
