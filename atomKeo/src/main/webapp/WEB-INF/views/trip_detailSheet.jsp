<%@page import="javassist.bytecode.stackmap.BasicBlock.Catch"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Trip Sheet</title>

<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<link href="css/select2.css" rel="stylesheet"/>
<script src="js/select2.js"></script>
<script type="text/javascript">
			
			
			 function sendTripSms(tripId1) {
				 var driver=$("[name=driver"+tripId1+"]").val();
				 var vehicle=$("[name=vehicle"+tripId1+"]").val();
				 var isescort=$("[name=isSecurity"+tripId1+"]").val();
                                 var time=$("[name=traveltime"+tripId1+"]").val();
				 var escort="";
				 try{
				 escort=$("[name=escort"+tripId1+"]").val();
				 }catch(e)
				 {
					 alert(e);
				 }
				 
				 if(driver==null||driver=="" ||driver=="null"||vehicle==null||vehicle=="" ||vehicle=="null")
					 {
					 alert("driver or vehicle not set");
					 }
				 else if(driver!=null&&isescort=="YES"&&(escort==null||escort=="" ||escort=="null"))
					 {					 					 
					alert("Escort Not Set");	 					 
					 }
				 else
					 {
					
				  $.ajax(
					 {
					         
					        type: "POST",
					        url: "SendSmsToDriver",
					        processData: true,
					         data:{trip:""+tripId1,vehicleid:""+vehicle,driverid:""+driver,escortid:""+escort,travelTime:""+time}
					         
					 
					} 		
				).done(function(msg) {
					alert(msg);
				});	
					 }				 
			} 
			 
			 function validateEscortChange(id) {
					var escortClass=$('#' + id ).attr("class");
					//alert(escortClass);
					var count=0;
					var escortValue = $('#' + id).val();
					 if(escortValue!="") { 
							   var myElements = $("."+escortClass);
							    for (var i=0;i<myElements.length;i++) {
							        if(myElements.eq(i).val()==escortValue)
							        	{
							        	if(id!=myElements.eq(i).attr('id'))
							        		{
							        		count=1;
							        		}
							        	}
							    }
							
						if(count>0) {
							$('#' + id).val("");
							alert("This escort has been selected already for the same time");
							showErrorMessage($('#' + id),"The field is required");
						} else {
							 removeErrorMessage(	$('#' + id));  
						}
				 
					 } else {
						 showErrorMessage($('#' + id),"The field is required");
					 }
					
					
				}
			
			
				
</script>


<script type="text/javascript">
	
	function printPage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("tripMode").value;
		var siteId = "1";
		window.location = "print_trips.jsp?siteId=" + siteId + "&tripDate="
				+ tripDate + "&tripTime=" + tripTime + "&tripMode=" + tripMode
				+ "";
	}
	

	function exportPage() {
		var tripDate = document.getElementById("tripDate").value;
		var tripTime = document.getElementById("tripTime").value;
		var tripMode = document.getElementById("log").value;
		var siteId = "1";
		var newwindow = window.open("export2PDF?siteId=" + siteId
				+ "&tripDate=" + tripDate + "&tripTime=" + tripTime
				+ "&tripMode=" + tripMode + "", 'name', 'location=no');
		if (window.focus) {
			newwindow.focus();
		}
	}
	
	
	function showErrorMessage(element, message) {
		removeErrorMessage(element); 
		 $(element).parent().append( 
				 "<img id='" + $(element).attr("id") + "_errorImage' src='images/error.png' title='" + message + "'   />");

	}
	
	function removeErrorMessage(element) {
		try { 
			if($("#"+$(element).attr("id") + "_errorImage" )!=undefined) {
				$("#"+$(element).attr("id") + "_errorImage" ).remove();
			}
		} catch(e ) {}
	}
	
	function requiredValidate(element, message) {
		var retFlag=true;
		//alert('...........((()))');
		try { 
		//var isToValidate =$("#driver_" + $(element).attr("id").split("_")[1]).val()!="" && $("#driver_" + $(element).attr("id").split("_")[1]).val()!=null;
		//alert( "IIII: " +(isToValidate) + "  " + $("#driver_" + $(element).attr("id").split("_")[1]).val() +  " isnull " + ($("#driver_" + $(element).attr("id").split("_")[1]).val()==null));
		//if(isToValidate) {
		//	alert("Not empty..");
		//}
		  
		 if($(element).val()==""  ) {
			 retFlag=false;
			 showErrorMessage(element,message);
		 }
		}catch (e) {
			// TODO: handle exception
			alert(e);
		}
		 return retFlag;
	}
	
	function validateForm() {
	 	try {
			flag = true;
		 $("select[class*=escortClass]").each(function() {
			 //alert($(this).val() + " - " + $(this).attr("class"));
			 if($(this).val()=="") { 
			  
				 flag= requiredValidate(this,"The field is required");
			 }
		 });
		 if(flag==false) {
			 alert("Validation Error");
		 }
		} catch(e) {
			alert(e);
		}
		return flag;
 
 return true;
	}
