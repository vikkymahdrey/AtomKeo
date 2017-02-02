<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Atom</title>
	
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
	
<script type="text/javascript" src="js/jquery-2.2.0.js" ></script>
<script type="text/javascript" src="js/dateValidation.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
 <link rel="stylesheet" href="css/jquery-ui.css">
  <script src="js/jquery-ui.js"></script>
<style type="text/css">
@import "css/jquery.datepick.css";
</style>
<script type="text/javascript">

$(document).ready(function()
{                                                                        
    $("#date").datepicker({ dateFormat: 'dd/mm/yyyy' }); 
    $("#enddate").datepicker( {dateFormat: 'dd/mm/yyyy' });
});
function loadScript() {
	if($('#date').val().length>0&&$('#enddate').val().length>0){
	cityLat = 12.9716;
	cityLon = 77.5946;
	try {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "https://maps.googleapis.com/maps/api/js?sensor=true&callback=initialize&client=gme-leptonsoftwareexport&signature=1t2jNPl7sIPdevsQdfKNrx25bko=";			
		document.body.appendChild(script);
	} catch (e) {

		alert("ERRO" + e);
	}
	}else{
		alert("Please select date!");
	}
}

function initialize() {
	geocoder = new google.maps.Geocoder();
	var myLatlng = new google.maps.LatLng(cityLat, cityLon);
	var myOptions = {
		zoom : 12,
		center : myLatlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"), myOptions);
	displayEmp();	  
		}

function displayEmp() {
	var convertDate = function(usDate) {
		  var dateParts = usDate.split(/(\d{1,2})\/(\d{1,2})\/(\d{4})/);
		  return dateParts[3] + "-" + dateParts[2] + "-" + dateParts[1];
		}
	var sdate=$('#date').val();
	var edate=$('#enddate').val();
	sdate = convertDate(sdate);
	edate = convertDate(edate);
	var startdate = sdate+" "+$('#sth').val()+":"+$('#stm').val()+":00";
	var enddate=edate+" "+$('#eth').val()+":"+$('#etm').val()+":00";
	var speed=$('#speed').val();
	var vid=$('#vid').val();
	$.ajax({url: "replaydata?vehicleid="+vid+"&fromdate="+startdate+"&todate="+enddate, success: function(result){
		var obj = jQuery.parseJSON(result)
		var image='icons/orangecar.png';
		var msg=obj.RESULT;
		if(msg=="TRUE"){
			var myLatLng ={lat:13.78900, lng:77.89999};
			  var marker =  new google.maps.Marker({
				  position : myLatLng,
					map : map,
					icon : image
				});
		var latarray=obj.LATS;
		var lngarray=obj.LNGS;
		var timearray=obj.TIMES;
		var status=obj.STATUS;
		var distance=obj.DISTANCES;
		var counter=0;
		var cordinates;
		var i=0;
			
			interval = window.setInterval(function() { 
				$("#datetimediv").html("<center>Date & Time-"+timearray[i]+"<br/>Status-"+status[i]+"<br/>Distance-"+distance[i]+"</center>");            
						  var newLatLong = new google.maps.LatLng(latarray[i], lngarray[i]);
						  marker.setPosition(newLatLong);
						  map.panTo(newLatLong);
							if(i!=0){
							cordinates=new Array();
							cordinates[1]=newLatLong;
							cordinates[0]=new google.maps.LatLng(latarray[i-1],lngarray[i-1]);
							                           var linepath = new google.maps.Polyline({
							                             path: cordinates,
							                             geodesic: true,
							                             strokeColor: '#0000FF',
							                             strokeOpacity: 2.0,
							                             strokeWeight: 3
							                           });

							                           linepath.setMap(map);
							}

					  i++;
					  if (counter >= latarray.length) {
						    window.clearInterval(interval);   
						  }
					}, speed);
		}else{
			alert("No data found!");
			}
		}
		});
}



</script>
	<link rel="stylesheet" href="css/atom.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
	div#filterContainer {
		height: auto;
		background-colore : red;
		width: 100%;
		height: 50px;
	}
	div#filterContainer > div {
		display: inline;
		 float: left;
		 padding-left:12px;
	}
		div#filterContainer >div > div:FIRST-CHILD {
		display: inline;
		 float: left;
		 margin-bottom: 5px; 
		 margin-top: 10px;
	}
	
	div#reportDiv {
		margin-top:25px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Replay Report</title>
