<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Form</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>

<h2>Customer Details 1</h2>
<form:form method="POST" action="submitUpdateEmail">
   	<table>
  
    <tr>
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" /></td>
        <td><form:errors path="firstName" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
         <td><form:errors path="lastName" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" /></td>
         <td><form:errors path="email" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="address">Address 1</form:label></td>
        <td><form:input path="address"  /></td>
        <td><form:errors path="address" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="country">Address 2</form:label></td>
        <td><form:input path="country" /></td>
        <td><form:errors path="country" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="state">State</form:label></td>
        <td><form:select path="state"  >        
              	  <form:options items="${command.states}" />
        	  </form:select>
        </td>
    </tr>
    <tr>
        <td><form:label path="city">City</form:label></td>
        <td><form:input path="city" /></td>
        <td><form:errors path="city" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="zip">Zip</form:label></td>
        <td><form:input path="zip" /></td>
        <td><form:errors path="zip" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="repName">Representative Name</form:label></td>
        <td><form:input path="repName" /></td>
        <td><form:errors path="repName" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="repTitle">Representative Title</form:label></td>
        <td><form:input path="repTitle" /></td>
        <td><form:errors path="repTitle" cssClass="error"/></td>
    </tr>
    <tr>
        <td>Document</td>
        <td><a href="${command.docUrl}">Download</a></td>
    </tr>                
    <tr>
        <td colspan="2">
            <input type="submit" value="Update"/>
        </td>
    </tr>
	</table>  
</form:form>


</body>
</html>