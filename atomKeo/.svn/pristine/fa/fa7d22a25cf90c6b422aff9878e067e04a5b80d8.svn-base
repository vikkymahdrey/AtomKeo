<%@ page import="java.util.*" %>
<%@page import="com.agiledge.atom.entities.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script type="text/javascript">
function deleteSubview(deleteid,viewId,viewName){
	
	 $("input[name=deleteId]").val(deleteid);
	 $("input[name=deleteviewid]").val(viewId);
	 $("input[name=deleteviewname]").val(viewName);
	 if(confirm("Do you really want to delete ?"))
		 { 
			$("form[name=deletesubviewform]").submit();
			
		 }
 
 
}

function  showAddSubview(viewId, viewName ) {
	
	var url = "AddSubview.jsp?viewId=" + encodeURI(viewId) + "&viewName=" + encodeURI(viewName); 
	window.location.href = url;
}

function showEditWindow(subviewId,parentId,subviewName, subviewKey,url,showorder){
	var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	var size = "height=450,width=400,top=200,left=300," + params;
	var url="editSubview.jsp?subviewId="+encodeURIComponent(subviewId)+"&parentId="+encodeURIComponent(parentId)+"&subviewName="+encodeURIComponent(subviewName)+"&subviewKey="+encodeURIComponent(subviewKey)+"&showorder="+encodeURIComponent(showorder)+"&url="+encodeURIComponent(url);
	  
    newwindow = window.open(url, 'Edit Subview', size);
	if (window.focus) {
		newwindow.focus();
	}
}
</script>
<title>Sub Views</title>
</head>
<body>
	  		<%@include file="Header.jsp"%>
	 	
   
<div id="body">
		<div class="content">
<%
int viewId=Integer.parseInt(request.getParameter("viewId"));
String viewName=request.getParameter("viewName");

List<View> subViewList=(List<View>)request.getAttribute("viewsList");
if(subViewList.isEmpty())
{%>
	 <center style="color: red;">Sorry,No Subview Found For This View</center>
<%}
else
{
%>
<h3>Sub Views Under <%=viewName %></h3>
<table>
<thead>
<th align="center">Id</th>
<th align="center">Sub-View Key</th>
<th align="center">Sub-View Name</th>
<th align="center">URL</th>
<th align="center">Show Order</th>
<th align="center">Action</th>
</thead>
<%
for(View v:subViewList)
{
%>
<tr>
<td align="center"><%=v.getId() %></td>
<td align="center"><%=v.getViewKey() %></td>
<td align="center"><%=v.getViewName() %></td>
<td align="center"><%=v.getPage().getUrl() %></td>
<td align="center"><%=v.getShowOrder() %></td>
<td><table ><tr><td style="border-bottom: 0px solid #cE5; padding: 0px;" > <img src="images/edit1.png" class="editButton" id="editImage" onclick="showEditWindow(<%=v.getId() %>,<%=v.getParentId() %>,'<%=v.getViewName() %>','<%=v.getViewKey()%>','<%=v.getPage().getId() %>',<%=v.getShowOrder() %>)"
									title="Edit" />
</td><td style="border-bottom: 0px solid #cE5; padding: 0px;"> 	<img src="images/delete1.png" class="deleteButton"  id="deleteImage" onclick="deleteSubview(<%=v.getId() %>,<%=viewId %>,'<%=viewName %>')"
									title="Delete" /> 
</td ></tr></table></td>
</tr>
<%
}
  }

%>

</table>
<table>
<tr>
</tr>
<td align="center">
<input align="middle" type="button" class="formbutton" value="Add Subview" onClick="showAddSubview(<%=viewId%>, '<%=viewName%>' )"/>
</td>
</table>
<form name="deletesubviewform" action="DeleteSubview" method="post">
<input type="hidden" name=deleteId id=deleteId/>
<input type="hidden" name=deleteviewid id=deleteviewid/>
<input type="hidden" name=deleteviewname id=deleteviewname/>
</form>
</body>
</html> 