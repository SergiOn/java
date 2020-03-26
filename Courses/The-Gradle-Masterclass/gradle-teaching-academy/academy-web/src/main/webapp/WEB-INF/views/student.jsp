<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/academy-web/addStudent">
   <table>
<%--     <tr>
        <td>Name</td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td>Age</td>
        <td><form:input path="age" /></td>
    </tr> --%>
    <tr>
        <td>id</td>
        <td><form:input path="id" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>