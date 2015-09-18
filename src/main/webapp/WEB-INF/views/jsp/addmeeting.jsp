<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <script>
                          
x = 3;

        $(document).ready(function() {
                $("#addMore").click(function() {
                	if (x < 7) {
                    $("#fileUpload1").append("<div class='fileUpload'><div class='dir-left form-label'><label for='image'>Upload File:</label></div><div>"
                    	+ "<div><select id='file_Type' name='fileTypes[" + x + "]' class='dir-left'>"
                    	+ "<option value='Agenda'>Agenda</option><option value='Bio'>Bio</option><option value='Pre Meeting Notes'>Pre Meeting Notes</option>"
                    	+ "<option value='Post Meeting Notes'>Post Meeting Notes</option><option value='Client Profile'>Client Profile</option><option value='General Information'>General Information</option>"
                    	+ "<option value='Others'>Others</option></select>"
                    	+ "</div><div> <input name='files[" + x + "]' type='file' /></div></div><div class='clear'></div></div>");
                x += 1;
                //alert(x); 
                	} 
                });
                
            
           
        });
    
   
  
</script>
</head>
<body>

<%-- <spring:url value="/fileUploadForm" var="fileuploadUrl" /> --%>


    <div class="main-container">
        <div>
            <img src="<c:url value="/resources/images/logo.png" />" class="header-padding" />
           
            <div class="sub-header">Meeting Entry</div>
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
                        
                        <h4 class="padding-top-big">Meeting Information</h4>
                         
                        <form:form id="addmeeting_form" action="${addMeetingAction}" 
                        enctype="multipart/form-data"  method="POST">
               
                           <div class="padding-top-big">
                                <div class="dir-left form-label"><form:label path="contactName">Contact Name: <span style="color: red">*</span></form:label></div>
                                <form:input path="contactName" class="dir-left form-input"  data-bvalidator="alphanum,minlength[3],required"/>
                                <form:errors path="contactName" cssClass="error"/>
                                 <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="day">Date: <span style="color: red">*</span></form:label></div>
                                <form:input path="day" id="meetDt" class="dir-left form-input" data-bvalidator="date[mm/dd/yyyy],required" />
                                <form:errors path="day" cssClass="error"/>
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="agenda">Meeting Topic: <span style="color: red">*</span></form:label></div>
                                <form:input path="agenda" class="dir-left form-input"  data-bvalidator="alphanum,minlength[5],required"/>
                                <form:errors path="agenda" cssClass="error"/>
                                <div class="clear"></div>
                               
                            </div><br>
                             <div class="fileUpload">
                                <div class="dir-left form-label">&nbsp;</div>
                            	<button id="addMore" class="dir-left" type="button">Add More Files</button>
                            	<div class="clear"></div>
                            </div>
                            
                             
                            <div id="fileUpload1">
                            
                            <div class="fileUpload">
								<div class="dir-left form-label"><label for="image">Upload File:</label></div>
                               <div>
                               <div>
								<select id="file_Type" name="fileTypes[0]" class="dir-left">
								<option value="Agenda">Agenda</option>
								<option value="Bio">Bio</option>
								<option value="Pre Meeting Notes">Pre Meeting Notes</option>
								<option value="Post Meeting Notes">Post Meeting Notes</option>
								<option value="Post Meeting Notes">Client Profile</option>
								<option value="General Information">General Information</option>
								<option value="Others">Others</option>							
								</select>
								</div>	
								<div><input name="files[0]" type="file" /></div>
                               </div>                             
                               <div class="clear"></div>
                            </div>
                            
                            <div class="fileUpload">
                                <div class="dir-left form-label"><label for="image">Upload File:</label></div>
                                  <div><div>
                                  <select id="file_Type" name="fileTypes[1]" class="dir-left">
									<option value="Agenda">Agenda</option>
									<option value="Bio">Bio</option>
									<option value="Pre Meeting Notes">Pre Meeting Notes</option>
									<option value="Post Meeting Notes">Post Meeting Notes</option>
									<option value="Client Profile">Client Profile</option>
									<option value="General Information">General Information</option>
									<option value="Others">Others</option>							
								</select></div><div> <input name="files[1]" type="file" /></div></div>                           
                                <div class="clear"></div>
                            </div>
                            
                            <div class="fileUpload">
                                <div class="dir-left form-label"><label for="image">Upload File:</label></div>
                                  <div><div>
                                  <select id="file_Type" name="fileTypes[2]" class="dir-left">
									
									<option value="Agenda">Agenda</option>
									<option value="Bio">Bio</option>
									<option value="Pre Meeting Notes">Pre Meeting Notes</option>
									<option value="Post Meeting Notes">Post Meeting Notes</option>
									<option value="Client Profile">Client Profile</option>
									<option value="General Information">General Information</option>
									<option value="Others">Others</option>		                                     
                                </select>
                                </div><div> <input name="files[2]" type="file" /></div></div>                            
                                <div class="clear"></div>
                            </div>
                            </div>
                             <form:hidden path="customer.custID" id="custId"/>
                 			<br>
				            <div>
				            <div class="form-btn dir-left" id="addMeeting" style="margin-left: 0px;">Submit</div>
				            <a class="form-btn dir-left" id="cancel" href="${updateCustLink}">Cancel</a>
				            <div class="clear"></div>
				            </div>
  
  <%--              <input type="submit" value="Add Meeting" id="addMeeting" />&nbsp;&nbsp;
 <a href="${fileuploadUrl}"><input type="button" value="Upload file" id="fileupload" /></a>&nbsp;&nbsp; --%>
              
       	</form:form>
       		 
       		 
							
<script type="text/javascript"> 
var updateCustLink = "${updateCustLink}";
 

    $(document).ready(function () {
        $('#addmeeting_form').bValidator();
       
        $("#addMeeting").click(function() {
        	//alert("Meeting Added Successfully.")
        	 $('#addmeeting_form').submit();
        });
        
        /* $("#cancel").click(function() {        	
        	window.history.back();
        }); */
        
        $( "#meetDt").datepicker();
        
        var custId =  window.location.pathname.substr(window.location.pathname.lastIndexOf("/") + 1);
        
        $("#cancel").attr("href", updateCustLink + "/"  + custId + "?mflag=1");        
        
    });
</script>					
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