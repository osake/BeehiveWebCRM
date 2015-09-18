<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<spring:url value="/addMeeting" var="addMeetingAction" />
<spring:url value="/fileUpload" var="fileUploadAction" />
<spring:url value="/resources/core/js" var="jsUrl" />
<spring:url value="/resources/core/css" var="coreCss" />
<spring:url value="/customer" var="updateCustLink" />
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mentrics</title>
<style>
    .error {color: red; font-weight: bold;}
</style>

	<script src="${jsUrl}/jquery-2.1.4.js"></script>
    <script src="${jsUrl}/jquery.cleditor.js"></script>
    <script src="${jsUrl}/jquery.bvalidator.js"></script>
    <script src="${jsUrl}/jquery.validate.min.js"></script>
    <script src="${jsUrl}/jquery.fileDownload.js"></script>
    <script src="${jsUrl}/jquery-ui.min.js"></script>
    <script src="${jsUrl}/jquery.fileDownload.js"></script>
    <script src="${jsUrl}/jquery-ui.min.js"></script>

<link href="<c:url value="/resources/core/css/bvalidator.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/core/js/jquery.bvalidator-yc.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/core/css/layout.css" />" />
<link href="${coreCss}/jquery-ui.min.css" type="text/css" rel="stylesheet" />
</head>
<body>

<%-- <spring:url value="/fileUploadForm" var="fileuploadUrl" /> --%>


    <div class="main-container">
        <div>
            <img src="<c:url value="/resources/images/logo.png" />" class="header-padding" />
           
            <div class="sub-header">Error</div>
        </div>
        <div class="content">
            <div class="content-padding">
            <div class="full-width padding-top-big">
		              
                    <div class="ele-padding">
                        <div style="display: none;">
                            <div class="dir-left tab-btn tab-btn-active">Add Meeting</div>
                            <!-- <div class="dir-left tab-btn">Details</div>
                            <div class="dir-left tab-btn">Contact</div>
                            <div class="dir-left tab-btn">Notes</div> -->
                            <div class="clear"></div>
                        </div>
                    
                       
                        <div class="ele-padding tab-content" style="border: 0px;">
                        <h3>${message}</h3>
                        
                        <h4 class="padding-top-big">An error occurred while processing. Please try again later or contact Administrator</h4>
                        <h3>${exception.message}</h3>
                        <div><c:forEach items="${exception.stackTrace}" var="ste">${ste}</c:forEach></div>
                        
    			
                            <div>
            
                            </div><br />
  
                        </div>
                         
                    </div>
		        </div>
                
            </div>
        </div>
        <div class="footer">© Copyright - Otsuka</div>
    </div>
</body>
</html>