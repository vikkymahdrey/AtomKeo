<%@page import="java.util.List"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@page import="com.agiledge.atom.constants.AuditLogConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Routing</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>

<script type="text/javascript">        
            $(document).ready(function()
            {                                                                        
                $("#for_date").datepick();  
               // getLogTime();       
            });     
        </script>
<script type="text/javascript">
var url = "";
var intervalId="";
            function validate()
            {
            	var siteId=document.getElementById("siteId").value;
                var date=document.getElementById("for_date").value;   
                var time=document.getElementById("logTime").value;
                var log=document.getElementById("log").value;     
                document.getElementById("vehicleused").innerHTML="";
               /*  if(siteId.length<1)
                {
                    alert("Choose Site");                  
                    return false;
                }
                else { */
                	if(date.length<1)
                     {
                   		 alert("Choose Date");                  
                    	 return false;
               		 }
                	else
                	{    
                		alert(document.getElementById("statustd").innerHTML.length);
                	
                		if(document.getElementById("statustd").innerHTML.length<10)
                		{
                		
                			document.getElementById("statustd").innerHTML="<b>Work in Progress please wait...</b>";
                			var url="routing?check=c&siteId="+siteId+"&for_date="+date+"&log="+log+"&logTime="+time+"";
                	
                   	xmlHttp=GetXmlHttpObject();
                    if (xmlHttp==null)
                    {
                        alert("Browser does not support HTTP Request");
                        return
                    }                    
                    xmlHttp.onreadystatechange=submitForm;
                    alert("3434");
                    xmlHttp.open("GET",url,true);
                    xmlHttp.send(null);
                	                                          
                }
              }
            }
            
            function submitForm()
            {
            try{	
           if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                 { 
        		var siteId=document.getElementById("siteId").value;
                var date=document.getElementById("for_date").value;   
                var time=document.getElementById("logTime").value;
                var log=document.getElementById("log").value;
                var vehicletypes;
                try
                {
                var vehicletypes=document.getElementById("vehicleTypes").value;
                }catch(e)
                {                 
                }
        	   var returnText=xmlHttp.responseText;  
        	   var routingflag=false;
        	    url="routing?siteId="+siteId+"&for_date="+date+"&log="+log+"&logTime="+time+"";
        	   for(var i=0;i<vehicletypes;i++)
        		   {
        		   url+='&vehicleType='+document.getElementById("type"+i).value;
        		   url+='&count'+document.getElementById("type"+i).value+'='+document.getElementById("count"+i).value;
        		   }
        	   if(returnText=="true")
        		   {
        		   if(confirm("Trips on selected day/shift already exists, OK to replace existing?"))
        		   {
        			   url+="&isOverwrite=yes";
        			   routingflag=true;
        		   }
        		   else
        			{        			  
        			   		if(confirm("Generate tripsheet for remaining shifts?"))
        					{        		
        				   		routingflag=true;	   
        			   			url+="&isOverwrite=no";
        			   		}    
        			   		else
        			   			{
        			   				document.getElementById("statustd").innerHTML="";
        			   				
        			   			}
                    }
        		   if(routingflag==true)
        			   {
        	   			xmlHttp=GetXmlHttpObject();
               				if (xmlHttp==null)
               				{
                   				alert("Browser does not support HTTP Request");
                   				return
               				}                    
               				xmlHttp.onreadystatechange=RoutingReturn;                   
               				xmlHttp.open("GET",url,false);
               				xmlHttp.send(null);
               				
        			   }
        		   }
        	   else
        		   {
        		   xmlHttp=GetXmlHttpObject();
      				if (xmlHttp==null)
      				{
          				alert("Browser does not support HTTP Request");
          				return
      				}            
      				
      				xmlHttp.onreadystatechange=RoutingReturn;                   
      				xmlHttp.open("GET",url,false);
      				xmlHttp.send(null);
        		   }
                 }
           }catch(e)
           {
        	 alert(e);  
           }
            }    
            
            function RoutingReturn()
            {
            	 if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
            		 {
            		 var retString=xmlHttp.responseText;
            		 var splittedString =retString.split("|");
            		 var divData="";
            		 try
            		 {
            		 if(splittedString[0]=="Routing Successful")
            			 {
	            			 divData="<table>"+splittedString[1]+"</table>";
	            		   	 document.getElementById("vehicleused").innerHTML=divData;
	                    	 document.getElementById("statustd").innerHTML="";
            			 }
            		 else
            			 {
	            			 if((retString== undefined || retString == null || retString.length <= 0) ) {
	            				 
	            				 executeInInterval();
	            				 
	            			 } else { 
	            			 alert(retString);
	            		   	 document.getElementById("vehicleused").innerHTML=divData;
	                    	 document.getElementById("statustd").innerHTML="";
	            			 }
            			}
            		 }
            		 catch(e)
            		 {
            			alert(e); 
            		   	 document.getElementById("vehicleused").innerHTML=divData;
                    	 document.getElementById("statustd").innerHTML="";
            		 }
         
            }
            }	 
            
            function executeInInterval() {
            	intervalId = setInterval(intervalRequests , 3000);
            }
            
            function stopExecuteInInterval() {
            	clearInterval(intervalId);
            }
            
            function intervalRequests() {
            	
  	   			xmlHttp=GetXmlHttpObject();
  	   			var checkUrl= "RoutingCheck";
   				if (xmlHttp==null)
   				{
       				alert("Browser does not support HTTP Request");
       				return
   				}        
   				 
   				xmlHttp.onreadystatechange= routingCheckingResult;                   
   				xmlHttp.open("GET",checkUrl,true);
   				xmlHttp.timeout = 40000; 
   				xmlHttp.send(null);
      	
            }
            
            function routingCheckingResult() {
		            	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
		       		 {
		            		document.getElementById("progressBar").innerHTML=document.getElementById("progressBar").innerHTML + "*";
		            		 
			       		 var retString=xmlHttp.responseText;
			       		 if(retString=="started" ) { 
			       			  ;
			       		 } else {
			       			 
			       			stopExecuteInInterval();
			       			 
			       		 var splittedString =retString.split("|");
			       		 var divData="";
			       		 try
			       		 {
			       		 if(splittedString[0]=="Routing Successful")
			       			 {
			       			 divData="<table>"+splittedString[1]+"</table>";
			       			 }
			       		 else
			       			 {
			           			 	alert(retString);
			           		}
			       		 }
			       		 catch(e)
			       		 {
			       			alert(e); 
			       		 }
			       	 document.getElementById("vehicleused").innerHTML=divData;
			       	 document.getElementById("statustd").innerHTML="";
		       		 }
			       }  
	            }
            
            function getLogTime()
            {     
            	var logtype=document.getElementById("log").value;
                //alert("onchange log event called");
                var site=document.getElementById("siteId").value;
                if(logtype=="All")
                	{
                	
                	var tripTimeId=document.getElementById("logTimeListTd");
                	//alert(tripTimeId);
                	tripTimeId.innerHTML='<select name="logTime" id="logTime"> <option value="All" >ALL</option></select>';
                	return;
                	}
                else
                	{
                var url="getLogTime?logtype="+logtype+"&site="+site;                                    
                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request");
                    return
                }                    
                xmlHttp.onreadystatechange=setLogTime;	
                xmlHttp.open("GET",url,true);                
                xmlHttp.send(null);
                
                	}
            }
                
            function GetXmlHttpObject()
            {
                var xmlHttp=null;
                if (window.XMLHttpRequest) 
                {
                    xmlHttp=new XMLHttpRequest();
                }                
                else if (window.ActiveXObject) 
                { 
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }

                return xmlHttp;
            }
        
            function setLogTime() 
            {                      
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                { 
                    var returnText=xmlHttp.responseText;
                    var logTimeListTd=document.getElementById("logTimeListTd");
                    logTimeListTd.innerHTML='<select  name="logTime" id="logTime"><Option value="All">All</Option>'+returnText+'</select>';                                             
                }
            }
            
        </script>


