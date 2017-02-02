<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.nfl.com" prefix="disp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<title>Driver Setup</title>
<% request.setAttribute("contextPath", request.getContextPath()); %>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/displaytag.css" />
<script type="text/javascript" src="js/dispx.js"></script>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script src="js/JavaScriptUtil.js"></script>

<script type="text/javascript">
	function validate() {
		var name = $("input[id=name]").val();
		var address1 = $("input[id=address1]").val();
		var address2 = $("input[id=address2]").val();
		var contactNo = $("input[id=contactNo]").val();
		var username = $("input[id=username]").val();
		var vendor = $("input[id=vendor]").val();
		var nameRegx = /^[A-Za-z][A-Za-z0-9 ]*$/;
		var vehicleNo = $("input[id=vehicleNo]").val();
		try {
			if (name == "") {
				alert("Name should not be blank !  ");
				return false;
			}
			if( nameRegx.test(name)==false) {
				alert("Name is invalid !");
				return false;
			}
			if (address1 == "") {
				alert("Address Should not be blank !");
				return false;
			} 
	
			if (contactNo == "") {
				alert("Contact No  Should not be blank !");
				return false;
			} 
			
			var phoneRegx = /^\+?\d?\d?\d{10}$/;
			
			if (phoneRegx.test(contactNo )==false) {
				alert("Contact No  is invalid!");
				return false;
			} 
			
			if (username == "") {
				alert("User Name Should not be blank !");
				return false;
			}  
			
			 if (vendor == "") {
				alert("select Vendor");
				return false;
			} 
			else {
				return true;
				 
			}
			return false;
		} catch (e) {
			alert(e)
			return false;
		}
	}
	
	function fillFields(id) {
		 
		
		$("#name").val($("#name-"+id).val());
		
		$("#address1").val($("#address1-"+id).val());
		$("#username").val($("#username-"+id).val());
		$("#contactNo").val($("#contactNo-"+id).val());
		$("#vendor").val($("#vendor-"+id).val());
		$("#driverId").val($("#driverId-"+id).val());
		$("form[name='driiveradd']").attr("action","updateDriver");
		$("input[name='submitbtn']").val("Update");
	}
	

	function clearFields() {
		 		
		$("#name").val("");
		
		$("#address1").val("");
		$("#username").val("");
		$("#contactNo").val("");
		$("#vendor").val("");
		$("#driverId").val("");
		$("form[name='driiveradd']").attr("action","addDriver");
		$("input[name='submitbtn']").val("Submit");
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
		List<Driver> driverList=(List<Driver>)request.getAttribute("driverList");
				
	%>

	<div id="body">
		<div class="content">


			<h3>Add Driver</h3>
			<hr />
			<form name="driiveradd" action="addDriver" method="post"
				onsubmit="return validate()">

				<table width="70%">
					<tr>
						<td align="center">Name</td>
						<td><input type="text" name="name" id="name" />
						<input type="hidden" name="driverId" id="driverId" />
						</td>
					</tr>
					<tr>
						<td align="center">Address1</td>
						<td><input type="text" name="address1" id="address1" /></td>
					</tr>
					<tr>
						<td align="center">Contact No</td>
						<td><input type="text" maxlength="10" name="contactNo" id="contactNo" /></td>
					</tr>
					<tr>
						<td align="center">User Name</td>
						<td><input type="text" name="username" id="username" /></td>
					</tr>
					<tr>
						<td align="center">Password</td>
						<td><input type="password"  name="password" id="password" /></td>
					</tr>
					<tr>
                    
					<tr>
						<td align="center">Vendor</td>
						<td><select name="vendor" id="vendor">
						<%
							List<Vendormaster> vendorMasterList = (List<Vendormaster>)request.getAttribute("vendorMasterList");
						%>
								<option></option>
								
								<%
								if(vendorMasterList!=null&&vendorMasterList.size()>0)
								{
								for(Vendormaster vendorMaster:vendorMasterList) {%>
								<option value="<%=vendorMaster.getId()%>"><%=vendorMaster.getCompany()%></option>
								<%} 
								
								}%>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="formbutton" name="submitbtn"
							value="Submit" /> <input type="button" class="formbutton"
							onclick="javascript:history.go(-1);" value="Back" />
							<input type="button" class="formbutton"
							onclick="clearFields()" value="Clear" />
							</td>
					</tr>
				</table>
			</form>
			<p>Driver</p>
			<disp:dipxTable id="row" list="<%=driverList %>" style="width:60%;" >
				<disp:dipxColumn group="1" title="Name" sortable="true" property="name" >
				<input type="hidden" id="driverId-${rowCount+1}" value="${row.id}"/>
				<input type="hidden" id="address1-${rowCount+1}" value="${row.address }"/>
				<input type="hidden" id="name-${rowCount+1}" value="${row.name }"/>
				<input type="hidden" id="contactNo-${rowCount+1}" value="${row.contact }"/>
				<input type="hidden" id="username-${rowCount+1}" value="${row.username }"/>
				<input type="hidden" id="vendor-${rowCount+1}" value="${row.vendormaster.id }"/>
				</disp:dipxColumn>
				<disp:dipxColumn group="1" title="User Name" sortable="true" property="username" ></disp:dipxColumn>
				<disp:dipxColumn group="1" title="Address" sortable="true" property="address" ></disp:dipxColumn>
				<disp:dipxColumn title="Vehicle" sortable="true">
				<c:if test="${fn:length(row.driverVehicles) > 0}">
					${row.driverVehicles.get(0).vehicle.regNo}
				</c:if>	
				</disp:dipxColumn>
				<disp:dipxColumn title="Action"   ><a onclick="fillFields('${rowCount+1}')">Edit</a></disp:dipxColumn>
			</disp:dipxTable>
		 
		 <%@include file="Footer.jsp"%> 
		</div>
	</div>
</body>

</html>
