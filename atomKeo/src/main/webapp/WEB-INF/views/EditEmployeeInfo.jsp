<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.agiledge.atom.config.files.*"%>
<%@page import="com.agiledge.atom.dao.intfc.OtherDao"%>
<%@page import="com.agiledge.atom.constants.*"%>
<%@page import="com.agiledge.atom.entities.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Employee Details</title>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script>
	function showPopup1(url) {
		var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		size = "height=450,width=520,top=200,left=300," + params;

		newwindow = window.open(url, 'name', size);

	}
	function validateDrop(status) {
		var points = document.getElementsByName('points');
		var point;
		for (var i = 0; i < points.length; i++) {
			if (points[i].checked) {
				point = points[i].value;
			}
		}
		if (point == "diffr") {
			$(".loc").show();
			$("#samedrop").val("no");
			$("#outlatlong").val("");
		} else if (point == "same") {
			$(".loc").hide();
			$("#samedrop").val("yes");
		} else {
			$("#samedrop").val("ignorecase");
			$("#outlatlong").val("");
			$("#inlatlong").val("");
		}
		if (status == 'hide') {
			$('.tripping').hide();
			document.getElementById("inRoute").value = "0";
			document.getElementById("outTime").value = "0";
			document.getElementById("outRoute").value = "0";
		} else {
			if ($('.tripping').is(':hidden')) {
				$('.tripping').show();
				document.getElementById("inTime").value = "";
				document.getElementById("inRoute").value = "";
				document.getElementById("outTime").value = "";
				document.getElementById("outRoute").value = "";
			}
		}
	}
	function validate() {
		$('.san').show();
		var fname = document.getElementById("fname").value;
		var lname = document.getElementById("lname").value;
		var gender = document.getElementById("gender").value;
		var email = document.getElementById("email").value;
		var mob = document.getElementById("mob").value;
		var addr = document.getElementById("addr").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;

		var homelatlong = document.getElementById("homelatlong").value;
		if (fname == "" || fname == null || fname == " ") {
			document.getElementById("errortag").innerHTML = "Please enter your first name";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#fname').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("fname").focus();
			return false;
		} else if (lname == "" || lname == null || lname == " ") {
			document.getElementById("errortag").innerHTML = "Please enter your last name";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#lname').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("lname").focus();
			return false;
		} else if (mob == "" || mob == null || mob.length != 10 || mob < 0) {
			document.getElementById("errortag").innerHTML = "Please enter a valid mobile number";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#mob').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("mob").focus();
			return false;
		} else if (addr == "" || addr == null || addr == " ") {
			document.getElementById("errortag").innerHTML = "Please enter your address";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#addr').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("addr").focus();
			return false;
		} else if (/^\s+$/.test(addr)) {
			document.getElementById("errortag").innerHTML = "Please enter your address";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#addr').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("addr").focus();
			return false;
		} else if (city == "" || city == null || city == " ") {
			document.getElementById("errortag").innerHTML = "Please enter city name";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#city').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("city").focus();
			return false;
		} else if (zip == "" || zip == null || zip == " " || isNaN(zip)
				|| zip.length > 6) {
			document.getElementById("errortag").innerHTML = "Please enter zip code";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#zip').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("zip").focus();
			return false;
		} else if ((state == "KA" && zip.substring(0, 3) != "560")
				|| (state == "TN" && zip.substring(0, 3) != "635" || zip < 100000)) {
			document.getElementById("errortag").innerHTML = "Please enter the correct zip code";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#zip').removeClass("form-control").addClass(
					"validation-required");
			document.getElementById("zip").focus();
			return false;
		} else if (homelatlong == "" || homelatlong == null
				|| homelatlong == " " || homelatlong == "(null, null)"
				|| homelatlong == "(, )") {
			document.getElementById("errortag").innerHTML = "Please pin your address on map";
			document.getElementById("alertwarn").innerHTML = "Please pin your address on map";
			$('.validation-required').removeClass("validation-required")
					.addClass("form-control");
			$('#validationAlertModal').modal();
			return false;
		} else {
			return true;
		}
	}
