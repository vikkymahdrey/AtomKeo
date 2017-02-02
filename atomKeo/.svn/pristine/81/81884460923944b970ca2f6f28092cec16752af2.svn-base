<%-- 
    Document   : newjsp
    Created on : Oct 22, 2012, 1:00:11 PM
    Author     : muhammad
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Print Trips</title>
<%

        String mimeType = "application/vnd.ms-excel";        
        response.setContentType(mimeType);        
        String fname = new Date().toString();
        response.setHeader("Content-Disposition", "inline; filename = APL" + fname + ".xls");        
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "Mon, 1 Jan 1995 05:00:00 GMT");
        %>
</head>
<body>

			<%
			
				String location=request.getParameter("location");
				List<Area> areas=(List<Area>)request.getAttribute("fetchAPL");
				%>
			<table style="width: 50%" border="1">
				<thead>
					<tr>
						<th align="center">Landmark Id</th>					
						<th align="center">Area</th>
						<th align="center">Place</th>
						<th align="center">Landmark</th>
						<th align="center">Latitude</th>
						<th align="center">Longitude</th>
					</tr>
				</thead>
				
				
				<%	for (Area area : areas) {
						for(Place place : area.getPlaces()){
							  for(Landmark lm:place.getLandmarks()){ %>
							  <tr>
								  <td><%=lm.getId()%></td>
									<td><%=area.getArea()%></td>
									<td><%=lm.getPlaceBean().getPlace()%></td>
									<td><%=lm.getLandmark()%></td>			 
									<td><%=lm.getLatitude()%></td>
									<td><%=lm.getLongitude()%></td>					
				              </tr>
				<%			  }
							  
							}
					}
				%>
				
			</table>
</body>
</html>
