<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Sign Up</title>
  </head>
  <body>
  		<p><a href = "${contextPath}/" >Home</a></p>
		<form action="${contextPath}/addUser" method = "POST" >
			<table>
            	<tr>
                	<td>Email:</td>
                	<td><input type='email' name='email' /></td>
            	</tr>
            	<tr>
                	<td>Password:</td>
                	<td><input type='password' name='password' /></td>
            	</tr>
            	<tr>
                	<td>Surname:</td>
                	<td><input type='text' name='surname' /></td>
            	</tr>
            	<tr>
                	<td>Name:</td>
                	<td><input type='text' name='name' /></td>
            	</tr>
            	<tr>
                	<td colspan='2'><input name="submit" type="submit" value="Sign Up" /></td>
            	</tr>
        	</table>
		</form>
  </body>
</html>