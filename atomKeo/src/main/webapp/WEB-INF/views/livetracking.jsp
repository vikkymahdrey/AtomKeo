<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Atom</title>
	
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
	
	
	<link rel="stylesheet" href="css/atom.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body onload="loadScript();">
  <%response.setIntHeader("Refresh", 60); %>
  <div class="wrapper">
	<header>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-4">
					<a href="#" class="header-logo">
						<img src="images/logo.png" alt="atom" />
					</a>
				</div>
			</div>
		</div>
	</header>
	
	<div class="page-main-heading mar-top-40 mar-btm-25 overflow-hidden">
		<div class="col-sm-7">
			<h1 class="text-uppercase text-regular">Live Tracking</h1>
			<h6 class="text-uppercase text-blue mar-top-5 text-regular">track everything</h6>
		</div>
		<div class="col-sm-5 text-right">
			<button class="btn btn-blue font-16" onClick="location.reload()"><img src="images/icon_refresh.png"   />&nbsp;&nbsp;REFRESH</button>&nbsp;&nbsp;
			<a href="employeeHome"><button class="btn btn-blue font-16">&nbsp;&nbsp;HOME</button>&nbsp;&nbsp;</a>
		
		</div>		
	</div>
	
	<div class="content-wrapper">
	
	
			<%String message=request.getParameter("message");
			message= message==null?"":message;
			%>
			<h6 align="center" style="color: green;" ><b><i><%=message %></i></b></h6>
		   
		   <div class="col-sm-8">
				
			</div>
			</div>
			<div id="map" style="width:100%;height:544px;"></div>
			
			
	
		
		
		
	</div>
	
  
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
	
<script type="text/javascript">
var mytripid;
function loadScript() {
	cityLat = 12.9716;
	cityLon = 77.5946;
	try {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "https://maps.googleapis.com/maps/api/js?sensor=true&callback=initialize&client=gme-leptonsoftwareexport4&signature=xghu9DIoNr63z8_al_oJCSPWQh0=";			
		document.body.appendChild(script);
	} catch (e) {

		alert("ERRO" + e);
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
	$.ajax({url: "getlivetrackingdata", success: function(result){
		var obj = jQuery.parseJSON(result);
		
		var lats=obj.LATS;
		var lngs=obj.LNGS;
		var vnos=obj.VEHICLENO;
		var status=obj.STATUS;
		var emps=obj.EMPS;
		var empstatus=obj.EMPSTATUS;
		var type=obj.TRIPTYPE;
		var tripid=obj.TRIPID;
		for(var i=0;i<lats.length;i++){
			placeMarker(lats[i],lngs[i],vnos[i],type[i],status[i],tripid[i]);
			

		}
		
	}
		});
}


function placeMarker(lat,lon,vtext,type,status,tripid) {	
	
	var image='images/map_pointer_single_lady.png';
	if(type=="SHUTTLE"){
		image='images/map_pointer_single_lady_wo_escot.png';
	}
	if(status=="danger"){
		image='images/map_pointer_pani_alarm.png';
		
	}
	var marker=new google.maps.LatLng(lat, lon);

	if (type == "SHUTTLE") {
			marker = new google.maps.Marker({
				position : marker,
				map : map,
				icon : image,
				title : vtext
			});

		} else {

		
			if (status == "danger") {
				marker = new google.maps.Marker({
					position : marker,
					map : map,
					icon : image,
					title : vtext
				});

				google.maps.event.addListener(marker, 'click', function() {
					PanicVehicle(tripid);

				});
				playsound();
			} else {
				
				marker = new google.maps.Marker({
					position : marker,
					map : map,
					icon : image,
					title : vtext
				});
				
				google.maps.event.addListener(marker, 'click', function() {
					
					TripDetails(tripid);

				});
			}
		}
		var infowindow = new google.maps.InfoWindow({
			content : vtext
		});
		marker.addListener('click', function() {
			infowindow.open(map, marker);
		});
	}

	function playsound() {
		//$("#dummy").html("<embed src=\"images/alarm.wav\" hidden=\"true\" autostart=\"true\" loop=\"false\" />");
		//$('#playsnd').html("<source src=\"images/alarm.wav\" type=\"audio/wav\">");
		var myAudio = new Audio('images/alarm.wav');
		myAudio.addEventListener('ended', function() {
			this.currentTime = 0;
			this.play();
		}, false);
		myAudio.play();
	}
	function PanicVehicle(tripId) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		var size = params;
		var url = "panicAlarmTrip?tripId=" + tripId;

		window = window.location.href=url;

	}
	function TripDetails(tripId) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		var size = "height=300,width=700,top=200,left=200," + params;
		var url = "tripInfoPopup?tripId=" + tripId;

		window = window.open(url, 'TripInfo', size);

	}</script>
	<div id="dummy"></div>
  </body>
</html>