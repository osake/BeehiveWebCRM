<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        color: red; font-weight: bold;
    }
</style>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/core/css/layout.css" />" />
     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  
</head>
<body>


<spring:url value="/updateCustomerView/${command.custID}" var="editcustomerUrl" />
<spring:url value="/addMeetingView/${command.custID}" var="addmeetingUrl" />
<spring:url value="/updateMeeting/${command.custID}?nomEtab=${activity.nom_etabl}" var="editmeetingUrl" />
<spring:url value="/removeMeeting" var="removemeetingUrl" />

    <div class="main-container">
        <div>
            <img src="<c:url value="/resources/core/images/logo.png" />" class="header-padding" />
           
            <div class="sub-header">Data Entry / Update Form</div>
        </div>
        <div class="content">
            <div class="content-padding">
                <div class="left-block padding-top-big dir-left">
                    <div class="ele-padding">
                        <h4>Details</h4>
                        <div class="ele-padding left-block-listitem">
                            <div class="padding-bottom ele-divider">Information</div>
                            <div class="padding-bottom padding-top ele-divider">Engagement / Activities</div>
                            <div class="padding-bottom padding-top ele-divider">History</div>
                            <div class="padding-bottom padding-top ele-divider">Contact</div>
                            <div class="padding-bottom padding-top ele-divider">Relationships</div>
                            <div class="padding-bottom padding-top ele-divider">Existing</div>
                        </div><br /><br /><br />
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
                     
                        <form:form action="editcustomer"  method="POST">
                            <div class="padding-top-big">
                                <div class="dir-left form-label"><form:label path="custName">Customer Name:</form:label></div>
                                <div class="dir-left form-input"> ${command.custName}</div>
                                 <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="address1">Address:</form:label></div>
                               <div class="dir-left form-input"> ${command.address1}</div>
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="phone">Email:</form:label></div>
                                <div class="dir-left form-input"> ${command.custEmail}</div>
                                <div class="clear"></div>
                               
                            </div><br /><br />
                            <div class="dir-left form-label">Customer Meetings:</div><br /><br/>
                           
						    <table>
                <thead>
                    <tr>
                        <th>Contact Person</th>
                        <th></th>
                        <th>Meeting Schedule</th>
                        <th></th>
                        <th>Meeting Agenda</th>
                        
                    </tr>
                </thead>
              <%--   <tbody id="meetingListContainer">
                    <c:forEach items="${meetingListContainer.meetingList}" var="Meeting" varStatus="i" begin="0" >
                        <tr class="meeting">    
                            <td><form:input path="meetingList[${i.index}].contactName" id="name${i.index}" disabled="true" /></td>
                            <td><form:errors path="meetingList[${i.index}].contactName" cssClass="error"/></td>
                            <td><form:input type="date" path="meetingList[${i.index}].day" id="day${i.index}" disabled="true"  /></td>
                            <td><form:errors path="meetingList[${i.index}].day" cssClass="error"/></td>
                            <td><form:textarea path="meetingList[${i.index}].agenda" id="agenda${i.index}"  disabled="true" /></td>
                            <td><form:errors path="meetingList[${i.index}].agenda" cssClass="error"/></td>
                                <c:url var="removeMeetingUrl" value="/removeMeeting">
                                    <c:param name="id" value="{meetingList[${i.index}].meetingId}" />
                                </c:url>
                            <td>  <a href="${editmeetingUrl}"> <input type="button" value="Update Meeting" /></a>&nbsp;&nbsp;</td>
                            
                             <td><a href="<c:url value="${removemeetingUrl} "  />"><input type="button" value="Remove Meeting" /></a>&nbsp;&nbsp;</td>
                           
                        </tr>
                    </c:forEach>
                </tbody> --%>
              
                <tbody id="meetingListContainer">
                    <c:forEach items="${meetingListContainer.meetingList}" var="Meeting" varStatus="i" begin="0" >
                        <tr class="meeting">  
                    
                            <td><form:input path="meetingList[${i.index}].contactName" id="name${i.index}" disabled="true" /></td>
                            <td><form:errors path="meetingList[${i.index}].contactName" cssClass="error"/></td>
                            <td><form:input path="meetingList[${i.index}].day" id="day${i.index}" disabled="true"  /></td>
                            <td><form:errors path="meetingList[${i.index}].day" cssClass="error"/></td>
                            <td><form:textarea path="meetingList[${i.index}].agenda" id="agenda${i.index}"  disabled="true" /></td>
                            
                            <td><form:errors path="meetingList[${i.index}].agenda" cssClass="error"/></td>
                                <c:url var="removeMeetingUrl" value="/removeMeeting">
                                    <c:param name="meetingId" value="${Meeting.meetingId}" />
                                    
                                </c:url>
                                <c:url var="updateMeetingUrl" value="/updateMeeting">
                                    <c:param name="meetingId" value="${Meeting.meetingId}" />
                                     <c:param name="custId" value="${command.custID}" />
                                </c:url>
                            <td>  <a href="${updateMeetingUrl}"> <input type="button" value="Update Meeting" /></a>&nbsp;&nbsp;</td>
                            
                             <td><a href="<c:url value="${removeMeetingUrl} "  />"><input type="button" value="Remove Meeting" /></a>&nbsp;&nbsp;</td>
                             
                           
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            
            
           <a href="${editcustomerUrl}"> <input type="button" value="Edit Customer" /></a>&nbsp;&nbsp;
           <a href="${addmeetingUrl}"> <input type="button" value="Add Meeting" /></a>&nbsp;&nbsp;
         
     
       	</form:form>
							
                            <div>
            
                            </div><br />
  
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