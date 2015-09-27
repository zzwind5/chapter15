<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head><title>New Users</title></head>
	<body>
		<form method="post" action="<c:url value='/user.html'/>">
			<table>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="userName"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td>Real Name:</td>
					<td><input type="text" name="realName"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>