</script>


</head>
<body>
	<%
		
		String tripDate = request.getParameter("tripDate");
		String tripMode = "" + request.getParameter("log");
		String tripTime =   request.getParameter("tripTime");
		try{
		List<TripDetail> tripSheetSaved = (List<TripDetail>)request.getAttribute("tripSheetSaved");
		List<Vehicle> vehList = null;
		
	%>
	<%
	boolean flag = false;
	Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
	if (userLoggedIn == null) {
			String param = request.getServletPath().substring(1) + "___"
					+ request.getQueryString();
			response.sendRedirect("index.jsp?page=" + param);
		} else {
			// empid = Long.parseLong(employeeId); 
	%>
	<%@include file="Header.jsp"%> 
	<%
		 }
		%>

	<div id="body">
		<div class="content">
			<hr />
			<form name="assignVehicle" action="tripSheetAssignVehicle" method="post" onsubmit="return validateForm()" >
 
				<input type="hidden" name="tripDate" id="tripDate"
				value="<%=tripDate%>" /> <input type="hidden"
				name="tripMode" id="tripMode" value="<%=tripMode%>" /> <input
				type="hidden" name="tripTime" id="tripTime" value="<%=tripTime%>" />
									
			<%
				if (tripSheetSaved != null && tripSheetSaved.size() > 0) {
					flag = true;
			%>
			<hr />
			<h3>Track Trip Sheet</h3>


			<table border="1" style=" width:85%; ">
				<%
			
				try{
					int serialNo=1;
					for (TripDetail td : tripSheetSaved) {
						vehList=(List<Vehicle>)request.getAttribute("vehicles");
						
									 
				%>
				<tr>
				</tr>
				<tr>
					<td colspan="7"  style="background-color: #ddd;" >
					<input type="hidden" name="tripId" value="<%=td.getId() %>">					
				 <div style="display: inline; margin-left: 15%; font-size: 15px; font-style: italic; ">
					 <%String tripCode=""; 
					 if(td.getTripCode()!=null){
						 tripCode=td.getTripCode();
					 }%>
					 Trip ID 
					 <%=tripCode%>
				</div>
				<div  style="display: inline; margin-left: 15%;  font-size: 15px; font-style: italic; ">
					Trip Date :
					 <%=OtherFunctions.changeDateFromatToddmmyyyy(OtherFunctions.changeDateFromat(td.getTripDate()))%>
				</div>
				<div  style="display: inline; margin-left: 15%;  font-size: 15px; font-style: italic; ">
					Time :
					<%=td.getTripLog()+""+td.getTripTime()%>
				</div>
					</td> 
				</tr>
			 
			 
				<tr>
					<td colspan="4">
						<div>
							<table>
								<tr>
									<td>Vehicle</td>
									<%String type="";
									if(td.getVehicleBean()!=null){
										type=td.getVehicleBean().getRegNo();}
										%>
									<td  ><%=type%></td>
								</tr>
								<tr>
									<td>Escort Trip</td>
									<td> YES </td>
									
								</tr>
								
							</table>
						
						</div>
					</td >
					<td colspan="3">
						<div>
							<table>
								<tr>
									
							<td>Vehicle Number</td>
							<td>
							<select class="vehicleclass" name="vehicle<%=td.getId()%>" id="vehicle_<%=serialNo%>">
									<option value="">Select</option>
		 
							<%
							 try{
								 			
							for(Vehicle v: vehList) {
								String selected="";									
								if(td.getVehicleBean()!=null){
									if(td.getVehicleBean().getId()==v.getId()) {
												selected="selected";
									}
								}
							%>
					
							<option <%=selected %> value="<%=v.getId()%>"><%=v.getRegNo()%></option>
		 					<%}
								
							 }catch(Exception e) {
									System.out.println("ERRRRRRRRRRRRRRRR"+e);
							 } 
								 %>
							</select>
							
							<select name="driver<%=td.getId()%>" id="driver_<%=serialNo%>">
							<option  value="0" >Select Driver</option>
							<%
							
							
							List<Driver> driverList =(List<Driver>)request.getAttribute("drivers");
							
							if(driverList!=null && driverList.size()>0) {
								for (Driver d : driverList) {
									String selected="";	
								if(td.getDriver()!=null){
									if(td.getDriver().getId()==d.getId()) {
									selected="selected";
									}
								}
					
							%>
								<option <%=selected %> value="<%=d.getId() %>" ><%=d.getName() %></option>
							<%} 
							
							}%>
							</select>
					</td>
					
					  <td>Escort</td>
							<td>
							 	 <input type="hidden" value="YES" name="isSecurity<%=td.getId() %>" />
								 <%
								 
									try {
											List<Escort> escortList = (List<Escort>)request.getAttribute("escorts");
												String escortClass = "";
												escortClass = "escortClass" + td.getTripTime().replace(":", "_");
												   escortClass = escortClass +td.getTripLog(); 
														
								%>
							<select class="<%=escortClass %>"  name="escort<%=td.getId()%>" id="escort_<%=serialNo%>" onchange="validateEscortChange('escort_<%=serialNo%>' )" >
									    <option value=""> Select Escort</option>
								    <%for(Escort esc : escortList) { 
								    	pageContext.setAttribute("escort", esc);
								    	String escortSelect = "";
								    	 if(td.getEscort()!=null){
								    	if(String.valueOf(esc.getId()).equals(td.getEscort().getId())) {
								    		escortSelect="selected";
								    	}
								    	 }	
								    %>
								    
								       <option <%=escortSelect %> value="<%=esc.getId()%>"><%=esc.getName()+"-"+ esc.getEscortClock()%> </option>
								   <% } %>
								</select>
								
								<%}catch(Exception e) { 
								 	System.out.println("Error "+ e);
								}%>
								
								
		
		  
							</td>
					 	
					 	<%-- <input type="button" class="formbutton" value="Set Security & SendSMS" onclick="sendTripSms('<%=td.getId() %>')" />	 --%>				 	
					 
					 
						
					
													
								</tr>
								
					
							</table>	
						</div>
					</td>
					
					 
				</tr>
				 
					
									

				<tr style="font-weight: bold; font-size: 26px; background-color: #E8E6DC">
					<td>#</td>
					<td>Name</td>
					<td>Contact</td>
					<td>Address</td>
				</tr>
						
				<%
					int i = 1;
				
				
				if(td.getTripDetailsChilds()!=null){
							for (TripDetailsChild tdc : td.getTripDetailsChilds()) {
				%>
				<tr>
					<td><%=i%></td>
					<td><%=tdc.getEmployee().getEmployeeFirstName() +" "+ tdc.getEmployee().getEmployeeLastName() %></td>
					<td><%=tdc.getEmployee().getContactNumber1()%></td>
					<td><%=tdc.getEmployee().getAddress()%></td>
					
				</tr>
				<%
					i++;
							}
				}			
				%>
				<tr>
				</tr>
				
				
				<tr>
					<td height='60px' colspan="7"> </td>
					 
				</tr>

				<%
				serialNo++;
					}
				}catch(Exception e)
				{e.printStackTrace();}
				%>
				<tr>
					<td colspan="7"> 
					<!-- <input style="margin-left: 3%" class="formbutton" type="button"
					value="Back"
					onclick="javascript:window.location = 'tripGeneration';" /> -->
					<input type="submit" style="margin-left: 60%"  class="formbutton" value="submit">
					</td>
				</tr> 
				</tbody>
			</table>
			
						</form>
			<br />


			<%
				}
				if (!flag) {
			%>
			<p>No trip Is is to display</p>
			<%
				}
	}catch(Exception e) {
		System.out.println("Error in vendorVehicleEntry.jsp : "+ e);
	}
	 
			%>

			<p>
			</p>
			<br /> <br />
			<hr />
			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
