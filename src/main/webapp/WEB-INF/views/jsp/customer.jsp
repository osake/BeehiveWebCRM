<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/resources/core/css" var="coreCss" />
<spring:url value="/resources/images" var="imageUrl" />
<spring:url value="/resources/core/js" var="jsUrl" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Mentrics</title>
    <link href="${coreCss}/layout.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="${coreCss}/jquery.cleditor.css" />
    <script src="${jsUrl}/jquery-2.1.4.js"></script>
    <script src="${jsUrl}/jquery.cleditor.js"></script>
    <script>
        $(document).ready(function () { 
        	
        	$("#update").click(function() {
        		  $("#emailForm").submit();
        	});
       	});
    </script>
</head>
<body>

<spring:url value="/sendemail" var="sendEmailUrl" />
<spring:url value="/customeredit" var="submitUrl" />


    <div class="main-container">
        <div>
            <img src="${imageUrl}/logo.png" class="header-padding"/>
            <div class="sub-header">Customer Form</div>
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
		        	<form:form action="${submitUrl}" id="emailForm">
                    <div class="ele-padding">
                        <div>
                            <div class="dir-left tab-btn tab-btn-active">General</div>
                            <div class="dir-left tab-btn">Details</div>
                            <div class="dir-left tab-btn">Contact</div>
                            <div class="dir-left tab-btn">Notes</div>
                            <div class="clear"></div>
                        </div>
                        <div class="ele-padding tab-content">
                            <div class="padding-top-big">
                                <div class="dir-left form-label">Account Name</div>
                                <form:input class="dir-left form-input" path="custName" required="required" type="text"/>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Account Email</div>
                                <form:input class="dir-left form-input" path="custEmail" required="required" type="email"/>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Primary Contact First Name</div>
                                <form:input class="dir-left form-input" path="firstName" required="required" type="text"/>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Primary Contact Last Name</div>
                                <form:input class="dir-left form-input" path="lastName" required="required" type="text"/>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Address Line 1</div>
                                <form:input class="dir-left form-input" path="address1" required="required" type="text"/>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Address Line 2</div>
                                <form:input class="dir-left form-input" path="address2" required="required" type="text"/>
                                <div class="clear"></div>
                            </div>
                            <div>
                                <div class="dir-left form-label">City</div>
                                <form:input class="dir-left form-input" path="city" required="required" type="text"/>
                                <div class="clear"></div>
                            </div><br />
                            <div class="padbtm" >
                                <div class="dir-left form-label">State</div>
                                <form:select path="state"  >        
				              		<form:options items="${command.states}" />
				        	  	</form:select>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Country</div>
                                <form:select path="country"  >        
				              		<form:option value="" label="Please Select"/>
				              		<form:option value="USA" label="USA"/>
				        	  	</form:select>
                                <div class="clear"></div>
                            </div>
                            <div class="padbtm" >
                                <div class="dir-left form-label">Stage</div>
									<form:select path="stage">
										<form:option value="" label="Please Select" />
										<form:option value="1. Pre Sales - Market Opportunity" />
										<form:option value="1. Pre Sales - Market Definition" />
										<form:option value="1. Pre Sales - Market Testing" />
										<form:option value="1. Pre Sales - Resource Allocation / Initial Contact" />
										<form:option value="2. Sales - Face to Face Meeting – Clinical Team" />
										<form:option value="2. Sales - Face to Face Meeting – IT Team" />
										<form:option value="2. Sales - Face to Face Meeting – Contract Discussion" />
										<form:option value="2. Sales - Pre-Implementation Planning" />
										<form:option value="3. Fulfillment - Implementation" />
										<form:option value="4. Post Sales - Client value realization and satisfaction" />
									</form:select>
									<div class="clear"></div>
                            </div>
                            <!-- <div>
                                <div class="dir-left form-label">Value</div>
                                <input class="dir-left form-input" type="text" />
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label">Size</div>
                                <input class="dir-left form-input" type="text" />
                                <div class="clear"></div>
                            </div><br />
                            <div>
                                <div class="dir-left form-label">Stage</div>
                                <input class="dir-left form-input" type="text" />
                                <div class="clear"></div>
                            </div><br /> -->
                            
                           	<div class="padding-top-big">
	                            <!-- <div class="dir-right form-btn">Save</div> -->
	                            <div class="dir-right form-btn" id="update">Update</div>
	                            <!-- <div class="dir-right form-btn">Review</div> -->
	                            <div class="clear"></div>
	                        </div>
                        </div>
                    </div>
                    
		        	</form:form>
		        </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">© Copyright - Otsuka</div>
    </div>


</body>
</html>