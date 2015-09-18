<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Form Submission</title>
</head>
<body>

<h2>Submitted Email Information</h2>
   <table>
    <tr>
        <td>Name: </td>
        <td>${firstName} ${lastName}</td>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${email}</td>
    </tr>
    <tr>
        <td>Title: </td>
        <td>${title}</td>
    </tr>
     <tr>
        <td>Address: </td>
        <td>${address}</td>
    </tr>
    <tr>
        <td>Country: </td>
        <td>${country}</td>
    </tr>
        <tr>
        <td>State: </td>
        <td>${state}</td>
    </tr>
        <tr>
        <td>City: </td>
        <td>${city}</td>
    </tr>
    <tr>
        <td>Zip: </td>
        <td>${zip}</td>
    </tr>
    <tr>
        <td>Document Link: </td>
        <td><a href="${docLink}">Document</a></td>
    </tr>
</table>


</body>
</html>