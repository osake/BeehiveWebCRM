<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<spring:url value="/submitupdatedcustomer" var="updateCustometAction" />

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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<link href="<c:url value="/resources/core/css/bvalidator.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/core/js/jquery.bvalidator-yc.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/core/css/layout.css" />" />
</head>
<body>




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
                        <h3>${message}</h3> 
                        <form:form id="updatecustomer_form" action="${updateCustometAction}" method="POST">
                        
                            
                            <div class="padding-top-big">
                                <div class="dir-left form-label"><form:label path="custName">Customer Name</form:label></div>
                                <form:input path="custName" class="dir-left form-input" data-bvalidator="alpha,required" />
                                <form:errors path="custName" cssClass="error"/>
                                 <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="address1">Address</form:label></div>
                                <form:input path="address1" class="dir-left form-input" data-bvalidator="alphanum,required" />
                                <form:errors path="address1" cssClass="error"/>
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="phone">Phone</form:label></div>
                                <form:input path="phone" class="dir-left form-input" data-bvalidator="number,minlength[10],maxlength[10],required"/>
                                <form:errors path="phone" cssClass="error"/>
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label"><form:label path="phone">Email</form:label></div>
                                <form:input path="custEmail" class="dir-left form-input" data-bvalidator="email,required" />
                                <form:errors path="custEmail" cssClass="error"/>
                                <div class="clear"></div>
                               
                            </div><br /><br />
                            
                             <form:hidden path="custID"  />
     
            <input type="submit" value="Update" id="submit" />&nbsp;&nbsp;
     
     
       	</form:form>
<script type="text/javascript"> 
    $(document).ready(function () {
        $('#updatecustomer_form').bValidator();
    });
</script>							
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