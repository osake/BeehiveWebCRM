<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<spring:url value="/addMeeting" var="addMeetingAction" />
<spring:url value="/fileUpload" var="fileUploadAction" />
<spring:url value="/resources/core/js" var="jsUrl" />
<spring:url value="/resources/core/css" var="coreCss" />
<spring:url value="/cust" var="updateCustLink" />

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

<script src="${jsUrl}/jquery-2.1.4.js"></script>
<script src="${jsUrl}/jquery.cleditor.js"></script>
<script src="${jsUrl}/jquery.bvalidator.js"></script>
<script src="${jsUrl}/jquery.validate.min.js"></script>
<script src="${jsUrl}/jquery.fileDownload.js"></script>
<script src="${jsUrl}/jquery-ui.min.js"></script>
<script src="${jsUrl}/jquery.fileDownload.js"></script>
<script src="${jsUrl}/jquery-ui.min.js"></script>

<script>
	window.getQueryParameters = function(str) {
		return (str || document.location.search).replace(/(^\?)/, '')
				.split("&").map(function(n) {
					return n = n.split("="), this[n[0]] = n[1], this;
				}.bind({}))[0];
	};
</script>

<link href="<c:url value="/resources/core/css/bvalidator.css" />"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/core/js/jquery.bvalidator-yc.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/core/css/layout.css" />" />
<link href="${coreCss}/jquery-ui.min.css" type="text/css"
	rel="stylesheet" />

</head>
<body>

	<%-- <spring:url value="/fileUploadForm" var="fileuploadUrl" /> --%>


	<div class="main-container">
		<div>
			<img src="<c:url value="/resources/images/logo.png" />"
				class="header-padding" />


			<div class="sub-header">Prospect Data</div>
		</div>
		<div class="content">
			<div class="content-padding" style="padding-top: 100px;">
				<div class="full-width padding-top-big">

					<div class="ele-padding">
						<form>
							<div class="addcust_linkcontainer">
								<div class="dir-right">

									<img src="<c:url value="/resources/images/add_customer.png" />"
										class="header-padding" style="height: 70px;" />
									<div class=" ele-margin  form-btn" id="addCust">Add
										Customer</div>
								</div>
							</div>

						</form>
						<div class="ele-padding tab-content pros_dropdwn">
							<h4 class="padding-top-big pros_dropdwnheading">Select a
								Prospect to update</h4>

							<div class="padding-bottom pros_wrap">
								<div class="dir-left form-label pros_label">Prospect:</div>
								<select class="dir-left form-input form-dropdown" id="custSel">
									<option value="">--Please Select a Prospect--</option>
									<c:forEach items="${customerList}" var="cust">
										<option value="${cust.custID}">${cust.custName}</option>
									</c:forEach>
								</select>
								<div class="clear"></div>
							</div>
							<div></div>
							<br />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">© Copyright - Otsuka</div>
		<div id="dialog" title="Confirmation" style="display: none">
			<p>Data was updated successfully.</p>
		</div>
	</div>
	<script type="text/javascript">
		var updateLink = "${updateCustLink}";

		$(document).ready(function() {

			$("#custSel").change(function() {
				var custId = $("#custSel").val();
				if (custId) {
					//custId = parseInt(custId);
					window.location.replace(updateLink + "/" + custId);
				}
			});

			$(function() {
				var queryP = getQueryParameters('');
				if (queryP.data == '1') {
					$("#dialog").dialog();
				}
			});

		});
	</script>
	<script>
		function gotoAdd() {
			var loc = location.href;
			loc = loc.replace("customersel", "addCustomer");

			location.href = loc;

		}

		$("#addCust").click(function() {

			gotoAdd();

		});
	</script>

</body>
</html>