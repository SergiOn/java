<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>

<body>

	<sf:form method="post"
		action="docreate"
		commandName="registration">

		<table class="formtable">
			<tr>
				<td class="label">Student Id:</td>
				<td><sf:input class="control" path="studentId" name="studentId"
						type="text" /><br />				
			</tr>
			<tr>
				<td class="label">Course Id:</td>
				<td><sf:input class="control" path="courseId" name="courseId"
						type="text" /><br />
			</tr>			
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Register Student on Course" type="submit" /></td>
			</tr>
		</table>
	</sf:form>

</body>
</html>