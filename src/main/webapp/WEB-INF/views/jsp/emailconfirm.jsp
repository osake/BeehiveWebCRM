<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
        	$("#emailText").cleditor();
        	
        	$("#update").click(function() {
        		  $("#emailForm").submit();
        	});
       	});
    </script>    
</head>
<body>

<spring:url value="/sendemail" var="sendEmailUrl" />
<spring:url value="/submitUpdateEmail" var="submitUrl" />


    <div class="main-container">
        <div>
            <img src="${imageUrl}/logo.png" class="header-padding"/>
            <div class="sub-header">Email Form</div>
        </div>
        <div class="content">
            <div class="content-padding">
                <div class="left-block padding-top-big dir-left">
                    <div class="ele-padding">
                        <h4>Documents</h4>
                        <div class="ele-padding left-block-listitem">
                            <div class="padding-bottom ele-divider">Email Body Templates</div>
                            <div class="padding-bottom padding-top ele-divider">Collaterals</div>
                        </div><br /><br /><br />
                        <h4>Audit History</h4>
                        <div class="ele-padding left-block-listitem">
                            <div class="padding-bottom ele-divider">Saved Documents</div>
                            <div class="padding-bottom padding-top ele-divider">Saved Emails</div>
                            <div class="padding-bottom padding-top ele-divider">Previous Emails</div>
                            <div class="padding-bottom padding-top ele-divider">Customer Notes</div>
                        </div>
                    </div>
                </div>
		        <div class="right-block padding-top-big dir-right">
		        
		        	Email was sent successfully to ${command.email}.

		        </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">© Copyright - Otsuka</div>
    </div>


</body>
</html>