<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adhoc Setup Edit</title>
</head>
<body>

	<script type="text/javascript">
		$(document).ready(
				function() {

					if ($("#approval").val() == "no") {
						$("#maxpendingrequetstr").hide();
						$("#approverwhotr").hide();
						$("#maxpendingrequetstrdrop").hide();
					} 
					/*
					$("#approvereqtr").hide();
					$("#requestcutofftr").hide();
					$("#cancelcutofftr").hide();
					$("#cancelModetr").hide();
					$("#maxrequetstr").hide();
					$("#whoalltr").hide();
					$("#approverwhotr").hide();
					$("#submittr").hide();
					 */
					
					$("#adhoctype").change(
							function() {
								if ($("#siteId").val() == null
										|| $("#siteId").val() == "") {
									$("#adhoctype").val("");
									alert("Please Select Site");
								} else if ($("#adhoctype").val() == null
										|| $("#adhoctype").val() == "") {
									$("#adhoctype").val("");
									alert("Please select Adhoc type");
								} else {
									$("#adhocIdSet").attr("action",
											"adhocSetupEdit");
									$('#adhocIdSet').attr('onsubmit',
											'return 1');
									$("#adhocIdSet").submit();
								}
							});
						$("#siteId").change(function() {
							$("#adhoctype").val("");
							$("#approvereqtr").hide();
							$("#requestcutofftr").hide();
							$("#cancelcutofftr").hide();
							$("#cancelModetr").hide();
							$("#maxrequetstr").hide();
							$("#maxpendingrequetstr").hide();
							$("#requestertr").hide();
							$("#approverwhotr").hide();
							$("#pickupDrop").hide();
							$("#submittr").hide();
							$("#pickuptr").hide();
							$("#droptr").hide();
							$("#requestcutofftrdrop").hide();
							
							$("#cancelcutofftrdrop").hide();
							$("#cancelModetrdrop").hide();
							$("#maxrequetstrdrop").hide();
							$("#maxpendingrequetstrdrop").hide();
						});
					/*
							$("#approval").val("no");
							$("#approvereqtr").hide();
							$("#requestcutofftr").hide();
							$("#cancelcutofftr").hide();
							$("#cancelModetr").hide();
							$("#maxrequetstr").hide();
							$("#approverwhotr").hide();
							$("#whoalltr").hide();
							$("#submittr").hide();

							if ($("#adhoctype").val() == "shiftExtension") {
								$("#approvereqtr").show();
								$("#requestcutofftr").show();
								$("#cancelcutofftr").show();
								$("#cancelModetr").show();
								$("#maxrequetstr").show();
								$("#whoalltr").show();
								$("#approverwhotr").hide();
								$("#submittr").show();
							} else if ($("#adhoctype").val() == "hotelAirport") {
								$("#approvereqtr").show();
								$("#requestcutofftr").show();
								$("#cancelcutofftr").show();
								$("#whoalltr").show();
								$("#submittr").show();
							} else if ($("#adhoctype").val() == "atDisposal") {
								$("#approvereqtr").show();
								$("#requestcutofftr").show();
								$("#cancelcutofftr").show();
								$("#whoalltr").show();
								$("#submittr").show();
							}
						});
					 */
					$("#approval").change(function() {
						if ($("#approval").val() == "yes") {
							$("#approverwhotr").show();
							$("#maxpendingrequetstr").show();
						} else {
							$("#approverwhotr").hide();
							$("#maxpendingrequetstr").hide();

						}
					});

				});
				function validateForm() {
					var regexp = /([01][0-9]|[02][0-4]):[0-5][0-9]/;
					var numregexp = /[0-9]+$/;
					if ($("#requesters").val() == null || $("#requesters").val() == "") {
						alert("Select Requester");
						return false;
					} else if ($("#approval").val() == "yes"
							&& ($("#approvers").val() == null || $("#approvers").val() == "")) {
						alert("Select Approvar");
						return false;
					} else if ($("#bookCutoff").val() == "" && $("#bookCutoffdrop").val == "") {
						alert("Enter Cut off time for Booking");
						return false;

					}

					else if ((!regexp.test($("#bookCutoff").val()) && (!regexp.test($("#bookCutoffdrop").val())))) {
						alert("Please enter book cut off time in correct format");
						return false;

					} else if ($("#cancelCutoff").val() == "" && $("#cancelCutoffdrop").val() == "" ) {
						alert("Enter Cut off time for Booking cancel");
						return false;

					}
					/*else if ($("#adhoctype").val() == "shiftExtension" && $("#cancelMode").val() == "") {
						alert("Existing Schedule Cancel Mode");				
					}*/
					else if ((!regexp.test($("#cancelCutoff").val())) && (!regexp.test($("#cancelCutoffdrop").val()))) {
						alert("Please enter cancellation cut off time in correct format");
						return false;

					} else if ($("#adhoctype").val() == "shiftExtension"
							&& ($("#existingCancel").val() == "" && $("#existingCanceldrop").val() == "" )) {
						alert("Enter existing  booking cancel Time");
						return false;
					} else if ($("#adhoctype").val() == "shiftExtension"
							&& ((!regexp.test($("#existingCancel").val())) && (!regexp.test($("#existingCanceldrop").val()))) ){
						alert("Please enter existing booking cancellation time in correct format");
						return false;

					} else if ($("#adhoctype").val() == "shiftExtension"
							&& (($("#maxrequest").val() == "") && ($("#maxrequestdrop").val() == ""))){
						alert("Enter maximum number of request per day for pick up/drop");
						return false;

					}
					/* else if((!numregexp.test($("#maxrequest").val()))&& (!numregexp.test($("#maxrequestdrop").val()))){
						alert("Enter valid maximum number of request per day for pickup/drop 1");
						return false
					} */
					else if (($("#adhoctype").val() == "shiftExtension")
							&& ( (($("#maxrequest").val() == "0") || ($("#maxrequestdrop").val() == "0" )) || ((!numregexp.test($("#maxrequest").val())) && (!numregexp.test($("#maxrequestdrop").val()))))) {

						alert("Enter valid maximum number of request per day for pickup/drop");
						return false;

					}
				
					
					
					

					else if (($("#adhoctype").val() == "shiftExtension"&& $("#approval").val() == "yes")
						&& (($("#maxpendingrequest").val() == "") && ($("#maxpendingrequestdrop").val() == ""))){
					alert("Enter maximum number of pending request per day for pick up/drop");
					return false;

				}
					else if (($("#adhoctype").val() == "shiftExtension" && $("#approval").val() == "yes")
							&& ( (($("#maxpendingrequest").val() == "0") || ($("#maxpendingrequestdrop").val() == "0" )) || ((!numregexp.test($("#maxpendingrequest").val())) && (!numregexp.test($("#maxpendingrequestdrop").val()))))) {

						alert("Enter valid maximum number of pending request per day for pickup/drop");
						return false;

					}
				
				else if ((!($("#adhoctype").val() == "shiftExtension") && ($("#approval").val() == "yes"))
							&& (($("#maxpendingrequest").val() == "") || (($(
									"#maxpendingrequest").val() == "0") || (!numregexp.test($("#maxpendingrequest").val()))))) {
						alert("Enter valid maximum number of pending request");
						return false;
					} else {
						return true;
					}
				}
		 function validateForm() {
			var regexp = /([01][0-9]|[02][0-4]):[0-5][0-9]/;
			var numregexp = /^[0-9]+$/;
			var adhoct = $.trim($("#adhoctype").val());
			if ($("#requesters").val() == null || $("#requesters").val() == "") {
				alert("Select Requester");
				return false;
			} else if ($("#approval").val() == "yes"
					&& ($("#approvers").val() == null || $("#approvers").val() == "")) {
				alert("Select Approvar");
				return false;
			} else if ($("#bookCutoff").val() == "") {
				alert("Enter Cut off time for Booking");
				return false;

			} else if (!regexp.test($("#bookCutoff").val())) {
				alert("Please enter booking cut off time in correct format");
				return false;

			} else if ($("#cancelCutoff").val() == "") {
				alert("Enter Cut off time for Booking cancel");
				return false;

			} else if (!regexp.test($("#cancelCutoff").val())) {
				alert("Please enter cancellation cut off time in correct format");
				return false;

			}
			else if ($("#adhoctype").val() == "shiftExtension"
					&& $("#existingCancel").val() == "") {
				alert("Enter existing  booking cancel Time");
				return false;
			} else if ($("#adhoctype").val() == "shiftExtension"
					&& (!regexp.test($("#existingCancel").val()))) {
				alert("Please enter existing booking cancellation time in correct format");
				return false;

			} else if ($("#adhoctype").val() == "shiftExtension"
					&& $("#maxrequest").val() == "") {
				alert("Enter maximum number of request per day");
				return false;
			}

			else if (adhoct == "shiftExtension"
					&& ($("#maxrequest").val() == ""
							|| $("#maxrequest").val() == "0" || (!numregexp
							.test($("#maxrequest").val())))) {

				alert("Enter valid maximum number of request per day ");
				return false;

			} else if ($("#approval").val() == "yes"
					&& ($("#maxpendingrequest").val() == "" || $(
							"#maxpendingrequest").val() == "0")) {
				alert("Enter valid maximum number of pending request");
				return false;
			} else {
				return true;
			}
		} 
	</script>

	<%@include file="Header.jsp"%>
	<div id="body">
		<div class="content">
			<h3>Modify Adhoc Setup</h3>

			<%
			String adhoctype = request.getParameter("adhoctype");
			String siteId = request.getParameter("siteId");
			String projectUnit = request.getParameter("projectUnit");
			
			Employee userLoggedIn = (Employee) session.getAttribute("userLoggedIn");	
			if (userLoggedIn == null) {
					String param = request.getServletPath().substring(1) + "___"
							+ request.getQueryString();
					response.sendRedirect("/?page=" + param);
				} else {
					// empid = Long.parseLong(employeeId); 
						 }
			
			
				List<Settingsstring> adhoctypeList=(List<Settingsstring>)request.getAttribute("settingsStr");
				List<Adhoctype> adhocdetailslist=null;
				List<Site> sites=(List<Site>)request.getAttribute("sites");
				List<String> atsList=(List<String>)request.getAttribute("atsList");
				               								
				if (adhoctype != null && adhoctype != "") {
					adhocdetailslist=(List<Adhoctype>)request.getAttribute("adhocdetailslist");
									
					 if (adhocdetailslist.size()==0) {
						response.sendRedirect("adhocSetup?adhoctype="
								+ adhoctype + "&siteId=" + siteId + "&projectUnit="
								+ projectUnit);
					} 
				} 
			%>
			<form id="adhocIdSet" action="AdhocSetup" method="post"
				onsubmit="return validateForm()">
				<table>
					<tr>
						<td align="center">Site</td>
						<td>&nbsp;</td>
						<td align="left"><select name="siteId" id="siteId">
								<option value="">Select</option>
								<%
								for (Site site : sites) {
									if (String.valueOf(site.getId()).equalsIgnoreCase(siteId)) {
							%>
							<option selected="selected" value=<%=site.getId()%>><%=site.getSiteName()%></option>
							<%
								} else {
							%>
							<option value=<%=site.getId()%>><%=site.getSiteName()%></option>
							<%
								}
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td align="center"><%=SettingsConstant.PROJECT_UNIT_TERM%></td>
						<td>&nbsp;</td>
						<td align="left" ><select name="projectUnit" id="projectUnit">
								<option value="all">All</option>
								<%
									for (String ats : atsList) {
										if (ats.equalsIgnoreCase(projectUnit)) {
								%>
								<option selected="selected"
									value="<%=ats%>" ><%=ats%></option>
								<%
									} else {
								%>
								<option value="<%=ats%>"><%=ats%></option>
								<%
									}
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td align="center">Adhoc Type :</td>
						<td>&nbsp;</td>
						<td align="left"><select name="adhoctype" id="adhoctype">
								<option value="">Select</option>
								<%
									String isSetVal = "";
								    long flag = 0;
									for (Settingsstring ss : adhoctypeList) {
									
										
										if (flag == ss.getId())
										{
											continue;
										}
										if (ss.getKeyVal().equalsIgnoreCase(adhoctype)) {
											
									
								%>
								
								 <option selected="selected" value="<%=ss.getKeyVal()%>"><%=ss.getVal()%></option>

								<%
									} else if
									(!ss.getKeyVal().equalsIgnoreCase(adhoctype)) {
										
								%>
								 <option value="<%=ss.getKeyVal()%>"><%=ss.getVal()%></option> 
								<%
									 } 
										flag = ss.getId();
									}
								%>
						</select></td>
					</tr>
				</table>
				<table>
				<%
				if(adhocdetailslist != null)
				{
				for(Adhoctype adhTyp : adhocdetailslist)
				{
					%>
					<% if (adhTyp != null && adhTyp.getType().equalsIgnoreCase(SettingsConstant.SHIFT_EXTENSTION)) {
						
				%>
				<%if (adhTyp.getPickdrop().equalsIgnoreCase("pick up")) {%>
			
					<tr id="requestertr">
						<td><input type="hidden" name="adhoctype"
							value="<%=adhTyp.getType()%>" /> <input type="hidden"
							id="adhocTypeId" name="adhocTypeId" value="<%=adhTyp.getId()%>" />
							Who raise the request</td>
						<td><select name="requesters" id="requesters"
							multiple="multiple">
								<%
									for (Adhocrequester ar : adhTyp.getAdhocrequesters()) {
								%>
								<option  value="<%=ar.getId()%>"><%=ar.getRole().getName()%></option>
								<%
									}
								%>

						</select></td>
					</tr>
					<tr id="approvereqtr">
						<td>Approval required or Not</td>
						<td colspan="2"><select name="approval" id="approval">
								<%
									if (adhTyp.getApproval().equalsIgnoreCase("yes")) {
								%>
								<option value="yes" selected>YES</option>
								<option value="no">NO</option>
								<%
									} else {
								%>
								<option value="yes">YES</option>
								<option value="no" selected>NO</option>
								<%
									}
								%>

						</select></td>
					</tr>

					<tr id="approverwhotr">
						<td>Who All</td>
						<td colspan="2"><select multiple="multiple" name="approvers"
							id="approvers">

								<%
									for (Adhocapprovar aa : adhTyp.getAdhocapprovars()) {
								%>
								<option  value="<%=aa.getId()%>"><%=aa.getRole().getName()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
                                   
                     
                     <tr id = "pickuptr"><td><h2>Pick Up</h2></td></tr>
                   
					<tr id="requestcutofftr">
						<td>Cut off time for raising request</td>
						<td colspan="2"><input type="text" name="bookCutoff"
							id="bookCutoff" value="<%=adhTyp.getRequestCutoff()%>" />hh:mm</td>
					</tr>
					<tr id="cancelcutofftr">
						<td>Cut off time for canceling the booking</td>
						<td colspan="2"><input type="text" name="cancelCutoff"
							id="cancelCutoff" value="<%=adhTyp.getCancelCutoff()%>" />hh:mm</td>
					</tr>
					<%
						if (adhTyp.getType().equalsIgnoreCase(SettingsConstant.SHIFT_EXTENSTION)) {
					%>
					<tr id="cancelModetr">
						<td>Cancellation mode of existing booking</td>
						<td><select name="cancelMode" id="cancelMode">
								<%
									if (adhTyp.getScheduleCancelMode().equalsIgnoreCase("automatic")) {
								%>
								<option selected="selected" value="automatic">Automatic</option>
								<option value="manual">Manual</option>
								<%
									} else {
								%>
								<option value="automatic">Automatic</option>
								<option selected="selected" value="manual">Manual</option>
								<%
									}
								%>
						</select></td>
						<td>Cut off Time to Cancel<input type="text"
							name="existingCancel" id="existingCancel"
							value="<%=adhTyp.getExistingCancel()%>" />hh:mm
						</td>
					</tr>
					<tr id="maxrequetstr">
						<td>Maximum number of request per day</td>
						<td><input type="text" name="maxrequest" id="maxrequest"
							value="<%=adhTyp.getMaxrequestPerDay()%>" /></td>
					</tr>
					<%
						}
					%>
					<tr id="maxpendingrequetstr">
						<td>Maximum number of pending request</td>
						<td><input type="text" name="maxpendingrequest"
							id="maxpendingrequest"
							value="<%=adhTyp.getMaxrequestWithOutApproval()%>" /></td>
					</tr>
					<%} %>
					<%if(adhTyp.getPickdrop().equalsIgnoreCase("drop")) {%>
					<tr id ="droptr"><td><h2>Drop</h2></td></tr>
					<tr id="requestcutofftrdrop">
						<td>Cut off time for raising request</td>
						<td colspan="2"><input type="text" name="bookCutoffdrop"
							id="bookCutoffdrop" value="<%=adhTyp.getRequestCutoff()%>" />hh:mm</td>
					</tr>
					<tr id="cancelcutofftrdrop">
						<td>Cut off time for canceling the booking</td>
						<td colspan="2"><input type="text" name="cancelCutoffdrop"
							id="cancelCutoffdrop" value="<%=adhTyp.getCancelCutoff()%>" />hh:mm</td>
					</tr>
					<%
						if (adhTyp.getType().equalsIgnoreCase(
									SettingsConstant.SHIFT_EXTENSTION)) {
					%>
					<tr id="cancelModetrdrop">
						<td>Cancellation mode of existing booking</td>
						<td><select name="cancelModedrop" id="cancelModedrop">
								<%
									if (adhTyp.getScheduleCancelMode().equalsIgnoreCase("automatic")) {
								%>
								<option selected="selected" value="automatic">Automatic</option>
								<option value="manual">Manual</option>
								<%
									} else {
								%>
								<option value="automatic">Automatic</option>
								<option selected="selected" value="manual">Manual</option>
								<%
									}
								%>
						</select></td>
						<td>Cut off Time to Cancel<input type="text"
							name="existingCanceldrop" id="existingCanceldrop"
							value="<%=adhTyp.getExistingCancel()%>" />hh:mm
						</td>
					</tr>
					<tr id="maxrequetstrdrop">
						<td>Maximum number of request per day</td>
						<td><input type="text" name="maxrequestdrop" id="maxrequestdrop"
							value="<%=adhTyp.getMaxrequestPerDay()%>" /></td>
					</tr>
					<%
						}
					%>
					<tr id="maxpendingrequetstrdrop">
						<td>Maximum number of pending request</td>
						<td><input type="text" name="maxpendingrequestdrop"
							id="maxpendingrequestdrop"
							value="<%=adhTyp.getMaxrequestWithOutApproval()%>" /></td>
					</tr>
					<tr id="submittr">
						<td></td>
						<td><input type="submit" value="Update" class="formbutton" /></td>
					</tr>
					<%}}
					else{
					%>
							
					
					<tr id="requestertr">
					<td><input type="hidden" name="adhoctype"
					value="<%=adhTyp.getType()%>" /> <input type="hidden"
					id="adhocTypeId" name="adhocTypeId" value="<%=adhTyp.getId()%>" />
					Who raise the request</td>
				<td><select name="requesters" id="requesters"
					multiple="multiple">
						<%
							for (Adhocrequester ar : adhTyp.getAdhocrequesters()) {
						%>
						<option  value="<%=ar.getId()%>"><%=ar.getRole().getId()%></option>
						<%
							}
						%>

				</select></td>
			</tr>
			<tr id="approvereqtr">
				<td>Approval required or Not</td>
				<td colspan="2"><select name="approval" id="approval">
						<%
							if (adhTyp.getApproval().equalsIgnoreCase("yes")) {
						%>
						<option value="yes" selected>YES</option>
						<option value="no">NO</option>
						<%
							} else {
						%>
						<option value="yes">YES</option>
						<option value="no" selected>NO</option>
						<%
							}
						%>

				</select></td>
			</tr>

			<tr id="approverwhotr">
				<td>Who All</td>
				<td colspan="2"><select multiple="multiple" name="approvers"
					id="approvers">

						<%
							for (Adhocapprovar aa : adhTyp.getAdhocapprovars()) {
						%>
						<option  value="<%=aa.getId()%>"><%=aa.getRole().getName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
     
           
			<tr id="requestcutofftr">
				<td>Cut off time for raising request</td>
				<td colspan="2"><input type="text" name="bookCutoff"
					id="bookCutoff" value="<%=adhTyp.getRequestCutoff()%>" />hh:mm</td>
			</tr>
			<tr id="cancelcutofftr">
				<td>Cut off time for canceling the booking</td>
				<td colspan="2"><input type="text" name="cancelCutoff"
					id="cancelCutoff" value="<%=adhTyp.getCancelCutoff()%>" />hh:mm</td>
			</tr>
			<%
				if (adhTyp.getType().equalsIgnoreCase(
							SettingsConstant.SHIFT_EXTENSTION)) {
			%>
			<tr id="cancelModetr">
				<td>Cancellation mode of existing booking</td>
				<td><select name="cancelMode" id="cancelMode">
						<%
							if (adhTyp.getScheduleCancelMode().equalsIgnoreCase("automatic")) {
						%>
						<option selected="selected" value="automatic">Automatic</option>
						<option value="manual">Manual</option>
						<%
							} else {
						%>
						<option value="automatic">Automatic</option>
						<option selected="selected" value="manual">Manual</option>
						<%
							}
						%>
				</select></td>
				<td>Cut off Time to Cancel<input type="text"
					name="existingCancel" id="existingCancel"
					value="<%=adhTyp.getExistingCancel()%>" />hh:mm
				</td>
			</tr>
			<tr id="maxrequetstr">
				<td>Maximum number of request per day</td>
				<td><input type="text" name="maxrequest" id="maxrequest"
					value="<%=adhTyp.getMaxrequestPerDay()%>" /></td>
			</tr>
			<%
				}
			%>
			<tr id="maxpendingrequetstr">
				<td>Maximum number of pending request</td>
				<td><input type="text" name="maxpendingrequest"
					id="maxpendingrequest"
					value="<%=adhTyp.getMaxrequestWithOutApproval()%>" /></td>
			</tr>
			<tr id="submittr">
						<td></td>
						<td><input type="submit" value="Update" class="formbutton" /></td>
					</tr>
			<%} %>
			<%
					}} %>
					
				</table>
				
			</form>

			<%@include file="Footer.jsp"%>
		</div>
	</div>

</body>
</html>