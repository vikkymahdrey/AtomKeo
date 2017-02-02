<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Add Route</title>

<script type="text/javascript">
	function submitForm(form) {
		try{
		var selectedAPL = document.getElementById("selectedapl");
		var routeName = document.getElementById("routeName");
		var routeSite = document.getElementById("routeSite");
		var selectedAPLLength = document.getElementById("selectedapl").options.length;
		if (routeName.value.length < 1) {
			alert("Route Name Should Not Blank");
		} else if (routeSite.value.length < 1) {
			alert("Site Should Not Blank");
		} else if (selectedAPLLength < 1) {
			alert("No  APL is selected");
		} else {
			for ( var i = 0; i < selectedAPLLength; i++) {
				selectedAPL.options[i].selected = true;
			}
			form.submit();
		}
		}catch(e)
		{aert(e);}
	}

	function listMoveRight() {
		var apl = document.getElementById("apl");
		var selectedAPL = document.getElementById("selectedapl");
		var optionSelected = apl.selectedIndex;
		var optionNew = document.createElement('option');
		optionNew.value = apl.options[optionSelected].value;
		optionNew.text = apl.options[optionSelected].innerHTML;
		try {
			selectedAPL.add(optionNew, null);
		} catch (e) {
			selectedAPL.add(optionNew);
		}
		apl.options[optionSelected] = null;
	}
	function listMoveLeft() {
		var selectedAPL = document.getElementById("selectedapl");
		var apl = document.getElementById("apl");
		var optionSelected = selectedAPL.selectedIndex;
		var optionNew = document.createElement('option');
		optionNew.value = selectedAPL.options[optionSelected].value;
		optionNew.text = selectedAPL.options[optionSelected].innerHTML;
		try {
			apl.add(optionNew, null);
		} catch (e) {
			apl.add(optionNew);
		}
		selectedAPL.options[optionSelected] = null;
	}
	function submitBranchForm() {
		document.getElementById("branchForm").submit();
	}
</script>
</head>
<body>	
	<div id="body">
		<div class="content">
			<%
			Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
			if (userLoggedIn == null) {
					String param = request.getServletPath().substring(1) + "___"
							+ request.getQueryString();
					response.sendRedirect("/?page=" + param);
				} else {
					%>
			<%@include file="Header.jsp"%> 
			<%
				 }
				
					String location = request.getParameter("branchId");
				System.out.println("branchIDjSp= "+location);
					if(location==null){
						location="";						
					}
						
				List<Branch> branchList=(List<Branch>)request.getAttribute("branches");
			%>
			<form action="loadRouteSetup" name="branchForm" id="branchForm">
				<table>
					<tr>
						<td>Location</td>
						<td><select name="branchId" onchange="submitBranchForm()">
								<option>Select</option>
								<%
									for (Branch branch : branchList) {
										if (location.equals(""+branch.getId())) {
								%>
								<option value="<%=branch.getId()%>" selected="selected"><%=branch.getLocation()%></option>
								<%
									} else {
								%>
								<option value="<%=branch.getId()%>"><%=branch.getLocation()%></option>
								<%
								}
									}
								%>
						</select></td>
						<td>
						<a href="routeUpload.do">Upload Route</a>
						</td>
					</tr>
				</table>
			</form>
			<%
				/* ArrayList<APLDto> aplDtos = new APLService().getAllAPL(location);
				List<SiteDto> siteDtos = new SiteDao().getSites(location);
				ArrayList<RouteDto> routeTypedtos = new RouteService()
						.getRoutetypes(); */
				if(location!=null && !location.equals("") ) {
				List<Area> areaList=(List<Area>)request.getAttribute("areaList");
				List<Site> siteList=(List<Site>)request.getAttribute("siteList");
				List<Routetype> routeTypeList=(List<Routetype>)request.getAttribute("routeTypeList");
			%>

			<h3>Add Route</h3>

			<form action="addRoute" name="addrouteform" id="addrouteform"
				method="POST">
				<table border="0" width="100%">
					<tbody>
						<tr>
							<td>Existing APL</td>
							<td>&nbsp;</td>
							<td>APL added to Route</td>
						</tr>
						<tr>
							<td rowspan="5" width="40%"><select name="apl" id="apl"
								multiple="multiple">
									<%
										for (Area area : areaList) {
											for(Place place: area.getPlaces()){
												for(Landmark landmark: place.getLandmarks()){
																				%>
									<option value="<%=landmark.getId()%>">
										<%=area.getArea() + ":" + landmark.getPlaceBean().getPlace() + ":"
						+ landmark.getLandmark()%>
									</option>
									<%
										}
									}
								}		 
									%>
							</select></td>
							<td width="10%" rowspan="5">
								<p>
									<input type="button" class="formbutton" name="right"
										value="&rArr;" onclick="listMoveRight()" />
								</p>
								<p>
									<input type="button" class="formbutton" name="left"
										value="&lArr;" onclick="listMoveLeft()" />
								</p>
							</td>
							<td rowspan="5"><select name="selectedapl" id="selectedapl"
								multiple="multiple">

							</select></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="center">Route Name</td>
							<td align="left"><input type="text" id="routeName"
								name="routeName"></td>
						</tr>
						<tr>
							<td align="center">Site</td>
							<td align="left"><select id="routeSite" name="routeSite">
									<%
										for (Site site : siteList) {
									%>

									<option value="<%=site.getId()%>"><%=site.getSiteName()%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td align="center">Route type</td>
							<td align="left"><select id="routeType" name="routeType">
									<%
										for (Routetype routeType : routeTypeList) {
									%>

									<option value="<%=routeType.getType()%>"><%=routeType.getTypeDesc()%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="2"><input type="button" id="submitbtn"
								name="submitbtn" class="formbutton" value="Submit"
								onclick="submitForm(this.form)"></td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</form>
			<%}
							%>
			<%@include file="Footer.jsp"%>
		</div>
	</div>

</body>
</html>
