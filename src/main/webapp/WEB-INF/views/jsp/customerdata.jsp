<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/resources/core/css" var="coreCss" />
<spring:url value="/resources/images" var="imageUrl" />
<spring:url value="/resources/core/js" var="jsUrl" />
<spring:url value="/resources/uf" var="fileUrl" />

<spring:url value="/submitupdatedcustomer" var="submitUrl" />

<spring:url value="/addMeetingView/${command.custID}" var="addmeetingUrl" />
<spring:url value="/downloadFile" var="downloadFileUrl" />


<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>Mentrics</title>
    <link href="${coreCss}/layout.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="${coreCss}/jquery.cleditor.css" rel="stylesheet" />
    <link href="${coreCss}/bvalidator.css" rel="stylesheet" type="text/css" />    
    <link href="${coreCss}/jquery-ui.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    var downloadLink = "${downloadFileUrl}";
    window.getQueryParameters = function(str) {
  	  return (str || document.location.search).replace(/(^\?)/,'').split("&").map(function(n){return n=n.split("="),this[n[0]]=n[1],this;}.bind({}))[0];
  	};
  	
  	var customStepDtValidator = function() {
    	var startDt = $("#startDt").val();
    	var endDt = $("#endDt").val();
    	
    	if(startDt && endDt && (Date.parse(startDt) >= Date.parse(endDt))) {
    		return false;
    	}
    	
    	return true;
    }
  	
    </script>