</head>
<body>
<br/><br/><br/>
<center><h1>Replay Report</h1></center>
<%
String date=request.getParameter("date");
String enddate=request.getParameter("enddate");
%>
<br/><br/><br/><br/>
<div id="filterContainer">
<div><div>Vehicle</div><div><select name="vid" id="vid">
<c:forEach var="v" items="${list}" >
<option value='${v.id}' >${v.regNo}</option>
</c:forEach>
</select></div></div>

<div><div>Start Date</div><div><label class="scd" ><input  name="date" id="date" type="text" size="6"
										readonly="readonly"
										class="{showOtherMonths: true, firstDay: 1, dateFormat: 'yyyy-mm-dd',
		                                                 minDate: new Date(2008, 12 - 1, 25)}"
										value="<%=date!=null&&date.trim().equals("")==false?date:"" %>" />
									</label>	</div></div>
<div><div>End Date</div><div><label class="scd" ><input required name="enddate" id="enddate" type="text" size="6"
										readonly="readonly"
										class="{showOtherMonths: true, firstDay: 1, dateFormat: 'yyyy-mm-dd',
		                                                 minDate: new Date(2008, 12 - 1, 25)}"
										value="<%=enddate!=null&&date.trim().equals("")==false?date:"" %>" />
									</label>	</div></div>

<div><div></div><div><br/><br/>Start Time</div></div><div><div> HOUR</div><div><select id="sth" >
<%
String time="";
for(int i=0;i<24;i++){
	if(i<10){
		time="0"+i;
	}
	else{
		time=""+i;
	}
%>
<option value=<%=time %>><%=time %></option>
<%} %>
</select></div></div>
<div><div>MINUTE</div><div><select id="stm">
<%
for(int i=0;i<60;i++){
	if(i<10){
		time="0"+i;
	}
	else{
		time=""+i;
	}
%>
<option value=<%=time %>><%=time %></option>
<%} %>
</select></div></div>
<div></div><div><br/><br/>End Time</div><div><div>HOUR</div><div><select id="eth">
<%
for(int i=0;i<24;i++){
	if(i<10){
		time="0"+i;
	}
	else{
		time=""+i;
	}
%>
<option value=<%=time %>><%=time %></option>
<%} %></select></div></div><div><div>  MINUTE</div><div><select id="etm">
<%
for(int i=0;i<60;i++){
	if(i<10){
		time="0"+i;
	}
	else{
		time=""+i;
	}
%>
<option value=<%=time %>><%=time %></option>
<%} %>
</select></div></div>
<div><div>Play Speed</div><div><select id="playspeed"><option value="900">1x</option><option value="600">2x</option><option value="300">4x</option><option value="200">8x</option><option value="100">16x</option><option value="50">32x</option></select></div></div>
<div style="margin-top:29px;">
							 
							 
							 	
							 		<input type="submit" id="playbtn" class="formbutton" value="&#9658;" onClick="loadScript();" />
							 	
							</div>
</div>	<br/><br/><br/><br/><br/><br/>
<div id="parent" style="width:100%" align="center">
<p align="center">Vehicle Path</p>
<div id="datetimediv"></div>
	<div id="child1" style="width:60%">
	<h5 id="datetime"></h5>
	</div>
	<div id="map" style="width:100%;height:544px;"></div>
			
			
		</div>
</body>
</html>