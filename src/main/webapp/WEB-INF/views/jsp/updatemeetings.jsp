<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<spring:url value="/savemeetingupdates" var="updateMeetingAction" />
<spring:url value="/fileUpload" var="fileUploadAction" />
<spring:url value="/resources/core/js" var="jsUrl" />
<spring:url value="/resources/core/css" var="coreCss" />
<spring:url value="/cust" var="updateCustLink" />
<spring:url value="/downloadFile" var="downloadFileUrl" />
 
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

var downloadLink = "${downloadFileUrl}";

window.getQueryParameters = function(str) {
	  return (str || document.location.search).replace(/(^\?)/,'').split("&").map(function(n){return n=n.split("="),this[n[0]]=n[1],this;}.bind({}))[0];
	};

var x = 3;
var max = 7;

        $(document).ready(function() {
        	
        	if($("#fileList option").length > 0) {
        		var fileCount = $("#fileList option").length;
        		max = 7-fileCount;
        	}
        	
        	x = $(".fileUpload").length;
        	
            $("#addMore").click(function() {
                	if (x < max) {
                    $("#fileUpload1").append("<div class='fileUpload'><div class='dir-left form-label'><label for='image'>Upload File:</label></div><div>"
                    	+ "<div><select id='file_Type' name='fileTypes[" + x + "]' class='dir-left'>"
                    	+ "<option value='Agenda'>Agenda</option><option value='Bio'>Bio</option><option value='Pre Meeting Notes'>Pre Meeting Notes</option>"
                    	+ "<option value='Post Meeting Notes'>Post Meeting Notes</option><option value='Client Profile'>Client Profile</option><option value='General Information'>General Information</option>"
                    	+ "<option value='Others'>Others</option></select>"
                    	+ "</div><div> <input name='files[" + x + "]' type='file' /></div></div><div class='clear'></div></div>");
                	x += 1; 
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
                         
                        <form:form id="addmeeting_form" action="${updateMeetingAction}" 
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
                             <div>
                                <div class="dir-left form-label">&nbsp;</div>
                            	<button id="addMore" class="dir-left" type="button">Add More Files</button>
                            	<div class="clear"></div>
                            </div>
                            
                             
                            <div id="fileUpload1">
                            
                            <c:if test="${not empty meeting.fileList}">
                            <div><b>Already Uploaded Files</b></div><br>
                            <div>
	                            <select id="fileList" name="fileList" class="dir-left">	                                            		
									<c:forEach items="${meeting.fileList}" var="file">
									<option value="${file.fileId}">${file.fileName} - ${file.fileType}</option>
									</c:forEach>									
								</select>
								<a href="#" id="fileDownload"><span class="dir-left edit-delete-btns download-btn" title="Download"></span></a>
								<div class="clear"></div>
							</div>
                            
        					<br>
        					</c:if>				
                            <c:if test="${fileCount < 7}">
                            <c:choose>
                            <c:when test="${fileCount > 3}">
                            	<c:forEach items="$(fileRemList)" var="name" varStatus="loop">
                            	<div class="fileUpload">
								<div class="dir-left form-label"><label for="image">Upload File:</label></div>
	                               <div>
	                               <div>
									<select id="file_Type" name="fileTypes[${i}]" class="dir-left">
									<option value="Agenda">Agenda</option>
									<option value="Bio">Bio</option>
									<option value="Pre Meeting Notes">Pre Meeting Notes</option>
									<option value="Post Meeting Notes">Post Meeting Notes</option>
									<option value="Client Profile">Client Profile</option>
									<option value="General Information">General Information</option>
									<option value="Others">Others</option>							
									</select>
									</div>	
									<div><input name="files[${loop.index}]" type="file" /></div>
	                               </div>                             
	                               <div class="clear"></div>
	                            </div>
   									
								</c:forEach>
   							</c:when>
   							<c:otherwise>
   							<c:forEach var="i" begin="0" end="2">
                            	<div class="fileUpload">
								<div class="dir-left form-label"><label for="image">Upload File:</label></div>
	                               <div>
	                               <div>
									<select id="file_Type" name="fileTypes[${i}]" class="dir-left">
									<option value="Agenda">Agenda</option>
									<option value="Bio">Bio</option>
									<option value="Pre Meeting Notes">Pre Meeting Notes</option>
									<option value="Post Meeting Notes">Post Meeting Notes</option>
									<option value="Client Profile">Client Profile</option>
									<option value="General Information">General Information</option>
									<option value="Others">Others</option>							
									</select>
									</div>	
									<div><input name="files[${i}]" type="file" /></div>
	                               </div>                             
	                               <div class="clear"></div>
	                            </div>
   									
								</c:forEach>
   							
   							
   							</c:otherwise>
   							</c:choose>
							</c:if>

                            </div>
                             <form:hidden path="customer.custID" id="custId"/>
                             <form:hidden path="meetingId" id="meetingId"/>
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
        
        $( "#meetDt").datepicker();
        var queryP = getQueryParameters('');
        //var custId =  window.location.pathname.substr(window.location.pathname.lastIndexOf("/") + 1);
        var custId = queryP.custId;
        
        $("#cancel").attr("href", updateCustLink + "/"  + custId + "?mflag=1");  
        
        $("#fileDownload").click(function() {
        	var selFileId = $("#fileList").val();
        	var url = downloadLink + "/" + selFileId;
        	window.open(url, 'Download');
        	
        	return false;
        });
        
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