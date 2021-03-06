<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.entities.Vendor"%>
<%@page import="com.agiledge.atom.entities.Vendormaster"%>
<%@page import="com.agiledge.atom.entities.Employee"%>
<%@page import="com.agiledge.atom.controller.VendorMasterController"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>


<script type="text/javascript">
          $(document).ready(function(){
        	 $("form[name=vendorForm]").hide();
        	 $("form[name=editVendor]").hide();
        	$("#showAddVendorDiv_a").click(showAddVendor);
        	$("#closeAddVendor_img").click(closeAddVendor);
        	 $(".editButton").click(showEditArea);
        	 $("#changePassword").click(showChangePassword);
        	 $(".deleteButton").click(deleteVendor);
        	 $("#closeVendorContract_img").click(closeVendorContract);
        	 $(".contractButton").click(showVendorContract);
        	
          });
          
          function deleteVendor()
          {
        	  var imageId= $(this).attr("id");
      		 var id= imageId.split("-")[1];
     		 $("form[name=deleteVendorFrom]").attr("action","deleteVendor");
     		 $("input[name=deleteid]").val(id);
     		 if(confirm("Do you really want to delete ?"))
     			 { 
     				$("form[name=deleteVendorFrom]").submit();
     				
     			 }
        	  
        	  
          }
          function showChangePassword()
          {
        	  if( $("#changePassword").is(":checked"))
        		  {
        		  $("#row-password").show();
        		  
        		  }
        	  else {
        		  $("#row-password").hide();
        		  $("#password").val("");
        	  }
        	  
          }
          
          function showAddVendor()
          {
        	  $("#vendor").val("");
        	//  $("form[name=editVendor]").attr("action","AddVendor");
        	  $("#windowTitle").text("Add Vendor");
        	  $("#showAddVendorDiv").hide();
        	  $("form[name=vendorForm]").show();
        	  $("#submitbtn").val("Add");
        	  $("#row-changePassword").hide();
        	  $("#row-password").show();
          }
          
          function clearVendorForm()
          {
        	  $("#id").val("");
        	  $("#name").val("");
        	  $("#address").val("");
        	  $("#contactNumber").val("");
        	  $("#email").val("");
        	  $("#loginId").val("");
        	  $("#password").val("");
        	  $("#changePassword").removeAttr("checked");
        	  
        	  
          }
          function closeAddVendor()
          {
        	  clearVendorForm();
        	  $("#showAddVendorDiv").show();
        	  $("form[name=vendorForm]").hide();
        	  $("form[name=vendorForm]").attr("action","#");
          }
          
          function showVendorContract()
          {
        	  var imageId= $(this).attr("id");
      		 var id= imageId.split("-")[1];
        	  $("form[name=vendorContractForm]").show();
        	  
        	  $("#contractDescription").val( $("#contractDescription-"+id).val());
        	 
        	  $("#contractRate").val( $("#contractRate-"+id).val());
        	  $("#vendor").val(id);
        	  $("#vendorNameInContract").val($("#name-"+ id).val());
        	   
          }
          function closeVendorContract()
          {
        	  clearVendorContractForm();
        	  
        	  $("form[name=vendorContractForm]").hide();
        	 // $("form[name=vendorContractForm]").attr("action","#");
        	  
          }
			function clearVendorContractForm()
			{
				$("#contractDescription").val("");
				$("#contractRate").val("");
				
			}
          function showEditArea()
          {
        	 try{ 
        		 
        		 var imageId= $(this).attr("id");
        		 var id= imageId.split("-")[1];
        		 
        		 
        	  $("form[name=vendorForm]").show();
        	  $("form[name=vendorForm]").attr("action","updateVendor");
        	  $("#windowTitle").text("Edit Vendor");
        	  $("#submitbtn").val("Update");
        	  
        	  $("input[name=Id]").val($(this).parent().parent().children().children(".Id").val());
        	  $("#vendor").val($(this).parent().parent().children().children(".vendor").val());
        
        	  $("#id").val(  $("#id-" + id).val());
        	  $("#name").val($("#name-" + id).val());
        	  $("#address").val($("#address-" + id).val( ));
        	  $("#contactNumber").val( $("#contactNumber-" + id).val( ));
        	  $("#email").val( $("#email-" + id).val( ));
        	  $("#loginId").val( $("#loginId-" + id).val( ));
        	  $("#password").val( $("#password-" + id).val( ));
        	  $("#company").val( $("#company-" + id).val( ));
        	  $("#changePassword").removeAttr("checked");
        	  $("#row-changePassword").show();
        	  $("#row-password").hide();
        	  $("#id").val(id);
        	 
        	 }catch(e)
        	 {
        		 alert(e);
        	 }
          }
          
          //-----validation
          
          
          function validateVendorContract()
          {
        	  // contractDescription, contractRate
        	  var flag=true;
        	  try{
        		  
        	if($("#contractDescription").val().trim()=="")
        	{
        		alert("Description should not be blank");
        		flag=false;
        	}else if($("#contractRate").val().trim()=="")
        	{
        		alert("Rate should not be blank");
        		flag=false;
        	}
        	else if( isNaN($("#contractRate").val().trim()))
    		{
    		alert("Contact Number should not be blank");
    		flag=false;
    		}
        	 
    	
        	
        	}catch (e) {
				
				alert(e.message);
				flag=false;
			}
        	return flag;   
        	  
          }
          
          
          function validateArea()
          {
        	  var flag=true;
        	  try{
        		  
        	if($("#name").val().trim()=="")
        	{
        		alert("Name should not be blank");
        		flag=false;
        	}else if($("#address").val().trim()=="")
        	{
        		alert("Address should not be blank");
        		flag=false;
        	}
        	else if($("#contactNumber").val().trim()=="")
    		{
    		alert("Contact Number should not be blank");
    		flag=false;
    		}
        	else if($("#email").val().trim()=="")
    		{
        		alert("Email address should not be blank");
        		flag=false;
        	}
        	else if($("#loginId").val().trim()=="")
    		{
        		alert("Login ID should not be blank");
        		flag=false;
        	}
        	else if($("#submitbtn").val()=="Update" )
        	{
        		 if( $("#changePassword").is(":checked"))
        			 {
        			 if($("#password").val().trim()=="")
        				 {
        				 alert("Password should not be blank.");
        				 flag=false;
        				 }
        			 }
        	}
        	else
        	{
        		  
    			 if($("#password").val().trim()=="")
    				 {
    				 alert("Password should not be blank");
    				 flag=false;
    				 }
    			 
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
  @SuppressWarnings("unchecked")
  List<Vendor> vendorlist =(List<Vendor>)request.getServletContext().getAttribute("vendorSetupList");
    @SuppressWarnings("unchecked")
    List<Vendormaster> vendormasterlist =(List<Vendormaster>)request.getServletContext().getAttribute("vendorMasterSetupList");
    System.err.println("vendormasterlist"+vendormasterlist);
    
    if(vendorlist== null || vendorlist.size() == 0){ %>
		No Vendor Found!.
<%	}
	else{	
%>
<h3>Vendor Setup</h3>
			<hr />
			<table>
				<tr>
					<td style="width: 70%; vertical-align: top;">
					<table>
						<thead>
							<tr>
									<th align="center">Id</th>
									<th align="center">Name</th>
									<th align="center">Login Id</th>
									<th align="center">Address</th>
									<th align="center">Contact Number</th>
									<th align="center">Email Address</th>
									<th align="center">Company</th>
									<th width="1%"></th>
								</tr>
							</thead>
							<%
							
                for(Vendor v: vendorlist)
                              {       
                                  %>
							<tr>

								<td align="center"><%=v.getId() %> <%-- <input type="hidden"
									id="contractDescription-<%=v.getId() %>"
									value="<%=dto.getContract().getDescription() %>" /> <input
									type="hidden" id="contractRate-<%=dto.getId() %>"
									value="<%=dto.getContract().getRate() %>" /> --%> <input
									type="hidden" value="<%=v.getId() %>"
									id="id-<%=v.getId() %>" /></td>
								<td align="center"><%=v.getName() %> <input type="hidden"
									value="<%=v.getName() %>" id="name-<%=v.getId() %>" /></td>
									<%-- <% Employee e=v.getEmployee(); String loginId=e.getLoginId(); %> --%>
								<td align="center"><%=v.getLoginId() %> <input
									type="hidden" value="<%=v.getLoginId()%>"
									id="loginId-<%=v.getId() %>" /></td>
								<td align="center"><%=v.getAddress() %> <input
									type="hidden" value="<%=v.getAddress() %>"
									id="address-<%=v.getId() %>" /></td>
								<td align="center"><%=v.getContactNumber() %> <input
									type="hidden" value="<%=v.getContactNumber() %>"
									id="contactNumber-<%=v.getId() %>" /></td>
								<td align="center"><%=v.getEmail() %> <input
									type="hidden" value="<%=v.getEmail() %>"
									id="email-<%=v.getId() %>" />									
									</td>
									<td>
									<%=v.getVendormaster().getCompany() %>
									<input type="hidden" value="<%=v.getVendormaster().getId() %>" id="company-<%=v.getId() %>" />
									</td>
								<td>
									<table>
										<tr>
											<td style="border-bottom: 0px solid #cE5; padding: 0px;">
												<img src="images/edit.png" class="editButton"
												id="editImg-<%=v.getId() %>" title="Edit" />
											</td>
											<td style="border-bottom: 0px solid #cE5; padding: 0px;">
												<img src="images/delete.png" class="deleteButton"
												id="deleteImg-<%=v.getId() %>" title="Delete" />

											</td>
											<%-- <td style="border-bottom: 0px solid #cE5; padding: 0px;">
												<img src="images/contract.jpg" class="contractButton"
												id="contractImg-<%=v.getId() %>" title="Contract" />

											</td> --%>
											<td align="center"><input type="button"
												class="formbutton"
												onclick="showAuditLog(<%=v.getId()%>,'<%=AuditLogConstants.VENDOR_MODULE%>');"
												value="Audit Log" /></td>

										</tr>
									</table>



								</td>
							</tr>
							<%
}   }     %>
						</table>


					</td>
					<td style="width: 30%; vertical-align: top;">
						<div id="showAddVendorDiv" style="padding-top: 3px;">
							<input type="button" class="formbutton" id="showAddVendorDiv_a"
								title="sdfsdf" value="Add  Vendor" />

						</div>
						<form name="deleteVendorFrom" action="deleteVendor" method="POST">
							<input type="hidden" name="deleteid" />
						</form>
						<form name="vendorContractForm" style="display: none;"
							action="VendorContract" method="POST"
							onsubmit="return validateVendorContract();">
							<table style="border-style: outset; width: 20%;">
								<thead>
									<tr>
										<th colspan="2"><label> Vendor Contract</label>
											<div style="float: right;" id="closeVendorContract">
												<img id="closeVendorContract_img" style="float: right;"
													src="images/close.png" title="Close" />
											</div></th>
									</tr>
								</thead>
								<tr>
									<td>Vendor</td>
									<td><input type="text" readonly="readonly"
										id="vendorNameInContract" /></td>

								</tr>
								<tr>
									<td>Contract Description</td>
									<td><input type="text" id="contractDescription"
										name="contractDescription" /></td>

								</tr>
								<tr>
									<td>Rate / Trip</td>
									<td><input type="text" id="contractRate"
										name="contractRate" /></td>

								</tr>
								<tr>
									<td align="center"></td>
									<td align="center"><input type="submit" class="formbutton"
										value="Update"> <input type="hidden" id="vendor"
										name="vendor" /></td>
								</tr>
							</table>
						</form>
						<form name="vendorForm" action="addVendor" method="POST"
							onsubmit="return validateArea();">
							<table style="border-style: outset; width: 20%;">
								<thead>
									<tr>
										<th colspan="2"><label id="windowTitle">Add
												Vendor</label>
											<div style="float: right;" id="closeAddVendor">
												<img id="closeAddVendor_img" style="float: right;"
													id="closeAddVendor" src="images/close.png" title="Close" />
											</div></th>
									</tr>
								</thead>
								<tr>
									<td align="center">Name</td>
									<td align="center"><input type="text" name="name"
										id="name" /> <input type="hidden" id="id" name="id" /></td>
								</tr>
								<tr>
									<td align="center">Address</td>
									<td align="center"><input type="text" name="address"
										id="address" /></td>
								</tr>
								<tr>
									<td align="center">Contact Number</td>
									<td align="center"><input type="text" name="contactNumber"
										id="contactNumber" /></td>
								</tr>
								<tr>
									<td align="center">Email</td>
									<td align="center"><input type="text" name="email"
										id="email" /></td>
								</tr>
								<tr>
									<td align="center">Login ID</td>
									<td align="center"><input type="text" name="loginId"
										id="loginId" /></td>
								</tr>
								<tr id="row-mastervendor">
									<td align="center">Company</td>
									<td><select name="company" id="company">
									<%
									for(Vendormaster vm: vendormasterlist){
										%>
										<option value="<%=vm.getId()%>"><%=vm.getCompany()%></option>
									<%}
									%>
									
									</select></td>
								</tr>
								<tr id="row-changePassword">
									<td align="center"></td>
									<td align="center"><input type="checkbox"
										name="changePassword" id="changePassword" value="true" />
										Change Password</td>
								</tr>
								<tr id="row-password">
									<td align="center">Password</td>
									<td align="center"><input type="password" name="password"
										id="password" /></td>
								</tr>
								<tr>
									<td align="center">&nbsp;</td>
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
	</div>
	</div>

</body>
</html> 