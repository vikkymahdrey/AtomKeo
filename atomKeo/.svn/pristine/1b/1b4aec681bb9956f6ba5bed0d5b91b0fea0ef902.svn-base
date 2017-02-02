<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Routing</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript">        
            $(document).ready(function()
            {                                                                 
                $("#tripDate").datepick();                                                            
            });     
        </script>
<script type="text/javascript">
            function validate()
            {
                var mod=document.getElementById("log").value;   
                var time=document.getElementById("tripTime").value;  
                var date=document.getElementById("tripDate").value;  
                if(date.length<1)
                {
                    alert("Choose Date");
                  //  date.focus();
                    return false;
                        
                }
                else if(mod=="ALL"&&time!="ALL")
                {
                    alert("Cannot choose time for both Shift");
                    //time.options[0].selected=true;   
                    return false;
                }
                else
                {
                    return true;                            
                }
               
            }
            
         
        </script>

</head>
<body>
	<%
			 List<Site> sites=(List<Site>)request.getAttribute("siteList");
				%>
			<%
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
	<hr />
	<div id="body">
		<div class="content">
			<h3>Generate and allocate trip sheet..</h3>
			<form name="form1" action="tripAllocation" method="POST"
				onsubmit="return validate()">
				<table>
					<tr>
							<%
							String tripDateSession ="";
							try {
							 tripDateSession = request.getSession().getAttribute("date").toString();
							} catch(Exception e) {
								tripDateSession="";
							}
							
								String tripDate=request.getParameter("tripDate");
							 	tripDate = (tripDate==null || tripDate.trim().equals("")) ?tripDateSession:tripDate;
							 	request.getSession().setAttribute("date", tripDate);
							%>

						<td align="right">Date</td>
						<td><input readonly="readonly" name="tripDate" id="tripDate" type="text" size="6"
							class="{showOtherMonths: true, firstDay: 1, dateFormat: 'yyyy-mm-dd',
                                                 minDate: new Date(2008, 12 - 1, 25)}" value="<%=tripDate %>" /></td>


						<td><select name="log" id="log">
						<option value="OUT">Drop</option>
						</select></td>						
						<td align="right">Shift</td>
						<td id="tripTimeId"><select name="tripTime" id="tripTime">
						<%=OtherFunctions.keoIntervalOptions()%>
								</select></td>

						<td>&nbsp;</td>
						<td><input type="submit" class="formbutton" value="Show" /></td>
					</tr>
				</table>
			</form>
			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
