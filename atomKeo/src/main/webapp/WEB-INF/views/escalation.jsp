<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.agiledge.atom.config.files.OtherFunctions" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-2.2.0.js" ></script>
<title>Escalation Matrix</title>
</head>
<body>
<script type="text/javascript">
$( document ).ready(function() {
  for(var i=1;i<11;i++){
	  if($('#admintime'+i).val()!=""){
	  $('#level'+i+'Atime').val($('#admintime'+i).val());
	  }
	  if($('#vendortime'+i).val()!=""){
	  $('#level'+i+'Vtime').val($('#vendortime'+i).val());
	  }
	  
  }
});
	function showPopup(url) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		var size = "height=450,width=700,top=200,left=300," + params;		
		newwindow = window.open(url, 'name', size);
		if (window.focus) {
			newwindow.focus();
		}
	}
</script>


<%@include file="Header.jsp"%>
<hr>
<div id="body">
		<div class="content">
		<form name="escalationForm" action="EscalationMatrix" method="post">
<table>
<%int i=1; %>
<c:forEach var="esc" items="${list}" >
<tr>
<th colspan="4">${esc.level}</th>
</tr>
<tr>
<td></td>
<td><b>1st Person</b></td>
<td><b>2nd person</b></td>

</tr>
<tr>
<td><b>Name</b></td>
<td><input type='hidden' name="Level<%=i%>AID" value="${esc.empid}" id="level<%=i%>AID" ><input type='text' value="${esc.employeename}"  readonly name="level<%=i%>Aname" id="level<%=i%>Aname" onclick="showPopup('escalationSearch?param=level<%=i%>A')"></td>
 <td><input type='hidden'  name="Level<%=i%>VID" value="${esc.vendorempid }" id="level<%=i%>VID"><input type='text' value="${esc.vendoremployeename}" readonly name="level<%=i%>Vname" id="level<%=i%>Vname" onclick="showPopup('escalationSearch?param=level<%=i%>V')" ></td>
 
</tr>
<tr>
<td><b>Time Gap(mns)</b></td>
<td><select name="Level<%=i %>Atime" id="level<%=i%>Atime"  ><%=OtherFunctions.FullminutesOptions()%></select></td> 
<td><select name="Level<%=i %>Vtime" id="level<%=i%>Vtime" ><%=OtherFunctions.FullminutesOptions()%></select>
<input type='hidden' value='${esc.emptimeslot}' id='admintime<%=i %>' />
<input type='hidden' value='${esc.vendortime}' id='vendortime<%=i %>' /></td>

</tr>
<%i++; %>
</c:forEach>
<tr><td></td><td colspan="2"><input type="submit" value="Add" class="formbutton">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" class="formbutton"></td><td></td></tr>
</table>
</form>
</div>
</div>
<%@include file="Footer.jsp"%>
</body>

</html>