</script>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom_siemens.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%
		Employee emp = (Employee) session.getAttribute("userLoggedIn");
		if (emp == null) {
			String param = request.getServletPath().substring(1) + "___"
					+ request.getQueryString();
			response.sendRedirect("/?page=" + param);
		} else {
			// empid = Long.parseLong(employeeId); 
		}
		String genderm = "checked='checked'", genderf = "";
		if (emp.getGender().equalsIgnoreCase("f")) {
			genderf = "checked='checked'";
			genderm = "";
		}
	%>
	<div class="wrapper">
		<div class="header-wrap">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 text-right">
						<img src="images/user_iocn_header.png" />&nbsp;Welcome
						<%=emp.getEmployeeFirstName() + " "
					+ emp.getEmployeeLastName()%>&nbsp;&nbsp;&nbsp;<a
							href="logout"><img src="images/logout_icon_header.png" />&nbsp;Log
							Out</a>
					</div>
				</div>
			</div>
		</div>

		<div class="main-page-container">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="breadcrumb-wrap">
					<a href="employeeHomeKeo"><img src="images/home.png" /></a>
					<a href="employeeHomeKeo" >My Information </a>
					<!-- <a href="#" class="current">Edit Information </a> -->
					<a href="transportRequest" >Book Transport</a>
					<a href="employeeviewtrip" >View Booking </a>
						</div>
						<div class="breadcrumb-wrap"></div>


						<div class="content-wrap">
							<form action="employeeEditSubmit" id="UserDetails" method="post"
								onsubmit="return validate();">

								<div class="row">
									<div class="col-sm-8 page-heading mar-top-20">
										<i class="page-heading-icon"><img
											src="images/edit_profile.png" /></i>
										<h5 class="text-blue text-semi-bold">Edit Information</h5>
									</div>

								</div>
								<div class="row mar-top-20">
									<div class="col-sm-12">
										<div class="alert alert-danger san" hidden="hidden"
											style="color: red">
											<p id="errortag"></p>
										</div>
									</div>
								</div>
								<div class="section-heading">
									<div class="row">
										<div class="col-sm-12">Personal Information</div>
									</div>
								</div>


								<div class="push-15">
									<div class="row">
										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 text-lightgrey mandatory">First
											Name:</div>
										<div class="col-md-3 col-sm-7 col-xs-6 mar-top-15">
											<input type="text" name="fname" id="fname"
												value="<%=emp.getEmployeeFirstName()%>" /><input
												type="hidden" id="email" name="email"
												value="<%=emp.getEmailAddress()%>"> <input
												type="hidden" name="homelatlong" id="homelatlong"
												value="<%="(" + emp.getEmpLat() + ", " + emp.getEmpLong() + ")"%>" />
										</div>

										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 col-md-offset-2 text-lightgrey mandatory">Last
											Name:</div>
										<div class="col-md-2 col-sm-7 col-xs-6 mar-top-15">
											<input type="text" name="lname" id="lname"
												value="<%=emp.getEmployeeLastName()%>" />
										</div>
									</div>


									<div class="row">
										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 text-lightgrey">Employee
											ID:</div>
										<div class="col-md-3 col-sm-7 col-xs-6 mar-top-15"><%=emp.getPersonnelNo()%></div>

										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 col-md-offset-2 text-lightgrey">Email:</div>
										<div class="col-md-2 col-sm-7 col-xs-6 mar-top-15"><%=emp.getEmailAddress()%></div>
									</div>


									<div class="row">
										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 text-lightgrey mandatory">Gender:</div>
										<div class="col-md-3 col-sm-7 col-xs-6 mar-top-15 text-break">
											Male&nbsp;&nbsp;<input type="radio" name="gender" id="gender"
												value="M" <%=genderm%> />&nbsp;&nbsp;&nbsp;&nbsp;Female&nbsp;&nbsp;<input
												type="radio" name="gender" id="gender" value="F"
												<%=genderf%> />
										</div>

										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 col-md-offset-2 text-lightgrey mandatory">Mobile
											No:</div>
										<div class="col-md-2 col-sm-7 col-xs-6 mar-top-15">
											<input type="text" name="mob" id="mob"
												value="<%=emp.getContactNumber1()%>" class="form-control" />
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-2 col-sm-5 col-xs-6 mar-top-15 text-lightgrey">City:</div>
										<div class="col-md-3 col-sm-7 col-xs-6 mar-top-15"><%=emp.getSiteBean().getBranchBean().getLocation()%>
										</div>

										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 col-md-offset-2 text-lightgrey">Site:</div>
										<div class="col-md-2 col-sm-7 col-xs-6 mar-top-15">
										<%  @SuppressWarnings("unchecked")
											 List<Site> sites=(List<Site>)request.getAttribute("sites"); %>
										 <select name="siteId" id="siteId" >
						
									 	<%if(sites!=null && !(sites.isEmpty())){
											for (Site s : sites) {
										%>
										<option value="<%=s.getId()%>">	<%=s.getSiteName()%></option>
            							<%
										 }
									 	 }
										%>
						</select> 
										</div>
									</div>

								</div>


								<div class="section-heading">
									<div class="row">
										<div class="col-sm-12">Current Residence Address</div>
									</div>
								</div>

								<div class="push-15">
									<div class="row">
										<div
											class="col-md-2 col-sm-5 col-xs-6 mar-top-15 text-lightgrey mandatory">Street
											Address:</div>
										<div class="col-md-3 col-sm-7 col-xs-6 mar-top-15 ">
											<textarea class="addresstextarea form-control" name="addr"
												id="addr"><%=emp.getAddress()%>
						</textarea>
										</div>

										<div class="col-md-5 col-sm-12 col-md-offset-2">


											<div class="row">
												<div
													class="col-md-5 col-sm-5 col-xs-6 mar-top-15 text-lightgrey"></div>
												<div class="col-md-5 col-sm-7 col-xs-6 mar-top-15">
													<a href="#"
														class="btn btn-light-blue has-icon map-popup-link"
														onclick="initialize('home')"><i><img
															src="images/map_icon.png" /></i>Pin Your Location on Map</a>
												</div>

											</div>


										</div>

									</div>
								</div>


								<div class="border-line mar-top-30 mar-btm-10"></div>

								<div class="row">
									<div class="col-sm-12 text-red text-12" style="color: red">*All
										fields are mandatory</div>
								</div>

								<div class="row text-right mar-btm-30">
									<div class="col-sm-12">
										<input type="submit" class="btn btn-blue save-btn"
											value="Save" />
									</div>
								</div>
							</form>
						</div>

						<div class="footer-wrap">
							<div class="row">
								<div class="col-sm-12 text-center">
									<p class="text-12">
										The information stored on this website is maintained in
										accordance with the organization's Data Privacy Policy. </span><br />Copyright
										� 2016 Siemens
								</div>
							</div>

						</div>


					</div>
				</div>

			</div>
		</div>



		<div class="popup-wrapper">
			<div class="popup-content-wrap">
				<div class="popup-header">
					Choose Home Location <a href="#" class="close-btn">X</a>
				</div>

				<div class="popup-inner-content-wrap">
					<div class="row">
						<div class="col-sm-6 mar-top-15">
							<input type="text" id="pac-input" placeholder="Search Box"
								class="form-control map-search-input" />
						</div>
						<div class="col-sm-6 text-right mar-top-15">
							<a href="#" class="btn btn-blue text-uppercase close-btn"
								onclick="return setvalues()">Set the location</a>
						</div>
					</div>

					<div class="map-wrapper" id="editProfileMap"></div>
				</div>

			</div>
		</div>


		<div class="modal fade" tabindex="-1" role="dialog"
			id="validationAlertModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">

					<div class="modal-body">
						<p class="alert alert-warning" id="alertwarn">
							<img src="images/alert_icon.png" />
						</p>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-blue" data-dismiss="modal">Ok</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->


	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="https://code.jquery.com/jquery-2.2.0.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?sensor=true&libraries=places&client=gme-leptonsoftwareexport4&signature=xghu9DIoNr63z8_al_oJCSPWQh0="></script>
	<script>
		$(document).ready(function() {
			$(".map-popup-link").click(function(e) {
				e.preventDefault();
				$(".popup-wrapper").fadeIn("slow");
				//	initialize();
			});

			$(".popup-wrapper .close-btn").click(function(e) {
				e.preventDefault();
				$(".popup-wrapper").fadeOut("slow")
			});

			/* 
			 $(".save-btn").click(function(e){
				e.preventDefault();
				$('#validationAlertModal').modal();
			});
			 */

		});
	</script>

	<script type="text/javascript">
		var lat = 12.9760559;
		var lng = 77.5922071;
		var marker;
		var markers;
		var count = 0;
		var map;
		var name;
		var i = 0;
		var finalvalue;
		function initialize(type) {
			
if((document.getElementById("homelatlong").value)==null ||(document.getElementById("homelatlong").value)=='null'){
				
			}else{
				var homelatlong=document.getElementById("homelatlong").value;
				
				lat = parseFloat((((homelatlong.split(", "))[0]).split("("))[1]);
				lng = parseFloat((((homelatlong.split(", "))[1]).split(")"))[0]);
			}
			if (count > 0) {
				for (var k = 0; k < markers.length; k++) {
					markers[k].setMap(null);
				}
			}
			name = type;

			$(".map-popup-link").click(function(e) {
				e.preventDefault();
				$(".popup-wrapper").fadeIn("slow");
			});

			$(".popup-wrapper .close-btn").click(function(e) {
				e.preventDefault();
				$(".popup-wrapper").fadeOut("slow")
			});

			google.maps.event.addDomListener(window, 'load', initialize);
			var mapProp;
			var myLatLng12 = new google.maps.LatLng(lat, lng);
			if (i == 0) {
				i = i + 1;
				mapProp = {
					center : new google.maps.LatLng(lat, lng),
					zoom : 12,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
				map = new google.maps.Map(document
						.getElementById("editProfileMap"), mapProp);
				google.maps.event.addListener(map, 'click', function(event) {
					placeMarker(event.latLng);
				});

			} else {

				/*			mapProp = {
										center:new google.maps.LatLng(lat,lng),
										zoom:12,
										mapTypeId:google.maps.MapTypeId.ROADMAP
									  };*/
				$('#pac-input').val('');
				map.panTo(new google.maps.LatLng(lat, lng));
			}
			markers = [];

			placeMarker(myLatLng12);
			var input = document.getElementById('pac-input');
			var searchBox = new google.maps.places.SearchBox(input);
			// map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

			map.addListener('bounds_changed', function() {
				searchBox.setBounds(map.getBounds());
			});
			markers = [];
			searchBox.addListener('places_changed', function() {
				var places = searchBox.getPlaces();

				if (places.length == 0) {
					return;
				}

				// Clear out the old markers.
				markers.forEach(function(marker1) {
					marker1.setMap(null);
					marker.setMap(null);
				});
				markers = [];

				// For each place, get the icon, name and location.
				var bounds = new google.maps.LatLngBounds();
				places.forEach(function(place) {
					marker.setMap(null);
					var icon = {
						url : place.icon,
						size : new google.maps.Size(71, 71),
						origin : new google.maps.Point(0, 0),
						anchor : new google.maps.Point(17, 34),
						scaledSize : new google.maps.Size(25, 25)
					};

					// Create a marker for each place.
					markers.push(new google.maps.Marker({
						map : map,
						//  icon: icon,
						position : place.geometry.location
					//icon:'images/house.png'
					}));

					finalvalue = place.geometry.location;
					if (place.geometry.viewport) {
						// Only geocodes have viewport.
						bounds.union(place.geometry.viewport);
					} else {
						bounds.extend(place.geometry.location);
					}
				});
				map.fitBounds(bounds);
			});
			// [END region_getplaces]

		}

		function placeMarker(location) {
			for (var k = 0; k < markers.length; k++) {
				markers[k].setMap(null);
			}
			if (count > 0) {
				marker.setMap(null);
			}
			markers.forEach(function(marker1) {
				marker1.setMap(null);
				marker.setMap(null);
			});
			for (var j = 0; j < markers.length; j++) {
				markers[j].setMap(null);

			}
			marker = new google.maps.Marker({
				position : location,
				map : map,
				title : name
			//icon:'images/house.png'
			});

			count = count + 1;
			finalvalue = location;
		}
		function setvalues() {
			document.getElementById(name + "latlong").value = finalvalue;
			name = "";
			finalvalue = "";

		}
	</script>
</body>
</html>