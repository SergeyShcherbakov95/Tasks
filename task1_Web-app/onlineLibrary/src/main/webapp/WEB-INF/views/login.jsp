<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Sign In</title>
  </head>
  <body>
  		<p><a href = "${contextPath}/" >Home</a></p>
    	<h3>Login with Email and Password</h3>
    	<c:if test = "${not empty error}">
    		<p><span style="color: #cc0000;">${error}</span></p>
    	</c:if>
    	<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
    	<form action="${loginUrl}" method="POST">
        	<table>
            	<tr>
                	<td>Email:</td>
                	<td><input type='text' name='email' /></td>
            	</tr>
            	<tr>
                	<td>Password:</td>
                	<td><input type='password' name='password' /></td>
            	</tr>
            	<tr>
                	<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
            	</tr>
        	</table>
    	</form>
</body>
</html>