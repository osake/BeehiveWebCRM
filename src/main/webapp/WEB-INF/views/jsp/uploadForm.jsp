<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mentrics</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/core/css/layout.css" />" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="jquery-1.2.6.min.js"></script>

</head>
<body>


	<spring:url value="/updateMeeting" var="editmeetingUrl" />

	<div class="main-container">
		<div>
			<img src="<c:url value="/resources/core/images/logo.png" />"
				class="header-padding" />

			<div class="sub-header">Data Entry / Update Form</div>
		</div>
		<div class="content">
			<div class="content-padding">
				<div class="left-block padding-top-big dir-left">
					<div class="ele-padding">
						<h4>Details</h4>
						<div class="ele-padding left-block-listitem">
							<div class="padding-bottom ele-divider">Information</div>
							<div class="padding-bottom padding-top ele-divider">Engagement
								/ Activities</div>
							<div class="padding-bottom padding-top ele-divider">History</div>
							<div class="padding-bottom padding-top ele-divider">Contact</div>
							<div class="padding-bottom padding-top ele-divider">Relationships</div>
							<div class="padding-bottom padding-top ele-divider">Existing</div>
						</div>
						<br />
						<br />
						<br />
						<h4>Workflows</h4>
					</div>
				</div>
				<div class="right-block padding-top-big dir-right">

					<div class="ele-padding">
						<div>
							<div class="dir-left tab-btn tab-btn-active">General</div>
							<div class="dir-left tab-btn">Details</div>
							<div class="dir-left tab-btn">Contact</div>
							<div class="dir-left tab-btn">Notes</div>
							<div class="clear"></div>
						</div>
 					<div class="ele-padding tab-content">
						<h4 class="padding-top-big">Please select a file to upload !</h4>


						<form:form method="post" enctype="multipart/form-data"
							modelAttribute="uploadedFile" action="fileUpload.htm">
							<table>
								<tr>
									<td>Upload File:</td>
									<td><input type="file" name="file" /></td>
									<td style="color: red; font-style: italic;"><form:errors
											path="file" /></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
							
							<input type="submit" value="Upload" />
						</form:form>

						<div></div>
						<br />

					</div>

				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="footer">© Copyright - Otsuka</div>
	</div>
</body>
</html>


