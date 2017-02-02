<%@page import="com.agiledge.atom.config.files.OtherFunctions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript">
function confirmValidate() {
	var flag = true;
		}
		</script>

<title>Change Password</title>
</head>
<body>
	
		<div class="container">
		<div class="header">
			<table width="100%">
				<tr>
					<td width="10%"><a href="http://www.agiledgesolutions.com"><img
							src="images/agile.png" /></a></td>

					<td width="90%">
						<h1 style="color: #FF4000;">ATOm</h1>
						<h4>Agiledge transport optimization manager</h4>
					</td>
			</table>
			<div class="clear"></div>
		</div>


		<div class="clr"></div>


	</div>
	
<h3 align="center">Forgot Password</h3>
<form name="ChangePassword" action="resetPassword" method="post" onSubmit="return confirmValidate()">
<table align="center">
<tr>
<td align="right"><b>Email</b></td><td align="left"><input type="text" name="email" id="email"/></td>
</tr>

</table>
<table align="center">
<tr align="center">
<td colspan="3">
<input type="submit" value="Submit"  class="formbutton" />&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
</table>
</form>
</body>
</html>