</head>
<body>
    <div class="main-container">
        <div>
            <img src="${imageUrl}/logo.png" class="header-padding" />
            <div class="sub-header">Prospect Data</div>
        </div>
        <div class="content">
            <div class="content-padding">
            	<div class="full-width padding-top-big">
                    <div class="ele-padding">
                        <ul class="tab-block">
                            <li  id="generalInfo" class="tab-btn tab-btn-active" style="border-left: 1px solid #48B1E0;" onclick='tabClick(this,"general-info")'>Weekly Updates</li>
                            <li class="tab-btn" onclick='tabClick(this,"address-info")'>Address</li>
                            <li id="meetingInfo" class="tab-btn" onclick='tabClick(this,"meeting-info")'>Meetings</li>
                            <li class="tab-btn" style="border-right: 1px solid #48B1E0;" onclick='tabClick(this,"general")'>General</li>
                        </ul>
                        <form:form action="${submitUrl}" id="customerForm" method="POST">
                        <div class="tab-content" >
                            <div class="ele-padding">
                                <div id="general-info">
                                    <h4 class="padding-top-big">Weekly Updates</h4>
                                    <br />
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Account Name: <span style="color: red">*</span></div>
                                        <form:input id="accountName" class="dir-left form-input" path="custName" data-bvalidator="required" required="required"  type="text" readonly="true"/>
                                        
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <%-- <div>
                                        <div class="dir-left form-label">Account Number:</div>
                                        <form:input class="dir-left form-input" path="custCode" data-bvalidator="alphanum,required" required="required" type="text"/>
                                        <div class="clear"></div>
                                    </div> 
                                     
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Account Type: <span style="color: red">*</span></div>
                                        <form:select class="dir-left form-input form-dropdown" path="accType" data-bvalidator="required">
				              				<form:option value="" label="Please Select"/>
				              				<form:option value="Active" label="Active"/>
				              				<form:option value="Passive" label="Passive"/>                                        
                                        </form:select>
                                        <div class="clear"></div>
                                    </div>
                                    --%>
                                    
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Step: <span style="color: red">* </span></div>
                                        <form:select path="stage" data-bvalidator="required" class="step dir-left form-input form-dropdown" id="stepField">
                                        	<form:option value="" label="--Please Select Stage--" />
											<form:option value="1" label="1. Demographic profile done" />
											<form:option value="2" label="2. Contact identified" />
											<form:option value="3" label="3. Call made to contact" />
											<form:option value="4" label="4. Telephone discussion with contact completed" />
											<form:option value="5" label="5. Prospect qualification done" />
											<form:option value="6" label="6. Meeting scheduled" />
											<form:option value="7" label="7. Meeting held" />
											<form:option value="8" label="8. Product demonstration done" />
											<form:option value="9" label="9. Agreement to receive proposal" />
											<form:option value="10" label="10. BAA sent" />
											<form:option value="11" label="11. BAA signed" />
											<form:option value="12" label="12. Data received for proposal" />
											<form:option value="13" label="13. Data analysis conducted" />
											<form:option value="14" label="14. Proposal completed" />
											<form:option value="15" label="15. Proposal reviewed with customer" />
											<form:option value="16" label="16. Commitment made" />
											<form:option value="17" label="17. Agreement sent" />
											<form:option value="18" label="18. Agreement signed" />
											<form:option value="19" label="19. Pre-Impementation Planning" />
											<form:option value="20" label="20. Implementation launched" />
											<form:option value="21" label="21. Post Sales - Client value realization and satisfaction" />
											<form:option value="22" label="22. Identify opportunity for up-sell" />
										</form:select>
                                        <div class="clear"></div>
                                    </div>                                    
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Step Start Date: <span style="color: red">* </span></div>
                                        <form:input class="dir-left form-label stepStartDate"  path="startTime" type="text" id="startDt"
                                         style="width: 158px;height: 25px;" data-bvalidator="customStepDtValidator,date[mm/dd/yyyy],required"
                                         data-bvalidator-msg="Step Start Date is required and should be before Step End Date"/>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Step End Date: </div>
                                        <form:input class="dir-left form-label stepStartDate"  path="endTime" type="text" id="endDt"
                                         style="width: 158px;height: 25px;" data-bvalidator="customStepDtValidator,date[mm/dd/yyyy]" 
                                         data-bvalidator-msg="Step End Date should be after Step Start Date"/>                                        
                                        <div class="clear"></div>
                                    </div>
                                    <%-- 
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Priority: <span style="color: red">*</span></div>
                                        <form:select path="priority" data-bvalidator="required">
											<form:option value="" label="Please Select" />
											<form:option value="0" label="Qualification not done" />
											<form:option value="1" label="Key need + commtiment possible by 12/15 + no CMT" />
											<form:option value="2" label="Key need + no CMT" />
											<form:option value="3" label="Key need + CMT" />
											<form:option value="4" label="Lost sale" />
											<form:option value="5" label="Not a prospect -- not qualified or interested" />
										</form:select>
                                        <div class="clear"></div>
                                    </div>
                                     --%>
                                    <div class="padding-bottom ele-hide" id="expContractVal">
                                        <div class="dir-left form-label">Expected Contract Value (in Millions): <span style="color: red">* </span></div>
                                        <form:input class="dir-left form-input" path="contractVal" data-bvalidator="required, number, min[0]" required="required" type="number"/>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Notes:</div>
                                        <form:textarea class="dir-left form-input" path="notes" rows="5" cols="51" style="height: auto;" />
                                        <div class="clear"></div>
                                    </div>
                                                                        
                                </div>
                                <div class="ele-hide" id="address-info">
                                    <h3 class="padding-top-big">Address Information</h3>
                                    <br />
                                    
                                    <div class="padding-bottom"><b>Primary Contact</b></div>
                                    	<div class="padding-bottom">
                                        	<div class="dir-left form-label"><i class="padding-left">Title:&nbsp;</i><span title="Designation e.g. CEO, CMO etc.">(?)</span></div>
                                        	<form:input class="dir-left form-input" path="title" type="text"/>
                                        	<div class="clear"></div>
                                    	</div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label"><i class="padding-left">Prefix:</i></div>
                                         <form:select class="dir-left form-input form-dropdown" path="prefix">
				              				<form:option value="" label="Please Select"/>
				              				<form:option value="Dr" label="Dr."/>
				              				<form:option value="Mr" label="Mr."/>
				              				<form:option value="Ms" label="Ms."/>                                        
				              				<form:option value="Mrs" label="Mrs."/>
				              				<form:option value="Miss" label="Miss"/>                                        
                                        </form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label"><i class="padding-left">First Name:</i></div>
                                        <form:input class="dir-left form-input" path="firstName" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label"><i class="padding-left">Last Name:</i></div>
                                        <form:input class="dir-left form-input" path="lastName" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                   
                                        <div class="padding-bottom">
                                        <div class="dir-left form-label "><i class="padding-left">Office Phone:</i> </div>
                                        <form:input   class="dir-left form-input required  testPhone" path="phone" size="12" maxlength="12" 
                                         data-bvalidator="regex[^\(\d{3}\) ?\d{3}( |-)?\d{4}|^\d{3}( |-)?\d{3}( |-)?\d{4}],minlength[10],maxlength[12]"
                                          data-bvalidator-msg-minlength="Please enter a phone number that is at least Ten characters long."
                                          data-bvalidator-msg-max="Please use a password that is less than 15 characters long." 
                                          data-bvalidator-msg="Please enter Pnone Number in xxx-xxx-xxxx Format only with maximum 10 digits."
                                         type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label "><i class="padding-left">Cell Phone:</i></div>
                                        <form:input class="dir-left form-input required  cellPhone" path="cellPhone" size="12" maxlength="12" 
                                         data-bvalidator="regex[^\(\d{3}\) ?\d{3}( |-)?\d{4}|^\d{3}( |-)?\d{3}( |-)?\d{4}],minlength[10],maxlength[12]"
                                          data-bvalidator-msg-minlength="Please enter a phone number that is at least Ten characters long."
                                          data-bvalidator-msg-max="Please use a password that is less than 15 characters long." 
                                          data-bvalidator-msg="Please enter Pnone Number in xxx-xxx-xxxx Format only with maximum 10 digits."
                                         type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                      
                                      
                                      
                                      <div class="padding-bottom"><b>Secondary Contact</b></div>
                                         <div class="padding-bottom">
                                        	<div class="dir-left form-label"><i class="padding-left">Title:</i><span title="Designation e.g. CEO, CMO etc.">(?)</span></div>
                                        	<form:input class="dir-left form-input" path="secondaryContactTitle" type="text"/>
                                        	<div class="clear"></div>
                                    	</div>
                                    	<div class="padding-bottom">
	                                        <div class="dir-left form-label"><i class="padding-left">Prefix:</i></div>
	                                         <form:select class="dir-left form-input form-dropdown" path="secondaryPrefix">
					              				<form:option value="" label="Please Select"/>
					              				<form:option value="Dr" label="Dr."/>
					              				<form:option value="Mr" label="Mr."/>
					              				<form:option value="Ms" label="Ms."/>                                        
					              				<form:option value="Mrs" label="Mrs."/>
					              				<form:option value="Miss" label="Miss"/>                                        
	                                        </form:select>
	                                        <div class="clear"></div>
                                    	</div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label"><i class="padding-left">First Name:</i></div>
                                        <form:input class="dir-left form-input" path="secondaryFirstName" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label"><i class="padding-left">Last Name:</i></div>
                                        <form:input class="dir-left form-input" path="secondaryLastName" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    
                                      <div class="padding-bottom">
                                        <div class="dir-left form-label "><i class="padding-left">Office Phone:</i></div>
                                        <form:input   class="dir-left form-input required  sectestPhone" path="secphone" size="12" maxlength="12" 
                                           data-bvalidator="regex[^\(\d{3}\) ?\d{3}( |-)?\d{4}|^\d{3}( |-)?\d{3}( |-)?\d{4}],minlength[10],maxlength[12]"
                                          data-bvalidator-msg-minlength="Please enter a phone number that is at least Ten characters long."
                                          data-bvalidator-msg-max="Please use a password that is less than 15 characters long." 
                                          data-bvalidator-msg="Please enter Pnone Number in xxx-xxx-xxxx Format only with maximum 10 digits."
                                         type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label "><i class="padding-left">Cell Phone:</i></div>
                                        <form:input class="dir-left form-input required  seccelltestPhone" path="seccelphone" size="12" maxlength="12" 
                                         data-bvalidator="regex[^\(\d{3}\) ?\d{3}( |-)?\d{4}|^\d{3}( |-)?\d{3}( |-)?\d{4}],minlength[10],maxlength[12]"
                                          data-bvalidator-msg-minlength="Please enter a phone number that is at least Ten characters long."
                                          data-bvalidator-msg-max="Please use a password that is less than 15 characters long." 
                                          data-bvalidator-msg="Please enter Pnone Number in xxx-xxx-xxxx Format only with maximum 10 digits."
                                         type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                        <div class="padding-bottom">
                                        <div class="dir-left form-label">Point of Contact:</div>
                                        Primary&nbsp;<form:radiobutton  path="pointOfContact"  id="test" value="Primary"/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;Secondary&nbsp;<form:radiobutton path="pointOfContact" value="Secondary"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Address Line 1:</div>
                                        <form:input class="dir-left form-input" path="address1" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Address Line 2:</div>
                                        <form:input class="dir-left form-input" path="address2" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">City:</div>
                                        <form:input class="dir-left form-input" path="city" required="required" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">State:</div>
                                        <form:select path="state"  class="dir-left form-input form-dropdown">        
						              		<form:options items="${command.states}" />
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Zip:</div>
                                        <form:input class="dir-left form-input" path="zip" data-bvalidator="min[5]" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    
		                            <div class="padding-bottom">
		                                <div class="dir-left form-label">Country</div>
		                                <form:select path="country"  class="dir-left form-input form-dropdown">        
						              		<form:option value="" label="Please Select"/>
						              		<form:option value="USA" label="USA"/>
						        	  	</form:select>
		                                <div class="clear"></div>
		                            </div>
		                            <div class="padding-bottom">
                                        <div class="dir-left form-label">Email:</div>
                                        <form:input class="dir-left form-input" path="custEmail" data-bvalidator="email" type="email"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Web Address:</div>
                                        <form:input class="dir-left form-input" path="webLink" type="text"/>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Contact Notes:</div>
                                         <form:textarea class="dir-left form-input" path="contactNotes" rows="5" cols="51" style="height: auto;"/>
                                        <div class="clear"></div>
                                    </div>
                                </div>
                                <div class="ele-hide" id="meeting-info">
                                    <h4 class="padding-top-big">Meeting Information</h4>
                                    <br />
                                    <table class="meeting-table">
                                        <tr>
                                            <th>Meeting Topic</th>
                                            <th>Date</th>
                                            <th>Contact Person</th>
                                             <th>Uploaded File</th>
                                            <th>Action</th>
                                        </tr>
                                        
                                        <%-- <c:forEach items="${fileListContainer}" var="file" varStatus="i" begin="0" >
	                                        <tr>
	                                            <td>${file.meeting.agenda}</td>
	                                            <td><spring:eval expression="file.meeting.day" /></td>
	                                            <td>${file.meeting.contactName}</td>
	                                            <td ><a href="${downloadFileUrl}/${file.fileId}" target="_blank"   rel="nofollow">${file.fileName}</a></td>
	                                            <td>
	                                             <c:url var="removeMeetingUrl" value="/removeMeeting">
					                                    <c:param name="meetingId" value="${file.meeting.meetingId}" />
					                                    <c:param name="fileId" value="${file.fileId}" />
					                                </c:url>
					                                 <c:url var="updateMeetingUrl" value="/updateMeeting">
					                                    <c:param name="meetingId" value="${file.meeting.meetingId}" />
					                                     <c:param name="custId" value="${file.meeting.customer.custID}" />
					                                </c:url>
	                                                <a href='<c:out value="${updateMeetingUrl}" />'>
	                                                	<span class="dir-left padding-right edit-delete-btns edit-btn" title="Edit Row"></span>
	                                                	<span class="clear"></span>
	                                                </a>
	                                                <a href='<c:out value="${removeMeetingUrl}" />'>
	                                                	 <span class="dir-left padding-left edit-delete-btns delete-btn" title="Delete Row" ></span>
	                                                	 <span class="clear"></span>
	                                                </a>
	                                                <div class="clear"></div>
	                                            </td>
	                                        </tr>
                                        	
                                        </c:forEach> --%>
                                        
                                        <c:forEach items="${meetingListContainer}" var="list" varStatus="i" begin="0" >
	                                        <tr>
	                                            <td>${list.agenda}</td>
	                                            <td><spring:eval expression="list.day" /></td>
	                                            <td>${list.contactName}</td>
	                                            <td>
	                                            	<c:if test="${not empty list.fileList}">	                                            	
	                                            	<select id="fileList-${i.index}" name="fileList" class="dir-left">	                                            			                                            		
	                                            		<c:forEach items="${list.fileList}" var="file">
            												<option value="${file.fileId}">${file.fileName} - ${file.fileType}</option>
        												</c:forEach>									
	                                            	</select>
	                                            	
	                                            	<a href="#" class="fileDownload" id="fileDownload-${i.index}"><span class="dir-left edit-delete-btns download-btn" title="Download"></span></a>
	                                            	<div class="clear"></div>
	                                            	</c:if>				
	                                            </td>
	                                            
				                                              <%-- <td ><a href="${downloadFileUrl}/${list.fileList.fileId}" 
	                                              target="_blank"   rel="nofollow">${file.fileName}</a></td>--%>
	                                               <td> 
	                                           <c:url var="removeMeetingUrl" value="/removeMeeting">
					                                    <c:param name="meetingId" value="${list.meetingId}" />
					                                </c:url>  
					                                 <c:url var="updateMeetingUrl" value="/updateMeeting">
					                                    <c:param name="meetingId" value="${list.meetingId}" />
					                                     <c:param name="custId" value="${list.customer.custID}" />
					                                </c:url>
	                                                <a href='<c:out value="${updateMeetingUrl}" />'>
	                                                	<span class="dir-left padding-right edit-delete-btns edit-btn" title="Edit Row"></span>
	                                                	<span class="clear"></span>
	                                                </a>
	                                                  <a href='<c:out value="${removeMeetingUrl}" />'>
	                                                	 <span class="dir-left padding-left edit-delete-btns delete-btn" title="Delete Row" ></span>
	                                                	 <span class="clear"></span>
	                                                </a>  
	                                                <div class="clear"></div>
	                                            </td>  
	                                        </tr>
                                        	
                                        </c:forEach>
                                    </table>
                                    <a class="dir-right ele-margin  form-btn" href="${addmeetingUrl}">Add Meetings</a><div class="clear"></div>
                                    
                                </div>
                                <div class="ele-hide" id="general">
                                    <h4 class="padding-top-big">General</h4>
                                    <br />
                                    <%--  <div class="padding-bottom">
                                        <div class="dir-left form-label">Sales Lead:</div>
                                        <form:select path="state"  class="dir-left form-input">        
						              		<form:options items="${salesLead}"  itemValue="lovId" itemLabel="lovValue"/>
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                      --%>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Primary Sales Lead:&nbsp;<span style="color: red">* </span><span class="ele-hide" title="Help Text">(?)</span> </div>
                                       <%--  <form:input class="dir-left form-input" path="primaryLead" type="text"/> --%>
                                         <form:select path="primaryLead"  class="dir-left form-input form-dropdown" data-bvalidator="required">
                                         	<form:option value="">--Select Primary Lead--</form:option>    
						              		<form:options items="${salesLead}"  itemValue="lovValue" itemLabel="lovValue"/>
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Open Minds SME: <span class="ele-hide" title="Help Text">(?)</span> </div>
                                        <%-- <form:input class="dir-left form-input" path="openMindsSme" type="text"/> --%>
                                           <form:select path="openMindsSme"  class="dir-left form-input form-dropdown">
                                           	<form:option value="">--Select SME--</form:option>        
						              		<form:options items="${OPENMINDSME}"  itemValue="lovValue" itemLabel="lovValue"/>
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Otsuka SME: <span class="ele-hide" title="Help Text">(?)</span> </div>
                                       <%--  <form:input class="dir-left form-input" path="otsukaSme" type="text"/> --%>
                                        <form:select path="otsukaSme"  class="dir-left form-input form-dropdown">
                                        	<form:option value="">--Select SME--</form:option>        
						              		<form:options items="${OTSUKASME}"  itemValue="lovValue" itemLabel="lovValue"/>
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">IBM SME: <span class="ele-hide" title="Help Text">(?)</span> </div>
                                        <%-- <form:input class="dir-left form-input" path="ibmSme" type="text"/> --%>
                                        <form:select path="ibmSme"  class="dir-left form-input form-dropdown">
                                        	<form:option value="">--Select SME--</form:option>        
						              		<form:options items="${IBMSME}"  itemValue="lovValue" itemLabel="lovValue"/>
						        	  	</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <%-- <div class="padding-bottom">
                                        <div class="dir-left form-label">HMA SME: <span title="Help Text">(?)</span> </div>
                                        <form:input class="dir-left form-input" path="hmaSme" type="text"/>
                                        <div class="clear"></div>
                                    </div> --%>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Total Membership (In Thousands): <span class="ele-hide" title="Help Text">(?)</span> </div>
                                        <form:input class="dir-left form-input" path="overallPopulation" data-bvalidator="number, min[0]" type="number"/>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Behavioral Health Population (In Thousands): <span class="ele-hide" title="Help Text">(?)</span> </div>
                                        <form:input class="dir-left form-input" path="smiPopulation" data-bvalidator="number, min[0]" type="number"/>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Size: <span title="Large: 19K and above, Medium: 3K to 19K, Small: 1 to 3K">(?)</span> </div>                                      
                                        <form:select path="size" class="dir-left form-input form-dropdown" id="sizeField">
											<form:option value="" label="Please Select" />
											<form:option value="L" label="Large"/>
											<form:option value="M" label="Medium"/>
											<form:option value="S" label="Small"/>
										</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Total Estimated Customer Revenue (In Millions): <span class="ele-hide" title="Help Text">(?)</span> </div>
                                        <form:input class="dir-left form-input" path="customerRevenue" data-bvalidator="number, min[0]" type="number"/>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Account Segment: <span class="ele-hide" style="color: red">*</span></div>
                                        <form:select class="dir-left form-input form-dropdown" path="segment">
											<form:option value="" label="Please Select" />
											<form:option value="National" />
											<form:option value="State" />
										</form:select>
                                        <div class="clear"></div>
                                    </div>
                                    
                                    <div class="padding-bottom">
                                        <div class="dir-left form-label">Financial Model: <span class="ele-hide" style="color: red">*</span></div>
                                        <form:select class="dir-left form-input form-dropdown" path="financeModel">
											<form:option value="" label="--Please Select--" />
											<form:option value="ASO" label="Administrative Services Only (ASO)" />
											<form:option value="Full Risk"/>
										</form:select>
                                        <div class="clear"></div>
                                    </div>
                                                                 
                                </div>
                            </div>
                            <div class="tab-footer">
                                <div class="dir-right ele-margin  form-btn" id="update">Submit</div>
                                <div class="dir-right ele-margin  form-btn" id="resetBtn">Reset</div>
                                <div class="clear"></div>
                            </div>
                        </div>
                         <input id="custPhone" type="hidden" value="${custPhone}"/>
                          <input id="cellPhone" type="hidden" value="${cellPhone}"/>
                          <input id="secPhone" type="hidden" value="${secPhone}"/>
                          <input id="seccellPhone" type="hidden" value="${seccellPhone}"/>
                         <input id="activeTab" type="hidden" value="${activeTab}"/>
                          <input id="stepData" type="hidden" value="${stepData}"/>
                          <input id="stepStartOldDate" type="hidden" value="${stepStartOldDate}"/>
                        <form:hidden path="custID"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">© Copyright - Otsuka</div>
    </div>
    
    <div id="dialog" title="Confirmation" style="display: none">
  		<p>Data was updated successfully.</p>
	</div>
	  

	<script src="${jsUrl}/jquery-2.1.4.js"></script>
    <script src="${jsUrl}/jquery.cleditor.js"></script>
    <script src="${jsUrl}/jquery.bvalidator.js"></script>
    <script src="${jsUrl}/jquery.validate.min.js"></script>
    <script src="${jsUrl}/jquery.fileDownload.js"></script>
    <script src="${jsUrl}/jquery-ui.min.js"></script>

    <iframe id="my_iframe" style="display:none;"></iframe>
    <script type="text/javascript">
    $('.seccelltestPhone').keyup(function() {

      var foo = $(this).val().split("-").join(""); // remove hyphens
      if (foo.length == 3 || foo.length == 6|| foo.length == 9 ) {
        foo = foo.match(new RegExp('.{1,3}', 'g')).join("-");
        $(this).val(foo);
      }
      else{
    	  
      }
      //$(this).val(foo);
    });
    $('.sectestPhone').keyup(function() {
    	
    	//alert("insode");
    	//var tes ==foo;
      var foo = $(this).val().split("-").join(""); // remove hyphens
      if (foo.length == 3 || foo.length == 6|| foo.length == 9 ) {
        foo = foo.match(new RegExp('.{1,3}', 'g')).join("-");
        $(this).val(foo);
      }
      else{
    	  
      }
      //$(this).val(foo);
    });
    $('.testPhone').keyup(function() {
    	
    	//alert("insode");
    	//var tes ==foo;
      var foo = $(this).val().split("-").join(""); // remove hyphens
      if (foo.length == 3 || foo.length == 6|| foo.length == 9 ) {
        foo = foo.match(new RegExp('.{1,3}', 'g')).join("-");
        $(this).val(foo);
      }
      else{
    	  
      }
      //$(this).val(foo);
    });

    $('.cellPhone').keyup(function() {
    	
    	//alert("insode");
    	//var tes ==foo;
      var foo = $(this).val().split("-").join(""); // remove hyphens
      if (foo.length == 3 || foo.length == 6|| foo.length == 9 ) {
        foo = foo.match(new RegExp('.{1,3}', 'g')).join("-");
        $(this).val(foo);
      }
      else{
    	  
      }
 
    });
    
    
        $(document).ready(function () {
        	/* Negative value*/



        	
        	$( "#overallPopulation" ).change(function() {
        		 
        		  overPop = $( "#overallPopulation" ).val();

        if(overPop<=0)
        {
        	$( "#overallPopulation" ).val(0) 
        	
        }
          
        		});



        	$( "#smiPopulation" ).change(function() {
        		
        		  overPop = $( "#smiPopulation" ).val();

        if(overPop<=0)
        {

        	$( "#smiPopulation" ).val(0) 


        	}


        		  
        		});



        	$( "#customerRevenue" ).change(function() {
        		 overPop = $( "#customerRevenue" ).val();
        		 if(overPop<=0){
        	$( "#customerRevenue" ).val(0) 
        	
        	}});
                	
        	var stageAuditMap = ${stageAudit};    		
        	 
        	$('#resetBtn').click(function() {
        		document.getElementById("customerForm").reset();
        	});
        	
        	
        	if($('#activeTab').val().length != 0){
        		jQuery('#generalInfo').removeClass('tab-btn-active');
        		jQuery('#meetingInfo').addClass('tab-btn-active');
        		$('#general-info').hide();
        		$('#meeting-info').show();
        	}
        	else{
        		jQuery('#meetingInfo').removeClass('tab-btn-active');
        		$('#meeting-info').hide();
        	}                 
            
        	
        	$('#customerForm').bValidator();
        	  
        	$("#update").click(function() {
        		var custPh= $('#custPhone').val();
        		var cellPh= $('#cellPhone').val();
        		
        		var secPh= $('#secPhone').val();
        		var seccellPh= $('#seccellPhone').val();        		
        		var oldStep= $('#stepData').val();
        		var oldStepStartDate= $('#stepStartOldDate').val();
            	var str = $('.testPhone').val();
            	var str1=$('.cellPhone').val();
            	var str2=$('.sectestPhone').val();
            	var str3=$('.seccelltestPhone').val();
            	var step = $('.step').val();
            	var stepStartDate = $('.stepStartDate').val(); 
            	var count = (str.match(/-/g) || []).length;
            	var count1 = (str.match(/x/g) || []).length;
            	
             	var count3 = (str1.match(/-/g) || []).length;
            	var count4 = (str1.match(/x/g) || []).length;
            	
            	
            	if(count1 ==6 && count == 2 && str2.length ==12){
            		$('.sectestPhone').val(secPh);
            	}else if($.isNumeric(str2.replace(/\-/g,'')))
            		{
            		 $('.sectestPhone').val(str2.replace(/\-/g,''));
            		 
            		}
            	
            	if(count1 ==6 && count == 2 && str3.length ==12){
            		$('.seccelltestPhone').val(seccellPh);
            	}else if($.isNumeric(str3.replace(/\-/g,'')))
            		{
            		 $('.seccelltestPhone').val(str3.replace(/\-/g,''));
            		 
            		}
            	
            	if(count1 ==6 && count == 2 && str.length ==12){
            		$('.testPhone').val(custPh);
            	}else if($.isNumeric(str.replace(/\-/g,'')))
            		{
            		 $('.testPhone').val(str.replace(/\-/g,''));
            		 
            		}
            	
            	if(count4 ==6 && count3 == 2 && str.length ==12){
            		$('.cellPhone').val(cellPh);
            	}else if($.isNumeric(str1.replace(/\-/g,'')))
            		{
            		 $('.cellPhone').val(str1.replace(/\-/g,''));
            		 
            		}
            	$("#customerForm").submit();
            	
            	//alert("Customer Informaton submitted Successfully.");
            	/* if(step != oldStep ) {
            		 
            		 if(stepStartDate != null){
            			  
        			$("#customerForm").submit();
            		 }else{
            			 $('.stepStartDate').removeAttr('value');
                 		$('#customerForm').bValidator();
            		 }
            		
            	} else {
            		
            	} */
        			
        		
      		});
        	
        	var checkContractValFieldDisplay = function() {
	        	var stepVal = $("#stepField").val();
	        	if(stepVal && (parseInt(stepVal) > 13)) {
	        		$("#expContractVal").show();
	        	} else {
	        		$("#expContractVal").hide();
	        	}
        	}
        	
        	var updateStepDates = function() {
        		//console.log(stageAuditMap);
        		var stageIdArr = stageAuditMap["stageId"];
        		var stageStartArr = stageAuditMap["stageStart"];
        		var stageEndArr = stageAuditMap["stageEnd"];
        		
        		$("#startDt").val("");
        		$("#endDt").val("");
        		
        		var stepVal = $("#stepField").val();
        		if(stageIdArr.indexOf(stepVal) > -1 ) {
        			var index = stageIdArr.indexOf(stepVal);
        			if(stageStartArr[index]) {
        				$("#startDt").val(stageStartArr[index]);
        			}
        			if(stageEndArr[index]) {
        				$("#endDt").val(stageEndArr[index]);
        			}        			
        		}
        	}
        	
        	checkContractValFieldDisplay();
        	
        	$("#stepField").change(function() {
        		updateStepDates();
        		checkContractValFieldDisplay();
        		
        	});
        	
        	
        	$(".fileDownload").click(function() {
        		var currId = $(this).attr("id");
        		if(currId && (currId.indexOf("-") > -1)) {
        			var currIndex = currId.substring(currId.indexOf("-"));
        			var selFileId = $("#fileList" + currIndex).val();
        		var url = downloadLink + "/" + selFileId;
        		window.open(url, 'Download');
        		}
        		
        		return false;
        	});

        });
        function tabClick(event, infoBoxId) {
        	
        	var validFlag = $('#customerForm').data('bValidator').validate();
        	if(!validFlag) {
        		return false;
        	}
        	
        	var boxId="meeting-info";
        	var addressBox = "address-info";
              if(infoBoxId == boxId){
            	  $('#update').hide();
            	  $('#reset').hide();
            	  $("#" + infoBoxId).show();
                  $(event).addClass("tab-btn-active");
                  $("#" + infoBoxId).siblings('div').hide();
                  $(event).siblings('li').removeClass("tab-btn-active");
        	
              }else{
            $("#" + infoBoxId).show();
            $(event).addClass("tab-btn-active");
            $("#" + infoBoxId).siblings('div').hide();
            $(event).siblings('li').removeClass("tab-btn-active");
                  $('#update').show();
            	  $('#reset').show();
              }  
              
              /* if(infoBoxId == addressBox) {
            	  $('.tab-content').css('min-height', '600px');
              } else {
            	  $('.tab-content').css('min-height', '480px');
              } */
           
        	
        }

        function deleteMeetingRow(event) {
            $(event).parents('tr').remove();
        }
        $(function() {
        	var queryP = getQueryParameters('');
        	if(queryP.data == '1') {
        		$("#dialog").dialog();
        	}
        	
        	if(queryP.mflag == '1') {
        		$("#meetingInfo").click();
        	}
        	
        	
        });
        
        $( "#startDt").datepicker();
        $( "#endDt").datepicker();
        
        $(function() {
            $( document ).tooltip();
          });
        
        
        
        
    </script>
     
</body>

</html>
