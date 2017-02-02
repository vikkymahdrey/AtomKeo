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
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>

<script type="text/javascript">
          $(document).ready(function(){
        	 $("form[name=area]").hide();
        	 $("form[name=editArea]").hide();
        	$("#showAddAreaDiv_a").click(showAddArea);
        	$("#closeAddArea_img").click(closeAddArea);
        	 $(".editButton").click(showEditArea);
        	 $("form[name=area]").draggable();
        	 
        	//$("#submitbtn").click(addAreaAjax);
        	
          });
          
          function addAreaAjax(){
        	  var area = $("input[name=area]").val().trim();
        	  var location = $("input[name=location]").val().trim();
          		 $.ajax({
                       url: 'addArea',
                       type: "POST",
                       data: 'area='+area+'&location='+location,
                       success: function (data) {
                       alert(data);
                    	   //$(".success").html(data);
                           	
                           },
          		 error: function(e){
          	     			        alert('Error: ' + e);
          	      }

                          
                       });
          	
          }
          
          function aplUpload() {
        	  if( isNaN($("select[name=branchId]").val() )) {
        		  alert('Please select location');
        	  } else { 
        		window.location.href="aplUpload?branchId=" +$("select[name=branchId]").val(); 
        	  }
          }
          
          function fixAPL() {
        	  window.open("marklandmark?location="+$("select[name=branchId]").val());
          }
          
          function showAddArea()
          {
        	  $("#area").val("");
        	  $("form[name=editArea]").attr("action","addArea");
        	  $("#windowTitle").text("Add Area");
        	  $("#showAddAreaDiv").hide();
        	  $("form[name=area]").show();
        	  $("#submitbtn").val("Add");
          }
          function closeAddArea()
          {
        	  $("#showAddAreaDiv").show();
        	  $("form[name=area]").hide();
        	  $("form[name=area]").attr("action","#");
          }

          function showEditArea()
          {
        	 try{ 
        	  $("form[name=area]").show();
        	  $("form[name=area]").attr("action","updateArea");
        	  $("#windowTitle").text("Edit Area");
        	  $("#submitbtn").val("Update");
        	  
        	  $("input[name=areaId]").val($(this).parent().parent().children().children(".areaId").val());
        	  $("#area").val($(this).parent().parent().children().children(".area").val());
        	 
        	 }catch(e)
        	 {
        		 alert(e);
        	 }
          }
          
          //-----validation
          
          function validateArea()
          {
        	  var flag=true;
        	  var area = $("input[name=area]").val().trim();
        	  var location = $("input[name=location]").val().trim();
        		  try{
        	if(area=="")
        	{
        		alert("Area should not be blank")
        		flag=false;
        	} else if (/^[A-Za-z0-9]+[A-Za-z0-9&\s\.\,\/\-\_:\(\)\[\]]*$/.test(area)==false) {
        		alert("Area includes invalid charectors");
        		flag=false;
        	}
        	}catch (e) {
				
				alert(e.message);
				flag=false;
			}
        	
        	return flag;   
          }
          function showAuditLog(relatedId,moduleName){
      		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
      		var size = "height=450,width=900,top=200,left=300," + params;
      		var url="showAuditLog?relatedNodeId="+relatedId+"&moduleName="+moduleName;	
      	    newwindow = window.open(url, 'AuditLog', size);

      		if (window.focus) {
      			newwindow.focus();
      		}
      	}
          function submitForm()
          {
        	 document.getElementById("branchForm").submit(); 
          }
          </script>
<title>Area</title>

</head>
<body>
	<div class="content">
		<div class="content_resize">
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
			
				String location = request.getParameter("branchId");
			 if(location==null){
				location="";
			}  
			
				List<Branch> branchList=(List<Branch>)request.getAttribute("branches");
					%>
			<form action="aplConfig" name="branchForm" id="branchForm">
				<table>
					<tr>
						<td>Location</td>
						<td><select name="branchId" onchange="submitForm()">
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
						  <a onclick="fixAPL()" > Fix APL</a>&nbsp;|&nbsp;<a onclick="aplUpload()" > Upload APL</a>
						</td>
					</tr>
				</table>
			</form>
			<%
				if (location != null  && !(location.isEmpty())) {
					@SuppressWarnings("unchecked")
					List<Area> areaList=(List<Area>)request.getAttribute("areaList");
					if(areaList!=null && areaList.size()>0){
					System.out.println("inside if condition of areaList");
					
			%>
			<h3>Area List</h3>
			<hr />
			<p align="center">
				<a href="aplDownload?location=<%=location%>">Download All APL</a>
			</p>
			<table style="width: 50%;float: left">
				<tr>
					<td style="width: 70%; vertical-align: top;">



						<table>

							<thead>

								<tr>
									<th align="center">Id</th>
									<th align="center">Area</th>
									<th width="1%"></th>
									<th align="center">Audit Log</th>
								</tr>
							</thead>
							<%
								for (Area area : areaList) {
									
							%>
							<tr>
								<td align="center"><%=area.getId()%></td>
								<td align="center"><a
									href="showPlace?areaId=<%=area.getId()%>"><%=area.getArea()%></a>
									<input type="hidden" value="<%=area.getId()%>"
									class="areaId" /> <input type="hidden"
									value="<%=area.getArea()%>" class="area" /></td>
								<td><img src="images/edit.png" class="editButton"
									title="Edit" /></td>
								<td align="center"><input type="button" align="middle"
									class="formbutton"
									onclick="showAuditLog(<%=area.getId()%>,'<%=AuditLogConstants.APL_MODULE%>');"
									value="Audit Log" /></td>
							</tr>
							<%
								}
							%>
						</table>


					</td>			
					<td style="width: 30%; vertical-align: top;">
						<div id="showAddAreaDiv" style="padding-top: 3px;">
							<input type="button" class="formbutton" id="showAddAreaDiv_a"
								value="Add  Area" />

						</div>
						<form name="area" id="addAreaForm" action="addArea"
							onsubmit="return validateArea();">
							<table style="border-style: outset; width: 20%;">
								<thead>
									<tr>
										<th colspan="2"><label id="windowTitle">Add Area</label>
											<div style="float: right;" id="closeAddArea">
												<img id="closeAddArea_img" style="float: right;"
													id="closeAddArea" src="images/close.png" title="Close" />
											</div></th>
									</tr>
								</thead>
								<tr>
									<td align="center">Area</td>
									<td align="center"><input type="text" name="area"
										id="area" /> <input type="hidden" name="areaId" /></td>
								</tr>
								<tr>
									<td align="center">&nbsp;
									<input type="hidden" name="location" value="<%=location%>" />
									</td>
									<td align="center"><input type="submit" class="formbutton"
										value="Add" name="submitbtn" id="submitbtn" /></td>
								</tr>
								<tr>
									<td align="center"></td>
								</tr>
							</table>
						</form>
					</td>					
				</tr>
			</table>
			<iframe  style="float: right; width: 48%" height="500px" src="marklandmark?location=<%=location%>" ></iframe>
			<%
				}
					
				 }
			%>
		</div>
	</div>
</body>
</html>
