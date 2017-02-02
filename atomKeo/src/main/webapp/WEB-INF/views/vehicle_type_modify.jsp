<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<title>Modify Vehicle Type</title>
<script type="text/javascript">
            function validate()
            {  
                var vehicleType=$("input[id=vehicleType]").val();
                var seat=$("input[id=seat]").val();             
                var sittingCapacity=$("input[id=sittingCapacity]").val();
                try{
                	
            if(vehicleType.trim()=="")
                        {
                        alert("Vehicle should not be blank !");
                        return false;
                        }
             else if(seat.trim()=="" )
                        {
                        alert("Seat should not be blank !");                            
                        return false;
                        }
             else if( isNaN(seat.trim()))
			             {
			             alert("Seat is invalid !");                            
			             return false;
			             }
             else if(sittingCapacity.trim()=="" )
                        {
                        alert(" Seat Capacity should not be blank !");                            
                        return false;                                
                        }
             else if( isNaN(sittingCapacity.trim()))
			             {
			             alert(" Seat Capacity is invalid !");                            
			             return false;                                
			             }
              else
		                  {
		                  return true;
		                  }
            return false;    
            }catch(e)
                {
                    alert(e)
                    return false;
                }
            }
        </script>
</head>

<body>
	<%
String vehicleTypeId=request.getParameter("id");
VehicleType vehicleType=(VehicleType)request.getAttribute("vehicleTypeList");

%>
	<div id="body">
		<div class="content">

			<%
       /*  long empid=0;
        String employeeId = OtherFunctions.checkUser(session);       
            empid = Long.parseLong(employeeId); */
            %>
			<%@include file="Header.jsp"%>

			<form name="vehicleType" action="addModifyVehicleType"
				onsubmit="return validate()">
				<h3>Vehicle Type</h3>
				<hr />
				<table width="70%">


					<tr>
						<input type="hidden" name="id"
							value="<%=vehicleType.getId()%>" />
						<td align="center">Vehicle</td>
						<td><input type="text" name="vehicleType" id="vehicleType"
							value="<%=vehicleType.getType()%>" /></td>
					</tr>
					<tr>
						<td align="center">Seat</td>
						<td><input type="text" name="seat" id="seat"
							value="<%=vehicleType.getSeat()%>" /></td>
					</tr>
					<tr>
						<td align="center">Seat Capacity</td>
						<td><input type="text" name="sittingCapacity"
							id="sittingCapacity"
							value="<%=vehicleType.getSitCap()%>" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="submitbtn" value="Submit"
							class="formbutton" /> <input type="button"
							onclick="javascript:history.go(-1);" value="Back"
							class="formbutton" /></td>
					</tr>
				</table>
			</form>



			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