</head>
<body>
	<div id="body">
		<div class="content">
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

			<h3>Automatic Routing</h3>
			<hr />
			<form name="form1" action="routing">
				<div>
					<!-- 	<input type="button" onclick="executeInInterval()" value="start" />
				<input type="button" onclick="stopExecuteInInterval()" value="stop" /> -->
					<div id="progressBar"></div>
				</div>
				<table>

					<tr>
						<%String site=request.getParameter("siteId");
			    		if(site == null ) site = "";
			    		%>
						<td align="right">Choose Site</td>
						<td><select name="siteId" id="siteId">
								<option value="">--select--</option>
								<%
										for (Site s : sites) {
											String siteSelect = "";
											if (site.equals(""+s.getId())) {
												siteSelect = "selected";
											}
									%>

								<option <%=siteSelect%> value="<%=s.getId()%>">
									<%=s.getSiteName()%>
								</option>


								<%
										}
									%>
						</select></td>
						<%
							String tripDateSession ="";
							try {
							 tripDateSession = request.getSession().getAttribute("date").toString();
							} catch(Exception e) {
								tripDateSession="";
							}
							
								String tripDate=request.getParameter("tripDate");
							 	tripDate= (tripDate==null || tripDate.trim().equals("")) ?tripDateSession:tripDate;
							 	request.getSession().setAttribute("date", tripDate);
							 	
							%>


						<td align="right">Date</td>
						<td><input name="for_date" id="for_date" type="text" size="6"
							class="{showOtherMonths: true, firstDay: 1, dateFormat: 'yyyy-mm-dd',
                                                 minDate: new Date(2008, 12 - 1, 25)}"
							value="<%=tripDate %>" /></td>

						<td align="right">Log In/Out</td>
						<td><select name="log" id="log" onchange="getLogTime()">
								<%
							String shiftLogLabel[] = {"ALL", "IN", "OUT"};
							String shiftLogValue[] = {"All", "IN", "OUT" };
							String slParamSession="";
							String slParam="";
							try {
								slParam=request.getParameter("shiftLog");
								slParam=slParam==null?"":slParam;
								slParamSession=request.getSession().getAttribute("shiftLog").toString();
							    slParam = slParam.equals("")?slParamSession:slParam;
							}catch(Exception e) {
								slParamSession="";
							}
							for(int slCntr=0;slCntr<shiftLogLabel.length; slCntr++) {
								String slSelect="";
								if(slParam.equals(shiftLogValue[slCntr])) {
									slSelect="selected";
								}
						%>
								<option <%=slSelect %> value="<%=shiftLogLabel[slCntr] %>"><%=shiftLogValue[slCntr] %></option>
								<%
							}	
								%>

						</select></td>

						<td align="right">Time</td>
						<td id="logTimeListTd"><select name="logTime" id="logTime">
								<option value="All">All</option>
						</select></td>

						<td>&nbsp;<input type="hidden" name="isOverwrite"
							id="isOverwrite" /></td>
						<td><input type="button" class="formbutton" value="Allocate"
							onclick="return validate()" /></td>
					</tr>

					<%
						List<VehicleType> vehicleTypeList=(List<VehicleType>)request.getAttribute("vehicleTypeList");
					%>
					<input type="hidden" id="vehicleTypes"
						value="<%=vehicleTypeList.size()%>" />
					<%
						for (int i = 0; i < vehicleTypeList.size(); i++) {
					%>
					<tr>
						<td><%=vehicleTypeList.get(i).getType()%></td>
						<td><input type="hidden" name="vehicleType" id="type<%=i%>"
							value="<%=vehicleTypeList.get(i).getId()%>" /> <input
							type="text" name="count<%=vehicleTypeList.get(i).getId()%>"
							id="count<%=i%>" /></td>
					</tr>
					<%
						}
					%>

					<tr>
						<td colspan="6" id="statustd"></td>
					</tr>
					<tr>
						<td colspan="6" id="vehicleused"></td>


					</tr>
				</table>
			</form>
			<%@include file="Footer.jsp"%>
		</div>
	</div>
</body>
</html>
